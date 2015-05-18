package ru.levelp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by vladimir on 14.04.15.
 */
public class Main {
    //ArrayList<Contact> book;
    private static final int PORT = 7001;
    private static final String IP = "localhost";
    private PrintWriter writer;
    private Socket socket;
    private Scanner scanner;
    private BufferedReader reader;
    private String message;
    /*
    1 - добавить контакт
    2 - удалить контакт
    3 - вывести все контакты
    4 - выход
     */
    public static void main(String[] args) {
        Main program = new Main();
        program.start();

//        //сравнение объектов
//        String str1 = "name";
//        String str2 = "name";
//        if (str1.equals(str2)) {
//
//        }
    }

    /*
    1) На клиенте меню: добавить контакт, вывести все контакты
    2) При добавлении считываем все данные с клавиатуры и отправляем на
        сервер в виде строки (например "Vova:12345:blabla@gmail.com")
    3) Сервер получает строку и создает новый контакт,
        который добавит в коллекцию
    4) При выборе на клиенте меню "Вывести все контакты" серверу отправляется
        запрос, сервер отвечает нам строчкой, в которой все контакты

    *5) Выполнить сохранение контактов в файл на сервере
    *6) Реализовать удаление контакта по имени, которое введем на клиенте
    *
    * "add:Vova:12345:blabla@gmail.com"
    * str = "remove:Vova"  -  String[] arr = str.split(":")
    * "stop"
    * "getAllContacts"
     */

    public void start() {
        scanner = new Scanner(System.in);
        //book = new ArrayList<Contact>();
        try {
            socket = new Socket(IP, PORT);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("1 - добавить контакт");
            System.out.println("2 - удалить контакт");
            System.out.println("3 - вывести все контакты");
            System.out.println("4 - выход");

            int answer = scanner.nextInt();

            if (answer == 1) {
                //добавление контакта
                addContact();
            } else if (answer == 2) {
                //удаление
                removeContact();
            } else if (answer == 3) {

                showAll();


                //вывод
//                for (int i = 0; i < book.size(); i++) {
//                    System.out.println(book.get(i).getName());
//                }
            } else if (answer == 4) {
                writer.println("stop");
                writer.flush();
                break;
            } else {
                System.out.println("Повторите ввод");
            }
        }

        writer.close();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeContact() {

        scanner = new Scanner(System.in);

        System.out.println("Введите имя удаляемого контакта: ");
        String delName = scanner.nextLine();
        String query = "removecontact:" + delName;
        writer.println(query);
        writer.flush();

        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(message);




    }

    private void showAll() {

        String query = "showall";

        writer.println(query);
        writer.flush();

        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(message);


    }

    public void addContact() {

        System.out.println("Введите имя:");
        String name = scanner.next();

        System.out.println("Введите телефон:");
        String phone = scanner.next();

        System.out.println("Введите email:");
        String email = scanner.next();

        String query = "add:" + name + ":" + phone + ":" + email;

        writer.println(query);
        writer.flush();
        try {
            message = reader.readLine();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void removeContact() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Введите имя:");
//        String name = scanner.next();
//
//        for (int i = 0; i < book.size(); i++) {
//            if (name.equals(book.get(i).getName())) {
//                book.remove(i);
//                break;
//            }
//        }
//        scanner.close();
//    }
    /*
    1) поиск по имени
    2) редактирование контакта
        1 - изменить имя
        2 - изменить телефон
        3 - изменить email
        4 - сохранить
    3) Создать две коллекции и заполнить их именами
        a) В третью коллекцию записываются имена из первых двух без повторений
        Dima, Anna, Alex, Anna
        Alex, Vova, Dima, Sasha
        Dima, Anna, Alex, Vova, Sasha

        b) В третью записываются только те, которые есть в обеих коллекциях
        Dima, Alex
     */
}
