package client;

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
		
		//Pane 
		BorderPane topPane = new BorderPane();
		Image image = new Image("/images/NichtLustig_Logo.jpg");
		ImageView logo = new ImageView(image);
		topPane.setTop(logo);
		
		//Username Label
		
		
		
		
		//Scene
		Scene scene = new Scene(topPane, 600, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	}
	
	public void start(){
		primaryStage.show();
	}}

