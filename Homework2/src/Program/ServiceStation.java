package Program;

public interface ServiceStation {
    default void updateTyre() {
        System.out.println("Меняем покрышку");
    }

    default void checkEngine() {
        System.out.println("Проверяем двигатель");
    }

    default void checkTrailer() {
        System.out.println("Проверяем прицеп");
    }

    void check(Car car);

    void check(Truck truck);

    void check(Bicycle bicycle);
}
