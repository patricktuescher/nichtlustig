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

	
	
	public Card(cardType type, int Augenzahl){
		this.type = type;
		this.Augenzahl = Augenzahl;
		this.status = Status.frei;
		String pfad = new String("Karte_");
		pfad = pfad + (this.type.name() + "_" + this.Augenzahl);
		System.out.println(pfad);
		this.image = new ImageView(new Image("images/" + pfad + ".png"));
	}


	protected int getAugenzahl() {
		return Augenzahl;
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
	protected String getType(){
		return this.type.name();
	}


}