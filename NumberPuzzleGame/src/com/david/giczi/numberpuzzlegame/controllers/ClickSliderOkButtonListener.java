package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GicziD
 */
public class ClickSliderOkButtonListener implements ActionListener {

    private final GameBoard board;

    public ClickSliderOkButtonListener(GameBoard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        board.getLogic().setNumberOfMix(board.getSlider().getValue());
        board.getNewGameOption().doClick();
        board.getSliderFrame().setVisible(false);

    }

}
