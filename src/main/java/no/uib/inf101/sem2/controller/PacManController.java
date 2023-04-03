package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.sem2.view.PacManView;

public class PacManController implements java.awt.event.KeyListener {
    
    ControllablePacManModel model;
    PacManView pacManView;


    public PacManController(ControllablePacManModel model, PacManView pacManView) {
        pacManView.setFocusable(true);

        this.model = model;
        this.pacManView = pacManView;
        pacManView.addKeyListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Left arrow was pressed
            model.movePacManLeft();
            pacManView.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Right arrow was pressed
            model.movePacManRight();
            pacManView.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Down arrow was pressed
            model.movePacManDown();
            pacManView.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Up arrow was pressed
            model.movePacManUp();
            pacManView.repaint();
        }}

    @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");}

}
