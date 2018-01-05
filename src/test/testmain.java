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
		
		

	}
}
