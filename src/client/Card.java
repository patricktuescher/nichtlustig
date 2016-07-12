package client;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card implements Serializable{
	
	private static final long serialVersionUID = 142l;
	private cardType type = cardType.Dino;
	private int Augenzahl;
	private ImageView image;
	private Status status;
	private Würfel würfel;
	final int cardheight = 90;
	final int cardwidth = 90;

	
	
	public Card(cardType type, int Augenzahl){
		this.type = type;
		this.Augenzahl = Augenzahl;
		this.status = Status.frei;
		String pfad = new String("Karte_");
		pfad = pfad + (this.type.name() + "_" + this.Augenzahl);
		this.image = new ImageView(new Image("images/" + pfad + ".png"));
	}


	protected int getAugenzahl() {
		return Augenzahl;
	}


	protected ImageView getImage() {
		this.image.setFitHeight(this.cardheight);
		this.image.setFitWidth(this.cardwidth);
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
	protected String getType(){
		return this.type.name();
	}
	public String toString(){
		return this.getType() + " Nr: " + this.Augenzahl;
	}


}