package Statistic;

public class Statistics {

    private static int countAttackFirstTeam ;
    private static int countAttackSecondTeam ;
    private static int moveNumbersFirstTeam;
    private static int moveNumbersSecondTeam;
    private static int allmove;

    public static int getAllmove() {
        return allmove;
    }

    public static void addTotalmove() {
        allmove++;
    }

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

    public static int getCountTotalAttacks() {
        return countAttackFirstTeam + countAttackSecondTeam;
    }
}
