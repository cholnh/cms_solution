package solution.cms.handler.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import solution.cms.common.sql.Config;
import solution.cms.handler.vo.CommonPathBean;

/**
 * CommonPathDAO
 * 
 * @version 1.0 [2018. 1. 4.]
 * @author Choi
 */
public class CommonPathDAO {
	public ArrayList<CommonPathBean> getList() {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM commonpath;", new MapListHandler());
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		Gson gson = new Gson();
		ArrayList<CommonPathBean> selectedList = gson.fromJson(gson.toJson(listOfMaps), new TypeToken<List<CommonPathBean>>() {}.getType());
		return selectedList;
	}

	
	public String getValue(String key) {
		String result = null;
		
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT value FROM commonpath WHERE commonpath.key = '"+key+"';", new MapListHandler());
			if(!listOfMaps.isEmpty())
				result = listOfMaps.get(0).get("value")+"";
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		
		return result;
	}
}
