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
	Character[] character = new Character[10];
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		
		size(width, height);
		smooth();
		loadData();
	}

	public void draw() {
		  background(0);

		  for(int i = 0 ; i < 10 ;  i ++)
		  {
			  if (mouseX > character[i].boxX-character[i].boxLength && mouseX < character[i].boxX+character[i].boxLength &&  mouseY > character[i].boxY-character[i].boxLength && mouseY < character[i].boxY+character[i].boxLength) {
	
				  character[i].cursorOnBox = true;  
	
			    if(!character[i].boxLocked) { 
	
			      stroke(255); 
	
			      fill(153);
	
			    } 
	
			  } else {
	
			    stroke(153);
	
			    fill(153);
	
			    character[i].cursorOnBox = false;
	
			  }
	
			  rect(character[i].boxX, character[i].boxY, character[i].boxLength, character[i].boxLength);
		  }
	}

	private void loadData(){
		json = loadJSONObject(path + "starwars-episode-1-interactions.json");
		for(int i = 0 ; i < 10 ;i++)
			character[i] = new Character(this);
		System.out.println(json);
	}

	public void mousePressed() {
	for(int i = 0 ; i < 10 ;  i ++)
	{	
		  if(character[i].cursorOnBox) { 
		
			  character[i].boxLocked = true; 
		
		    fill(255, 255, 255);
		
		  } else {
		
			  character[i].boxLocked = false;
		
		  }
		
		  character[i].bdifx = mouseX-character[i].boxX; 
		
		  character[i].bdify = mouseY-character[i].boxY; 
	}
	
	
	}
	
	
	public void mouseDragged() {
	for(int i = 0 ; i < 10 ;  i ++)
	{		
	  if(character[i].boxLocked) {
	
		  character[i].boxX = mouseX-character[i].bdifx; 
	
		  character[i].boxY = mouseY-character[i].bdify; 
	
	  }
	}
	
	}
	
	
	
	public void mouseReleased() {
	for(int i = 0 ; i < 10 ;  i ++)
		{		
			character[i].boxLocked = false;
		}
	  
	}

}
