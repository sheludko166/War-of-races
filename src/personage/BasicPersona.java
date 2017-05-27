package personage;

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
    public void attack1(BasicPersona persona) {
        if(profession.equals(Profession.MAG)){
            persona.modifyAttack1 = persona.attack1 * this.attack1 /100;
            persona.modifyAttack2 = persona.attack2 * this.attack1 / 100;
            persona.setModify(true);
        }else {
            if(this.isModify() || this.isUnModify()){
                persona.health = persona.health - this.modifyAttack1;
            }else{
                persona.health = persona.health - this.attack1;
            }
        }
    }

    @Override
    public void attack2(BasicPersona persona) {
         if(profession.equals(Profession.MAG)){
            if(this.isModify() || this.isUnModify()){
                persona.health = persona.health - this.modifyAttack2;
            }else{
                persona.health = persona.health - this.attack2;
            }
        }else {
            if(this.isModify() || this.isUnModify()){
                persona.health = persona.health - this.modifyAttack2;
            }else{
                persona.health = persona.health - this.attack2;
            }
        }
    }
}
