
package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
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
public class ClickSliderOkButtonListenerClassTest {
    
   
    private GameLogic logic;
    private GameBoard board;

    @Before
    public void createGameLogic() {

        logic = new GameLogic(4);
        board = new GameBoard(logic);
    }
    
    
    @Test
    public void testDoClickSliderOkButtonIfSliderValueIsDeafult() throws InvalidInputValueException{
        
        board.createBoardFrame();
        board.getIntroTimer().stop();
        board.getSettingOption().doClick();
        board.getSliderOkButton().doClick();
        
        assertTrue(logic.getNumberOfMix() == board.getSlider().getValue());
        assertFalse(board.getSliderFrame().isVisible());
        
    }
    
    @Test
    public void testDoClickSliderOkButtonIfSliderValueIsZero() throws InvalidInputValueException{
        
        board.createBoardFrame();        
        board.getSettingOption().doClick();
        board.getSlider().setValue(0);
        board.getSliderOkButton().doClick();
        
        assertTrue(logic.getNumberOfMix() == 1);
        assertFalse(board.getSliderFrame().isVisible());
        
    }
    
    @Test
    public void testDoClickSliderOkButtonIfSliderValueIsOneHundred() throws InvalidInputValueException{
        
        board.createBoardFrame();
        board.getIntroTimer().stop();
        board.getSettingOption().doClick();
        board.getSlider().setValue(100);
        board.getSliderOkButton().doClick();
        
        assertTrue(logic.getNumberOfMix() == 100);
        assertFalse(board.getSliderFrame().isVisible());
        
    }
    
    
}
