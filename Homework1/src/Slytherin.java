public class Slytherin extends Hogwarts {

    private int cunning;
    private int determination;
    private int ambition;
    private int ingenuity;
    private int lustForPower;

    public Slytherin(String name, int magic, int transgression, int cunning, int determination, int ambition, int ingenuity, int lustForPower) {
        super(name, magic, transgression);
        this.cunning = cunning;
        this.determination = determination;
        this.ambition = ambition;
        this.ingenuity = ingenuity;
        this.lustForPower = lustForPower;
    }

    public int getCunning() {
        return cunning;
    }

    public void setCunning(int cunning) {
        this.cunning = cunning;
    }

    public int getDetermination() {
        return determination;
    }

    public void setDetermination(int determination) {
        this.determination = determination;
    }

    public int getAmbition() {
        return ambition;
    }

    public void setAmbition(int ambition) {
        this.ambition = ambition;
    }

    public int getIngenuity() {
        return ingenuity;
    }

    public void setIngenuity(int ingenuity) {
        this.ingenuity = ingenuity;
    }

    public int getLustForPower() {
        return lustForPower;
    }

    public void setLustForPower(int lustForPower) {
        this.lustForPower = lustForPower;
    }

    private int ability() {
        return cunning + determination + ambition + ingenuity + lustForPower;
    }

    public void compareSlytherin(Slytherin student) {
        int ability1 = ability();
        int ability2 = student.ability();
        if (ability1 > ability2) {
            System.out.printf("Слизеринец %s лучше, чем слизеринец %s: %d VS %d%n", getName(), student.getName(), ability1, ability2);
        } else if (ability1 < ability2) {
            System.out.printf("Слизеринец %s лучше, чем слизеринец %s: %d VS %d%n", student.getName(), getName(), ability2, ability1);
        } else {
            System.out.printf("Слизеринец %s такой же, как слизеринец %s: %d VS %d%n", student.getName(), getName(), ability1, ability2);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%s; хитрость: %d; решительность: %d; амбициозность: %d; находчивость: %d; жажда власти: %d;",
                super.toString(),
                cunning,
                determination,
                ambition,
                ingenuity,
                lustForPower
        );
    }
}
