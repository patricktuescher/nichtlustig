package client;

import client.ClientModel;
import client.ClientView;
import javafx.event.ActionEvent;

public class ClientController {


	
	ClientView view;
	ClientModel model;
	
	public ClientController(ClientView view, ClientModel model){
		this.view = view;
		this.model = model;
	}

	private void handleButtonAction(ActionEvent event){
		
		
	}
}
