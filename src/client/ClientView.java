package client;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ClientView {
	
	Stage primaryStage;
	ClientModel model;
	protected Button b_login, b_register,b_quitGame, b_backLobby ,b_statistic, b_rules, b_spielErstellen, b_spielBeitreten, b_backStatistik, b_backRegeln,b_nextImage, b_previousImage, b_backGame, b_würfeln ;
	protected Label labelPL1, labelPL2;
	protected PasswordField pf_password;
	protected Scene sceneLobby, sceneLogin, sceneGame, sceneStatistik, sceneRegeln;
	protected CheckBox passwordCheck;
	
	//Height and Width of the cards
	final int cardheight = 90;
	final int cardwidth = 90;
	
	//Image Array
	protected final int colIndex = 13;
	protected final int rowIndex = 6;	
	protected ImageView[] cardRieb, cardYeti, cardLemming, cardProf, cardDino, cardTod;

	//Cubes 
	ImageView cubeViewPink, cubeViewWhite1, cubeViewWhite2, cubeViewBlack1, cubeViewBlack2, cubeViewRed1, cubeViewRed2;
	protected Image[] cubePink, cubeWhite1, cubeWhite2, cubeBlack1, cubeBlack2, cubeRed1, cubeRed2;	
	
	//Cubes PL2
	ImageView cubeViewPinkPL2, cubeViewWhite1PL2, cubeViewWhite2PL2, cubeViewBlack1PL2, cubeViewBlack2PL2, cubeViewRed1PL2, cubeViewRed2PL2;
	protected Image[] cubePinkPL2, cubeWhite1PL2, cubeWhite2PL2, cubeBlack1PL2, cubeBlack2PL2, cubeRed1PL2, cubeRed2PL2;	
	
	
	//Rules
	ImageView regelnView;
	protected Image[] regeln;
	protected static  int currentRuleImage = 0;

	
	//Height and Width of cubes
	final int cubeheight = 60;
	final int cubewidth = 60;
	
	
	public ClientView(Stage primaryStage, ClientModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		
		
		
		
		///////////////// LOGIN FENSTER //////////////////////////
		
		
		
		//InnerPane Login
		GridPane innerPaneLogin = new GridPane();
		innerPaneLogin.setGridLinesVisible(false);
		innerPaneLogin.setHgap(10);
		innerPaneLogin.setVgap(10);
		innerPaneLogin.setPadding(new Insets(200,200,0,280));
		
		//Label Username
		Label lb_username = new Label("User name: ");
		innerPaneLogin.add(lb_username, 2, 1);
		lb_username.setId("lb-username");
		
		//Label Password
		Label lb_password = new Label("Password: ");
		innerPaneLogin.add(lb_password, 2, 4);
		lb_password.setId("lb-password");
		
		//Textbox Username
		TextField tf_username = new TextField();
		innerPaneLogin.add(tf_username, 2, 2);
		tf_username.setId("pf-login");
		tf_username.setPromptText("user name");
		
		//Passwortbox Password
		PasswordField pf_password = new PasswordField();
		innerPaneLogin.add(pf_password, 2, 5);
		pf_password.setId("pf-login");
		pf_password.setPromptText("password");
		
		// checkbox password visibility
		passwordCheck = new CheckBox("password visible"); 	
		innerPaneLogin.add(passwordCheck, 2, 6);

		
		//Button Login
		b_login = new Button("Login");
		innerPaneLogin.add(b_login, 1, 25);
		b_login.setPrefSize(200, 70);
		b_login.setId("b-login");
		
		//Button Register
		b_register = new Button("Register");
		innerPaneLogin.add(b_register, 2, 25);
		b_register.setPrefSize(200, 70);
		b_register.setId("b-login");
		
		//Button Quit game
		b_quitGame = new Button("Quit Game");
		innerPaneLogin.add(b_quitGame, 3, 25);
		b_quitGame.setPrefSize(200,70);
		b_quitGame.setId("b-login");
		
		//Scene Login
		sceneLogin = new Scene(innerPaneLogin, 1200, 800);
		sceneLogin.getStylesheets().add("ClientStylesheet");
		
		
		///////////////// LOBBY FENSTER //////////////////////////
		
		//TopPane Lobby
		BorderPane topPaneLobby = new BorderPane();
		topPaneLobby.setId("topPaneLobby"); //ID for CSS
		
		
		//GridPane Lobby
		GridPane innerPaneLobby = new GridPane();
		innerPaneLobby.setHgap(20);
		innerPaneLobby.setVgap(20);
		innerPaneLobby.setGridLinesVisible(false);
		innerPaneLobby.setPadding(new Insets(250,0,10,270));
		topPaneLobby.setCenter(innerPaneLobby);
		
		
		//Button Spiel erstellen 
		b_spielErstellen = new Button("Spiel erstellen");
		b_spielErstellen.setPrefSize(200, 70);
		innerPaneLobby.add(b_spielErstellen, 1, 2);
		b_spielErstellen.setId("b-login");
		
		
		//Button Statistik
		b_statistic = new Button("Statistik");
		b_statistic.setPrefSize(200,70);
		innerPaneLobby.add(b_statistic, 2, 2);
		b_statistic.setId("b-login");
	
		
		//Button Regeln 
		b_rules = new Button("Regeln");
		b_rules.setPrefSize(200,70);
		innerPaneLobby.add(b_rules, 3, 2);
		b_rules.setId("b-login");
		
		
		//Button Spiel beitreten 
		b_spielBeitreten = new Button("Spiel beitreten");
		b_spielBeitreten.setPrefSize(200,70);
		innerPaneLobby.add(b_spielBeitreten, 2, 9);
		b_spielBeitreten.setId("b-login");
		
		
		//Button Zurück
		b_backLobby = new Button("Zurück");
		b_backLobby.setPrefSize(200,70);
		innerPaneLobby.add(b_backLobby, 6, 9);
		b_backLobby.setId("b-login");
		
		//Label select game
		Label select_label = new Label("Spiel auswählen");
		select_label.setId("label");
		select_label.setPrefSize(200, 20);
		select_label.setAlignment(Pos.CENTER);
		innerPaneLobby.add(select_label, 2, 7);
		
		
		
	
		
		//ListView availabe games
		ListView<String> listLobby = new ListView<String>();
		ObservableList<String> itemsLobby =FXCollections.observableArrayList (
		 "bisch", "druffe", "?", "?");
		listLobby.setItems(itemsLobby);
		listLobby.setMinSize(100, 200);
		listLobby.setMaxSize(200, 200);
		listLobby.setId("gamelist");
		innerPaneLobby.add(listLobby, 2, 8);
		
		
		
				
				
		//Scene Lobby
		sceneLobby = new Scene(topPaneLobby, 1200, 800);
		sceneLobby.getStylesheets().add("ClientStylesheet");
		
		///////////////// STATISTIK FENSTER //////////////////////////
		
		//TopPane Statistik
		BorderPane topPaneStatistik = new BorderPane();
		topPaneStatistik.setId("topPaneStatistik"); //ID for CSS
		
		//HBox Statistik
		HBox centerPaneStatistik = new HBox();
		centerPaneStatistik.setAlignment(Pos.CENTER);
		centerPaneStatistik.setPadding(new Insets(50,0,0,0));
		topPaneStatistik.setCenter(centerPaneStatistik);
		
		//HBox Statistik Bottom
		HBox bottomPaneStatistik = new HBox();
		bottomPaneStatistik.setPadding(new Insets(0,0,20,0));
		bottomPaneStatistik.setAlignment(Pos.CENTER);
		topPaneStatistik.setBottom(bottomPaneStatistik);
		
		//TableView Statistik
		TableView table = new TableView();
	    table.setEditable(true);
	    TableColumn userNameCol = new TableColumn("user name");
	    TableColumn scoreCol = new TableColumn("score");
	    TableColumn dateCol = new TableColumn("date");
	    table.getColumns().addAll(userNameCol, scoreCol, dateCol);
	    table.setMaxSize(600, 400);
	    table.setMinSize(600, 400);
	    centerPaneStatistik.getChildren().add(table);
		
		//Button Zurück
		b_backStatistik = new Button("Zurück");
		bottomPaneStatistik.getChildren().add(b_backStatistik);
		b_backStatistik.setPrefSize(200, 70);
		b_backStatistik.setId("b-login");
				
		//Scene Lobby
		sceneStatistik = new Scene(topPaneStatistik, 1200, 800);
		sceneStatistik.getStylesheets().add("ClientStylesheet");
		
		
		///////////////// REGELN FENSTER //////////////////////////
		
		//BorderPane Regeln
		BorderPane topPaneRegeln = new BorderPane();
		topPaneRegeln.setId("topPaneRules"); //ID for CS

		//HBox Regeln Center
		HBox innerPaneRegeln= new HBox();
		innerPaneRegeln.setPadding(new Insets(100,0,0,0));
		topPaneRegeln.setCenter(innerPaneRegeln);
		
		//HBox Regeln Bottom
		HBox bottomPaneRegeln = new HBox();
		bottomPaneRegeln.setPadding(new Insets(0,0,20,0));
		bottomPaneRegeln.setAlignment(Pos.CENTER);
		topPaneRegeln.setBottom(bottomPaneRegeln);
		
		

		//Button Zurück
		b_backRegeln = new Button("Zurück");
		b_backRegeln.setPrefSize(200, 70);
		bottomPaneRegeln.getChildren().add(b_backRegeln);
		b_backRegeln.setId("b-login");
		
		
		//Regeln image[] //// image position in reglen array to be defined; currently set to 0 for testing
		regeln = new Image[6];
		for(int i = 1; i<7; i++)
		{
		regeln[i-1] = new Image("images/nicht_lustig_Regeln_"+i+".jpg");
		}
		regelnView = new ImageView();
		regelnView.setImage(regeln[currentRuleImage]);
		innerPaneRegeln.setAlignment(Pos.CENTER);
		regelnView.setFitHeight(500);
		regelnView.setFitWidth(400);

					
		// Button next
		b_nextImage = new Button(">");
		b_nextImage.setPrefSize(20,20);
		b_nextImage.setId("b-login");
	
		
		// Button previous
		b_previousImage = new Button("<");
		b_previousImage.setPrefSize(20,20);
		b_previousImage.setId("b-login");
		
		// added nodes to inner pane regeln
		innerPaneRegeln.getChildren().addAll(b_previousImage, regelnView,b_nextImage);
		innerPaneRegeln.setSpacing(10);
		
				
		//Scene Regeln
		sceneRegeln = new Scene(topPaneRegeln, 1200, 800);
		sceneRegeln.getStylesheets().add("ClientStylesheet");
		
		///////////////// GAME FENSTER //////////////////////////
		
		//TopPane Game
		BorderPane topPaneGame = new BorderPane();
		topPaneGame.setId("topPaneGame"); //ID for CSS
		

		//HBox Game Top
		HBox innertopPaneGame = new HBox();
		innertopPaneGame.setPadding(new Insets(20,0,0,350));
		innertopPaneGame.setMinHeight(100);
		innertopPaneGame.setMaxHeight(100);
		innertopPaneGame.setSpacing(15);
		topPaneGame.setTop(innertopPaneGame);
		
		//BorderPane Game Bottom
		BorderPane bottomPaneGame = new BorderPane();
		topPaneGame.setBottom(bottomPaneGame);
		
		
		
		//HBox Game Bottom Cube
		HBox bottomPaneGameCube = new HBox();
		bottomPaneGameCube.setPadding(new Insets(20,0,20,200));
		bottomPaneGameCube.setSpacing(15);
		bottomPaneGame.setTop(bottomPaneGameCube);

		
		//HBox Game Bottom Chat
		VBox bottomPaneGameChat = new VBox();
		bottomPaneGameChat.setPadding(new Insets(0,0,10,200));
		bottomPaneGameChat.setMinHeight(100);
		bottomPaneGame.setBottom(bottomPaneGameChat);
		
		
		
		//GridPane Game Center
		GridPane innerPaneGame= new GridPane();
		innerPaneGame.setHgap(5);
		innerPaneGame.setVgap(5);
		innerPaneGame.setPadding(new Insets(0, 0, 15, 120));
		innerPaneGame.setGridLinesVisible(false);
		topPaneGame.setCenter(innerPaneGame);	
		
		//GridPane Game Left
		GridPane leftPaneGame = new GridPane();
		leftPaneGame.setHgap(5);
		leftPaneGame.setVgap(5);
		leftPaneGame.setPadding(new Insets(105, 0, 15, 100));
		leftPaneGame.setGridLinesVisible(false);
		topPaneGame.setLeft(leftPaneGame);
		

		//Button Zurück
		b_backGame = new Button("Zurück");
		b_backGame.setId("b-login");
		
		
		// Label Score PL1 - to be difined
		labelPL1 = new Label("");
				
		// Label Score PL2 - to be difined
		labelPL2 = new Label("");
			
	
		
		//Scene Game
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
				innerPaneGame.add(cardRieb[i-1], i, 2);
			}
				
		cardYeti = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageYeti = new Image("images/Karte_Yeti_"+i+".png");
				cardYeti[i-1] = new ImageView();
				cardYeti[i-1].setImage(imageYeti);
				cardYeti[i-1].setFitHeight(cardheight);
				cardYeti[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardYeti[i-1], i, 4);
				}
		
		cardLemming = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageLemming = new Image("images/Karte_Lemming_"+i+".png");
				cardLemming[i-1] = new ImageView();
				cardLemming[i-1].setImage(imageLemming);
				cardLemming[i-1].setFitHeight(cardheight);
				cardLemming[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardLemming[i-1], i, 6);
				}

		cardProf = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageYeti = new Image("images/Karte_Prof_"+i+".png");
				cardProf[i-1] = new ImageView();
				cardProf[i-1].setImage(imageYeti);
				cardProf[i-1].setFitHeight(cardheight);
				cardProf[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardProf[i-1], i, 8);
				}
		
		cardDino = new ImageView[5];
		for (int i = 1; i < 6; i++){
				Image imageDino = new Image("images/Karte_Dino_"+i+".png");
				cardDino[i-1] = new ImageView();
				cardDino[i-1].setImage(imageDino);
				cardDino[i-1].setFitHeight(cardheight);
				cardDino[i-1].setFitWidth(cardwidth);
				innerPaneGame.add(cardDino[i-1], i, 10);
				}		
		
		cardTod = new ImageView[6];		
		for (int i = 1; i < 7; i++){		
				Image imageTod = new Image("images/Karte_Tod_"+i+".png");		
				cardTod[i-1] = new ImageView();		
				cardTod[i-1].setImage(imageTod);		
				cardTod[i-1].setFitHeight(cardheight);		
				cardTod[i-1].setFitWidth(cardwidth);		
				if(i<4){		
					leftPaneGame.add(cardTod[i-1], 2, 2*i);		
				}		
				else{		
					leftPaneGame.add(cardTod[i-1], 4, 2*(i-3)); 
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
		cubeViewPink.setFitHeight(cubeheight);
		cubeViewPink.setFitWidth(cubewidth);
		
		// Cubes white1
		cubeWhite1 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeWhite1[i-1] = new Image("images/Weiss_Würfel_"+i+".png");
			}
		int cWhite1 = (int) (Math.random()*5);
		cubeViewWhite1 = new ImageView();
		cubeViewWhite1.setImage(cubeWhite1[cWhite1]);
		cubeViewWhite1.setFitHeight(cubeheight);
		cubeViewWhite1.setFitWidth(cubewidth);
		
		// Cubes white2
		cubeWhite2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeWhite2[i-1] = new Image("images/Weiss_Würfel_"+i+".png");
			}
		int cWhite2 = (int) (Math.random()*5);
		cubeViewWhite2 = new ImageView();
		cubeViewWhite2.setImage(cubeWhite2[cWhite2]);
		cubeViewWhite2.setFitHeight(cubeheight);
		cubeViewWhite2.setFitWidth(cubewidth);
		
		// Cubes black1
		cubeBlack1 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeBlack1[i-1] = new Image("images/Schwarz_Würfel_"+i+".png");
			}
		int cBlack1 = (int) (Math.random()*5);
		cubeViewBlack1 = new ImageView();
		cubeViewBlack1.setImage(cubeBlack1[cBlack1]);
		cubeViewBlack1.setFitHeight(cubeheight);
		cubeViewBlack1.setFitWidth(cubewidth);
		
		// Cubes black2
		cubeBlack2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeBlack2[i-1] = new Image("images/Schwarz_Würfel_"+i+".png");
			}
		int cBlack2 = (int) (Math.random()*5);
		cubeViewBlack2 = new ImageView();
		cubeViewBlack2.setImage(cubeBlack2[cBlack2]);
		cubeViewBlack2.setFitHeight(cubeheight);
		cubeViewBlack2.setFitWidth(cubewidth);
		
		
		//Cubes red1
		cubeRed1 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeRed1[i-1] = new Image("images/Rot_Würfel_"+i+".png");
			}
		int cRed1 = (int) (Math.random()*5);
		cubeViewRed1 = new ImageView();
		cubeViewRed1.setImage(cubeRed1[cRed1]);
		cubeViewRed1.setFitHeight(cubeheight);
		cubeViewRed1.setFitWidth(cubewidth);
		
		
		
		// Cubes red2
		cubeRed2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeRed2[i-1] = new Image("images/Rot_Würfel_"+i+".png");
			}
		int cRed2 = (int) (Math.random()*5);
		cubeViewRed2 = new ImageView();
		cubeViewRed2.setImage(cubeRed2[cRed2]);
		cubeViewRed2.setFitHeight(cubeheight);
		cubeViewRed2.setFitWidth(cubewidth);
	
		
		
		
		//Button würfeln
		b_würfeln = new Button("Würfeln");
		b_würfeln.setId("b-login");
		
		//added nodes to BottomPane
		bottomPaneGameCube.getChildren().addAll(b_würfeln,cubeViewPink,cubeViewWhite1,cubeViewWhite2,cubeViewBlack1,cubeViewBlack2,cubeViewRed1,cubeViewRed2,b_backGame);
		
		
		//////////////////CUBES PL2 (SecondPlayer) //////////////////////////
		
		
		// Cubes pink PL2
		cubePinkPL2 = new Image[6];
		for(int i = 1; i<7; i++){
			cubePinkPL2[i-1] = new Image("images/Pink_Würfel_"+i+".png");
			}
		int cPinkPL2 = (int) (Math.random()*6);
		cubeViewPinkPL2 = new ImageView();
		cubeViewPinkPL2.setImage(cubePink[cPinkPL2]);
		cubeViewPinkPL2.setFitHeight(cubeheight);
		cubeViewPinkPL2.setFitWidth(cubewidth);
		
		// Cubes white1PL2
		cubeWhite1PL2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeWhite1PL2[i-1] = new Image("images/Weiss_Würfel_"+i+".png");
			}
		int cWhite1PL2 = (int) (Math.random()*5);
		cubeViewWhite1PL2 = new ImageView();
		cubeViewWhite1PL2.setImage(cubeWhite1[cWhite1PL2]);
		cubeViewWhite1PL2.setFitHeight(cubeheight);
		cubeViewWhite1PL2.setFitWidth(cubewidth);
		
		// Cubes white2 PL2
		cubeWhite2PL2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeWhite2PL2[i-1] = new Image("images/Weiss_Würfel_"+i+".png");
			}
		int cWhite2PL2 = (int) (Math.random()*5);
		cubeViewWhite2PL2 = new ImageView();
		cubeViewWhite2PL2.setImage(cubeWhite2[cWhite2PL2]);
		cubeViewWhite2PL2.setFitHeight(cubeheight);
		cubeViewWhite2PL2.setFitWidth(cubewidth);
		
		// Cubes black1 PL2
		cubeBlack1PL2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeBlack1PL2[i-1] = new Image("images/Schwarz_Würfel_"+i+".png");
			}
		int cBlack1PL2 = (int) (Math.random()*5);
		cubeViewBlack1PL2 = new ImageView();
		cubeViewBlack1PL2.setImage(cubeBlack1[cBlack1PL2]);
		cubeViewBlack1PL2.setFitHeight(cubeheight);
		cubeViewBlack1PL2.setFitWidth(cubewidth);
		
		// Cubes black2 PL2
		cubeBlack2PL2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeBlack2PL2[i-1] = new Image("images/Schwarz_Würfel_"+i+".png");
			}
		int cBlack2PL2 = (int) (Math.random()*5);
		cubeViewBlack2PL2 = new ImageView();
		cubeViewBlack2PL2.setImage(cubeBlack2[cBlack2PL2]);
		cubeViewBlack2PL2.setFitHeight(cubeheight);
		cubeViewBlack2PL2.setFitWidth(cubewidth);
		
		
		//Cubes red1 PL2
		cubeRed1PL2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeRed1PL2[i-1] = new Image("images/Rot_Würfel_"+i+".png");
			}
		int cRed1PL2 = (int) (Math.random()*5);
		cubeViewRed1PL2 = new ImageView();
		cubeViewRed1PL2.setImage(cubeRed1[cRed1PL2]);
		cubeViewRed1PL2.setFitHeight(cubeheight);
		cubeViewRed1PL2.setFitWidth(cubewidth);

		// Cubes red2
		cubeRed2PL2 = new Image[5];
		for(int i = 1; i<6; i++){
			cubeRed2PL2[i-1] = new Image("images/Rot_Würfel_"+i+".png");
			}
		int cRed2PL2 = (int) (Math.random()*5);
		cubeViewRed2PL2 = new ImageView();
		cubeViewRed2PL2.setImage(cubeRed2[cRed2PL2]);
		cubeViewRed2PL2.setFitHeight(cubeheight);
		cubeViewRed2PL2.setFitWidth(cubewidth);
		
		//added nodes to innertopPaneGame
		innertopPaneGame.getChildren().addAll(cubeViewPinkPL2, cubeViewWhite1PL2, cubeViewWhite2PL2, cubeViewBlack1PL2, cubeViewBlack2PL2, cubeViewRed1PL2, cubeViewRed2PL2);
				
		//Chat
		
		TextArea chatWindow = new TextArea();
		chatWindow.setMinSize(200, 50);
		chatWindow.setMaxSize(800, 50);
		chatWindow.setText("Hier könnte Ihre Chat-Nachricht stehen");
		
		
		//VBox Chat Input and Send
		
		HBox ChatInput = new HBox();
		
		TextField chatInputWindow = new TextField();
		chatInputWindow.setText("Schreibe hier deine Nachricht...");
		chatInputWindow.setMaxSize(700, 30);
		chatInputWindow.setMinSize(700, 30);
		
		Button sendButton = new Button();
		sendButton.setText("senden");
		sendButton.setMinSize(100, 30);
		sendButton.setMaxSize(100, 30);
		sendButton.setId("b-sendchat");
		
		ChatInput.getChildren().addAll(chatInputWindow, sendButton);
		
		
		bottomPaneGameChat.getChildren().addAll(chatWindow, ChatInput);
		
		
		//////////////// Primary Stage ////////////////////////
		
		primaryStage.setTitle("Client-Applikation");
		primaryStage.setScene(sceneLogin);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("images/ClientIcon.png"));
		
		
	}
	
	public void start(){
		primaryStage.show();
	}}