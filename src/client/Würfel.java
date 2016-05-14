package client;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Würfel {
	//Height and Width of cubes
	final int cubeheight = 60;
	final int cubewidth = 60;
	
	private Farbe farbe = Farbe.Pink;
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
	
	public Farbe getFarbe(){
		return this.farbe;
	}
	
	public ImageView getImageView(){
		
		return this.image;
	}
	public int getAktAugenzahl(){
		return this.aktAugenzahl;
	}
	
	public void roll(){
		Random rand = new Random();
		this.aktAugenzahl = rand.nextInt(maxAugenzahl)+1;
		image.setImage(new Image("images/" + this.farbe.name()+"_Würfel_"+aktAugenzahl+".png"));
		this.image.setStyle("");
	}
	
	public void click(){
		String css = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,5), 20, 0, 0, 0);";
		this.image.setStyle(css);
		
	}


}
