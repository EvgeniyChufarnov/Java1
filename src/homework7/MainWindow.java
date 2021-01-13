package homework7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 600;

    private JButton settingsButton;
    private JButton quitButton;

    private Settings settingsWindow;
    private GameMap gameMap;

    public MainWindow() {
        init();
        bottomPanelInit();
        settingsWindow = new Settings(this);
        gameMap = new GameMap();
        add(gameMap, BorderLayout.CENTER);
    }

    private void init() {
        setTitle("Tic Tac Toe");

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation((screenWidth - WINDOW_WIDTH) /2, (screenHeight - WINDOW_HEIGHT) /2);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    private void bottomPanelInit() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        settingsButton = new JButton("Settings");
        bottomPanel.add(settingsButton);
        settingsButton.addActionListener(e -> onSettingsClicked());

        quitButton = new JButton("Quit");
        bottomPanel.add(quitButton);
        quitButton.addActionListener(e -> onQuitClicked());

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void onSettingsClicked() {
        settingsWindow.setVisible(true);
    }

    private void onQuitClicked() {
        System.exit(0);
    }

    public void startTheGame(int gameMode, int mapWidth, int mapHeight, int winNum) {
        gameMap.starNewGame(gameMode, mapWidth, mapHeight, winNum);
    }
}
