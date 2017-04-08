package by.it.group473601.zinkevich.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        int[][] array = new int [one.length()][two.length()];
        int max = 101;
        for(int i = 0; i < one.length(); i++){
            for (int j = 0; j < two.length(); j++){
                array[i][j] = max;
            }
        }

        int result = -1;
        for(int i = 0; i < one.length(); i++){
            for (int j = 0; j < two.length(); j++){
                result = editDistance(i, j, array, max, one, two);
            }
        }





        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    int editDistance(int i, int j, int[][] array, int max, String one, String two) {

        if (array[i][j] == max){
            if (i == 0){
                array[i][j] = j;
            }
            else {
                if (j == 0) {
                    array[i][j] = i;
                }
                else {
                    int insert = editDistance(i, j - 1, array, max, one, two) + 1;
                    int delete = editDistance(i - 1, j, array, max, one, two) + 1;
                    int substitute = editDistance(i - 1, j - 1, array, max, one, two) + difference(one.charAt(i), two.charAt(j));

                    int min = -1;
                    if (insert < delete){
                        min = insert;
                    }
                    else {
                        min = delete;
                    }
                    if (min > substitute){
                        min = substitute;
                    }
                    array[i][j] = min;
                }
            }

        }

        return array[i][j];
    }
    int difference(char a, char b){
        if (a == b) {
            System.out.println("==");
            return 0;
        }
        else {
            System.out.println("!=");
            return 1;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/zinkevich/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

