package client;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import message.CardClick;
import message.ChatMessage;
import message.ClientLogout;
import message.ClientTurn;
import message.GameComplete;
import message.WürfelRoll;
import message.initiateNewGame;


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
			view.primaryStage.setScene(view.sceneLobby);
			sl.getLogger().info("Change to Lobby Scene");
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
					view.cardAL.get(d).clickMe();
					server.sendObject(new CardClick(view.cardAL.get(d)));
				}
			});	
		}
		
		///////////////// EventHandler chosen cubes //////////////////////////
		
		for(int i = 0; i < view.WürfelPL1.size();i++){
			final int d = i;
			view.WürfelPL1.get(i).getImageView().setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					view.WürfelPL1.get(d).click();
					
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
						

					}
				});
		
				
		///////////////// EventHandler FertigButton - LoginScene //////////////////////////
				view.b_fertigGame.setOnAction(new EventHandler<ActionEvent>(){
					
					
					//@Override
					public void handle(ActionEvent arg0) {

						model.resetPlayerRoll();
//						view.b_fertigGame.setDisable(true);
//						view.b_würfeln.setDisable(true);
//						selectAllWürfel();
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
				card.clickOther();
				view.cardAL.set(x, card);
				view.updateCards();
				break;
			}
		}
	}
	
	
	public void setOpClientTurn(){
		model.resetPlayerRoll();
//		Platform.runLater(new Runnable(){
//			@Override
//			public void run() {
//				view.topPaneGame.setId("topPaneGamePL2");
//			}
//		});
//		view.turnPL1.setVisible(false);
//		view.turnPL2.setVisible(true);
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
		view.select_label.setText(connectionName + " ist online");
		view.innerPaneLobby.getChildren().remove(view.select_label);
		view.innerPaneLobby.add(view.select_label, 2, 8);
	}
	public void setUpGame(){
		setUpDie();
	}
	
	public void getAlert(){
   	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Login failure");
	alert.setHeaderText("Login not sucessfull");
	alert.showAndWait();
	}
	
	public void setUpDie(){
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				for(int x = 0; x < view.WürfelPL1.size(); x++){
					view.WürfelPL1.get(x).resetWürfel();
					
					}
				setWürfelDisabled(true);
			}
		});
		
	}
	
	public void würfeln(){
		for(int x = 0; x < view.WürfelPL1.size(); x++){
			if(!view.WürfelPL1.get(x).isSelected()){

			view.WürfelPL1.get(x).roll();
			}
			
			}
		
		server.sendObject(new WürfelRoll(view.WürfelPL1));

	}
	
	public ArrayList<Würfel> getWürfel(){
		return view.WürfelPL1;
	}


	public synchronized void initiateTurn() {
		new Thread(new Runnable(){
		@Override public void run(){
			
		
		model.resetPlayerRoll();


	
		
		setUpDie();
		view.b_würfeln.setDisable(false);
		
		while(model.getPlayerRollCounter() == 0){
		setWürfelDisabled(true);
		view.b_fertigGame.setDisable(true);

		}
		
		while(model.getPlayerRollCounter() < 3 && !model.allWürfelSelected(view.WürfelPL1)){
		setWürfelDisabled(false);	
		view.b_würfeln.setDisable(false);
		view.b_fertigGame.setDisable(false);
		
		}
		System.out.println("hier");
//		selectAllWürfel();
		view.b_würfeln.setDisable(true);
		
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
			model.startCardChecker(view.cardAL, view.WürfelPL1);

		};
		
//		model.startCardChecker(view.cardAL, view.WürfelPL1);
		
			});
			}
		}).start();
	}
	
	
	public void setWürfelDisabled(boolean disabled){
		for(int x = 0; x < view.WürfelPL1.size(); x++){
			view.WürfelPL1.get(x).getImageView().setDisable(disabled);
		}
	}
	public void selectAllWürfel(){
		for(int x = 0; x < view.WürfelPL1.size(); x++){
			view.WürfelPL1.get(x).click();
		}
//		server.sendObject(new WürfelRoll(view.WürfelPL1));
	}
	


	

}
