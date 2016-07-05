package client;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents a dice which can be used in the nichtLustig application
 */
public class Würfel {
	//Height and Width of cubes
	final int cubeheight = 60;
	final int cubewidth = 60;
	
	private Farbe farbe = Farbe.Pink;
	private boolean selected = false;
	private int maxAugenzahl;
	private int aktAugenzahl = 1;
	private ImageView image = new ImageView(new Image("images/" + this.farbe.name()+ "_Würfel_"+aktAugenzahl+".png"));
	
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
		this.roll();

	}
	/**
	 * @return color of dice
	 */
	public Farbe getFarbe(){
		return this.farbe;
	}
	
	/**
	 * @return ImageView of dice
	 */
	public ImageView getImageView(){
		return this.image;
	}
	
	/**
	 * @return current number of dice (1-6)
	 */
	public int getAktAugenzahl(){
		return this.aktAugenzahl;
	}
	
	/**
	 * rolls die which have not been selected
	 */
	public void roll(){
		if(!this.isSelected()){
		Random rand = new Random();
		this.aktAugenzahl = rand.nextInt(maxAugenzahl)+1;
		image.setImage(new Image("images/" + this.farbe.name()+"_Würfel_"+aktAugenzahl+".png"));
		this.image.setStyle("");
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
	}
	
	/**
	 * @return boolean if dice is selected or not (true or false)
	 */
	public boolean isSelected(){
		return this.selected;
	}


}
