package Helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dima on 27.05.2017.
 */
public class Helper {
    private static final Logger logger = LogManager.getLogger(Helper.class);


    public static int random(){
        return (int) Math.round(Math.random());
    }

    public static int random(int i){
        return (int) Math.round(Math.random() * i);
    }

    public static void logSeparator(){
        logger.info("======================================================");
    }

}
