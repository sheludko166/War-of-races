package personage;

/**
 * Created by dos on 26.05.2017.
 */
public class Undead extends BasicPersona{
    public Undead(Profession profession) {
        super(profession);
        this.setName(Orc.class.getSimpleName() +" "+ profession);
        if(profession.equals(Profession.ARCHER)){
            this.attack1 = 4;
            this.attack2 = 2;
        }else if(profession.equals(Profession.MAG)){
            this.attack1 = 50;
            this.attack2 = 5;
        }else if(profession.equals(Profession.WARRIOR)){
            this.attack1 = 18;
            this.attack2 = 18;
        }

    }
    public Undead(Profession profession, int index) {
        super(profession);
        this.setName(Elf.class.getSimpleName() +" "+ profession+index);
    }

    @Override
    public void attack1(BasicPersona persona) {
        if(profession.equals(Profession.ARCHER)){
            persona.health = persona.health - this.attack1;
        }else if(profession.equals(Profession.MAG)){
            persona.modifyAttack1 = persona.attack1 * this.attack1 /100;
            persona.modifyAttack2 = persona.attack2 * this.attack1 / 100;
            persona.setUnModify(true);
        }else if(profession.equals(Profession.WARRIOR)){
            persona.health = persona.health - this.attack1;
        }
    }
}
