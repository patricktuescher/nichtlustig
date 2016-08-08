/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class is used to define the client GUI.
	 * @author 
	 */

package client;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tools.Translator;


public class ClientView {
	
	Stage primaryStage;
	ClientModel model;
	protected TextField tf_username;
	protected Button b_login, b_register,b_quitGame, b_backLobby ,b_statistic, b_rules, b_spielErstellen, b_spielBeitreten, b_backStatistik, b_backRegeln,b_nextImage, b_previousImage, b_backGame, b_würfeln, b_sendchat, b_fertigGame, b_backLoginFailed, b_languageChange, b_gameLobby;
	protected Label labelPL1, labelPL2, loginFailed, lb_username, lb_password, lb_chooseLanguage, labelFinished;
	protected TableView<Integer> table;
	protected TableColumn<Integer,String> rankCol, userNameCol, scoreCol, dateCol;
	protected GameAvailableImage gai;
	protected PasswordField pf_password;
	protected Scene sceneLobby, sceneLogin, sceneGame, sceneStatistik, sceneRegeln, sceneLoginFailed, sceneGameFinished;
	protected ServiceLocator sl;
	protected Translator t;
	protected int scorePL1, scorePL2;
	protected Rectangle rectangleSpace,rectangleSpace1, rectangleSpace2;
	protected ImageView turnPL1, turnPL2, winnerIV, loserIV, drawIV;
	protected Image turn1, turn2, turn1_de, turn2_de, winner, winner_de, loser, loser_de, draw, draw_de;
	protected HBox centerPaneStatistik ;
	protected BorderPane topPaneStatistik, topPaneGameFinished;
	
	//Height and Width of the cards
	final int cardheight = 90;
	final int cardwidth = 90;
	
	GridPane innerPaneLobby;
	Label select_label;
	
	//Image Array
	protected final int colIndex = 13;
	protected final int rowIndex = 6;
	GridPane innerPaneGame;
	GridPane leftPaneGame;
	BorderPane topPaneGame;
	protected ArrayList <Card> cardAL = new ArrayList<Card>();
	

	//Cubes PL1
	ArrayList <Würfel> WürfelPL1;
	Würfel cubeViewPink,cubeViewWhite1, cubeViewWhite2, cubeViewBlack1, cubeViewBlack2, cubeViewRed1, cubeViewRed2;
	
	//Cubes PL2
	HBox innertopPaneGame;
	ArrayList <Würfel> WürfelPL2;
	Würfel cubeViewPinkPL2, cubeViewWhite1PL2, cubeViewWhite2PL2, cubeViewBlack1PL2, cubeViewBlack2PL2, cubeViewRed1PL2, cubeViewRed2PL2;
	
	//@author Kevin Trottmann
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
		
		
		
/*----------------------------------------- Login Fenster -----------------------------------------*/
		//@author Kevin Trottmann
		
		//InnerPane Login
		GridPane innerPaneLogin = new GridPane();
		innerPaneLogin.setGridLinesVisible(false);
		innerPaneLogin.setHgap(10);
		innerPaneLogin.setVgap(10);
		innerPaneLogin.setPadding(new Insets(200,200,0,280));
		
		//Label Username
		lb_username = new Label(t.getString("Label.UserName")+ ": ");
		innerPaneLogin.add(lb_username, 2, 1);
		lb_username.setId("lb-username");
		
		//Label Password
		lb_password = new Label(t.getString("Label.Password")+": ");
		innerPaneLogin.add(lb_password, 2, 4);
		lb_password.setId("lb-password");
		
		//label choose language
		lb_chooseLanguage = new Label(t.getString("Label.Language")+": ");
		innerPaneLogin.add(lb_chooseLanguage, 2, 6);
		lb_chooseLanguage.setId("lb-password");
		
		//Textbox Username
		tf_username = new TextField();
		innerPaneLogin.add(tf_username, 2, 2);
		tf_username.setId("pf-login");
		tf_username.setPromptText(t.getString("Label.UserName"));
		
		//Passwortbox Password
		pf_password = new PasswordField();
		innerPaneLogin.add(pf_password, 2, 5);
		pf_password.setId("pf-login");
		pf_password.setPromptText(t.getString("Label.Password"));
		

		//language Change Button (German / Englisch)
		b_languageChange = new Button(t.getString("Button.languageChange"));
		innerPaneLogin.add(b_languageChange,2,7);
		b_languageChange.setPrefSize(210, 40);
		b_languageChange.setId("b-login");
		
		
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
		
/*----------------------------------------- Lobby Fenster -----------------------------------------*/ 
		
		//TopPane Lobby
		BorderPane topPaneLobby = new BorderPane();
		topPaneLobby.setId("topPaneLobby"); //ID for CSS
		
		
		//GridPane Lobby
		innerPaneLobby = new GridPane();
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
		b_backLobby = new Button(t.getString("Button.Logout"));
		b_backLobby.setPrefSize(200,70);
		innerPaneLobby.add(b_backLobby, 6, 9);
		b_backLobby.setId("b-login");
		
		//Label Spielerhost game
		select_label = new Label();
		select_label.setId("label");
		select_label.setPrefSize(200, 20);
		select_label.setAlignment(Pos.CENTER);
		innerPaneLobby.add(select_label, 2, 8);
		
		
		//Images availabe games
		this.gai = new GameAvailableImage(false);
		
		innerPaneLobby.add(gai.getImage(), 2, 7);
				
				
		//Scene Lobby
		sceneLobby = new Scene(topPaneLobby, 1200, 800);
		sceneLobby.getStylesheets().add("ClientStylesheet");
		
/*----------------------------------------- Statistik Fenster -----------------------------------------*/ 
		//@author Kevin Trottmann
		
		//TopPane Statistik
		topPaneStatistik = new BorderPane();
		topPaneStatistik.setId("topPaneStatistik"); //ID for CSS
		
		//HBox Statistik
		centerPaneStatistik = new HBox();
		centerPaneStatistik.setAlignment(Pos.CENTER);
		centerPaneStatistik.setPadding(new Insets(50,0,0,0));
		topPaneStatistik.setCenter(centerPaneStatistik);
		
		//HBox Statistik Bottom
		HBox bottomPaneStatistik = new HBox();
		bottomPaneStatistik.setPadding(new Insets(0,0,20,0));
		bottomPaneStatistik.setAlignment(Pos.CENTER);
		topPaneStatistik.setBottom(bottomPaneStatistik);
		
		//Button Zurück
		b_backStatistik = new Button(t.getString("Button.Back"));
		bottomPaneStatistik.getChildren().add(b_backStatistik);
		b_backStatistik.setPrefSize(200, 70);
		b_backStatistik.setId("b-login");
				
		//Scene Lobby
		sceneStatistik = new Scene(topPaneStatistik, 1200, 800);
		sceneStatistik.getStylesheets().add("ClientStylesheet");
		
		
/*----------------------------------------- Regeln Fenster -----------------------------------------*/ 
		
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
		regelnView.setFitHeight(600);
		regelnView.setFitWidth(600);

		// Button next
		b_nextImage = new Button(">");
		b_nextImage.setPrefSize(20,20);
		b_nextImage.setId("b-login");	
		
		// Button previous
		b_previousImage = new Button("<");
		b_previousImage.setPrefSize(20,20);
		b_previousImage.setId("b-login");
		
		// added nodes to inner pane Regeln
		innerPaneRegeln.getChildren().addAll(b_previousImage, regelnView,b_nextImage);
		innerPaneRegeln.setSpacing(10);
				
		//Scene Regeln
		sceneRegeln = new Scene(topPaneRegeln, 1200, 800);
		sceneRegeln.getStylesheets().add("ClientStylesheet");
		
/*----------------------------------------- Game Fenster -----------------------------------------*/ 
		
		//TopPane Game
		topPaneGame = new BorderPane();
		topPaneGame.setId("topPaneGame"); //ID for CSS
		

		//HBox Game Top aka Spieler 2 Bereich
		innertopPaneGame = new HBox();
		innertopPaneGame.setPadding(new Insets(15,0,0,60));
		innertopPaneGame.setMinHeight(100);
		innertopPaneGame.setMaxHeight(100);
		innertopPaneGame.setSpacing(15);
		topPaneGame.setTop(innertopPaneGame);
		
		//BorderPane Game Bottom
		BorderPane bottomPaneGame = new BorderPane();
		topPaneGame.setBottom(bottomPaneGame);
		
		
		
		//HBox Game Bottom Cube aka Spieler 1 Bereich
		HBox bottomPaneGameCube = new HBox();
		bottomPaneGameCube.setPadding(new Insets(20,0,0,10));
		bottomPaneGameCube.setSpacing(15);
		bottomPaneGame.setTop(bottomPaneGameCube);

		
		//HBox Game Bottom Chat
		VBox bottomPaneGameChat = new VBox();
		bottomPaneGameChat.setPadding(new Insets(0,0,10,200));
		bottomPaneGameChat.setMinHeight(100);
		bottomPaneGame.setBottom(bottomPaneGameChat);
		
		//Button Zurück
		b_backGame = new Button(t.getString("Button.Aufgeben"));
		b_backGame.setMinSize(100, 30);
		b_backGame.setMaxSize(100, 30);
		b_backGame.setId("b-sendchat");
		
		//Button Fertig
		b_fertigGame = new Button(t.getString("Button.Fertig"));
		b_fertigGame.setMinSize(120, 50);
		b_fertigGame.setId("b-login");
		
		// Space (between send & back button)
		rectangleSpace = new Rectangle(95,50);
		rectangleSpace.setId("spaceRectangle");
		
		// Label Score PL1 
		int scorePL1= 0; // needs to be adjusted
		labelPL1 = new Label(""+scorePL1);
		labelPL1.setMinSize(50, 50);
		labelPL1.setPadding(new Insets(0,0,0,130));
		labelPL1.setId("lb-labelscore");
				
		// Label Score PL2 
		int scorePL2 = 0; // needs to be adjusted
		labelPL2 = new Label(""+scorePL2);
		labelPL2.setMinSize(50, 50);
		labelPL2.setPadding(new Insets(0,0,0,260));
		labelPL2.setId("lb-labelscore");

		//ImageView Turn PL1
		turnPL1 = new ImageView();
		turn1 = new Image("images/yourTurn.png");
		turn1_de = new Image("images/deinZug.png");
		turnPL1.setImage(turn1);
		
		//ImageView Turn PL2
		turnPL2 = new ImageView();
		turn2= new Image("images/opponentsTurn.png");
		turn2_de= new Image("images/gegnersZug.png");
		turnPL2.setImage(turn2);
		
		//Scene Game
		sceneGame = new Scene(topPaneGame, 1200, 850);
		sceneGame.getStylesheets().add("ClientStylesheet");
		
/*-----------------------------------------  Cards -----------------------------------------*/ 
		
		for(int x = 1;x<6;x++){
			this.cardAL.add(new Card(cardType.Dino, x));
			this.cardAL.add(new Card(cardType.Lemming, x));
			this.cardAL.add(new Card(cardType.Prof, x));
			this.cardAL.add(new Card(cardType.Rieb, x));
			this.cardAL.add(new Card(cardType.Yeti, x));
			this.cardAL.add(new Card(cardType.Tod, x));
		}
		this.cardAL.add(new Card(cardType.Tod, 6));
	
		updateCards();
		
/*----------------------------------------- Cubes Player 1  -----------------------------------------*/ 
		
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
		b_würfeln = new Button(t.getString("Button.roll"));
		b_würfeln.setMinSize(100, 50);
		b_würfeln.setId("b-login");
		
		// space (between done botton & score label)
		rectangleSpace1 = new Rectangle(70,50);
		rectangleSpace1.setId("spaceRectangle");
		
		
		//added nodes to BottomPane
		bottomPaneGameCube.getChildren().addAll(turnPL1, b_würfeln,cubeViewPink.getImageView(),cubeViewWhite1.getImageView(),cubeViewWhite2.getImageView(),cubeViewBlack1.getImageView(),cubeViewBlack2.getImageView(),cubeViewRed1.getImageView(),cubeViewRed2.getImageView(),b_fertigGame,labelPL1);
		
/*----------------------------------------- Cubes Player 2 -----------------------------------------*/ 
		
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
		
		// space (between dice and score label on "opponent's" side)
		rectangleSpace2 = new Rectangle(220,50);
		rectangleSpace2.setId("spaceRectangle");
		
		//added nodes to innertopPaneGame
		innertopPaneGame.getChildren().addAll(turnPL2,cubeViewPinkPL2.getImageView(), cubeViewWhite1PL2.getImageView(), cubeViewWhite2PL2.getImageView(), cubeViewBlack1PL2.getImageView(), cubeViewBlack2PL2.getImageView(), cubeViewRed1PL2.getImageView(), cubeViewRed2PL2.getImageView(),labelPL2);
				
		//Chat
		chatWindow = new TextArea();
		chatWindow.setMinSize(200, 90);
		chatWindow.setMaxSize(800, 90);
		chatWindow.setText("");
		chatWindow.setEditable(false);
		
		
		//VBox Chat Input and Send
		HBox ChatInput = new HBox();
		chatInputWindow = new TextField();
		chatInputWindow.setText(t.getString("TextField.click"));
		chatInputWindow.setMaxSize(700, 30);
		chatInputWindow.setMinSize(700, 30);
		
		// b_sendchat Button
		b_sendchat = new Button();
		b_sendchat.setText(t.getString("Button.send"));
		b_sendchat.setMinSize(100, 30);
		b_sendchat.setMaxSize(100, 30);
		b_sendchat.setId("b-sendchat");
		
		ChatInput.getChildren().addAll(chatInputWindow, b_sendchat,rectangleSpace, b_backGame);
		
		bottomPaneGameChat.getChildren().addAll(chatWindow, ChatInput);

/*----------------------------------------- GameFinished Fenster -----------------------------------------*/
		
		//TopPane GameFinished
		topPaneGameFinished = new BorderPane();
		topPaneGameFinished.setId("gameOver");
		
		//Button GameLobby
		b_gameLobby = new Button (t.getString("Button.backLobby"));
		b_gameLobby.setPrefSize(220, 70);
		topPaneGameFinished.setBottom(b_gameLobby);
		b_gameLobby.setId("b-login");
		
		//ImageView winner
		winnerIV = new ImageView();
		winner = new Image("images/winner.png");
		winner_de = new Image("images/sieger.png");
		
		//ImageView loser
		loserIV = new ImageView();
		loser = new Image("images/loser.png");
		loser_de = new Image("images/verlierer.png");
	
		//ImageView draw
		drawIV = new ImageView();
		draw = new Image("images/draw.png");
		draw_de = new Image("images/unentschieden.png");
	
		
		//Scene GameFinished
		sceneGameFinished = new Scene(topPaneGameFinished, 1200, 800);
		sceneGameFinished.getStylesheets().add("ClientStylesheet");
		
		

/*----------------------------------------- Primary Stage -----------------------------------------*/ 
		
		primaryStage.setTitle(t.getString("Stage.title"));
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
	
	public ArrayList<Würfel> getWürfelPL1(){
		return this.WürfelPL1;
	}
	
	public ArrayList<Würfel> getWürfelPL2(){
		return this.WürfelPL2;
	}
	public void updateTable(ArrayList<String> scoreValues, ArrayList<String> nameValues, ArrayList <String> dateValues){
		centerPaneStatistik.getChildren().remove(table);
		ArrayList<String> rank = new ArrayList<String>();
		//Self-made bubble sort by Burri
		for(int y = 0; y < scoreValues.size(); y++){
			for(int x = 0; x < scoreValues.size()-1;x++){
				if(Integer.parseInt(scoreValues.get(x)) < Integer.parseInt(scoreValues.get(x+1))){
					String score1 = scoreValues.get(x);
					String score2 = scoreValues.get(x+1);
					String name1 = nameValues.get(x);
					String name2 = nameValues.get(x+1);
					String date1 = dateValues.get(x);
					String date2 = dateValues.get(x+1);
					
					scoreValues.set(x, score2);
					scoreValues.set(x+1, score1);
					nameValues.set(x, name2);
					nameValues.set(x+1, name1);
					dateValues.set(x, date2);
					dateValues.set(x+1, date1);
				}
			}
		}
		for(int x = 1; x < scoreValues.size()+1;x++){
			rank.add(x+"");
		}
			
            table = new TableView<>();
            table.setEditable(false);
            
            for (int i = 0; i < scoreValues.size() && i < nameValues.size() &&  i < dateValues.size()  ; i++) {
                table.getItems().add(i);
            }
            
            rankCol = new TableColumn<>(t.getString("TableColumn.Rank")); 
            rankCol.setCellValueFactory(cellData -> {
            	Integer rowIndex = cellData.getValue();
            	return new ReadOnlyStringWrapper(rank.get(rowIndex));
            });
            
            
            userNameCol = new TableColumn<>(this.t.getString("TableColumn.UserNameCol")); 
            userNameCol.setCellValueFactory(cellData -> {
            	Integer rowIndex = cellData.getValue();
            	return new ReadOnlyStringWrapper(nameValues.get(rowIndex));
            
            });
            
            scoreCol = new TableColumn<>(this.t.getString("TableColumn.Score")); 
            scoreCol.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(scoreValues.get(rowIndex));
            
            });
            
            dateCol = new TableColumn<>(this.t.getString("TableColumn.date")); 
            dateCol.setCellValueFactory(cellData -> {
            	Integer rowIndex = cellData.getValue();
            	return new ReadOnlyStringWrapper(dateValues.get(rowIndex));
            
            });
            rankCol.setSortable(false);
            userNameCol.setSortable(false);
            scoreCol.setSortable(false);
            dateCol.setSortable(false);
            rankCol.setMinWidth(60);
            userNameCol.setMinWidth(200);
            scoreCol.setMinWidth(200);
            dateCol.setMinWidth(140);
            table.getColumns().add(rankCol);
            table.getColumns().add(userNameCol);
            table.getColumns().add(scoreCol);
            table.getColumns().add(dateCol);
            table.setMaxSize(600, 400);
    	    table.setMinSize(600, 400);
    	   centerPaneStatistik.getChildren().add(table);
    	   
		    
    	   sl.getLogger().info("TableView created");
    	   sl.getLogger().info("added Data from Highscore file to TableView");
		    
		    		
	}
	
	public void updateCards(){
		innerPaneGame= new GridPane();
		innerPaneGame.setHgap(5);
		innerPaneGame.setVgap(5);
		innerPaneGame.setPadding(new Insets(0, 0, 15, 120));
		innerPaneGame.setGridLinesVisible(false);
		
		leftPaneGame = new GridPane();
		leftPaneGame.setHgap(5);
		leftPaneGame.setVgap(5);
		leftPaneGame.setPadding(new Insets(105, 0, 15, 100));
		leftPaneGame.setGridLinesVisible(false);
		
		//Putting it into the Grid
		int dino = 1;
		int lemming = 1;
		int prof = 1;
		int rieb = 1;
		int yeti = 1;
		int tod = 1;
		boolean b = true;
		for(int x = 0;x<31;x++){
			String s = this.cardAL.get(x).getType();
			
			switch(s){
			case "Dino": innerPaneGame.add(this.cardAL.get(x).getImage(), dino++,10); this.cardAL.get(x).getImage().setDisable(true);break;
			case "Prof": innerPaneGame.add(this.cardAL.get(x).getImage(), prof++, 8); this.cardAL.get(x).getImage().setDisable(true);break;
			case "Lemming":innerPaneGame.add(this.cardAL.get(x).getImage(), lemming++, 6); this.cardAL.get(x).getImage().setDisable(true);break;
			case "Yeti":innerPaneGame.add(this.cardAL.get(x).getImage(), yeti++, 4); this.cardAL.get(x).getImage().setDisable(true);break;
			case "Rieb":innerPaneGame.add(this.cardAL.get(x).getImage(), rieb++, 2); this.cardAL.get(x).getImage().setDisable(true);break;
			default: if(b){
				leftPaneGame.add(this.cardAL.get(x).getImage(), 0, tod);
				this.cardAL.get(x).getImage().setDisable(true);
				b = false;
				break;
			}
			else{
				leftPaneGame.add(this.cardAL.get(x).getImage(), 1, tod++);
				this.cardAL.get(x).getImage().setDisable(true);
				b = true;
				break;
			}
			}
		}
		topPaneGame.setLeft(leftPaneGame);
		topPaneGame.setCenter(innerPaneGame);
	}
}