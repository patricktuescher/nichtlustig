package client;

import java.awt.Color;

import client.ClientModel;
import client.ClientView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class ClientController {


	
	ClientView view;
	ClientModel model;
	
	public ClientController(ClientView view, ClientModel model){
		this.view = view;
		this.model = model;
		
		
		
		///////////////// LOGIN FENSTER //////////////////////////
		
		
		// EventHandler LoginButton - LoginScene
		view.b_login.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);	
			}
			
		});
		
		
		// EventHandler RegisterButton - LoginScene
		view.b_register.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneLobby);	
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
			view.primaryStage.setScene(view.sceneLogin);	
			}
					
		});	
		
		
		// EventHandler StatistikButton - LobbyScene
		view.b_statistic.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneStatistik);	
			}
					
		});
			
		
		// EventHandler RegelnButton - LobbyScene
		view.b_rules.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneRegeln);	
			}
					
		});
				
		
		// EventHandler SpielErstellenButton - LobbyScene !!!!! to be difined
		view.b_spielErstellen.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneGame);	
			}
					
		});
				
		
		// EventHandler SpielBeitretenButton - LobbyScene !!!!! to be difined
		view.b_spielBeitreten.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
			view.primaryStage.setScene(view.sceneGame);	
			}
					
		});
			
			
		
		///////////////// STATISTIK FENSTER //////////////////////////
		
		// EventHandler ZurückButton - StatistikScene
		view.b_backStatistik.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);	
			}
			
		});
		
		
	///////////////// REGELN FENSTER //////////////////////////
		
		// EventHandler ZurückButton - RegelnScene
		view.b_backRegeln.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);	
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
					}
					}
					
				});
	
		
	///////////////// GAME FENSTER //////////////////////////
			
		
		// EventHandler ZurückButton - GameScene
		view.b_backGame.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);	
			}
			
		});

		//EventHandler Würfel  - GameScrene
		view.b_würfeln.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				// Würfel Pink
				int a = (int) (Math.random()*6);
				view.cubeViewPink.setImage(view.cubePink[a]);
				//Würfel white 1
				int b = (int) (Math.random()*5);
				view.cubeViewWhite1.setImage(view.cubeWhite1[b]);
				//Würfel white 2
				int c = (int) (Math.random()*5);
				view.cubeViewWhite2.setImage(view.cubeWhite2[c]);
				//Würfel red 1
				int d= (int) (Math.random()*5);
				view.cubeViewRed1.setImage(view.cubeRed1[d]);
				//Würfel red 2
				int e = (int) (Math.random()*5);
				view.cubeViewRed2.setImage(view.cubeRed2[e]);
				//Würfel black 1
				int f = (int) (Math.random()*5);
				view.cubeViewBlack1.setImage(view.cubeBlack1[f]);
				//Würfel black 2
				int g = (int) (Math.random()*5);
				view.cubeViewBlack2.setImage(view.cubeBlack2[g]);
			}
			
		});
	
		

		///////////////// EventHandler zoom cards //////////////////////////		
		
		//EventHandler Zoom cardRieb

		for(int i = 0; i<5; i++){
			final int d = i;
			
			view.cardRieb[d].setOnMouseEntered(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardRieb[d].toFront();
					view.cardRieb[d].setScaleX(2);
					view.cardRieb[d].setScaleY(2);
				}
					
			});
			
			view.cardRieb[d].setOnMouseExited(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardRieb[d].toBack();
					view.cardRieb[d].setScaleX(1);
					view.cardRieb[d].setScaleY(1);
				}
		
		
			});		
		}		
		
		
		//EventHandler Zoom cardYeti

		for(int i = 0; i<5; i++){
			final int d = i;
			
			view.cardYeti[d].setOnMouseEntered(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardYeti[d].toFront();
					view.cardYeti[d].setScaleX(2);
					view.cardYeti[d].setScaleY(2);
				}
					
			});
			
			view.cardYeti[d].setOnMouseExited(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardYeti[d].toBack();
					view.cardYeti[d].setScaleX(1);
					view.cardYeti[d].setScaleY(1);
				}
		
		
			});		
		}		
		
		//EventHandler Zoom cardLemming

		for(int i = 0; i<5; i++){
			final int d = i;
			
			view.cardLemming[d].setOnMouseEntered(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardLemming[d].toFront();
					view.cardLemming[d].setScaleX(2);
					view.cardLemming[d].setScaleY(2);
				}
					
			});
			
			view.cardLemming[d].setOnMouseExited(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardLemming[d].toBack();
					view.cardLemming[d].setScaleX(1);
					view.cardLemming[d].setScaleY(1);
				}
		
		
			});		
		}		
		
		
		//EventHandler Zoom cardProf

		for(int i = 0; i<5; i++){
			final int d = i;
			
			view.cardProf[d].setOnMouseEntered(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardProf[d].toFront();
					view.cardProf[d].setScaleX(2);
					view.cardProf[d].setScaleY(2);
				}
					
			});
			
			view.cardProf[d].setOnMouseExited(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardProf[d].toBack();
					view.cardProf[d].setScaleX(1);
					view.cardProf[d].setScaleY(1);
				}
		
		
			});		
		}		
		
		//EventHandler Zoom cardDino

		for(int i = 0; i<5; i++){
			final int d = i;
			
			view.cardDino[d].setOnMouseEntered(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardDino[d].toFront();
					view.cardDino[d].setScaleX(2);
					view.cardDino[d].setScaleY(2);
				}
					
			});
			
			view.cardDino[d].setOnMouseExited(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardDino[d].toBack();
					view.cardDino[d].setScaleX(1);
					view.cardDino[d].setScaleY(1);
				}
		
		
			});		
		}			

		
		//EventHandler Zoom cardTod

		for(int i = 0; i<6; i++){
			final int d = i;
			
			view.cardTod[d].setOnMouseEntered(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardTod[d].toFront();
					view.cardTod[d].setScaleX(2);
					view.cardTod[d].setScaleY(2);
				}
					
			});
			
			view.cardTod[d].setOnMouseExited(new EventHandler<MouseEvent>(){
		
				@Override
				public void handle(MouseEvent arg0){
					view.cardTod[d].toBack();
					view.cardTod[d].setScaleX(1);
					view.cardTod[d].setScaleY(1);
				}
		
		
			});		
		}	

		///////////////// EventHandler chosen cards //////////////////////////
		
		
		//EventHandler Choose cardRieb
		for(int i = 1; i<6; i++){
			final int d = i;
		view.cardRieb[i-1].setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
//				view.cardRieb[d-1].setRotate(180);
//				view.cardRieb[d-1].setOpacity(0.5);
		
				InnerShadow innerShadow = new InnerShadow();
				innerShadow.setOffsetX(2);
				innerShadow.setOffsetY(2);
				innerShadow.setChoke(0.5);				
				view.cardRieb[d-1].setEffect(innerShadow);
//				view.cardRieb[d-1].setId("shadow");
			}
			
		});
		}		
		
		//EventHandler Choose cardYeti
		for(int i = 1; i<6; i++){
			final int d = i;
		view.cardYeti[i-1].setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[d-1].setRotate(180);
			}
			
		});
		}
		
		//EventHandler Choose cardLemming
		for(int i = 1; i<6; i++){
			final int d = i;
		view.cardLemming[i-1].setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[d-1].setRotate(180);
			}
			
		});
		}		
		
		//EventHandler Choose cardProf
		for(int i = 1; i<6; i++){
			final int d = i;
		view.cardProf[i-1].setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardProf[d-1].setRotate(180);
			}
			
		});
		}		
		
		
		//EventHandler Choose cardDino
		for(int i = 1; i<6; i++){
			final int d = i;
		view.cardDino[i-1].setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
//				Image dinoP1 = new Image("images/Karte_Yeti_"+d+".png");
//				view.cardDino[d-1].setImage(dinoP1);
				view.cardDino[d-1].setRotate(180);
			}
			
		});
		}
		

		
		// EventHandler Chat send-Button
//		view.b_sendchat.setOnAction(new EventHandler<ActionEvent>(){
//
//			@Override
//			public void handle(ActionEvent arg0) {
//			view.chatWindow.setText("Fehler, deine Nachricht konnte nicht gesendet werden!");
//			view.chatWindow.insertText(2, "Fehler");
//			}
//					
//		});
		
		
		
		
		
	}

}
