package personage;

import Helper.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class Orc extends BasicPersona {

    private static final Logger logger = LogManager.getLogger(Orc.class);

    public Orc(Profession profession, int index) {
        super(profession);
        //this.attack1 and this.attack2 - is the damage for each profession
        switch (profession){
            case ARCHER:
                this.setName(Orc.class.getSimpleName() +" "+ profession + index);
                logger.info("Created " + Orc.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 3;
                this.attack2 = 2;
                break;

            case MAG:
                this.setName(Orc.class.getSimpleName() +" Shaman");
                logger.info("Created " + Orc.class.getSimpleName() +" Shaman");
                this.attack1 = 150;
                break;

            case WARRIOR:
                this.setName(Orc.class.getSimpleName() +" Goblin" + index);
                logger.info("Created " + Orc.class.getSimpleName() +" Goblin" + index);
                this.attack1 = 20;
                this.attack2 = 20;
                break;
        }
    }

    @Override
    public void attack2(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam, ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        String atac = "";
        if(profession.equals(Profession.ARCHER)){
            atac = " hit the blade ";
        }else if(profession.equals(Profession.MAG)){
            atac = " send a curse ";
        }else if (profession.equals(Profession.WARRIOR)){
            atac = " attacked with a club ";
        }
        int attack;
        if(isModify() || isUnModify()){
            attack = modifyAttack2;
            setModify(false);
            setUnModify(false);
        }else{
            attack = attack2;
        }

        switch (profession){
            case MAG:
                if(modifyDefensibleTeam.size() > 0){
                    int a = Helper.random(modifyDefensibleTeam.size()-1);
                    modifyDefensibleTeam.get(a).setModify(false);
                    logger.info(this.getName() + atac + modifyDefensibleTeam.get(a).getName());
                }else{
                    logger.info(this.getName() + " could not "+atac);
                }
                break;

            default:
                if(modifyDefensibleTeam.size() > 0 && defensibleTeam.size()>0){
                    attackIfTwoDefensebleTeam(defensibleTeam, modifyDefensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() <= 0 && defensibleTeam.size()>0){
                    attackDefensebleTeam(defensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() >0 && defensibleTeam.size()<=0){
                    attackModifyDefensibleTeam(modifyDefensibleTeam, atac, attack);
                }
                break;
        }
    }
}

