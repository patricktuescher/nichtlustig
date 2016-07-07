package client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

import CommonClasses.Translator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientView {
	
	Stage primaryStage;
	ClientModel model;
	protected Button b_login, b_register,b_quitGame, b_backLobby ,b_statistic, b_rules, b_spielErstellen, b_spielBeitreten, b_backStatistik, b_backRegeln,b_nextImage, b_previousImage, b_backGame, b_würfeln, b_sendchat ;
	protected Label labelPL1, labelPL2;
	protected PasswordField pf_password;
	protected Scene sceneLobby, sceneLogin, sceneGame, sceneStatistik, sceneRegeln;
	protected CheckBox passwordCheck;
	protected ServiceLocator sl;
	protected Translator t;
	
	//Height and Width of the cards
	final int cardheight = 90;
	final int cardwidth = 90;
	
	//Image Array
	protected final int colIndex = 13;
	protected final int rowIndex = 6;	
	protected ImageView[] cardRieb, cardYeti, cardLemming, cardProf, cardDino, cardTod;

	//Cubes PL1
	ArrayList <Würfel> WürfelPL1;
	Würfel cubeViewPink,cubeViewWhite1, cubeViewWhite2, cubeViewBlack1, cubeViewBlack2, cubeViewRed1, cubeViewRed2;
	
	//Cubes PL2
	ArrayList <Würfel> WürfelPL2;
	Würfel cubeViewPinkPL2, cubeViewWhite1PL2, cubeViewWhite2PL2, cubeViewBlack1PL2, cubeViewBlack2PL2, cubeViewRed1PL2, cubeViewRed2PL2;
	
	//Chat
	protected int port = 1201;
	protected BufferedReader streamIn;
	protected TextArea chatWindow;
	protected PrintStream streamOut;
	protected TextField chatInputWindow;
	
	
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
		sl = ServiceLocator.getServiceLocator();
		t = sl.getTranslator();
		//System.out.println(model.browse("127.0.0.1", 8080, new Integer(3)));
		
		
		
		///////////////// LOGIN FENSTER //////////////////////////
		
		//InnerPane Login
		GridPane innerPaneLogin = new GridPane();
		innerPaneLogin.setGridLinesVisible(false);
		innerPaneLogin.setHgap(10);
		innerPaneLogin.setVgap(10);
		innerPaneLogin.setPadding(new Insets(200,200,0,280));
		
		//Label Username
		Label lb_username = new Label(t.getString("Label.UserName")+ ": ");
		innerPaneLogin.add(lb_username, 2, 1);
		lb_username.setId("lb-username");
		
		//Label Password
		Label lb_password = new Label(t.getString("Label.Password")+": ");
		innerPaneLogin.add(lb_password, 2, 4);
		lb_password.setId("lb-password");
		
		//Textbox Username
		TextField tf_username = new TextField();
		innerPaneLogin.add(tf_username, 2, 2);
		tf_username.setId("pf-login");
		tf_username.setPromptText(t.getString("Label.UserName"));
		
		//Passwortbox Password
		PasswordField pf_password = new PasswordField();
		innerPaneLogin.add(pf_password, 2, 5);
		pf_password.setId("pf-login");
		pf_password.setPromptText(t.getString("Label.Password"));
		
		// checkbox password visibility
		passwordCheck = new CheckBox(t.getString("CheckBox.passwordCheck")); 	
		innerPaneLogin.add(passwordCheck, 2, 6);

		
		//Button Login
		b_login = new Button(t.getString("Button.Login"));
		innerPaneLogin.add(b_login, 1, 25);
		b_login.setPrefSize(200, 70);
		b_login.setId("b-login");
		
		//Button Register
		b_register = new Button(t.getString("Button.Register"));
		innerPaneLogin.add(b_register, 2, 25);
		b_register.setPrefSize(200, 70);
		b_register.setId("b-login");
		
		//Button Quit game
		b_quitGame = new Button(t.getString("Button.QuitGame"));
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
		b_spielErstellen = new Button(t.getString("Button.newGame"));
		b_spielErstellen.setPrefSize(200, 70);
		innerPaneLobby.add(b_spielErstellen, 1, 2);
		b_spielErstellen.setId("b-login");
		
		
		//Button Statistik
		b_statistic = new Button(t.getString("Button.Stats"));
		b_statistic.setPrefSize(200,70);
		innerPaneLobby.add(b_statistic, 2, 2);
		b_statistic.setId("b-login");
	
		
		//Button Regeln 
		b_rules = new Button(t.getString("Button.Rules"));
		b_rules.setPrefSize(200,70);
		innerPaneLobby.add(b_rules, 3, 2);
		b_rules.setId("b-login");
		
		
		//Button Spiel beitreten 
		b_spielBeitreten = new Button(t.getString("Button.JoinGame"));
		b_spielBeitreten.setPrefSize(200,70);
		innerPaneLobby.add(b_spielBeitreten, 2, 9);
		b_spielBeitreten.setId("b-login");
		
		
		//Button Zurück
		b_backLobby = new Button(t.getString("Button.Back"));
		b_backLobby.setPrefSize(200,70);
		innerPaneLobby.add(b_backLobby, 6, 9);
		b_backLobby.setId("b-login");
		
		//Label select game
		Label select_label = new Label(t.getString("Label.selectGame")+": ");
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
	    TableColumn userNameCol = new TableColumn(t.getString("TableColumn.UserNameCol"));
	    TableColumn scoreCol = new TableColumn(t.getString("TableColumn.Score"));
	    TableColumn dateCol = new TableColumn(t.getString("TableColumn.date"));
	    table.getColumns().addAll(userNameCol, scoreCol, dateCol);
	    table.setMaxSize(600, 400);
	    table.setMinSize(600, 400);
	    centerPaneStatistik.getChildren().add(table);
		
		//Button Zurück
		b_backStatistik = new Button(t.getString("Button.Back"));
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
		b_backRegeln = new Button(t.getString("Button.Back"));
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
		
		
		////////////////// CUBES PL1//////////////////////////
		
		WürfelPL1  = new ArrayList<Würfel>();
		
		// Cubes pink
		cubeViewPink = new Würfel(Farbe.Pink);
		WürfelPL1.add(cubeViewPink);
	
		// Cubes white
		cubeViewWhite1 = new Würfel(Farbe.Weiss);
		cubeViewWhite2 = new Würfel(Farbe.Weiss);
		WürfelPL1.add(cubeViewWhite1);
		WürfelPL1.add(cubeViewWhite2);
		

		// Cubes black1
		cubeViewBlack1 = new Würfel(Farbe.Schwarz);
		cubeViewBlack2 = new Würfel(Farbe.Schwarz);
		WürfelPL1.add(cubeViewBlack1);
		WürfelPL1.add(cubeViewBlack2);
		
		//Cubes red1
		cubeViewRed1 = new Würfel(Farbe.Rot);
		cubeViewRed2 = new Würfel(Farbe.Rot);
		WürfelPL1.add(cubeViewRed1);
		WürfelPL1.add(cubeViewRed2);
		
		
		//Button würfeln
		b_würfeln = new Button("Würfeln");
		b_würfeln.setId("b-login");
		
		//added nodes to BottomPane
		bottomPaneGameCube.getChildren().addAll(b_würfeln,cubeViewPink.getImageView(),cubeViewWhite1.getImageView(),cubeViewWhite2.getImageView(),cubeViewBlack1.getImageView(),cubeViewBlack2.getImageView(),cubeViewRed1.getImageView(),cubeViewRed2.getImageView(),b_backGame);
		
		
		//////////////////CUBES PL2 (SecondPlayer) //////////////////////////
		
		WürfelPL2  = new ArrayList<Würfel>();
		
		// Cubes pink PL2
		cubeViewPinkPL2 = new Würfel(Farbe.Pink);
		WürfelPL2.add(cubeViewPinkPL2);
		
		// Cubes white PL2
		cubeViewWhite1PL2 = new Würfel(Farbe.Weiss);
		cubeViewWhite2PL2 = new Würfel(Farbe.Weiss);
		WürfelPL2.add(cubeViewWhite1PL2);
		WürfelPL2.add(cubeViewWhite2PL2);
	
		
		// Cubes black PL2
		cubeViewBlack1PL2 = new Würfel(Farbe.Schwarz);
		cubeViewBlack2PL2 = new Würfel(Farbe.Schwarz);
		WürfelPL2.add(cubeViewBlack1PL2);
		WürfelPL2.add(cubeViewBlack2PL2);
		
		//Cubes red PL2
		cubeViewRed1PL2 = new Würfel(Farbe.Rot);
		cubeViewRed2PL2 = new Würfel(Farbe.Rot);
		WürfelPL2.add(cubeViewRed1PL2);
		WürfelPL2.add(cubeViewRed2PL2);
		
		//added nodes to innertopPaneGame
		innertopPaneGame.getChildren().addAll(cubeViewPinkPL2.getImageView(), cubeViewWhite1PL2.getImageView(), cubeViewWhite2PL2.getImageView(), cubeViewBlack1PL2.getImageView(), cubeViewBlack2PL2.getImageView(), cubeViewRed1PL2.getImageView(), cubeViewRed2PL2.getImageView());
				
		//Chat
		
		chatWindow = new TextArea();
		chatWindow.setMinSize(200, 50);
		chatWindow.setMaxSize(800, 50);
		chatWindow.setText("Willkommen");
		chatWindow.setEditable(false);
		
		
		//VBox Chat Input and Send
		
		HBox ChatInput = new HBox();
		
		chatInputWindow = new TextField();
		chatInputWindow.setText("Hier klicken ...");
		chatInputWindow.setMaxSize(700, 30);
		chatInputWindow.setMinSize(700, 30);
		
		
		Button b_sendchat = new Button();
		b_sendchat.setText("senden");
		b_sendchat.setMinSize(100, 30);
		b_sendchat.setMaxSize(100, 30);
		b_sendchat.setId("b-sendchat");
		
		
		
		
		ChatInput.getChildren().addAll(chatInputWindow, b_sendchat);
		
		
		bottomPaneGameChat.getChildren().addAll(chatWindow, ChatInput);
		
		
		//////////////// Primary Stage ////////////////////////
		
		primaryStage.setTitle("Client-Applikation");
		primaryStage.setScene(sceneLogin);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("images/ClientIcon.png"));
		
		
	}
	
	public void start(){
		primaryStage.show();
	}
	
	public String getName(){
		return "ClientView";
	}	
}