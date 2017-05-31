package personage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by dos on 26.05.2017.
 */
public class Elf extends BasicPersona implements BasicIntefase{
    private static final Logger logger = LogManager.getLogger(Elf.class);


    public Elf(Profession profession, int index) {
        super(profession);

        switch (profession){
            case ARCHER:{
                this.setName(Elf.class.getSimpleName() +" "+ profession + index);
                logger.info("Создан " + Elf.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 7;
                this.attack2 = 3;
                break;
            }
            case MAG:{
                this.setName(Elf.class.getSimpleName() +" "+ profession);
                logger.info("Создан " +Elf.class.getSimpleName() +" "+ profession);
                this.attack1 = 150;
                this.attack2 = 10;
                break;
            }
            case WARRIOR:{
                this.setName(Elf.class.getSimpleName() +" "+ profession + index);
                logger.info("Создан " +Elf.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 15;
                this.attack2 = 15;
                break;
            }
        }

    }




}
