/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class is used to set the image according to whether a user already started a game or not.
	 * @author 
	 */

package client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameAvailableImage {
	private boolean on;
	private ImageView iv;
	
	public GameAvailableImage(boolean on){
		this.on = on;
		if (on){
			setImageOn();
		}
		else{
			setImageOff();
		}
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
