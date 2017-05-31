import Factory.*;
import Helper.Helper;
import Statistic.Statistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import personage.BasicPersona;
import java.util.ArrayList;

public class War {
    private static ArrayList firstTeam = new ArrayList<>();
    private static ArrayList firstTeamModify = new ArrayList<>();
    private static ArrayList secondTeam = new ArrayList<>();
    private static ArrayList secondTeamModify = new ArrayList<>();
    private static War war;
    private static boolean isWinner = false;
    public static Statistics statistics;
    private static final Logger logger = LogManager.getLogger(War.class);
    public War(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        statistics = new Statistics();
        war = new War(statistics);

        logger.info("Choise of races...");
        Helper.logSeparator();

        Enum races1 = war.randomAlliance();
        Enum races2 = war.randomOrda();

        String nameRaces1 = races1.name();
        String nameRaces2 = races2.name();
        logger.info(nameRaces1 + "  vs  " + nameRaces2);
        Helper.logSeparator();

        firstTeam =  SquadFactory.getSquad(races1);
        secondTeam = SquadFactory.getSquad(races2);

        int countAttackTeam = 0;
        for (int i = Helper.random(); true; i++) {
            if(i % 2 == 0){
                countAttackTeam++;
                logger.info("Attack " + countAttackTeam + ". Attacks " + nameRaces1);
                Helper.logSeparator();
                int a = run(firstTeam,firstTeamModify,secondTeam,secondTeamModify);
                statistics.addCountAttackFirstTeam();
                statistics.addMoveNumbersFirstTeam(a);
                if(isWinner(secondTeam,secondTeamModify)){
                    logger.info("The battle is over!");
                    logger.info("Victory of " + nameRaces1);
                    Helper.logSeparator();
                    break;
                }

            }else{
                countAttackTeam++;
                logger.info("Attack number " + countAttackTeam + ". Attacks " + nameRaces2);
                Helper.logSeparator();
                int a = run(secondTeam,secondTeamModify,firstTeam,firstTeamModify);
                statistics.addCountAttackSecondTeam();
                statistics.addMoveNumbersSecondTeam(a);
                if(isWinner(firstTeam,firstTeamModify)){
                    logger.info("The battle is over!");
                    logger.info("Victory of " + nameRaces2);
                    Helper.logSeparator();
                    break;
                }
            }
        }
        logger.info("Battle statistics:");
        Helper.logSeparator();
        logger.info("Number of all attacks completed: " + statistics.getCountTotalAttacks());
        logger.info("Number of attacks completed by the squad " + nameRaces1 +" : "+ statistics.getCountAttackFirstTeam());
        logger.info("Number of attacks completed by the squad " + nameRaces2 +" : "+ statistics.getCountAttackSecondTeam());
        Helper.logSeparator();
        logger.info("Number of all moves: " + statistics.getAllmove());
        logger.info("Number of moves by the squad " + nameRaces1 + " : "+ statistics.getMoveNumbersFirstTeam());
        logger.info("Number of moves by the squad " + nameRaces2 + " : "+ statistics.getMoveNumbersSecondTeam());
        Helper.logSeparator();
    }

    private static int run(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam,
                            ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        ArrayList listWhoWalked = new ArrayList<>();
        int count = 0;
        while (modifyAttackingTeam.size() > 0 && !isWinner(defensibleTeam,modifyDefensibleTeam)){
            ++count;
            Statistics.addTotalmove();
            logger.info("Move namber "+String.valueOf(Statistics.getAllmove()));
            int index = Helper.random(modifyAttackingTeam.size()-1);
            if(Helper.random()==0){
                modifyAttackingTeam.get(index).attack1(attackingTeam,modifyAttackingTeam,defensibleTeam,modifyDefensibleTeam);
                listWhoWalked.add(modifyAttackingTeam.get(index));
                modifyAttackingTeam.remove(index);
            }else{
                modifyAttackingTeam.get(index).attack2(attackingTeam,modifyAttackingTeam,defensibleTeam,modifyDefensibleTeam);
                listWhoWalked.add(modifyAttackingTeam.get(index));
                modifyAttackingTeam.remove(index);
            }
        }

        while (attackingTeam.size() > 0 && !isWinner(defensibleTeam,modifyDefensibleTeam)){
            ++count;
            ArrayList attackTeam = new ArrayList<>();
            attackTeam.addAll(listWhoWalked);
            attackTeam.addAll(attackingTeam);
            int index = Helper.random(attackingTeam.size()-1);
            Statistics.addTotalmove();
            logger.info("Move number "+String.valueOf(Statistics.getAllmove()));
            if(Helper.random()==0){
                attackingTeam.get(index).attack1(attackTeam,modifyAttackingTeam,defensibleTeam,modifyDefensibleTeam);
                listWhoWalked.add(attackingTeam.get(index));
                attackingTeam.remove(index);
            }else{
                attackingTeam.get(index).attack2(attackTeam,modifyAttackingTeam,defensibleTeam,modifyDefensibleTeam);
                listWhoWalked.add(attackingTeam.get(index));
                attackingTeam.remove(index);
            }
        }

        for(int i = 0; i < modifyAttackingTeam.size(); i++){
            listWhoWalked.remove(modifyAttackingTeam.get(i));
        }
        attackingTeam.addAll(listWhoWalked);
        Helper.logSeparator();
        return count;
    }

    private static boolean isWinner(ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        if(defensibleTeam.size() ==0 && modifyDefensibleTeam.size() == 0){
            isWinner = true;
            return true;
        }else{
            return false;
        }
    }

    private Alliance randomAlliance(){
        Alliance[] list = Alliance.values();
        int a = Helper.random(list.length-1);
        return list[a];
    }

    private Orda randomOrda(){
        Orda[] list = Orda.values();
        int a = Helper.random(list.length-1);
        return list[a];
    }
}
