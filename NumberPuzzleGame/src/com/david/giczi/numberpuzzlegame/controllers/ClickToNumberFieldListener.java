package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.model.NumberSquare;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author GicziD
 */
public class ClickToNumberFieldListener implements ActionListener {

    private final GameBoard board;

    public ClickToNumberFieldListener(GameBoard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        countClick();
        int clickedFieldIndex = board.getNumberFieldIndex((JButton) e.getSource());
        int resultIndex = board.getLogic().evaluateWhereThePlayerCanStep(clickedFieldIndex);
        if (isInvalidClickedGameField(resultIndex)) {
            return;
        }

        board.changeNumberFields(clickedFieldIndex, resultIndex);
        
        closeOrContinueTheGame();
        
    }

    private boolean isInvalidClickedGameField(int clickedFieldIndex) {

        if (clickedFieldIndex == -1) {

            board.infoWindow("Nem léptethető mező.");
            return true;
        }
        return false;
    }

    private void closeOrContinueTheGame() {

        if (board.getLogic().isTheEndOfTheGame()) {

            board.changeGameFieldsNumberColor(new Color(165, 42, 42));
            board.addImageIconToZeroNumberField();
            board.getSecTimer().stop();
            board.getjFrame().setTitle("NumberPuzzle - "+ board.formatSecondCounterValue());
            board.infoWindow("Az időeredményed: "
            + board.formatSecondCounterValue().split(":")[0]+" perc "
            + board.formatSecondCounterValue().split(":")[1]+" mperc\n"
            + "Maximálisan szükséges kattintások száma: "+NumberSquare.getNeedfulClickCounter()+" db\n"
            + "Kattintásaid száma: "+board.getClickCounter()+" db\n\n"+getMessage());

            if (board.questionWindow("Szeretnél új játékot játszani?")) {

                board.getNewGameOption().doClick();
            } else {

                if (board.questionWindow("Szeretnél kilépni a játékból?")) {

                   System.exit(0);
                    
                } else {

                    board.disbledAllNumberFields();
                }

            }

        }

    }
    
    private void countClick(){
        
        int clickPcs = board.getClickCounter();
        clickPcs++;
        board.setClickCounter(clickPcs);
        
    }

    private String getMessage(){
        
      String msg = (board.getClickCounter() <= NumberSquare.getNeedfulClickCounter()) ?
                   "Gratulálunk!".toUpperCase() : "Sebaj, legközelebb!"; 
     
      return msg;
    }
    
}
