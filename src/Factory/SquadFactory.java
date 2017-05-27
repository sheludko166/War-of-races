package Factory;


import personage.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dos on 26.05.2017.
 */
public class SquadFactory {

    public static List getSquad(Enum races) {

        if (races.equals(Alliance.HUMAN)) {
            return getHumanSquad();
        } else if (races.equals(Alliance.ELVES)) {
            return getElvesSquad();
        } else if (races.equals(Orda.ORCS)) {
            return getOrcsSquad();
        } else if (races.equals(Orda.UNDEAD)) {
            return getUndeadSquad();
        } else {
            return null;
        }
    }

    private static List getUndeadSquad() {
        ArrayList<Undead> list = new ArrayList<>();
        list.add(new Undead(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Undead(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Undead(Profession.WARRIOR, j));
        }
        return list;
    }

    private static List getOrcsSquad() {
        ArrayList<Orc> list = new ArrayList<>();
        list.add(new Orc(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Orc(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Orc(Profession.WARRIOR, j));
        }
        return list;
    }

    private static List getElvesSquad() {
        ArrayList<Elf> list = new ArrayList<>();
        list.add(new Elf(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Elf(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Elf(Profession.WARRIOR, j));
        }
        return list;
    }

    private static List getHumanSquad() {
        ArrayList<Human> list = new ArrayList<>();
        list.add(new Human(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Human(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Human(Profession.WARRIOR, j));
        }
        return list;
    }
}
