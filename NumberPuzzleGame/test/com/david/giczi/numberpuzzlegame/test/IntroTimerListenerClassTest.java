
package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.model.NumberSquare;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author GicziD
 */
public class IntroTimerListenerClassTest {
    
    private GameLogic logic;
    private GameBoard board;

    @Before
    public void createGameLogic() {

        logic = new GameLogic(4);
        board = new GameBoard(logic);
    }
    
    @Test
    public void testIntroTimerListener() throws InvalidInputValueException{
        
        board.createBoardFrame();
        assertTrue(logic.getNumberOfMix() == NumberSquare.getNumberBoardStore().size());
        assertTrue(board.getIntroTimer().isRunning());
        assertFalse(board.getSecTimer().isRunning());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IntroTimerListenerClassTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(0,  NumberSquare.getNumberBoardStore().size());
        assertTrue(board.getSecTimer().isRunning());
        assertFalse(board.getIntroTimer().isRunning());
    }
    
    
    
}
