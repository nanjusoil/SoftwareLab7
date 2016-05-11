package main.java;

import processing.core.PApplet;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	JSONObject json;
	Character character = new Character(this);
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		
		size(width, height);
		smooth();
		loadData();
	}

	public void draw() {
		  background(0);

		  if (mouseX > character.boxX-character.boxLength && mouseX < character.boxX+character.boxLength &&  mouseY > character.boxY-character.boxLength && mouseY < character.boxY+character.boxLength) {

			  character.cursorOnBox = true;  

		    if(!character.boxLocked) { 

		      stroke(255); 

		      fill(153);

		    } 

		  } else {

		    stroke(153);

		    fill(153);

		    character.cursorOnBox = false;

		  }

		  rect(character.boxX, character.boxY, character.boxLength, character.boxLength);
	}

	private void loadData(){
		json = loadJSONObject(path + "starwars-episode-1-interactions.json");
		System.out.println(json);
	}

	public void mousePressed() {
	
	  if(character.cursorOnBox) { 
	
		  character.boxLocked = true; 
	
	    fill(255, 255, 255);
	
	  } else {
	
		  character.boxLocked = false;
	
	  }
	
	  character.bdifx = mouseX-character.boxX; 
	
	  character.bdify = mouseY-character.boxY; 
	
	
	
	}
	
	
	public void mouseDragged() {
	
	  if(character.boxLocked) {
	
		  character.boxX = mouseX-character.bdifx; 
	
		  character.boxY = mouseY-character.bdify; 
	
	  }
	
	}
	
	
	
	public void mouseReleased() {
	
		character.boxLocked = false;
	  
	}

}
