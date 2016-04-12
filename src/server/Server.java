package server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Server extends Application{
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ServerModel model = new ServerModel();
		ServerView view = new ServerView(primaryStage, model);
		ServerController controller = new ServerController(view, model);
	
		
		view.start();
		
	}

}
