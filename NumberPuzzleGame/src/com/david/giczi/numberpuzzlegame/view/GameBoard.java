package com.david.giczi.numberpuzzlegame.view;

import com.david.giczi.numberpuzzlegame.controllers.AgainOptionListener;
import com.david.giczi.numberpuzzlegame.controllers.ClickSliderOkButtonListener;
import com.david.giczi.numberpuzzlegame.controllers.ClickToNumberFieldListener;
import com.david.giczi.numberpuzzlegame.controllers.CreateSliderListener;
import com.david.giczi.numberpuzzlegame.controllers.ExitGameListener;
import com.david.giczi.numberpuzzlegame.controllers.IntroTimerListener;
import com.david.giczi.numberpuzzlegame.controllers.ModifyBoardSizeListener;
import com.david.giczi.numberpuzzlegame.controllers.SecTimerListener;
import com.david.giczi.numberpuzzlegame.controllers.SliderChangeListener;
import com.david.giczi.numberpuzzlegame.exception.InvalidInputValueException;
import com.david.giczi.numberpuzzlegame.model.GameLogic;
import com.david.giczi.numberpuzzlegame.model.NumberSquare;
import com.david.giczi.numberpuzzlegame.utils.Option;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

/**
 *
 * @author GicziD
 */
public class GameBoard {

    private GameLogic logic;
    private final List<JButton> numberFields;
    private final Timer secTimer;
    private final Timer introTimer;
    private JFrame jFrame;
    private int secondCounter;
    private int clickCounter;
    private JMenuItem biggerBoardOption;
    private JMenuItem smallerBoardOption;
    private JMenuItem newGameOption;
    private JMenuItem exitOption;
    private JFrame sliderFrame;
    private final JSlider slider;
    private JButton sliderOkButton;
    private JMenuItem settingOption;
    private JMenuItem againOption;
    
    public GameBoard(GameLogic logic) {
        this.logic = logic;
        numberFields = new ArrayList<>();
        secTimer = new Timer(1000, new SecTimerListener(this));
        introTimer = new Timer(200, new IntroTimerListener(this));
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
    }

    public GameLogic getLogic() {
        return logic;
    }

    public void setLogic(GameLogic logic) {
        this.logic = logic;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public int getSecondCounter() {
        return secondCounter;
    }

    public void setSecondCounter(int secondCounter) {
        this.secondCounter = secondCounter;
    }

    public Timer getSecTimer() {
        return secTimer;
    }

    public Timer getIntroTimer() {
        return introTimer;
    }

    public List<JButton> getNumberFields() {
        return numberFields;
    }

    public JMenuItem getNewGameOption() {
        return newGameOption;
    }

    public JMenuItem getExitOption() {
        return exitOption;
    }

    public JMenuItem getBiggerBoardOption() {
        return biggerBoardOption;
    }

    public JMenuItem getSmallerBoardOption() {
        return smallerBoardOption;
    }

    public int getClickCounter() {
        return clickCounter;
    }

    public void setClickCounter(int clickCounter) {
        this.clickCounter = clickCounter;
    }

    public JSlider getSlider() {
        return slider;
    }

    public JFrame getSliderFrame() {
        return sliderFrame;
    }

    public JButton getSliderOkButton() {
        return sliderOkButton;
    }

    public JMenuItem getSettingOption() {
        return settingOption;
    }

    public JMenuItem getAgainOption() {
        return againOption;
    }
         
    public void createBoardFrame() throws InvalidInputValueException {

        jFrame = new JFrame("NumberPuzzle - 0:00");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLayout(new GridLayout(logic.getBoardSide(), logic.getBoardSide()));
        jFrame.setSize(logic.getBoardSide() * 100, logic.getBoardSide() * 100);
        jFrame.setLocationRelativeTo(null);

        createOptionMenu();
        addNumberFieldsToBoardFrame();
        mixBoardNumber();

        introTimer.start();
        jFrame.setVisible(true);

    }

    private void createOptionMenu() {

        Color fc = new Color(105, 105, 105);

        JMenuBar menuBar = new JMenuBar();
        JMenu option = new JMenu("Opciók");
        option.setForeground(fc);
        settingOption = new JMenuItem("Keverés");
        settingOption.addActionListener(new CreateSliderListener(this));
        againOption = new JMenuItem("Újra");
        againOption.addActionListener(new AgainOptionListener(this));
        newGameOption = new JMenuItem("Új játék");
        newGameOption.addActionListener(new ModifyBoardSizeListener(this,
                Option.NEW_GAME));
        biggerBoardOption = new JMenuItem("Nagyobbra");
        biggerBoardOption.addActionListener(new ModifyBoardSizeListener(this,
                Option.BIGGER));
        smallerBoardOption = new JMenuItem("Kisebbre");
        smallerBoardOption.addActionListener(new ModifyBoardSizeListener(this,
                Option.SMALLER));
        exitOption = new JMenuItem("Kilépés");
        exitOption.addActionListener(new ExitGameListener(this));

        option.add(settingOption);
        option.addSeparator();
        option.add(againOption);
        option.add(newGameOption);
        option.add(biggerBoardOption);
        option.add(smallerBoardOption);
        option.addSeparator();
        option.add(exitOption);
        menuBar.add(option);

        jFrame.setJMenuBar(menuBar);

    }

    private void addNumberFieldsToBoardFrame() {

        Font font = new Font("Book Antiqua", Font.BOLD, 40);
        Color bc = new Color(240, 230, 140);
        Color fc = new Color(0, 128, 0);
        
        initGameBoard();

        numberFields.forEach(element -> element.setFont(font));
        numberFields.forEach(element -> element.setBackground(bc));
        numberFields.forEach(element -> element.setForeground(fc));
        numberFields.forEach(element -> element.
                addActionListener(new ClickToNumberFieldListener(this)));
        numberFields.stream().filter(element -> "0".equals(element.getText()))
                .forEach(element -> element.setVisible(false));
        numberFields.forEach(element -> jFrame.add(element));

    }

    private void mixBoardNumber() throws InvalidInputValueException {

        logic.mixGameBoardNumber();

        while (logic.isTheEndOfTheGame()) {
            NumberSquare.clearBoardStore();
            logic.mixGameBoardNumber();
        }

    }

    private void initGameBoard() {

        logic.initGameBoard();

        logic.getBoard().forEach(element
                -> numberFields
                        .add(new JButton(String.valueOf(element))));

    }

    public void initNumberFields(List<Integer> board) {

        enabledAllNumberFields();

        numberFields.get(numberFields.size() - 1).setIcon(null);

        for (int i = 0; i < numberFields.size(); i++) {

            numberFields.get(i).setText(String.valueOf(board.get(i)));

            if (board.get(i) == 0) {

                numberFields.get(i).setVisible(false);
            } else {
                numberFields.get(i).setVisible(true);

            }

        }

    }

    private void enabledAllNumberFields() {

        numberFields.forEach(element -> element.setEnabled(true));
    }

    public String formatSecondCounterValue() {

        int sec = secondCounter % 60;
        int min = secondCounter / 60;

        return sec < 10 ? min + ":0" + sec : min + ":" + sec;
    }

    public boolean questionWindow(String text) {

        Object[] options = {"Igen",
            "Nem"};

        return JOptionPane.YES_OPTION
                == JOptionPane.showOptionDialog(jFrame, text, "NumberPuzzle",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    }

    public void infoWindow(String text) {

        JOptionPane
                .showMessageDialog(
                        jFrame, text, "NumberPuzzle", JOptionPane.INFORMATION_MESSAGE);

    }

    public int modifyBoardSideValue(Option option, int boardSideValue) {

        if (option == Option.SMALLER && boardSideValue > 3) {
            boardSideValue--;
        } else if (option == Option.BIGGER && boardSideValue < 5) {
            boardSideValue++;
        } else if (option == Option.NEW_GAME) {

            enabledAllNumberFields();
        }

        return boardSideValue;
    }

    public void enabledOrDisabledSmallerBiggerOption(int boardSideValue) {

        if (boardSideValue == 3) {
            smallerBoardOption.setEnabled(false);
        } else if (boardSideValue > 3 && boardSideValue < 5) {
            smallerBoardOption.setEnabled(true);
            biggerBoardOption.setEnabled(true);
        } else if (boardSideValue == 5) {
            biggerBoardOption.setEnabled(false);
        }

    }

    public void destroyGameBoard() {

        jFrame.setVisible(false);
        jFrame = null;
        secondCounter = 1;
        clickCounter = 0;
        numberFields.clear();
    }

    public void changeGameFieldsNumberColor(Color fc) {

        numberFields.forEach(element -> element.setForeground(fc));

    }

    public int getNumberFieldIndex(JButton clickedField) {

        return numberFields.indexOf(clickedField);
    }

    public void changeNumberFields(int clickedFieldIndex, int resultIndex) {

        String clickedFieldValue = numberFields.get(clickedFieldIndex).getText();
        numberFields.get(clickedFieldIndex).setVisible(false);
        numberFields.get(resultIndex).setVisible(true);
        numberFields.get(resultIndex).setText(clickedFieldValue);
    }

    public void disbledAllNumberFields() {

        numberFields.stream().forEach(element -> element.setEnabled(false));
    }

    public void addImageIconToZeroNumberField() {

        ImageIcon img = new ImageIcon("./icon/welldone.jpg");
        JButton zeroButton = numberFields.stream()
                .filter(element -> !element.isVisible()).findAny().get();
        zeroButton.setText(null);
        zeroButton.setVisible(true);
        zeroButton.setIcon(img);
    }

    public void createSliderFrame() {

        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderChangeListener(this));
        sliderOkButton = new JButton("Ok");
        sliderOkButton.addActionListener(new ClickSliderOkButtonListener(this));

        sliderFrame = new JFrame();

        sliderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        sliderFrame.setResizable(false);
        sliderFrame.setSize(250, 115);
        sliderFrame.setLocationRelativeTo(null);

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(slider);
        sliderPanel.add(sliderOkButton);

        sliderFrame.add(sliderPanel);
        sliderFrame.setVisible(true);
    }
}
