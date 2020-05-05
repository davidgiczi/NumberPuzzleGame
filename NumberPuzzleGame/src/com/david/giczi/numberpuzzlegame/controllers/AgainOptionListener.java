package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GicziD
 */
public class AgainOptionListener implements ActionListener {

    private final GameBoard board;

    public AgainOptionListener(GameBoard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        board.getSecTimer().stop();
        board.getLogic().setBoard(board.getLogic().getSavedBoard());
        board.initNumberFields(board.getLogic().getSavedBoard());
        board.changeGameFieldsNumberColor(new Color(0, 128, 0));
        board.setSecondCounter(0);
        board.setClickCounter(0);
        board.getSecTimer().start();

    }

}
