package personage;

/**
 * Created by dos on 26.05.2017.
 */
public class Orc extends BasicPersona {
    public Orc(Profession profession) {
        super(profession);
        this.setName(Orc.class.getSimpleName() +" "+ profession);
        if(profession.equals(Profession.ARCHER)){
            this.attack1 = 3;
            this.attack2 = 2;
        }else if(profession.equals(Profession.MAG)){
            this.attack1 = 150;

        }else if(profession.equals(Profession.WARRIOR)){
            this.attack1 = 20;
            this.attack2 = 20;
        }

    }
    public Orc(Profession profession, int index) {
        super(profession);
        this.setName(Elf.class.getSimpleName() +" "+ profession+index);
    }

    @Override
    public void attack2(BasicPersona persona) {
        if(profession.equals(Profession.ARCHER)){
            persona.health = persona.health - this.attack2;
        }else if(profession.equals(Profession.MAG)){
            if(persona.isModify()){
                persona.modifyAttack1 =0;
                persona.modifyAttack2 = 0;
                persona.setModify(false);
            }
        }else if(profession.equals(Profession.WARRIOR)){
            persona.health = persona.health - this.attack2;
        }
    }
}
