package gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static JFrame frame = new JFrame();
	
	private static Image img;
	private static Image img2;
	
	private static int x = 1;
	
	private GameMap gameMap;

	static final int MAP_X = 500; 
	static final int MAP_Y = 200; 
	
	public static void main( String args[] ) {
		
	    GameMap gameMap = new GameMap( MAP_X, MAP_Y, 4, 4 );
	    
		System.out.println( "000" );

		Thread thread = new GameThread();
		thread.start();
		
		System.out.println( "222" );
		
		String message = "aaa";
		
		String threadName = Thread.currentThread().getName();
	    System.out.format( "%s: %s%n", threadName, message );
	    String threadName2 = thread.getName();
	    System.out.format( "%s: %s%n", threadName2, message );
		
		
	    
	    /*JFrame*/ frame = new JFrame();
	    frame.setTitle("Polygon");
	    frame.setSize(1200, 800);
	    
	    frame.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent e) {
	    		System.exit(0);
	    	}
	    });
	    
	    Container contentPane = frame.getContentPane();
	    contentPane.add(new Main());
	    frame.setVisible(true);
	    
	    contentPane.addMouseListener(new MouseAdapter() {
	    //frame.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent event) {
	            //System.out.println(event.getPoint());

	        	System.out.println( "RowForWidth("+ event.getPoint().x +"): " + gameMap.getRowForWidth( event.getPoint().x ) );
	        	System.out.println( "ColumnForHeight("+ event.getPoint().y +"): " + gameMap.getColumnForHeight( event.getPoint().y ) );

	    	    if ((event.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {

    	            System.out.println( "Left mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }

        	    if ((event.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {

    	            System.out.println( "Center mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }

        	    if ((event.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {

    	            System.out.println( "Right mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }
        	    
        	    MapPositionItem mapPositionItem = gameMap.getMapPosition( event.getPoint().x, event.getPoint().y );
        	    
        	    if ( mapPositionItem != null ) {
        	    
        	    	System.out.println(mapPositionItem);
        	    
        	    }
        	    
        	    System.out.println();
        	    
	        }

	    });
	    
	    //( x=8,y=30 ;  x=1191,y=790 ;  x=8,y=790 <- levy spodni )(OLD)
	    //java.awt.Point[x=1183,y=761]
	    
	    System.out.println( "W: " + frame.getWidth() );
	    System.out.println( "H: " + frame.getHeight() );
	    
	    
	    try {
	        System.out.println( "1-Is img null: " + (img == null) );
	        img = ImageIO.read( new File("1.png") );
	        System.out.println( "2-Is img null: " + (img == null) );
	    } catch (IOException e) {
	    	System.err.println( e );
	    }
	    
	    try {
	        img2 = ImageIO.read( new File("2.png") );
	    } catch (IOException e) {
	    	System.err.println( e );
	    }

	    frame.repaint();
	    
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
	    
		Polygon p = new Polygon();
		for (int i = 0; i < 5; i++) {
			p.addPoint( (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 5)), (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 5)) );
		}
		g.drawPolygon(p);
		
		for (int i = 0; i < 10; i++) {
			g.drawRect( 5 + (i*5), 5 + (i*5), 90 - (i*10), 90 - (i*10) );			
		}

		g.drawRect( 100, 100, 1000, 600 );
		
		g.setColor( Color.RED );
		g.drawRect( 1183, 761, 1, 1 ); // frame bottom, right
		g.drawRect( 1173, 751, 9, 9 );
		g.drawRect( 1133, 711, 49, 49 );
		
		g.drawImage( img, 200, 200, null );
		g.drawImage( img, 300, 200, null );
		g.drawImage( img, 200, 300, null );
		g.drawImage( img, 300, 300, null );

		g.drawRect( x, 300, 3, 3 );
		
		x = x + 1;
		
		System.out.println( "X: " + x );

		
		g.drawImage( img2, 500, 200, null );
		g.drawImage( img2, 525, 200, null );
		g.drawImage( img2, 500, 225, null );
		g.drawImage( img2, 525, 225, null );

		g.drawImage( img2, 550, 200, null );
		g.drawImage( img2, 575, 200, null );
		g.drawImage( img2, 550, 225, null );
		g.drawImage( img2, 575, 225, null );

		g.drawImage( img2, 500, 250, null );
		g.drawImage( img2, 525, 250, null );
		g.drawImage( img2, 500, 275, null );
		g.drawImage( img2, 525, 275, null );

		g.drawImage( img2, 550, 250, null );
		g.drawImage( img2, 575, 250, null );
		g.drawImage( img2, 550, 275, null );
		g.drawImage( img2, 575, 275, null );
		
	}

}
