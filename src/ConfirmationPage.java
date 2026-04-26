import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.*;

public class ConfirmationPage {
    private Stage stage;
    private String userName, movie, time;
    private List<String> seats;
    private double total;

    public ConfirmationPage(Stage stage, String userName, String movie,
                            String time, List<String> seats, double total) {
        this.stage = stage;
        this.userName = userName;
        this.movie = movie;
        this.time = time;
        this.seats = seats;
        this.total = total;
    }

    public void show() {
        Label tick = new Label("✅");
        tick.setFont(Font.font("Arial", 50));

        Label title = new Label("Booking Confirmed!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        title.setStyle("-fx-text-fill: #00ff99;");

        Collections.sort(seats);
        String bookingId = "MTB" + (int)(Math.random() * 90000 + 10000);

        VBox detailsBox = new VBox(10);
        detailsBox.setAlignment(Pos.CENTER_LEFT);
        detailsBox.setPadding(new Insets(10, 40, 10, 40));

        detailsBox.getChildren().addAll(
            styledLabel("👤  Name:        " + userName),
            styledLabel("🎬  Movie:       " + movie),
            styledLabel("🕐  Time:        " + time),
            styledLabel("🪑  Seats:       " + seats),
            styledLabel("💰  Total:       ₹" + String.format("%.2f", total)),
            styledLabel("🎟  Booking ID:  " + bookingId)
        );

        Separator sep1 = new Separator();
        Separator sep2 = new Separator();

        Label note = new Label("Please collect your tickets 15 mins before showtime.");
        note.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 12px;");
        note.setWrapText(true);
        note.setTextAlignment(TextAlignment.CENTER);

        Button homeBtn = new Button("🏠 Book Another Ticket");
        homeBtn.setStyle("-fx-background-color: #e50914; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 20;");
        homeBtn.setOnAction(e -> new LoginPage(stage).show());

        VBox layout = new VBox(15, tick, title, sep1, detailsBox, sep2, note, homeBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #1a1a2e;");

        Scene scene = new Scene(layout, 480, 480);
        stage.setScene(scene);
        stage.show();
    }

    private Label styledLabel(String text) {
        Label l = new Label(text);
        l.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        return l;
    }
}