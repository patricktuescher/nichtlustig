package server;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ServerView {
	
	protected Stage primaryStage;
	protected ServerModel model;
	protected Button ConnectServer, DisconnectServer;
	protected Circle status;
	protected Label onlineOffline;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		
		//Setting up BorderPane
		BorderPane topPane = new BorderPane();
		topPane.setId("topPane"); //ID for CSS
		topPane.setPrefHeight(800);
		topPane.setPrefWidth(1200);
		
		//Offline-Online Status monitor
		status = new Circle();
		status.setRadius(30);
		status.setId("redCircle");
		
		//Online-Offline-Label
		onlineOffline = new Label("Offline");
		onlineOffline.setId("offline");
		
		
		//Connect- and Disconnect Buttons
		ConnectServer = new Button("Connect to Server");
		ConnectServer.setId("ServerButtons");
		ConnectServer.setPrefWidth(500);
		DisconnectServer = new Button("Disconnect from Server");
		DisconnectServer.setId("ServerButtons");
		DisconnectServer.setPrefWidth(500);
		
		//upperPane
		GridPane upperPane = new GridPane();
		upperPane.setPrefHeight(500);
		upperPane.setAlignment(Pos.TOP_LEFT);
		upperPane.setVgap(10);
		upperPane.setHgap(10);
		upperPane.add(status, 35, 26);
		upperPane.add(onlineOffline, 36, 26);
		
		//bottomPane
		GridPane bottomPane = new GridPane();
		bottomPane.setAlignment(Pos.TOP_CENTER);
		bottomPane.setVgap(10);
		bottomPane.add(ConnectServer, 0, 0);
		bottomPane.add(DisconnectServer, 0, 1);
		
		//Vertical-Box
		VBox centerBox = new VBox();
		centerBox.getChildren().add(upperPane);
		centerBox.getChildren().add(bottomPane);
		topPane.setCenter(centerBox);
		
		Scene scene = new Scene(topPane);
		scene.getStylesheets().add("ServerStylesheet");
		primaryStage.setTitle("Server-Applikation");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
	}
	
	public void start(){
		primaryStage.show();
	}

}
