package solution.cms.project.subject;

import java.util.HashMap;
import java.util.Map;

/**
 * Subject
 * 
 * @version 1.0 [2018. 1. 5.]
 * @author Choi
 */
public class Subject {

	private String Name;
	private Map<String, DataType> column;
	
	public Subject(String Name) {
		this.Name = Name;
		column = new HashMap<>();
	}
	
	public void addColumn(String name, DataType type) {
		column.put(name, type);
	}
	
}
