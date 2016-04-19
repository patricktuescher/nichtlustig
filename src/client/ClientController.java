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
		
		
		//EventHandler Zoom DinoCards

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
