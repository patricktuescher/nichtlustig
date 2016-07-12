package client;

import java.io.Serializable;
import java.util.logging.Logger;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card implements Serializable{
	
	private static final long serialVersionUID = 142l;
	private cardType type = cardType.Dino;
	private int Augenzahl;
	private ImageView image;
	private Status status;
	private Würfel würfel;
	final int cardheight = 90;
	final int cardwidth = 90;
	Logger logger = ServiceLocator.getServiceLocator().getLogger();

	
	
	public Card(cardType type, int Augenzahl){
		this.type = type;
		this.Augenzahl = Augenzahl;
		this.status = Status.frei;
		String pfad = new String("Karte_");
		pfad = pfad + (this.type.name() + "_" + this.Augenzahl);
		this.image = new ImageView(new Image("images/" + pfad + ".png"));
		logger.fine(this.type.name()+ " Card has been created. Number of card is: " + this.Augenzahl);
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
	
	public void zoomCard(){
		this.image.toFront();
		this.image.setScaleX(2);
		this.image.setScaleY(2);
	}
	public void deZoomCard(){
		this.image.toBack();
		this.image.setScaleX(1);
		this.image.setScaleY(1);
	}
	public void click(){
		
		InnerShadow innerShadow = new InnerShadow(20, Color.BLUE);
		innerShadow.setOffsetX(2);
		innerShadow.setOffsetY(2);
		innerShadow.setChoke(0.5);	
		this.getImage().setEffect(innerShadow);
		this.getImage().setId("shadow");
		logger.info(this.type.name()+ " Card has been clicked on. Number of card is: " + this.Augenzahl);
		
	}


}