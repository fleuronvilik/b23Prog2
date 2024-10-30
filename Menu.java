import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public class Menu {
    private static Customer c;

    public static int getAnswer() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("menu.txt"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Enter the corresponding number to the answer: ");
        int answer = EasyScanner.nextInt();

        String feedback;

        switch(answer) {
            case 1:
                feedback = "Output of all clients";
                break;
            case 11:
            case 21:
                feedback = "All male customers";
                break;
            case 111:
            case 211:
                feedback = "All male customers in the southwest";
                break;
            case 112:
            case 212:
                feedback = "All male customers in the southeast";
                break;
            case 12:
            case 22:
                feedback = "All female customers";
                break;
            case 121:
            case 221:
                feedback = "All female customers in the southwest";
                break;
            case 122:
            case 222:
                feedback = "All female customers in the southeast";
                break;
            case 13:
            case 23:
                feedback = "All customers in the southwest";
                break;
            case 14:
            case 24:
                feedback = "All customers in the southeast";
                break;
            case 2:
                feedback = "Automated Report";
                break;
            case 3:
                feedback = "Search a customer";
                break;
            case 4:
                feedback = "Delete a customer";
                break;
            case 5:
                feedback = "Add a customer";
                break;
            case 6:
                feedback = "Sort by ID";
                break;
            case 61:
                feedback = "Sort by age";
                break;
            case 62:
                feedback = "Sort by number of children";
                break;
            case 63:
                feedback = "Sort by sex";
                break;
            case 64:
                feedback = "Sort by region";
                break;
            case 65:
                feedback = "Sort by bmi";
                break;
            case 66:
                feedback = "Sort by charges";
                break;
            default:
                System.out.println("Invalid choice");
                return -1;
        }
        System.out.println("You have chosen - " + feedback + ".");
        return answer;
    }

    public static Customer getCustomerInfos() {
        c = null;
        try {
            int age, children;
            double bmi, charges;
            String sex, smoker, region;

            do {
                System.out.print("Enter customer age: ");
                age = EasyScanner.nextInt();
            } while (age < 18);

            do {
                System.out.print("Enter customer sex (male/female): ");
                sex = EasyScanner.nextString();
            } while (!(sex.equals("male") || sex.equals("female")));

            System.out.print("Enter customer bmi: ");
            bmi = EasyScanner.nextDouble();

            System.out.print("How many children does he/she have? ");
            children = EasyScanner.nextInt();

            do {
                System.out.print("Smoker (yes/no)? ");
                smoker = EasyScanner.nextString();
            } while (!(smoker.equals("yes") || smoker.equals("no")));

            do {
                System.out.print("Enter customer region (northeast, northwest, southeast, southwest): ");
                region = EasyScanner.nextString();
            } while (!(region.equals("northeast") || region.equals("northwest") || region.equals("southeast") || region.equals("southwest")));

            System.out.print("Enter customer charges: ");
            charges = EasyScanner.nextDouble();

            c = new Customer(0, age, sex, bmi, children, smoker.equals("yes"), region, charges);

            return c;
        } catch(InputMismatchException e) {
            System.out.println("That's not a valid input.");
            return c;
        }
    }
}