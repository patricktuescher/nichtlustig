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
		
		
		//Connect- and Disconnect Buttons
		ConnectServer = new Button("Connect to Server");
		ConnectServer.setId("ServerButtons");
		ConnectServer.setPrefWidth(500);
		DisconnectServer = new Button("Disconnect from Server");
		DisconnectServer.setId("ServerButtons");
		DisconnectServer.setPrefWidth(500);
		
		
		//innerPane
		GridPane innerPane = new GridPane();
		innerPane.setAlignment(Pos.TOP_CENTER);
		innerPane.setVgap(10);
		innerPane.add(status, 0, 27);
		innerPane.add(ConnectServer, 0, 50);
		innerPane.add(DisconnectServer, 0, 52);
		topPane.setCenter(innerPane);
		
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
