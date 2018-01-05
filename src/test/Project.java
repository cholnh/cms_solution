package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	
	public static void main(String...args) {
		Project p = new Project("test");
	}
	
	public Project(String projectName) {
		this.projectName = projectName;
		this.projectPath = "";
		this.projectFile = new File(projectPath + projectName);
		
		try {
			fcopy(getDefaultDummy(), projectFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private File getRoot() {
		return projectFile;
	}
	
	public static void fcopy(File sourceLocation, File targetLocation) throws IOException {
		if (sourceLocation.isDirectory()) {
			fcopyDirectory(sourceLocation, targetLocation);
		} else {
			fcopyFile(sourceLocation, targetLocation);
		}
	}

	private static void fcopyDirectory(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String f : source.list()) {
			fcopy(new File(source, f), new File(target, f));
		}
	}

	private static void fcopyFile(File source, File target) throws IOException {        
		try (
				InputStream in = new FileInputStream(source);
				OutputStream out = new FileOutputStream(target)
				) {
			byte[] buf = new byte[1024];
			int length;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
			}
		}
	}
	
	private File getDefaultDummy() {
		return new File("dummy");
	}
}
