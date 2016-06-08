import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Main extends JPanel {
	
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 950;
    private final int HEIGHT = 670;
    
    public static int HEX_WIDTH = 50;
    public static int HEX_WIDTH_HALF = HEX_WIDTH / 2;
    public static int HEX_HEIGHT = 44;

    private Font font = new Font("Arial", Font.BOLD, 18);
    FontMetrics metrics;

    public Main() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(font);
        
        // changing color of image + transparent images overlay
        
        BufferedImage bimg = ImageChangeColour.colorImage();
        /*g2d.drawImage(bimg, null, 0, 0);
        
        bimg = ImageChangeColour.colorImage();
        g2d.drawImage(bimg, null, HEX_WIDTH, 0);
        
        bimg = ImageChangeColour.colorImage();
        g2d.drawImage(bimg, null, HEX_WIDTH_HALF, HEX_HEIGHT);
        
        bimg = ImageChangeColour.colorImage();
        g2d.drawImage(bimg, null, HEX_WIDTH + HEX_WIDTH_HALF, HEX_HEIGHT);*/
        
        /*int width = 12;
        int height = 12;
        
        for (int row = 0; row < height; row++) {
        	for (int col = 0; col < (width + (((row % 2) == 1) ? 1 : 0)); col++) {
                
            	bimg = ImageChangeColour.colorImage();
                g2d.drawImage(bimg, null, 50 + col * HEX_WIDTH + (((row % 2) == 1) ? 0 : HEX_WIDTH_HALF), 50 + row * HEX_HEIGHT);
            
            }
        }*/
        
        Map map = new Map(13, 13);
        map.paint(g2d);
        
        //TODO array of new objects - has x,y - know neighbours, can paint
        
        
	    this.addMouseListener(new MouseAdapter() {
	    //frame.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent event) {
	            //System.out.println(event.getPoint());

	        	//System.out.println( "RowForWidth("+ event.getPoint().x +"): " + gameMap.getRowForWidth( event.getPoint().x ) );
	        	//System.out.println( "ColumnForHeight("+ event.getPoint().y +"): " + gameMap.getColumnForHeight( event.getPoint().y ) );

	    	    if ((event.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {

    	            System.out.println( "Left mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }

        	    if ((event.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {

    	            System.out.println( "Center mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }

        	    if ((event.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {

    	            System.out.println( "Right mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );

        	    }
        	    
        	    /*MapPositionItem mapPositionItem = gameMap.getMapPosition( event.getPoint().x, event.getPoint().y );
        	    
        	    if ( mapPositionItem != null ) {
        	    
        	    	System.out.println(mapPositionItem);
        	    
        	    }*/
        	    
        	    //System.out.println();
        	    
	        }
	        
	    });
	    
    	//g2d.drawString("[X,Y]", 30, 30);
	    
		this.addMouseMotionListener(new MouseAdapter() {
			
	        public void mouseMoved(MouseEvent event) {
	        	g2d.drawString("[" + event.getPoint().x + "," + event.getPoint().y + "]", 30, 30);
	        	//System.out.println("[" + event.getPoint().x + "," + event.getPoint().y + "]");
	        	//repaint();
	        }
	        
	    });
	    	        
   }

	public static void main(String[] args) {
        JFrame f = new JFrame();
        Main p = new Main();

        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}