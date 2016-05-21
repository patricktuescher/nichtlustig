package server;

import client.Würfel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	
	private cardType type = cardType.Dino;
	private int points;
	private ImageView image;
	private Status status;
	private Würfel würfel;
	
	
	public Card(cardType type, int point, Image image, Status status){
		this.type = type;
		this.points = point;
		this.image = new ImageView(image);
		this.status = status;
	}


	protected int getPoints() {
		return points;
	}


	protected ImageView getImage() {
		return image;
	}


	protected Status getStatus() {
		return status;
	}
	
	
	protected void setStatus(Status status) {
		this.status = status;
	}


	protected Würfel getWürfel() {
		return würfel;
	}


}