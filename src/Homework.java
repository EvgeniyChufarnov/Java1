public class Homework {
    public static void main(String[] args) {
        System.out.println(calculate(3,3,5,4));
        System.out.println(isSumInTheRange(5, 13));
        isPositiveOrNegative(0);
        greetings("Александр");
        isLeapYear(2100);
    }

    // 1) Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат с плавающей точкой,
    // где a, b, c, d – целочисленные входные параметры этого метода;

    public static float calculate(int a, int b, int c, int d) {
        return (a * (b + ((float) c / d)));
    }

    // 2) Написать метод, принимающий на вход два целых числа,
    // и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
    // если да – вернуть true, в противном случае – false;

    public static boolean isSumInTheRange(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    // 3) Написать метод, которому в качестве параметра передается целое число,
    // метод должен проверить положительное ли число передали, или отрицательное.
    // Замечание: ноль считаем положительным числом. Результат работы метода вывести в консоль

    public static void isPositiveOrNegative(int number) {
        if (number >= 0) {
            System.out.println("Передано положительное число " + number);
        } else {
            System.out.println("Передано отрицательное число " + number);
        }
    }

    // 4) Написать метод, которому в качестве параметра передается строка, обозначающая имя,
    // метод должен вернуть приветственное сообщение «Привет, переданное_имя!»; Вывести приветствие в консоль.

    public static void greetings(String name) {
        System.out.println("Привет, "+name+"!");
    }

    // 5)*Написать метод, который определяет является ли год високосным. Каждый 4-й год является високосным,
    // кроме каждого 100-го, при этом каждый 400-й – високосный.
    // Для проверки работы вывести результаты работы метода в консоль

    public static void isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + " - високосный год.");
        } else {
            System.out.println(year + " - невисокосный год.");
        }
    }
}
