package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	private ArrayList<Label> labels;
	private int timesRoll = 0;
	private String newD = "";
	private boolean show;
	private boolean typeRoll;

	public Dice(double x, double y, int width, int height, double speed, BufferedImage sprite, 
			TextLabel dValue, TextLabel stat, TextLabel timesRoll, boolean show, boolean type) {
		super(x, y, width, height, speed, sprite);
		labels = new ArrayList<Label>();
		typeRoll = type;
		this.show = show;
		String t = timesRoll.text;
		String newT = "";
		for(int i = 0; i < (t).length(); i++) {
			if(String.valueOf((t).charAt(i)).equals("1") || 
					String.valueOf((t).charAt(i)).equals("2") || 
					String.valueOf((t).charAt(i)).equals("3") ||
					String.valueOf((t).charAt(i)).equals("4") || 
					String.valueOf((t).charAt(i)).equals("5") || 
					String.valueOf((t).charAt(i)).equals("6") ||
					String.valueOf((t).charAt(i)).equals("7") || 
					String.valueOf((t).charAt(i)).equals("8") || 
					String.valueOf((t).charAt(i)).equals("9") ||
					String.valueOf((t).charAt(i)).equals("0")) {
				newT+=t.charAt(i);
			}
		}
		if(newT != "") {
			this.timesRoll = Integer.parseInt(newT);
		}else {
			this.timesRoll = 1;
		}
		String s = stat.text;
		String newS = "";
		for(int i = 0; i < (s).length(); i++) {
			if(String.valueOf((s).charAt(i)).equals("1") || 
					String.valueOf((s).charAt(i)).equals("2") || 
					String.valueOf((s).charAt(i)).equals("3") ||
					String.valueOf((s).charAt(i)).equals("4") || 
					String.valueOf((s).charAt(i)).equals("5") || 
					String.valueOf((s).charAt(i)).equals("6") ||
					String.valueOf((s).charAt(i)).equals("7") || 
					String.valueOf((s).charAt(i)).equals("8") || 
					String.valueOf((s).charAt(i)).equals("9") ||
					String.valueOf((s).charAt(i)).equals("0")) {
				newS+=s.charAt(i);
			}
		}
		if(newS != "") {
			this.stat = Integer.parseInt(newS);
		}else {
			this.stat = 1;
		}
		String d = dValue.text;
		String newD = "";
		boolean isPlus = false;
		for(int i = 0; i < (d).length(); i++) {
			if(String.valueOf((d).charAt(i)).equals("1") || 
					String.valueOf((d).charAt(i)).equals("2") || 
					String.valueOf((d).charAt(i)).equals("3") ||
					String.valueOf((d).charAt(i)).equals("4") || 
					String.valueOf((d).charAt(i)).equals("5") || 
					String.valueOf((d).charAt(i)).equals("6") ||
					String.valueOf((d).charAt(i)).equals("7") || 
					String.valueOf((d).charAt(i)).equals("8") || 
					String.valueOf((d).charAt(i)).equals("9") ||
					String.valueOf((d).charAt(i)).equals("0") ||
					String.valueOf((d).charAt(i)).equals("+")) {
				if(String.valueOf((d).charAt(i)).equals("+")) {
					if(i-1 >= 0) {
						if(String.valueOf((d).charAt(i-1)).equals("1") || 
								String.valueOf((d).charAt(i-1)).equals("2") || 
								String.valueOf((d).charAt(i-1)).equals("3") ||
								String.valueOf((d).charAt(i-1)).equals("4") || 
								String.valueOf((d).charAt(i-1)).equals("5") || 
								String.valueOf((d).charAt(i-1)).equals("6") ||
								String.valueOf((d).charAt(i-1)).equals("7") || 
								String.valueOf((d).charAt(i-1)).equals("8") || 
								String.valueOf((d).charAt(i-1)).equals("9") ||
								String.valueOf((d).charAt(i-1)).equals("0")) {
							this.newD+=d.charAt(i);
							isPlus = true;
						}
					}
				}else {
					if(!isPlus) {
						newD+=d.charAt(i);
					}
					this.newD+=d.charAt(i);
				}
			}
		}
		if(newD != "") {
			this.d = Integer.parseInt(newD);
		}else {
			this.d = 1;
		}
		labels.add(stat);
		labels.add(dValue);
		labels.add(timesRoll);
		edit = new EditButton(0, 0, 0, 0, 0, null);
		this.setMask((int)x+10, (int)y+10, width-20, height-20);
	}
	
	private int updateArrayList(int index) {
		int var = 0;
		if(((TextLabel)labels.get(index)).text != "") {
			String numbers = "";
			for(int i = 0; i < ((TextLabel)labels.get(index)).text.length(); i++) {
				if(String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("1") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("2") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("3") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("4") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("5") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("6") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("7") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("8") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("9") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("0")) {
					numbers+=((TextLabel)labels.get(index)).text.charAt(i);
				}
			}
			if(numbers != "") {
				var = Integer.parseInt(numbers);
			}
		}
		return var;
	}
	
	private String updateArrayListD(int index) {
		String var = "";
		if(((TextLabel)labels.get(index)).text != "") {
			String numbers = "";
			for(int i = 0; i < ((TextLabel)labels.get(index)).text.length(); i++) {
				if(String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("1") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("2") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("3") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("4") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("5") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("6") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("7") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("8") || 
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("9") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("0") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).equals("+") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("a") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("b") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("c") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("d") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("e") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("f") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("g") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("h") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("i") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("j") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("k") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("l") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("m") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("n") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("o") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("p") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("q") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("r") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("s") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("t") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("u") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("v") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("w") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("x") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("y") ||
						String.valueOf(((TextLabel)labels.get(index)).text.charAt(i)).toLowerCase().equals("z")) {
					numbers+=((TextLabel)labels.get(index)).text.charAt(i);
				}
			}
			if(numbers != "") {
				var = numbers;
			}
		}
		return var;
	}
	
	public void tick() {
		if(((TextLabel)labels.get(0)).width > 0) {
			this.stat = updateArrayList(0);
		}
		boolean hasPlus = false;
		if(((TextLabel)labels.get(1)).width > 0) {
			String ss = updateArrayListD(1);
			for(int i = 0; i < ss.length(); i++) {
				if(String.valueOf(((TextLabel)labels.get(1)).text.charAt(i)).equals("+")) {
					hasPlus = true;
					if(i+1 < ss.length()) {
						String[] s = ss.split("\\+");
						this.d = Integer.parseInt(s[0]);
					}
				}
			}
			if(!hasPlus) {
				if(ss != "") {
					this.d = Integer.parseInt(ss);
				}
			}
			this.newD = ss;
		}
		if(((TextLabel)labels.get(2)).width > 0) {
			this.timesRoll = updateArrayList(2);
		}
		double sizeDice = 2;
		if((Game.mouseController.currentX > this.maskx && Game.mouseController.currentY > this.masky-Game.roller.getY()*Game.roller.step) &&
				(Game.mouseController.currentX < this.maskx+this.maskw && 
						Game.mouseController.currentY < this.masky-Game.roller.getY()*Game.roller.step+this.getHeight())) {
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
		Entity e = new Entity(maskx, masky-Game.roller.getY()*Game.roller.step, maskw, maskh, speed, getSprite());
		if(this.isColliding(e, Game.mouseController)) {
			Game.mouseController.resetPosition();
			int wLabel = 350;
			int hLabel = 150;
			int xLabel = ((Game.WIDTH*Game.SCALE)/2)-wLabel/2;
			int yLabel = ((Game.HEIGHT*Game.SCALE)/2)-hLabel/2;
			if(/*edit.isEditing()*/false) {
				mEditor = new MouseEditorLabel(xLabel, yLabel, wLabel, hLabel, 0, null, this.d, this);
				Game.entities.add(mEditor);
			}else {
				int value[] = new int[timesRoll];
				String plus[] = new String[1];
				for(int i = 0; i < this.newD.length(); i++) {
					if(String.valueOf(newD.charAt(i)).equals("+")) {
						if(i+1 < newD.length()) {
							plus = this.newD.split("\\+");
						}else {
							String[] catchNumber = this.newD.split("\\+");
							plus[0] = catchNumber[0];
						}
					}
				}
				if(plus[0] == null) {
					plus[0] = this.newD;
				}
				for(int i = 0; i < timesRoll; i++) {
					if(Integer.parseInt(plus[0]) > 0) {
						value[i] = rand.nextInt(Integer.parseInt(plus[0]))+1;
					}else {
						return;
					}
				}
				
				if(this.show) {
					if(typeRoll) { // maior
						if(value[0] > d-(stat/5)) {
							currentState = state5;
						}else if(value[0] > d-(stat/2)) {
							currentState = state4;
						}else if(value[0] > d-stat) {
							currentState = state3;
						}else if(value[0] == 1) {
							currentState = state1;
						}else if(value[0] <= d-stat) {
							currentState = state2;
						}
					}else { // menor
						if(value[0] <= stat) {
							currentState = state3;
						}else if(value[0] > (stat/2)) {
							currentState = state2;
						}
					}
				}else {
					currentState = null;
				}
				
				DiceLabel diceLabel = new DiceLabel(xLabel, yLabel, wLabel, hLabel, 0, this.getSprite(), 
						value, currentState, plus);
				Game.entities.add(diceLabel);	
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		//g.setColor(Color.red);
		//g.drawRect(maskx, masky - Game.roller.getY()*Game.roller.step, maskw, maskh);
	}
	
}
