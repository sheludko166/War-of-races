import Factory.Alliance;
import Factory.Orda;
import Factory.SquadFactory;
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

        String s = "Выбор расс...";
        System.out.println(s);
        statistics.addProgressWar(s);

        Enum races1 = war.randomAlliance();
        String nameRaces1 = races1.name();
        firstTeam =  SquadFactory.getSquad(races1);

        Enum races2 = war.randomOrda();
        String nameRaces2 = races2.name();
        secondTeam = SquadFactory.getSquad(races2);

        s = nameRaces1 + " против " + nameRaces2;
        System.out.println(s);
        statistics.addProgressWar(s);

        int countAttackTeam = 0;

        for (int i = Helper.random(); true; i++) {
            if(i % 2 == 0){
                countAttackTeam++;
                s = "Атака " + countAttackTeam + "-я. Атакует отряд " + nameRaces1;
                System.out.println(s);
                Statistics.addProgressWar(s);
                int a = run(firstTeam,firstTeamModify,secondTeam,secondTeamModify);
                statistics.addCountAttackFirstTeam();
                statistics.addMoveNumbersFirstTeam(a);
                if(isWinner(secondTeam,secondTeamModify)){
                    s = "Бой окончен! \n"+ "Победили " + nameRaces1;
                    System.out.println(s);
                    statistics.addProgressWar(s);
                    break;
                }

            }else{
                countAttackTeam++;
                s = "Атака " + countAttackTeam + "-я. Атакует отряд " + nameRaces2;
                System.out.println(s);
                Statistics.addProgressWar(s);
                int a = run(secondTeam,secondTeamModify,firstTeam,firstTeamModify);
                statistics.addCountAttackSecondTeam();
                statistics.addMoveNumbersSecondTeam(a);
                if(isWinner(firstTeam,firstTeamModify)){
                    s = "Бой окончен! \n"+ "Победили " + nameRaces2;
                    System.out.println(s);
                    statistics.addProgressWar(s);
                    break;
                }
            }
        }
        System.out.println("Hoda pervoi" + statistics.getMoveNumbersFirstTeam());
        System.out.println("Hoda vtoroi" + statistics.getMoveNumbersSecondTeam());
        System.out.println("Atack pervoi" + statistics.getCountAttackFirstTeam());
        System.out.println("Atack vtoroi" + statistics.getCountAttackSecondTeam());
        System.out.println(statistics.getProgressWar().toString());

    }






    private static int run(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam,
                            ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        ArrayList listWhoWalked = new ArrayList<>();
        int count = 0;
        while (modifyAttackingTeam.size() > 0 && !isWinner(defensibleTeam,modifyDefensibleTeam)){
            ++count;
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
        System.out.println("========================================================");
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
