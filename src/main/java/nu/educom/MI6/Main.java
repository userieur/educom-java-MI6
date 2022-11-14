package nu.educom.MI6;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    int serviceNumber = 0;
    String actualNumber = "";
    String codeSentence = "For ThE Royal QUEEN";
    boolean turnedOn = true;
    ArrayList<Integer> blacklist = new ArrayList<Integer>();
    ArrayList<Integer> loggedIn = new ArrayList<Integer>();



    //  ASK FOR THE INPUT FROM USER, IF NOT INTEGER RETURN PLEASE ENTER NUMBER
    while (turnedOn) {
      System.out.println("Enter service number");
      boolean needCodeSentence = true;

      while (true) {
        try {
          Scanner scanner = new Scanner(System.in);
          int userInput = scanner.nextInt();
//          System.out.println("You have entered : " + userInput);

          if (blacklist.contains(userInput)) {
            System.out.println("You are still a failure " + String.format("%03d", serviceNumber));
            needCodeSentence = false;
            break;
          } else if (loggedIn.contains(userInput)) {
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

//      System.out.println("Service Number is: " + actualNumber);

      //  ASK FOR THE INPUT FROM USER, IF NOT INTEGER RETURN PLEASE ENTER NUMBER
      if (needCodeSentence) {
        System.out.println("Please enter code-sentence: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
//        System.out.println("You have entered : " + userInput);
        if (userInput.equals(codeSentence)) {
          System.out.println("You have logged in successfully " + actualNumber);
          loggedIn.add(serviceNumber);
        } else {
          System.out.println("You is stupid: " + actualNumber);
          blacklist.add(serviceNumber);
        }
      }
    }



  }
}