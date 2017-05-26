package personage;

/**
 * Created by dos on 26.05.2017.
 */
public class Undead extends BasicPersona{
    public Undead(Profession profession, int index) {
        super(profession);
        switch (profession){
            case ARCHER:{
                this.setName(Orc.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 4;
                this.attack2 = 2;
                break;
            }
            case MAG:{
                this.setName(Orc.class.getSimpleName() +" "+ profession );
                this.attack1 = 50;
                this.attack2 = 5;
                break;
            }
            case WARRIOR:{
                this.setName(Orc.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 18;
                this.attack2 = 18;
                break;
            }
        }

    }


    @Override
    public void attack1(BasicPersona persona) {
        if(profession.equals(Profession.ARCHER)){
            if(this.isModify() || this.isUnModify()){
                persona.health = persona.health - this.modifyAttack1;
            }else{
                persona.health = persona.health - this.attack1;
            }
        }else if(profession.equals(Profession.MAG)){
            persona.modifyAttack1 = persona.attack1 * this.attack1 /100;
            persona.modifyAttack2 = persona.attack2 * this.attack1 / 100;
            persona.setUnModify(true);
        }else if(profession.equals(Profession.WARRIOR)){
            if(this.isModify() || this.isUnModify()){
                persona.health = persona.health - this.modifyAttack1;
            }else{
                persona.health = persona.health - this.attack1;
            }
        }
    }
}
