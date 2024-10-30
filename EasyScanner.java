import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class EasyScanner
{
    public static int nextInt()
    {
        Scanner keyboard = new Scanner(System.in);
        int i = keyboard.nextInt();
        return i;
    }
    public static double nextDouble()
    {
        Scanner keyboard = new Scanner(System.in);
        double d = keyboard.nextDouble();
        return d;
    }
    public static String nextString()
    {
        Scanner keyboard = new Scanner(System.in);
        String s = keyboard.nextLine();
        return s;
    }

    public static Scanner fopen(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            return scanner;
        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
