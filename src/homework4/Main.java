package homework4;

public class Main {
    private static Employee[] employees = new Employee[5];

    public static void main(String[] args) {
        employees[0] = new Employee("Иванов Иван Иванович", "Директор", "8-999-555-66-77", 100000, 35);
        employees[1] = new Employee("Петров Петр Петрович", "Слесарь 4 разряда", "8-999-555-66-88", 15000, 60);
        employees[2] = new Employee("Олегов Олег Олегович", "Водитель", "8-999-555-66-99", 20000, 38);
        employees[3] = new Employee("Кириллов Кирилл Кириллович", "Слесарь 3 разряда", "8-999-555-66-11", 15500, 54);
        employees[4] = new Employee("Евпатьев Евпатий Евпатьевич", "Менеджер", "8-999-555-66-22", 60000, 31);

        //4 Вывести при помощи методов из пункта 3 ФИО и должность.
        for(Employee employee: employees) {
            System.out.println(employee.getName());
            System.out.println(employee.getPosition());
            System.out.println();
        }

        //5 Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
        for(Employee employee: employees) {
            if (employee.getAge() >= 40)
                System.out.println(employee);
        }

        raiseSalary(35, 10000);

        // Проверяем изменения
        System.out.println();
        for(Employee employee: employees) {
            System.out.println(employee);
        }
    }

    //6* Создать метод, повышающий зарплату всем сотрудникам старше 35 лет на 10000;
    private static void raiseSalary(int age, int value) {
        for(Employee employee: employees) {
            if (employee.getAge() >= age)
                employee.raiseSalary(value);
        }
    }
}
