/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function  The clientController class  acts on both model and view. It controls the data flow into model object and updates the view whenever data changes.
	 * @author 
	 */

package client;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import message.CardClick;
import message.CardDeath;
import message.CardValued;
import message.ChatMessage;
import message.ClientLogout;
import message.ClientTurn;
import message.DieRoll;
import message.GameComplete;
import message.GameFinished;
import message.GameQuit;
import message.HighscoreUpdate;
import message.PointUpdate;
import message.initiateNewGame;
import message.newAccountMessage;
import message.newScore;
import tools.Translator;

public class ClientController {

	protected ServiceLocator sl;       
	protected ClientView view;
	protected ClientModel model;
	protected ServerListener server;
	protected static Account clientOwner;
	protected Translator t;
	protected TableView<Integer> table;
	protected TableColumn<Integer,String> nameCol, dateCol;
	protected TableColumn<Integer,Number> scoreCol;


	
	public ClientController(ClientView view, ClientModel model){
		this.view = view;
		this.model = model;
		sl = ServiceLocator.getServiceLocator();
		t = sl.getServiceLocator().getTranslator();
		ServerListener.controller = this;

		
/*----------------------------------------- LOGIN FENSTER -----------------------------------------*/ 
		
		
		// EventHandler Language Change Button - LoginScene
				view.b_languageChange.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						if(view.t.getCurrentLocaleString().equals("en")) {
			            	   sl.getServiceLocator().setTranslator(new Translator("de"));
			            	   view.topPaneStatistik.setStyle("-fx-background-image: url(images/DE_StatistikHintergrund.png);");
			            	   view.turnPL1.setImage(view.turn1_de);
			            	   view.turnPL2.setImage(view.turn2_de);
			      			}
			     			else{
			     			sl.getServiceLocator().setTranslator(new Translator("en"));
			     			view.topPaneStatistik.setStyle("-fx-background-image: url(images/EN_StatistikHintergrund.png);");
							view.turnPL1.setImage(view.turn1);
							view.turnPL2.setImage(view.turn2);
			     			}
	
								updateView();
						
					}
				});
				
		// EventHandler LoginButton - LoginScene
		view.b_login.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				server = ServerListener.getServerListener();
				clientOwner = new Account(view.tf_username.getText(), view.pf_password.getText());
				server.connect(clientOwner);
				sl.getLogger().info("Change to Lobby Scene");
				view.pf_password.setText("");

			}
		});
		
		
		
		
		
		// EventHandler RegisterButton - LoginScene
		view.b_register.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			server = ServerListener.getServerListener();
			server.connect();
			server.sendObject(new newAccountMessage(new Account(view.tf_username.getText(), view.pf_password.getText())));
			view.tf_username.setText("");
			view.pf_password.setText("");
			//server.disconnect();
			}		
		});
		
		
		// EventHandler QuitGameButton - LoginScene
		view.b_quitGame.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			System.exit(0);	
			}		
		});
		
		
		
		
		
/*----------------------------------------- LOBBY FENSTER -----------------------------------------*/ 
				
		
		// EventHandler ZurückButton - LobbyScene
		view.b_backLobby.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			server.sendObject(new ClientLogout());
			sl.getLogger().info("Change to Login Scene");
			}		
		});	
		
		
		// EventHandler StatistikButton - LobbyScene
		view.b_statistic.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				server.sendObject(new HighscoreUpdate());
				
			    
			    
			}		
		});
		
		
		
		
		
		
			
		
		// EventHandler RegelnButton - LobbyScene
		view.b_rules.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneRules);
			sl.getLogger().info("Change to Regeln Scene");
			}		
		});
				
		
		// EventHandler SpielErstellenButton - LobbyScene !!!!! to be difined
		view.b_gameCreate.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneGame);
			sl.getLogger().info("Change to Game Scene");
			disableCards();
			view.turnPL1.setVisible(false);
			view.turnPL2.setVisible(false);
			server.sendObject(new initiateNewGame());
			}	
		});
				
		
		// EventHandler SpielBeitretenButton - LobbyScene !!!!! to be difined
		view.b_gameJoin.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			server.sendObject(new GameComplete());
			view.primaryStage.setScene(view.sceneGame);
			sl.getLogger().info("Change to Game Scene");
			disableCards();
			}		
		});	
			
		
/*----------------------------------------- Statistik FENSTER -----------------------------------------*/ 
		//@author Kevin Trottmann		
		
		// EventHandler ZurückButton - StatistikScene
		view.b_backStatistic.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				view.centerPaneStatistik.getChildren().remove(table);
				view.primaryStage.setScene(view.sceneLobby);
				sl.getLogger().info("Change to Lobby Scene");
			}
		});
		
		
/*----------------------------------------- Regeln FENSTER -----------------------------------------*/ 
		
		
		// EventHandler ZurückButton - RegelnScene
		view.b_backRules.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);
				sl.getLogger().info("Change to Lobby Scene");
			}
		});
		
		// EventHandler nextImage Button - image position in reglen array to be defined; currently set to 1 for testing
				view.b_nextImage.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) 
					{
					if (ClientView.currentRuleImage <view.rules.length-1){
					view.rulesView.setImage(view.rules[ClientView.currentRuleImage+1]);
					ClientView.currentRuleImage++;
					sl.getLogger().info("Change RuleImage Plus");
					}
					}
				});
	
				
		// EventHandler nextImage Button - image position in reglen array to be defined; currently set to 1 for testing
				view.b_previousImage.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) 
					{
					if (ClientView.currentRuleImage >0){
					view.rulesView.setImage(view.rules[ClientView.currentRuleImage-1]);
					ClientView.currentRuleImage--;
					sl.getLogger().info("Change RuleImage Minus");
					}
					}
					
				});
	
		
/*----------------------------------------- Game Fenster -----------------------------------------*/ 

				
/*----------------------------------------- EventHandler Zurück Button -----------------------------------------*/ 

				view.b_backGame.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				playerQuit();
				server.sendObject(new GameQuit());
			}
		});

/*----------------------------------------- EventHandler Würfel -----------------------------------------*/ 

		view.b_roll.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				roll();
				model.incrementPlayerRoll();
				System.out.println(getDies());
				model.startCardChecker(view.cardAL, getDies());
				checkTurn();
				}
		});
		
/*----------------------------------------- EventHandler Zoom Cards -----------------------------------------*/ 
		
		for(int x = 0;x<31;x++){	
		final int d = x;
			view.cardAL.get(x).getImage().setOnMouseEntered(new EventHandler<MouseEvent>(){
				
				@Override
				public void handle(MouseEvent arg0){
					view.cardAL.get(d).zoomCard();
				}
			});
			view.cardAL.get(x).getImage().setOnMouseExited(new EventHandler<MouseEvent>(){
				
				@Override
				public void handle(MouseEvent arg0){
					view.cardAL.get(d).deZoomCard();
				}
			});
			
/*----------------------------------------- EventHandler Chosen  Cards -----------------------------------------*/ 
			
			if(!view.cardAL.get(x).getType().equals(cardType.Tod.toString())){
				view.cardAL.get(x).getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardAL.get(d).click(clientOwner);
						view.cardAL.get(d).setOwner(clientOwner);
						for(int y = 0; y < view.cardAL.get(d).getDie().size(); y++){
							if(view.diesPL1.contains(view.cardAL.get(d).getDie().get(y))){
								view.diesPL1.get(view.diesPL1.indexOf(view.cardAL.get(d).getDie().get(y))).setUsed(true);
								view.diesPL1.get(view.diesPL1.indexOf(view.cardAL.get(d).getDie().get(y))).click();
								if(view.cardAL.get(d).getDie().size() == 2){
									view.diesPL1.get(view.diesPL1.lastIndexOf(view.cardAL.get(d).getDie().get(y))).setUsed(true);
									view.diesPL1.get(view.diesPL1.lastIndexOf(view.cardAL.get(d).getDie().get(y))).click();
								}
								System.out.println(view.cardAL.get(d).getDie().get(y) + "is used");
							}
						}
						if(view.cardAL.get(d).getType().equals("Dino")){
							for(int y = 1; y < view.diesPL1.size(); y++){
							view.diesPL1.get(y).setUsed(true);
							view.diesPL1.get(y).click();
							}
						}
						model.startCardChecker(view.cardAL, view.diesPL1);
						server.sendObject(new CardClick(view.cardAL.get(d)));
						view.b_finishGame.setDisable(false);
					}
				});	
				
				}else{
					view.cardAL.get(x).getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){
						
						@Override
						public void handle(MouseEvent arg0){
							if(view.cardAL.get(d).getStatus().equals(Status.todgesetzt)){
								removeCardDeath(view.cardAL, view.cardAL.get(d));
								

								
							}
							
							view.cardAL.get(d).setStatus(Status.todungesetzt);
							view.cardAL.get(d).click(clientOwner);



							model.checkCardsToChooseDeath(view.cardAL);
							
							if(!model.checkCardsToChooseDeath(view.cardAL)){
								view.b_finishGame.setDisable(false);
							}
							
							server.sendObject(new CardClick(view.cardAL.get(d)));
							
			
							
							for(int t = 0;t<31;t++){
								final int a = t;
								view.cardAL.get(t).getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){

									@Override
									public void handle(MouseEvent arg0){
										view.cardAL.get(d).setStatus(Status.todgesetzt);
										server.sendObject(new CardClick(view.cardAL.get(d)));
										view.cardAL.get(a).setStatus(Status.tod);
										view.cardAL.get(a).setcardDeath(view.cardAL.get(d));
										view.cardAL.get(a).getImage();

										
										server.sendObject(new CardDeath(view.cardAL.get(a)));
										updatePoints();
										disableCards();
										view.b_finishGame.setDisable(false);
										
									}
								});
							}
								
				
						}
			});		
			}
				}
		
/*----------------------------------------- EventHandler Chosen Cubes -----------------------------------------*/ 
		
		for(int i = 0; i < this.getDies().size();i++){
			final int d = i;
			this.getDies().get(i).getImageView().setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					getDies().get(d).click();
					
					
				}
				}
			);
			
		}
		
		
/*----------------------------------------- EventHandler b_sendchat Button -----------------------------------------*/ 
		
				view.b_sendchat.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						if(!view.chatInputWindow.getText().equals("")){
						server.sendObject(new ChatMessage(view.chatInputWindow.getText()));
						view.chatInputWindow.clear();
						}
						

					}
				});
		
				
/*----------------------------------------- EventHandler Fertig Button -----------------------------------------*/ 
			
				view.b_finishGame.setOnAction(new EventHandler<ActionEvent>(){
					
					
					//@Override
					public void handle(ActionEvent arg0) {

						model.resetPlayerRoll();
						resetCardHandler();
						System.out.println(getDies());
						disableCards();
//						changeCardsToGewählt();
						if(checkGameContinue()){
							updatePoints();	
							server.sendObject(new ClientTurn(false));
						}else{
							if(!profCard()){
							server.sendObject(new GameFinished());
							}
							else{
								Platform.runLater(new Runnable(){
									@Override
									public void run(){
										valueProfCard();
									}
								});


							}
						}
						}
				});
				
/*----------------------------------------- EventHandler GameFinished Button -----------------------------------------*/ 
				
				// EventHandler ZurückButton - GameFinished
				view.b_gameLobby.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						view.primaryStage.setScene(view.sceneLobby);
							
						sl.getLogger().info("Change to Lobby Scene");
					}		
				});

		}
	
	
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void setLobbyScene(){
		this.view.primaryStage.setScene(this.view.sceneLobby);
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void setGameFinishedScene(){
		this.view.primaryStage.setScene(this.view.sceneGameFinished);
	}
	/**
	 * @author Nicola Burri
	 */
	public void setLoginFailedScene(){
		this.view.primaryStage.setScene(this.view.sceneLoginFailed);
		this.view.primaryStage.centerOnScreen();
	}
	/**
	 * @author Nicola Burri
	 */
	public void setLoginScene(){
		this.view.primaryStage.setScene(this.view.sceneLogin);
		this.view.tf_username.clear();
	}
	/**
	 * @author Nicola Burri
	 */
	public void addNewMessage(String s){
		view.chatWindow.appendText(s+"\n");
	}
	/**
	 * @author Nicola Burri
	 */
	public void setOpponentDi(ArrayList<Würfel> dies){
		sl.getLogger().info("Opponents Die are being set");
			
		for(int x = 0;x<view.diesPL2.size();x++){
			view.diesPL2.get(x).setCurrentNumberofeyes(dies.get(x).getCurrentNumberofeyes());
	}
		sl.getLogger().info("Opponent Die changed");
	}
	
	/**
	 * @author Nicola Burri
	 */
	
	public void opponentSelectCard(Card card){
		for(int x = 0; x < view.cardAL.size();x++){
			if(view.cardAL.get(x).equals(card)){
				view.cardAL.get(x).clickOther(card.getOwner());
				view.cardAL.get(x).setStatus(card.getStatus());
				break;
			}
		}
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void opponentValueCard(Card card){
		for(int x = 0; x < view.cardAL.size();x++){
			if(view.cardAL.get(x).equals(card)){
				view.cardAL.get(x).setStatus(Status.gewertet);
				view.cardAL.get(x).getImage();
				break;
			}

		}
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void opponentDeathCard(Card card){
		for(int x = 0; x < view.cardAL.size();x++){
			if(view.cardAL.get(x).equals(card)){
				view.cardAL.get(x).setcardDeath(card.getcardDeath());
				view.cardAL.get(x).setStatus(Status.tod);
				view.cardAL.get(x).getImage();
				break;
			}
		}
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void setOpClientTurn(){
		model.resetPlayerRoll();
		view.turnPL1.setVisible(false);
		view.turnPL2.setVisible(true);
		view.b_roll.setDisable(true);
		view.b_finishGame.setDisable(true);
			
	
	}
	
	/**
	 * @author Nicola Burri
	 */
	public void setGameAvImageOnOff(boolean on){
		if(on){
			this.view.gai.setImageOn();
			this.view.b_gameCreate.setDisable(true);
			this.view.b_gameJoin.setDisable(false);
		}
		else{
			this.view.b_gameCreate.setDisable(false);
			this.view.b_gameJoin.setDisable(true);
			this.view.gai.setImageOff();
		}
		view.innerPaneLobby.add(view.gai.getImage(), 2, 7);
	}
	/**
	 * @author Nicola Burri
	 */
	public void setAvailableLabel(String connectionName){
		view.select_label.setText(connectionName + " " + view.t.getString("Text.isonline"));
		view.innerPaneLobby.getChildren().remove(view.select_label);
		view.innerPaneLobby.add(view.select_label, 2, 8);
	}
	/**
	 * @author Nicola Burri
	 */
	public void setUpGame(){
		setUpDie();
		setUpCards();
		setUpPoints();
		view.scoreHbox.getChildren().clear();
		view.chatWindow.clear();
		setupLabel();
		setGameAvImageOnOff(false);
		view.select_label.setText(null);
		view.b_gameCreate.setVisible(true);
	}
	
	/**
	 * @author Kevin Trottmann
	 */
	public void getAlert(){
	t = sl.getServiceLocator().getTranslator(); //für Kevin: sonst wird nicht die richtige Sprache ausgegeben
   	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle(t.getString("Text.AlertLogintitel"));
	alert.setHeaderText(t.getString("Text.AlertLogintext"));
	alert.initStyle(StageStyle.TRANSPARENT);
	alert.setContentText(t.getString("Text.AlertLogintext2"));
	alert.showAndWait();
	this.setLoginScene();
	}
	
	/**
	 * @author Kevin Trottmann
	 */
	public void getRegAlert(){ 
		t = sl.getServiceLocator().getTranslator();
	   	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(t.getString("Text.AlertRegtitel"));
		alert.setHeaderText(t.getString("Text.AlertRegtext"));
		alert.initStyle(StageStyle.TRANSPARENT);
		alert.setContentText(t.getString("Text.AlertRegtext2"));
		alert.showAndWait();
		this.setLoginScene();
		}
	/**
	 * @author Kevin Trottmann
	 */
	public void getRegFailedAlert(){
		t = sl.getServiceLocator().getTranslator();
	   	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(t.getString("Text.AlertRegfailtitel"));
		alert.setHeaderText(t.getString("Text.AlertRegfailtext"));
		alert.initStyle(StageStyle.TRANSPARENT);
		alert.setContentText(t.getString("Text.AlertRegfailtext2"));
		alert.showAndWait();
		this.setLoginScene();
		}
	

	 /**
	  * @author Nicola Burri
	  * 
	  */
	
	public void setUpDie(){
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				for(int x = 0; x < getDies().size(); x++){
					getDies().get(x).resetWürfel();
					
					}
				setDiesDisabled(true);
			}
		});
		
	}
	 /**
	  * @author Nicola Burri
	  * 
	  */
	public void setUpCards(){
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				for(int x = 0; x<view.cardAL.size(); x++){
					view.cardAL.get(x).setOwner(null);
					view.cardAL.get(x).setStatus(Status.frei);
					view.cardAL.get(x).getImage().setEffect(null);
					view.cardAL.get(x).getImage().setId(null);
				}
			}	
		});
	}
	 /**
	  * @author Nicola Burri
	  * 
	  */
	public void setUpPoints(){
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				view.scorePL1 = 0;
				view.scorePL2 = 0;
				view.labelPL1.setText(""+view.scorePL1);
				view.labelPL2.setText(""+view.scorePL2);
			}
		});
	}
	 /**
	  * @author Nicola Burri
	  * 
	  */
	public void roll(){
		for(int x = 0; x < this.getDies().size(); x++){
			if(!this.getDies().get(x).isSelected()){

			this.getDies().get(x).roll();
			}
			
			}
		
		server.sendObject(new DieRoll(this.getDies()));
	
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public ArrayList<Würfel> getDies(){
		return view.getDiesPL1();
	}

	 /**
	  * @author Nicola Burri
	  * 
	  */

	public void initiateTurn() {
		view.turnPL1.setVisible(true);
		view.turnPL2.setVisible(false);
		model.resetPlayerRoll();
		setUpDie();
		view.b_roll.setDisable(false);
		view.b_finishGame.setDisable(true);
	}

	/**
	 * @author Nicola Burri & Patrick Tüscher
	 * 
	 */
	public void checkTurn(){
		if(model.getPlayerRollCounter() == 0){
			setDiesDisabled(true);
			view.b_finishGame.setDisable(true);
		}else if(model.getPlayerRollCounter() < 3 && !model.allDiesSelected(getDies())){
			setDiesDisabled(false);
			view.b_roll.setDisable(false);
			view.b_finishGame.setDisable(true);
		}else{
			selectAllDies();
			view.b_roll.setDisable(true);
			valueCards();
			updatePoints();
			model.startCardChecker(view.cardAL, getDies());
			model.activateCards(view.cardAL, getDies(), clientOwner);
			if(model.countDeath == 0){
				view.b_finishGame.setDisable(false);
				resetFinishButton();
			}
			

		}
	}
	

	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void changeCardsToElected(){
		for(int x = 0; x< view.cardAL.size();x++){
			if(view.cardAL.get(x).getStatus().equals(Status.neugewählt)){
				view.cardAL.get(x).setStatus(Status.gewählt);
			}
		}
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void valueCards(){
		sl.getLogger().info("Bewertung gestartet");
		if(view.getDiesPL1().get(0).getCurrentNumberofeyes() == 6){
		//keine Bewertung wenn Pudel des Todes gewürfelt wurde
		}else{
		for(int x = 0; x < view.cardAL.size(); x++){
			if(!view.cardAL.get(x).getType().equals(cardType.Tod.toString())){
				
			if(view.cardAL.get(x).getNumberofeyes() == view.getDiesPL1().get(0).getCurrentNumberofeyes() && view.cardAL.get(x).getStatus().equals(Status.gewählt)){
					view.cardAL.get(x).setStatus(Status.gewertet);
					sl.getLogger().info(view.cardAL.get(x).toString() + "hat jetzt den Status:" + view.cardAL.get(x).getStatus());
					view.cardAL.get(x).getImage();
					server.sendObject(new CardValued(view.cardAL.get(x)));
			}					
			}
			sl.getLogger().info(view.cardAL.get(x).toString());
		}
	}
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void updatePoints(){
		view.scorePL1 = 0;
		view.scorePL2 = 0;
		int yetiPL1 = 0;
		int yetiPL2 = 0;
		sl.getLogger().info("Update der Punkte gestartet");
		for(int x = 0; x<view.cardAL.size();x++){
			if(view.cardAL.get(x).getStatus().equals(Status.gewertet) && view.cardAL.get(x).getOwner().equals(clientOwner)){
				switch(view.cardAL.get(x).getType()){
				case "Rieb": 	view.scorePL1 += 2;
				continue;
				case "Prof":	view.scorePL1 += 1;
				continue;
				case "Lemming": view.scorePL1 += 4;
				continue;
				case "Yeti": 	yetiPL1++;
				continue;
				case "Dino":	view.scorePL1 += view.cardAL.get(x).getNumberofeyes();
				continue;
				}
			}
			
			if(view.cardAL.get(x).getStatus().equals(Status.gewählt) && view.cardAL.get(x).getOwner().equals(clientOwner)){
				switch(view.cardAL.get(x).getType()){
				case "Rieb": 	view.scorePL1 += 1;
				continue;
				case "Prof":	view.scorePL1 += 1;
				continue;
				case "Lemming": view.scorePL1 += 1;
				continue;
				case "Yeti": 	view.scorePL1 += 1;
				continue;
				case "Dino":	view.scorePL1 += 1;
				continue;
				}
			}
			
			if(view.cardAL.get(x).getStatus().equals(Status.todungesetzt) && view.cardAL.get(x).getOwner().equals(clientOwner)){
				view.scorePL1 -= 1;
				continue;
			}
			
			if(view.cardAL.get(x).getStatus().equals(Status.gewertet) && !view.cardAL.get(x).getOwner().equals(clientOwner)){
				switch(view.cardAL.get(x).getType()){
				case "Rieb": 	view.scorePL2 += 2;
				continue;
				case "Prof":	view.scorePL2 += 1;
				continue;
				case "Lemming": view.scorePL2 += 4;
				continue;
				case "Yeti": 	yetiPL2++;
				continue;
				case "Dino":	view.scorePL2 += view.cardAL.get(x).getNumberofeyes();
				continue;
				}
			}
			
			if(view.cardAL.get(x).getStatus().equals(Status.gewählt) && !view.cardAL.get(x).getOwner().equals(clientOwner)){
				switch(view.cardAL.get(x).getType()){
				case "Rieb": 	view.scorePL2 += 1;
				continue;
				case "Prof":	view.scorePL2 += 1;
				continue;
				case "Lemming": view.scorePL2 += 1;
				continue;
				case "Yeti": 	view.scorePL2 += 1;
				continue;
				case "Dino":	view.scorePL2 += 1;
				continue;
				}
			}
			
			if(view.cardAL.get(x).getStatus().equals(Status.todungesetzt) && !view.cardAL.get(x).getOwner().equals(clientOwner)){
				view.scorePL2 -= 1;
				continue;
			}
			sl.getLogger().info("Punkte könnten berechnet werden für:" + view.cardAL.get(x).toString());
		}
		if(yetiPL1 == 1){
			view.scorePL1 += 1;
		}else if(yetiPL1>1){
			view.scorePL1 += (yetiPL1*3);
		}
		
		if(yetiPL2 ==1){
			view.scorePL2 += 1;
		}else if(yetiPL2>1){
			view.scorePL2 += (yetiPL2*3);
		}
		
		view.labelPL1.setText(""+view.scorePL1);
		view.labelPL2.setText(""+view.scorePL2);
		server.sendObject(new PointUpdate(view.scorePL1, view.scorePL2));
		
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void updatePointsFromOtherClient(int points1, int points2){
		view.scorePL1 = points1;
		view.scorePL2 = points2;
		view.labelPL1.setText(""+view.scorePL1);
		view.labelPL2.setText(""+view.scorePL2);
	}
	 /**
	  * @author Nicola Burri
	  * 
	  */
	
	public void setDiesDisabled(boolean disabled){
		for(int x = 0; x < this.getDies().size(); x++){
			this.getDies().get(x).getImageView().setDisable(disabled);
		}
	}
	 /**
	  * @author Nicola Burri
	  * 
	  */
	
	public void selectAllDies(){
		for(int x = 0; x < this.getDies().size(); x++){
			this.getDies().get(x).click();
		}
	}
	 /**
	  * @author Nicola Burri
	  * @param 
	  */
	
	public void disableCards(){
		for(int x = 0; x< view.cardAL.size(); x++){
			view.cardAL.get(x).getImage().setDisable(true);
			view.cardAL.get(x).getImage().setOpacity(0.5);
		}
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public boolean checkGameContinue(){
		int countFreeCards = 0;
		for(int x = 0; x<view.cardAL.size(); x++){
			if(view.cardAL.get(x).getStatus() == Status.frei && !view.cardAL.get(x).getType().equals(cardType.Tod.toString())){
				
				countFreeCards++;
			
			}
		}
		if(countFreeCards <= 5){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * @author Patrick Tüscher / Marco Kunz
	 * 
	 */
	public void checkWinner(){
		server.sendObject(new newScore(view.scorePL1));
		view.finalScore1.setText(view.t.getString("Text.score")+" "+view.scorePL1+" "+ view.t.getString("Text.points"));
		if(view.scorePL1 > view.scorePL2){
			if(view.t.getCurrentLocaleString().equals("de")){
			view.winnerIV.setImage(view.winner_de);
			}else{
				view.winnerIV.setImage(view.winner);
			}
				
			view.scoreHbox.getChildren().addAll(view.winnerIV);
			view.scoreHbox.getChildren().addAll(view.finalScore1);
			
				
		}else if(view.scorePL1 < view.scorePL2){
			if(view.t.getCurrentLocaleString().equals("de")){
				view.loserIV.setImage(view.loser_de);
				}else{
					view.loserIV.setImage(view.loser);
					}
			view.scoreHbox.getChildren().addAll(view.loserIV);
			view.scoreHbox.getChildren().addAll(view.finalScore1);
		}else{
			if(view.t.getCurrentLocaleString().equals("de")){
				view.drawIV.setImage(view.draw_de);}
				else{
					view.drawIV.setImage(view.draw);
					view.scoreHbox.getChildren().addAll(view.drawIV);
					view.scoreHbox.getChildren().addAll(view.finalScore1);
		}
		}
		setGameFinishedScene();
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void playerQuit(){
		view.scorePL1 = 0;
		if(view.scorePL2<4){
			view.scorePL2 = 3;
		}
		server.sendObject(new PointUpdate(view.scorePL1, view.scorePL2));
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void resetCardHandler(){
		for(int x = 0;x<31;x++){	
		final int d = x;
		if(!view.cardAL.get(x).getType().equals(cardType.Tod.toString())){
			view.cardAL.get(x).getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){
				
				@Override
				public void handle(MouseEvent arg0){
					view.cardAL.get(d).click(clientOwner);
					view.cardAL.get(d).setOwner(clientOwner);
					for(int y = 0; y < view.cardAL.get(d).getDie().size(); y++){
						if(view.diesPL1.contains(view.cardAL.get(d).getDie().get(y))){
							view.diesPL1.get(view.diesPL1.indexOf(view.cardAL.get(d).getDie().get(y))).setUsed(true);
							view.diesPL1.get(view.diesPL1.indexOf(view.cardAL.get(d).getDie().get(y))).click();
							if(view.cardAL.get(d).getDie().size() == 2){
								view.diesPL1.get(view.diesPL1.lastIndexOf(view.cardAL.get(d).getDie().get(y))).setUsed(true);
								view.diesPL1.get(view.diesPL1.lastIndexOf(view.cardAL.get(d).getDie().get(y))).click();
							}
							System.out.println(view.cardAL.get(d).getDie().get(y) + "is used");
						}
					}
					if(view.cardAL.get(d).getType().equals("Dino")){
						for(int y = 1; y < view.diesPL1.size(); y++){
						view.diesPL1.get(y).setUsed(true);
						view.diesPL1.get(y).click();
						}
					}
					model.startCardChecker(view.cardAL, view.diesPL1);
					server.sendObject(new CardClick(view.cardAL.get(d)));
					view.b_finishGame.setDisable(false);
				}
			});	
			
			}else{
				view.cardAL.get(x).getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						if(view.cardAL.get(d).getStatus().equals(Status.todgesetzt)){
							removeCardDeath(view.cardAL, view.cardAL.get(d));


							
						}
						
						view.cardAL.get(d).setStatus(Status.todungesetzt);
						view.cardAL.get(d).click(clientOwner);


						model.checkCardsToChooseDeath(view.cardAL);
						
						if(!model.checkCardsToChooseDeath(view.cardAL)){
							view.b_finishGame.setDisable(false);
						}
						
						server.sendObject(new CardClick(view.cardAL.get(d)));
						
		
						
						for(int t = 0;t<31;t++){
							final int a = t;
							view.cardAL.get(t).getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){

								@Override
								public void handle(MouseEvent arg0){
									view.cardAL.get(d).setStatus(Status.todgesetzt);
									server.sendObject(new CardClick(view.cardAL.get(d)));
									view.cardAL.get(a).setStatus(Status.tod);
									view.cardAL.get(a).setcardDeath(view.cardAL.get(d));
									view.cardAL.get(a).getImage();

									
									server.sendObject(new CardDeath(view.cardAL.get(a)));
									updatePoints();
									disableCards();
									view.b_finishGame.setDisable(false);
									
								}
							});
						}
							
			
					}
		});		
		}
			}
	}
	
	/**
	 * @author Kevin Trottmann
	 */
	 public void updateView(){
		 	view.t = sl.getTranslator();
		 	view.b_register.setText(view.t.getString("Button.Register"));
			view.b_login.setText(view.t.getString("Button.Login"));
			view.b_quitGame.setText(view.t.getString("Button.QuitGame"));
			view.lb_username.setText(view.t.getString("Label.UserName"));
			view.lb_password.setText(view.t.getString("Label.Password"));
			//view.lb_chooseLanguage.setText(view.t.getString("Label.Language"));
			view.b_gameCreate.setText(view.t.getString("Button.newGame"));
			view.b_backStatistic.setText(view.t.getString("Button.Stats"));
			view.b_gameJoin.setText(view.t.getString("Button.JoinGame"));
			view.b_backLobby.setText(view.t.getString("Button.Logout"));
			view.b_backGame.setText(view.t.getString("Button.Aufgeben"));
			view.b_backRules.setText(view.t.getString("Button.Back"));
			view.b_backStatistic.setText(view.t.getString("Button.Back"));
			view.b_rules.setText(view.t.getString("Button.Rules"));
			view.b_statistic.setText(view.t.getString("Button.Stats"));
			view.b_roll.setText(view.t.getString("Button.roll"));
			view.b_sendchat.setText(view.t.getString("Button.send"));
			view.chatInputWindow.setText(view.t.getString("TextField.click"));
			view.b_finishGame.setText(view.t.getString("Button.Fertig"));
			view.primaryStage.setTitle(view.t.getString("Stage.title"));
			view.b_languageChange.setText(view.t.getString("Button.languageChange"));

				}
	 /**
	  * @author Nicola Burri
	  * @param hu Highscore
	  */
	 public void updateTable(HighscoreUpdate hu){
		 Platform.runLater(new Runnable(){
				@Override
				public void run(){
					view.updateTable(hu.getScoreValues(), hu.getNameValues(), hu.getDateValues());
					view.primaryStage.setScene(view.sceneStatistic);
				}
			});
		 
		 
	 }
	 
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	 public boolean profCard(){
		 boolean b = false;
		 for(int x = 0; x < view.cardAL.size(); x++){
			 if(view.cardAL.get(x).getOwner() != null && view.cardAL.get(x).getType().equals("Prof") && view.cardAL.get(x).getOwner().equals(clientOwner) && view.cardAL.get(x).getStatus().equals(Status.gewertet)){
				 b = true;
			 }
		 }
		 return b;
	 }
	 
	/**
	 * @author Patrick Tüscher
	 * 
	 */	 
	 public void valueProfCard(){
		 model.resetPlayerRoll();
		 int würfel = 0;
		 for(int x = 0; x < view.cardAL.size(); x++){
			 if(view.cardAL.get(x).getOwner() != null && view.cardAL.get(x).getType().equals("Prof") && view.cardAL.get(x).getOwner().equals(clientOwner) && view.cardAL.get(x).getStatus().equals(Status.gewertet)){
				 würfel++;
			 }
		 }
		 

		 final int würfelFinal = würfel;
		 view.b_roll.setDisable(false);
		 view.turnPL2.setVisible(false);
		 view.turnPL1.setVisible(true);
		 view.b_finishGame.setDisable(true);
		 Image profWerten = new Image("images/profValue.png");
		 Image profWerten_de= new Image("images/profWerten.png");
		 if(view.t.getCurrentLocaleString().equals("de")){
			 view.turnPL1.setImage(profWerten_de);
		 }else{
			 view.turnPL1.setImage(profWerten);
		 }
		 
		 for(int x = 0; x < view.diesPL1.size(); x++){
			 if(x == 0){
			 view.diesPL1.get(x).getImageView().setOpacity(0);
			 }else{
			 
			 if(x <= würfel){
				 view.diesPL1.get(x).resetWürfel();
				 System.out.println(view.diesPL1.get(x));
				 
			 }else{
				 view.diesPL1.get(x).getImageView().setOpacity(0);
			 }
		 }
		 
			view.b_roll.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					roll();
					model.incrementPlayerRoll();
					System.out.println(getDies());
					würfelProf(würfelFinal);
					}
			});
			
			view.b_finishGame.setOnAction(new EventHandler<ActionEvent>(){
				
				
				//@Override
				public void handle(ActionEvent arg0) {
					view.b_finishGame.setDisable(true);
					server.sendObject(new GameFinished());
					resetFinishButton();
				}

			});
		 
		 }
	 }

	/**
	 * @author Patrick Tüscher
	 * 
	 */
	 public void würfelProf(int würfel){
		 int bestWürfel = 0;
		 if(model.getPlayerRollCounter()>0){
			 view.b_roll.setDisable(true);
		 
		 
		 for(int x = 0; x < würfel; x++){
			 if(view.diesPL1.get(x+1).getCurrentNumberofeyes()> bestWürfel){
				 bestWürfel = view.diesPL1.get(x).getCurrentNumberofeyes();
			 }

		 }
		 resetDieButton();
		 updatePoints();
		 view.scorePL1 += bestWürfel;
		 view.scorePL1 -= würfel;
		 view.labelPL1.setText(""+view.scorePL1);
		 server.sendObject(new PointUpdate(view.scorePL1, view.scorePL2));
		 view.b_finishGame.setDisable(false);
	 }
	 }
	 
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	 public void resetDieButton(){
			view.b_roll.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent arg0) {
					roll();
					model.incrementPlayerRoll();
					System.out.println(getDies());
					model.startCardChecker(view.cardAL, getDies());
					checkTurn();
					}
			});
	 }
	 
	 /**
	 * @author Patrick Tüscher
	 * 
	 */
	 public void resetFinishButton(){
			view.b_finishGame.setOnAction(new EventHandler<ActionEvent>(){
				
				
				//@Override
				public void handle(ActionEvent arg0) {

					model.resetPlayerRoll();
					resetCardHandler();
					System.out.println(getDies());
					disableCards();
					changeCardsToElected();
					if(checkGameContinue()){
						updatePoints();	
						server.sendObject(new ClientTurn(false));
					}else{
						if(!profCard()){
						server.sendObject(new GameFinished());
						}
						else{
							Platform.runLater(new Runnable(){
								@Override
								public void run(){
									valueProfCard();
								}
							});


						}
					}
					}
			});
	 }
	 
		/**
		 * @author Patrick Tüscher
		 * 
		 */
		public void removeCardDeath(ArrayList<Card> cardAL, Card cardTod){
			for(int x = 0; x<31; x++){
				if(cardAL.get(x).getcardDeath() == null){
					
				}else if(cardAL.get(x).getcardDeath().equals(cardTod)){

			
							cardAL.get(x).setcardDeath(null);
							cardAL.get(x).setStatus(Status.gewertet);
							cardAL.get(x).getImage();

							server.sendObject(new CardValued(view.cardAL.get(x)));

					break;
				}
			}


			
		}
		
		/**
		 * @author Patrick Tüscher
		 * 
		 */
		public void setupLabel(){
			 if(view.t.getCurrentLocaleString().equals("de")){
				 view.turnPL1.setImage(view.turn1_de);
				 view.turnPL2.setImage(view.turn2_de);
			 }else{
				 view.turnPL1.setImage(view.turn1);
				 view.turnPL2.setImage(view.turn2);
			 }
		}
		
		
	 
	 
}
