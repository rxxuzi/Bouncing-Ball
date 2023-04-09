package bounce;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Core extends JPanel implements ActionListener  {

	private static final long serialVersionUID = 1L;
	
	static ArrayList<Ball> Balls = new ArrayList<Ball>();

	static double StartTime = System.currentTimeMillis();
	
	final static int Speed = 3;
	
	static int SCREEN_WIDTH = 750, SCREEN_HEIGHT = 750;
	static int radius = 25;
	static int mouseX, mouseY;
	boolean Clicked = false;
	static boolean removeMode = false;
	static int Counter = 0;
	static String name = null;
	int num = 0;
	boolean add = false;
	boolean allremove = false;
	boolean[] k = new boolean[10];
	Color c;
	JLabel label = new JLabel("");
	JTextField text1 = new JTextField();
	JButton b1 = new JButton();
	JButton b2 = new JButton();
	JButton b3 = new JButton();
	JButton b4 = new JButton();
	Random rand = new Random();
	KeyInput kc = new KeyInput();
	

	
	public Core() {
		
		this.setFocusable(true);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.addMouseListener(new AboutMouse());
		this.addMouseMotionListener(new AboutMouse());
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.addKeyListener(kc);
		
		
		text1.setColumns(10);
		this.setLayout(null);
		text1.setBounds(10,140,50,20);
		//入力文字を白に
		text1.setForeground(Color.WHITE);
		//不透明にする
		text1.setOpaque(true);
		//30%にする
		text1.setBackground(new Color(255, 255, 255, 15));
		
		b1.setBounds( 60,140,20,20);
		b2.setBounds( 80,140,20,20);
		b3.setBounds(100,140,20,20);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b1.setBackground(Color.RED);
		b2.setBackground(Color.GREEN);
		b3.setBackground(Color.BLUE);
		label.setForeground(Color.WHITE);
        label.setBounds(10, 120, 200, 20);
		
		this.add(text1);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(label);
	
		
	}
	
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	private void draw(Graphics g) {
		
		SCREEN_WIDTH = getWidth();
		SCREEN_HEIGHT= getHeight();
		
		int elapsedTime = (int)(System.currentTimeMillis() - StartTime ) ;
		
		if(add) {
			for(int i = 0 ; i < num ; i ++) {
				Balls.add(new Ball(getWidth()/2, getHeight()/2, radius, Counter));
//				Balls.add(new Ball(rand.nextInt(getWidth()), rand.nextInt(getHeight()), radius, Counter));
				Counter ++;
			}
			add = false;
		}
		
		for(int i = 0 ; i < Balls.size() ; i++) {
		
			Balls.get(i).draw(g , c);
		}
		
		
		
		for(int  i = 0 ; i < Balls.size() ; i ++) {
			Balls.get(i).moveX(SCREEN_WIDTH);
			Balls.get(i).moveY(SCREEN_HEIGHT);
		}
		
		if(allremove) {
			for(int i = 0 ; i < Balls.size() ; i++) {
				Balls.get(i).delete();
			}
			
			if(Balls.size() == 0) {
				allremove = !allremove;
			}
			Counter = 0;
		}
		
		g.setColor(Color.YELLOW);
		g.drawString(elapsedTime/ 1000 + "." + elapsedTime % 100 /10  + "s", 10 , 10);
		g.drawString("Balls : " + Balls.size()  , 10 , 22);
		g.drawString("Width : " + getWidth() + ", Height :" + getHeight() , 10 ,33);
		if(removeMode) {
			g.drawString("REMOVEMODE!!", 10 , 45);
			remove();
		}
		
		int fs = 12;
		Font font = new Font("Courier New", Font.PLAIN, fs);
		g.setFont(font);
		int ny = 46;
		
//		g.drawString("______       _ _  "  , 10 , ny); ny += fs;
//		g.drawString("| ___ \\     | | | "  , 10 , ny); ny += fs;
//		g.drawString("| |_/ / __ _| | |___"  , 10 , ny); ny += fs;
//		g.drawString("| ___ \\/ _` | | / __|"  , 10 , ny); ny += fs;
//		g.drawString("| |_/ / (_| | | \\__ \\"  , 10 , ny); ny += fs;
//		g.drawString("\\____/ \\__,_|_|_|___/"  , 10 , ny); ny += fs;

		g.drawString("______       _ _          "  , 10 , ny); ny += fs;
		g.drawString("| ___ \\     | | |      	"  , 10 , ny); ny += fs;
		g.drawString("| |_/ / __ _| | |___      "  , 10 , ny); ny += fs;
		g.drawString("| ___ \\/ _` | | / __|    "  , 10 , ny); ny += fs;
		g.drawString("| |_/ / (_| | | \\__ \\_  "  , 10 , ny); ny += fs;
		g.drawString("\\____/ \\__,_|_|_|___(_) "  , 10 , ny); ny += fs;
		
		check();
		
		
		Sleep();
	}
	
	
	@SuppressWarnings("unused")
	private void drawGrid(Graphics g) {
		int size = 25;
		g.setColor(Color.LIGHT_GRAY);
		for(int x = 0; x <= getWidth()/size ; x ++) {
			g.drawLine(x * size , 0, x * size, getHeight());
		}
		for(int y = 0; y <= getHeight()/size ; y ++) {
			g.drawLine(0 , y * size , getWidth() , y * size);
		}
	}
	
	private void Sleep() {
		try {
			Thread.sleep(Speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
		
	}
	
	public void remove() {
		
		
		for(int i = 0 ; i < Balls.size() ; i ++ ) {
			double sx = Balls.get(i).x - Balls.get(i).r;
			double gx = Balls.get(i).x + Balls.get(i).r;
			double sy = Balls.get(i).y - Balls.get(i).r;
			double gy = Balls.get(i).y + Balls.get(i).r;
			
			if((mouseX > sx && mouseX < gx ) && (mouseY > sy && mouseY < gy )) {
				System.out.println("Delete : " + i);
				Balls.get(i).delete();
			}
		}
		
	}
	
	private void check() {

		
		for(int i = 0 ; i < Balls.size() ; i ++ ) {
			double sx = Balls.get(i).x - Balls.get(i).r;
			double gx = Balls.get(i).x + Balls.get(i).r;
			double sy = Balls.get(i).y - Balls.get(i).r;
			double gy = Balls.get(i).y + Balls.get(i).r;
			
			if((mouseX > sx && mouseX < gx ) && (mouseY > sy && mouseY < gy )) {
				
//				System.out.println("mouse on" + Balls.get(i).count);
			}
		}
		
	}
	
	class KeyInput implements KeyListener {
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE: System.out.println(19); break;
			case KeyEvent.VK_ESCAPE: System.exit(ABORT); break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
	}

	public class AboutMouse implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseMoved(java.awt.event.MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
		}

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			
			if(e.getButton() == MouseEvent.BUTTON1) {		
				System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")" + "Click Count : " + Counter);
				int CT = (int) (System.currentTimeMillis() - StartTime);
				System.out.println("Time : " + CT/1000 + "." + CT%100 + "s" );				
				Balls.add(new Ball(e.getX(), e.getY(), radius, Counter));
				Counter ++;
				
				
			}else if(e.getButton() == MouseEvent.BUTTON3) {
				remove();
				
				
			}else if(e.getButton() == MouseEvent.BUTTON2) {
				removeMode = !removeMode;
			}
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
		
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Red Button
		if (e.getSource() == b1) {
			
            String text = text1.getText();
            
            try {

            	num = Integer.parseInt(text); //String -> int 
            	
            	if(num > 2000) {
            		num = 2000; 
            		text1.setText(2000+"");
            	}
            	
            	add = true;
            	text = "add " + num + "balls";

                System.out.println("add" + num);
                
            }catch (NumberFormatException exception) {
            	
            	text = "Please enter a natural number";
                System.out.println("error");
                text1.setText("");
//				exception.printStackTrace();
			}
            
            label.setText(text);
        }
		
		//Blue Button
		
		if(e.getSource() == b3) {
			allremove = !allremove;
			System.out.println("remove");
			label.setText("ALL DELETE");
		}
		
		//Green Button 
		if(e.getSource() == b2 ) {
			
			if(Balls.size() > 0) {
				String text = text1.getText();
	            int n = 0;
	            int en = 0;
	            int size = Balls.size();
	            
	            try {
	            	n = Integer.parseInt(text); //String -> int 
	                System.out.println("delete" + n);
	            }catch (NumberFormatException exception) {
	            	text = "Please enter a natural number";
	                System.out.println("error");
	                text1.setText("");
				}
	            
	            for(int i = 0 ; i < n ; i ++ ) {
	            	if(Balls.size() >= 1) {
	    				Balls.get(Balls.size() - 1).delete();
	    				Counter --;
	    				System.out.println("Delete :" + Counter);
	    				en++;
	    			}
	            }
	            
	            text = "Delete " + ( size-1 ) + "~" + (size - en ); 
				if( en >= 1) {
					label.setText(text);					
				}else {
					System.out.println("Empty");
					label.setText("Please enter a natural number");
				}
				
			}else {
				System.out.println("Empty");
				label.setText("Empty");
			}
			
		}
		
	}
}
