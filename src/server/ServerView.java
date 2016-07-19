package server;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class ServerView {
	
	protected Stage primaryStage;
	protected ServerModel model;
	protected Button exit, ConnectServer, DisconnectServer;
	protected Label onlineOffline;
	protected ImageView serverIMV, serverIMV2, conOnlineIMV, conOfflineIMV;
	protected MenuItem MIclose, MIdeutsch, MIenglisch;
	protected GridPane upperPane;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
	
		
		//Setting up BorderPane
		BorderPane topPane = new BorderPane();
		topPane.setId("topPane"); //ID for CSS
		topPane.setPrefHeight(800);
		topPane.setPrefWidth(1200);
	
		/*
		//Menu-Bar
		MenuBar bar = new MenuBar();
		Menu file = new Menu("Datei");
		Menu language = new Menu("Sprache");
		MIdeutsch = new MenuItem("deutsch");
		MIenglisch = new MenuItem("englisch");
		language.getItems().addAll(MIdeutsch,MIenglisch);
		MIclose = new MenuItem("schliessen");
		file.getItems().add(MIclose);
		bar.getMenus().addAll(file, language);*/
		
		//Vertical-Box
		HBox boxForBar = new HBox();
		//boxForBar.getChildren().add(bar);
		topPane.setTop(boxForBar);

		
		//Online-Offline-Label
		onlineOffline = new Label("Offline");
		onlineOffline.setId("offline");
		
		//serverImage 1
		Image serverImage;
		ImageView serverIMV;
		serverImage = new  Image("images/server.png");
		serverIMV = new ImageView();
		serverIMV.setImage(serverImage);
		
		//serverImage 2
		Image serverImage2;
		ImageView serverIMV2;
		serverImage2 = new  Image("images/server.png");
		serverIMV2 = new ImageView();
		serverIMV2.setImage(serverImage2);
		
		//ConOnline
		Image conOnline;
		conOnline = new  Image("images/ConOnline.png");
		conOnlineIMV = new ImageView();
		conOnlineIMV.setImage(conOnline);
		conOnlineIMV.setVisible(false);
		
		
		//ConOffline
		Image conOffline;
		conOffline = new  Image("images/ConOffline.png");
		conOfflineIMV = new ImageView();
		conOfflineIMV.setImage(conOffline);
		conOfflineIMV.setVisible(true);
		
		
		//Connect- and Disconnect Buttons
		ConnectServer = new Button("Connect to Server");
		ConnectServer.setId("ServerButtons");
		ConnectServer.setPrefWidth(500);
		DisconnectServer = new Button("Disconnect from Server");
		DisconnectServer.setId("ServerButtons");
		DisconnectServer.setPrefWidth(500);
		
		//language choiceBox
		ChoiceBox<String> choicebox = new ChoiceBox<>();
		choicebox.getItems().addAll("choose language","deutsch","englisch");
		choicebox.setPrefSize(500, 10);
		choicebox.setValue("choose language");
		choicebox.setId("ServerButtons");
		
		
		//Exit button
		exit = new Button("Exit");
		exit.setId("ServerButtons");
		exit.setPrefWidth(500);
		
		
		//upperPane
		GridPane upperPane = new GridPane();
		upperPane.setPrefHeight(500);
		upperPane.setAlignment(Pos.CENTER);
		upperPane.setVgap(10);
		upperPane.setHgap(10);
		upperPane.add(conOfflineIMV, 4, 20);
		upperPane.add(conOnlineIMV, 4, 20);
		upperPane.add(serverIMV, 3, 20);
		upperPane.add(serverIMV2, 5, 20);
		
		
		
		//bottomPane
		GridPane bottomPane = new GridPane();
		bottomPane.setAlignment(Pos.TOP_CENTER);
		bottomPane.setVgap(10);
		bottomPane.add(choicebox,0,0);
		bottomPane.add(ConnectServer, 0, 1);
		bottomPane.add(DisconnectServer, 0, 2);
		bottomPane.add(exit, 0, 3);

		
		//Vertical-Box
		VBox centerBox = new VBox();
		centerBox.getChildren().add(upperPane);
		centerBox.getChildren().add(bottomPane);
		topPane.setCenter(centerBox);
		
		
		Scene scene = new Scene(topPane);
		scene.getStylesheets().add("ServerStylesheet");
		primaryStage.setTitle("Server-Applikation");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("images/ServerIcon.png"));
		primaryStage.setScene(scene);
	}
	
	public void start(){
		primaryStage.show();
	}
    
    /**
     * Stopping the view - just make it invisible
     */
    public void stop() {
        primaryStage.hide();
    }
    
    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
        return primaryStage;
    }
}
