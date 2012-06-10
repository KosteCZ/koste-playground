/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainJPanel.java
 *
 * Created on 2.6.2012, 15:48:53
 */

package village;

import java.awt.Graphics;

/**
 *
 * @author Honza
 */
public class MainJPanel extends javax.swing.JPanel {

    private Game game;

    /** Creates new form MainJPanel */
    public MainJPanel(Game game) {
        initComponents();
        this.game = game;
//          setBounds(100, 200, 300, 400);
//          setLocation(500, 600);
//          setSize(700, 800);
        repaint();
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        game.paint(g);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}