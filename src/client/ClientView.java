package client;


import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane; 
import javafx.stage.Stage;

public class ClientView {
	
	Stage primaryStage;
	ClientModel model;
	
	public ClientView(Stage primaryStage, ClientModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		

		
		BorderPane topPane = new BorderPane();
		Image image = new Image("/images/NichtLustig_Logo.jpg");
		ImageView logo = new ImageView(image);
		topPane.setCenter(new Label("Hello World"));
		topPane.setTop(logo);
		
		Scene scene = new Scene(topPane);
		primaryStage.setScene(scene);
	}
	
	public void start(){
		primaryStage.show();
	}}

