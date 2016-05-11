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
/*
 * �]��draw �C�����|�Qcall�A�ҥH�]�w�@��drawSet��flag�קKnodes�٨S��l�ƴN�Qaccess
 * mouseX Y�Oproccessing���Ѫ��ܼơA��L�ΨөMCharacter�̭���boxX�����B��i�H�P�_�{�b�O�_��Цb�c�l�W�A�åB�ھڪ��p���L���P���C��
 * �C��draw������|�ˬd�@���a�ϤW�̷s���s�{���p�A�ܥB��s�s�u
 * 
 */
	public void draw() {
		  background(255);

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
				  line(500 , 0 , 500 , 650);
				  line(500 , 650 , 1200 , 650);
				  line(1200 , 650 , 1200 , 0);
				  line(1200 , 0 , 500 , 0);
				  rect(character[i].boxX, character[i].boxY, character[i].boxLength, character[i].boxLength);
				  rect(character[i].boxX, character[i].boxY, character[i].boxLength, character[i].boxLength);
				  rect(0 , 625 , 50 , 50);
				  rect(100 , 625 , 50 , 50);
				  text("add all", 0 , 610); 
				  text("clear all", 100 , 610);

			  }
			  network.display();
		  }


	}
//fileindex�O�@�ӥi�H�ǥ���L���㢶���ܪ��ܼơA�o�̷|���K��Json�������J�A�åB���K�ǤJNetwork�ѥL�P�_�s�u�C
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
//�p�G�ƹ����U�h�N�����C��A�åB���Character���W�r�C
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
	  if (mouseX > 0 && mouseX < 50 &&  mouseY > 575 && mouseY < 675) 
		  loadData();
	  else if (mouseX >100 && mouseX < 150 &&  mouseY > 575 && mouseY < 675)
		  setAllNetwork();
	
	}
	
	/*���ΦbmousePressed�P�_�L���ܼơA�b����p�G�b�c�l�W���ܷ|�]�@��Flag�o��P�_Flag��true�N�}�l���ܽc�l�y��
	**�]��mouseDragged mousePressed�O�P�ɶi�檺
	*/
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
	
	
	/*
	 * (non-Javadoc)
	 * @see processing.core.PApplet#mouseReleased()
	 */
	public void mouseReleased() {
	for(int i = 0 ; i < nodes.size() ;  i ++)
		{		
			character[i].boxLocked = false;
		}
	  
	}
	/*
	 * ²�檺�������character���y�в��J�خؤ��C
	 */
	public void setAllNetwork()
	{
		for(int i = 0 ; i < nodes.size() ;i++)
		{
			if(character[i].boxX<500)
			character[i].boxX = character[i].boxX + 650;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see processing.core.PApplet#keyPressed()
	 * loadData�i�H��sfile 1~7
	 * ��L�h�Ocall������function�Y�i
	 */
	public void keyPressed(){
		  if (key == '1') {
			  fileindex = 1 ;
			  loadData();
		  }
		  if (key == '2') {
			  fileindex = 2;
			  loadData();
		  }
		  if (key == '3') {
			  fileindex = 3;
			  loadData();
		  }
		  if (key == '4') {
			  fileindex = 4;
			  loadData();
		  }
		  if (key == '5') {
			  fileindex = 5;
			  loadData();
		  }
		  if (key == '6') {
			  fileindex = 6;
			  loadData();
		  }
		  if (key == '7') {
			  fileindex = 7;
			  loadData();
		  }
		  if (key == 'r') {
			  loadData();
		  }
		  if (key == 's') {
		      setAllNetwork();
		  }
	}

}
