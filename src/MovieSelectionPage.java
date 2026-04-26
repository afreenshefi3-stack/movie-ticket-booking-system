import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MovieSelectionPage {
    private Stage stage;
    private String userName;

    String[][] movies = {
        {"The Drama", "Romance | 3h 2m", "⭐ 8.4"},
        {"Inception",         "Sci-Fi | 2h 28m", "⭐ 8.8"},
        {"Project Hail Mary",   "Sci-Fi | 2h 32m", "⭐ 9.0"},
        {"Interstellar",      "Sci-Fi | 2h 49m", "⭐ 8.6"},
        {"Spider-Man: NWH",   "Action | 2h 28m", "⭐ 8.2"},
        {"Oppenheimer",       "Drama  | 3h 0m",  "⭐ 8.5"}
    };

    String[] showTimes = {"10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM", "10:00 PM"};

    public MovieSelectionPage(Stage stage, String userName) {
        this.stage = stage;
        this.userName = userName;
    }

    public void show() {
        Label title = new Label("🎬 Select a Movie");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        title.setStyle("-fx-text-fill: white;");

        Label welcome = new Label("Welcome, " + userName + "!");
        welcome.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 13px;");

        ComboBox<String> movieBox = new ComboBox<>();
        for (String[] m : movies) movieBox.getItems().add(m[0]);
        movieBox.setPromptText("Choose a movie");
        movieBox.setMinWidth(300);

        Label infoLabel = new Label("");
        infoLabel.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 13px;");

        movieBox.setOnAction(e -> {
            int idx = movieBox.getSelectionModel().getSelectedIndex();
            if (idx >= 0)
                infoLabel.setText(movies[idx][1] + "   " + movies[idx][2]);
        });

        ComboBox<String> timeBox = new ComboBox<>();
        timeBox.getItems().addAll(showTimes);
        timeBox.setPromptText("Choose show time");
        timeBox.setMinWidth(300);

        Button nextBtn = new Button("Select Seats →");
        nextBtn.setStyle("-fx-background-color: #e50914; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 20;");

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        nextBtn.setOnAction(e -> {
            String movie = movieBox.getValue();
            String time  = timeBox.getValue();
            if (movie == null || time == null) {
                errorLabel.setText("⚠ Please select both a movie and show time.");
            } else {
                new SeatBookingPage(stage, userName, movie, time).show();
            }
        });

        VBox layout = new VBox(15, title, welcome, movieBox, infoLabel, timeBox, nextBtn, errorLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #1a1a2e;");

        movieBox.setStyle("-fx-background-color: #16213e; -fx-text-fill: white;");
        timeBox.setStyle("-fx-background-color: #16213e; -fx-text-fill: white;");

        stage.setScene(new Scene(layout, 500, 430));
        stage.show();
    }
}