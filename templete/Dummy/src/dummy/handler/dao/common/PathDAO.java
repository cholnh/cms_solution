package com.dummy.handler.dao.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dummy.common.sql.Config;

/**
 * PathDAO
 * 
 * @version 1.0 [2017. 12. 18.]
 * @author Choi
 */
public class PathDAO {

	public String getPath(String key) {
		String result = null;
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT value FROM common-path where key = '" + key + "';", new MapListHandler());
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		
		if(listOfMaps!=null && !listOfMaps.isEmpty())
			result = (String) listOfMaps.get(0).get("key");
		
		return result;
	}
}
