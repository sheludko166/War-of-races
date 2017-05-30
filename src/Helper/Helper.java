package Helper;

import Statistic.Statistics;

/**
 * Created by Dima on 27.05.2017.
 */
public class Helper {
    public static void writeMessage(String s){
        System.out.println(s);
    }

    public static int random(){
        return (int) Math.round(Math.random());
    }

    public static int random(int i){
        return (int) Math.round(Math.random() * i);
    }

    public static void writeSeporator(){
        System.out.println("===================================================");
        Statistics.addProgressWar("===================================================");
    }
    public static void writeFirstPartMessage(String s){
        System.out.print(s);
        Statistics.addProgressWar(s);
    }
    public static void writeMessageInConsoleAndStatistics(String s) {
        System.out.println(s);
        Statistics.addProgressWar(s);
    }
}
