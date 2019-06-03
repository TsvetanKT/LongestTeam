package employee.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.TimeUtils;

public class TeamGenerator {

	public static List<TeamData> generateTeams(List<EmployeeData> employees) {

		List<TeamData> teams = new ArrayList<>();
		for (EmployeeData employee : employees) {

			// TODO what about working many times on one project
			for (EmployeeData otherEmployee : employees) {

				// This is needed so we don't make 2 instances of teams for the 2 employees
				if (employee.getEmpId() > otherEmployee.getEmpId() || otherEmployee.getEmpId() == employee.getEmpId()
						|| otherEmployee.getProjectId() != employee.getProjectId()) {

					continue;
				}

				long daysWorkedTogether = TimeUtils.getCommonDaysBetweenPeriods(employee.getDateFrom(),
						employee.getDateTo(), otherEmployee.getDateFrom(), otherEmployee.getDateTo());

				if (daysWorkedTogether == 0) {
					continue;
				}

				teams.add(new TeamData(employee.getEmpId(), otherEmployee.getEmpId(), otherEmployee.getProjectId(),
						(int) daysWorkedTogether));

			}
		}

		return teams;
	}
	
	public static TeamSummary getLongestWorkingTogetherTeamFromEmployees(List<EmployeeData> employees) {
	    List<TeamData> teams = TeamGenerator.generateTeams(employees);
	    return getLongestWorkingTogetherTeam(teams);
	}
	
	public static TeamSummary getLongestWorkingTogetherTeam(List<TeamData> teams) {
	    
	    Map<String, TeamSummary> teamMap = new HashMap<>();
	    TeamSummary maxWorkingTeam = new TeamSummary(-1, -1, -1, -1);
        
	    TeamSummary current = null;
        for (TeamData teamData : teams) {
            
            if (teamMap.containsKey(teamData.toString())) {
                current = teamMap.get(teamData.toString());
                current.addProjectDays(teamData.getProjectId(), teamData.getDaysWorked());
                
                //teamMap.put(teamData.toString(), current);
            } else {
                current = new TeamSummary(teamData.getEmployeeId1(), teamData.getEmployeeId2(),
                        teamData.getProjectId(), teamData.getDaysWorked());
            }
            
            if (current.getTotalDaysTogether() > maxWorkingTeam.getTotalDaysTogether()) {
                maxWorkingTeam = current;
            }
        }
	    
	    return maxWorkingTeam;
	}
}
