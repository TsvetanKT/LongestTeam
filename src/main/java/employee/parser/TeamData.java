package employee.parser;

public class TeamData {

	private int employeeId1;
	private int employeeId2;
	private int projectId;
	private int daysWorked;
	
	public TeamData(int employeeId1, int employeeId2, int projectId, int daysWorked) {
		this.employeeId1 = employeeId1;
		this.employeeId2 = employeeId2;
		this.projectId = projectId;
		this.daysWorked = daysWorked;
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
	
	public int getProjectId() {
		return projectId;
	}
	
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public int getDaysWorked() {
		return daysWorked;
	}
	
	public void setDaysWorked(int daysWorked) {
		this.daysWorked = daysWorked;
	}
}
