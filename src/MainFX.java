import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) {

        // Buttons
        Button teamsBtn = new Button("View Teams");
        Button playerBtn = new Button("Search Player by name");
        Button coachBtn = new Button("View All Coaches");
        Button stadiumBtn = new Button("View All Stadiums");
        Button statsBtn = new Button("Top Guns");
        Button teamRunsBtn = new Button("Runs by Each Team");

        Button addStadiumBtn = new Button("Add Stadium");
        Button addMatchBtn = new Button("Add Match");

        Button matchBtn = new Button("Match Summary");
        Button strikeBtn = new Button("Strike Rate");
        Button teamPlayersBtn = new Button("Players by Team");

        Button exitBtn = new Button("Exit");

        Button[] allButtons = {
                teamsBtn, playerBtn, coachBtn, stadiumBtn, statsBtn, teamRunsBtn,
                addStadiumBtn, addMatchBtn,
                matchBtn, strikeBtn, teamPlayersBtn,
                exitBtn
        };

        for (Button b : allButtons) {
            b.setPrefWidth(180);
        }

        // Colors
        teamsBtn.setStyle("-fx-background-color: #4fc3f7; -fx-font-weight: bold;");
        playerBtn.setStyle("-fx-background-color: #4fc3f7; -fx-font-weight: bold;");
        coachBtn.setStyle("-fx-background-color: #4fc3f7; -fx-font-weight: bold;");
        stadiumBtn.setStyle("-fx-background-color: #4fc3f7; -fx-font-weight: bold;");
        statsBtn.setStyle("-fx-background-color: #4fc3f7; -fx-font-weight: bold;");
        teamRunsBtn.setStyle("-fx-background-color: #4fc3f7; -fx-font-weight: bold;");

        addStadiumBtn.setStyle("-fx-background-color: #66bb6a; -fx-font-weight: bold;");
        addMatchBtn.setStyle("-fx-background-color: #66bb6a; -fx-font-weight: bold;");

        matchBtn.setStyle("-fx-background-color: #ab47bc; -fx-font-weight: bold;");
        strikeBtn.setStyle("-fx-background-color: #ab47bc; -fx-font-weight: bold;");
        teamPlayersBtn.setStyle("-fx-background-color: #ab47bc; -fx-font-weight: bold;");

        exitBtn.setStyle("-fx-background-color: #ef5350; -fx-font-weight: bold;");

        // Output (BIG RIGHT BOX)
        TextArea output = new TextArea();
        output.setWrapText(true);
        output.setStyle(
                "-fx-control-inner-background: #1e3a8a;" +   // dark blue
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        output.setPrefWidth(450);
        output.setPrefHeight(500);

        // Logo
        Image img = new Image(getClass().getResource("/ipl.png").toExternalForm());
        ImageView logo = new ImageView(img);
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);

        // Heading
        Label heading = new Label("IPL MANAGEMENT SYSTEM");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Top layout
        VBox top = new VBox(5, logo, heading);
        top.setAlignment(Pos.CENTER);

        // LEFT PANEL (buttons)
        VBox left = new VBox(10,
                teamsBtn, playerBtn, coachBtn, stadiumBtn, statsBtn, teamRunsBtn,
                addStadiumBtn, addMatchBtn,
                matchBtn, strikeBtn, teamPlayersBtn,
                exitBtn
        );

        left.setAlignment(Pos.TOP_CENTER);
        left.setStyle(
                "-fx-background-color: #1e293b;" +
                        "-fx-padding: 15;" +
                        "-fx-background-radius: 10;"
        );

        // CENTER LAYOUT (left + right)
        HBox center = new HBox(20, left, output);
        center.setAlignment(Pos.CENTER);

        // ROOT
        VBox root = new VBox(15, top, center);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #0f172a, #1e293b);" +
                        "-fx-padding: 20;"
        );

        stage.setScene(new Scene(root, 700, 600));
        stage.setTitle("IPL System");
        stage.show();

        // ACTIONS

        teamsBtn.setOnAction(e -> output.setText(capture(() -> Queries.viewTeams())));

        playerBtn.setOnAction(e -> {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Player Name");
            d.showAndWait().ifPresent(name ->
                    output.setText(capture(() -> Queries.playerByName(name))));
        });

        coachBtn.setOnAction(e -> output.setText(capture(() -> Queries.viewCoaches())));
        stadiumBtn.setOnAction(e -> output.setText(capture(() -> Queries.viewStadiums())));
        statsBtn.setOnAction(e -> output.setText(capture(() -> Queries.topStats())));
        teamRunsBtn.setOnAction(e -> output.setText(capture(() -> Queries.teamTotalRuns())));

    

        addStadiumBtn.setOnAction(e -> {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter: id,name,city,capacity");

            d.showAndWait().ifPresent(input -> {
                try {
                    String[] p = input.split(",");

                    String result = Queries.addStadium(
                            Integer.parseInt(p[0]),
                            p[1],
                            p[2],
                            Integer.parseInt(p[3])
                    );

                    output.setText(result);

                } catch (Exception ex) {
                    output.setText("Invalid input format! Use: id,name,city,capacity");
                }
            });
        });

        addMatchBtn.setOnAction(e -> {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter: match_id,stadium_id,team1,team2,winner");
            d.showAndWait().ifPresent(input -> {
                try {
                    String[] p = input.split(",");
                    Queries.addMatch(
                            Integer.parseInt(p[0]),
                            Integer.parseInt(p[1]),
                            Integer.parseInt(p[2]),
                            Integer.parseInt(p[3]),
                            Integer.parseInt(p[4]));
                    output.setText("Match Added!");
                } catch (Exception ex) {
                    output.setText("Invalid input");
                }
            });
        });

        matchBtn.setOnAction(e -> {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Match ID");
            d.showAndWait().ifPresent(id ->
                    output.setText(capture(() ->
                            Queries.callMatchSummary(Integer.parseInt(id)))));
        });

        strikeBtn.setOnAction(e -> {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Player Name");
            d.showAndWait().ifPresent(name ->
                    output.setText(capture(() -> Queries.getStrikeRate(name))));
        });

        teamPlayersBtn.setOnAction(e -> {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Team ID");
            d.showAndWait().ifPresent(id ->
                    output.setText(capture(() ->
                            Queries.playersByTeam(Integer.parseInt(id)))));
        });

        exitBtn.setOnAction(e -> stage.close());
    }

    private String capture(Runnable action) {
        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        action.run();

        System.setOut(old);
        return baos.toString();
    }

    public static void main(String[] args) {
        launch();
    }
}