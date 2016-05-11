package main.java;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	JSONObject json;
	JSONArray nodes;
	JSONArray links;
	JSONObject charnode;
	public Character[] character;
	Network network ;
	boolean drawSet = false;
	int fileindex=1;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		
		size(width, height);
		smooth();
		loadData();
	}

	public void draw() {
		  background(0);

		  if(drawSet == true)
		  {
			  for(int i = 0 ; i < nodes.size() ;  i ++)
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
			  network.display();
		  }
	}

	private void loadData(){
		json = loadJSONObject(path + "starwars-episode-"+fileindex+"-interactions.json");
		nodes = json.getJSONArray("nodes");
		links = json.getJSONArray("links");
		character = new Character[nodes.size()];
		network = new Network(this , character ,nodes.size());
		for(int i = 0 ; i < nodes.size() ;i++)
		{
			charnode = nodes.getJSONObject(i);
			character[i] = new Character(this);
			character[i].name = charnode.getString("name");
		}
		network.setNetwork(links);
		drawSet = true;
	}

	public void mousePressed() {
	for(int i = 0 ; i < nodes.size() ;  i ++)
	{	
		  if(character[i].cursorOnBox) { 
		
			  character[i].boxLocked = true; 
		
		    fill(255, 255, 255);
		    text(character[i].name, character[i].boxX, character[i].boxY); 
		
		  } else {
		
			  character[i].boxLocked = false;
		
		  }
		
		  character[i].bdifx = mouseX-character[i].boxX; 
		
		  character[i].bdify = mouseY-character[i].boxY; 
	}
	
	
	}
	
	
	public void mouseDragged() {
	for(int i = 0 ; i < nodes.size() ;  i ++)
	{		
	  if(character[i].boxLocked) {
		  text(character[i].name, character[i].boxX, character[i].boxY); 
		  character[i].boxX = mouseX-character[i].bdifx; 
	
		  character[i].boxY = mouseY-character[i].bdify; 
	
	  }
	}
	
	}
	
	
	
	public void mouseReleased() {
	for(int i = 0 ; i < nodes.size() ;  i ++)
		{		
			character[i].boxLocked = false;
		}
	  
	}
	public void setAllNetwork()
	{
		for(int i = 0 ; i < nodes.size() ;i++)
		{
			character[i].boxX = character[i].boxX + 500;
		}
	}


}
