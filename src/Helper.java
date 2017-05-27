/**
 * Created by Dima on 27.05.2017.
 */
public class Helper {
    public void writeMessage(String s){
        System.out.println(s);
    }

    public static int random(){
        return (int) Math.round(Math.random());
    }
    public static int random(int i){
        return (int) Math.round(Math.random() * i);
    }
}
