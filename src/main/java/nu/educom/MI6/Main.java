package nu.educom.MI6;

public class Main {

  public static void main(String[] args) {
    boolean turnedOn = true;

    var loginSession = new Login();
    loginSession.Login();

    while (turnedOn) {
      loginSession.checkLogin();
    }
  }
}