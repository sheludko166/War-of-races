package personage;

import Helper.Helper;
import org.apache.logging.log4j.*;
import java.util.ArrayList;

public class BasicPersona implements BasicIntefase{
    protected int health;
    private String name;
    protected Profession profession;
    private boolean isAlive;
    private boolean modify;
    private boolean unModify;
    protected int attack1;
    protected int attack2;
    protected int modifyAttack1;
    protected int modifyAttack2;

    private static final Logger logger = LogManager.getLogger(BasicPersona.class);

    public BasicPersona(Profession profession) {
        this.health = 100;
        this.isAlive = true;
        this.modify = false;
        this.profession = profession;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    public boolean isUnModify() {
        return unModify;
    }

    public void setUnModify(boolean unModify) {
        this.unModify = unModify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    @Override
    public void attack1(ArrayList attackingTeam, ArrayList modifyAttackingTeam,
                        ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        String atac = "";
        if(profession.equals(Profession.ARCHER)){
            atac = " pierced by an arrow ";
        }else if(profession.equals(Profession.MAG)){
            atac = " improved ";
        }else if (profession.equals(Profession.WARRIOR)){
            if(this instanceof Orc){
                atac = " attacked with a club ";
            }else if (this instanceof Undead){
                atac = " attacked with a spear ";
            }else{
                atac = " attacked with a sword ";
            }
        }

        int attack;
        if(isModify() || isUnModify()){
            attack = modifyAttack1;
            setModify(false);
            setUnModify(false);
        }else{
            attack = attack1;
        }

        switch (profession){
            case MAG:
                ArrayList<? extends BasicPersona> attackingTeamTmp = new ArrayList<>();
                attackingTeamTmp.addAll(attackingTeam);
                attackingTeamTmp.remove(this);
                if (attackingTeamTmp.size() > 0) {
                    int i = Helper.random(attackingTeamTmp.size() -1);
                    attackingTeamTmp.get(i).setModify(true);
                    attackingTeamTmp.get(i).modifyAttack1 = attackingTeamTmp.get(i).attack1 * attack1/100;
                    attackingTeamTmp.get(i).modifyAttack2 = attackingTeamTmp.get(i).attack2 * attack1/100;
                    modifyAttackingTeam.add(attackingTeamTmp.get(i));
                    logger.info(name + atac + attackingTeamTmp.get(i).getName());
                }else{
                    this.attack2(attackingTeam,modifyAttackingTeam,defensibleTeam,modifyDefensibleTeam);
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


    @Override
    public void attack2(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam, ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        String atac = "";
        if(profession.equals(Profession.ARCHER)){
            atac = " attacked ";
        }else if(profession.equals(Profession.MAG)){
            atac = " attacked with magic ";
        }else if (profession.equals(Profession.WARRIOR)){
            if(this instanceof Orc){
                atac = " attacked with a club ";
            }else if (this instanceof Undead){
                atac = " attacked with a spear ";
            }else{
                atac = " attacked with a sword ";
            }
        }
        int attack;
        if(isModify() || isUnModify()){
            attack = modifyAttack2;
            setModify(false);
            setUnModify(false);
        }else{
            attack = attack2;
        }
        if(modifyDefensibleTeam.size() > 0 && defensibleTeam.size()>0){
            attackIfTwoDefensebleTeam(defensibleTeam, modifyDefensibleTeam, atac, attack);
        }else if(modifyDefensibleTeam.size() <= 0 && defensibleTeam.size()>0){
            attackDefensebleTeam(defensibleTeam, atac, attack);
        }else if(modifyDefensibleTeam.size() >0 && defensibleTeam.size()<=0){
            attackModifyDefensibleTeam(modifyDefensibleTeam, atac, attack);
        }
    }


    protected void attackModifyDefensibleTeam(ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {
        int a = Helper.random(modifyDefensibleTeam.size() - 1);

        modifyDefensibleTeam.get(a).health = modifyDefensibleTeam.get(a).health - attack;
        if (modifyDefensibleTeam.get(a).health <= 0) {
            modifyDefensibleTeam.get(a).setAlive(false);
            logger.info(name + atac + modifyDefensibleTeam.get(a).getName() +
                    " damage: " + attack + ". " + modifyDefensibleTeam.get(a).getName() + " died.");
            modifyDefensibleTeam.remove(a);
        } else {
            logger.info(name + atac + modifyDefensibleTeam.get(a).getName() + " damage: " + attack);
        }
    }

    protected void attackDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, String atac, int attack) {
        int a = Helper.random(defensibleTeam.size() -1);
        defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
        if(defensibleTeam.get(a).health <= 0){
            defensibleTeam.get(a).setAlive(false);
            logger.info(name + atac + defensibleTeam.get(a).getName() + " damage: "
                    + attack+ ". " + defensibleTeam.get(a).getName() + " died.");
            defensibleTeam.remove(a);
        }else{
            logger.info(name + atac + defensibleTeam.get(a).getName() + " damage: " + attack);
        }
    }

    protected void attackIfTwoDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {
        if(Helper.random() == 0){
            int a = Helper.random(defensibleTeam.size() -1);
            defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
            if(defensibleTeam.get(a).health <= 0){
                defensibleTeam.get(a).setAlive(false);
                logger.info(name + atac + defensibleTeam.get(a).getName() + " damage: "
                        + attack+ ". " + defensibleTeam.get(a).getName() + " died.");
                defensibleTeam.remove(a);
            }else{
                logger.info(name + atac + defensibleTeam.get(a).getName() + " damage: " + attack);
            }
        }else {
            int a = Helper.random(modifyDefensibleTeam.size() - 1);
            modifyDefensibleTeam.get(a).health = modifyDefensibleTeam.get(a).health - attack;
            if (modifyDefensibleTeam.get(a).health <= 0) {
                modifyDefensibleTeam.get(a).setAlive(false);
                logger.info(name + atac + modifyDefensibleTeam.get(a).getName() +
                        " damage: " + attack + ". " + modifyDefensibleTeam.get(a).getName() + " died.");
                modifyDefensibleTeam.remove(a);
            } else {
                logger.info(name + atac + modifyDefensibleTeam.get(a).getName() + " damage: " + attack);
            }
        }
    }
}
