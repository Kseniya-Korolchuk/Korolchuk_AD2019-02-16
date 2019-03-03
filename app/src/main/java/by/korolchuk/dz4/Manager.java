package by.korolchuk.dz4;

public class Manager {

    private static Manager instance = new Manager();

    public static Manager getInstance() {
        return instance;
    }

    private Dz4Activity dz4Activity;

    private Manager() {

    }

    public void setDz4Activity(Dz4Activity dz4Activity) {
        this.dz4Activity = dz4Activity;
    }
}
