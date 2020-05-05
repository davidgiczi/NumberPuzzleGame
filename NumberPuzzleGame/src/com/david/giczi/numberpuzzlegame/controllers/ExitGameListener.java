package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GicziD
 */
public class ExitGameListener implements ActionListener {

    private final GameBoard board;

    public ExitGameListener(GameBoard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (board.questionWindow("Szeretnél kilépni a játékból?")) {
            System.exit(0);
        }

    }

}
