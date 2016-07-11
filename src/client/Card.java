package client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	
	private cardType type = cardType.Dino;
	private int points;
	private ImageView image;
	private Status status;
	private Würfel würfel;
	
	
	public Card(cardType type, int point){
		this.type = type;
		this.points = point;
		this.status = Status.frei;
		String pfad = new String("Karte_");
		pfad.concat(this.type.name() + "_" + this.points);
		this.image = new ImageView(new Image("images/" + pfad));
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