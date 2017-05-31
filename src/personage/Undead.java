package personage;

import Helper.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class Undead extends BasicPersona {
    private static final Logger logger = LogManager.getLogger(Undead.class);

    public Undead(Profession profession, int index) {
        super(profession);
        switch (profession) {
            //this.attack1 and this.attack2 - is the damage for each profession
            case ARCHER:
                this.setName(Undead.class.getSimpleName() + " Hunter" + index);
                logger.info("Created "+Undead.class.getSimpleName() + " Hunter" + index);
                this.attack1 = 4;
                this.attack2 = 2;
                break;

            case MAG:
                this.setName(Undead.class.getSimpleName() + " Necromancer");
                logger.info("Created "+Undead.class.getSimpleName() + " Necromancer");
                this.attack1 = 50;
                this.attack2 = 5;
                break;

            case WARRIOR:
                this.setName(Undead.class.getSimpleName() + " Zombie" + index);
                logger.info("Created " + Undead.class.getSimpleName() + " Zombie" + index);
                this.attack1 = 18;
                this.attack2 = 18;
                break;
        }
    }

    @Override
    public void attack1(ArrayList attackingTeam, ArrayList modifyAttackingTeam,
                        ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        String atac = "";
        if(profession.equals(Profession.ARCHER)){
            atac = " pierced by an arrow ";
        }else if(profession.equals(Profession.MAG)){
            atac = " caused ailment ";
        }else if (profession.equals(Profession.WARRIOR)){
                atac = " attacked with a spear ";
        }

        int attack;
        if(isModify() || isUnModify()){
            attack = modifyAttack1;
            setModify(false);
            setUnModify(false);
        }else{
            attack = attack1;
        }

        switch (profession) {
            case MAG:
                if(modifyDefensibleTeam.size() > 0 && defensibleTeam.size()>0){
                    magicAttackIfTwoDefensebleTeam(defensibleTeam, modifyDefensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() <= 0 && defensibleTeam.size()>0){
                    magicAttackDefensebleTeam(defensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() >0 && defensibleTeam.size()<=0){
                    magicAttackModifyDefensibleTeam(modifyDefensibleTeam, atac, attack);
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

    private void magicAttackModifyDefensibleTeam(ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {
        int a = Helper.random(modifyDefensibleTeam.size() - 1);
        modifyDefensibleTeam.get(a).setUnModify(true);
        modifyDefensibleTeam.get(a).modifyAttack1 = modifyDefensibleTeam.get(a).modifyAttack1 * attack1/100;
        modifyDefensibleTeam.get(a).modifyAttack2 = modifyDefensibleTeam.get(a).modifyAttack2 * attack1/100;
        logger.info(this.getName() + atac + modifyDefensibleTeam.get(a).getName());
    }

    private void magicAttackDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, String atac, int attack) {
        int a = Helper.random(defensibleTeam.size() - 1);
        defensibleTeam.get(a).setUnModify(true);
        defensibleTeam.get(a).modifyAttack1 = defensibleTeam.get(a).attack1 * attack1/100;
        defensibleTeam.get(a).modifyAttack2 = defensibleTeam.get(a).attack2 * attack1/100;
        logger.info(this.getName() + atac + defensibleTeam.get(a).getName());
    }

    private void magicAttackIfTwoDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {
        if (Helper.random() == 0) {
            int a = Helper.random(defensibleTeam.size() - 1);
            defensibleTeam.get(a).setUnModify(true);
            defensibleTeam.get(a).modifyAttack1 = defensibleTeam.get(a).attack1 * attack1/100;
            defensibleTeam.get(a).modifyAttack2 = defensibleTeam.get(a).attack2 * attack1/100;
            String s = this.getName() + atac + defensibleTeam.get(a).getName();
            logger.info(s);
        } else {
            int a = Helper.random(modifyDefensibleTeam.size() - 1);
            modifyDefensibleTeam.get(a).setUnModify(true);
            modifyDefensibleTeam.get(a).modifyAttack1 = modifyDefensibleTeam.get(a).modifyAttack1 * attack1/100;
            modifyDefensibleTeam.get(a).modifyAttack2 = modifyDefensibleTeam.get(a).modifyAttack2 * attack1/100;
            logger.info(this.getName() + atac + modifyDefensibleTeam.get(a).getName());
        }
    }
}
