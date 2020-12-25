package homework4;

//1 Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
//2 Конструктор класса должен заполнять эти поля при создании объекта;
//3 Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;

public class Employee {
    private static int nextID = 1;
    private String name;
    private String position;
    private String phone;
    private int salary;
    private int age;
    private int employeeID;

    public Employee(String name, String position, String phone, int salary, int age) {
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
        //7** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер
        employeeID = nextID++;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public int getID() {
        return employeeID;
    }

    //Можно было и в Main всё расписать, просто хотел попробовать, как работает переписывание toString класса Object
    public String toString() {
        return "ФИО: " + name + " Должность: " + position + " Телефон: " + phone + " Заработная плата: " + salary +
                " Возраст: " + age + " ID: " + employeeID;
    }

    public void raiseSalary(int value) {
        salary += value;
    }
}
