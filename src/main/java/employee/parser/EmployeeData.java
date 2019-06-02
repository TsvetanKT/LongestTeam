package employee.parser;

import java.time.LocalDate;
import java.util.Date;

import utils.TimeUtils;

public class EmployeeData {

	private final String TODAY_KEYWORD = "NULL";

	private int empId;
	private int projectId;
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public EmployeeData(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
		this.empId = empId;
		this.projectId = projectId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s", this.empId, this.projectId, this.dateFrom, this.dateTo);
	}
}
