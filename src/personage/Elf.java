package personage;

/**
 * Created by dos on 26.05.2017.
 */
public class Elf extends BasicPersona implements BasicIntefase{

    public Elf(Profession profession) {
        super(profession);
        this.setName(Elf.class.getSimpleName() +" "+ profession);
        if(profession.equals(Profession.ARCHER)){
            this.attack1 = 7;
            this.attack2 = 3;
        }else if(profession.equals(Profession.MAG)){
            this.attack1 = 150;
            this.attack2 = 10;
        }else if(profession.equals(Profession.WARRIOR)){
            this.attack1 = 15;
            this.attack2 = 15;
        }

    }
    public Elf(Profession profession, int index) {
        super(profession);
        this.setName(Elf.class.getSimpleName() +" "+ profession+index);
    }



}
