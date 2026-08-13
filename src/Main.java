import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n========= IPL DATABASE =========");
            System.out.println("1  - View Teams");
            System.out.println("2  - Search Player");
            System.out.println("3  - Coaches");
            System.out.println("4  - Stadiums");
            System.out.println("5  - Top Stats");
            System.out.println("6  - Team Total Runs");
            System.out.println("7  - Add Stadium");
            System.out.println("8  - Add Match");
            System.out.println("9  - Match Summary (Procedure)");
            System.out.println("10 - Strike Rate (Function)");
            System.out.println("11 - Players by Team (Cursor)");
            System.out.println("12 - Exit");

            System.out.print("\nEnter your choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1 -> Queries.viewTeams();

                case 2 -> {
                    System.out.print("Enter name: ");
                    Queries.playerByName(sc.nextLine());
                }

                case 3 -> Queries.viewCoaches();

                case 4 -> Queries.viewStadiums();

                case 5 -> Queries.topStats();

                case 6 -> Queries.teamTotalRuns();

                case 7 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    System.out.print("City: ");
                    String c = sc.nextLine();
                    System.out.print("Capacity: ");
                    int cap = sc.nextInt();

                    Queries.addStadium(id, n, c, cap);
                }

                case 8 -> {
                    System.out.print("Match ID: ");
                    int id = sc.nextInt();
                    System.out.print("Stadium ID: ");
                    int s = sc.nextInt();
                    System.out.print("Team1 ID: ");
                    int t1 = sc.nextInt();
                    System.out.print("Team2 ID: ");
                    int t2 = sc.nextInt();
                    System.out.print("Winner ID: ");
                    int w = sc.nextInt();

                    Queries.addMatch(id, s, t1, t2, w);
                }

                case 9 -> {
                    System.out.print("Enter match ID: ");
                    Queries.callMatchSummary(sc.nextInt());
                }

                case 10 -> {
                System.out.print("Enter player name: ");
                Queries.getStrikeRate(sc.nextLine());
                }

                case 11 -> {
                    System.out.print("Enter team ID: ");
                    Queries.playersByTeam(sc.nextInt());
                }

                case 12 -> System.exit(0);

                default -> System.out.println("Invalid choice");
            }
        }
    }
}