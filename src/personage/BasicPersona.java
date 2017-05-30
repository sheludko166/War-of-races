package personage;



import java.util.ArrayList;

/**
 * Created by dos on 25.05.2017.
 */
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

    public BasicPersona(Profession profession) {
        this.health = 100;
        this.isAlive = true;
        this.modify = false;

       this.profession = profession;
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
            atac = " пронзил стрелой ";
        }else if(profession.equals(Profession.MAG)){
            atac = " улучшил ";
        }else if (profession.equals(Profession.WARRIOR)){
            if(this instanceof Orc){
                atac = " атаковал дубиной ";
            }else if (this instanceof Undead){
                atac = " атаковал копьем ";
            }else{
                atac = " атаковал мечом ";
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
            case MAG: {
                ArrayList<? extends BasicPersona> attackingTeamTmp = new ArrayList<>();
                attackingTeamTmp.addAll(attackingTeam);
                attackingTeamTmp.remove(this);
                if (attackingTeamTmp.size() > 0) {
                    int i = random(attackingTeamTmp.size() -1);
                    attackingTeamTmp.get(i).setModify(true);
                    attackingTeamTmp.get(i).modifyAttack1 = attackingTeamTmp.get(i).attack1 * attack1/100;
                    attackingTeamTmp.get(i).modifyAttack2 = attackingTeamTmp.get(i).attack2 * attack1/100;
                    modifyAttackingTeam.add(attackingTeamTmp.get(i));
                    System.out.println(name + atac + attackingTeamTmp.get(i).getName());
                }else{
                    this.attack2(attackingTeam,modifyAttackingTeam,defensibleTeam,modifyDefensibleTeam);
                }
                break;
            }
            default: {
                if(modifyDefensibleTeam.size() > 0 && defensibleTeam.size()>0){
                    attackIfTwoDefensebleTeam(defensibleTeam, modifyDefensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() <= 0 && defensibleTeam.size()>0){
                    attackDefensebleTeam(defensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() >0 && defensibleTeam.size()<=0){
                    attackModifyDefensibleTeam(modifyDefensibleTeam, atac, attack);
                }
                /*if(modifyDefensibleTeam.size()>0){
                    if(random() == 0){
                        int a = random(defensibleTeam.size() -1);
                        defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
                        if(defensibleTeam.get(a).health <= 0){
                            defensibleTeam.get(a).setAlive(false);
                            System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: "
                                    + attack+ ". " + defensibleTeam.get(a).getName() + " убит.");
                            defensibleTeam.remove(a);
                        }else{
                            System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
                        }

                    }else {
                        int a = random(modifyDefensibleTeam.size() - 1);

                        modifyDefensibleTeam.get(a).health = modifyDefensibleTeam.get(a).health - attack;
                        if (modifyDefensibleTeam.get(a).health <= 0) {
                            modifyDefensibleTeam.get(a).setAlive(false);
                            System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() +
                                    " урон: " + attack + ". " + modifyDefensibleTeam.get(a).getName() + " убит.");
                            modifyDefensibleTeam.remove(a);
                        } else {
                            System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() + " урон: " + attack);
                        }

                    }
                }else{
                    int a = random(defensibleTeam.size() -1);
                    defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
                    if(defensibleTeam.get(a).health <= 0){
                        defensibleTeam.get(a).setAlive(false);
                        System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: "
                                + attack+ ". " + defensibleTeam.get(a).getName() + " убит.");
                        defensibleTeam.remove(a);
                    }else{
                        System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
                    }
                }*/
                break;
            }
        }
    }



    @Override
    public void attack2(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam, ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        String atac = "";
        if(profession.equals(Profession.ARCHER)){
            atac = " атаковал ";
        }else if(profession.equals(Profession.MAG)){
            atac = " атаковал магией ";
        }else if (profession.equals(Profession.WARRIOR)){
            if(this instanceof Orc){
                atac = " атаковал дубиной ";
            }else if (this instanceof Undead){
                atac = " атаковал копьем ";
            }else{
                atac = " атаковал мечом ";
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
        /*if(modifyDefensibleTeam.size()>0){
            if(random() == 0){
                int a = random(defensibleTeam.size() -1);
                    defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
                    if(defensibleTeam.get(a).health <= 0){
                        defensibleTeam.get(a).setAlive(false);
                        System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: "
                                + attack+ ". " + defensibleTeam.get(a).getName() + " убит.");
                        defensibleTeam.remove(a);
                    }else{
                        System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
                    }

            }else {
                int a = random(modifyDefensibleTeam.size() - 1);

                modifyDefensibleTeam.get(a).health = modifyDefensibleTeam.get(a).health - attack;
                if (modifyDefensibleTeam.get(a).health <= 0) {
                    modifyDefensibleTeam.get(a).setAlive(false);
                    System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() +
                            " урон: " + attack + ". " + modifyDefensibleTeam.get(a).getName() + " убит.");
                    modifyDefensibleTeam.remove(a);
                } else {
                    System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() + " урон: " + attack);
                }

            }
        }else{
            int a = random(defensibleTeam.size() -1);
            defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
            if(defensibleTeam.get(a).health <= 0){
                defensibleTeam.get(a).setAlive(false);
                System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: "
                        + attack+ ". " + defensibleTeam.get(a).getName() + " убит.");
                defensibleTeam.remove(a);
            }else{
                System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
            }
        }*/

    }

    protected static int random(int i){
        return (int) Math.round(Math.random() * i);
    }
    protected static int random(){
        return (int) Math.round(Math.random() );
    }

    protected void attackModifyDefensibleTeam(ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {
        int a = random(modifyDefensibleTeam.size() - 1);

        modifyDefensibleTeam.get(a).health = modifyDefensibleTeam.get(a).health - attack;
        if (modifyDefensibleTeam.get(a).health <= 0) {
            modifyDefensibleTeam.get(a).setAlive(false);
            System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() +
                    " урон: " + attack + ". " + modifyDefensibleTeam.get(a).getName() + " убит.");
            modifyDefensibleTeam.remove(a);
        } else {
            System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() + " урон: " + attack);
        }
    }

    protected void attackDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, String atac, int attack) {
        int a = random(defensibleTeam.size() -1);
        defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
        if(defensibleTeam.get(a).health <= 0){
            defensibleTeam.get(a).setAlive(false);
            System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: "
                    + attack+ ". " + defensibleTeam.get(a).getName() + " убит.");
            defensibleTeam.remove(a);
        }else{
            System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
        }
    }

    protected void attackIfTwoDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {
        if(random() == 0){
            int a = random(defensibleTeam.size() -1);
            defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
            if(defensibleTeam.get(a).health <= 0){
                defensibleTeam.get(a).setAlive(false);
                System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: "
                        + attack+ ". " + defensibleTeam.get(a).getName() + " убит.");
                defensibleTeam.remove(a);
            }else{
                System.out.println(name + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
            }

        }else {
            int a = random(modifyDefensibleTeam.size() - 1);

            modifyDefensibleTeam.get(a).health = modifyDefensibleTeam.get(a).health - attack;
            if (modifyDefensibleTeam.get(a).health <= 0) {
                modifyDefensibleTeam.get(a).setAlive(false);
                System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() +
                        " урон: " + attack + ". " + modifyDefensibleTeam.get(a).getName() + " убит.");
                modifyDefensibleTeam.remove(a);
            } else {
                System.out.println(name + atac + modifyDefensibleTeam.get(a).getName() + " урон: " + attack);
            }

        }
    }



}
