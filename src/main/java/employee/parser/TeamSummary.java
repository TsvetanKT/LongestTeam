package employee.parser;

import java.util.HashMap;
import java.util.Map;

public class TeamSummary {
	
	private int employeeId1;
	private int employeeId2;
	private Map<Integer, Integer> projects;
	private int totalDaysTogether;
	
	public TeamSummary(int employeeId1, int employeeId2) {
		this.employeeId1 = employeeId1;
		this.employeeId2 = employeeId2;
		this.projects = new HashMap<>();
	}

	public int getEmployeeId1() {
		return employeeId1;
	}

	public void setEmployeeId1(int employeeId1) {
		this.employeeId1 = employeeId1;
	}

	public int getEmployeeId2() {
		return employeeId2;
	}

	public void setEmployeeId2(int employeeId2) {
		this.employeeId2 = employeeId2;
	}

	public Map<Integer, Integer> getProjects() {
		return projects;
	}

	public void setProjects(Map<Integer, Integer> projects) {
		this.projects = projects;
	}
	
	public int getTotalDaysTogether() {
		return totalDaysTogether;
	}

	public void setTotalDaysTogether(int totalDaysTogether) {
		this.totalDaysTogether = totalDaysTogether;
	}

	public void addProjectDays(int projectId, int workingDays) {
		if (this.projects.containsKey(projectId)) {
			this.projects.put(projectId, this.projects.get(projectId) + workingDays);
		} else { 
			this.projects.put(projectId, workingDays);
		}
		
		this.totalDaysTogether += workingDays;
	}
}
