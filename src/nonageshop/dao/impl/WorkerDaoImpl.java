package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nonageshop.dao.WorkerDao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Worker;

public class WorkerDaoImpl implements WorkerDao {
	private static final WorkerDaoImpl instance = new WorkerDaoImpl();

	public static WorkerDaoImpl getInstance() {
		return instance;
	}

	private WorkerDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int workerCheck(Worker worker) {
		String sql = "SELECT 1 FROM worker WHERE id=? AND pwd=?";
        try (Connection con = JdbcUtil.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, worker.getId());
            pstmt.setString(2, worker.getPwd());
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 0;
	}

}
