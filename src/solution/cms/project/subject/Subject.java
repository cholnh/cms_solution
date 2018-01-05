package solution.cms.project.subject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Subject
 * 
 * @version 1.0 [2018. 1. 5.]
 * @author Choi
 */
public class Subject {

	private String subjectName;
	
	private Map<String, DataType> column;
	
	public Subject(String subjectName) {
		this.subjectName = subjectName;
		column = new HashMap<>();
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Map<String, DataType> getColumn() {
		return column;
	}

	public void setColumn(Map<String, DataType> column) {
		this.column = column;
	}

	public void addColumn(String name, DataType type) {
		column.put(name, type);
	}
	
	public Map<String, String> getMap() {
	
		Map<String, String> map = new HashMap<>();
		
		// subject
		map.put("Subject-Name", subjectName);
		map.put("Subject-Name-lowercase", subjectName.toLowerCase());
		
		// action
		map.put("Subject-Action-Name", subjectName + "Action");
		
		// beans
		String beanContent = "\tpublic Integer id" + subjectName + ";" + System.lineSeparator();
		Object[] colset = column.keySet().toArray();
		for(int i=0; i<colset.length; i++) {
			String name = colset[i]+"";
			DataType type = column.get(name);
			beanContent += "\tpublic " + type + " " + name + ";" + System.lineSeparator();
		}
		map.put("Subject-Bean-Name", subjectName + "Bean");
		map.put("Subject-Bean-Content", beanContent);
		
		
		// dao
		map.put("Subject-DAO-Name", subjectName + "DAO");
		map.put("Subject-DAO-Alias", subjectName.toUpperCase().charAt(0) + "dao");
		
		// sql
		String sqlQ = "null,";
		String sqlValue = "";
		for(int i=0; i<colset.length; i++) {
			sqlValue += colset[i]+"";
			
			if(i!=0) sqlQ += "?";
			
			if(i!=colset.length-1) {
				if(i!=0) sqlQ += ",";
				sqlValue += ",";
			}
		}
		map.put("Subject-DAO-Content-add-sql", "INSERT INTO " + subjectName.toLowerCase() + " VALUES(" + sqlQ + ");");
		map.put("Subject-DAO-Content-add-sql-value", sqlValue);
		
		sqlQ = "";
		sqlValue = "";
		for(int i=0; i<colset.length; i++) {
			String name = colset[i]+"";
			DataType type = column.get(name);
			
			sqlQ += name + "=?";
			sqlValue += "maps.get(\"" + name + "\")";
			
			if(i!=colset.length-1) {
				sqlQ += ",";
				sqlValue += ",";
			}
		}
		sqlQ += " WHERE id" + subjectName + "=?;";
		sqlValue += "," + "maps.get(\"id" + subjectName + "\")";
		
		map.put("Subject-DAO-Content-edit-sql", "UPDATE " + subjectName.toLowerCase() + " SET " + sqlQ);
		map.put("Subject-DAO-Content-edit-sql-value", sqlValue);
		
		map.put("Subject-DAO-Content-delete-sql", "DELETE FROM " + subjectName + " WHERE id" + subjectName + " = ?;");
		map.put("Subject-DAO-Content-delete-sql-value", "maps.get(\"id" + subjectName + "\")");
		
		return map;
	}
	
}
