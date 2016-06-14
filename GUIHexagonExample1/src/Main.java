import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public static final String APP_VERSION = "0.0.1"; 
	public static final String APP_TITLE = "Ovce: boj o pastviny (autor: Jan Koščák, verze " + APP_VERSION + ")"; 
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 670;    
    public static int HEX_WIDTH = 50;
    public static int HEX_WIDTH_HALF = HEX_WIDTH / 2;
    public static int HEX_HEIGHT = 44;
    
	private static JFrame jFrame = new JFrame();
	
	private Player[] players;
	private Map map;
	private Hex lastClickedHex = null;
	private int sendSheepsCount = 1;
    
    private Font font = new Font("Arial", Font.BOLD, 18);
	private Color themePenColor = Color.BLACK;

    public Main() {
    	
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        players = new Player[2];
        players[0] = new Player("Hráč 1", Color.RED);
        players[1] = new Player("Hráč 2", Color.BLUE);
        
        map = new Map(13, 13);
        map.generateMap1();
        map.conquerHex(4, 5, players[0], 16);
        map.conquerHex(9, 9, players[1], 16);
        
        //map.getReachableHexes(map.getHexPosition(300, 300)); // 6,6
        //map.getReachableHexes(map.getHexPosition(230, 300)); // 4,6
        
    }

    @Override
    public void paintComponent(Graphics g) {
    	
		super.paintComponent(g);
		
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(font);
        
        // changing color of image + transparent images overlay
        
        /* BufferedImage bimg = ImageChangeColour.getImage();
        g2d.drawImage(bimg, null, 0, 0);
        
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
        
        //TODO array of new objects - has x,y - know neighbours, can paint
        
        map.paint(g2d);
        
    	g2d.setColor(themePenColor);
    	g2d.drawString("Na tahu je: ", 800, 60);
    	g2d.setColor(players[0].getColor());
    	g2d.drawString(players[0].getName(), 800, 80);	        	
    	g2d.setColor(themePenColor);
    	g2d.drawString("Skóre: ", 800, 120);	        	

    	for (int i = 0; i < players.length; i++) {
        	if ( players[i].isAlive() ) {
        		g2d.setColor(players[i].getColor());
        	} else {
        		g2d.setColor(Color.DARK_GRAY);
        	}
        	g2d.drawString(players[i].getName() + " - mrtvé: " + players[i].deadHexesCount(), 800, 140 + i * 20);	        	
        	g2d.drawString(players[i].getName() + " - živé:  " + players[i].liveHexesCount(), 800, 180 + i * 20);	        	
        	g2d.setColor(themePenColor);
		}
    	
    	Hex selectedHex = map.getSelectedHex();
    	
    	if ( selectedHex != null ) {
    		
    		selectedHex.customPaint( g2d, 850, 264, selectedHex.getSheepCount() );
    		selectedHex.customPaint( g2d, 825, 308, Hex.HEX_ARROW_LEFT );
    		selectedHex.customPaint( g2d, 875, 308, Hex.HEX_ARROW_RIGHT );
    		selectedHex.customPaint( g2d, 800, 352, selectedHex.getSheepCount() - sendSheepsCount );
    		selectedHex.customPaint( g2d, 900, 352, sendSheepsCount );
    		
        	g2d.drawString("Dostupné klávesy:", 800, 450);
        	boolean isLessThanTen = selectedHex.getSheepCount() < 10;
        	int avalibleKeys = isLessThanTen ? selectedHex.getSheepCount() : 10;
        	for (int i = 1; i < avalibleKeys; i++) {
            	g2d.drawString("" + i, 800 + (i-1)*19, 470);				
			}
        	if (selectedHex.getSheepCount() > 10) { g2d.drawString("a=10", 800 + 0 * 60,   490); }
        	if (selectedHex.getSheepCount() > 11) { g2d.drawString("b=11", 800 + 1 * 60+1, 490); }
        	if (selectedHex.getSheepCount() > 12) { g2d.drawString("c=12", 800 + 2 * 60+1, 490); }
        	if (selectedHex.getSheepCount() > 13) { g2d.drawString("d=13", 800 + 0 * 60,   510); }
        	if (selectedHex.getSheepCount() > 14) { g2d.drawString("e=14", 800 + 1 * 60+1, 510); }
        	if (selectedHex.getSheepCount() > 15) { g2d.drawString("f=15", 800 + 2 * 60+5, 510); }
    	}
    	
    	jFrame.addKeyListener(new KeyAdapter() {
    		public void keyPressed(KeyEvent event) {

       			//System.out.println("KEY!!!");
       			//System.out.println("KEY: " + event.getKeyChar());
       			
       			if ( selectedHex != null ) {
       				
       				try {
       					
       					String key = String.valueOf(event.getKeyChar());
       					
           				if ( "a".equals(key) ) { key = "10"; }
           				if ( "b".equals(key) ) { key = "11"; }
           				if ( "c".equals(key) ) { key = "12"; }
           				if ( "d".equals(key) ) { key = "13"; }
           				if ( "e".equals(key) ) { key = "14"; }
           				if ( "f".equals(key) ) { key = "15"; }
           					
       					int potencialSendSheepsCount = Integer.valueOf( key );
       				
       					if (potencialSendSheepsCount > 0 && potencialSendSheepsCount < selectedHex.getSheepCount()) {
       				
       						sendSheepsCount = potencialSendSheepsCount;
       					
       					} else {	
           					sendSheepsCount = 1;	
           				}
       				} catch (Exception e) {
       					sendSheepsCount = 1;
       				}
       			} else {	
       				sendSheepsCount = 1;	
       			}

    		}
    	}); 
        
	    this.addMouseListener(new MouseAdapter() {
	    //frame.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent event) {
	            //System.out.println(event.getPoint());

	        	//System.out.println( "RowForWidth("+ event.getPoint().x +"): " + gameMap.getRowForWidth( event.getPoint().x ) );
	        	//System.out.println( "ColumnForHeight("+ event.getPoint().y +"): " + gameMap.getColumnForHeight( event.getPoint().y ) );

	    	    if ( (event.getModifiers() & InputEvent.BUTTON1_MASK) != 0 ) {

//    	            System.out.println( "Left mouse button clicked on point [" + event.getPoint().x + "," + event.getPoint().y + "]" );
    	            
    	            Hex hex = map.getHexPosition(event.getPoint().x, event.getPoint().y);
    	            
    	            if ( lastClickedHex == null || (hex != null && (lastClickedHex.getX() != hex.getX() || lastClickedHex.getY() != hex.getY())) ) {
    	            
    	            	boolean conquered = false;
    	            	conquered = map.doConquere( hex, sendSheepsCount );
    	            	if (conquered == false ) {
    	            		map.getReachableHexes( hex );
    	                 	lastClickedHex = hex;
    	                 	sendSheepsCount = 1;
    	              	} else {
    	                  	lastClickedHex = null;
    	                  	sendSheepsCount = 1;
    	             	}
    	            	
    	            }
    	            
    	            map.killPotentionallyDeadHexes( players );

//    	            System.out.println();

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

	        	//System.out.println("[" + event.getPoint().x + "," + event.getPoint().y + "]");
	            
	        	Graphics2D g2d = (Graphics2D) jFrame.getGraphics();
	        	g2d.setFont(font);
	        	g2d.drawString("a[" + event.getPoint().x + "," + event.getPoint().y + "]", 60, 230);	        	
	        	
	            Polygon p = new Polygon();
	            for (int i = 0; i < 5; i++)
	              p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 5)),
	                  (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 5)));

	            //g2d.drawPolygon(p);
	            
	            if ( p.contains(event.getPoint().x + 7, event.getPoint().y + 30) ) {
	            	g2d.drawPolygon(p);
	            //	g2d.drawString("in", 60, 280); 
	            } else {
	            	g2d.fillPolygon(p);
		        //    g2d.drawString("out", 60, 280);
	            }
	        	
	            /*
	            BufferedImage bimg = ImageChangeColour.getImage(HexType.GRASS.name());
	            Rectangle2D rect = new Rectangle2D.Double(0, 0, Hex.HEX_WIDTH, Hex.HEX_HEIGHT+Hex.HEX_HEIGHT_OVER);
	            TexturePaint texturePaint = new TexturePaint(bimg, rect);
	            
	            Polygon p2 = new Polygon();
	            p2.addPoint(7+Hex.HEX_WIDTH*4, 30+Hex.HEX_HEIGHT*2);
	            p2.addPoint(7+Hex.HEX_WIDTH*5, 30+Hex.HEX_HEIGHT*2);
	            p2.addPoint(7+Hex.HEX_WIDTH*5, 30+Hex.HEX_HEIGHT*3+Hex.HEX_HEIGHT_OVER);
	            p2.addPoint(7+Hex.HEX_WIDTH*4, 30+Hex.HEX_HEIGHT*3+Hex.HEX_HEIGHT_OVER);
	            		  
	            g2d.draw(p2);
	            
	            g2d.setPaint(texturePaint);
	            g2d.fill(p2);
	            */
	            
	            repaint();
		        jFrame.repaint();
	        	
	        }	        
	        
	    });
	    	        
    	//repaint();
        //jFrame.repaint();
        
  }

	public static void main(String[] args) {
        /*JFrame jFrame = new JFrame();*/
        Main jPanelMain = new Main();

        jFrame.setContentPane(jPanelMain);
        jFrame.setTitle(APP_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}