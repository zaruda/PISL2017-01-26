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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

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

public class B_EditDist {

    int getDistanceEdinting(String firstString, String secondString) {
	int m = firstString.length(), n = secondString.length();
	int[] firstDifference;
	int[] secondDifference = new int[n + 1];

	for (int i = 0; i <= n; i++)
	    secondDifference[i] = i;

	for (int i = 1; i <= m; i++) {
	    firstDifference = secondDifference;
	    secondDifference = new int[n + 1];
	    for (int j = 0; j <= n; j++) {
		if (j == 0) {
		    secondDifference[j] = i;
		} else {
		    int cost = (firstString.charAt(i - 1) != secondString.charAt(j - 1)) ? 1 : 0;
		    if (secondDifference[j - 1] < firstDifference[j]
			    && secondDifference[j - 1] < firstDifference[j - 1] + cost) {
			secondDifference[j] = secondDifference[j - 1] + 1;
		    } else if (firstDifference[j] < firstDifference[j - 1] + cost) {
			secondDifference[j] = firstDifference[j] + 1;
		    } else {
			secondDifference[j] = firstDifference[j - 1] + cost;
		    }
		}
	    }
	}
	return secondDifference[n];
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson7/dataABC.txt");
	B_EditDist instance = new B_EditDist();
	Scanner scanner = new Scanner(stream);
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
