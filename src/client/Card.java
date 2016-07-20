package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kontroburtüs.
 * All rights reserved.
 * 
 * This class represents a card which can be implemented into the nichtLustig application.
 * @author Manipake Kontroburtüs
 */

public class Card implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9093800489872546795L;
	private cardType type = cardType.Dino;
	private int Augenzahl;
	private transient ImageView image;
	private Status status;
	private ArrayList <Würfel> würfel;
	private final int cardHeight = 90;
	private final int cardWidth = 90;
	private transient Logger logger = ServiceLocator.getServiceLocator().getLogger();

	/**
	 * The constructor creates a card
	 * @param type example cardType.Dino
	 * @param Augenzahl What number is showing on the card
	 */
	
	public Card(cardType type, int Augenzahl){
		this.type = type;
		this.Augenzahl = Augenzahl;
		this.status = Status.frei;
		logger.fine(this.type.name()+ " Card has been created. Number of card is: " + this.Augenzahl);
	}
	
	public void setWürfel(ArrayList<Würfel> würfel){
		this.würfel = würfel;
	}
	
	public ArrayList<Würfel> getWürfel(){
		return this.würfel;
	}
	
	public boolean check(ArrayList<Würfel> würfel){
		if(this.würfel != null && würfel.contains(this.würfel)){
			return true;
		}
		return false;
	}


	public int getAugenzahl() {
		return Augenzahl;
	}


	public ImageView getImage() {
		if(this.image == null){
		this.image = new ImageView();
		}
		String pfad = new String("Karte_");
		pfad = pfad + (this.type.name() + "_" + this.Augenzahl);
		this.image.setImage(new Image("images/" + pfad + ".png"));
		this.image.setFitHeight(this.cardHeight);
		this.image.setFitWidth(this.cardWidth);
		return image;
	}


	public Status getStatus() {
		return status;
	}
	
	
	public void setStatus(Status status) {
		this.status = status;
	}


	public String getType(){
		return this.type.name();
	}
	public String toString(){
		return this.getType() + " Nr: " + this.Augenzahl;
	}
	public boolean equals(Card otherCard){
		if(this.Augenzahl == otherCard.getAugenzahl() && this.getType().equals(otherCard.getType())){
			return true;
		}
		else return false;
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
	public void clickMe(){
		
		InnerShadow innerShadow = new InnerShadow(20, Color.web("489dff"));
		innerShadow.setOffsetX(2);
		innerShadow.setOffsetY(2);
		innerShadow.setChoke(0.5);	
		this.getImage().setEffect(innerShadow);
		this.getImage().setId("shadow");
		logger.info(this.type.name()+ " Card has been clicked on. Number of card is: " + this.Augenzahl);
		
	}
	public void clickOther(){
		InnerShadow innerShadow = new InnerShadow(20, Color.GREEN);
		innerShadow.setOffsetX(2);
		innerShadow.setOffsetY(2);
		innerShadow.setChoke(0.5);	
		this.getImage().setEffect(innerShadow);
		this.getImage().setId("shadow");

	}


}