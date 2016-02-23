package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static JFrame frame = new JFrame();
	
	private static int x = 1;
	
	private static Storage storage;
	private static Storage field;

	static final int STORAGE_X = 10; 
	static final int STORAGE_Y = 20; 
	
	static final int FILED_X = 630; 
	static final int FIELD_Y = 20; 
	
	static Bean[][] beans;
	
	public static final String APP_TITLE_BEANS = "Fazole - betaverze"; 
	
	public static final String STORAGE_NAME_STORAGE = "Sklad";
	public static final String STORAGE_NAME_FIELD = "Pole";
	
	private static Bean selectedBean = null;
	
	public static void main( String args[] ) {
		
	    storage = new Storage( STORAGE_NAME_STORAGE, STORAGE_X, STORAGE_Y, 15, 10 );
	    field = new Storage( STORAGE_NAME_FIELD, FILED_X, FIELD_Y, 10, 2 );
	    
	    
	    frame = new JFrame();
	    frame.setTitle(APP_TITLE_BEANS);
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

	        	System.out.println( "RowForWidth("+ event.getPoint().x +"): " + storage.getRowForWidth( event.getPoint().x ) );
	        	System.out.println( "ColumnForHeight("+ event.getPoint().y +"): " + storage.getColumnForHeight( event.getPoint().y ) );

	    	    if ((event.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {

    	            System.out.println( "Left mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );
    	            
        	    }

        	    if ((event.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {

    	            System.out.println( "Center mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }

        	    if ((event.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {

    	            System.out.println( "Right mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }
        	    
        	    MapPositionItem mapPositionItem = storage.getMapPosition( event.getPoint().x, event.getPoint().y );
        	    
        	    if ( mapPositionItem != null ) {
        	    
        	    	System.out.println(mapPositionItem);
        	    
    	            selectedBean = storage.selectNewSelectedBean(mapPositionItem, selectedBean);

        	    }
        	    
        	    System.out.println();
        	    
        	    frame.repaint();
        	    
	        }

	    });
	    
	    //( x=8,y=30 ;  x=1191,y=790 ;  x=8,y=790 <- levy spodni )(OLD)
	    //java.awt.Point[x=1183,y=761]
	    
	    System.out.println( "W: " + frame.getWidth() );
	    System.out.println( "H: " + frame.getHeight() );
	    
	    
	    storage.initStorageStrategy1();//TODO
	    field.initFieldStrategy1();//TODO

	    //GameThread thred = new GameThread ();
	    
	    frame.repaint();
	    
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		 // frame bottom, right
		g.setColor( Color.RED );
		g.drawRect( 1183, 761, 1, 1 ); // frame bottom, right
		g.drawRect( 1173, 751, 9, 9 );
		g.drawRect( 1133, 711, 49, 49 );
		
		x = x + 1;
		
		System.out.println( "X: " + x );

		
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		g.drawString("Detail fazole:", 1050, 20 - 5);	
		
		if (selectedBean == null) {
			selectedBean = new Bean(1050, 20, BeanColours.BEAN_COULOUR_ITEM_EMPTY);
		} else {
			
			System.err.println("Painting!!!");
			
			g.drawImage(selectedBean.getInnerImage(), selectedBean.getX(), selectedBean.getY(), 125, 125, null);
		}
		
		
		storage.paintComponent(g);
		
		field.paintComponent(g);
		
	}

}
