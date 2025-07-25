import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class WrongStudentName extends Exception { }

class WrongAge extends Exception { }

class WrongDateOfBirth extends Exception { }

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                int option = menu();
                if (option == 0) {
                    System.out.println("Koniec programu.");
                    break;
                } else if (option < 0 || option > 3) {
                    System.out.println("Niepoprawny wybór menu!");
                    continue;  // powrót do początku pętli
                }
                switch (option) {
                    case 1:
                        exercise1();
                        break;
                    case 2:
                        exercise2();
                        break;
                    case 3:
                        exercise3();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Błędny wybór menu! Proszę wpisać cyfrę.");
                scan.nextLine(); // czyścimy bufor, aby usunąć nieprawidłowe dane
            } catch (IOException e) {
                System.out.println("Błąd wejścia/wyjścia: " + e.getMessage());
            } catch (WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            } catch (WrongAge e) {
                System.out.println("Błędny wiek! Poprawny wiek to liczba z przedziału 1-99.");
            } catch (WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia! Poprawny format: DD-MM-YYYY.");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine(); // czyszczenie bufora
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if (name.contains(" "))
            throw new WrongStudentName();
        return name;
    }

    // Metoda exercise1 waliduje imię, wiek i datę urodzenia.
    // Data jest sprawdzana pod kątem liczby znaków: 2-2-4.
    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth {
        String name = ReadName();

        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        if (age < 1 || age > 99)
            throw new WrongAge();
        scan.nextLine(); // czyszczenie bufora

        System.out.println("Podaj datę urodzenia: ");
        String date = scan.nextLine();
        if (!validateDate(date))
            throw new WrongDateOfBirth();

        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for (Student current : students)
            System.out.println(current.ToString());
    }

    public static void exercise3() throws IOException {
        scan.nextLine(); // czyszczenie bufora
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        Student wanted = (new Service()).findStudentByName(name);
        if (wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }

    // Metoda walidacji daty sprawdza, czy ciąg ma trzy części (rozdzielonych myślnikami)
    // z długościami odpowiednio 2, 2 i 4 znaków.
    public static boolean validateDate(String date) {
        String[] parts = date.split("-");
        if (parts.length != 3)
            return false;
        return parts[0].length() == 2 && parts[1].length() == 2 && parts[2].length() == 4;
    }
}
