package homework3;

import java.util.Random;
import java.util.Scanner;

public class Homework {
    private final static char PLAYER_DOT = 'X';
    private final static char PC_DOT = '0';
    private final static char EMPTY_DOT = '_';
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Random RANDOM = new Random();

    private static int mapWidth;
    private static int mapHeight;
    private static int dotToWin;
    private static char[][] map;

    private static void init() {
        mapWidth = 5;
        mapHeight = 5;
        dotToWin = 4;
        map = new char[mapHeight][mapWidth];

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                map[i][j] = EMPTY_DOT;
            }
        }
    }

    private static void paint() {
        for (char[] row: map) {
            System.out.print("|");
            for (char element: row) {
                System.out.print(element + "|");
            }
            System.out.println();
        }
    }

    private static void playerTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите координаты X, Y");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValid(x, y) || !isEmpty(x, y));

        map[y][x] = PLAYER_DOT;
    }

    public static void aiTurn() {
        int[] coordinates;

        coordinates = tryToPut(PC_DOT); // Пытаемся выйграть, подставив свой знак
        if (coordinates[0] == -1) coordinates = tryToPut(PLAYER_DOT); // Пытаемся заблокировать ход противнику
        if (coordinates[0] == -1) {
            do {
                coordinates[0] = RANDOM.nextInt(mapWidth);
                coordinates[1] = RANDOM.nextInt(mapHeight);
            } while (!isEmpty(coordinates[0], coordinates[1]));
        }

        map[coordinates[1]][coordinates[0]] = PC_DOT;
    }

    /*
     * Подставляет переданный знак во все пустые клетки, возвращает [x,y], в случае выйгрыша,
     * возвращает [-1,-1], если выйгрыша не случилось
     */
    public static int[] tryToPut(char dot) {
        int[] position = {-1, -1};
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (map[i][j] == EMPTY_DOT) {
                    map[i][j] = dot;
                    if (isVictory(dot)) {
                        position[0] = j;
                        position[1] = i;
                        return position;
                    }
                    map[i][j] = EMPTY_DOT;
                }
            }
        }

        return position;
    }

    private static boolean isValid(int x, int y) {
        return (x >= 0 && x < mapWidth) && (y >= 0 && y < mapHeight);
    }

    private static boolean isEmpty(int x, int y) {
        return map[y][x] == EMPTY_DOT;
    }

    private static boolean isVictory(char dot) {
        StringBuilder horizontal = new StringBuilder(mapWidth);
        StringBuilder vertical = new StringBuilder(mapHeight);
        StringBuilder diagonal1 = new StringBuilder(mapHeight + 1);
        StringBuilder diagonal2 = new StringBuilder(mapHeight + 1);

        StringBuilder winningLine = new StringBuilder();
        winningLine.append(String.valueOf(dot).repeat(dotToWin));

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if (i == 0) {
                    horizontal.append(map[j]);
                    if (horizontal.toString().contains(winningLine.toString())) return true;
                    horizontal.delete(0, horizontal.length());
                }

                vertical.append(map[j][i]);
                if (i + j == mapWidth) diagonal1.append(" ");
                if (i - j == -1) diagonal2.append(" ");
                diagonal1.append((i + j < mapWidth) ? map[j][i + j] : map[j][i + j- mapWidth]);
                diagonal2.append((i - j >= 0) ? map[j][i - j] : map[j][i - j + mapWidth]);
            }

            if (vertical.toString().contains(winningLine.toString()) ||
                diagonal1.toString().contains(winningLine.toString()) ||
                diagonal2.toString().contains(winningLine.toString())) return true;

            vertical.delete(0, vertical.length());
            diagonal1.delete(0, diagonal1.length());
            diagonal2.delete(0, diagonal2.length());
        }

        return false;
    }

    private static boolean isFull() {
        for (char[] row: map) {
            for (char element: row) {
                if (element == EMPTY_DOT) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        init();
        paint();

        while (true) {
            playerTurn();
            paint();

            if (isVictory(PLAYER_DOT)) {
                System.out.println("Вы выйграли!");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

            System.out.println("Ход компьютера:");
            aiTurn();
            paint();

            if (isVictory(PC_DOT)) {
                System.out.println("Вы проиграли!");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }
}
