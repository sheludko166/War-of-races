package personage;

/**
 * Created by dos on 26.05.2017.
 */
public enum Profession {
    MAG(1),
    ARCHER(2),
    WARRIOR(3);

    private int index;
    Profession(int i) {
        this.index = i;
    }

    public int getIndex() {
        return index;
    }
}
