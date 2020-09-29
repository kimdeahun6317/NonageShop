package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.QnADao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.QnA;

public class QnADaoImpl implements QnADao {
	private static final QnADaoImpl instance = new QnADaoImpl();

	private QnADaoImpl() {
	}

	public static QnADao getInstance() {
		return instance;
	}

	@Override
	public ArrayList<QnA> listQna(String id) {
		String sql = "SELECT NO,SUBJECT,CONTENT,REPLY,ID,REPLY_USEYN,WRITE_DATE FROM QNA WHERE id =? ORDER BY NO DESC";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<QnA> list = new ArrayList<QnA>();
					do {
						list.add(getQna(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	private QnA getQna(ResultSet rs) throws SQLException {
		int no = rs.getInt("NO");
		String subject = rs.getString("SUBJECT");
		String content = rs.getString("CONTENT");
		String rep = rs.getString("REPLY");
		String id = rs.getString("ID");
		String repYN = rs.getString("REPLY_USEYN");
		Date writeDate = rs.getDate("WRITE_DATE");
		return new QnA(no, subject, content, rep, id, repYN, writeDate);
	}

	@Override
	public QnA getQnA(int no) {
		String sql = "SELECT NO,SUBJECT,CONTENT,REPLY,ID,REPLY_USEYN,WRITE_DATE FROM QNA WHERE NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					do {
						return getQna(rs);
					} while (rs.next());
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public int insertQna(QnA qna) {
		String sql = "insert into qna (SUBJECT,CONTENT,ID)values(?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, qna.getSubject());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, qna.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

}
