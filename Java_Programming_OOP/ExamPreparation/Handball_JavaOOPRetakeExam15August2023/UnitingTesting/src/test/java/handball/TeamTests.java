package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.UnitingTesting.src.test.java.handball;

import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.UnitingTesting.src.main.java.handball.HandballPlayer;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.UnitingTesting.src.main.java.handball.Team;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TeamTests {
    static final String NAME = "team";

    @Test(expected = NullPointerException.class)
    public void testNullNameThrows() {
        Team team = new Team(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyNameThrows() {
        Team team = new Team("  ", 1);
    }

    @Test
    public void testName() {
        Team team = new Team(NAME, 1);
        assertEquals(NAME, team.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativePositionThrows() {
        Team team = new Team(NAME, -1);
    }
    @Test
    public void testZeroPosition() {
        Team team = new Team(NAME, 0);
        assertEquals(0, team.getPosition());
    }

    @Test
    public void testGetCountOnEmptyTeam() {
        Team team = new Team(NAME, 1);
        assertEquals(0, team.getCount());
    }

    @Test
    public void testGetCount() {
        Team team = new Team(NAME, 1);
        HandballPlayer player = new HandballPlayer("name");
        team.add(player);
        assertEquals(1, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerOnFullTeamThrows(){
        Team team = new Team(NAME, 1);
        HandballPlayer player = new HandballPlayer("name");
        HandballPlayer player2 = new HandballPlayer("name2");
        team.add(player);
        team.add(player2);
    }

    @Test
    public void testAddPlayerIncreaseTeamSize(){
        Team team = new Team(NAME, 5);
        HandballPlayer player = new HandballPlayer("name");
        HandballPlayer player2 = new HandballPlayer("name2");
        team.add(player);
        team.add(player2);
        assertEquals(2, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingPlayerThrows() {
        Team team = new Team(NAME, 5);
        HandballPlayer player = new HandballPlayer("name");
        HandballPlayer player2 = new HandballPlayer("name2");
        team.add(player);
        team.remove(player2.getName());
    }

    @Test
    public void testRemoveExistingPlayerDecreaseTeamSize() {
        Team team = new Team(NAME, 5);
        HandballPlayer player = new HandballPlayer("name");
        HandballPlayer player2 = new HandballPlayer("name2");
        team.add(player);
        team.add(player2);
        team.remove(player2.getName());
        assertEquals(1, team.getCount());
    }

    @Test
    public void testPlayerForAnotherTeamSetPlayerToNotIsActive() {
        Team team = new Team(NAME, 5);
        HandballPlayer player = new HandballPlayer("name");
        team.add(player);
        team.playerForAnotherTeam(player.getName());
        assertFalse(player.isActive());
    }

    @Test
    public void testPlayerForAnotherTeamReturnPlayer() {
        Team team = new Team(NAME, 5);
        HandballPlayer player = new HandballPlayer("name");
        team.add(player);
        HandballPlayer playerForAnotherTeam = team.playerForAnotherTeam(player.getName());
        assertEquals(player, playerForAnotherTeam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerForAnotherTeamByNonExistingPlayerThrows() {
        Team team = new Team(NAME, 5);
        HandballPlayer player = new HandballPlayer("name");
        HandballPlayer player2 = new HandballPlayer("name2");
        team.add(player);
        team.playerForAnotherTeam(player2.getName());
    }

    @Test
    public void testGetStatisticsWithNoPlayers() {
        Team team = new Team("team", 5);

        String actual = team.getStatistics();

        String expected = "The player  is in the team team.";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStatisticsWithPlayers() {
        // Create a Team object with players
        Team team = new Team("team", 5);
        HandballPlayer player1 = new HandballPlayer("Player1");
        HandballPlayer player2 = new HandballPlayer("Player2");
        team.add(player1);
        team.add(player2);

        String actual = team.getStatistics();

        String expected = "The player Player1, Player2 is in the team team.";
        assertEquals(expected, actual);
    }



}
