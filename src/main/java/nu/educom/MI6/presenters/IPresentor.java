package nu.educom.MI6.presenters;

import nu.educom.MI6.data.objects.Agent;

public interface IPresentor {
    void displayLogin();
    void triggerLogin();
    void triggerPassword();
    void exit();
}
