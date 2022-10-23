package complexJsonPath;

import java.lang.reflect.Field;

public class ReflectionTeste {
}
class Student {

    // private fields
    private String name;
    private int age;

    // Constructor
    public Student(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    private int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    // Override toString method to get required
    // output at terminal
    @Override public String toString()
    {
        return "Employee [name=" + name + ", age=" + age
                + "]";
    }
}

// Program will throw an exception on online IDE
// Compile and run the program on offline IDE
class GFG {

    public static void main(String[] args)
    {
        try {

            // Student object created
            Student e = new Student("Kapil", 23);

            // Create Field object
            Field privateField
                    = Student.class.getDeclaredField("name");

            // Set the accessibility as true
            privateField.setAccessible(true);

            // Store the value of private field in variable
            String name = (String)privateField.get(e);

            // Print the value
            System.out.println("Name of Student:" + name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}