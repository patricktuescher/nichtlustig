/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author Kevin Trottmann / Marco Kunz
	 */

package server;

import server.ServiceLocator;
import javafx.animation.PathTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import tools.Translator;

public class ServerView {
	
	protected Stage primaryStage;
	protected ServerModel model;
	protected Button exit, ConnectServer, DisconnectServer, languageChange;
	protected Label onlineOffline;
	protected ImageView serverIMV, serverIMV2, serverIMVOn, serverIMV2On, pointsIMV;
	protected Image serverImage, serverImage2, serverImageOn, serverImage2On, points;
	protected MenuItem MIclose, MIgerman, MIenglisch;
	protected GridPane upperPane;
	protected Translator t;
	protected ServiceLocator sl;
	protected PathTransition pt;
	protected Circle circle;
	protected HBox middleBox;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		sl = ServiceLocator.getServiceLocator();
		t = sl.getTranslator();
			
		//Setting up BorderPane
		BorderPane topPane = new BorderPane();
		topPane.setId("topPane"); //ID for CSS
		topPane.setPrefHeight(800);
		topPane.setPrefWidth(1200);
		
		//Vertical-Box
		HBox boxForBar = new HBox();
		//boxForBar.getChildren().add(bar);
		topPane.setTop(boxForBar);
		
		//points
		points = new  Image("images/points.png");
		pointsIMV = new ImageView();
		pointsIMV.setImage(points);
		pointsIMV.setVisible(false);
		
		//pathConnection
		Path path = new Path();
		path.getElements().addAll(new MoveTo(10,10), new HLineTo(200));
		path.setFill(null);
		path.setVisible(false);
		pt = new PathTransition(Duration.millis(1000),path, pointsIMV);
		pt.setCycleCount(2);
		pt.setAutoReverse(true);
		
		
		//serverImage 1 
		serverImage = new  Image("images/server.png");
		serverIMV = new ImageView();
		serverIMV.setImage(serverImage);
		serverIMV.setVisible(true);
		
		//serverImage 2 
		serverImage2 = new  Image("images/server.png");
		serverIMV2 = new ImageView();
		serverIMV2.setImage(serverImage2);
		serverIMV2.setVisible(true);
		
		//serverImage 1  ON
		serverImageOn = new  Image("images/serverOn.png");
		serverIMVOn = new ImageView();
		serverIMVOn.setImage(serverImageOn);
		serverIMVOn.setVisible(false);
		
		//serverImage 2  ON
		serverImage2On = new  Image("images/serverOn.png");
		serverIMV2On = new ImageView();
		serverIMV2On.setImage(serverImage2On);
		serverIMV2On.setVisible(false);
		
	
		//connect- and disconncet ButtonIcon
		Image connectButtonIcon = new Image("images/connectButtonIcon.png");
		ImageView connectIV = new ImageView(connectButtonIcon);
		Image disconnectButtonIcon = new Image("images/disconnectButtonIcon.png");
		ImageView disconnectIV = new ImageView(disconnectButtonIcon);
		
		//Connect- and Disconnect Buttons
		ConnectServer = new Button(t.getString("Button.ConnectServer"),connectIV);
		ConnectServer.setId("ServerButtons");
		ConnectServer.setPrefWidth(500);
		DisconnectServer = new Button(t.getString("Button.DisconnectServer"),disconnectIV);
		DisconnectServer.setId("ServerButtons");
		DisconnectServer.setPrefWidth(500);
		
		//languageButtonIcon
		Image languageButtonIcon = new Image("images/languageButtonIcon.png");
		ImageView languageIV = new ImageView(languageButtonIcon);
		
		//@author Kevin Trottmann
		//Language Change Button German / Englisch
		languageChange = new Button (t.getString("Button.languageChange"),languageIV);
		languageChange.setId("ServerButtons");
		languageChange.setPrefWidth(500);
		
		//exitButtonIcon
		Image exitButtonIcon = new Image("images/exitButtonIcon.png");
		ImageView exitIV = new ImageView(exitButtonIcon);
		
		//Exit button
		exit = new Button(t.getString("Button.exit"),exitIV);
		exit.setId("ServerButtons");
		exit.setPrefWidth(500);
		
		// HBox middleBox
		middleBox = new HBox();
		middleBox.getChildren().addAll(pointsIMV, path);
		middleBox.setPadding(new Insets (35,70,0,0));
	
		
		//upperPane
		GridPane upperPane = new GridPane();
		upperPane.setGridLinesVisible(false);
		upperPane.setPrefHeight(500);
		upperPane.setAlignment(Pos.CENTER);
		upperPane.setVgap(10);
		upperPane.setHgap(10);
		upperPane.setPadding(new Insets(200,0,0,0));
		upperPane.add(serverIMV, 1, 1);
		upperPane.add(serverIMV2, 3, 1);
		upperPane.add(middleBox, 2, 1);
		upperPane.add(serverIMVOn, 1, 1);
		upperPane.add(serverIMV2On, 3, 1);
		
		//bottomPane
		GridPane bottomPane = new GridPane();
		bottomPane.setGridLinesVisible(false);
		bottomPane.setAlignment(Pos.TOP_CENTER);
		bottomPane.setVgap(10);
		bottomPane.add(languageChange,0,0);
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
	
	public String getName(){
		return "ServerView";
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
