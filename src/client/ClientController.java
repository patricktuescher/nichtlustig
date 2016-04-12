package client;

import client.ClientModel;
import client.ClientView;

public class ClientController {


	
	ClientView view;
	ClientModel model;
	
	public ClientController(ClientView view, ClientModel model){
		this.view = view;
		this.model = model;
	}

}
