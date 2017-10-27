package com.youknowjoejoe.gmail.grapher.testing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.youknowjoejoe.gmail.grapher.Grapher;
import com.youknowjoejoe.gmail.grapher.functions.DDX;
import com.youknowjoejoe.gmail.grapher.functions.Function;
import com.youknowjoejoe.gmail.grapher.functions.RationalFunction;
import com.youknowjoejoe.gmail.grapher.math.Vec2;
import com.youknowjoejoe.gmail.grapher.math.Vec2i;

public class Main {

	public static void main(String[] args) throws IOException {
		//Function f1 = (x)->(x*x*x*x*x-5*x*x*x*x-x*x*x+28*x*x-2*x);
		Function f1 = new RationalFunction((x)->x,(x)->(x*x*x+x*x+1));
		Function f2 = new DDX(f1,0.00001);
		Function f3 = new DDX(f2,0.00001);
		
		Grapher g = new Grapher(
					new Vec2i(800,800), //dimensions in pixels
					new Vec2(-4,4), //x range
					new Vec2(-60,60), //y range
					1, //x scale
					5, //y scale
					64000 //x frequency
				);
		BufferedImage img = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, 800, 800);
		
		g.drawAxis(g2d);
		
		g2d.setColor(Color.blue);
		g.drawFunction(g2d, f1);
		
		g2d.setColor(Color.red);
		System.out.println(g.drawFunction(g2d, f2));
		
		g2d.setColor(Color.magenta);
		g.drawFunction(g2d, f3);
		
		g2d.dispose();
		
		ImageIO.write(img, "png", new File("output.png"));
	}

}
