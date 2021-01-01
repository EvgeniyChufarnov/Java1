package homework6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[] filesToMerge = {"aboutGIT1.txt", "aboutGIT2.txt"};

        //пишем в разных блоках try, чтобы ошибка в 1 методе не прекращала выполнение других

        try {
            FilesManipulator.MergeTxtFiles(filesToMerge, "aboutGIT3.txt", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(FilesManipulator.contentsInTxtFile("aboutGIT1.txt", "по-прежнему работают"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(FilesManipulator.contentsInTxtFile2("aboutGIT1.txt", "по-прежнему работают"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try { ;
            System.out.println(FilesManipulator.contentsInDirectory("aboutGIT2.txt", "хостинга."));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
