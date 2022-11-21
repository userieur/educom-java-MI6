package nu.educom.MI6.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nu.educom.MI6.data.objects.Agent;
import nu.educom.MI6.data.objects.LoginAttempt;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Crud {

    private DateTimeFormatter dtf   = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Connection conn         = null;
    private String url              = "jdbc:mysql://127.0.0.1:3306/MI6";
    private String user             = "mi6_user";
    private String password         = "7PBBE(vfg5QfLNp!";

    public Crud() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Agent fetchAgent(String agentNumber) throws SQLException {
        String query            = "SELECT * FROM agents WHERE agent_number= ?";
        PreparedStatement ps    = conn.prepareStatement(query);
                                  ps.setString(1, agentNumber);
        ResultSet rs            = ps.executeQuery();
        boolean check           = false;

        var agent = new Agent();
        while (rs.next()) {
            check = true;
            agent.setID(rs.getInt("id"));
            agent.setAgentNumber(rs.getString("agent_number"));
            agent.setPassword(rs.getString("password"));
            agent.setActive(rs.getBoolean("active"));
            agent.setLicencToKill(rs.getDate("licence_to_kill"));
        }

        return check ? agent : null;
    }

    public void addLoginAttempt (int agentID, boolean success) throws SQLException {
        var now     = LocalDateTime.now();
        String query            = "INSERT INTO login_attempts (agent_id, timestamp, successful) VALUES (?,?,?)";
        PreparedStatement ps    = conn.prepareStatement(query);
        ps.setInt(1, agentID);
        ps.setString(2, dtf.format(now));
        ps.setInt(3, (success ? 1 : 0));
        ps.execute();
    }

    public List<LoginAttempt> fetchLoginAttempts (int agentID) throws SQLException {
        // Find the latest successful login-attempt
        String subquery         = "SELECT MAX(timestamp) FROM login_attempts WHERE agent_id = ? AND successful=1";
        // Combine with main query of finding the id of said login-attempt
        String query            = String.format("SELECT id as 'max_id' FROM login_attempts WHERE agent_id = ? AND timestamp=(%s)", subquery);

        PreparedStatement ps    = conn.prepareStatement(query);
                                  ps.setInt(1, agentID);
                                  ps.setInt(2, agentID);
        ResultSet rs            = ps.executeQuery();

        // If there is no previous successful login, set value to 0
        int lastSuccess;
        try {
            rs.next();
            lastSuccess = rs.getInt("max_id");}
        catch (Exception e) {lastSuccess = 0;}

        query                   = "SELECT * FROM login_attempts WHERE agent_id= ?";
        if (lastSuccess > 0) {  // ^ SELECT ALL if there is no lastSuccess, otherwise v
            query               = String.format("SELECT * FROM login_attempts WHERE agent_id= ? AND id >= %s", lastSuccess);
        }

        ps                      = conn.prepareStatement(query);
                                  ps.setInt(1, agentID);
        rs                      = ps.executeQuery();
        boolean check           = false;

        List<LoginAttempt> loginAttempts = new ArrayList<LoginAttempt>();

        while (rs.next()) {
            check = true;
            var la = new LoginAttempt();
            la.setId(rs.getInt("id"));
            la.setAgentId(rs.getInt("agent_id"));
            la.setTimestamp(LocalDateTime.parse(rs.getString("timestamp"), dtf));
            la.setSuccessful(rs.getBoolean("successful"));
            loginAttempts.add(la);
        }

        // If there are no records, return null
        return check ? loginAttempts : null;
    }
}

