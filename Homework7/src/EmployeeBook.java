import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class EmployeeBook {

    static final String EMPLOYEE_STRING = "Employee{fullName='%s', salary=%d, id=%d}";

    private static final Map<Integer, Employee> employees = new HashMap<>();

    static {
        Employee employee1 = new Employee("Рожков Гордей Станиславович", 4, 1500);
        Employee employee2 = new Employee("Павлов Фрол Лаврентьевич", 2, 1800);
        Employee employee3 = new Employee("Рогов Трофим Проклович", 5, 2000);
        Employee employee4 = new Employee("Фокин Юрий Степанович", 1, 2556);
        Employee employee5 = new Employee("Харитонов Игнатий Всеволодович", 3, 7456);
        Employee employee6 = new Employee("Носов Пантелеймон Адольфович", 4, 8372);
        Employee employee7 = new Employee("Логинов Вячеслав Геннадьевич", 1, 1267);
        Employee employee8 = new Employee("Пономарёв Клим Дмитриевич", 2, 9000);
        Employee employee9 = new Employee("Шаров Август Натанович", 5, 8296);
        Employee employee10 = new Employee("Рыбаков Влас Оскарович", 3, 4528);

        employees.put(employee1.getId(), employee1);
        employees.put(employee2.getId(), employee2);
        employees.put(employee3.getId(), employee3);
        employees.put(employee4.getId(), employee4);
        employees.put(employee5.getId(), employee5);
        employees.put(employee6.getId(), employee6);
        employees.put(employee7.getId(), employee7);
        employees.put(employee8.getId(), employee8);
        employees.put(employee9.getId(), employee9);
        employees.put(employee10.getId(), employee10);
        employees.put(null, null);
    }

    public void getAllEmployees() {      //список всех сотрудников со всеми данными
        employees.entrySet().forEach(System.out::println);
    }

    public void getAllSalary() {         //подсчет затрат на все зарплаты
        AtomicInteger allSalary = new AtomicInteger();
        employees.forEach((key, value) -> {
            if (key != null && value != null) {
                allSalary.addAndGet(value.getSalary());
            }
        });
        System.out.println("Зарплата всех сотрудников за месяц составляет: " + allSalary);
    }

    public void getMinSalary() {         //поиск сотрудника с минимальной зарплатой
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        employees.forEach((key, value) -> {
            if (key != null && value != null) {
                if (min.get() > value.getSalary()) {
                    min.set(value.getSalary());
                }
            }
        });
        System.out.println("Минимальная зарплата сотрудника за месяц: " + min);
    }

    public void getMaxSalary() {         //поиск сотрудника с максимальной зарплатой
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        employees.forEach((key, value) -> {
            if (key != null && value != null) {
                if (max.get() < value.getSalary()) {
                    max.set(value.getSalary());
                }
            }
        });
        System.out.println("Максимальная зарплата сотрудника за месяц: " + max);
    }

    public void getAverageSalary() {     //подсчет среднего значения зарплат
        AtomicInteger allSalary = new AtomicInteger();
        AtomicInteger count = new AtomicInteger();
        employees.forEach((key, value) -> {
            if (key != null && value != null) {
                allSalary.addAndGet(value.getSalary());
                count.getAndIncrement();
            }
        });
        System.out.printf("Средняя зарплата всех сотрудников за месяц составляет: %.2f",
                allSalary.doubleValue() / count.doubleValue());
        System.out.println();
    }

    public void getAllFullNames() {      //список Ф. И. О. всех сотрудников
        employees.forEach((key, value) -> {
            if (key != null && value != null) {
                System.out.println(value.getFullName());
            }
        });
    }

    public void indexedSalary(int percent) {     //индексация всех зарплат
        employees.forEach((key, value) -> {
            if (key != null && value != null) {
                value.setSalary(value.getSalary() + value.getSalary() / 100 * percent);
            }
        });
        System.out.println("Зарплата проиндексирована на " + percent + "%");
    }

    public void getEmployeeWithMinSalary(int department) {       //сотрудник с минимальной зарплатой в отделе
        System.out.println("Сотрудник с минимальной зарплатой в отделе № " + department);
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .min(Comparator.comparing(entry -> entry.getValue().getSalary()))
                .ifPresent(System.out::println);
    }

    public void getEmployeeWithMaxSalary(int department) {       //сотрудник с максимальной зарплатой в отделе
        System.out.println("Сотрудник с максимальной зарплатой в отделе № " + department);
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .max(Comparator.comparing(entry -> entry.getValue().getSalary()))
                .ifPresent(System.out::println);
    }

    public void getDepartmentSalary(int department) {        //сумма затрат на зарплату по отделу
        var sum = employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .map(Map.Entry::getValue)
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
        System.out.println("Сумма затрат на зарплату по отделу: " + sum);
    }

    public void getDepartmentAverageSalary(int department) {     //средняя зарплата по отделу
        var sum = employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .map(Map.Entry::getValue)
                .map(Employee::getSalary)
                .mapToDouble(Integer::doubleValue)
                .average()
                .stream()
                .sum();
        System.out.println("Средняя зарплата по отделу: " + sum);
    }

    public void indexTheSalariesOfDepartmentEmployees(int percent, int department) { //Проиндексировать зарплату всех сотрудников отдела на процент
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .forEach(entry -> entry.getValue().setSalary(entry.getValue().getSalary() + entry.getValue().getSalary() / 100 * percent));
        System.out.println("Зарплата сотрудников отдела №" + department + " проиндексирована на " + percent + "%");
    }

    public void getAllEmployeesOfTheDepartment(int department) {     //Напечатать всех сотрудников отдела (все данные, кроме отдела)
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .forEach(entry -> System.out.printf((EMPLOYEE_STRING) + "\n",
                        entry.getValue().getFullName(),
                        entry.getValue().getSalary(),
                        entry.getValue().getId()));
    }

    public void getAllEmployeesWithSalaryLessThanNumber(int number) {    //напечатать всех сотрудников с зарплатой меньше числа
        System.out.println("Сотрудники с зарплатой меньше чем " + number);
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getSalary() < number)
                .forEach(System.out::println);
    }

    public void getAllEmployeesWithSalaryGreaterThanTheNumber(int number) {  //напечатать всех сотрудников с зарплатой больше числа
        System.out.println("Сотрудники с зарплатой больше или равной чем " + number);
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getSalary() >= number)
                .forEach(System.out::println);
    }

    public void addNewEmployee(String fullName, int department, int salary) {   //добавить нового сотрудника
        Employee employee = new Employee(fullName, department, salary);
        employees.put(employee.getId(), employee);
        System.out.println("Новый сотрудник удачно трудоустроен.");
    }

    public void deleteEmployeeById(int id) {        //удалить сотрудника по ID
        if (employees.remove(id) == null) {
            throw new RuntimeException("Сотрудник с данным ID " + id + " отсутствует.");
        }
        System.out.println("Сотрудник с ID " + id + " успешно удалён.");
    }

    public void deleteEmployeeByName(String name) {     //удалить сотрудника по имени
        var employee = employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .map(Map.Entry::getValue)
                .filter(value -> Objects.equals(value.getFullName(), name))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Сотрудник " + name + " отсутствует."));

        employees.remove(employee.getId());
        System.out.println("Сотрудник " + name + " успешно удалён.");
    }

    public void changeEmployeeSalary(String name, int salary) {     //изменить зарплату сотрудника по имени
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .map(Map.Entry::getValue)
                .filter(value -> Objects.equals(value.getFullName(), name))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Сотрудник " + name + " отсутствует."))
                .setSalary(salary);
    }

    public void changeEmployeeDepartment(String name, int department) {     //изменить отдел сотрудника по имени
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .map(Map.Entry::getValue)
                .filter(value -> Objects.equals(value.getFullName(), name))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Сотрудник " + name + " отсутствует."))
                .setDepartment(department);
    }

    /**
     * Данный метод выведет на экран всех сотрудников для каждого отдела в порядке возрастания отдела.
     * За зависимость взяты уникальные номера отделов, имеющиеся у всех сотрудников, так как отдел сотруднику
     * присваивается после прохождения валидации в конструкторе.
     */
    public void getAllEmployeesByAllDepartment() {
        employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .map(Map.Entry::getValue)
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .forEach((key, value) -> System.out.println("В отделе №" + key + " работают следующие сотрудники:" + value));
    }
}
