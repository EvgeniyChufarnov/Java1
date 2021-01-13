package homework7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private final int SETTINGS_WINDOW_WIDTH = 400;
    private final int SETTINGS_WINDOW_HEIGHT = 400;

    private final int MIN_MAP_SIZE = 3;
    private final int MAX_MAP_SIZE = 10;

    private final String MAP_WIDTH_TEXT_PREFIX = "Horizontal cells: ";
    private final String MAP_HEIGHT_TEXT_PREFIX = "Vertical cells: ";
    private final String WIN_NUM_TEXT_PREFIX = "Number of cells to win: ";

    private MainWindow mainWindow;

    private GridLayout layout;
    private JRadioButton pveModeRadiobutton;
    private JRadioButton pvpModeRadiobutton;

    private JSlider mapWidthSlider;
    private JSlider mapHeightSlider;
    private JSlider winNumberSlider;

    private int mapWidth = MIN_MAP_SIZE;
    private int mapHeight = MIN_MAP_SIZE;
    private int winNumber = MIN_MAP_SIZE;
    private int maxWinNumber = MIN_MAP_SIZE;

    public Settings(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setTitle("Settings");

        Rectangle rect = mainWindow.getBounds();
        setLocation((int) rect.getCenterX() - SETTINGS_WINDOW_WIDTH / 2,
                (int) rect.getCenterY() - SETTINGS_WINDOW_HEIGHT / 2);
        setSize(SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOW_HEIGHT);
        setResizable(false);

        setLayout(new GridLayout(11, 1));

        initRadioButtons();
        initSliders();
        buttonInit();
    }

    private void initRadioButtons() {
        pveModeRadiobutton = new JRadioButton("Play versus AI", true);
        pvpModeRadiobutton = new JRadioButton("Play versus another player");
        ButtonGroup group = new ButtonGroup();
        group.add(pveModeRadiobutton);
        group.add(pvpModeRadiobutton);

        add(new JLabel("Choose a game mode"));
        add(pveModeRadiobutton);
        add(pvpModeRadiobutton);
    }

    private void initSliders() {
        JLabel mapWidthLabel = new JLabel(MAP_WIDTH_TEXT_PREFIX + mapWidth);
        JLabel mapHeightLabel = new JLabel(MAP_HEIGHT_TEXT_PREFIX + mapHeight);
        JLabel winNumLabel = new JLabel(WIN_NUM_TEXT_PREFIX + winNumber);

        mapWidthSlider = createSlider(MIN_MAP_SIZE, MAX_MAP_SIZE, MIN_MAP_SIZE,
                e -> onMapSizeChanged(mapWidthSlider, mapWidthLabel, MAP_WIDTH_TEXT_PREFIX));

        mapHeightSlider = createSlider(MIN_MAP_SIZE, MAX_MAP_SIZE, MIN_MAP_SIZE,
                e -> onMapSizeChanged(mapHeightSlider, mapHeightLabel, MAP_HEIGHT_TEXT_PREFIX));

        winNumberSlider = createSlider(MIN_MAP_SIZE, MIN_MAP_SIZE, MIN_MAP_SIZE,
                e -> onWinNumberChanged(winNumLabel));

        add(new JLabel("Choose the map size"));
        add(mapWidthLabel);
        add(mapWidthSlider);
        add(mapHeightLabel);
        add(mapHeightSlider);
        add(winNumLabel);
        add(winNumberSlider);
    }

    private void buttonInit() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> onStartButtonClicked());
        add(startButton);
    }

    private JSlider createSlider(int min, int max, int current, ChangeListener listener) {
        JSlider slider = new JSlider(min, max, current);
        slider.addChangeListener(listener);
        return slider;
    }

    private void onMapSizeChanged(JSlider slider, JLabel label, String prefix) {
        maxWinNumber = Integer.min(mapWidthSlider.getValue(), mapHeightSlider.getValue());
        winNumberSlider.setMaximum(maxWinNumber);
        label.setText(prefix + slider.getValue());
    }

    private void onWinNumberChanged(JLabel label) {
        label.setText(WIN_NUM_TEXT_PREFIX + winNumberSlider.getValue());
    }

    private void onStartButtonClicked() {
        int gameMode;
        if (pveModeRadiobutton.isSelected()) {
            gameMode = GameMap.PVE_MODE;
        } else if(pvpModeRadiobutton.isSelected()) {
            gameMode = GameMap.PVP_MODE;
        } else {
            throw new RuntimeException("Undefined game mode was selected");
        }

        mainWindow.startTheGame(gameMode,
                mapWidthSlider.getValue(),
                mapHeightSlider.getValue(),
                winNumberSlider.getValue());

        setVisible(false);
    }
}
