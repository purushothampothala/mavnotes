abstract class Vehicle {
    private String model;
    private int year;

    public Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public abstract void start(); // Abstract method

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}

// encapsulating implementation details within a class
class Car extends Vehicle {
    private String color;

    public Car(String model, int year, String color) {
        super(model, year);
        this.color = color;
    }

    @Override
    public void start() {
        System.out.println("Car engine started.");
    }

    public String getColor() {
        return color;
    }
}
public class AbstractionAndEncapsulation {

    public static void main(String[] args) {
        Car car = new Car("Honda", 2023, "Red");

        System.out.println("Model: " + car.getModel());
        System.out.println("Year: " + car.getYear());
        System.out.println("Color: " + car.getColor());

        car.start();
    }}