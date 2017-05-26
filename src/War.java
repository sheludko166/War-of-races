import personage.Elf;
import personage.Human;
import personage.Orc;
import personage.Profession;

/**
 * Created by dos on 25.05.2017.
 */
public class War {
    public static void main(String[] args) {
        Elf elf = new Elf(Profession.MAG);
        Orc orc = new Orc(Profession.MAG);
        elf.attack1(elf);
        elf.attack2(orc);
        orc.attack2(elf);
        elf.attack2(orc);
    }
}
