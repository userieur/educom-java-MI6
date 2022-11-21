package nu.educom.MI6.models;

import nu.educom.MI6.data.Crud;
import nu.educom.MI6.data.objects.*;
import nu.educom.MI6.models.validations.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {

    private Crud                    crud;
    private Agent                   agent;
    private String                  error;
    private List<LoginAttempt>      loginAttempts   = new ArrayList<LoginAttempt>();
    public HashMap<String, Input>   userInput;

    public Model(Crud crud) {
        this.crud = crud;
    }

    public void validateAgentId() {
        var input           = new AgentNumberValidator(userInput.get("Login").value);
        if (!input.isValid()) {
            userInput.get("Login").error = input.getError();
        } else {
            this.agent      = new Agent();
            agent.setAgentNumber(input.getValue());
        }
    }

    public boolean authenticateAgent() throws SQLException {
        String agentNR          = agent.getAgentNumber();
        agent                   = crud.fetchAgent(agentNR);
        loginAttempts           = crud.fetchLoginAttempts(agent.getID());
        var validator           = new validateAgent(agent, loginAttempts);
        error                   = validator.validate(userInput.get("Password").value);
        // Regardless of success, register attempt
        crud.addLoginAttempt(agent.getID(), error.equals(""));
        return error.equals("");
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getError() {
        return error;
    }
}
