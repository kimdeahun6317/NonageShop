package nonageshop.ds.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;

public class JdbcUtilTest {

	@Test
	public void testGetConnection() {
		System.out.println("testGetConnection()");
		Connection con = JdbcUtil.getConnection();
		Assert.assertNotNull(con);
	}

}
