package by.it.group473602.matys.lesson7;

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

    public static int naiveEditDistance(String sourceString, String destinationString) {
	int matchDist;
	int insertDist;
	int deleteDist;
	int swapDist;

	if (sourceString.length() == 0) {
	    return destinationString.length();
	} else if (destinationString.length() == 0) {
	    return sourceString.length();
	} else {
	    matchDist = naiveEditDistance(sourceString.substring(1), destinationString.substring(1));
	    if (sourceString.charAt(0) != destinationString.charAt(0))
		matchDist++;

	    insertDist = naiveEditDistance(sourceString.substring(1), destinationString) + 1;
	    deleteDist = naiveEditDistance(sourceString, destinationString.substring(1)) + 1;

	    if (sourceString.length() > 1 && destinationString.length() > 1
		    && sourceString.charAt(0) == destinationString.charAt(1)
		    && sourceString.charAt(1) == destinationString.charAt(0)) {
		swapDist = naiveEditDistance(sourceString.substring(2), destinationString.substring(2)) + 1;
	    } else {
		swapDist = Integer.MAX_VALUE;
	    }

	    return Math.min(matchDist, Math.min(insertDist, Math.min(deleteDist, swapDist)));
	}
    }

    int getDistanceEdinting(String firstString, String secondString) {
	return naiveEditDistance(firstString, secondString);
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson7/dataABC.txt");
	A_EditDist instance = new A_EditDist();
	Scanner scanner = new Scanner(stream);
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	scanner.close();
    }
}
