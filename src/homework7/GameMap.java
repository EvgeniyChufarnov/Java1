package homework7;

import javax.swing.*;
import java.awt.*;

public class GameMap extends JPanel {
    public final static int PVE_MODE = 1;
    public final static int PVP_MODE = 2;

    private final static int PANEL_WIDTH = 584;
    private final static int PANEL_HEIGHT = 535;

    private int gameMode;
    private int mapWidth;
    private int mapHeight;
    private int winNum;

    public void starNewGame(int gameMode, int mapWidth, int mapHeight, int winNum) {
        this.gameMode = gameMode;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.winNum = winNum;

        drawMap();
    }

    // Расчертил линиями, вряд ли это пригодится дальше, т.к. нужны какие-то обекты для регистрации клика,
    // и вокруг них логичнее делать рамки, а если пустая клетка это картинка, то она уже может быть с рамкой,
    // но в рамках этой домашки пока так.
    private void drawMap() {
        Graphics graphics = getGraphics();

        graphics.clearRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        int step = Integer.min(PANEL_WIDTH / mapWidth, PANEL_HEIGHT / mapHeight);
        int mapSizeX = step * mapWidth;
        int mapSizeY = step * mapHeight;
        int paddingX = (PANEL_WIDTH - mapSizeX) / 2;
        int paddingY = (PANEL_HEIGHT - mapSizeY) / 2;

        for (int i = 0; i < mapWidth + 1; i++) {
            graphics.drawLine(paddingX + step * i, paddingY, paddingX + step * i, PANEL_HEIGHT - paddingY);
        }

        for (int i = 0; i < mapHeight + 1; i++) {
            graphics.drawLine(paddingX,paddingY + step * i, PANEL_WIDTH - paddingX, paddingY + step * i);
        }
    }
}
