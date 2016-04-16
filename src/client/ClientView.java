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
		//TopPane  Login
		BorderPane topPaneLogin = new BorderPane();
		topPaneLogin.setId("topPaneLogin"); //ID for CSS
		
		
		
		//InnerPane Login
		GridPane innerPaneLogin = new GridPane();
		innerPaneLogin.setAlignment(Pos.TOP_CENTER);
		innerPaneLogin.setHgap(10);
		innerPaneLogin.setVgap(10);
		topPaneLogin.setCenter(innerPaneLogin);
		
		//Label Username
		Label lb_username = new Label("Username: ");
		innerPaneLogin.add(lb_username, 0, 45);
		lb_username.setId("lb-username");
		
		//Label Password
		Label lb_password = new Label("Passwort: ");
		innerPaneLogin.add(lb_password, 0, 48);
		lb_password.setId("lb-password");
		
		//Textbox Username
		TextField tf_username = new TextField();
		innerPaneLogin.add(tf_username, 3, 45);
		tf_username.setId("pf-login");
		tf_username.setText("Benutzername");
		
		//Passwortbox Password
		PasswordField pf_password = new PasswordField();
		innerPaneLogin.add(pf_password, 3, 48);
		pf_password.setId("pf-login");
		pf_password.setText("Passwort");
		
		//Button Login
		Button b_login = new Button("Login");
		innerPaneLogin.add(b_login, 3, 51);
		b_login.setId("b-login");
		//b_login.setOnAction(handleButtonAction(e));
		
		//Scene Login
		Scene sceneLogin = new Scene(topPaneLogin, 1200, 800);
		sceneLogin.getStylesheets().add("ClientStylesheet");
		
		
		///////////////// LOBBY FENSTER //////////////////////////
		
		//TopPane Lobby
		BorderPane topPaneLobby = new BorderPane();
		topPaneLobby.setId("topPaneLobby"); //ID for CSS
		
		//GridPane Lobby
		GridPane innerPaneLobby = new GridPane();
		innerPaneLobby.setHgap(10);
		innerPaneLobby.setVgap(10);
		topPaneLobby.setCenter(innerPaneLobby);
		
		
		//Button Zurück
		Button b_back = new Button("Zurück");
		innerPaneLobby.add(b_back, 80, 60);
		b_back.setId("b-login");
		
		//Button Statistik
		Button b_statistic = new Button("Statistik");
		innerPaneLobby.add(b_statistic, 40, 30);
		b_statistic.setId("b-login");
		
		
		//Button Regeln 
		Button b_rules = new Button("Regeln");
		innerPaneLobby.add(b_rules, 60, 30);
		b_rules.setId("b-login");
		
		//Button Spiel erstellen 
		Button b_spielErstellen = new Button("Spiel erstellen");
		innerPaneLobby.add(b_spielErstellen, 20, 30);
		b_spielErstellen.setId("b-login");
		
		
		//Button Spiel beitreten 
		Button b_spielBeitreten = new Button("Spiel beitreten");
		innerPaneLobby.add(b_spielBeitreten, 40, 50);
		b_spielBeitreten.setId("b-login");
		
		
		//Scene Lobby
		Scene sceneLobby = new Scene(topPaneLobby, 1200, 800);
		sceneLobby.getStylesheets().add("ClientStylesheet");
		
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
		
		///////////////// GAME FENSTER //////////////////////////
		
		//TopPane Regeln
		BorderPane topPaneGame = new BorderPane();
		topPaneGame.setId("topPaneGame"); //ID for CSS

		//GridPane Regeln
		GridPane innerPaneGame= new GridPane();
		innerPaneGame.setHgap(10);
		innerPaneGame.setVgap(10);
		topPaneGame.setCenter(innerPaneGame);

		//Button Zurück
		Button b_backGame = new Button("Zurück");
		innerPaneGame.add(b_backGame, 80, 60);
		b_backGame.setId("b-login");
				
		//Scene Regeln
		Scene sceneGame = new Scene(topPaneGame, 1200, 800);
		sceneGame.getStylesheets().add("ClientStylesheet");
				
				
		
		
		
	
		primaryStage.setTitle("Client-Applikation");
		primaryStage.setScene(sceneGame);
		primaryStage.setResizable(false);
		
		
		
	}
	
	public void start(){
		primaryStage.show();
	}}

