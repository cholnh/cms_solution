package solution.cms.project;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import solution.cms.common.util.FileManager;
import solution.cms.project.subject.Subject;

/**
 * Project
 * 
 * @version 1.0 [2017. 12. 18.]
 * @author Choi
 */
public class Project {

	private String	projectPath;
	private String 	projectName;
	private File 	projectFile;
	
	private List<Subject> subject;
	
	public static void main(String...args) {
		Project p = new Project("test");
	}
	
	public Project(String projectName) {
		this.projectName = projectName;
		this.projectPath = "";
		this.projectFile = new File(projectPath + projectName);
		
		try {
			FileManager.fcopy(new File("dummy"), projectFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Map<String, String> getProjectMap() {
		/*
		Project 이름		[##_Project-Name_##]

		패키지 이름			[##_Project-Package-Name_##]					ex) com.solution.cms
						[##_Project-Package-Bean_##]					ex) com.solution.cms.handler.vo
						[##_Project-Package-Config_##]					ex) com.solution.cms.common.sql.Config
						[##_Project-Package-DAO_##]						ex) com.solution.cms.handler.dao
						[##_Project-Package-Action_##]					ex) com.solution.cms.handler.action

		Subject 이름		[##_Subject-Name_##]							ex) Category
						[##_Subject-Name-lowercase_##]					ex) category

		Action 이름		[##_Subject-Action-Name_##]						ex) CategoryAction
						
		Beans 이름		[##_Subject-Bean-Name_##]						ex) CategoryBean
		Beans 내용		[##_Subject-Bean-Content_##]					ex) Public Integer idx;

		Subject Column	[##_Subject-Column-Name_##]						ex) idCommonPath
						[##_Subject-Column-Type_##]						ex) String
						[##_Subject-Column-Size_##]						ex) 5
						
		DAO 이름			[##_Subject-DAO-Name_##]						ex) CategoryDAO
		DAO 이름			[##_Subject-DAO-Alias_##]						ex) Cdao

		add	sql			[##_Subject-DAO-Content-add-sql_##] 			ex) INSERT INTO [Subject-Name] VALUES(?, ?, ?, ?, ?, ? .... );
						[##_Subject-DAO-Content-add-sql-value_##]		ex) null, maps.get("[Column1-Name]"), maps.get("[Column2-Name]") .... 

		edit sql		[##_Subject-DAO-Content-edit-sql_##] 			ex) UPDATE [Subject-Name] SET [maps.get("[Column1-Name]")] = ?, [NAMELIST] = ?, [PARENTIDX] = ? .... WHERE [idx] = ?;
						[##_Subject-DAO-Content-edit-sql-value_##]		ex) null, maps.get("[Column1-Name]"), maps.get("[Column2-Name]") .... maps.get("[targetIdx]")
						
		delete sql		[##_Subject-DAO-Content-delete-sql_##]			ex) DELETE FROM [Subject-Name] WHERE indexCATEGORY = ?;
						[##_Subject-DAO-Content-delete-sql-value_##]	ex) maps.get("[targetIdx]")
		*/
		
		Map<String, String> map = new HashMap<>();
		
		// project
		map.put("Project-Name", "");
		
		// package
		map.put("Project-Package-Name", "");
		map.put("Project-Package-Bean", "");
		map.put("Project-Package-Config", "");
		map.put("Project-Package-DAO", "");
		map.put("Project-Package-Action", "");
		
		// subject
		map.put("Subject-Name", "");
		map.put("Subject-Name-lowercase", "");
		
		// column
		map.put("Subject-Column-Name", "");
		map.put("Subject-Column-Type", "");
		map.put("Subject-Column-Size", "");
		
		// action
		map.put("Subject-Action-Name", "");
		
		// beans
		map.put("Subject-Bean-Name", "");
		map.put("Subject-Bean-Content", "");
		
		// dao
		map.put("Subject-DAO-Name", "");
		map.put("Subject-DAO-Alias", "");
		
		// sql
		map.put("Subject-DAO-Content-add-sql", "");
		map.put("Subject-DAO-Content-add-sql-value", "");
		map.put("Subject-DAO-Content-edit-sql", "");
		map.put("Subject-DAO-Content-edit-sql-value", "");
		map.put("Subject-DAO-Content-delete-sql", "");
		map.put("Subject-DAO-Content-delete-sql-value", "");
		
		return map;
		
	}


}
