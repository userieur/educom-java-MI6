package nu.educom.MI6.data.objects;

import java.util.Date;

public class Agent {
    private int ID                  = 0;
    private String agent_number     = null;
    private String password         = null;
    private boolean active          = false;
    private Date licence_to_kill    = null;

    @Override
    public String toString() {
        return "Agent{" +
                "ID=" + ID +
                ", agent_number='" + agent_number + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", licence_to_kill=" + licence_to_kill +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAgentNumber() {
        return agent_number;
    }

    public void setAgentNumber(String agent_number) {
        this.agent_number = agent_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getLicenceToKill() {
        return licence_to_kill;
    }

    public void setLicencToKill(Date licence_to_kill) {
        this.licence_to_kill = licence_to_kill;
    }

}
