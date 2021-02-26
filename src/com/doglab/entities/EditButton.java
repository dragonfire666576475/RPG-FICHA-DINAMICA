package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class EditButton extends Button{

	public EditButton(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}

	public void tick() {
		Entity e = new Entity(getX(), getY(), getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(e, Game.mouseController)) {
			Game.mouseController.resetPosition();
			actionPerformed();
		}
	}
	
	public void actionPerformed() {
		TextLabel.editionTime = !TextLabel.editionTime;
	}
	
	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX(), this.getY(), width, height, null);
	}
	
}
