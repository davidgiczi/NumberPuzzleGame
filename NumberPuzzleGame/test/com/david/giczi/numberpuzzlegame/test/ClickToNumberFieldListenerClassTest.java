package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author GicziD
 */
public class ClickToNumberFieldListenerClassTest {

    private GameLogic logic;
    private GameBoard board;

    @Before
    public void createGameLogic() {

        logic = new GameLogic(4);
        board = new GameBoard(logic);
    }

    @Test
    public void testInvalidClickToNumberField() throws InvalidInputValueException {

        logic.setNumberOfMix(1);
        board.createBoardFrame();
        board.getIntroTimer().stop();
        board.getNumberFields().get(0).doClick();
        assertEquals(1, board.getClickCounter());
           
    }
    @Test
    public void testValidClickToNumberField() throws InvalidInputValueException {

        logic.setNumberOfMix(0);
        board.createBoardFrame();
        board.getIntroTimer().stop();
        board.initNumberFields(logic.getBoard());
        int emptyFieldIndex = -1;

        for (JButton numberField : board.getNumberFields()) {

            if (!numberField.isVisible()) {
                emptyFieldIndex = board.getNumberFieldIndex(numberField);
            }

        }
        
        assertFalse(board.getNumberFields().get(emptyFieldIndex).isVisible());
        
        switch(emptyFieldIndex){
             
            case 14:
                board.getNumberFields().get(10).doClick();
                assertFalse(board.getNumberFields().get(10).isVisible());
                assertTrue(board.getNumberFields().get(emptyFieldIndex).isVisible());
                assertEquals("15", board.getNumberFields().get(emptyFieldIndex).getText());  
                break;
            case 10:
                board.getNumberFields().get(6).doClick();
                assertFalse(board.getNumberFields().get(6).isVisible());
                assertTrue(board.getNumberFields().get(emptyFieldIndex).isVisible());
                assertEquals("7", board.getNumberFields().get(emptyFieldIndex).getText());   
                break;
            case 11:
                board.getNumberFields().get(7).doClick();
                assertFalse(board.getNumberFields().get(7).isVisible());
                assertTrue(board.getNumberFields().get(emptyFieldIndex).isVisible());
                assertEquals("8", board.getNumberFields().get(emptyFieldIndex).getText());  
                break;
            
        }
        
    }

}
