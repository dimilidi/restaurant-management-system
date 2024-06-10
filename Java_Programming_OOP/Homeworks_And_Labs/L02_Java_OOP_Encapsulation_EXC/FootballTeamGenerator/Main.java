package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.FootballTeamGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Team> teamsByName = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        while (!"END".equals(command)) {
            String[] commandParts = command.split(";");
            String commandName = commandParts[0];
            switch (commandName) {
                case "Team":
                    handleAddTeam(commandParts[1], teamsByName);
                    break;
                case "Add":
                    handleAddPlayer(commandParts[1], commandParts[2], commandParts[3],
                            commandParts[4], commandParts[5], commandParts[6], commandParts[7],
                            teamsByName);
                    break;
                case "Remove":
                    handleRemovePlayer(commandParts[1], commandParts[2], teamsByName);
                    break;
                case "Rating":
                    handleRating(commandParts[1], teamsByName);
                    break;

            }

            command = scanner.nextLine();
        }
    }

    private static void handleRating(String teamName,  Map<String, Team> teamsByName) {
        Team team = getTeam(teamName, teamsByName);
        if (team == null) return;

        System.out.println(team.getName() + " - " + (int) team.getRating());
    }

    private static void handleRemovePlayer(String teamName, String playerName, Map<String, Team> teamsByName) {
        Team team = getTeam(teamName, teamsByName);
        if (team == null) return;

        try{
            team.removePlayer(playerName);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleAddPlayer(String teamName, String playerName, String enduranceStr,
                                        String dribbleStr, String sprintStr, String passingStr,
                                        String shootingStr, Map<String, Team> teamsByName) {
        Team team = getTeam(teamName, teamsByName);
        if (team == null) return;
        try{
            Player player = new Player(playerName,
                    Integer.parseInt(enduranceStr),
                    Integer.parseInt(sprintStr),
                    Integer.parseInt(dribbleStr),
                    Integer.parseInt(passingStr),
                    Integer.parseInt(shootingStr)
            );

            team.addPlayer(player);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static Team getTeam(String teamName, Map<String, Team> teamsByName) {
        Team team = teamsByName.get(teamName);
        if(team == null) {
            System.out.printf("Team %s does not exist.%n", teamName);
            return null;
        }
        return team;
    }

    private static void handleAddTeam(String teamName, Map<String, Team> teamsByName) {
        try {
            Team team =  new Team(teamName);
            teamsByName.put(team.getName(), team);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
