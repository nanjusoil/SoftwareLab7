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
 * 因為draw 每次都會被call，所以設定一個drawSet的flag避免nodes還沒初始化就被access
 * mouseX Y是proccessing提供的變數，把他用來和Character裡面的boxX等等運算可以判斷現在是否游標在箱子上，並且根據狀況給他不同的顏色
 * 每次draw完之後會檢查一次地圖上最新的連現狀況，變且更新連線
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
//fileindex是一個可以藉由鍵盤１～７改變的變數，這裡會順便把Json全部載入，並且順便傳入Network供他判斷連線。
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
//如果滑鼠按下去就改變顏色，並且顯示Character的名字。
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
	
	/*先用在mousePressed判斷過的變數，在那邊如果在箱子上的話會設一個Flag這邊判斷Flag為true就開始改變箱子座標
	**因為mouseDragged mousePressed是同時進行的
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
	 * 簡單的把全部的character的座標移入框框內。
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
	 * loadData可以更新file 1~7
	 * 其他則是call相應的function即可
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
