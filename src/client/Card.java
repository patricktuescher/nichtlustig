package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFileFormat.Type;

import javafx.application.Platform;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kontroburtüs.
 * All rights reserved.
 * 
 * This class represents a card which can be implemented into the nichtLustig application.
 * @author Nicola Burri
 */

public class Card implements Serializable{
	
	

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
		setWürfel();
	}
	
	/**
	 * sets the corresponding die to the card, which will be needed to select the card. 
	 */
	
	private void setWürfel(){
		this.würfel = new ArrayList<Würfel>();
		switch(this.getType()){
		case "Tod": 	würfel.add(new Würfel(this.Augenzahl, Farbe.Pink));
						break;
		case "Rieb": 	würfel.addAll(Arrays.asList(new Würfel(this.Augenzahl, Farbe.Schwarz), new Würfel(this.Augenzahl, Farbe.Schwarz)));
						break;
		case "Prof":	würfel.addAll(Arrays.asList(new Würfel(this.Augenzahl, Farbe.Schwarz), new Würfel(this.Augenzahl, Farbe.Weiss), new Würfel(this.Augenzahl, Farbe.Rot)));
						break;
		case "Lemming": würfel.addAll(Arrays.asList(new Würfel(this.Augenzahl, Farbe.Weiss), new Würfel(this.Augenzahl, Farbe.Weiss)));
						break;
		case "Yeti": 	würfel.addAll(Arrays.asList(new Würfel(this.Augenzahl, Farbe.Rot), new Würfel(this.Augenzahl, Farbe.Rot)));
						break;
		}
	}
	
	public ArrayList<Würfel> getWürfel(){
		return this.würfel;
	}
	
	
	
	public void check(ArrayList<Würfel> wuerfeltoTest, int summe){
		boolean b = true;
		ArrayList<Würfel> würfelPL1 = new ArrayList<Würfel>();
		würfelPL1.addAll(wuerfeltoTest);
		int auswahl = this.getAugenzahl();
		if(this.getType().equals(cardType.Dino.toString())){
			switch(auswahl){
			case 1:	if(summe <= 24){
					b = false;
					}break;
					
			case 2:	if(summe <= 25){
					b = false;
					}break;
			
			case 3:	if(summe <= 26){
					b = false;
					}break;
					
			case 4:	if(summe <= 27){
					b = false;
					}break;
					
			case 5:	if(summe <= 28){
					b = false;
					}break;
					
			}
		}else{
				
		for(int x = 0; x < this.getWürfel().size(); x++){
			if(!würfelPL1.contains(this.getWürfel().get(x))){
				b = false;
				break;
			}
			else
				würfelPL1.remove(this.getWürfel().get(x));
			}
		}
		if(b){
//			this.getImage().setOpacity(0.5);
			this.getImage().setVisible(true);
		}else{
//			getImage().setOpacity(0.5);
//			this.getImage().setOpacity(0.5);
			this.getImage().setVisible(false);
		}
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
		logger.info(this.type.name()+ " Card has been clicked on. Needed die are: " + würfel.toString());
		
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