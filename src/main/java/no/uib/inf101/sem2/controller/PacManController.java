package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;

import javax.swing.Timer;

import java.awt.event.ActionEvent;

import no.uib.inf101.sem2.Main;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.PacDirection;
import no.uib.inf101.sem2.view.PacManView;

public class PacManController implements java.awt.event.KeyListener {
    
    ControllablePacManModel model;
    PacManView pacManView;
    Timer timer;
    Main main;

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
                if(model.getGameState()!= GameState.START_GAME){
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_LEFT -> {
                            // Left arrow was pressed
                            // move the pacMan left
                            model.setDirection(PacDirection.LEFT);
                            pacManView.repaint();
                        }
                        case KeyEvent.VK_RIGHT -> {
                            // Right arrow was pressed
                            // move the pacMan right
                            model.setDirection(PacDirection.RIGHT);
                            pacManView.repaint();
                        }
                        case KeyEvent.VK_DOWN -> {
                            // Down arrow was pressed
                            // move the pacMan down
                            model.setDirection(PacDirection.DOWN);
                            pacManView.repaint();
                        }
                        case KeyEvent.VK_UP -> {
                            // Up arrow was pressed
                            // move the pacMan up
                            model.setDirection(PacDirection.UP);
                            pacManView.repaint();
                        }
                        case KeyEvent.VK_P -> {
                            // p was pressed 
                            // pause the game
                            switch(model.getGameState()){
                                case ACTIVE_GAME -> model.setGameState(GameState.PAUSE_GAME);
                                case PAUSE_GAME -> model.setGameState(GameState.ACTIVE_GAME);
                            }   pacManView.repaint();
                        }
                        case KeyEvent.VK_ESCAPE -> {
                            // Escape was pressed
                            // exit the game
                            System.exit(0);
                        }
                    }}
                // GameState is START_GAME
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // Space was pressed
                    // start the game
                    model.setGameState(GameState.ACTIVE_GAME);
                    pacManView.repaint();
                }
                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // Escape was pressed
                    // exit the game
                    System.exit(0);
                }
            }
             // GameState is GAME_OVER
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // Space was pressed
                // start new game
                newGame();
                updateTimer();
            }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                // Escape was pressed
                // exit the game
                System.exit(0);
            }
        }
        

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
    public void newGame() {
       

        // Reset the game state
        model.setGameState(GameState.ACTIVE_GAME);
        
        // Reset the moving Pac-Man's initial position
        model.resetPacMan();
            
        // Reset the score
        model.resetScore();

        // Reset the ghosts
        model.resetGhosts();
            
        // Start the timer again
        timer.start();
            
        // Repaint the view
        pacManView.repaint(); 
        }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    /* public void updateScore() {
        pacManView.updateScore();
    } */




   





    


   
}
