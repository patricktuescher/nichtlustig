package client;

import client.ClientModel;
import client.ClientView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
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
		
	
		
	///////////////// GAME FENSTER //////////////////////////
			
		
		// EventHandler ZurückButton - GameScene
		view.b_backGame.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				view.primaryStage.setScene(view.sceneLobby);	
			}
			
		});

		//EventHandler Würfel - GameScrene
		
		
		view.b_würfeln.setOnAction(new EventHandler<ActionEvent>(){
		
			@Override
			public void handle(ActionEvent arg0) {
				int c = (int) (Math.random()*6);
				view.cubeView.setImage(view.cube[c]);
			}
			
		});
		
		
		//EventHandler Zoom cardRieb

		view.cardRieb[0].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[0].toFront();
				view.cardRieb[0].setScaleX(2);
				view.cardRieb[0].setScaleY(2);
			}
						
		});

		view.cardRieb[0].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[0].toBack();
				view.cardRieb[0].setScaleX(1);
				view.cardRieb[0].setScaleY(1);
			}
			
			
		});

		
		view.cardRieb[1].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[1].toFront();
				view.cardRieb[1].setScaleX(2);
				view.cardRieb[1].setScaleY(2);
			}
			
			
		});
		
		view.cardRieb[1].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[1].toBack();
				view.cardRieb[1].setScaleX(1);
				view.cardRieb[1].setScaleY(1);
			}
			
			
		});

		
		view.cardRieb[2].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[2].toFront();
				view.cardRieb[2].setScaleX(2);
				view.cardRieb[2].setScaleY(2);
			}
			
			
		});
		
		view.cardRieb[2].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[2].toBack();
				view.cardRieb[2].setScaleX(1);
				view.cardRieb[2].setScaleY(1);
			}
			
			
		});

		
		view.cardRieb[3].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[3].toFront();
				view.cardRieb[3].setScaleX(2);
				view.cardRieb[3].setScaleY(2);
			}
			
			
		});

		view.cardRieb[3].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[3].toBack();
				view.cardRieb[3].setScaleX(1);
				view.cardRieb[3].setScaleY(1);
			}
			
			
		});

		
		view.cardRieb[4].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[4].toFront();
				view.cardRieb[4].setScaleX(2);
				view.cardRieb[4].setScaleY(2);
			}
			
			
		});

		view.cardRieb[4].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardRieb[4].toBack();
				view.cardRieb[4].setScaleX(1);
				view.cardRieb[4].setScaleY(1);
			}
			
			
		});
		
		
		//EventHandler Zoom cardYeti

		view.cardYeti[0].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[0].toFront();
				view.cardYeti[0].setScaleX(2);
				view.cardYeti[0].setScaleY(2);
			}
						
		});

		view.cardYeti[0].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[0].toBack();
				view.cardYeti[0].setScaleX(1);
				view.cardYeti[0].setScaleY(1);
			}
			
			
		});

		
		view.cardYeti[1].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[1].toFront();
				view.cardYeti[1].setScaleX(2);
				view.cardYeti[1].setScaleY(2);
			}
			
			
		});
		
		view.cardYeti[1].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[1].toBack();
				view.cardYeti[1].setScaleX(1);
				view.cardYeti[1].setScaleY(1);
			}
			
			
		});

		
		view.cardYeti[2].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[2].toFront();
				view.cardYeti[2].setScaleX(2);
				view.cardYeti[2].setScaleY(2);
			}
			
			
		});
		
		view.cardYeti[2].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[2].toBack();
				view.cardYeti[2].setScaleX(1);
				view.cardYeti[2].setScaleY(1);
			}
			
			
		});

		
		view.cardYeti[3].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[3].toFront();
				view.cardYeti[3].setScaleX(2);
				view.cardYeti[3].setScaleY(2);
			}
			
			
		});

		view.cardYeti[3].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[3].toBack();
				view.cardYeti[3].setScaleX(1);
				view.cardYeti[3].setScaleY(1);
			}
			
			
		});

		
		view.cardYeti[4].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[4].toFront();
				view.cardYeti[4].setScaleX(2);
				view.cardYeti[4].setScaleY(2);
			}
			
			
		});

		view.cardYeti[4].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardYeti[4].toBack();
				view.cardYeti[4].setScaleX(1);
				view.cardYeti[4].setScaleY(1);
			}
			
			
		});
		
		
		//EventHandler Zoom cardLemming

		view.cardLemming[0].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[0].toFront();
				view.cardLemming[0].setScaleX(2);
				view.cardLemming[0].setScaleY(2);
			}
						
		});

		view.cardLemming[0].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[0].toBack();
				view.cardLemming[0].setScaleX(1);
				view.cardLemming[0].setScaleY(1);
			}
			
			
		});

		
		view.cardLemming[1].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[1].toFront();
				view.cardLemming[1].setScaleX(2);
				view.cardLemming[1].setScaleY(2);
			}
			
			
		});
		
		view.cardLemming[1].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[1].toBack();
				view.cardLemming[1].setScaleX(1);
				view.cardLemming[1].setScaleY(1);
			}
			
			
		});

		
		view.cardLemming[2].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[2].toFront();
				view.cardLemming[2].setScaleX(2);
				view.cardLemming[2].setScaleY(2);
			}
			
			
		});
		
		view.cardLemming[2].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[2].toBack();
				view.cardLemming[2].setScaleX(1);
				view.cardLemming[2].setScaleY(1);
			}
			
			
		});

		
		view.cardLemming[3].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[3].toFront();
				view.cardLemming[3].setScaleX(2);
				view.cardLemming[3].setScaleY(2);
			}
			
			
		});

		view.cardLemming[3].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[3].toBack();
				view.cardLemming[3].setScaleX(1);
				view.cardLemming[3].setScaleY(1);
			}
			
			
		});

		
		view.cardLemming[4].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[4].toFront();
				view.cardLemming[4].setScaleX(2);
				view.cardLemming[4].setScaleY(2);
			}
			
			
		});

		view.cardLemming[4].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardLemming[4].toBack();
				view.cardLemming[4].setScaleX(1);
				view.cardLemming[4].setScaleY(1);
			}
			
			
		});
		
		
		//EventHandler Zoom cardProf

				view.cardProf[0].setOnMouseEntered(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[0].toFront();
						view.cardProf[0].setScaleX(2);
						view.cardProf[0].setScaleY(2);
					}
								
				});

				view.cardProf[0].setOnMouseExited(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[0].toBack();
						view.cardProf[0].setScaleX(1);
						view.cardProf[0].setScaleY(1);
					}
					
					
				});

				
				view.cardProf[1].setOnMouseEntered(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[1].toFront();
						view.cardProf[1].setScaleX(2);
						view.cardProf[1].setScaleY(2);
					}
					
					
				});
				
				view.cardProf[1].setOnMouseExited(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[1].toBack();
						view.cardProf[1].setScaleX(1);
						view.cardProf[1].setScaleY(1);
					}
					
					
				});

				
				view.cardProf[2].setOnMouseEntered(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[2].toFront();
						view.cardProf[2].setScaleX(2);
						view.cardProf[2].setScaleY(2);
					}
					
					
				});
				
				view.cardProf[2].setOnMouseExited(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[2].toBack();
						view.cardProf[2].setScaleX(1);
						view.cardProf[2].setScaleY(1);
					}
					
					
				});

				
				view.cardProf[3].setOnMouseEntered(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[3].toFront();
						view.cardProf[3].setScaleX(2);
						view.cardProf[3].setScaleY(2);
					}
					
					
				});

				view.cardProf[3].setOnMouseExited(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[3].toBack();
						view.cardProf[3].setScaleX(1);
						view.cardProf[3].setScaleY(1);
					}
					
					
				});

				
				view.cardProf[4].setOnMouseEntered(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[4].toFront();
						view.cardProf[4].setScaleX(2);
						view.cardProf[4].setScaleY(2);
					}
					
					
				});

				view.cardProf[4].setOnMouseExited(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent arg0){
						view.cardProf[4].toBack();
						view.cardProf[4].setScaleX(1);
						view.cardProf[4].setScaleY(1);
					}
					
					
				});
		
		
		//EventHandler Zoom cardDino

		view.cardDino[0].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[0].toFront();
				view.cardDino[0].setScaleX(2);
				view.cardDino[0].setScaleY(2);
			}
						
		});

		view.cardDino[0].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[0].toBack();
				view.cardDino[0].setScaleX(1);
				view.cardDino[0].setScaleY(1);
			}
			
			
		});

		
		view.cardDino[1].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[1].toFront();
				view.cardDino[1].setScaleX(2);
				view.cardDino[1].setScaleY(2);
			}
			
			
		});
		
		view.cardDino[1].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[1].toBack();
				view.cardDino[1].setScaleX(1);
				view.cardDino[1].setScaleY(1);
			}
			
			
		});

		
		view.cardDino[2].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[2].toFront();
				view.cardDino[2].setScaleX(2);
				view.cardDino[2].setScaleY(2);
			}
			
			
		});
		
		view.cardDino[2].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[2].toBack();
				view.cardDino[2].setScaleX(1);
				view.cardDino[2].setScaleY(1);
			}
			
			
		});

		
		view.cardDino[3].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[3].toFront();
				view.cardDino[3].setScaleX(2);
				view.cardDino[3].setScaleY(2);
			}
			
			
		});

		view.cardDino[3].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[3].toBack();
				view.cardDino[3].setScaleX(1);
				view.cardDino[3].setScaleY(1);
			}
			
			
		});

		
		view.cardDino[4].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[4].toFront();
				view.cardDino[4].setScaleX(2);
				view.cardDino[4].setScaleY(2);
			}
			
			
		});

		view.cardDino[4].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardDino[4].toBack();
				view.cardDino[4].setScaleX(1);
				view.cardDino[4].setScaleY(1);
			}
			
			
		});

		
		//EventHandler Zoom cardTod

		view.cardTod[0].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[0].toFront();
				view.cardTod[0].setScaleX(2);
				view.cardTod[0].setScaleY(2);
			}
						
		});

		view.cardTod[0].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[0].toBack();
				view.cardTod[0].setScaleX(1);
				view.cardTod[0].setScaleY(1);
			}
			
			
		});

		
		view.cardTod[1].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[1].toFront();
				view.cardTod[1].setScaleX(2);
				view.cardTod[1].setScaleY(2);
			}
			
			
		});
		
		view.cardTod[1].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[1].toBack();
				view.cardTod[1].setScaleX(1);
				view.cardTod[1].setScaleY(1);
			}
			
			
		});

		
		view.cardTod[2].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[2].toFront();
				view.cardTod[2].setScaleX(2);
				view.cardTod[2].setScaleY(2);
			}
			
			
		});
		
		view.cardTod[2].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[2].toBack();
				view.cardTod[2].setScaleX(1);
				view.cardTod[2].setScaleY(1);
			}
			
			
		});

		
		view.cardTod[3].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[3].toFront();
				view.cardTod[3].setScaleX(2);
				view.cardTod[3].setScaleY(2);
			}
			
			
		});

		view.cardTod[3].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[3].toBack();
				view.cardTod[3].setScaleX(1);
				view.cardTod[3].setScaleY(1);
			}
			
			
		});

		
		view.cardTod[4].setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[4].toFront();
				view.cardTod[4].setScaleX(2);
				view.cardTod[4].setScaleY(2);
			}
			
			
		});

		view.cardTod[4].setOnMouseExited(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				view.cardTod[4].toBack();
				view.cardTod[4].setScaleX(1);
				view.cardTod[4].setScaleY(1);
			}
			
			
		});
		
		
		//EventHandler Choose DinoCards
		
		view.cardDino[1].setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent arg0){
				Image dinoP1 = new Image("images/Karte_Yeti_1.png");
				view.cardDino[1].setImage(dinoP1);
			}
			
		});	
		
	}

}
