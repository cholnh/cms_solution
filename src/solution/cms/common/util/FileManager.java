package solution.cms.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * FileManager
 * 
 * @version 1.0 [2018. 1. 5.]
 * @author Choi
 */
public class FileManager {

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
	
}
