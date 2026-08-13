import java.sql.*;
//import jdbc classes
//prepared is for SQL code, here query is treated as data, not SQL code
public class Queries {
    //VIEW ALL TEAMS
    public static void viewTeams() {
        try {
            Connection con = DBConnection.connect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM team");
            //result set is the collection of rows/stores the data returned by the sql query
            while (rs.next()) {    //moves row by row
                System.out.println(rs.getInt("team_id") + " | " +
                        rs.getString("team_name") + " | " +
                        rs.getString("city"));
            }
            con.close();   //closes the db connection
        } catch (Exception e) { System.out.println(e); }
    }
    //VIEW PLAYER USING NAME
    public static void playerByName(String name) {
        try {
            Connection con = DBConnection.connect();

            String q = "SELECT p.name, p.role, ps.runs_scored, ps.wickets_taken, ps.catches_taken " +
                    "FROM player p JOIN player_stats ps ON p.player_id = ps.player_id WHERE p.name = ?";

            PreparedStatement ps = con.prepareStatement(q);   //prepares the query
            ps.setString(1, name);    //replaces question mark wih the actual val

            ResultSet rs = ps.executeQuery();   //executes the query

            while (rs.next()) {
                System.out.println(rs.getString("name") + " | " +
                        rs.getString("role") + " | Runs: " +
                        rs.getInt("runs_scored") + " | Wickets: " +
                        rs.getInt("wickets_taken") + " | Catches: " +
                        rs.getInt("catches_taken"));
            }
            con.close();
        } catch (Exception e) { System.out.println(e); }
    }
    //VIEW THE COACHES OF ALL TEAMS
    public static void viewCoaches() {
        try {
            Connection con = DBConnection.connect();

            String q = "SELECT c.name, c.experience, t.team_name " +
                    "FROM coach c JOIN team t ON c.team_id = t.team_id";

            ResultSet rs = con.createStatement().executeQuery(q);

            while (rs.next()) {
                System.out.println(rs.getString("name") +
                        " | Exp: " + rs.getInt("experience") +
                        " | Team: " + rs.getString("team_name"));
            }
            con.close();
        } catch (Exception e) { System.out.println(e); }
    }
    //  VIEW ALL STADIUMS
    public static void viewStadiums() {
        try {
            Connection con = DBConnection.connect();

            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT stadium_id, stadium_name, city, capacity FROM stadium");

            while (rs.next()) {
                System.out.println(rs.getInt("stadium_id") + " | " +
                        rs.getString("stadium_name") + " | " +
                        rs.getString("city") + " | Cap: " +
                        rs.getInt("capacity"));
            }
            con.close();
        } catch (Exception e) { System.out.println(e); }
    }
    //TOP GUNS
    public static void topStats() {
        try {
            Connection con = DBConnection.connect();

            String q =
                    "SELECT " +
                            "(SELECT p.name FROM player p JOIN player_stats ps ON p.player_id = ps.player_id " +
                            "WHERE ps.runs_scored = (SELECT MAX(runs_scored) FROM player_stats) FETCH FIRST 1 ROWS ONLY) AS top_runs, " +
                            "(SELECT p.name FROM player p JOIN player_stats ps ON p.player_id = ps.player_id " +
                            "WHERE ps.wickets_taken = (SELECT MAX(wickets_taken) FROM player_stats) FETCH FIRST 1 ROWS ONLY) AS top_wickets, " +
                            "(SELECT p.name FROM player p JOIN player_stats ps ON p.player_id = ps.player_id " +
                            "WHERE ps.catches_taken = (SELECT MAX(catches_taken) FROM player_stats) FETCH FIRST 1 ROWS ONLY) AS top_catches " +
                            "FROM dual";

            ResultSet rs = con.createStatement().executeQuery(q);

            while (rs.next()) {
                System.out.println("Top Run Scorer: " + rs.getString("top_runs"));
                System.out.println("Top Wicket Taker: " + rs.getString("top_wickets"));
                System.out.println("Top Catches: " + rs.getString("top_catches"));
            }

            con.close();
        } catch (Exception e) { System.out.println(e); }
    }

    public static void teamTotalRuns() {
        try {
            Connection con = DBConnection.connect();
            //find all the playrs of the team, and add their runs
            String q = "SELECT t.team_name, SUM(ps.runs_scored) total_runs " +
                    "FROM team t JOIN player p ON t.team_id = p.team_id " +
                    "JOIN player_stats ps ON p.player_id = ps.player_id " +
                    "GROUP BY t.team_name ORDER BY total_runs DESC";

            ResultSet rs = con.createStatement().executeQuery(q);

            while (rs.next()) {
                System.out.println(rs.getString("team_name") +
                        " | Total Runs: " + rs.getInt("total_runs"));
            }

            con.close();
        } catch (Exception e) { System.out.println(e); }
    }
    //add a stadium
   /* public static void addStadium(int id, String name, String city, int cap) {
        try {
            Connection con = DBConnection.connect();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO stadium VALUES (?, ?, ?, ?)");    //insert query

            ps.setInt(1, id);      //binds the values
            ps.setString(2, name);
            ps.setString(3, city);
            ps.setInt(4, cap);

            ps.executeUpdate();    //executes the insert query

            System.out.println("Stadium added!");
            con.close();
        } catch (Exception e) { System.out.println(e); }
    }*/

    public static String addStadium(int id, String name, String city, int cap) {
        try {
            Connection con = DBConnection.connect();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO stadium VALUES (?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, city);
            ps.setInt(4, cap);

            ps.executeUpdate();

            con.close();
            return "Stadium Added Successfully!";

        } catch (Exception e) {
            String msg = e.getMessage();

            // proper error message
            if (msg != null && msg.contains("ORA-")) {
                msg = msg.substring(msg.indexOf(":") + 1).trim();
            }

            return msg;
        }
    }

    public static void addMatch(int id, int stad, int t1, int t2, int win) {
        try {
            Connection con = DBConnection.connect();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO match VALUES (?, ?, ?, ?, SYSDATE, 0, 0, ?)");

            ps.setInt(1, id);
            ps.setInt(2, stad);
            ps.setInt(3, t1);
            ps.setInt(4, t2);
            ps.setInt(5, win);

            ps.executeUpdate();

            System.out.println("Match added!");
            con.close();
        } catch (Exception e) { System.out.println(e); }
    }

    //  PROCEDURE CALL : match summary
    public static void callMatchSummary(int id) {
        try {
            Connection con = DBConnection.connect();

            CallableStatement cs = con.prepareCall("{call match_summary_proc(?, ?, ?, ?, ?, ?)}");

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.registerOutParameter(6, Types.VARCHAR);

            cs.execute();

            System.out.println("Match: " + cs.getString(2) + " vs " + cs.getString(3));
            System.out.println("Score: " + cs.getInt(4) + " - " + cs.getInt(5));
            System.out.println("Result: " + cs.getString(6));

            con.close();
        } catch (Exception e) { System.out.println(e); }
    }

    //  FUNCTION CALL
    public static void getStrikeRate(String name) {
        try {
            Connection con = DBConnection.connect();

            CallableStatement cs = con.prepareCall("{? = call get_strike_rate(?)}");

            cs.registerOutParameter(1, Types.FLOAT);
            cs.setString(2, name);

            cs.execute();

            System.out.println("Strike Rate: " + cs.getFloat(1));

            con.close();
        } catch (Exception e) { System.out.println(e); }
    }

    // Java uses ResultSet to display players.
    // The PL/SQL version uses a cursor.
    public static void playersByTeam(int id) {
        try {
            Connection con = DBConnection.connect();

            String q = "SELECT name, role FROM player WHERE team_id = ?";

            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getString("role"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}