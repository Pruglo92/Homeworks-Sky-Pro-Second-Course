public class Gryffindor extends Hogwarts {

    private int nobility;
    private int honor;
    private int bravery;

    public Gryffindor(String name, int magic, int transgression, int nobility, int honor, int bravery) {
        super(name, magic, transgression);
        this.nobility = nobility;
        this.honor = honor;
        this.bravery = bravery;
    }

    public int getNobility() {
        return nobility;
    }

    public void setNobility(int nobility) {
        this.nobility = nobility;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public int getBravery() {
        return bravery;
    }

    public void setBravery(int bravery) {
        this.bravery = bravery;
    }

    private int ability() {
        return nobility + honor + bravery;
    }

    public void compareGryffindor(Gryffindor student) {
        int ability1 = ability();
        int ability2 = student.ability();
        if (ability1 > ability2) {
            System.out.printf("Гриффиндорец %s лучше, чем гриффиндорец %s: %d VS %d%n", getName(), student.getName(), ability1, ability2);
        } else if (ability1 < ability2) {
            System.out.printf("Гриффиндорец %s лучше, чем гриффиндорец %s: %d VS %d%n", student.getName(), getName(), ability2, ability1);
        } else {
            System.out.printf("Гриффиндорец %s такой же, как гриффиндорец %s: %d VS %d%n", student.getName(), getName(), ability1, ability2);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%s; благородство: %d; честь: %d; храбрость: %d;",
                super.toString(),
                nobility,
                honor,
                bravery
        );
    }
}
