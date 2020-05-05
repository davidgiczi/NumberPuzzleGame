package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import javax.swing.JFrame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GicziD
 */
public class ModifyBoardSizeListenerClassTest {

   private GameLogic logic;
   private GameBoard board;

    @Before
    public void createGameLogic() {

        logic = new GameLogic(4);

    }

    @Test
    public void testClickBiggerBoardOption() throws InvalidInputValueException {

        board = new GameBoard(logic);
        assertEquals(4, board.getLogic().getBoardSide());
        board.createBoardFrame();
        board.getIntroTimer().stop();
        assertTrue(board.getBiggerBoardOption().isEnabled());
        assertTrue(board.getSmallerBoardOption().isEnabled());
        JFrame beforeClickFrame = board.getjFrame();
        board.getBiggerBoardOption().doClick();
        assertEquals(5, board.getLogic().getBoardSide());
        JFrame afterClickFrame = board.getjFrame();
        assertNotEquals(beforeClickFrame, afterClickFrame);
        assertFalse(board.getBiggerBoardOption().isEnabled());
        assertTrue(board.getSmallerBoardOption().isEnabled());

    }

    
    @Test
    public void testClickSmallerBoardOption() throws InvalidInputValueException{
        
        board = new GameBoard(logic);
        assertEquals(4, board.getLogic().getBoardSide());
        board.createBoardFrame();
        board.getIntroTimer().stop();
        assertTrue(board.getBiggerBoardOption().isEnabled());
        assertTrue(board.getSmallerBoardOption().isEnabled());
        JFrame beforeClickFrame = board.getjFrame();
        board.getSmallerBoardOption().doClick();
        assertEquals(3, board.getLogic().getBoardSide());
        JFrame afterClickFrame = board.getjFrame();
        assertNotEquals(beforeClickFrame, afterClickFrame);
        assertTrue(board.getBiggerBoardOption().isEnabled());
        assertFalse(board.getSmallerBoardOption().isEnabled());
    }
    
}
