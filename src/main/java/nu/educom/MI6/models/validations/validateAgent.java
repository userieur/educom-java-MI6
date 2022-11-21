package nu.educom.MI6.models.validations;

import nu.educom.MI6.data.objects.Agent;
import nu.educom.MI6.data.objects.LoginAttempt;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class validateAgent {

    private Agent agent;
    private List<LoginAttempt> loginAttempts;
    private int result;

    public validateAgent (Agent agent, List<LoginAttempt> loginAttempts) {
        this.agent = agent;
        this.loginAttempts = loginAttempts;
    }

    public String validate (String inputPassword) {
        if (!checkPassword(inputPassword)) {return String.format("Access Denied %s", agent.getAgentNumber());}
        if (!checkActive()) {return String.format("Access Denied %s", agent.getAgentNumber());}
        if (!(checkTimeOut()==0)) {return String.format("You are time out for %s more seconds %s", result ,agent.getAgentNumber());}
        return "";
    }

    private boolean checkPassword (String pw) {
        return pw.equals(agent.getPassword());
    }

    private boolean checkActive() {
        return agent.isActive();
    }

    private int checkTimeOut() {
        int tries = 0;
        try {tries = loginAttempts.size();}
        catch (Exception e) {}

        if (tries > 0) {
            var now     = LocalDateTime.now();
            var lastAttempt     = loginAttempts.get(tries-1).getTimestamp();
            int diff    = (int) Duration.between(lastAttempt, now).getSeconds();
            int timeout = (int) (Math.pow(2, tries)*60);
            result  = timeout-diff;
            return Math.max(result, 0);
        }
        return 0;
    }

}
