import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

    public void addStudent(Student student) throws IOException {
        FileWriter f = new FileWriter("db.txt", true);
        BufferedWriter b = new BufferedWriter(f);
        b.append(student.ToString());
        b.newLine();
        b.close();
    }

    public Collection<Student> getStudents() throws IOException {
        ArrayList<Student> ret = new ArrayList<>();
        FileReader f = new FileReader("db.txt");
        BufferedReader reader = new BufferedReader(f);
        String line;
        while ((line = reader.readLine()) != null) {
            ret.add(Student.Parse(line));
        }
        reader.close();
        return ret;
    }

    public Student findStudentByName(String name) throws IOException {
        Collection<Student> students = this.getStudents();
        for (Student current : students) {
            if (current.GetName().equals(name))
                return current;
        }
        return null;
    }
}
