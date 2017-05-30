package personage;

import java.util.ArrayList;

/**
 * Created by dos on 26.05.2017.
 */
public class Undead extends BasicPersona {
    public Undead(Profession profession, int index) {
        super(profession);
        switch (profession) {
            case ARCHER: {
                this.setName(Undead.class.getSimpleName() + " Hunter" + index);
                this.attack1 = 4;
                this.attack2 = 2;
                break;
            }
            case MAG: {
                this.setName(Undead.class.getSimpleName() + " Necromancer");
                this.attack1 = 50;
                this.attack2 = 5;
                break;
            }
            case WARRIOR: {
                this.setName(Undead.class.getSimpleName() + " Zombie" + index);
                this.attack1 = 18;
                this.attack2 = 18;
                break;
            }
        }

    }

    @Override
    public void attack1(ArrayList attackingTeam, ArrayList modifyAttackingTeam,
                        ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        String atac = "";
        if(profession.equals(Profession.ARCHER)){
            atac = " пронзил стрелой ";
        }else if(profession.equals(Profession.MAG)){
            atac = " наслал недуг на ";
        }else if (profession.equals(Profession.WARRIOR)){
                atac = " атаковал копьем ";
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
            case MAG: {
                if(modifyDefensibleTeam.size() > 0 && defensibleTeam.size()>0){
                    magicAttackIfTwoDefensebleTeam(defensibleTeam, modifyDefensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() <= 0 && defensibleTeam.size()>0){
                    magicAttackDefensebleTeam(defensibleTeam, atac, attack);
                }else if(modifyDefensibleTeam.size() >0 && defensibleTeam.size()<=0){
                    magicAttackModifyDefensibleTeam(modifyDefensibleTeam, atac, attack);
                }
                /*if (modifyDefensibleTeam.size() > 0) {
                    if (random() == 0) {
                        int a = random(defensibleTeam.size() - 1);
                        defensibleTeam.get(a).setUnModify(true);
                        defensibleTeam.get(a).modifyAttack1 = defensibleTeam.get(a).attack1 * attack1/100;
                        defensibleTeam.get(a).modifyAttack2 = defensibleTeam.get(a).attack2 * attack1/100;
                        System.out.println(this.getName() + atac + defensibleTeam.get(a).getName());

                    } else {
                        int a = random(modifyDefensibleTeam.size() - 1);
                        modifyDefensibleTeam.get(a).setUnModify(true);
                        modifyDefensibleTeam.get(a).modifyAttack1 = modifyDefensibleTeam.get(a).modifyAttack1 * attack1/100;
                        modifyDefensibleTeam.get(a).modifyAttack2 = modifyDefensibleTeam.get(a).modifyAttack2 * attack1/100;
                        System.out.println(this.getName() + atac + modifyDefensibleTeam.get(a).getName());

                    }
                } else {
                    int a = random(defensibleTeam.size() - 1);
                    defensibleTeam.get(a).setUnModify(true);
                    defensibleTeam.get(a).modifyAttack1 = defensibleTeam.get(a).attack1 * attack1/100;
                    defensibleTeam.get(a).modifyAttack2 = defensibleTeam.get(a).attack2 * attack1/100;
                    System.out.println(this.getName() + atac + defensibleTeam.get(a).getName());
                }*/
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
                /*if (modifyDefensibleTeam.size() > 0) {
                    if (random() == 0) {
                        int a = random(defensibleTeam.size() - 1);
                        defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
                        if (defensibleTeam.get(a).health <= 0) {
                            defensibleTeam.get(a).setAlive(false);
                            System.out.println(this.getName() + atac + defensibleTeam.get(a).getName() + " урон: "
                                    + attack + ". " + defensibleTeam.get(a).getName() + " убит.");
                            defensibleTeam.remove(a);
                        } else {
                            System.out.println(this.getName() + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
                        }

                    } else {
                        int a = random(modifyDefensibleTeam.size() - 1);

                        modifyDefensibleTeam.get(a).health = modifyDefensibleTeam.get(a).health - attack;
                        if (modifyDefensibleTeam.get(a).health <= 0) {
                            modifyDefensibleTeam.get(a).setAlive(false);
                            System.out.println(this.getName() + atac + modifyDefensibleTeam.get(a).getName() +
                                    " урон: " + attack + ". " + modifyDefensibleTeam.get(a).getName() + " убит.");
                            modifyDefensibleTeam.remove(a);
                        } else {
                            System.out.println(this.getName() + atac + modifyDefensibleTeam.get(a).getName() + " урон: " + attack);
                        }

                    }
                } else {
                    int a = random(defensibleTeam.size() - 1);
                    defensibleTeam.get(a).health = defensibleTeam.get(a).health - attack;
                    if (defensibleTeam.get(a).health <= 0) {
                        defensibleTeam.get(a).setAlive(false);
                        System.out.println(this.getName() + atac + defensibleTeam.get(a).getName() + " урон: "
                                + attack + ". " + defensibleTeam.get(a).getName() + " убит.");
                        defensibleTeam.remove(a);
                    } else {
                        System.out.println(this.getName() + atac + defensibleTeam.get(a).getName() + " урон: " + attack);
                    }
                }*/
                break;
            }
        }
    }

    private void magicAttackModifyDefensibleTeam(ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {

        int a = random(modifyDefensibleTeam.size() - 1);
        modifyDefensibleTeam.get(a).setUnModify(true);
        modifyDefensibleTeam.get(a).modifyAttack1 = modifyDefensibleTeam.get(a).modifyAttack1 * attack1/100;
        modifyDefensibleTeam.get(a).modifyAttack2 = modifyDefensibleTeam.get(a).modifyAttack2 * attack1/100;
        System.out.println(this.getName() + atac + modifyDefensibleTeam.get(a).getName());
    }

    private void magicAttackDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, String atac, int attack) {
        int a = random(defensibleTeam.size() - 1);
        defensibleTeam.get(a).setUnModify(true);
        defensibleTeam.get(a).modifyAttack1 = defensibleTeam.get(a).attack1 * attack1/100;
        defensibleTeam.get(a).modifyAttack2 = defensibleTeam.get(a).attack2 * attack1/100;
        System.out.println(this.getName() + atac + defensibleTeam.get(a).getName());

    }

    private void magicAttackIfTwoDefensebleTeam(ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam, String atac, int attack) {
        if (random() == 0) {
            int a = random(defensibleTeam.size() - 1);
            defensibleTeam.get(a).setUnModify(true);
            defensibleTeam.get(a).modifyAttack1 = defensibleTeam.get(a).attack1 * attack1/100;
            defensibleTeam.get(a).modifyAttack2 = defensibleTeam.get(a).attack2 * attack1/100;
            System.out.println(this.getName() + atac + defensibleTeam.get(a).getName());

        } else {
            int a = random(modifyDefensibleTeam.size() - 1);
            modifyDefensibleTeam.get(a).setUnModify(true);
            modifyDefensibleTeam.get(a).modifyAttack1 = modifyDefensibleTeam.get(a).modifyAttack1 * attack1/100;
            modifyDefensibleTeam.get(a).modifyAttack2 = modifyDefensibleTeam.get(a).modifyAttack2 * attack1/100;
            System.out.println(this.getName() + atac + modifyDefensibleTeam.get(a).getName());

        }
    }
}
