package solution.cms.handler.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import solution.cms.common.sql.Config;

public class DAO {
	
	public String delete(String data) {
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(conn, "DELETE FROM category WHERE indexCATEGORY = ?;", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}
	
}
