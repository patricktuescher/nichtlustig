// Gruppe: Manipake Kontroburt�s
// 
// Version 1.0.0
//
// CLient-Applikation
//
/////////////////////////////////////////////////////////////////////////////////////

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

