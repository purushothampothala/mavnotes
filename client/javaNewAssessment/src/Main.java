import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        MyClass obj = new MyClass();

        int privateVariable=obj.getPrivateVariable();
        System.out.println("private Variable= "+privateVariable);
        int publicValue=obj.publicValue;
        System.out.println("Public value= "+publicValue);
        int protectedValue=obj.protectedValue;
        System.out.println("protected Value= "+protectedValue);

        }
}

class MyClass {
    private int privateVariable = 42;

    public int publicValue=52;
    protected int protectedValue=62;

    public int getPrivateVariable() {
        return privateVariable;
    }

    public void setPrivateVariable(int privateVariable) {
        this.privateVariable = privateVariable;
    }
}