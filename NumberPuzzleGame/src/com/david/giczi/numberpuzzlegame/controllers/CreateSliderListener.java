package com.david.giczi.numberpuzzlegame.controllers;

import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GicziD
 */
public class CreateSliderListener implements ActionListener {

    private final GameBoard board;

    public CreateSliderListener(GameBoard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        board.createSliderFrame();

        String disp = board.getSlider().getValue() == 0
                ? String.valueOf(1) : String.valueOf(board.getSlider().getValue());

        board.getSliderFrame().setTitle(String.valueOf(disp));

    }

}
