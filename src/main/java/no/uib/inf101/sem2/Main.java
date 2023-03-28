package no.uib.inf101.sem2;

import javax.swing.JFrame;

import no.uib.inf101.sem2.pacMan.model.PacManBoard;
import no.uib.inf101.sem2.pacMan.model.PacManModel;
import no.uib.inf101.sem2.pacMan.view.PacManView;

public class Main {
  public static void main(String[] args) {
    PacManBoard board = new PacManBoard(20, 20);
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
