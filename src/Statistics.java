/**
 * Created by Dima on 30.05.2017.
 */


public class Statistics {

    private static int countAttackFirstTeam = 0;
    private static int countAttackSecondTeam = 0;

    private static int moveNumbersFirstTeam;
    private static int moveNumbersSecondTeam;


    private static StringBuilder progressWar = new StringBuilder();

    public int getMoveNumbersFirstTeam() {
        return moveNumbersFirstTeam;
    }

    public static void addMoveNumbersFirstTeam(int move) {
        moveNumbersFirstTeam += move;
    }

    public int getMoveNumbersSecondTeam() {
        return moveNumbersSecondTeam;
    }

    public static void addMoveNumbersSecondTeam(int move) {
        moveNumbersSecondTeam += move;
    }


    public static int getCountAttackFirstTeam() {

        return countAttackFirstTeam;
    }

    public static void addCountAttackFirstTeam() {
        countAttackFirstTeam++;
    }

    public int getCountAttackSecondTeam() {
        return countAttackSecondTeam;
    }

    public static void addCountAttackSecondTeam() {
        countAttackSecondTeam++;
    }

    public int getCountTotalAttacks() {
        return countAttackFirstTeam + countAttackSecondTeam;
    }


    public StringBuilder getProgressWar() {
        return progressWar;
    }

    public static void addProgressWar(String progress) {
        progressWar.append(progress + "\n");
    }


}
