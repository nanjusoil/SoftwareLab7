package main.java;

import java.util.Random;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float boxX;
	public float boxY;
	public int boxLength;

	public boolean cursorOnBox;

	public boolean boxLocked;

	public float bdifx; 

	public float bdify;
	
	Random random = new Random();
	
	private final static int width = 1200, height = 650;
	public Character(MainApplet parent){

		this.parent = parent;
		boxX = random.nextInt(500);

		boxY = random.nextInt(500);
		
		boxLength = 20;
		
		cursorOnBox = false;
		
		boxLocked = false;
		
		bdifx = 0;
		
		bdify = 0;
	}

	public void display(){

	}
	
}
