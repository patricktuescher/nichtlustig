package client;

import java.util.Random;

public class Würfel {
	
	private enum farbe{
		pink, weiss, schwarz, rot
	}
	private int aktuelleAugenzahl;
	
	public Würfel(farbe farbe){
		this.roll();
	}
	
	
	public void roll(){
		Random random = new Random();
		if(this.equals(farbe.pink)){
			this.aktuelleAugenzahl = random.nextInt(6)+1;
		}
		else
			this.aktuelleAugenzahl = random.nextInt(5)+1;
	}
	
	public String toString(){
		Integer Ausgabe = this.aktuelleAugenzahl;
		return Ausgabe.toString();
	}
	
	public static void main(String[] args){
		for(int x = 0;x<1000;x++){
		Würfel rot1 = new Würfel(farbe.pink);
		System.out.println(rot1);
		}
}
}

	