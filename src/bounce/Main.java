package bounce;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.Dimension;
import java.awt.Toolkit;
public class Main {
	
	static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static void main(String[] args) {
		Core p = new Core();
		JFrame jf = new JFrame();
		ImageIcon icon = new ImageIcon("./rsc/icon.png");
		JLayeredPane lp = new JLayeredPane();
		
		int width = 750;
		int height= 750;
		
		lp.setPreferredSize(new Dimension(width,height));
		
		jf.setIconImage(icon.getImage());
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Bouncing Ball !!");
		jf.add(p);
		
		jf.setSize(width,height);
		jf.setLocation(500, 10);
		
		

	}

}
