import Factory.Alliance;
import Factory.Orda;
import Factory.SquadFactory;
import Helper.Helper;
import Statistic.Statistics;
import personage.BasicPersona;



import java.util.ArrayList;

/**
 * Created by dos on 25.05.2017.
 */
public class War {
    private static ArrayList firstTeam = new ArrayList<>();
    private static ArrayList firstTeamModify = new ArrayList<>();
    private static ArrayList secondTeam = new ArrayList<>();
    private static ArrayList secondTeamModify = new ArrayList<>();
    private static War war;
    private static boolean isWinner = false;
    public static Statistics statistics;

    public War(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        statistics = new Statistics();
        war = new War(statistics);

        Helper.writeMessageInConsoleAndStatistics("Выбор расс...");
        Helper.writeSeporator();

        Enum races1 = war.randomAlliance();
        Enum races2 = war.randomOrda();

        String nameRaces1 = races1.name();
        String nameRaces2 = races2.name();

        Helper.writeMessageInConsoleAndStatistics(nameRaces1 + " против " + nameRaces2);
        Helper.writeSeporator();

        firstTeam =  SquadFactory.getSquad(races1);
        secondTeam = SquadFactory.getSquad(races2);

        int countAttackTeam = 0;

        for (int i = Helper.random(); true; i++) {
            if(i % 2 == 0){
                countAttackTeam++;
                Helper.writeMessageInConsoleAndStatistics("Атака " + countAttackTeam + "-я. Атакует отряд " + nameRaces1);
                int a = run(firstTeam,firstTeamModify,secondTeam,secondTeamModify);
                statistics.addCountAttackFirstTeam();
                statistics.addMoveNumbersFirstTeam(a);
                if(isWinner(secondTeam,secondTeamModify)){
                    Helper.writeMessageInConsoleAndStatistics("Бой окончен! \n"+ "Победили " + nameRaces1);
                    Helper.writeSeporator();
                    break;
                }

            }else{
                countAttackTeam++;
                Helper.writeMessageInConsoleAndStatistics("Атака " + countAttackTeam + "-я. Атакует отряд " + nameRaces2);
                int a = run(secondTeam,secondTeamModify,firstTeam,firstTeamModify);
                statistics.addCountAttackSecondTeam();
                statistics.addMoveNumbersSecondTeam(a);
                if(isWinner(firstTeam,firstTeamModify)){
                    Helper.writeMessageInConsoleAndStatistics("Бой окончен! \n"+ "Победили " + nameRaces2);
                    Helper.writeSeporator();
                    break;
                }
            }
        }
        Helper.writeMessageInConsoleAndStatistics("");
        Helper.writeSeporator();
        Helper.writeMessageInConsoleAndStatistics("Статистика боя:");
        Helper.writeSeporator();
        Helper.writeMessageInConsoleAndStatistics("Кол-во совершонных атак: " + statistics.getCountTotalAttacks());
        Helper.writeMessageInConsoleAndStatistics("Кол-во совершонных атак отрядом " + nameRaces1 +" : "+ statistics.getCountAttackFirstTeam());
        Helper.writeMessageInConsoleAndStatistics("Кол-во совершонных атак отрядом " + nameRaces2 +" : "+ statistics.getCountAttackSecondTeam());
        Helper.writeSeporator();
        Helper.writeMessageInConsoleAndStatistics("Кол-во совершонных ходов: " + statistics.getAllmove());
        Helper.writeMessageInConsoleAndStatistics("Кол-во ходов отрядом " + nameRaces1 + " : "+ statistics.getMoveNumbersFirstTeam());
        Helper.writeMessageInConsoleAndStatistics("Кол-во ходов отрядом " + nameRaces2 + " : "+ statistics.getMoveNumbersSecondTeam());
        Helper.writeSeporator();


        Helper.writeMessage(statistics.getProgressWar().toString());

    }






    private static int run(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam,
                            ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        ArrayList listWhoWalked = new ArrayList<>();

        int count = 0;
        while (modifyAttackingTeam.size() > 0 && !isWinner(defensibleTeam,modifyDefensibleTeam)){
            ++count;
            Statistics.addTotalmove();
            Helper.writeFirstPartMessage("Ход "+String.valueOf(Statistics.getAllmove())+"-й ");
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
            Helper.writeFirstPartMessage("Ход "+String.valueOf(Statistics.getAllmove())+"-й ");
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
        Helper.writeSeporator();
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
