package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.security.ntlm.Client;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import message.CardClick;
import message.ChatMessage;
import message.ClientLogout;
import message.ClientTurn;
import message.GameComplete;
import message.WürfelRoll;
import message.initiateNewGame;
import message.newAccountMessage;
import tools.Translator;


public class ClientController {


	protected ServiceLocator sl;       
	protected ClientView view;
	protected ClientModel model;
	protected ServerListener server;



	
	public ClientController(ClientView view, ClientModel model){
		this.view = view;
		this.model = model;
		sl = ServiceLocator.getServiceLocator();
		ServerListener.controller = this;

		
		
		///////////////// LOGIN FENSTER //////////////////////////
		
		
		// EventHandler Language Change Button - LoginScene
				view.b_languageChange.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						if(view.t.getCurrentLocaleString().equals("en")) {
			            	   //view.t = new Translator("de");
			            	   sl.getServiceLocator().setTranslator(new Translator("de"));
			            	   sl.getLogger().info("Language changed to de");
			            	   view.turnPL1.setImage(view.turn1_de);
			            	   view.turnPL2.setImage(view.turn2_de);
			      			}
			     			else{
			 
			       			//view.t = new Translator("en");
			     			sl.getServiceLocator().setTranslator(new Translator("en"));
							view.turnPL1.setImage(view.turn1);
							view.turnPL2.setImage(view.turn2);
			               sl.getLogger().info("Language changed to en");
			     			} 
						updateView();
					}
				});
				
		// EventHandler LoginButton - LoginScene
		view.b_login.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				server = ServerListener.getServerListener();
				server.connect(new Account(view.tf_username.getText()));
				sl.getLogger().info("Change to Lobby Scene");
				

			}
		});
		
		
		
		
		
		// EventHandler RegisterButton - LoginScene
		view.b_register.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			server = ServerListener.getServerListener();
			server.connect();
			server.sendObject(new newAccountMessage(new Account(view.tf_username.getText())));
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
		
		
		
		
		
		///////////////// LOBBY FENSTER //////////////////////////
				
		
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
			view.primaryStage.setScene(view.sceneStatistik);
			sl.getLogger().info("Change to Statistik Scene");
			
			   BufferedReader br = null;
			    try {
			        br = new BufferedReader(new FileReader(new File("src/server/Highscore.txt")));
			        String line = null;
			        
			        while((line = br.readLine()) != null) {
			        	
			            String[] parts = line.split(",");
			            System.out.println( parts[0]);
			            System.out.println( parts[1]);
			            System.out.println( parts[2]);
//			            view.userNameCol.cellFactoryProperty(parts[0]);
			            
			        }
			    } catch(FileNotFoundException e) {
			        e.printStackTrace();
			    } catch(IOException e) {
			        e.printStackTrace();
			    } finally {
			        if(br != null) {
			            try {
			                br.close();
			            } catch(IOException e) {
			                e.printStackTrace();
			            }
			        }
			    }
			    
			
			}		
		});
			
		
		// EventHandler RegelnButton - LobbyScene
		view.b_rules.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneRegeln);
			sl.getLogger().info("Change to Regeln Scene");
			}		
		});
				
		
		// EventHandler SpielErstellenButton - LobbyScene !!!!! to be difined
		view.b_spielErstellen.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneGame);
			sl.getLogger().info("Change to Game Scene");
			server.sendObject(new initiateNewGame());
			}	
		});
				
		
		// EventHandler SpielBeitretenButton - LobbyScene !!!!! to be difined
		view.b_spielBeitreten.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			server.sendObject(new GameComplete());
			view.primaryStage.setScene(view.sceneGame);
			sl.getLogger().info("Change to Game Scene");
			}		
		});	
			
		
		///////////////// STATISTIK FENSTER //////////////////////////
		
		
		// EventHandler ZurückButton - StatistikScene
		view.b_backStatistik.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);
				sl.getLogger().info("Change to Lobby Scene");
			}
		});
		
		
	     ///////////////// REGELN FENSTER //////////////////////////
		
		
		// EventHandler ZurückButton - RegelnScene
		view.b_backRegeln.setOnAction(new EventHandler<ActionEvent>(){

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
					if (ClientView.currentRuleImage <view.regeln.length-1){
					view.regelnView.setImage(view.regeln[ClientView.currentRuleImage+1]);
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
					view.regelnView.setImage(view.regeln[ClientView.currentRuleImage-1]);
					ClientView.currentRuleImage--;
					sl.getLogger().info("Change RuleImage Minus");
					}
					}
					
				});
	
		
		///////////////// GAME FENSTER //////////////////////////
			
		
		// EventHandler ZurückButton - GameScene
		view.b_backGame.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);
				sl.getLogger().info("Change to Lobby Scene");
			}
		});

		//EventHandler Würfel  - GameScrene
		view.b_würfeln.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				würfeln();
//				cardChecker cc = new cardChecker();
//				sl.getLogger().info("cardChecker erstellt");
//				cc.cardCheckforDisable(view.cardAL, view.WürfelPL1);
//				sl.getLogger().info("Cards gecheckt");
				model.incrementPlayerRoll();
				System.out.println(getWürfel());
				model.startCardChecker(view.cardAL, getWürfel());
				checkTurn();
				}
		});
	
		for(int x = 0;x<31;x++){
			
		///////////////// EventHandler zoom cards //////////////////////////
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
			
			///////////////// EventHandler chosen cards //////////////////////////
			view.cardAL.get(x).getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){
				
				@Override
				public void handle(MouseEvent arg0){
					view.cardAL.get(d).click();
					for(int y = 0; y < view.cardAL.get(d).getWürfel().size(); y++){
						if(view.WürfelPL1.contains(view.cardAL.get(d).getWürfel().get(y))){
							view.WürfelPL1.get(view.WürfelPL1.indexOf(view.cardAL.get(d).getWürfel().get(y))).setUsed(true);
							view.WürfelPL1.get(view.WürfelPL1.indexOf(view.cardAL.get(d).getWürfel().get(y))).click();
							System.out.println(view.cardAL.get(d).getWürfel().get(y) + "is used");
						}
					}
					if(view.cardAL.get(d).getType().equals("Dino")){
						for(int y = 0; y < view.WürfelPL1.size(); y++){
						view.WürfelPL1.get(y).setUsed(true);
						view.WürfelPL1.get(y).click();
						}
					}
					model.startCardChecker(view.cardAL, view.WürfelPL1);
					server.sendObject(new CardClick(view.cardAL.get(d)));
				}
			});	
		}
		
		///////////////// EventHandler chosen cubes //////////////////////////
		
		for(int i = 0; i < this.getWürfel().size();i++){
			final int d = i;
			this.getWürfel().get(i).getImageView().setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					getWürfel().get(d).click();
					
					
				}
				}
			);
			
		}
		

		
		///////////////// EventHandler LoginButton - LoginScene //////////////////////////
				view.b_sendchat.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						server.sendObject(new ChatMessage(view.chatInputWindow.getText()));
						view.chatInputWindow.clear();
//						model.startCardChecker(view.cardAL, getWürfel());
						

					}
				});
		
				
		///////////////// EventHandler FertigButton - LoginScene //////////////////////////
				view.b_fertigGame.setOnAction(new EventHandler<ActionEvent>(){
					
					
					//@Override
					public void handle(ActionEvent arg0) {

						model.resetPlayerRoll();
						System.out.println(getWürfel());
						disableCards();
						server.sendObject(new ClientTurn(false));
						}
				});
			
	
	
	
			///////////////// EventHandler FertigButton - LoginScene //////////////////////////
//			view.b_backLoginFailed.setOnAction(new EventHandler<ActionEvent>(){
//				
//				//@Override
//				public void handle(ActionEvent arg0) {
//					view.primaryStage.setScene(view.sceneLogin);
//				}
//			});
//		
		}
	
	

	
	public void setLobbyScene(){
		this.view.primaryStage.setScene(this.view.sceneLobby);
	}
	
	public void setLoginFailedScene(){
		this.view.primaryStage.setScene(this.view.sceneLoginFailed);
		this.view.primaryStage.centerOnScreen();
	}
	
	public void setLoginScene(){
		this.view.primaryStage.setScene(this.view.sceneLogin);
		this.view.tf_username.clear();
	}
	
	public void addNewMessage(String s){
		view.chatWindow.appendText(s+"\n");
	}
	
	
	public void setOpponentDi(ArrayList<Würfel> würfel){
		sl.getLogger().info("Opponents Die are being set");
			
		for(int x = 0;x<view.WürfelPL2.size();x++){
			view.WürfelPL2.get(x).setAktAugenzahl(würfel.get(x).getAktAugenzahl());
		
	
		}
		
		
		
//		view.WürfelPL2.clear();
//		view.WürfelPL2.addAll(würfel);
//		view.innertopPaneGame.getChildren().clear();
//		view.innertopPaneGame.getChildren().add(view.turnPL2);
//		for(int x = 0;x<view.WürfelPL2.size();x++){
//		if(view.WürfelPL2.get(x).isSelected())
//			view.WürfelPL2.get(x).getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,5), 30, 0, 0, 0);");
//		view.innertopPaneGame.getChildren().add(view.WürfelPL2.get(x).getImageView());
//		}
//		view.innertopPaneGame.getChildren().add(view.labelPL2);
		sl.getLogger().info("Opponent Die changed");
	}
	
	
	
	public void opponentSelectCard(Card card){
		for(int x = 0; x < view.cardAL.size();x++){
			if(view.cardAL.get(x).equals(card)){
				view.cardAL.get(x).clickOther();
//				card.clickOther();
//				view.cardAL.set(x, card);
//				view.updateCards();
				break;
			}
		}
	}
	
	
	public void setOpClientTurn(){
		model.resetPlayerRoll();
		view.turnPL1.setVisible(false);
		view.turnPL2.setVisible(true);
		view.b_würfeln.setDisable(true);
		view.b_fertigGame.setDisable(true);
			
	
	}
	
	
	public void setGameAvImageOnOff(boolean on){
		if(on){
			this.view.gai.setImageOn();
			this.view.b_spielErstellen.setDisable(true);
			this.view.b_spielBeitreten.setDisable(false);
		}
		else{
			this.view.b_spielErstellen.setDisable(false);
			this.view.b_spielBeitreten.setDisable(true);
			this.view.gai.setImageOff();
		}
		view.innerPaneLobby.add(view.gai.getImage(), 2, 7);
	}
	public void setAvailableLabel(String connectionName){
		view.select_label.setText(connectionName + " " + view.t.getString("Text.isonline"));
		view.innerPaneLobby.getChildren().remove(view.select_label);
		view.innerPaneLobby.add(view.select_label, 2, 8);
	}
	public void setUpGame(){
		setUpDie();
	}
	
	public void getAlert(){
   	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle("Login failure");
	alert.setHeaderText("Login failed");
	alert.initStyle(StageStyle.TRANSPARENT);
	alert.setContentText("Oops... your account couldn't be verified. Please make sure you enter valid credentials");
	alert.showAndWait();
	this.setLoginScene();
	}
	
	public void setUpDie(){
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				for(int x = 0; x < getWürfel().size(); x++){
					getWürfel().get(x).resetWürfel();
					
					}
				setWürfelDisabled(true);
			}
		});
		
	}
	
	public void würfeln(){
		sl.getLogger().info("Würfel-Methode aufgerufen");
		for(int x = 0; x < this.getWürfel().size(); x++){
			if(!this.getWürfel().get(x).isSelected()){

			this.getWürfel().get(x).roll();
			}
			
			}
		
		server.sendObject(new WürfelRoll(this.getWürfel()));
		

	}
	
	public ArrayList<Würfel> getWürfel(){
		return view.getWürfelPL1();
	}


	public void initiateTurn() {
		view.turnPL1.setVisible(true);
		view.turnPL2.setVisible(false);
		model.resetPlayerRoll();
		setUpDie();
		view.b_würfeln.setDisable(false);
//		while(model.getPlayerRollCounter() == 0){
//		setWürfelDisabled(true);
//		view.b_fertigGame.setDisable(true);
//
//		}
//		
//		while(model.getPlayerRollCounter() < 3 && !model.allWürfelSelected(this.getWürfel())){
//		setWürfelDisabled(false);	
//		view.b_würfeln.setDisable(false);
//		view.b_fertigGame.setDisable(false);
//		
//		}
//		System.out.println("hier");
//		selectAllWürfel();
//		view.b_würfeln.setDisable(true);


		
	}
	
	public void checkTurn(){
		if(model.getPlayerRollCounter() == 0){
			setWürfelDisabled(true);
			view.b_fertigGame.setDisable(true);
		}else if(model.getPlayerRollCounter() < 3 && !model.allWürfelSelected(getWürfel())){
			setWürfelDisabled(false);
			view.b_würfeln.setDisable(false);
			view.b_fertigGame.setDisable(false);
		}else{
			selectAllWürfel();
			view.b_würfeln.setDisable(true);
			
		}
	}
	
	
	public void bewerteCards(){
		for(int x = 0; x>view.cardAL.size(); x++){
			
		}
	}
	
	
	public void setWürfelDisabled(boolean disabled){
		for(int x = 0; x < this.getWürfel().size(); x++){
			this.getWürfel().get(x).getImageView().setDisable(disabled);
		}
	}
	
	
	public void selectAllWürfel(){
		for(int x = 0; x < this.getWürfel().size(); x++){
			this.getWürfel().get(x).click();
		}
//		server.sendObject(new WürfelRoll(view.WürfelPL1));
	}
	
	public void disableCards(){
		for(int x = 0; x< view.cardAL.size(); x++){
			view.cardAL.get(x).getImage().setDisable(true);
		}
	}
	
	 public void updateView(){
		 	view.t = sl.getTranslator();
			view.b_register.setText(view.t.getString("Button.Register"));
			view.b_login.setText(view.t.getString("Button.Login"));
			view.b_quitGame.setText(view.t.getString("Button.QuitGame"));
			view.lb_username.setText(view.t.getString("Label.UserName"));
			view.lb_password.setText(view.t.getString("Label.Password"));
			view.lb_chooseLanguage.setText(view.t.getString("Label.Language"));
			view.b_spielErstellen.setText(view.t.getString("Button.newGame"));
			view.b_backStatistik.setText(view.t.getString("Button.Stats"));
			view.b_spielBeitreten.setText(view.t.getString("Button.JoinGame"));
			view.b_backLobby.setText(view.t.getString("Button.Logout"));
			view.b_backGame.setText(view.t.getString("Button.Back"));
			view.b_backRegeln.setText(view.t.getString("Button.Back"));
			view.b_backStatistik.setText(view.t.getString("Button.Back"));
			view.b_rules.setText(view.t.getString("Button.Rules"));
			view.b_statistic.setText(view.t.getString("Button.Stats"));
			view.userNameCol.setText(view.t.getString("TableColumn.UserNameCol"));
			view.scoreCol.setText(view.t.getString("TableColumn.Score"));
			view.dateCol.setText(view.t.getString("TableColumn.date"));
			view.b_würfeln.setText(view.t.getString("Button.roll"));
			view.b_sendchat.setText(view.t.getString("Button.send"));
			view.chatInputWindow.setText(view.t.getString("TextField.click"));
			view.b_fertigGame.setText(view.t.getString("Button.Fertig"));
			view.primaryStage.setTitle(view.t.getString("Stage.title"));
			view.b_languageChange.setText(view.t.getString("Button.languageChange"));
			
		}
	 
	

}
