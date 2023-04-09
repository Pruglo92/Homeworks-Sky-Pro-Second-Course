import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> nums = new ArrayList<>(List.of(8, 1, 1, 2, 3, 4, 4, 5, 5, 6, 7));
    public static List<String> words = new ArrayList<>(
            List.of("красный", "синий", "зелёный", "фиолетовый", "зелёный", "синий", "красный")
    );

    public static void main(String[] args) {
        task1(nums);
        task2(nums);
        task3(words);
        task4(words);
    }

    public static void task1(List<Integer> list) {  //вывод только нечётных чисел
        System.out.println("Задание №1");
        list.stream()
                .filter(x -> x % 2 != 0)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    public static void task2(List<Integer> list) {  //вывод только четных чисел без повторений в порядке возрастания
        System.out.println("Задание №2");
        list.stream()
                .filter(x -> x % 2 == 0)
                .distinct()
                .sorted()
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    public static void task3(List<String> list) {   //не совсем понял какую уникальность тут нужно
        System.out.println("Задание №3");

        list.stream()
                .distinct()
                .forEach(x -> System.out.print(x + " "));   //такую

        System.out.println();

        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);  //или вот такую
    }

    public static void task4(List<String> list) {   //вывод колличества дублей из списка слов
        System.out.println("Задание №4");
        var entryStream = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))  //тут будут элементы листа и кол-во их повторений
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));  //тут только повторяющиеся и кол-во их повторений
//        Map<String, Long> result = new HashMap<>();
//        for (Map.Entry<String, Long> entry : entryStream.entrySet()) {
//            if (entry.getValue() > 1L) {
//                result.put(entry.getKey(), entry.getValue());
//            }
//        }  //можно было и так, начиная с 50 строки

        System.out.println("result: " + entryStream);
//        System.out.println("result: " + result);

    }
}
