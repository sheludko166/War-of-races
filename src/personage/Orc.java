package personage;

/**
 * Created by dos on 26.05.2017.
 */
public class Orc extends BasicPersona {
    public Orc(Profession profession, int index) {
        super(profession);

        switch (profession){
            case ARCHER:{
                this.setName(Orc.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 3;
                this.attack2 = 2;
                break;
            }
            case MAG:{
                this.setName(Orc.class.getSimpleName() +" "+ profession);
                this.attack1 = 150;
                break;
            }
            case WARRIOR:{
                this.setName(Orc.class.getSimpleName() +" "+ profession + index);
                this.attack1 = 20;
                this.attack2 = 20;
                break;
            }
        }


    }

    @Override
    public void attack2(BasicPersona persona) {
        if(profession.equals(Profession.ARCHER)){
            if(this.isModify() || this.isUnModify()){
                persona.health = persona.health - this.modifyAttack2;
            }else{
                persona.health = persona.health - this.attack2;
            }
        }else if(profession.equals(Profession.MAG)){
            if(persona.isModify()){
                persona.modifyAttack1 =0;
                persona.modifyAttack2 = 0;
                persona.setModify(false);
            }
        }else if(profession.equals(Profession.WARRIOR)){
            if(this.isModify() || this.isUnModify()){
                persona.health = persona.health - this.modifyAttack2;
            }else{
                persona.health = persona.health - this.attack2;
            }
        }
    }
}
