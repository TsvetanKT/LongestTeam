package teamTests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import employee.parser.EmployeeData;
import employee.parser.EmployeeFactory;
import employee.parser.TeamData;
import employee.parser.TeamGenerator;
import employee.parser.TeamSummary;

public class TeamGenerationTests {

	@Test
	public void generateTeamsPositive() {
		List<EmployeeData> employees = new ArrayList<>();
		employees.add(new EmployeeData(1, 2, LocalDate.of(1985, 5, 2), LocalDate.of(1995, 1, 1)));
		employees.add(new EmployeeData(2, 2, LocalDate.of(1985, 5, 1), LocalDate.of(1995, 1, 1)));
		employees.add(new EmployeeData(3, 2, LocalDate.of(1992, 5, 1), LocalDate.of(1993, 1, 1)));
		employees.add(new EmployeeData(4, 3, LocalDate.of(1992, 5, 1), LocalDate.of(1993, 1, 1)));

		List<TeamData> teams = TeamGenerator.generateTeams(employees);

		for (TeamData team : teams) {
			System.out.println(String.format("proj: %s | %s -- %s  -> days: %s", team.getProjectId(),
					team.getEmployeeId1(), team.getEmployeeId2(), team.getDaysWorked()));
		}

		Assert.assertEquals(teams.get(0).getDaysWorked(), 3531);
	}
	
	@Test
	public void testEmployeeFromFile() {
		List<EmployeeData> employeesFromResourceFile = EmployeeFactory
				.getEmployeesFromResourceFile("employeesData.csv");
		
		Assert.assertEquals(employeesFromResourceFile.size(), 3);
	}
	
	public static void main(String[] args) {
		List<EmployeeData> employees = new ArrayList<>();
		employees.add(new EmployeeData(1, 2, LocalDate.of(1985, 5, 2), LocalDate.of(1995, 1, 1)));
		employees.add(new EmployeeData(2, 2, LocalDate.of(1985, 5, 1), LocalDate.of(1995, 1, 1)));
		employees.add(new EmployeeData(3, 2, LocalDate.of(1992, 5, 1), LocalDate.of(1993, 1, 1)));
		employees.add(new EmployeeData(4, 3, LocalDate.of(1992, 5, 1), LocalDate.of(1993, 1, 1)));

		TeamSummary longestTeam = TeamGenerator.getLongestWorkingTogetherTeam(employees);
		System.out.println(longestTeam.getTotalDaysTogether());
	}
}
