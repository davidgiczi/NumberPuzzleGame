package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.NumberSquare;
import com.david.giczi.numberpuzzlegame.utils.Option;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GicziD
 */
public class ModifyBoardSizeListener implements ActionListener {

    private final GameBoard board;
    private final Option option;

    public ModifyBoardSizeListener(GameBoard board, Option option) {
        this.board = board;
        this.option = option;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        board.getSecTimer().stop();
        board.destroyGameBoard();
        int boardSideValue = board.getLogic().getBoardSide();
        int newBoardSideValue = board.modifyBoardSideValue(option, boardSideValue);
        board.getLogic().setBoardSide(newBoardSideValue);
        NumberSquare.clearBoardStore();
        try {
            board.createBoardFrame();
        } catch (InvalidInputValueException ex) {
            Logger.getLogger(ModifyBoardSizeListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        board.enabledOrDisabledSmallerBiggerOption(newBoardSideValue);

    }

}
