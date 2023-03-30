package no.uib.inf101.sem2;

import javax.swing.JFrame;

import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.view.PacManView;

public class Main {
  public static void main(String[] args) {
   
    PacManBoard board = new PacManBoard(11, 21);
    PacManModel model = new PacManModel(board);
    PacManView view = new PacManView(model);


    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
