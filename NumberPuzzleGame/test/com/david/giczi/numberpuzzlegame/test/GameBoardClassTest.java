package com.david.giczi.numberpuzzlegame.test;

import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.utils.Option;
import com.david.giczi.numberpuzzlegame.view.GameBoard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GicziD
 */
public class GameBoardClassTest {

    private GameLogic logic;
    private GameBoard board;

    @Before
    public void createGameBoard() {

        logic = new GameLogic(4);
        board = new GameBoard(logic);

    }

    @Test
    public void testGameBoardConstructor() {

        assertEquals(logic, board.getLogic());
        assertNotNull(board);
        assertNotNull(board.getSecTimer());
        assertNotNull(board.getIntroTimer());
        assertNotNull(board.getSlider());
    }

    @Test
    public void testCreateBoardFrameMethod() throws InvalidInputValueException {

        board.createBoardFrame();

        assertNotNull(board.getjFrame());
        assertEquals("NumberPuzzle - 0:00", board.getjFrame().getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, board.getjFrame().getDefaultCloseOperation());
        assertFalse(board.getjFrame().isResizable());
        assertNotNull(board.getjFrame().getLayout());
        assertEquals(new Dimension(logic.getBoardSide() * 100,
                logic.getBoardSide() * 100), board.getjFrame().getSize());
        assertTrue(board.getjFrame().isVisible());

    }

    @Test
    public void testCreateOptioMenuMethod() throws InvalidInputValueException {

        board.createBoardFrame();

        assertNotNull(board.getNewGameOption());
        assertEquals("Új játék", board.getNewGameOption().getText());
        assertEquals(new Color(51, 51, 51), board.getNewGameOption().getForeground());
        assertNotNull(board.getBiggerBoardOption());
        assertEquals("Nagyobbra", board.getBiggerBoardOption().getText());
        assertEquals(new Color(51, 51, 51), board.getBiggerBoardOption().getForeground());
        assertNotNull(board.getSmallerBoardOption());
        assertEquals("Kisebbre", board.getSmallerBoardOption().getText());
        assertEquals(new Color(51, 51, 51), board.getSmallerBoardOption().getForeground());
        assertNotNull(board.getExitOption());
        assertEquals("Kilépés", board.getExitOption().getText());
        assertEquals(new Color(51, 51, 51), board.getExitOption().getForeground());

    }

    @Test
    public void testAddNumberFieldsToBoardFrameMethod() throws InvalidInputValueException {

        board.createBoardFrame();

        assertEquals(16, board.getNumberFields().size());

        board.getNumberFields().forEach(numberField
                -> assertEquals(new Font("Book Antiqua", Font.BOLD, 40), numberField.getFont()));

        board.getNumberFields().forEach(numberField
                -> assertEquals(new Color(240, 230, 140), numberField.getBackground()));

        board.getNumberFields().forEach(numberField
                -> assertEquals(new Color(0, 128, 0), numberField.getForeground()));

        board.getNumberFields().stream()
                .filter(element -> "0".equals(element.getText()))
                .forEach(element -> assertEquals("0", element.getText()));
        board.getNumberFields().stream()
                .filter(element -> "0".equals(element.getText()))
                .forEach(element -> assertFalse(element.isVisible()));

    }

    @Test
    public void testFormatSecondCounterValueMethod() {

        board.setSecondCounter(5);
        assertEquals("0:05", board.formatSecondCounterValue());

        board.setSecondCounter(15);
        assertEquals("0:15", board.formatSecondCounterValue());

        board.setSecondCounter(135);
        assertEquals("2:15", board.formatSecondCounterValue());

    }

    @Test
    public void testModifyBoardSideValueMethod() {

        int boardSideValue = board.modifyBoardSideValue(Option.SMALLER, 5);
        assertEquals(4, boardSideValue);
        boardSideValue = board.modifyBoardSideValue(Option.SMALLER, 3);
        assertEquals(3, boardSideValue);
        boardSideValue = board.modifyBoardSideValue(Option.BIGGER, 4);
        assertEquals(5, boardSideValue);
        boardSideValue = board.modifyBoardSideValue(Option.BIGGER, 5);
        assertEquals(5, boardSideValue);
        boardSideValue = board.modifyBoardSideValue(Option.NEW_GAME, 4);
        assertEquals(4, boardSideValue);

    }

    @Test
    public void testEnabledAllNumberFieldsMethod() {

        board.modifyBoardSideValue(Option.NEW_GAME, 4);

        board.getNumberFields().forEach(element -> assertTrue(element.isEnabled()));

    }

    @Test
    public void testEnabledOrDisabledSmallerBiggerOptionMethod() throws InvalidInputValueException {

        board.createBoardFrame();
        board.enabledOrDisabledSmallerBiggerOption(3);
        assertFalse(board.getSmallerBoardOption().isEnabled());
        board.enabledOrDisabledSmallerBiggerOption(4);
        assertTrue(board.getSmallerBoardOption().isEnabled());
        assertTrue(board.getBiggerBoardOption().isEnabled());
        board.enabledOrDisabledSmallerBiggerOption(5);
        assertFalse(board.getBiggerBoardOption().isEnabled());
    }

    @Test
    public void testDestroyGameBoardMethod() throws InvalidInputValueException {

        board.createBoardFrame();
        board.getIntroTimer().stop();
        board.destroyGameBoard();
        assertNull(board.getjFrame());
        assertEquals(1, board.getSecondCounter());
        assertEquals(0, board.getNumberFields().size());

    }

    @Test
    public void testChangeGameFieldsNumberColorMethod() {

        board.changeGameFieldsNumberColor(new Color(165, 42, 42));

        board.getNumberFields().forEach(element
                -> assertEquals(new Color(165, 42, 42), element.getForeground()));

    }

    @Test
    public void testGetNumberFieldIndexMethod() throws InvalidInputValueException {

        board.createBoardFrame();
        int index = board.getNumberFieldIndex(board.getNumberFields().get(0));
        assertEquals(0, index);
        index = board.getNumberFieldIndex(board.getNumberFields().get(8));
        assertEquals(8, index);
        index = board.getNumberFieldIndex(board.getNumberFields().get(15));
        assertEquals(15, index);

    }

    @Test
    public void testInitNumberFieldsMethod() throws InvalidInputValueException {

        board.createBoardFrame();
        board.initNumberFields(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));

        board.getNumberFields().forEach(field -> assertTrue(field.isEnabled()));
        board.getNumberFields().stream()
                .filter(field -> board.getNumberFields()
                .indexOf(field) < board.getNumberFields().size() - 1)
                .forEach(field -> assertTrue(field.isVisible()));

        for (int i = 0; i < board.getNumberFields().size() - 1; i++) {

            assertEquals(i + 1, Integer.parseInt(board.getNumberFields().get(i).getText()));

        }
        assertEquals(0, Integer.parseInt(board.getNumberFields()
                .get(board.getNumberFields().size() - 1).getText()));
        assertFalse(board.getNumberFields().get(board.getNumberFields().size() - 1).isVisible());
    }

    @Test
    public void testChangeNumberFields() throws InvalidInputValueException {

        board.createBoardFrame();
        board.initNumberFields(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));
        assertTrue(board.getNumberFields().get(10).isVisible());
        assertFalse(board.getNumberFields().get(15).isVisible());
        assertEquals(11, Integer.parseInt(board.getNumberFields().get(10).getText()));
        assertEquals(0, Integer.parseInt(board.getNumberFields().get(15).getText()));
        board.changeNumberFields(10, 15);
        assertTrue(board.getNumberFields().get(15).isVisible());
        assertFalse(board.getNumberFields().get(10).isVisible());
        assertEquals(11, Integer.parseInt(board.getNumberFields().get(15).getText()));

    }

    @Test
    public void TestDisbledAllNumberFieldsMethod() throws InvalidInputValueException {
        board.createBoardFrame();
        board.disbledAllNumberFields();

        board.getNumberFields().forEach(element -> assertFalse(element.isEnabled()));

    }

    @Test
    public void testAddImageIconToZeroNumberFieldMethod() throws InvalidInputValueException {

        board.createBoardFrame();
        board.addImageIconToZeroNumberField();

        assertNotNull(board.getNumberFields().stream()
                .filter(element -> element.getIcon() != null).findAny().get());
    }

    @Test
    public void testCreateSliderFrame() {

        board.createSliderFrame();

        assertNotNull(board.getSlider());
        assertNotNull(board.getSliderFrame());
        assertNotNull(board.getSliderOkButton());
        assertEquals("Ok", board.getSliderOkButton().getText());
        assertEquals(1, board.getSlider().getMinorTickSpacing());
        assertEquals(50, board.getSlider().getMajorTickSpacing());
        assertTrue(board.getSlider().getPaintTicks());
        assertTrue(board.getSlider().getPaintLabels());
        assertEquals(JFrame.HIDE_ON_CLOSE, board.getSliderFrame().getDefaultCloseOperation());
        assertFalse(board.getSliderFrame().isResizable());
        assertEquals(new Dimension(250, 115), board.getSliderFrame().getSize());
        assertTrue(board.getSliderFrame().isVisible());
    }

}
