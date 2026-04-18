import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

// ─────────────────────────────────────────────────────────────────
//  MainApp.java
//  Entry point of the JavaFX application.
//
//  Concepts (from syllabus):
//    - JavaFX Application Life Cycle
//    - Stage (the window)
//    - Scene (what's inside the window)
//    - Nodes (Label, Button)
//    - Layout: BorderPane, VBox
//    - Event Handling (button clicks)
//    - Connecting JavaFX UI with JDBC
// ─────────────────────────────────────────────────────────────────
public class MainApp extends Application {

    // Shared DB connection — created once, used across all screens
    static Connection con;

    // ── JavaFX Life Cycle: start() is called after init() ────────
    @Override
    public void start(Stage primaryStage) {

        // Step 1: Connect to database when app starts
        try {
            con = RestaurantJDBC.getConnection();
            RestaurantJDBC.createTables(con);
        } catch (Exception e) {
            // Show error alert if DB connection fails
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Could not connect to database.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return; // stop the app if DB is not available
        }

        // Step 2: Build the Main Menu screen and show it
        showMainMenu(primaryStage);
    }

    // ── Main Menu Screen ──────────────────────────────────────────
    public static void showMainMenu(Stage stage) {

        // Label — title
        Label titleLabel = new Label("Restaurant Management System");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label subLabel = new Label("Select a table to manage:");
        subLabel.setStyle("-fx-font-size: 14px;");

        // Buttons — one for each table
        Button restaurantBtn = new Button("Manage Restaurants");
        Button menuItemBtn   = new Button("Manage Menu Items");
        Button exitBtn       = new Button("Exit");

        // Button sizes
        restaurantBtn.setPrefWidth(220);
        menuItemBtn.setPrefWidth(220);
        exitBtn.setPrefWidth(220);

        // ── Event Handling ────────────────────────────────────────
        // When "Manage Restaurants" is clicked → open RestaurantUI
        restaurantBtn.setOnAction(e -> {
            RestaurantUI restaurantScreen = new RestaurantUI(stage);
            restaurantScreen.show();
        });

        // When "Manage Menu Items" is clicked → open MenuItemUI
        menuItemBtn.setOnAction(e -> {
            MenuItemUI menuScreen = new MenuItemUI(stage);
            menuScreen.show();
        });

        // Exit button closes the app
        exitBtn.setOnAction(e -> {
            try {
                if (con != null && !con.isClosed()) con.close();
            } catch (Exception ex) { }
            stage.close();
        });

        // ── Layout: VBox (vertical stack of nodes) ────────────────
        VBox vbox = new VBox(15); // 15px spacing between children
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(40));
        vbox.getChildren().addAll(titleLabel, subLabel, restaurantBtn, menuItemBtn, exitBtn);

        // ── BorderPane — center the VBox ─────────────────────────
        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        // ── Scene — wrap the layout ───────────────────────────────
        Scene scene = new Scene(root, 450, 350);

        // ── Stage — the window ────────────────────────────────────
        stage.setTitle("Restaurant Management System");
        stage.setScene(scene);
        stage.show();
    }

    // ── main() — launches the JavaFX app ─────────────────────────
    public static void main(String[] args) {
        launch(args);
    }
}
