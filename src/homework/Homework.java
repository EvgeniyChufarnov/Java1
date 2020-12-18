package homework;

import java.util.Arrays;

public class Homework {
    public static void main (String[] args) {
        int[] array1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("замена 0 на 1 и наоборот:");
        System.out.println(Arrays.toString(swapValuesInArrayOfTwoValues(array1, 1, 0)));

        int[] array2 = new int[8];
        System.out.println("Заполненный прогрессией массив:");
        System.out.println(Arrays.toString(fillArrayWithProgression(array2)));

        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Удвоенные номера меньше 6:");
        System.out.println(Arrays.toString(multiplyNumberLessThenValue(array3, 6, 2)));

        System.out.println("Минимум: " + min(array3));
        System.out.println("Максимум: " + max(array3));

        int[][] firstArray5 = new int[10][10];
        System.out.println("Заполнение диагонали способом с 1 циклом:");
        visualizeAsMatrix(fillDiagonals(firstArray5, 1));
        int[][] secondArray5 = new int[10][10];
        System.out.println("Заполнение диагонали способом с 2 циклами:");
        visualizeAsMatrix(fillDiagonals2(secondArray5, 1));

        int[] array6 = {5, 1, 1, 2, 1};
        System.out.println("Равны ли стороны массива:");
        System.out.println(checkBalance(array6));

        int[] firstArray7 = {1,2,3,4,5};
        System.out.println("Смещение массива 1 циклом:");
        System.out.println(Arrays.toString(shiftArray(firstArray7, -24)));
        int[] secondArray7 = {1,2,3,4,5};
        System.out.println("Смещение массива 2 циклами:");
        System.out.println(Arrays.toString(shiftArray2(secondArray7, -24)));
        int[] thirdArray7 = {1,2,3,4,5};
        System.out.println("Смещение массива разбиением на 2:");
        System.out.println(Arrays.toString(shiftArray3(thirdArray7, -24)));
    }

    /*
     * 1 Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * Написать метод, заменяющий в  принятом массиве 0 на 1, 1 на 0;
     */

    public static int[] swapValuesInArrayOfTwoValues(int[] array, int value1, int value2) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == value1) ? value2 : value1;
        }
        return array;
    }

    /*
     * 2 Задать пустой целочисленный массив размером 8. Написать метод,
     * который c помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
     */

    public static int[] fillArrayWithProgression(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 + (3 * i);
        }
        return array;
    }

    /*
     * 3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], написать метод,
     * принимающий на вход массив и умножающий числа меньше 6 на 2;
     */

    public static int[] multiplyNumberLessThenValue(int[] array, int value, int multiplier) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < value) array[i] *= multiplier;
        }
        return array;
    }

    /*
     * 4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
     */

    public static int min(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) min = array[i];
        }
        return min;
    }

    public static int max(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        return max;
    }

    /*
     * 5 * Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
     * заполнить его диагональные элементы единицами, используя цикл(ы);
     */

    public static int[][] fillDiagonals(int[][] array, int value) { // вариант с 1 циклом
        for (int i = 0; i < array.length; i++) {
            array[i][i] = value;
            if (i != (array.length - 1) - i) { // Избегаем заполнения середины 2 раза, при нечетной длине массива
                array[i][(array.length - 1) - i] = value;
            }
        }
        return array;
    }

    public static int[][] fillDiagonals2(int[][] array, int value) { // вариант с 2 циклами
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i == j || j == (array.length - 1) - i) {
                    array[i][j] = value;
                }
            }
        }
        return array;
    }

    public static void visualizeAsMatrix(int[][] array) { // метод для отображения массива в виде матрицы в консоли
        for (int i = 0; i < array[0].length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }

    /*
     * 6 ** Написать метод, в который передается не пустой одномерный целочисленный массив,
     * метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.
     * Примеры:
     * checkBalance([1, 1, 1, || 2, 1]) → true,
     * checkBalance ([2, 1, 1, 2, 1]) → false,
     * checkBalance ([10, || 1, 2, 3, 4]) → true.
     * Абстрактная граница показана символами ||, эти символы в массив не входят.
     */

    public static boolean checkBalance(int[] array) {
        int sumLeft, sumRight;

        for (int i = 1; i < array.length; i++) {
            sumLeft = 0;
            sumRight = 0;

            for (int j = 0; j < array.length; j++) {
                if (j < i) {
                    sumLeft += array[j];
                } else {
                    sumRight += array[j];
                }
            }

            if (sumLeft == sumRight) return true;
        }

        return false;
    }

    /*
     * 7 *** Написать метод, которому на вход подаётся одномерный массив и
     * число n (может быть положительным, или отрицательным), при этом метод должен циклически
     * сместить все элементы массива на n позиций.
     * [1,2,3,4,5], -2 => [3,4,5,1,2]
     * [1,2,3,4,5], 2 => [4,5,1,2,3]
     * 8 **** Не пользоваться вспомогательным массивом при решении задачи 7.
     */

    public static int[] shiftArray(int[] array, int n) { // способ с 1 проходом по массиву
        if (Math.abs(n) % array.length == 0) return array; // возвращаем массив если сдвиг равен длине или 0

        if (Math.abs(n) > array.length) {
            n = n % array.length;  // вычисляем n, если n больше длины массива
        }

        if (n < 0) {
            n = array.length + n;  // заменяем вращение влево, вращением вправо
        }

        int output = 0;
        int input = 0;
        int newIndex;

        for (int i = 0; i < array.length; i++) {
            newIndex = ((i + 1) * n < array.length ? (i + 1) * n : ((i + 1) * n) % array.length); // вычисляем индекс, куда вписать значение
            if (i != array.length - 1) output = array[newIndex]; // сохраняем находившееся там значение
            array[newIndex] = (i == 0) ? array[i] : input; // вписываем сохраненное с предыдущей итерации значение
            if (i != array.length - 1) input = output; // освобождаем output для следующего цикла
        }

        return array;
    }

    public static int[] shiftArray2(int[] array, int n) { // способ с 2 циклами
        if (Math.abs(n) % array.length == 0) return array; // возвращаем массив если сдвиг равен длине или 0

        if (Math.abs(n) > array.length) {
            n = n % array.length;  // вычисляем n, если n больше длины массива
        }

        if (n < 0) {
            n = array.length + n;  // заменяем вращение влево, вращением вправо
        }

        int temp;

        for (int i = 0; i < n; i++) {
            temp = array[array.length - 1];
            for (int j = array.length - 1; j >= 0; j--) {
                array[j] = (j != 0) ? array[j - 1] : temp;
            }
        }

        return array;
    }

    public static int[] shiftArray3(int[] array, int n) { // способ с разбиением на 2 массива (не соотв. пункту 8 задания)
        if (Math.abs(n) % array.length == 0) return array; // возвращаем массив если сдвиг равен длине или 0

        if (Math.abs(n) > array.length) {
            n = n % array.length;  // вычисляем n, если n больше длины массива
        }

        if (n < 0) {
            n = array.length + n;  // заменяем вращение влево, вращением вправо
        }

        int[] leftPart = Arrays.copyOfRange(array, 0, array.length - n);
        int[] rightPart = Arrays.copyOfRange(array, array.length - n, array.length);

        for (int i = 0; i < array.length; i++) {
            array[i] = (i < n) ? rightPart[i] : leftPart[i - n];
        }

        return array;
    }
}
