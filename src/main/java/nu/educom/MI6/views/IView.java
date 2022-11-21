package nu.educom.MI6.views;

import nu.educom.MI6.data.objects.Input;
import nu.educom.MI6.presenters.IPresentor;

import java.util.HashMap;

public interface IView {

    void askForLogin();
    void askForPassword();
    void showMessage(String message);
    void installPresentor(IPresentor presentor);
    HashMap<String, Input> updateUserInput();

}

