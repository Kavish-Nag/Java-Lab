import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

// ─────────────────────────────────────────────────────────────────
//  RestaurantUI.java
//  JavaFX screen to perform INSERT, SELECT, UPDATE, DELETE
//  on the Restaurant table.
//
//  Concepts (from syllabus):
//    - Stage, Scene, Nodes
//    - Layouts: VBox, HBox, BorderPane, GridPane
//    - UI Controls: TextField, Button, Label, TableView
//    - Event Handling (all 4 CRUD button clicks)
//    - Connecting JavaFX UI with JDBC
// ─────────────────────────────────────────────────────────────────
public class RestaurantUI {

    private Stage stage;               // the window
    private TableView<RestaurantRow> tableView;   // displays DB rows
    private ObservableList<RestaurantRow> data;   // data bound to TableView

    // Input fields
    private TextField idField;
    private TextField nameField;
    private TextField addressField;

    // Status label at the bottom
    private Label statusLabel;

    // Constructor — receives the main stage so we can go back
    public RestaurantUI(Stage stage) {
        this.stage = stage;
    }

    // ── Build and show the screen ─────────────────────────────────
    public void show() {

        // ── TOP: Title + Back button ──────────────────────────────
        Label title = new Label("Restaurant Table - CRUD Operations");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button backBtn = new Button("← Back to Menu");
        backBtn.setOnAction(e -> MainApp.showMainMenu(stage));

        HBox topBar = new HBox(20, backBtn, title);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(10));

        // ── CENTER: TableView ─────────────────────────────────────
        tableView = new TableView<RestaurantRow>();
        data = FXCollections.observableArrayList();
        tableView.setItems(data);

        // Create columns — each column maps to a field in RestaurantRow
        TableColumn<RestaurantRow, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(60);

        TableColumn<RestaurantRow, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(180);

        TableColumn<RestaurantRow, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(220);

        tableView.getColumns().addAll(idCol, nameCol, addressCol);

        // When user clicks a row → fill the input fields automatically
        tableView.setOnMouseClicked(e -> {
            RestaurantRow selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                idField.setText(selected.getId());
                nameField.setText(selected.getName());
                addressField.setText(selected.getAddress());
            }
        });

        // ── RIGHT: Input Form using GridPane ──────────────────────
        Label idLbl      = new Label("ID:");
        Label nameLbl    = new Label("Name:");
        Label addressLbl = new Label("Address:");

        idField      = new TextField();
        nameField    = new TextField();
        addressField = new TextField();

        idField.setPromptText("e.g. 11");
        nameField.setPromptText("e.g. Spice Garden");
        addressField.setPromptText("e.g. 123 MG Road");

        // GridPane — organises labels and fields in a grid
        GridPane form = new GridPane();
        form.setHgap(10);  // horizontal gap between columns
        form.setVgap(10);  // vertical gap between rows
        form.setPadding(new Insets(10));

        // Row 0
        form.add(idLbl,      0, 0);
        form.add(idField,    1, 0);
        // Row 1
        form.add(nameLbl,    0, 1);
        form.add(nameField,  1, 1);
        // Row 2
        form.add(addressLbl, 0, 2);
        form.add(addressField, 1, 2);

        // ── CRUD Buttons ──────────────────────────────────────────
        Button insertBtn = new Button("INSERT");
        Button selectBtn = new Button("SELECT ALL");
        Button updateBtn = new Button("UPDATE");
        Button deleteBtn = new Button("DELETE");
        Button clearBtn  = new Button("Clear Fields");

        insertBtn.setPrefWidth(120);
        selectBtn.setPrefWidth(120);
        updateBtn.setPrefWidth(120);
        deleteBtn.setPrefWidth(120);
        clearBtn.setPrefWidth(120);

        // Style the buttons with different colors
        insertBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        selectBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        updateBtn.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        // VBox to stack buttons vertically
        VBox buttonBox = new VBox(10, insertBtn, selectBtn, updateBtn, deleteBtn, clearBtn);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.TOP_CENTER);

        // Combine form + buttons in a VBox on the right side
        VBox rightPanel = new VBox(15, form, buttonBox);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setPrefWidth(280);

        // ── BOTTOM: Status message ────────────────────────────────
        statusLabel = new Label("Ready. Click SELECT ALL to load data.");
        statusLabel.setStyle("-fx-padding: 8px; -fx-font-size: 13px;");

        // ── Event Handling ────────────────────────────────────────

        // INSERT button
        insertBtn.setOnAction(e -> handleInsert());

        // SELECT ALL button
        selectBtn.setOnAction(e -> handleSelectAll());

        // UPDATE button
        updateBtn.setOnAction(e -> handleUpdate());

        // DELETE button
        deleteBtn.setOnAction(e -> handleDelete());

        // Clear button
        clearBtn.setOnAction(e -> {
            idField.clear();
            nameField.clear();
            addressField.clear();
            statusLabel.setText("Fields cleared.");
        });

        // ── BorderPane: arrange all sections ─────────────────────
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(tableView);
        root.setRight(rightPanel);
        root.setBottom(statusLabel);
        BorderPane.setMargin(tableView, new Insets(0, 10, 0, 10));

        // ── Scene and Stage ───────────────────────────────────────
        Scene scene = new Scene(root, 800, 450);
        stage.setTitle("Manage Restaurants");
        stage.setScene(scene);
        stage.show();

        // Load data automatically when screen opens
        handleSelectAll();
    }

    // ── INSERT handler ────────────────────────────────────────────
    private void handleInsert() {
        try {
            int    id      = Integer.parseInt(idField.getText().trim());
            String name    = nameField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || address.isEmpty()) {
                setStatus("Please fill all fields.", "red");
                return;
            }

            RestaurantJDBC.insertRestaurant(MainApp.con, id, name, address);
            setStatus("Inserted: " + name, "green");
            handleSelectAll(); // refresh table

        } catch (NumberFormatException e) {
            setStatus("ID must be a number.", "red");
        } catch (Exception e) {
            setStatus("Insert failed: " + e.getMessage(), "red");
        }
    }

    // ── SELECT ALL handler ────────────────────────────────────────
    private void handleSelectAll() {
        try {
            data.clear(); // clear old data from table
            ArrayList<String[]> rows = RestaurantJDBC.getAllRestaurants(MainApp.con);

            for (String[] row : rows) {
                // row[0]=id, row[1]=name, row[2]=address
                data.add(new RestaurantRow(row[0], row[1], row[2]));
            }

            setStatus("Loaded " + rows.size() + " restaurant(s).", "blue");

        } catch (Exception e) {
            setStatus("Select failed: " + e.getMessage(), "red");
        }
    }

    // ── UPDATE handler ────────────────────────────────────────────
    private void handleUpdate() {
        try {
            int    id      = Integer.parseInt(idField.getText().trim());
            String name    = nameField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || address.isEmpty()) {
                setStatus("Please fill all fields.", "red");
                return;
            }

            int rows = RestaurantJDBC.updateRestaurant(MainApp.con, id, name, address);
            if (rows > 0) {
                setStatus("Updated Restaurant ID: " + id, "green");
                handleSelectAll();
            } else {
                setStatus("No restaurant found with ID: " + id, "red");
            }

        } catch (NumberFormatException e) {
            setStatus("ID must be a number.", "red");
        } catch (Exception e) {
            setStatus("Update failed: " + e.getMessage(), "red");
        }
    }

    // ── DELETE handler ────────────────────────────────────────────
    private void handleDelete() {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            int rows = RestaurantJDBC.deleteRestaurant(MainApp.con, id);
            if (rows > 0) {
                setStatus("Deleted Restaurant ID: " + id, "green");
                handleSelectAll();
                idField.clear(); nameField.clear(); addressField.clear();
            } else {
                setStatus("No restaurant found with ID: " + id, "red");
            }

        } catch (NumberFormatException e) {
            setStatus("ID must be a number.", "red");
        } catch (Exception e) {
            setStatus("Delete failed: " + e.getMessage(), "red");
        }
    }

    // ── Helper: set status label text and colour ──────────────────
    private void setStatus(String msg, String color) {
        statusLabel.setText(msg);
        statusLabel.setStyle("-fx-text-fill: " + color + "; -fx-padding: 8px; -fx-font-size: 13px;");
    }

    // ════════════════════════════════════════════════════════════
    //  Inner class: RestaurantRow
    //  JavaFX TableView needs a class with getters matching column names.
    //  Concepts: Inner class, private members, getters
    // ════════════════════════════════════════════════════════════
    public static class RestaurantRow {
        private String id;
        private String name;
        private String address;

        public RestaurantRow(String id, String name, String address) {
            this.id      = id;
            this.name    = name;
            this.address = address;
        }

        public String getId()      { return id; }
        public String getName()    { return name; }
        public String getAddress() { return address; }
    }
}
