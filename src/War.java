import Factory.Alliance;
import Factory.Orda;
import Factory.SquadFactory;
import personage.BasicPersona;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dos on 25.05.2017.
 */
public class War {
    private static List<? extends BasicPersona> firstTeam = new ArrayList<>();
    private static List<? extends BasicPersona> secondTeam = new ArrayList<>();
    private static War war;

    public static void main(String[] args) {
        war = new War();
        Enum e = war.randomAlliance();
        war.firstTeam = SquadFactory.getSquad(war.randomAlliance());
        war.secondTeam = SquadFactory.getSquad(war.randomOrda());

    }

    private Alliance randomAlliance(){
        Alliance[] list = Alliance.values();
        int a = Helper.random(list.length-1);
        return list[a];
    }

    private Enum randomOrda(){
        Orda[] list = Orda.values();
        int a = Helper.random(list.length-1);
        return list[a];
    }
}
