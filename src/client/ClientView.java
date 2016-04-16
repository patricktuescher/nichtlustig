package client;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientView {
	
	Stage primaryStage;
	ClientModel model;
	protected Button b_login, b_register, b_backLobby ,b_statistic, b_rules, b_spielErstellen, b_spielBeitreten, b_backStatistik, b_backRegeln, b_backGame ;
	protected Scene sceneLobby, sceneLogin, sceneGame, sceneStatistik, sceneRegeln;
	
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
		Label lb_username = new Label("User name: ");
		innerPaneLogin.add(lb_username, 2, 22);
		lb_username.setId("lb-username");
		
		//Label Password
		Label lb_password = new Label("Password: ");
		innerPaneLogin.add(lb_password, 2, 25);
		lb_password.setId("lb-password");
		
		//Textbox Username
		TextField tf_username = new TextField();
		innerPaneLogin.add(tf_username, 4, 22);
		tf_username.setId("pf-login");
		tf_username.setPromptText("user name");
		
		//Passwortbox Password
		PasswordField pf_password = new PasswordField();
		innerPaneLogin.add(pf_password, 4, 25);
		pf_password.setId("pf-login");
		pf_password.setPromptText("password");
		
		//Button Login
		b_login = new Button("Login");
		innerPaneLogin.add(b_login, 3, 51);
		b_login.setId("b-login");
		//b_login.setOnAction(handleButtonAction(e));
		
		//Button Register
		b_register = new Button("Register");
		innerPaneLogin.add(b_register, 6, 51);
		b_register.setId("b-login");
		
		//Scene Login
		sceneLogin = new Scene(topPaneLogin, 1200, 800);
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
		b_backLobby = new Button("Zurück");
		innerPaneLobby.add(b_backLobby, 80, 60);
		b_backLobby.setId("b-login");
		
		//Button Statistik
		b_statistic = new Button("Statistik");
		innerPaneLobby.add(b_statistic, 40, 30);
		b_statistic.setId("b-login");
		
		
		//Button Regeln 
		b_rules = new Button("Regeln");
		innerPaneLobby.add(b_rules, 60, 30);
		b_rules.setId("b-login");
		
		//Button Spiel erstellen 
		b_spielErstellen = new Button("Spiel erstellen");
		innerPaneLobby.add(b_spielErstellen, 20, 30);
		b_spielErstellen.setId("b-login");
		
		
		//Button Spiel beitreten 
		b_spielBeitreten = new Button("Spiel beitreten");
		innerPaneLobby.add(b_spielBeitreten, 40, 50);
		b_spielBeitreten.setId("b-login");
		
		
		//Scene Lobby
		sceneLobby = new Scene(topPaneLobby, 1200, 800);
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
		b_backStatistik = new Button("Zurück");
		innerPaneStatistik.add(b_backStatistik, 80, 60);
		b_backStatistik.setId("b-login");
				
		//Scene Lobby
		sceneStatistik = new Scene(topPaneStatistik, 1200, 800);
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
		b_backRegeln = new Button("Zurück");
		innerPaneRegeln.add(b_backRegeln, 80, 60);
		b_backRegeln.setId("b-login");
				
		//Scene Regeln
		sceneRegeln = new Scene(topPaneRules, 1200, 800);
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
		b_backGame = new Button("Zurück");
		innerPaneGame.add(b_backGame, 0, 0);
		b_backGame.setId("b-login");
				
		//Scene Regeln
		sceneGame = new Scene(topPaneGame, 1200, 800);
		sceneGame.getStylesheets().add("ClientStylesheet");
				
				
		//Textfield Punkte Player 1
		TextField scorePlayer1 = new TextField("12");
		
		///////////////// CARDS //////////////////////////
		
		//Height and Width of the cards
		int cardheight = 100;
		int cardwidth = 100;
		
		//Image Array
		int colIndex = 1;
		int rowIndex = 20;
				
		Image[] cardRieb = new Image[5];
		for (int i = 1; i < 6; i++){
				cardRieb[i-1] = new Image("images/Karte_Rieb_"+i+".png");
				ImageView viewtestcard = new ImageView();
				viewtestcard.setImage(cardRieb[i-1]);
				viewtestcard.setFitHeight(cardheight);
				viewtestcard.setFitWidth(cardwidth);
				innerPaneGame.add(viewtestcard, colIndex+i, rowIndex+1);
			}
				
		Image[] cardYeti = new Image[5];
		for (int i = 1; i < 6; i++){
				cardYeti[i-1] = new Image("images/Karte_Yeti_"+i+".png");
				ImageView viewtestcard1 = new ImageView();
				viewtestcard1.setImage(cardYeti[i-1]);
				viewtestcard1.setFitHeight(cardheight);
				viewtestcard1.setFitWidth(cardwidth);
					innerPaneGame.add(viewtestcard1, colIndex+i, rowIndex+2);
				}
				
		Image[] cardLemming = new Image[5];
		for (int i = 1; i < 6; i++){
				cardLemming[i-1] = new Image("images/Karte_Lemming_"+i+".png");
				ImageView viewtestcard = new ImageView();
				viewtestcard.setImage(cardLemming[i-1]);
				viewtestcard.setFitHeight(cardheight);
				viewtestcard.setFitWidth(cardwidth);
				innerPaneGame.add(viewtestcard, colIndex+i, rowIndex+3);
				}
				
		Image[] cardProf = new Image[5];
		for (int i = 1; i < 6; i++){
				cardProf[i-1] = new Image("images/Karte_Prof_"+i+".png");
				ImageView viewtestcard = new ImageView();
				viewtestcard.setImage(cardProf[i-1]);
				viewtestcard.setFitHeight(cardheight);
				viewtestcard.setFitWidth(cardwidth);
				innerPaneGame.add(viewtestcard, colIndex+i, rowIndex+4);
				}
				
		Image[] cardDino = new Image[5];
		for (int i = 1; i < 6; i++){
				cardDino[i-1] = new Image("images/Karte_Dino_"+i+".png");
				ImageView viewtestcard = new ImageView();
				viewtestcard.setImage(cardDino[i-1]);
				viewtestcard.setFitHeight(cardheight);
				viewtestcard.setFitWidth(cardwidth);
				innerPaneGame.add(viewtestcard, colIndex+i, rowIndex+5);
				}
		
		
	
		primaryStage.setTitle("Client-Applikation");
		primaryStage.setScene(sceneLogin);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("images/ClientIcon.png"));
		
		
		
	}
	
	public void start(){
		primaryStage.show();
	}}

