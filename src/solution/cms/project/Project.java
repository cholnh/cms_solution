package solution.cms.project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import solution.cms.common.util.FileManager;
import solution.cms.handler.dao.CommonPathDAO;
import solution.cms.project.subject.DataType;
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
	
	private String 	packageName;
	
	private List<Subject> subject;
	
	public static void main(String...args) {
		Project p = new Project("douzone", "com.manual.douzone");
		Subject sub = new Subject("Category");
		sub.addColumn("title", DataType.String);
		sub.addColumn("contents", DataType.String);
		sub.addColumn("count", DataType.Integer);
		
		p.addSubject(sub);
		p.make();
	}
	
	public Project(String projectName) {
		this.projectName = projectName;
		this.projectPath = "";
		this.projectFile = new File(projectPath + projectName);
		this.packageName = projectName;
	}
	
	public Project(String projectName, String packageName) {
		this(projectName);
		this.packageName = packageName;
	}
	
	public void addSubject(String subjectName) {
		addSubject(new Subject(subjectName));
	}
	
	public void addSubject(Subject s) {
		if(subject == null) 
			subject = new ArrayList<>();
		subject.add(s);
	}
	
	public void make() {
		CommonPathDAO cdao = new CommonPathDAO();
		Map<String, String> map = getMap();
		
		try {
			File source = new File(cdao.getValue("dummy"));
			File target = new File(cdao.getValue("result")+"/"+projectName);
			FileManager.fcopy(source, target);
			
			for(Subject s : subject) {
				Map<String, String> smap = s.getMap();
				smap.putAll(map);
				String action 	= replace(cdao.getValue("action"), smap);
				String dao 		= replace(cdao.getValue("dao"), smap);
				String vo 		= replace(cdao.getValue("vo"), smap);
				
				String actionPath = target.getAbsolutePath()+"/"+cdao.getValue("put-action")+"/"+smap.get("Subject-Action-Name")+".java";
				String daoPath = target.getAbsolutePath()+"/"+cdao.getValue("put-dao")+"/"+smap.get("Subject-DAO-Name")+".java";
				String voPath = target.getAbsolutePath()+"/"+cdao.getValue("put-vo")+"/"+smap.get("Subject-Bean-Name")+".java";
				
				FileManager.write(actionPath, action, false);
				FileManager.write(daoPath, dao, false);
				FileManager.write(voPath, vo, false);
			}
			
			File[] files = target.listFiles();
			for(File file : files) {
				if(file.getName().equals("src")) {
					File packageDir = file.listFiles()[0];
					packageDir.renameTo(new File(file.getAbsolutePath()+"\\"+packageName));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String replace(String path, Map<String, String> map) {
		String result = "";
		
		try {
			Scanner scan = new Scanner(new File(path));
			Set<String> set = new HashSet<>();
			
			while(scan.hasNext()) {
				String line = scan.nextLine();
				
				char[] lines = line.toCharArray();
				for(int i=0; i<lines.length;i++) {
					String from = "";
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
			scan.close();
			
			for(String replace : set) {
				String value = map.get(replace);
				if(value != null)
					result = result.replaceAll("\\[##_"+replace+"_##\\]", value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
		}
		
		return result;
	}
	
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<>();
		
		// project
		map.put("Project-Name", projectName);
		
		// package
		map.put("Project-Package-Name", 	packageName);
		map.put("Project-Package-Bean", 	packageName + ".handler.vo");
		map.put("Project-Package-DAO", 		packageName + ".handler.dao");
		map.put("Project-Package-Action", 	packageName + ".handler.action");
		map.put("Project-Package-Config", 	packageName + ".common.sql.Config");
	
		/*
		for(Subject s : subject) {
			map.putAll(s.getMap());
		}
		*/
		return map;
	}


}
