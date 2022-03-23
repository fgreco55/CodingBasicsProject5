import java.util.ArrayList;
import java.util.Scanner;

public class UserInput {
        private ArrayList<String> args;             // Where to store user's input (collection of string tokens)
        private Scanner userinput;                  // To see if there was any data was input
        private Scanner tokens;                     // parsing what the user input

        /*
         Basic Constructor
         */
        UserInput() {
                userinput = new Scanner(System.in);
                args = new ArrayList<String>();
        }

        /*
         clear the list of user input tokens
         */
        private void clear() {
                args = new ArrayList<String>();
        }

        /*
         Get the first token on the line ("zeroth" token) -  convenience method
         */
        public String getCommand() {
                return args.get(0);
        }

        /*
         How many tokens did the user input
         */
        public int numargs() {
                return args.size();
        }

        /*
         Primary method that gets a line from the user and puts each token into an arraylist
         */
        public ArrayList<String> getArgs() {
                clear();
                if (userinput.hasNextLine()) {                  // If the user typed something... even just a <CR>
                        String cmd = userinput.nextLine();

                        if (cmd.isEmpty() == false) {             // If the user typed some words.

                                tokens = new Scanner(cmd);              // break up the line into words
                                while (tokens.hasNext()) {             // While there are words in the input, print them
                                        args.add(tokens.next());
                                }
                        }
                }
                return args;
        }

        /*
         Simple utility program to display all the args
         */
        public void displayArgs() {
                for (int i = 0; i < numargs(); i++ ) {
                        System.out.println("Arg: " + i + "  = [" + args.get(i) + "]");
                }
        }

    /*
     Simple test to see if this class works properly
     */

        public static void main(String[] argv) {
                UserInput ui = new UserInput();

                while (true) {
                        System.out.print("Command> ");          // show prompt to user

                        ui.getArgs();                           // Get the input from the user
                        ui.displayArgs();                       // Display all the args
                }
        }
}
