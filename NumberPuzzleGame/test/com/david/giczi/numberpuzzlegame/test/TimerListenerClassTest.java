package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author GicziD
 */
public class TimerListenerClassTest {

   private  GameLogic logic;
   private GameBoard board;

    @Before
    public void createGameLogic() {

        logic = new GameLogic(4);
       

    }
    
    @Test
    public void testTimerListener() throws InvalidInputValueException{
        
        board = new GameBoard(logic);
        board.createBoardFrame();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerListenerClassTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        board.getSecTimer().stop();
        
        assertEquals(7, board.getSecondCounter());
        
        int ellapsedSec = board.getSecondCounter()-1;
        board.setSecondCounter(ellapsedSec);
        assertEquals("NumberPuzzle - " + board.formatSecondCounterValue(), board.getjFrame().getTitle());
        
    }
    
}
