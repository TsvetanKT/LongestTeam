package teamTests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
		List<EmployeeData> employees = getSimpleEmployeeData();
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
	
	@Test
	public void testMaxWorkingDayTeam() {
	    List<EmployeeData> employees = getSimpleEmployeeData();
	    TeamSummary longestTeam = TeamGenerator.getLongestWorkingTogetherTeamFromEmployees(employees);
	    Assert.assertEquals(longestTeam.getTotalDaysTogether(), 3531);
	}
	
	@Test
    public void testNoTeamMaxWorkingDaysFromFile() {
        testTeamMaxWorkingDaysFromFile("employeesData.csv", -1);
    }
	
	@Test
    public void testTeamMaxWorkingDaysFromFile() {
        testTeamMaxWorkingDaysFromFile("employeesData-Team.csv", 1);
    }
	
	@Test
    public void testTeamMaxWorkingDaysStrangeDateFormat() {
        testTeamMaxWorkingDaysFromFile("employeesData-differentDateFormats.csv", 31);
	}
	
	@Test
	public void testManyRandomEmployees() {
	    final int NUMBER_OF_EMPLOYEES = 10000;
	    List<EmployeeData> ran = generaterandomEmployees(NUMBER_OF_EMPLOYEES);
        TeamSummary longestTeam = TeamGenerator.getLongestWorkingTogetherTeamFromEmployees(ran);
        
        System.out.println(String.format(
                "#1: %s | #2:  %s | Proj together: %s | Last proj Id: %s | days together: %s", 
                longestTeam.getEmployeeId1(), longestTeam.getEmployeeId2(), longestTeam.getProjects().size(),
                longestTeam.getLastProject(), longestTeam.getTotalDaysTogether()));
        System.out.println("Size " + ran.size());
        Assert.assertEquals(ran.size(), NUMBER_OF_EMPLOYEES);
        Assert.assertTrue(longestTeam.getTotalDaysTogether() > 10);
	}
	
	@Test
    public void testMultipleTimesWorkingTogether() {
        TeamSummary teamResult = testTeamMaxWorkingDaysFromFile("employeesData-manyTimesWorkingTogether.csv", 3);
        Assert.assertEquals(teamResult.getEmployeeId1(), 143);
        Assert.assertEquals(teamResult.getEmployeeId2(), 144);
        Assert.assertEquals(teamResult.getLastProject(), 13);
        Assert.assertEquals(teamResult.getProjects().size(), 2);
    }
	
	private TeamSummary testTeamMaxWorkingDaysFromFile(String filename, int expectedMaxWorkingDays) {
	    List<EmployeeData> employeesFromResourceFile = EmployeeFactory
                .getEmployeesFromResourceFile(filename);
        TeamSummary longestTeam = TeamGenerator.getLongestWorkingTogetherTeamFromEmployees(employeesFromResourceFile);
        Assert.assertEquals(longestTeam.getTotalDaysTogether(), expectedMaxWorkingDays);
        return longestTeam;
	}
	
	private static List<EmployeeData> getSimpleEmployeeData() {
	    List<EmployeeData> employees = new ArrayList<>();
        employees.add(new EmployeeData(1, 2, LocalDate.of(1985, 5, 2), LocalDate.of(1995, 1, 1)));
        employees.add(new EmployeeData(2, 2, LocalDate.of(1985, 5, 1), LocalDate.of(1995, 1, 1)));
        employees.add(new EmployeeData(3, 2, LocalDate.of(1992, 5, 1), LocalDate.of(1993, 1, 1)));
        employees.add(new EmployeeData(4, 3, LocalDate.of(1992, 5, 1), LocalDate.of(1993, 1, 1)));
        return employees;
	}
	
	private static List<EmployeeData> generaterandomEmployees(int numberOfEmployees) {
	    List<EmployeeData> employees = new ArrayList<>();
	    
	    Random ran = new Random();
	    for (int i = 0; i < numberOfEmployees; i++) {
	        
	        LocalDate first = LocalDate.of(ran.nextInt(30) + 1980, ran.nextInt(11) + 1, ran.nextInt(27) + 1);
	        LocalDate second = LocalDate.of(ran.nextInt(30) + 1980, ran.nextInt(11) + 1, ran.nextInt(27) + 1);
	        
	        if (first.isAfter(second)) {
	            LocalDate temp = second;
	            first = second;
	            second = temp;
	        }
	        
	        employees.add(new EmployeeData(ran.nextInt(30) + 1, ran.nextInt(20), 
	                first, second));
        }
	    
	    return employees;
	}
}
