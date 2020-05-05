
package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author GicziD
 */
public class AgainOptionListenerClassTest {
    
    private GameLogic logic;
    private GameBoard board;

    @Before
    public void createGameLogic() {

        logic = new GameLogic(4);
        board = new GameBoard(logic);
    }
    
    @Test
    public void testDoClickAgainOption() throws InvalidInputValueException{
        
        board.createBoardFrame();
        board.getAgainOption().doClick();
        
        assertEquals(logic.getBoard(), logic.getSavedBoard());
        
        for(int i = 0; i < logic.getBoard().size(); i++){
            
            assertTrue(logic.getBoard().get(i)== Integer.parseInt(board.getNumberFields().get(i).getText()));
            assertEquals(new Color(0, 128, 0), board.getNumberFields().get(i).getForeground());
        }
        
        assertTrue(0 == board.getSecondCounter());
        assertTrue(0 == board.getClickCounter());
    }
    
}
