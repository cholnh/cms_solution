package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import solution.cms.handler.dao.CommonPathDAO;

public class testmain {

	public static void main(String...args) {
		namelist();
	}
	
	public static void fileTest() {
		String testkey = new CommonPathDAO().getValue("templete");
		File f = new File(testkey);

		System.out.println("testkey : "+testkey);
		System.out.println(f.exists() + " " + f.getAbsolutePath());
	}
	
	public static void namelist() {
		
//		final String sub_ProjectName = "\\[##_Project-Name_##\\]";
//		
//		String temp = "sdfsjfljfl [##_Project-Name_##] zxczxczttttttt[##_Project-Name_##] [##_Project-Name_##]tttttsd";
//		System.out.println(temp.replaceAll(sub_ProjectName, ProjectName));
		Map<String, String> map = new HashMap<>();
		map.put("Project-Package-Bean", "CMS_solution");
		map.put("Subject-Bean-Name", "CategoryBean");
		map.put("Subject-Bean-Content", "String idx;");
		
		CommonPathDAO Cdao = new CommonPathDAO();
		try {
			String result = "";
			Scanner s1 = new Scanner(new File(Cdao.getValue("vo")));
			Set<String> set = new HashSet<>();
			
			while(s1.hasNext()) {
				String line = s1.nextLine();
				
				char[] lines = line.toCharArray();
				for(int i=0; i<lines.length;i++) {
					String from = "";
					//System.out.println(lines[i]);
					if(lines[i]=='['&&lines[i+1]=='#'&&lines[i+2]=='#'&&lines[i+3]=='_') {
						for(int j=i+4; j<lines.length;j++) {
							if(lines[j]=='_'&&lines[j+1]=='#'&&lines[j+2]=='#'&&lines[j+3]==']') {
								i = j+3;
								break;
							}
							else
								from += lines[j];
						}
					}
					if(!from.equals("")) 
						set.add(from);
				}
				
				result += line + System.lineSeparator();
			}
			s1.close();
			
			for(String replace : set) {
				String value = map.get(replace);
				if(value != null)
					result = result.replaceAll("\\[##_"+replace+"_##\\]", value);
			}
			System.out.println(result);
			
			
//			final String ProjectName = "CMS_solution";	// user input
//			final String rep_ProjectName = "\\[##_Project-Name_##\\]";
//			
//			final String SubjectBeanName = "CMS_solution";	// user input
//			final String rep_SubjectBeanName = "\\[##_Subject-Bean-Name_##\\]";
//			
			
			
			
			//result = result.replaceAll(rep_SubjectBeanName, SubjectBeanName);
			//System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		
//		Project 이름		[##_Project-Name_##]
//
//		패키지 이름			[##_Project-Package-Name_##]					ex) com.solution.cms
//						[##_Project-Package-Bean_##]					ex) com.solution.cms.handler.vo
//						[##_Project-Package-Config_##]					ex) com.solution.cms.common.sql.Config
//						[##_Project-Package-DAO_##]						ex) com.solution.cms.handler.dao
//						[##_Project-Package-Action_##]					ex) com.solution.cms.handler.action
//
//		Subject 이름		[##_Subject-Name_##]							ex) Category
//						[##_Subject-Name-lowercase_##]					ex) category
//
//		Action 이름		[##_Subject-Action-Name_##]						ex) CategoryAction
//						
//		Beans 이름		[##_Subject-Bean-Name_##]						ex) CategoryBean
//		Beans 내용		[##_Subject-Bean-Content_##]					ex) Public Integer idx;
//
//		Subject Column	[##_Subject-Column-Name_##]						ex) idCommonPath
//						[##_Subject-Column-Type_##]						ex) String
//						[##_Subject-Column-Size_##]						ex) 5
//						
//		DAO 이름			[##_Subject-DAO-Name_##]						ex) CategoryDAO
//		DAO 이름			[##_Subject-DAO-Alias_##]						ex) Cdao
//
//		add	sql			[##_Subject-DAO-Content-add-sql_##] 			ex) INSERT INTO [Subject-Name] VALUES(?, ?, ?, ?, ?, ? .... );
//						[##_Subject-DAO-Content-add-sql-value_##]		ex) null, maps.get("[Column1-Name]"), maps.get("[Column2-Name]") .... 
//
//		edit sql		[##_Subject-DAO-Content-edit-sql_##] 			ex) UPDATE [Subject-Name] SET [maps.get("[Column1-Name]")] = ?, [NAMELIST] = ?, [PARENTIDX] = ? .... WHERE [idx] = ?;
//						[##_Subject-DAO-Content-edit-sql-value_##]		ex) null, maps.get("[Column1-Name]"), maps.get("[Column2-Name]") .... maps.get("[targetIdx]")
//						
//		delete sql		[##_Subject-DAO-Content-delete-sql_##]			ex) DELETE FROM [Subject-Name] WHERE indexCATEGORY = ?;
//						[##_Subject-DAO-Content-delete-sql-value_##]	ex) maps.get("[targetIdx]")
//					
	}
}
