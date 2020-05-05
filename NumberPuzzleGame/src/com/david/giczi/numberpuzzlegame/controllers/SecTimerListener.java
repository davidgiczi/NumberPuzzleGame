package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GicziD
 */
public class SecTimerListener implements ActionListener {

    private final GameBoard board;

    public SecTimerListener(GameBoard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String displaySec = board.formatSecondCounterValue();
        board.getjFrame().setTitle("NumberPuzzle - " + displaySec);
        int ellapsedSec = board.getSecondCounter();
        ellapsedSec++;
        board.setSecondCounter(ellapsedSec);

    }

}
