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
        JFrame frame                = new JFrame("Geen idee wat dit doet eigenlijk");
        boolean needCodeSentence    = true;
        int serviceNumber           = 0; // The variable that will be saved in blacklist & loggedIn
        String showNumber           = ""; // The variable that will be shown to the user in communication

        while (true) {
            // Wat doet dit eigenlijk?
            String jInput = JOptionPane.showInputDialog(frame, "Enter service number:");
            // If user clicks cancel, system quits.
            if (jInput == null) {System.exit(0);}
            // Otherwise checks the value
            try {
                int userInput = Integer.parseInt(jInput);
                serviceNumber = userInput;
                showNumber = String.format("%03d", serviceNumber);
                if (this.blacklist.contains(userInput)) {
                    JOptionPane.showMessageDialog(frame, String.format("You are still a failure %s", showNumber));
                    needCodeSentence = false;
                    break;
                } else if (this.loggedIn.contains(userInput)) {
                    JOptionPane.showMessageDialog(frame, String.format("You are already logged in %s", showNumber));
                    needCodeSentence = false;
                    break;
                } else if (0 < userInput && userInput < 957) {
                    break;
                }
                JOptionPane.showMessageDialog(frame, "Which is false, please enter a number between 1-956");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame,"Which is false, please enter a number");
            }
        }

        if (needCodeSentence) {
            String jInput = JOptionPane.showInputDialog(frame,  String.format("Please enter code-sentence %s", showNumber));
            // If user clicks cancel, system quits.
            if (jInput == null) {System.exit(0);}
            String userInput = jInput;
            if (userInput.equals(codeSentence)) {
                JOptionPane.showMessageDialog(frame, String.format("You have logged in successfully  %s", showNumber));
                loggedIn.add(serviceNumber);

            } else {
                JOptionPane.showMessageDialog(frame, String.format("ACCESS DENIED  %s", showNumber));
                blacklist.add(serviceNumber);
            }
        }
    }

}
