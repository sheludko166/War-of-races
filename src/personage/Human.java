package personage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Human extends BasicPersona implements BasicIntefase{
    private static final Logger logger = LogManager.getLogger(Human.class);

    public Human(Profession profession, int index) {
        super(profession);

        switch (profession){
            //this.attack1 and this.attack2 - is the damage for each profession
            case ARCHER:
                this.setName(Human.class.getSimpleName() +" Crossbowman" + index);
                logger.info("Created " + Human.class.getSimpleName() +" Crossbowman" + index);
                this.attack1 = 5;
                this.attack2 = 3;
                break;

            case MAG:
                this.setName(Human.class.getSimpleName() +" "+ profession);
                logger.info("Created " +Human.class.getSimpleName() +" "+ profession);
                this.attack1 = 150;
                this.attack2 = 4;
                break;

            case WARRIOR:
                this.setName(Human.class.getSimpleName() +" "+ profession + index);
                logger.info("Created " +Human.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 18;
                this.attack2 = 18;
                break;
        }
    }
}
