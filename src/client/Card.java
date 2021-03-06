/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class represents a card which can be implemented into the nichtLustig application.
	 * @author Nicola Burri
	 */

package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card implements Serializable{
	
	private static final long serialVersionUID = 9093800489872546795L;
	private cardType type = cardType.Dino;
	private int numberofeyes;
	private transient ImageView image;
	private Status status;
	private ArrayList <Würfel> dies;
	private final int cardHeight = 90;
	private final int cardWidth = 90;
	private transient Logger logger = ServiceLocator.getServiceLocator().getLogger();
	private Account owner;
	private String pfad;
	private Card cardDeath;

	/**
	 * The constructor creates a card
	 * @param type example cardType.Dino
	 * @param Augenzahl What number is showing on the card
	 */
	
	public Card(cardType type, int Augenzahl){
		this.type = type;
		this.numberofeyes = Augenzahl;
		this.status = Status.frei;
		this.owner = null;
		this.cardDeath = null;
		logger.fine(this.type.name()+ " Card has been created. Number of card is: " + this.numberofeyes);
		setDie();
	}
	
	/**
	 * sets the corresponding die to the card, which will be needed to select the card. 
	 */
	
	private void setDie(){
		this.dies = new ArrayList<Würfel>();
		switch(this.getType()){
		case "Tod": 	dies.add(new Würfel(this.numberofeyes, Farbe.Pink));
						break;
		case "Rieb": 	dies.addAll(Arrays.asList(new Würfel(this.numberofeyes, Farbe.Schwarz), new Würfel(this.numberofeyes, Farbe.Schwarz)));
						break;
		case "Prof":	dies.addAll(Arrays.asList(new Würfel(this.numberofeyes, Farbe.Schwarz), new Würfel(this.numberofeyes, Farbe.Weiss), new Würfel(this.numberofeyes, Farbe.Rot)));
						break;
		case "Lemming": dies.addAll(Arrays.asList(new Würfel(this.numberofeyes, Farbe.Weiss), new Würfel(this.numberofeyes, Farbe.Weiss)));
						break;
		case "Yeti": 	dies.addAll(Arrays.asList(new Würfel(this.numberofeyes, Farbe.Rot), new Würfel(this.numberofeyes, Farbe.Rot)));
						break;
		}
	}
	
	public ArrayList<Würfel> getDie(){
		return this.dies;
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public boolean check(ArrayList<Würfel> wuerfeltoTest, int summe){
		boolean b = true;
		ArrayList<Würfel> würfelPL1 = new ArrayList<Würfel>();
		würfelPL1.addAll(wuerfeltoTest);
		int auswahl = this.getNumberofeyes();
		if(this.getStatus()== Status.frei || this.getStatus() == Status.gewählt && !this.getOwner().equals(ClientController.clientOwner)){
		if(this.getType().equals(cardType.Dino.toString())){
			summe = summe - würfelPL1.get(0).getCurrentNumberofeyes();
			switch(auswahl){
			case 1:	if(summe != 24){
					b = false;
					}break;
					
			case 2:	if(summe != 25){
					b = false;
					}break;
			
			case 3:	if(summe != 26){
					b = false;
					}break;
					
			case 4:	if(summe != 27){
					b = false;
					}break;
					
			case 5:	if(summe != 28){
					b = false;
					}break;
					
			}
		}else{
		for(int x = 0; x < this.getDie().size(); x++){
			if(!würfelPL1.contains(this.getDie().get(x)) || würfelPL1.get(würfelPL1.indexOf(this.getDie().get(x))).isUsed()){
				b = false;
				break;
			}
			else
				würfelPL1.remove(this.getDie().get(x));
			}
		}
		}else{
			b = false;
		}
		
		if(b){
			this.getImage().setOpacity(1);
			return true;
		}else{
			this.getImage().setOpacity(0.5);
			return false;
		}
	}
	
	


	public int getNumberofeyes() {
		return numberofeyes;
	}


	public ImageView getImage() {
		if(this.image == null){
		this.image = new ImageView();
		}
		pfad = new String("Karte_");
		pfad = pfad + (this.type.name() + "_" + this.numberofeyes);
		if(status == Status.gewertet){
			pfad = pfad + "_gewertet";
		}
		if(status == Status.tod){
			pfad = pfad + "_tod";
		}
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
	
	public Account getOwner(){
		return owner;
	}
	
	public void setOwner(Account owner){
		this.owner= owner;
	}
	
	public Card getcardDeath(){
		return cardDeath;
	}
	
	public void setcardDeath(Card cardDeath){
		this.cardDeath = cardDeath;
	}

	public String getType(){
		return this.type.name();
	}
	
	public String toString(){
		return this.getType() + " Nr: " + this.numberofeyes + " Status: " + this.status;
	}
	
	public boolean equals(Card otherCard){
		if(otherCard != null){
		if(this.numberofeyes == otherCard.getNumberofeyes() && this.getType().equals(otherCard.getType())){
			return true;
		}else{
			return false;
		}
		}else{
			return false;
		}
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
	
	public void click(Account acc){
		this.owner = acc;
		if(this.status == Status.frei || this.status == Status.gewählt){
			this.status = Status.gewählt;
		}
		
			InnerShadow innerShadow = new InnerShadow(20, Color.web("489dff"));
			innerShadow.setOffsetX(2);
			innerShadow.setOffsetY(2);
			innerShadow.setChoke(0.5);	
			this.getImage().setEffect(innerShadow);
			this.getImage().setId("shadow");
			logger.info(this + " is chosen. Needed die are: " + dies.toString());
		
	}
	
	public void clickOther(Account acc){
		this.owner = acc;
		if(this.status == Status.frei || this.status == Status.gewählt){
		this.status = Status.gewählt;
		}
		
		InnerShadow innerShadow = new InnerShadow(20, Color.GREEN);
		innerShadow.setOffsetX(2);
		innerShadow.setOffsetY(2);
		innerShadow.setChoke(0.5);	
		this.getImage().setEffect(innerShadow);
		this.getImage().setId("shadow");
				
	}

}