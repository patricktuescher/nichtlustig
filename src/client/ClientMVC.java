/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class starts the client applications.
	 * @author 
	 */

package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMVC extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ClientModel model = new ClientModel();
		ClientView view = new ClientView(primaryStage, model);
		ClientController controller = new ClientController(view, model);
	
		view.start();
		
	}
	
	public String getName(){
		return "Client";
	}
}

