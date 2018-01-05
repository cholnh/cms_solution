package com.manual.douzone.handler.dao;

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

import com.manual.douzone.common.sql.Config;
import com.manual.douzone.handler.vo.CategoryBean;

public class CategoryDAO  {
	
	public ArrayList<CategoryBean> getList() {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM category;", new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		Gson gson = new Gson();
		ArrayList<CategoryBean> selectedList = gson.fromJson(gson.toJson(listOfMaps), new TypeToken<List<CategoryBean>>() {}.getType());
		
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
			queryRunner.update(conn, "INSERT INTO category VALUES(null,?,?);", contents,count,title);
			
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
			queryRunner.update(conn, "UPDATE category SET contents=?,count=?,title=? WHERE idCategory=?;", maps.get("contents"),maps.get("count"),maps.get("title"),maps.get("idCategory"));
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
			queryRunner.update(conn, "DELETE FROM Category WHERE idCategory = ?;", maps.get("idCategory"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return result;
	}
}
