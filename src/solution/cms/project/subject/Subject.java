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
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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
	
}
