public class Student {

    private String Name;
    private int Age;
    private String Date;

    public Student(String name, int age, String date) {
        Name = name;
        Age = age;
        Date = date;
    }

    public String GetName() {
        return Name;
    }

    public int GetAge() {
        return Age;
    }

    public String GetDate() {
        return Date;
    }

    public String ToString() {
        return Name + " " + Age + " " + Date;
    }

    public static Student Parse(String str) {
        String[] data = str.split(" ");
        if (data.length != 3)
            return new Student("Parse Error", -1, "");
        try {
            int age = Integer.parseInt(data[1]);
            return new Student(data[0], age, data[2]);
        } catch (NumberFormatException e) {
            return new Student("Parse Error", -1, "");
        }
    }
}
