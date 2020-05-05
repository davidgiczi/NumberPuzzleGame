
package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author GicziD
 */
public class CreateSliderListenerClassTest {
    
    
    private GameLogic logic;
    private GameBoard board;

    @Before
    public void createGameLogic() {

        logic = new GameLogic(4);
        board = new GameBoard(logic);
    }
    
    
    @Test
    public void testDoClickSettingOptionIfSliderValueIsDefault() throws InvalidInputValueException{
        
        board.createBoardFrame();
        board.getSettingOption().doClick();
        assertTrue(board.getSliderFrame().isVisible());
        assertEquals("10", board.getSliderFrame().getTitle());
              
    }
    
    @Test
    public void testDoClickSettingOptionIfSliderValueIsZero() throws InvalidInputValueException{
        
        board.createBoardFrame();
        board.getSlider().setValue(0);
        board.getSettingOption().doClick();
        assertEquals("1", board.getSliderFrame().getTitle());
        
        
    }
    
    @Test
    public void testDoClickSettingOptionIfSliderValueIsOneHundred() throws InvalidInputValueException{
        
        board.createBoardFrame();
        board.getSlider().setValue(100);
        board.getSettingOption().doClick();
        assertEquals("100", board.getSliderFrame().getTitle());
        
        
    }
    
    
    
}
