package [##_Project-Package-DAO_##];

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

import [##_Project-Package-Config_##];
import [##_Project-Package-Bean_##].[##_Subject-Bean-Name_##];

public class [##_Subject-Name_##]DAO  {
	
	public ArrayList<[##_Subject-Bean-Name_##]> getList() {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM [##_Subject-Name-lowercase_##];", new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		Gson gson = new Gson();
		ArrayList<[##_Subject-Bean-Name_##]> selectedList = gson.fromJson(gson.toJson(listOfMaps), new TypeToken<List<[##_Subject-Bean-Name_##]>>() {}.getType());
		
		return selectedList;
	}
	
	public String add(String data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> maps = new Gson().fromJson(data, Map.class);;
		
		String result = null;
		Connection conn = Config.getInstance().sqlLogin();
		List<Map<String, Object>> listOfMaps = null;
		try {
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(conn, "[##_Subject-DAO-Content-add-sql_##]", [##_Subject-DAO-Content-add-sql-value_##]);
			
			listOfMaps = queryRunner.query(conn, "SELECT LAST_INSERT_ID();", new MapListHandler());
			if(!listOfMaps.isEmpty())
				result = listOfMaps.get(0).get("LAST_INSERT_ID()") + "";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return result;
	}
	
	public String edit(String data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> maps = new Gson().fromJson(data, Map.class);;
		
		String result = null;
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(conn, "[##_Subject-DAO-Content-edit-sql_##]", [##_Subject-DAO-Content-edit-sql-value_##]);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return result;
	}
	
	public String delete(String data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> maps = new Gson().fromJson(data, Map.class);
		
		String result = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(conn, "[##_Subject-DAO-Content-delete-sql_##]", [##_Subject-DAO-Content-delete-sql-value_##]);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return result;
	}
}
