package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;

import javax.swing.Timer;

import java.awt.event.ActionEvent;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.PacDirection;
import no.uib.inf101.sem2.view.PacManView;

public class PacManController implements java.awt.event.KeyListener {
    
    ControllablePacManModel model;
    PacManView pacManView;
    Timer timer;


    public PacManController(ControllablePacManModel model, PacManView pacManView) {
        pacManView.setFocusable(true);
        
        this.pacManView = pacManView;
        this.model = model;

        //timer
        this.timer = new Timer(model.getTimerDelay(), this::clockTick);;
        this.timer.start();


        pacManView.addKeyListener(this);

    }
 
    @Override
        public void keyPressed(KeyEvent e) {
            if (model.getGameState() != GameState.GAME_OVER){
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    // Left arrow was pressed
                    // move the pacMan left
                    model.setDirection(PacDirection.LEFT);
                    pacManView.repaint();
            }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    // Right arrow was pressed
                    // move the pacMan right
                    model.setDirection(PacDirection.RIGHT);
                    pacManView.repaint();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    // Down arrow was pressed
                    // move the pacMan down
                    model.setDirection(PacDirection.DOWN);
                    pacManView.repaint();
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    // Up arrow was pressed
                    // move the pacMan up
                    model.setDirection(PacDirection.UP);
                    pacManView.repaint();
                }}}

    public void updateTimer() {
        int delay = model.getTimerDelay();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
        }

    public void clockTick(ActionEvent e) {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.clockTick();
            // kall til hjelpemetoden som oppdaterer delay for timeren
            updateTimer();
            pacManView.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }




   





    


   
}
