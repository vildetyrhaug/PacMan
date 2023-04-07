package no.uib.inf101.sem2;

import javax.swing.JFrame;

import no.uib.inf101.sem2.controller.PacManController;
import no.uib.inf101.sem2.model.PacDirection;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;
import no.uib.inf101.sem2.view.PacManView;

public class Main {
  public static void main(String[] args) {
   
    PacManBoard board = new PacManBoard(11, 21);

    PacManFactory factory = new RandomPacManFactory();

    PacManModel model = new PacManModel(board, factory);
    PacManView view = new PacManView(model);

    PacManController controller = new PacManController(model, view);
    PacDirection direction = PacDirection.CENTER;


    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
