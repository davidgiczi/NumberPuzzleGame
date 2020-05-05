
package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.model.NumberSquare;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GicziD
 */
public class IntroTimerListener implements ActionListener{

    private final GameBoard board;
    private int counter = -1;

    public IntroTimerListener(GameBoard board) {
        this.board = board;
    }
    
      
    @Override
    public void actionPerformed(ActionEvent e) {
        
       counter++;
        
      if( counter < board.getLogic().getNumberOfMix() ){
          
          board.initNumberFields(NumberSquare.getNumberBoardStore().get(counter));
        
      }
      else{
          
          NumberSquare.clearBoardStore();
          counter = -1;
          board.getIntroTimer().stop();
          board.getSecTimer().start();
      }
        
       
    }
 
    
    
    
}
