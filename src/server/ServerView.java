package server;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ServerView {
    private ServerModel model;
    private Stage stage;

    protected Label lblPort = new Label("Port");
    protected TextField txtPort = new TextField("8080");
    protected Button btnGo = new Button("Go");
    protected TextArea txtLog = new TextArea();
    
    protected ServerView(Stage stage, ServerModel model) {
        this.stage = stage;
        this.model = model;
        
        stage.setTitle("Web Server v0");
        
        BorderPane root = new BorderPane();

        HBox topBox = new HBox();
        topBox.setId("TopBox");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        root.setTop(topBox);
        
        topBox.getChildren().addAll(lblPort, txtPort, spacer, btnGo);
        txtPort.setId("Port");
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);
        scrollPane.setContent(txtLog);
        txtLog.setWrapText(true);

        Scene scene = new Scene(root);
        stage.setScene(scene);;
    }
    
    public void start() {
        stage.show();
    }
    
    /**
     * Stopping the view - just make it invisible
     */
    public void stop() {
        stage.hide();
    }
    
    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
        return stage;
    }
}
