package employee.parser;

import java.util.ArrayList;
import java.util.List;

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

	public static TeamSummary getLongestWorkingTogetherTeam(List<EmployeeData> employees) {
		List<TeamData> teams = generateTeams(employees);

		// TODO need to implement this correctly
		System.out.println("Fix that");

		TeamSummary temp = new TeamSummary(teams.get(0).getEmployeeId1(), teams.get(0).getEmployeeId2());
		temp.setTotalDaysTogether(55);
		return temp;
	}
}
