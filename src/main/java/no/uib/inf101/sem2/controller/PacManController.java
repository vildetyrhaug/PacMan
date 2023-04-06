package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;
import no.uib.inf101.sem2.view.PacManView;

public class PacManController implements java.awt.event.KeyListener {
    
    ControllablePacManModel model;
    PacManView pacManView;


    public PacManController(ControllablePacManModel model, PacManView pacManView) {
        pacManView.setFocusable(true);
        
        this.pacManView = pacManView;
        this.model = model;
        
        pacManView.addKeyListener(this);

    }
 
    @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                // Left arrow was pressed
                // move the pacMan left
                //model.movePacManLeft();
                if (model.movePacMan(0, -1)){
                    System.out.println("left");
                    pacManView.repaint();
            }}
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // Right arrow was pressed
                // move the pacMan right
                //model.movePacManRight();
                if (model.movePacMan(0, 1)){
                    System.out.println("right");
                    pacManView.repaint();
            }}
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                // Down arrow was pressed
                // move the pacMan down
                //model.movePacManDown();
                if (model.movePacMan(1, 0))
                    pacManView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                // Up arrow was pressed
                // move the pacMan up
                //model.movePacManUp();
                if (model.movePacMan(-1, 0))
                    pacManView.repaint();
            }
        }
        
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }



   





    


   
}
