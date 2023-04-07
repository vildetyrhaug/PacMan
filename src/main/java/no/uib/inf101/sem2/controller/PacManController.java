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
    PacDirection direction;


    public PacManController(ControllablePacManModel model, PacManView pacManView) {
        pacManView.setFocusable(true);
        
        this.pacManView = pacManView;
        this.model = model;

        PacDirection direction = PacDirection.CENTER;

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
                    direction = PacDirection.LEFT;
                    System.out.println("left, direction:" + direction);
                    model.movePacMan(direction);
                    pacManView.repaint();
                
            }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    // Right arrow was pressed
                    // move the pacMan right
                    direction = PacDirection.RIGHT;
                    System.out.println("right, direction:" + direction);
                    model.movePacMan(direction);
                    pacManView.repaint();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    // Down arrow was pressed
                    // move the pacMan down
                    direction = PacDirection.DOWN;
                    model.movePacMan(direction);
                    pacManView.repaint();
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    // Up arrow was pressed
                    // move the pacMan up
                    direction = PacDirection.UP;
                    model.movePacMan(direction);
                    pacManView.repaint();
                
            }}}

    public void updateTimer() {
        int delay = model.getTimerDelay();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
        }

    public void clockTick(ActionEvent e) {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            System.out.println("calling clockTick");
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
