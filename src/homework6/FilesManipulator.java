package homework6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Так как мы точно не знаем, что нам придёт в качестве аргументов и существуют ли такие файлы
 * выкидываем исключения вовне, чтобы они обрабатывались вызывающими методами
 * Также использовал запись try-with-resource, которая автоматически закрывает потоки, в случае исключений
 */

public class FilesManipulator {
    /*
     * Метод копирует содержимое файлов inputFileNames в файл outputFileName, флаг override означает, нужно ли
     * перезаписывать файл, если он существует.
     */
    public static void MergeTxtFiles(String[] inputFileNames, String outputFileName, boolean override) throws IOException {
        for (String inputFileName : inputFileNames) {
            if (!isTxt(inputFileName)) {
                throw new IOException("wrong extension of input file " + inputFileName);
            } else if (Files.notExists(Path.of(inputFileName))) { // Предотвращаем начало выполнения сразу, если, например, не найден последний файл
                throw new IOException("input file " + inputFileName + " was not found");
            } else if (inputFileName.equals(outputFileName)) {
                throw new IOException(inputFileName + " is equal to outputFileName");
            }
        }

        if (!isTxt(outputFileName))
            throw new IOException("wrong extension of output file");

        if (!override && Files.exists(Path.of(outputFileName)))
            throw new IOException("file is already exists");

//        Для 1 варианта
        try (BufferedWriter bw = new BufferedWriter (new FileWriter(outputFileName))) {
            for (String inputFileName : inputFileNames) {
                copyToFile(inputFileName, bw);
            }
        }
//        Для 2 варианта
//        try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
//            for (String inputFileName : inputFileNames) {
//                copyToFile2(inputFileName, fos);
//            }
//        }
    }

    /*
     * 1 вариант.
     * Копирует файл в поток, с помощью BufferedInputStream
     */
    private static void copyToFile(String inputFileName, BufferedWriter bw) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String nextLn;

            while ((nextLn = br.readLine()) != null) {
                bw.write(nextLn);
                bw.newLine();
            }
        }
    }

    /*
     * 2 вариант.
     * Копирует файл в поток встроенными средстами языка
     */
    private static void copyToFile2(String inputFileName, FileOutputStream fos) throws IOException {
        Files.copy(Path.of(inputFileName), fos);
    }

    /*
     * Проверяем, является ли файл .txt
     */
    private static boolean isTxt(String fileName) {
        return fileName.matches(".+(\\.txt)$");
    }

    /*
     * Вариант 1 поиска строки в файле
     * Выгружаем построчно строки из файла и ищем в них необходимую строку
     * Возвращаем true, если строка найда, false - если нет
     */
    public static boolean contentsInTxtFile(String fileName, String stringToFind) throws IOException {
        if (!isTxt(fileName))
            throw new IOException("wrong file extension");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(stringToFind))
                    return true;
            }
        }

        return false;
    }

    /*
     * Вариант 2 поиска строки в файле
     * Загружаем файл в ArrayList, как int и ищем совпадение алгоритмом Кнута — Морриса — Пратта
     * возвращаем индекс первого вхождения, либо -1, если строка не найдена
     */
    public static int contentsInTxtFile2(String fileName, String stringToFind) throws IOException {
        if (!isTxt(fileName))
            throw new IOException("wrong file extension");

        char[] charsToFind = stringToFind.toCharArray();
        ArrayList<Integer> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int i;
            while ((i = br.read()) != -1) {
                data.add(i);
            }
        }

        return KnuthMorrisPrattAlgorithm(data, charsToFind);
    }

    /*
     * Собственно сам алгоритм, списывал с псевдокода с википедии
     * https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
     */
    private static int KnuthMorrisPrattAlgorithm(ArrayList<Integer> s, char[] w) {
        int j = 0; // Текущая позиция в тексте
        int k = 0; // Текущая позиция в слове
        int[] t = getTable(w); // "Partial match" table. таблица для искомого слова, для оптимизации поиска

        while (j < s.size()) {
            if (w[k] == s.get(j)) {
                j++;
                k++;
                if (k == w.length) {
                    return j - k;
                }
            } else {
                k = t[k];
                if (k < 0) {
                    j++;
                    k++;
                }
            }
        }

        return -1;
    }

    private static int[] getTable(char[] w) {
        int pos = 1; // текущая позиция в таблице
        int cnd = 0; // the zero-based index in W of the next character of the current candidate substring

        int[] t = new int[w.length];
        t[0] = -1;

        while (pos < w.length) {
            if (w[pos] == w[cnd]) {
                t[pos] = t[cnd];
            } else {
                t[pos] = cnd;
                cnd = t[cnd];
                while (cnd >= 0 && w[pos] != w[cnd]) {
                    cnd = t[cnd];
                }
            }
            pos++;
            cnd++;
        }

        return t;
    }

    /*
     * Ищем строку в файлах .txt в указанной директории
     */
    public static boolean contentsInDirectory(String path, String stringToFind) throws IOException {
        ArrayList<Path> txtFiles;
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            // фильтруем только .txt файлы и собираем их в ArrayList txtFiles
            txtFiles = paths.filter(n -> isTxt(n.toString())).collect(Collectors.toCollection(ArrayList::new));
        }

        for (Path file : txtFiles) {
            if (contentsInTxtFile(file.toString(), stringToFind))
                return true;
        }

        return false;
    }
}
