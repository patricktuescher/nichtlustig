package client;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
		///////////////// LOGIN FENSTER //////////////////////////
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
		tf_username.setId("pf-login");
		tf_username.setText("Benutzername");
		
		//Passwortbox Password
		PasswordField pf_password = new PasswordField();
		innerPane.add(pf_password, 3, 48);
		pf_password.setId("pf-login");
		pf_password.setText("Passwort");
		
		//Button Login
		Button b_login = new Button("Login");
		innerPane.add(b_login, 3, 51);
		b_login.setId("b-login");
		//b_login.setOnAction(handleButtonAction(e));
		
		//Scene Login
		Scene scene = new Scene(topPane, 1200, 800);
		scene.getStylesheets().add("ClientStylesheet");
		
		
		///////////////// LOBBY FENSTER //////////////////////////
		
		//TopPane Lobby
		BorderPane topPane1 = new BorderPane();
		topPane1.setId("topPane1"); //ID for CSS
		
		//GridPane Lobby
		GridPane innerPane1 = new GridPane();
		innerPane1.setHgap(10);
		innerPane1.setVgap(10);
		topPane1.setCenter(innerPane1);
		
		
		//Button Zurück
		Button b_back = new Button("Zurück");
		innerPane1.add(b_back, 80, 60);
		b_back.setId("b-login");
		
		//Button Statistik
		Button b_statistic = new Button("Statistik");
		innerPane1.add(b_statistic, 40, 30);
		b_statistic.setId("b-login");
		
		
		//Button Regeln 
		Button b_rules = new Button("Regeln");
		innerPane1.add(b_rules, 60, 30);
		b_rules.setId("b-login");
		
		//Button Spiel erstellen 
		Button b_spielErstellen = new Button("Spiel erstellen");
		innerPane1.add(b_spielErstellen, 20, 30);
		b_spielErstellen.setId("b-login");
		
		
		//Button Spiel beitreten 
		Button b_spielBeitreten = new Button("Spiel beitreten");
		innerPane1.add(b_spielBeitreten, 40, 50);
		b_spielBeitreten.setId("b-login");
		
		
		//Scene Lobby
		Scene scene1 = new Scene(topPane1, 1200, 800);
		scene1.getStylesheets().add("ClientStylesheet");
		
		///////////////// STATISTIK FENSTER //////////////////////////
		
		//TopPane Statistik
		BorderPane topPaneStatistik = new BorderPane();
		topPaneStatistik.setId("topPaneStatistik"); //ID for CSS
		
		//GridPane Statistik
		GridPane innerPaneStatistik = new GridPane();
		innerPaneStatistik.setHgap(10);
		innerPaneStatistik.setVgap(10);
		topPaneStatistik.setCenter(innerPaneStatistik);
		
		//Button Zurück
		Button b_backStatistik = new Button("Zurück");
		innerPaneStatistik.add(b_backStatistik, 80, 60);
		b_backStatistik.setId("b-login");
				
		//Scene Lobby
		Scene sceneStatistik = new Scene(topPaneStatistik, 1200, 800);
		sceneStatistik.getStylesheets().add("ClientStylesheet");
		
		
		///////////////// REGELN FENSTER //////////////////////////
		
		//TopPane Regeln
		BorderPane topPaneRules = new BorderPane();
		topPaneRules.setId("topPaneRules"); //ID for CSS

		//GridPane Regeln
		GridPane innerPaneRegeln= new GridPane();
		innerPaneRegeln.setHgap(10);
		innerPaneRegeln.setVgap(10);
		topPaneRules.setCenter(innerPaneRegeln);

		//Button Zurück
		Button b_backRegeln = new Button("Zurück");
		innerPaneRegeln.add(b_backRegeln, 80, 60);
		b_backRegeln.setId("b-login");
				
		//Scene Regeln
		Scene sceneRegeln = new Scene(topPaneRules, 1200, 800);
		sceneRegeln.getStylesheets().add("ClientStylesheet");
				
				
		
	
		primaryStage.setTitle("Client-Applikation");
		primaryStage.setScene(sceneRegeln);
		primaryStage.setResizable(false);
		
		
		
	}
	
	public void start(){
		primaryStage.show();
	}}

