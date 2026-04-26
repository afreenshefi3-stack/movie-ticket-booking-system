import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LoginPage {
    private Stage stage;

    public LoginPage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("🎬 Movie Ticket Booking System");

        Label title = new Label("🎬 Movie Ticket Booking");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label subtitle = new Label("Please login to continue");
        subtitle.setFont(Font.font("Arial", 14));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setMaxWidth(250);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setMaxWidth(250);

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: #e50914; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 20;");
        loginBtn.setMinWidth(120);

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        loginBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || email.isEmpty()) {
                errorLabel.setText("⚠ Please fill in all fields.");
            } else if (!email.contains("@")) {
                errorLabel.setText("⚠ Enter a valid email address.");
            } else {
                new MovieSelectionPage(stage, name).show();
            }
        });

        VBox layout = new VBox(15, title, subtitle, nameField, emailField, loginBtn, errorLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #1a1a2e;");
        title.setStyle("-fx-text-fill: white;");
        subtitle.setStyle("-fx-text-fill: #aaaaaa;");
        nameField.setStyle("-fx-background-color: #16213e; -fx-text-fill: white; -fx-prompt-text-fill: gray;");
        emailField.setStyle("-fx-background-color: #16213e; -fx-text-fill: white; -fx-prompt-text-fill: gray;");

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
}