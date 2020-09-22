package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import nonageshop.dao.MemberDao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Member;

public class MemberDaoImpl implements MemberDao {
	private static final MemberDaoImpl instance = new MemberDaoImpl();

	public static MemberDaoImpl getInstance() {
		return instance;
	}

	private MemberDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int confirmID(String userid) {
		String sql = "SELECT ID,PWD,NAME,EMAIL,ZIP_NO,ADDRESS,PHONE,UNSIGN_USEYN,JOIN_DATE FROM MEMBER WHERE ID = ?";
		int result = -1;
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = 1;
				} else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return result;
	}

	public Member getMember(ResultSet rs) throws SQLException {
		String id = rs.getString("ID");
		String pwd = rs.getString("PWD");
		String name = rs.getString("NAME");
		String email = rs.getString("EMAIL");
		String zipNo = rs.getString("ZIP_NO");
		String address = rs.getString("ADDRESS");
		String phone = rs.getString("PHONE");
		String useyn = rs.getString("UNSIGN_USEYN");
		Date joindate = rs.getDate("JOIN_DATE");
		return new Member(id, pwd, name, email, zipNo, address, phone, useyn, joindate);
	}

	@Override
	public Member getMember(String id) {
		String sql = "SELECT ID,PWD,NAME,EMAIL,ZIP_NO,ADDRESS,PHONE,UNSIGN_USEYN,JOIN_DATE FROM MEMBER WHERE ID = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getMember(rs);
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public int insertMember(Member member) {
		String sql = "INSERT INTO MEMBER(ID, PWD, NAME, ZIP_NO, ADDRESS, PHONE) VALUES (?, ?,?, ?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getZipNo());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

}
