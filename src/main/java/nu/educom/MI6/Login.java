package nu.educom.MI6;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Login {
    String codeSentence = "For ThE Royal QUEEN";
    ArrayList<Integer> blacklist;
    ArrayList<Integer> loggedIn;

    public void Login() {
        this.blacklist = new ArrayList<Integer>();
        this.loggedIn = new ArrayList<Integer>();
    }

    public void checkLogin() {
        JFrame frame = new JFrame("InputDialog Example #1");
        boolean needCodeSentence = true;
        int serviceNumber = 0;
        String actualNumber = "";

        while (true) {
            String jInput = JOptionPane.showInputDialog(frame, "Enter service number:");
            // If user clicks cancel, system quits.
            if (jInput == null) {System.exit(0);}

            // Otherwise checks the value
            try {
                int userInput = Integer.parseInt(jInput);
                serviceNumber = userInput;
                actualNumber = String.format("%03d", serviceNumber);
                if (this.blacklist.contains(userInput)) {
                    JOptionPane.showMessageDialog(null, String.format("You are still a failure %s", actualNumber));
                    needCodeSentence = false;
                    break;
                } else if (this.loggedIn.contains(userInput)) {
                    JOptionPane.showMessageDialog(null, String.format("You have already logged in %s", actualNumber));
                    needCodeSentence = false;
                    break;
                } else if (0 < userInput && userInput < 957) {
                    break;
                }
                JOptionPane.showMessageDialog(null, "Which is false, please enter a number between 1-956");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Which is false, please enter a number");
            }
        }

        if (needCodeSentence) {
            String jInput = JOptionPane.showInputDialog(frame,  String.format("Please enter code-sentence %s", actualNumber));
            // If user clicks cancel, system quits.
            if (jInput == null) {System.exit(0);}
            String userInput = jInput;
            if (userInput.equals(codeSentence)) {
                JOptionPane.showMessageDialog(null, String.format("You have logged in successfully  %s", actualNumber));
                loggedIn.add(serviceNumber);

            } else {
                JOptionPane.showMessageDialog(null, String.format("ACCESS DENIED  %s", actualNumber));
                blacklist.add(serviceNumber);
            }
        }
    }

}
