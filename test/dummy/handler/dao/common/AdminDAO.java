package com.dummy.handler.dao.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dummy.common.sql.Config;
import com.dummy.handler.vo.MemberBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AdminDAO {
	
	public String siteCheck(HttpSession session) {
		return session.getId();
	}

	public boolean getUsername(String username) {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM member WHERE username = BINARY(?)", new MapListHandler(), username);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		if(listOfMaps.size() == 1)
			return true;
		else return false;
	}

	public boolean getPassword(String username, String password) {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM member WHERE username = BINARY(?) AND password = BINARY(?)", new MapListHandler(), username, password);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		if(listOfMaps.size() == 1)
			return true;
		else return false;
	}

	public MemberBean getUserInfo(String id, String pw) {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM member WHERE username = BINARY(?) AND password = BINARY(?)", new MapListHandler(), id, pw);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		Gson gson = new Gson();
		ArrayList<MemberBean> selectedList = gson.fromJson(gson.toJson(listOfMaps), new TypeToken<List<MemberBean>>() {}.getType());
		
		return selectedList.get(0);
	}

	public ArrayList<MemberBean> getAdminList() {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM member order by idmember desc;", new MapListHandler());
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		Gson gson = new Gson();
		ArrayList<MemberBean> selectedList = gson.fromJson(gson.toJson(listOfMaps), new TypeToken<List<MemberBean>>() {}.getType());
		
		return selectedList;
	}

	public MemberBean getAdmin(String username) {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM member WHERE username = BINARY(?)", new MapListHandler(), username);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		Gson gson = new Gson();
		ArrayList<MemberBean> selectedList = gson.fromJson(gson.toJson(listOfMaps), new TypeToken<List<MemberBean>>() {}.getType());
		
		return selectedList.get(0);
	}

	public String editAdmin(String data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> maps = new Gson().fromJson(data, Map.class);
		
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(conn, "UPDATE member SET password = ?, nickname = ?, flag = ? WHERE username = ?", maps.get("password"), maps.get("nickname"), maps.get("flag"), maps.get("username"));
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		
		return "";
	}

	public void deleteAdmin(String username) {
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(conn, "DELETE FROM member WHERE username = ?", username);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public String addAdmin(String data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> maps = new Gson().fromJson(data, Map.class);
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String regdate = sdf.format(today);
		
		if(checkUsername((String) maps.get("username")).equals("false"))
			return "";
		
		if(checkEmail((String) maps.get("email")).equals("false"))
			return "";
		
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(conn, "INSERT INTO member VALUES(0, ?, ?, ?, ?, ?)", maps.get("username"), maps.get("password"), maps.get("nickname"),  regdate, maps.get("flag"));
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		
		return "";
	}

	public String checkUsername(String username) {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM member WHERE username = BINARY(?)", new MapListHandler(), username);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		if(listOfMaps.isEmpty())
			return "true";
		else return "false";
	}

	public String checkEmail(String email) {
		List<Map<String, Object>> listOfMaps = null;
		Connection conn = Config.getInstance().sqlLogin();
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(conn, "SELECT * FROM member WHERE email = BINARY(?)", new MapListHandler(), email);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		if(listOfMaps.isEmpty())
			return "true";
		else return "false";
	}
}
