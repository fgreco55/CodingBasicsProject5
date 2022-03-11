import java.util.Scanner;

public class MyApp {
    public static void main(String[] args) {
        Scanner userinput;

        while (true) {
            System.out.print("Command> ");
            userinput = new Scanner(System.in);
            String cmd = userinput.next();

            System.out.print("What information do you need: ");
            String info = userinput.next();

            System.out.println("Command [" + cmd + "] " + "Info [" + info + "]");
        }
    }
}
