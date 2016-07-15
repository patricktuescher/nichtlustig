package client;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameAvailableImage {
	private boolean on;
	private ImageView iv;
	
	public GameAvailableImage(boolean on){
		this.on = on;
		if (on)
			this.iv= new ImageView(new Image("images/Clown_on.png"));
		else
			this.iv= new ImageView(new Image("images/Clown_off.png"));
	}
	public void setImageOn(){
		this.iv = new ImageView(new Image("images/Clown_on.png"));
	}
	public void setImageOff(){
		this.iv = new ImageView(new Image("images/Clown_off.png"));
	}

	public ImageView getImage(){
		return this.iv;
	}
}
