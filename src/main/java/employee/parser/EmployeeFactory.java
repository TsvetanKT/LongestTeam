package employee.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import employee.parser.EmployeeData;
import utils.ResourceUtils;
import utils.TimeUtils;

public class EmployeeFactory {
	
	private static final String CSV_SEPARATOR = ",";

	public static List<EmployeeData> getEmployeesFromResourceFile(String resourceName) {
		String content = ResourceUtils.loadResourceFileAsString(resourceName);
		return getEmployeesFromString(content);
	}
	
	public static List<EmployeeData> getEmployeesFromFile(File file) {
		String content = ResourceUtils.loadFileString(file);
		return getEmployeesFromString(content);
	}
	
	private static List<EmployeeData> getEmployeesFromString(String csvContent) {
		String[] lines = csvContent.split("\n");
		
		List<EmployeeData> empData = new ArrayList<>();
		
		// Skip the 1 line - no useful data there
		for (int i = 1; i < lines.length; i++) {
			String[] args = lines[i].split(CSV_SEPARATOR);
			
			EmployeeData employee = new EmployeeData(
				Integer.parseInt(args[0]),	
				Integer.parseInt(args[1]),	
				TimeUtils.parseLocalDate(args[2]),
				TimeUtils.parseLocalDate(args[3]));
			
			empData.add(employee);
		}
		
		return empData;
	}
	
}
