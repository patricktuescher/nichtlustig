package client;


import javafx.geometry.Insets;
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
	protected Button b_login, b_register, b_backLobby ,b_statistic, b_rules, b_spielErstellen, b_spielBeitreten, b_backStatistik, b_backRegeln, b_backGame, b_würfeln ;
	protected Label scorePlayer1, scorePlayer2;
	protected Scene sceneLobby, sceneLogin, sceneGame, sceneStatistik, sceneRegeln;
	
	//Height and Width of the cards
	final int cardheight = 90;
	final int cardwidth = 90;
	
	//Image Array
	protected final int colIndex = 13;
	protected final int rowIndex = 6;	
	protected ImageView[] cardRieb, cardYeti, cardLemming, cardProf, cardDino, cardTod;

	//Cube
	ImageView cubeViewPink, cubeViewWhite1, cubeViewWhite2, cubeViewBlack1, cubeViewBlack2, cubeViewRed1, cubeViewRed2;
	protected Image[] cubePink, cubeWhite1, cubeWhite2, cubeBlack1, cubeBlack2, cubeRed1, cubeRed2;	
	
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
		innerPaneLobby.setGridLinesVisible(true);
		topPaneLobby.setCenter(innerPaneLobby);
		
		
		//Button Spiel erstellen 
		b_spielErstellen = new Button("Spiel erstellen");
		b_spielErstellen.setPrefSize(300, 200);
		innerPaneLobby.add(b_spielErstellen, colIndex+1, rowIndex+1);
		b_spielErstellen.setId("b-login");
		
		
		//Button Statistik
		b_statistic = new Button("Statistik");
		b_statistic.setPrefSize(300, 200);
		innerPaneLobby.add(b_statistic, colIndex+2, rowIndex+1);
		b_statistic.setId("b-login");
	
		
		//Button Regeln 
		b_rules = new Button("Regeln");
		b_rules.setPrefSize(300, 200);
		innerPaneLobby.add(b_rules, colIndex+3, rowIndex+1);
		b_rules.setId("b-login");
		
		
		//Button Spiel beitreten 
		b_spielBeitreten = new Button("Spiel beitreten");
		b_spielBeitreten.setPrefSize(300, 200);
		innerPaneLobby.add(b_spielBeitreten, colIndex+2, rowIndex+2);
		b_spielBeitreten.setId("b-login");
		
		
		//Button Zurück
		b_backLobby = new Button("Zurück");
		b_backLobby.setPrefSize(300,200);
		innerPaneLobby.add(b_backLobby, colIndex+3, rowIndex+3);
		b_backLobby.setId("b-login");
				
				
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
		innerPaneStatistik.add(b_backStatistik, colIndex+0, rowIndex+0);
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
		
		//TopPane Game
		BorderPane topPaneGame = new BorderPane();
		topPaneGame.setId("topPaneGame"); //ID for CSS
		

		//GridPane Game Center
		GridPane innerPaneGame= new GridPane();
		innerPaneGame.setHgap(10);
		innerPaneGame.setVgap(10);
		topPaneGame.setCenter(innerPaneGame);		
		

		//Button Zurück
		b_backGame = new Button("Zurück");
		innerPaneGame.add(b_backGame, colIndex+18, rowIndex+15);
		b_backGame.setId("b-login");
				
		
		//Label Punkte Player 1		
		scorePlayer1 = new Label(null);		
		innerPaneGame.add(scorePlayer1, 2, 2);		
		
		//Label Punkte Player 2		
		scorePlayer2 = new Label(null);		
		innerPaneGame.add(scorePlayer2, 1, 5);		
				
		
		
		
		//Scene Regeln
		sceneGame = new Scene(topPaneGame, 1200, 800);
		sceneGame.getStylesheets().add("ClientStylesheet");
				
				

		
		///////////////// CARDS //////////////////////////
		
		cardRieb = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageRieb = new Image("images/Karte_Rieb_"+i+".png");
				cardRieb[i-1] = new ImageView();
				cardRieb[i-1].setImage(imageRieb);
				cardRieb[i-1].setFitHeight(cardheight);
				cardRieb[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardRieb[i-1], colIndex+i, rowIndex+1);
			}
				
		cardYeti = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageYeti = new Image("images/Karte_Yeti_"+i+".png");
				cardYeti[i-1] = new ImageView();
				cardYeti[i-1].setImage(imageYeti);
				cardYeti[i-1].setFitHeight(cardheight);
				cardYeti[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardYeti[i-1], colIndex+i, rowIndex+2);
				}
		
		cardLemming = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageLemming = new Image("images/Karte_Lemming_"+i+".png");
				cardLemming[i-1] = new ImageView();
				cardLemming[i-1].setImage(imageLemming);
				cardLemming[i-1].setFitHeight(cardheight);
				cardLemming[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardLemming[i-1], colIndex+i, rowIndex+3);
				}

		cardProf = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageYeti = new Image("images/Karte_Prof_"+i+".png");
				cardProf[i-1] = new ImageView();
				cardProf[i-1].setImage(imageYeti);
				cardProf[i-1].setFitHeight(cardheight);
				cardProf[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardProf[i-1], colIndex+i, rowIndex+4);
				}
		
		cardDino = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageDino = new Image("images/Karte_Dino_"+i+".png");
				cardDino[i-1] = new ImageView();
				cardDino[i-1].setImage(imageDino);
				cardDino[i-1].setFitHeight(cardheight);
				cardDino[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardDino[i-1], colIndex+i, rowIndex+5);
				}		
		
		cardTod = new ImageView[6];		
		for (int i = 1; i < 7; i++){		
				Image imageTod = new Image("images/Karte_Tod_"+i+".png");		
				cardTod[i-1] = new ImageView();		
				cardTod[i-1].setImage(imageTod);		
				cardTod[i-1].setFitHeight(cardheight);		
				cardTod[i-1].setFitWidth(cardwidth);		
				if(i<4){		
					innerPaneGame.add(cardTod[i-1], colIndex-2, rowIndex+i);		
				}		
				else{		
					innerPaneGame.add(cardTod[i-1], colIndex-1, rowIndex+i-3);
				}
		}
		
		
		////////////////// CUBES //////////////////////////
		
		
		// Cubes pink
		cubePink = new Image[6];
		for(int i = 1; i<7; i++){
			cubePink[i-1] = new Image("images/Pink_Würfel_"+i+".png");
			}
		int cPink = (int) (Math.random()*6);
		cubeViewPink = new ImageView();
		cubeViewPink.setImage(cubePink[cPink]);
		cubeViewPink.setScaleX(0.2);
		cubeViewPink.setScaleY(0.2);
		innerPaneGame.add(cubeViewPink, colIndex, rowIndex+8);
		
		
		// Cubes white1
		cubeWhite1 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeWhite1[i-1] = new Image("images/Weiss_Würfel_"+i+".png");
			}
		int cWhite1 = (int) (Math.random()*5);
		cubeViewWhite1 = new ImageView();
		cubeViewWhite1.setImage(cubeWhite1[cWhite1]);
		cubeViewWhite1.setScaleX(0.2);
		cubeViewWhite1.setScaleY(0.2);
		innerPaneGame.add(cubeViewWhite1, colIndex-1, rowIndex+8);
		
		// Cubes white2
		cubeWhite2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeWhite2[i-1] = new Image("images/Weiss_Würfel_"+i+".png");
			}
		int cWhite2 = (int) (Math.random()*5);
		cubeViewWhite2 = new ImageView();
		cubeViewWhite2.setImage(cubeWhite2[cWhite2]);
		cubeViewWhite2.setScaleX(0.2);
		cubeViewWhite2.setScaleY(0.2);
		innerPaneGame.add(cubeViewWhite2, colIndex-2, rowIndex+8);
		
		// Cubes black1
		/*cubeBlack1 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeBlack1[i-1] = new Image("images/Schwarz_Würfel_"+i+".png");
			}
		int cBlack1 = (int) (Math.random()*5);
		cubeViewBlack1 = new ImageView();
		cubeViewBlack1.setImage(cubeBlack1[cBlack1]);
		cubeViewBlack1.setScaleX(0.2);
		cubeViewBlack1.setScaleY(0.2);
		innerPaneGame.add(cubeViewBlack1, colIndex-1, rowIndex+8);*/
		
		// Cubes black2
		/*cubeBlack2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeBlack2[i-1] = new Image("images/Schwarz_Würfel_"+i+".png");
			}
		int cBlack2 = (int) (Math.random()*5);
		cubeViewBlack2 = new ImageView();
		cubeViewBlack2.setImage(cubeBlack2[cBlack2]);
		cubeViewBlack2.setScaleX(0.2);
		cubeViewBlack2.setScaleY(0.2);
		innerPaneGame.add(cubeViewBlack2, colIndex-1, rowIndex+8);*/
		
		
		// Cubes red1
		/*cubeRed1 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeRed1[i-1] = new Image("images/Rot_Würfel_"+i+".png");
			}
		int cRed1 = (int) (Math.random()*5);
		cubeViewRed1 = new ImageView();
		cubeViewRed1.setImage(cubeRed1[cRed1]);
		cubeViewRed1.setScaleX(0.2);
		cubeViewRed1.setScaleY(0.2);
		innerPaneGame.add(cubeViewRed1, colIndex-1, rowIndex+8);*/
		
		
		// Cubes red2
		/*cubeRed2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeRed2[i-1] = new Image("images/Rot_Würfel_"+i+".png");
			}
		int cRed2 = (int) (Math.random()*5);
		cubeViewRed2 = new ImageView();
		cubeViewRed2.setImage(cubeRed2[cRed2]);
		cubeViewRed2.setScaleX(0.2);
		cubeViewRed2.setScaleY(0.2);
		innerPaneGame.add(cubeViewRed2, colIndex-1, rowIndex+8);*/
	
		
		
		
		//Button würfeln
		b_würfeln = new Button("Würfeln");
		innerPaneGame.add(b_würfeln, colIndex-2, rowIndex+8);
		b_würfeln.setId("b-login");
		
		
		
		//////////////// Primary Stage ////////////////////////
		
		primaryStage.setTitle("Client-Applikation");
		primaryStage.setScene(sceneLogin);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("images/ClientIcon.png"));
		
		
	}
	
	public void start(){
		primaryStage.show();
	}}

