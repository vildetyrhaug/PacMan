package no.uib.inf101.sem2.model.pacManModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;

public class SetGameStateTest {
    
    @Test
    public void testSetGameState() {
        PacManBoard board = new PacManBoard(new String[]{
            "###",
            "ooo",
            "ooo",
            "###"
        });
        PacManModel model = new PacManModel(board);

        // Test that the game state is not GAME_OVER
        assertNotEquals(model.getGameState(), GameState.GAME_OVER);

        // Set the game state to GAME_OVER
        model.setGameState(GameState.GAME_OVER);

        // Test that the game state is now GAME_OVER
        assertEquals(model.getGameState(), GameState.GAME_OVER);

        // Set the game state to ACTIVE_GAME
        model.setGameState(GameState.ACTIVE_GAME);

        // Test that the game state is now ACTIVE_GAME
        assertEquals(model.getGameState(), GameState.ACTIVE_GAME);

        // Set the game state to GAME_WON
        model.setGameState(GameState.GAME_WON);

        // Test that the game state is now GAME_WON
        assertEquals(model.getGameState(), GameState.GAME_WON);
        
}
}

