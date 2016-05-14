package server;

import client.Würfel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	
	private String name;
	private int point;
	private ImageView image;
	private Status status;
	private Würfel würfel;
	
	
	public Card(String name, int point, Image image, Status status){
		this.name = name;
		this.point = point;
		this.image = new ImageView(image);
		this.status = status;
	}


	protected String getName() {
		return name;
	}


	protected int getPoint() {
		return point;
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


}