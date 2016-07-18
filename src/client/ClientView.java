package client;



import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.text.Position;

import tools.Translator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class ClientView {
	
	Stage primaryStage;
	ClientModel model;
	protected TextField tf_username;
	protected Button b_login, b_register,b_quitGame, b_backLobby ,b_statistic, b_rules, b_spielErstellen, b_spielBeitreten, b_backStatistik, b_backRegeln,b_nextImage, b_previousImage, b_backGame, b_würfeln, b_sendchat, b_fertigGame, b_backLoginFailed;
	protected Label labelPL1, labelPL2,turnPL1, turnPL2, loginFailed;
	protected GameAvailableImage gai;
	protected PasswordField pf_password;
	protected Scene sceneLobby, sceneLogin, sceneGame, sceneStatistik, sceneRegeln, sceneLoginFailed;
	protected CheckBox passwordCheck;
	protected ServiceLocator sl;
	protected Translator t;
	protected Integer scorePL1, scorePL2;
	protected Rectangle rectangleSpace,rectangleSpace1, rectangleSpace2;
	
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
		
		//label choose language
		Label lb_chooseLanguage = new Label(t.getString("Label.Language")+": ");
		innerPaneLogin.add(lb_chooseLanguage, 2, 6);
		lb_chooseLanguage.setId("lb-password");
		
		//Textbox Username
		tf_username = new TextField();
		innerPaneLogin.add(tf_username, 2, 2);
		tf_username.setId("pf-login");
		tf_username.setPromptText(t.getString("Label.UserName"));
		
		//Passwortbox Password
		PasswordField pf_password = new PasswordField();
		innerPaneLogin.add(pf_password, 2, 5);
		pf_password.setId("pf-login");
		pf_password.setPromptText(t.getString("Label.Password"));
		
		/*
		// checkbox password visibility
		passwordCheck = new CheckBox(t.getString("CheckBox.passwordCheck")); 	
		innerPaneLogin.add(passwordCheck, 2, 6);*/

		
		//language choiceBox
		ChoiceBox<String> choiceboxLogin = new ChoiceBox<>();
		choiceboxLogin.getItems().addAll("deutsch","englisch");
		choiceboxLogin.setPrefSize(200, 40);
		choiceboxLogin.setValue("deutsch");
		innerPaneLogin.add(choiceboxLogin,2,7);
		choiceboxLogin.setId("pf-login");
		
		
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
		
		///////////////// GAME FENSTER //////////////////////////
		
		//TopPane Game
		topPaneGame = new BorderPane();
		topPaneGame.setId("topPaneGame"); //ID for CSS
		

		//HBox Game Top
		innertopPaneGame = new HBox();
		innertopPaneGame.setPadding(new Insets(20,0,0,240));
		innertopPaneGame.setMinHeight(100);
		innertopPaneGame.setMaxHeight(100);
		innertopPaneGame.setSpacing(15);
		topPaneGame.setTop(innertopPaneGame);
		
		//BorderPane Game Bottom
		BorderPane bottomPaneGame = new BorderPane();
		topPaneGame.setBottom(bottomPaneGame);
		
		
		
		//HBox Game Bottom Cube
		HBox bottomPaneGameCube = new HBox();
		bottomPaneGameCube.setPadding(new Insets(20,100,20,200));
		bottomPaneGameCube.setSpacing(15);
		bottomPaneGame.setTop(bottomPaneGameCube);

		
		//HBox Game Bottom Chat
		VBox bottomPaneGameChat = new VBox();
		bottomPaneGameChat.setPadding(new Insets(0,0,10,200));
		bottomPaneGameChat.setMinHeight(100);
		bottomPaneGame.setBottom(bottomPaneGameChat);
		
		
		
		
		
		

		//Button Zurück
		b_backGame = new Button(t.getString("Button.Back"));
		b_backGame.setMinSize(100, 30);
		b_backGame.setMaxSize(100, 30);
		b_backGame.setId("b-sendchat");
		
		//Button Fertig
		b_fertigGame = new Button(t.getString("Button.Fertig"));
		b_fertigGame.setMinSize(120, 50);
		b_fertigGame.setId("b-login");
		
		// Space (between send & back button)
		rectangleSpace = new Rectangle(90,50);
		rectangleSpace.setId("spaceRectangle");
		
		
		// Label Score PL1 
		int scorePL1= 15; // needs to be adjusted
		labelPL1 = new Label(""+scorePL1);
		labelPL1.setMinSize(50, 50);
		labelPL1.setId("lb-labelscore");
		

				
		// Label Score PL2 
		int scorePL2 = 33; // needs to be adjusted
		labelPL2 = new Label(""+scorePL2);
		labelPL2.setMinSize(50, 50);
		labelPL2.setId("lb-labelscore");
		
		
		// Label Turn PL1
		turnPL1 = new Label("Your turn");
		turnPL1.setVisible(false);
		
		// Label Turn PL2
		turnPL2 = new Label("opponent's turn");
		turnPL2.setVisible(false);
		
		//Scene Game
		sceneGame = new Scene(topPaneGame, 1200, 850);
		sceneGame.getStylesheets().add("ClientStylesheet");
		
		
		
				
				

		
		///////////////// CARDS //////////////////////////
		
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
		b_würfeln = new Button(t.getString("Button.roll"));
		b_würfeln.setMinSize(100, 50);
		b_würfeln.setId("b-login");
		
		// space (between done botton & score label)
		rectangleSpace1 = new Rectangle(100,50);
		rectangleSpace1.setId("spaceRectangle");
		
		
		//added nodes to BottomPane
		bottomPaneGameCube.getChildren().addAll(turnPL1, b_würfeln,cubeViewPink.getImageView(),cubeViewWhite1.getImageView(),cubeViewWhite2.getImageView(),cubeViewBlack1.getImageView(),cubeViewBlack2.getImageView(),cubeViewRed1.getImageView(),cubeViewRed2.getImageView(),b_fertigGame,rectangleSpace1,labelPL1);
		
		
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
		
		// space (between dice and score label on "opponent's" side)
		rectangleSpace2 = new Rectangle(210,50);
		rectangleSpace2.setId("spaceRectangle");
		
		//added nodes to innertopPaneGame
		innertopPaneGame.getChildren().addAll(turnPL2,cubeViewPinkPL2.getImageView(), cubeViewWhite1PL2.getImageView(), cubeViewWhite2PL2.getImageView(), cubeViewBlack1PL2.getImageView(), cubeViewBlack2PL2.getImageView(), cubeViewRed1PL2.getImageView(), cubeViewRed2PL2.getImageView(),rectangleSpace2,labelPL2);
				
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
		
		
		b_sendchat = new Button();
		b_sendchat.setText(t.getString("Button.send"));
		b_sendchat.setMinSize(100, 30);
		b_sendchat.setMaxSize(100, 30);
		b_sendchat.setId("b-sendchat");
		
		
		ChatInput.getChildren().addAll(chatInputWindow, b_sendchat,rectangleSpace, b_backGame);
		
		
		bottomPaneGameChat.getChildren().addAll(chatWindow, ChatInput);
		
		
		//////////////// Primary Stage ////////////////////////
		
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
			case "Dino": innerPaneGame.add(this.cardAL.get(x).getImage(), dino++,10); break;
			case "Prof": innerPaneGame.add(this.cardAL.get(x).getImage(), prof++, 8); break;
			case "Lemming":innerPaneGame.add(this.cardAL.get(x).getImage(), lemming++, 6); break;
			case "Yeti":innerPaneGame.add(this.cardAL.get(x).getImage(), yeti++, 4); break;
			case "Rieb":innerPaneGame.add(this.cardAL.get(x).getImage(), rieb++, 2); break;
			default: if(b){
				leftPaneGame.add(this.cardAL.get(x).getImage(), 0, tod);
				b = false;
				break;
			}
			else{
				leftPaneGame.add(this.cardAL.get(x).getImage(), 1, tod++);
				b = true;
				break;
			}
			}
		}
		topPaneGame.setLeft(leftPaneGame);
		topPaneGame.setCenter(innerPaneGame);
	}
}