package nu.educom.MI6;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    String codeSentence = "For ThE Royal QUEEN";
    int serviceNumber = 0;
    String actualNumber = "";
    ArrayList<Integer> blacklist;
    ArrayList<Integer> loggedIn;

    public void Login() {
        this.blacklist = new ArrayList<Integer>();
        this.loggedIn = new ArrayList<Integer>();
    }

    public void checkLogin() {
        System.out.println("Enter service number");
        boolean needCodeSentence = true;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int userInput = scanner.nextInt();

                if (this.blacklist.contains(userInput)) {
                    System.out.println("You are still a failure " + String.format("%03d", serviceNumber));
                    needCodeSentence = false;
                    break;
                } else if (this.loggedIn.contains(userInput)) {
                    System.out.println("You have already logged in " + String.format("%03d", serviceNumber));
                    needCodeSentence = false;
                    break;
                } else if (0 < userInput && userInput < 957) {
                    serviceNumber = userInput;
                    actualNumber = String.format("%03d", serviceNumber);
                    break;
                }
                System.out.println("Which is false, please enter a number between 1-956");
            } catch (Exception e) {
                System.out.println("Which is false, please enter a number");
            }
        }

        // VRAAG: Kan ik hier met een %s ook inserten?
        if (needCodeSentence) {
            System.out.println("Please enter code-sentence " + actualNumber);
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if (userInput.equals(codeSentence)) {
                System.out.println("You have logged in successfully " + actualNumber);
                loggedIn.add(serviceNumber);

            } else {
                System.out.println("You are now blacklisted " + actualNumber);
                blacklist.add(serviceNumber);
            }
        }
    }
}
