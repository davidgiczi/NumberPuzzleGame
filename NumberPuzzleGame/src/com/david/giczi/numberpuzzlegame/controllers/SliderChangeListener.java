
package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.view.GameBoard;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author GicziD
 */
public class SliderChangeListener implements ChangeListener{

    
    private final GameBoard board;

    public SliderChangeListener(GameBoard board) {
        this.board = board;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
         String disp = board.getSlider().getValue() == 0 ?
                String.valueOf(1) : String.valueOf(board.getSlider().getValue());
        
         board.getSliderFrame().setTitle(disp);
    }

   
    
   
    
   
    
}
