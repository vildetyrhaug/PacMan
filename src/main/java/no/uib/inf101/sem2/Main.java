package no.uib.inf101.sem2;

import javax.swing.JFrame;

import no.uib.inf101.sem2.controller.PacManController;
import no.uib.inf101.sem2.ghost.GhostFactory;
import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;
import no.uib.inf101.sem2.view.PacManView;

public class Main {
  public static void main(String[] args) {
   
    PacManBoard board = new PacManBoard(
      new String[]{
      "###################",
      "#     #     #     #",
      "# ### # ### # ### #",
      "#   f    #    f   #",
      "## ## ##   ## ## ##",
      "#     ### ###     #",
      "# ### ##   ## ### #",
      "# ##           ## #",
      "# #  # ## ## #  # #",
      "       #GGG#       ",
      "# #  # ##### #  # #",
      "# ##     P     ## #",
      "# ### ##   ## ### #",
      "#     ### ###     #",
      "## ## ##   ## ## ##",
      "#   f    #    f   #",
      "# ### # ### # ### #",
      "#     #     #     #",
      "###################"
    });

    PacManFactory pacManFactory = new RandomPacManFactory();
    GhostFactory ghostFactory = new RandomGhostFactory();

    PacManModel model = new PacManModel(board, pacManFactory, ghostFactory, 4);
    PacManView view = new PacManView(model);

    PacManController controller = new PacManController(model, view);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
