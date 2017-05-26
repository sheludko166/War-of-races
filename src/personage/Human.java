package personage;

/**
 * Created by dos on 26.05.2017.
 */
public class Human extends BasicPersona implements BasicIntefase{

    public Human(Profession profession) {
        super(profession);
        this.setName(Human.class.getName() +" "+ profession);
        if(profession.equals(Profession.ARCHER)){
            this.attack1 = 5;
            this.attack2 = 3;
        }else if(profession.equals(Profession.MAG)){
            this.attack1 = 150;
            this.attack2 = 4;
        }else if(profession.equals(Profession.WARRIOR)){
            this.attack1 = 18;
            this.attack2 = 18;
        }

    }

    public Human(Profession profession, int index) {
        super(profession);
        this.setName(Elf.class.getSimpleName() +" "+ profession+index);
    }
}
