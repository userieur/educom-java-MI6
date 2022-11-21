package nu.educom.MI6.data.objects;

import java.time.LocalDateTime;

public class LoginAttempt {
    private int id                       = 0;
    private int agent_id                 = 0;
    private LocalDateTime timestamp      = null;
    private boolean successful           = false;

    @Override
    public String toString() {
        return "LoginAttempt{" +
                "id=" + id +
                ", agent_id=" + agent_id +
                ", timestamp=" + timestamp +
                ", successful=" + successful +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgentId() {
        return agent_id;
    }

    public void setAgentId(int agent_id) {
        this.agent_id = agent_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
