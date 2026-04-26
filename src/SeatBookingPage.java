import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.*;

public class SeatBookingPage {
    private Stage stage;
    private String userName, movie, time;
    private Set<String> selectedSeats = new HashSet<>();

    // Pre-book some seats to make it realistic
    private Set<String> bookedSeats = new HashSet<>(Arrays.asList("A1","A2","B4","C3","D5","E2","E3"));

    private static final double PRICE = 180.0; // ₹ per seat

    public SeatBookingPage(Stage stage, String userName, String movie, String time) {
        this.stage = stage;
        this.userName = userName;
        this.movie = movie;
        this.time = time;
    }

    public void show() {
        Label title = new Label("🪑 Select Your Seats");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: white;");

        Label movieLabel = new Label("🎬 " + movie + "  |  🕐 " + time);
        movieLabel.setStyle("-fx-text-fill: #cccccc;");

        // Screen label
        Label screen = new Label("━━━━━━━━  SCREEN  ━━━━━━━━");
        screen.setStyle("-fx-text-fill: #e50914; -fx-font-size: 13px;");

        // Legend
        Button av = new Button("Available"); av.setStyle("-fx-background-color: #16213e; -fx-text-fill: white; -fx-font-size: 10px;");
        Button sel = new Button("Selected");  sel.setStyle("-fx-background-color: #e50914; -fx-text-fill: white; -fx-font-size: 10px;");
        Button bk  = new Button("Booked");    bk.setStyle("-fx-background-color: #555555; -fx-text-fill: white; -fx-font-size: 10px;");
        HBox legend = new HBox(10, av, sel, bk);
        legend.setAlignment(Pos.CENTER);

        Label selectedLabel = new Label("Selected: None");
        selectedLabel.setStyle("-fx-text-fill: #aaaaaa;");

        Label priceLabel = new Label("Total: ₹0.00");
        priceLabel.setStyle("-fx-text-fill: #00ff99; -fx-font-size: 15px;");

        // Seat grid — 5 rows (A–E), 8 cols
        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setAlignment(Pos.CENTER);

        String[] rows = {"A","B","C","D","E"};
        Map<String, Button> seatButtons = new HashMap<>();

        for (int r = 0; r < rows.length; r++) {
            Label rowLabel = new Label(rows[r]);
            rowLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
            grid.add(rowLabel, 0, r);

            for (int c = 1; c <= 8; c++) {
                String seatId = rows[r] + c;
                Button seatBtn = new Button(seatId);
                seatBtn.setMinSize(48, 36);
                seatBtn.setFont(Font.font("Arial", 10));

                if (bookedSeats.contains(seatId)) {
                    seatBtn.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
                    seatBtn.setDisable(true);
                } else {
                    seatBtn.setStyle("-fx-background-color: #16213e; -fx-text-fill: white;");
                    seatBtn.setOnAction(e -> {
                        if (selectedSeats.contains(seatId)) {
                            selectedSeats.remove(seatId);
                            seatBtn.setStyle("-fx-background-color: #16213e; -fx-text-fill: white;");
                        } else {
                            selectedSeats.add(seatId);
                            seatBtn.setStyle("-fx-background-color: #e50914; -fx-text-fill: white;");
                        }
                        updateLabels(selectedLabel, priceLabel);
                    });
                }
                seatButtons.put(seatId, seatBtn);
                grid.add(seatBtn, c, r);
            }
        }

        Button bookBtn = new Button("Confirm Booking ✔");
        bookBtn.setStyle("-fx-background-color: #e50914; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 20;");

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        bookBtn.setOnAction(e -> {
            if (selectedSeats.isEmpty()) {
                errorLabel.setText("⚠ Please select at least one seat.");
            } else {
                double total = selectedSeats.size() * PRICE;
                new ConfirmationPage(stage, userName, movie, time,
                        new ArrayList<>(selectedSeats), total).show();
            }
        });

        VBox layout = new VBox(12, title, movieLabel, screen, legend, grid, selectedLabel, priceLabel, bookBtn, errorLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #1a1a2e;");

        stage.setScene(new Scene(layout, 560, 560));
        stage.show();
    }

    private void updateLabels(Label selectedLabel, Label priceLabel) {
        if (selectedSeats.isEmpty()) {
            selectedLabel.setText("Selected: None");
            priceLabel.setText("Total: ₹0.00");
        } else {
            List<String> sorted = new ArrayList<>(selectedSeats);
            Collections.sort(sorted);
            selectedLabel.setText("Selected: " + sorted);
            priceLabel.setText("Total: ₹" + String.format("%.2f", selectedSeats.size() * PRICE));
        }
    }
}