package client;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientView {
	
	Stage primaryStage;
	ClientModel model;
	
	public ClientView(Stage primaryStage, ClientModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		
		//TopPane 
		BorderPane topPane = new BorderPane();
		
		//InnerPane
		GridPane innerPane = new GridPane();
		innerPane.setAlignment(Pos.TOP_CENTER);
		innerPane.setHgap(10);
		innerPane.setVgap(10);
		topPane.setCenter(innerPane);
		
		//Label Username
		Label lb_username = new Label("Username: ");
		innerPane.add(lb_username, 0, 45);
		lb_username.setId("lb-username");
		
		//Label Password
		Label lb_password = new Label("Passwort: ");
		innerPane.add(lb_password, 0, 48);
		lb_password.setId("lb-password");
		
		//Textbox Username
		TextField tf_username = new TextField();
		innerPane.add(tf_username, 3, 45);
		tf_username.setId("tf-username");
		
		//Textbox Password
		TextField tf_password = new TextField();
		innerPane.add(tf_password, 3, 48);
		tf_password.setId("tf-password");
		
		//Button Login
		Button b_login = new Button("Login");
		innerPane.add(b_login, 3, 51);
		b_login.setId("b-login");
		
		//Scene
		Scene scene = new Scene(topPane, 1200, 800);
		scene.getStylesheets().add("ClientStylesheet");
		primaryStage.setTitle("Client-Applikation");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	}
	
	public void start(){
		primaryStage.show();
	}}

