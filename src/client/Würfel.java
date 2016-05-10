package client;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Würfel {
	private Farbe farbe;
	private int maxAugenzahl;
	private int aktAugenzahl = 1;
	private ImageView image;
	
	public Würfel(Farbe farbe, Image image){
		this.farbe = farbe;
		this.image = new ImageView(image);
		if(this.farbe.equals(Farbe.pink))
			maxAugenzahl = 6;
		else
			maxAugenzahl = 5;
		
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
	}

}
