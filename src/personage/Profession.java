package personage;

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
