package by.it.group473601.kalashynski.lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.

Алгоритм жадный. Для реализации обдумайте надежный шаг.

*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event implements Comparable{
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }

        @Override
        public int compareTo(Object o) {
            Event event = (Event) o;
            if (this.stop < event.stop) {
                return -1;
            } else if (this.stop > event.stop) {
                return 1;
            }
            return 0;
        }

    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                            new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                            new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                            new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                            new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                            new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
                          };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();
        Arrays.sort(events);
        int duration =from;
        for(int i=0;i<events.length;i++) {
            if(events[i].start>=duration&&events[i].stop<=to) {
                duration=events[i].stop;
                result.add(events[i]);
            }
        }
        return result;
    }
}
