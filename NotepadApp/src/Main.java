import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        TextArea textArea = new TextArea();

        MenuBar menuBar = new MenuBar();

        // FILE MENU
        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem saveFile = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");

        newFile.setOnAction(e -> textArea.clear());
        exit.setOnAction(e -> stage.close());

        fileMenu.getItems().addAll(newFile, openFile, saveFile, exit);

        // EDIT MENU
        Menu editMenu = new Menu("Edit");

        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        MenuItem cut = new MenuItem("Cut");

        copy.setOnAction(e -> textArea.copy());
        paste.setOnAction(e -> textArea.paste());
        cut.setOnAction(e -> textArea.cut());

        editMenu.getItems().addAll(copy, paste, cut);

        // MENU BAR
        menuBar.getMenus().addAll(fileMenu, editMenu);

        // LAYOUT
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("My Notepad");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}