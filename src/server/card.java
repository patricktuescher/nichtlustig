package server;

import javafx.scene.image.Image;

public class card {
	
	private String name;
	private int points;
	private Cube cubes;
	private Image image;
	private Status status;
	
	
	public card(){
		name = "";
		points = 0;
		image = new Image("");
		status = new Status();
	}


	protected Status getStatus() {
		return status;
	}


	protected void setStatus(Status status) {
		this.status = status;
	}


	protected String getName() {
		return name;
	}


	protected void setName(String name) {
		this.name = name;
	}


	protected int getPoints() {
		return points;
	}


	protected void setPoints(int points) {
		this.points = points;
	}


	protected Image getImage() {
		return image;
	}


	protected void setImage(Image image) {
		this.image = image;
	}
	

	
	
	
	
	
	
	
		

}
