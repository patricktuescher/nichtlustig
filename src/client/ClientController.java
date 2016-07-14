package client;

import java.awt.Color;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.ClientModel;
import client.ClientView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import message.*;


public class ClientController {


	protected ServiceLocator sl;       
	ClientView view;
	ClientModel model;
	ServerListener server;
	
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
			}	
		});
				
		
		// EventHandler SpielBeitretenButton - LobbyScene !!!!! to be difined
		view.b_spielBeitreten.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
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
				for(int x = 0; x < view.WürfelPL1.size(); x++){
					view.WürfelPL1.get(x).roll();
					server.sendObject(new WürfelRoll(view.WürfelPL1));
					}
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
						server.sendObject(new ClientTurn(false));
						view.turnPL1.setVisible(true);
						view.turnPL1.setVisible(false);
					}
				});
			
	}
	
	public void setLobbyScene(){
		this.view.primaryStage.setScene(this.view.sceneLobby);
	}
	public void setLoginScene(){
		this.view.primaryStage.setScene(this.view.sceneLogin);
		this.view.tf_username.clear();
	}
	
	public void addNewMessage(String s){
		view.chatWindow.appendText(s+"\n");
	}
	public void setOpponentDi(ArrayList<Würfel> würfel){
		view.WürfelPL2.clear();
		view.WürfelPL2.addAll(würfel);
		view.innertopPaneGame.getChildren().clear();
		for(int x = 0;x<view.WürfelPL2.size();x++){
		if(view.WürfelPL2.get(x).isSelected())
			view.WürfelPL2.get(x).getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,5), 30, 0, 0, 0);");
		view.innertopPaneGame.getChildren().addAll(view.WürfelPL2.get(x).getImageView());
		}
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
	

}
