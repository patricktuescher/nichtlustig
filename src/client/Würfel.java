/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class represents a die which can be implemented into the nichtLustig application.
	 * @author Nicola Burri
	 */

package client;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Würfel implements Serializable {
	
	private static final long serialVersionUID = -7272955027696020072L;

	//Height and Width of cubes
	final int cubeheight = 60;
	final int cubewidth = 60;
	
	private Farbe farbe = Farbe.Pink;
	private boolean selected = false;
	private boolean used = false;
	private int maxAugenzahl;
	private int aktAugenzahl = 1;
	private transient ImageView image = new ImageView(new Image("images/" + this.farbe.name()+ "_Würfel_"+aktAugenzahl+".png"));
	
	private transient ServiceLocator sl = ServiceLocator.getServiceLocator();
	private transient Logger logger = sl.getLogger();
	
	public Würfel(Farbe farbe){
		this.farbe = farbe;
		if(this.farbe.equals(Farbe.Pink)){
			maxAugenzahl = 6;
		}
		else
			maxAugenzahl = 5;
		
		image.setFitHeight(cubeheight);
		image.setFitWidth(cubewidth);
		image.setId("Wuerfel");
		
		logger.fine("Die has been created");
		this.roll();

	}
	public Würfel(int Augenzahl, Farbe farbe){
		this.aktAugenzahl = Augenzahl;
		this.farbe = farbe;
	}
	/**
	 * @return color of the die
	 */
	public Farbe getColor(){
		return this.farbe;
	}
	
	public boolean equals(Object obj){
		Würfel other = (Würfel)obj;
		if(this.getCurrentNumberofeyes() == other.getCurrentNumberofeyes() && this.getColor().equals(other.getColor())){
			return true;
		}
		else return false;
	}
	
	/**
	 * @return ImageView of die
	 */
	public ImageView getImageView(){
		if(this.image == null){
			image = new ImageView(new Image("images/" + this.farbe.name()+"_Würfel_"+aktAugenzahl+".png"));
			image.setFitHeight(cubeheight);
			image.setFitWidth(cubewidth);
			image.setId("Wuerfel");
		}
		return this.image;
	}
	
	/**
	 * @return current number of die (1-6)
	 */
	public int getCurrentNumberofeyes(){
		return this.aktAugenzahl;
	}
	
	public void setCurrentNumberofeyes(int Augenzahl){
		this.aktAugenzahl = Augenzahl;
		image.setImage(new Image("images/" + this.farbe.name()+"_Würfel_"+aktAugenzahl+".png"));
		this.image.setStyle("");
	}
	
	/**
	 * rolls the dice which have not been selected
	 */
	public void roll(){
		if(!this.isSelected()){
		Random rand = new Random();
		this.aktAugenzahl = rand.nextInt(maxAugenzahl)+1;
		image.setImage(new Image("images/" + this.farbe.name()+"_Würfel_"+aktAugenzahl+".png"));
		this.image.setStyle("");
		logger.fine(this.farbe + " die has been rolled. Number of die: " + this.aktAugenzahl);
		}
	}
	
	/**
	 * activates css effect on selected die
	 */
	public void click(){
		String css = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,5), 30, 0, 0, 0);";
		if(this.image.getStyle() == (css)){
			this.image.setStyle(css);
		}
			
		else{
			this.image.setStyle(css);
		}
		selected = true;
		logger.fine(this.farbe + " die has been clicked. Number of die: " + this.aktAugenzahl);
	}
	
	/**
	 * @return boolean if die is selected or not (true or false)
	 */
	public boolean isSelected(){
		return this.selected;
	}
	
	public void resetWürfel(){
		this.image.setStyle("");
		this.used = false;
		this.selected = false;
		this.image.setOpacity(1);
	}
	
	public String toString(){
		return "Würfel "+ this.aktAugenzahl + " "+this.getColor();
	}
	
	public void setUsed(boolean used){
		this.used = used;
	}
	
	public boolean isUsed(){
		return this.used;
	}
	public void setColor(Farbe newFarbe){
		this.farbe = newFarbe;
	}


}
