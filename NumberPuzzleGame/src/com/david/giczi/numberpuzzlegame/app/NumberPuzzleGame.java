package com.david.giczi.numberpuzzlegame.app;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.view.GameBoard;

/**
 *
 * @author GicziD
 */
public class NumberPuzzleGame {

    public static void main(String[] args) throws InvalidInputValueException {

    new GameBoard(new GameLogic(4)).createBoardFrame();
   
   
   
   
        
       
    }

}
