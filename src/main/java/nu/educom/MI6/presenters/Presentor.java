package nu.educom.MI6.presenters;

import nu.educom.MI6.data.Crud;
import nu.educom.MI6.models.Model;
import nu.educom.MI6.views.IView;

public class Presentor implements IPresentor {

    Crud crud;
    IView view;
    Model model;

    public Presentor(IView view, Crud crud) {
        this.view = view;
        this.crud = crud;
        this.model = new Model(crud);
        this.view.installPresentor(this);

    }

    // This function starts the login process
    @Override
    public void displayLogin() {
        view.askForLogin();
    }

    // This function is called when input has been entered
    @Override
    public void triggerLogin() {
        model.userInput = view.updateUserInput();
        model.validateAgentId();
        try { // When input is valid agent_number
            var agentNR = model.getAgent().getAgentNumber();
            if (model.authenticateAgent()) {
                view.showMessage(String.format("Login Successful %s", agentNR));
            } else {
                view.showMessage(model.getError());
            }
            model.setAgent(null);
        } catch (Exception e) { // When input is invalid
            view.showMessage(model.userInput.get("Login").error);
        }
    }

    // This function is called when Agent is allowed to login
    @Override
    public void triggerPassword() {

    }

    @Override
    public void exit() {

    }

}


