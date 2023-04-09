package bounce;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;


public class Ball {
	
	
	double x;
	double y;
	int r;
	int count;
	Graphics g;
	Random rand = new Random();
	
	double speedX = rand.nextInt(4) +  1 / (rand.nextInt(2)+1 + 0.1);
	double speedY = rand.nextInt(4) +  1 / (rand.nextInt(2)+1 + 0.1);
	
	boolean Down = rand.nextBoolean();
	boolean Right = rand.nextBoolean();
	
	Ball(double x , double y , int r , int counter){
		this.x = (int)x;
		this.y = (int)y;
		this.r = r;
		this.count = counter;
	}
	
	public Color setColor() {
		Color c = null;
		switch(count % 24) {
		case 0  : c = new Color(185,  31,  87); break;
		case 1  : c = new Color(208,  47,  72); break;
		case 2  : c = new Color(221,  68,  59); break;
		case 3  : c = new Color(233,  91,  35); break;
		case 4  : c = new Color(230, 120,   0); break;
		case 5  : c = new Color(244, 157,   0); break;
		case 6  : c = new Color(241, 181,   0); break;
		case 7  : c = new Color(238, 201,   0); break;
		case 8  : c = new Color(210, 193,   0); break;
		case 9  : c = new Color(168, 187,   0); break;
		case 10 : c = new Color( 88, 169,  29); break;
		case 11 : c = new Color(  0, 161,  90); break;
		case 12 : c = new Color(  0, 146, 110); break;
		case 13 : c = new Color(  0, 133, 127); break;
		case 14 : c = new Color(  0, 116, 136); break;
		case 15 : c = new Color(  0, 112, 155); break;
		case 16 : c = new Color(  0,  96, 156); break;
		case 17 : c = new Color(  0,  91, 165); break;
		case 18 : c = new Color( 26,  84, 165); break;
		case 19 : c = new Color(  0,  91, 165); break;
		case 20 : c = new Color(112,  63, 150); break;
		case 21 : c = new Color(129,  55, 138); break;
		case 22 : c = new Color(143,  46, 124); break;
		case 23 : c = new Color(173,  46, 108); break;
		default : c = Color.DARK_GRAY; break;
		}

		return c;
		
	}
	
	public void draw(Graphics g, Color c) {
		g.setColor(setColor());
		int dx = (int) x;
		int dy = (int) y;
		g.fillOval(dx - r , dy - r, 2*r, 2*r );
		g.setColor(Color.white);
		g.drawString(count+"",dx,dy);
	}
	
	public void delete() {
		Core.Balls.remove(this);
	}
	
	public void moveX(double w) {
		
		if(x >  w - r) Right =  false;
		if(x <  0 + r) Right = true;	
		
		if(Right) {
			x += speedX;
		}else {
			x -= speedX;
		}
	}
	
	public void moveY(double h) {
		
		if(y > h - r) Down  = false;
		if(y < 0 + r) Down  = true;
		
		if(Down) {
			y += speedY;
		}else {
			y -= speedY;
		}

	}
	
}
