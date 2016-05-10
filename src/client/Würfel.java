package client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Würfel {
	private Farbe farbe;
	private int maxAugenzahl;
	private int aktAugenzahl = 1;
	private ImageView image;
	
	public Würfel(Farbe farbe, Image image){
		this.farbe = farbe;
		//this.image.setImage(image);
		if(this.farbe.equals(Farbe.pink))
			maxAugenzahl = 6;
		else
			maxAugenzahl = 5;
		
	}

}
