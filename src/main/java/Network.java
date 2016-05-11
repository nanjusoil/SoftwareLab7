package main.java;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private JSONArray links;
	private JSONObject connection;
	private Character[] character;
	private int size;
	public Network(PApplet parent , Character[] character , int size){
	
		this.parent = parent;
		this.character = character;
		this.size = size;
		
	}

	public void display(){
		for(int i = 0 ; i < size ; i++)
		  character[i].drawNetwork();
		for(int i = 0; i <links.size() ; i++)
		{
			connection = links.getJSONObject(i);
			if(character[connection.getInt("source")].isDrawNetwork == true &&character[connection.getInt("target")].isDrawNetwork == true)
			{
				parent.line(character[connection.getInt("source")].boxX , character[connection.getInt("source")].boxY , character[connection.getInt("target")].boxX , character[connection.getInt("target")].boxY);
				parent.fill(153);
				parent.strokeWeight(connection.getInt("value")/3);
			}
		}
	}
	
	public void setNetwork( JSONArray links){
		this.links = links;
	}
	
}
