package com.doglab.entities;

import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Dice extends Entity{

	private boolean current = false;
	private int size = 10;
	private String state1 = "SE FUDEU", state2 = "FRACASSO", state3 = "NORMAL", state4 = "BOM", 
			state5 = "EXTREMO";
	private String currentState = state1;
	public int d = 0;
	private int stat;
	public EditButton edit;
	private MouseEditorLabel mEditor;
	
	public Dice(double x, double y, int width, int height, double speed, BufferedImage sprite, int dValue, int stat) {
		super(x, y, width, height, speed, sprite);
		this.d = dValue;
		this.stat = stat;
		edit = new EditButton(0, 0, 0, 0, 0, null);
	}
	
	public void tick() {
	
		double sizeDice = 2;
		if((Game.mouseController.currentX > this.getX() && Game.mouseController.currentY > this.getY()) &&
				(Game.mouseController.currentX < this.getX()+this.getWidth() && 
						Game.mouseController.currentY < this.getY()+this.getHeight())) {
			if(!current) {
				this.x-=size;
				this.y-=size;
				this.width+=size*sizeDice;
				this.height+=size*sizeDice;
				current = true;
			}
		}else {
			if(current) {
				this.x+=size;
				this.y+=size;
				this.width-=size*sizeDice;
				this.height-=size*sizeDice;
				current = false;
			}
		}
		
		if(mEditor != null) {
			if(mEditor.returnD() != this.d) {
				this.d = mEditor.returnD();
			}
		}
		
		if(this.isColliding(this, Game.mouseController)) {
			Game.mouseController.resetPosition();
			int wLabel = 350;
			int hLabel = 150;
			int xLabel = ((Game.WIDTH*Game.SCALE)/2)-wLabel/2;
			int yLabel = ((Game.HEIGHT*Game.SCALE)/2)-hLabel/2;
			if(edit.isEditing()) {
				mEditor = new MouseEditorLabel(xLabel, yLabel, wLabel, hLabel, 0, null, this.d, this);
				Game.entities.add(mEditor);
			}else {
				
				int value = rand.nextInt(d)+1;
				
				if(value >= d-(stat/5)) {
					currentState = state5;
				}else if(value >= d-(stat/2)) {
					currentState = state4;
				}else if(value >= stat) {
					currentState = state3;
				}else if(value == 1) {
					currentState = state1;
				}else if(value <= stat) {
					currentState = state2;
				}
				
				DiceLabel diceLabel = new DiceLabel(xLabel, yLabel, wLabel, hLabel, 0, this.getSprite(), 
						value, currentState);
				Game.entities.add(diceLabel);	
			}
		}
	}

}
