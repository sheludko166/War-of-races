package Factory;


import Statistic.Statistics;
import personage.*;
import Helper.Helper;

import java.util.ArrayList;


/**
 * Created by dos on 26.05.2017.
 */
public class SquadFactory {

    public static ArrayList getSquad(Enum races) {

        if (races.equals(Alliance.HUMAN)) {
            System.out.println("Создание отряда HUMAN");
            Statistics.addProgressWar("Создание отряда HUMAN");
            return getHumanSquad();
        } else if (races.equals(Alliance.ELVES)) {
            System.out.println("Создание отряда ELVES");
            Statistics.addProgressWar("Создание отряда ELVES");
            return getElvesSquad();
        } else if (races.equals(Orda.ORCS)) {
            System.out.println("Создание отряда ORCS");
            Statistics.addProgressWar("Создание отряда ORCS");
            return getOrcsSquad();
        } else if (races.equals(Orda.UNDEAD)) {
            System.out.println("Создание отряда UNDEAD");
            Statistics.addProgressWar("Создание отряда UNDEAD");
            return getUndeadSquad();
        } else {
            return null;
        }
    }

    private static ArrayList<Undead> getUndeadSquad() {

        ArrayList<Undead> list = new ArrayList<>();
        list.add(new Undead(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Undead(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Undead(Profession.WARRIOR, j));
        }
        Helper.writeSeporator();
        return list;
    }

    private static ArrayList<Orc> getOrcsSquad() {
        ArrayList<Orc> list = new ArrayList<>();
        list.add(new Orc(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Orc(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Orc(Profession.WARRIOR, j));
        }
        Helper.writeSeporator();
        return list;
    }

    private static ArrayList<Elf> getElvesSquad() {
        ArrayList<Elf> list = new ArrayList<>();
        list.add(new Elf(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Elf(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Elf(Profession.WARRIOR, j));
        }
        Helper.writeSeporator();
        return list;
    }

    private static ArrayList<Human> getHumanSquad() {
        ArrayList<Human> list = new ArrayList<>();
        list.add(new Human(Profession.MAG,0));
        for(int i =1; i<4; i++){
            list.add(new Human(Profession.ARCHER,i));
        }
        for(int j = 1; j < 5; j++){
            list.add(new Human(Profession.WARRIOR, j));
        }
        Helper.writeSeporator();
        return list;
    }
}
