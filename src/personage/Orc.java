package personage;

import Helper.Helper;

import java.util.ArrayList;

/**
 * Created by dos on 26.05.2017.
 */
public class Orc extends BasicPersona {
    public Orc(Profession profession, int index) {
        super(profession);

        switch (profession){
            case ARCHER:{
                this.setName(Orc.class.getSimpleName() +" "+ profession + index);
                Helper.writeMessageInConsoleAndStatistics("Создан " + Orc.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 3;
                this.attack2 = 2;
                break;
            }
            case MAG:{
                this.setName(Orc.class.getSimpleName() +" Shaman");
                Helper.writeMessageInConsoleAndStatistics("Создан " + Orc.class.getSimpleName() +" Shaman");
                this.attack1 = 150;
                break;
            }
            case WARRIOR:{
                this.setName(Orc.class.getSimpleName() +" Goblin" + index);
                Helper.writeMessageInConsoleAndStatistics("Создан " + Orc.class.getSimpleName() +" Goblin" + index);
                this.attack1 = 20;
                this.attack2 = 20;
                break;
            }
        }


    }



    @Override
    public void attack2(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam, ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam) {
        String atac = "";
        if(profession.equals(Profession.ARCHER)){
            atac = " ударил клинком ";
        }else if(profession.equals(Profession.MAG)){
            atac = " проклял ";
        }else if (profession.equals(Profession.WARRIOR)){
            atac = " атаковал дубиной ";
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
            case MAG: {
                if(modifyDefensibleTeam.size() > 0){
                    int a = Helper.random(modifyDefensibleTeam.size()-1);
                    modifyDefensibleTeam.get(a).setModify(false);
                    Helper.writeMessageInConsoleAndStatistics(this.getName() + atac + modifyDefensibleTeam.get(a).getName());

                }else{
                    Helper.writeMessageInConsoleAndStatistics(this.getName() + " никого не "+atac);
                }
                break;
            }
            default:{
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
}
