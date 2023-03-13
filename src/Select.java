import javax.mail.internet.InternetAddress;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class Select {

    //Функция для выбора получателей
    public static Vector<String> selectingAddress() throws IOException {
        int choice;
        Vector<String> select = null;
        do {
            System.out.println("1 - Выбрать по фамилии");
            System.out.println("2 - Выбрать по должности");
            System.out.println("3 - Выбрать по отделу работы");
            System.out.println("4 - Завершить действие");
            Scanner in = new Scanner(System.in);
            System.out.println("Выберите действие: ");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    select = Select.selectSurname("resource/contacts.txt");
                    return select;
                case 2:
                    select = Select.selectPost("resource/contacts.txt");
                    return select;

                case 3:
                    select = Select.selectDepatment("resource/contacts.txt");
                    return select;

                case 4:
                    break;
                default:
                    System.out.println("Выбрано несуществующее действие");
                    break;
            }
        } while (choice != 4);


        Vector<String> offline = null;
        return offline;
    }


    public static Vector<String> selectSurname(String ref) throws IOException {

        BufferedReader txtReader = new BufferedReader(new FileReader(ref));
        //Вектор для выделения получателей
        Vector<String> select = new Vector<>();

        boolean enter = true;
        do {

            //Ввод искомой фамилии
            System.out.println("\nЕсли вы хотите завершить добавление по фамилии, напишите exit");
            System.out.println("\nВведите фамилию: ");
            Scanner in = new Scanner(System.in);
            String search = in.nextLine();

            if (Objects.equals(search, "exit"))
            {
                enter = false;
                break;
            }
            else {
                InternetAddress address;
                String line;
                while ((line = txtReader.readLine()) != null) {

                    //Массив для разбития
                    String[] data;
                    data = line.split(";");

                    if (Objects.equals(data[0], search)) {
                        select.add(line);
                    }
                }
            }
        }while(enter);

        System.out.println(select);

        txtReader.close();
        return select;
    }
    public static Vector<String> selectPost(String ref) throws IOException {

        BufferedReader txtReader = new BufferedReader(new FileReader(ref));
        //Вектор для выделения получателей
        Vector<String> select = new Vector<>();

        boolean enter = true;
        do {

            //Ввод искомой фамилии
            System.out.println("\nЕсли вы хотите завершить добавление по фамилии, напишите exit");
            System.out.println("\nВведите должность: ");
            Scanner in = new Scanner(System.in);
            String search = in.nextLine();

            if (Objects.equals(search, "exit"))
            {
                enter = false;
                break;
            }
            else {
                InternetAddress address;
                String line;
                while ((line = txtReader.readLine()) != null) {

                    //Массив для разбития
                    String[] data;
                    data = line.split(";");

                    if (Objects.equals(data[6], search)) {
                        select.add(line);
                    }
                }
            }
        }while(enter);

        System.out.println(select);

        txtReader.close();
        return select;
    }

    public static Vector<String> selectDepatment(String ref) throws IOException {

        BufferedReader txtReader = new BufferedReader(new FileReader(ref));
        //Вектор для выделения получателей
        Vector<String> select = new Vector<>();

        boolean enter = true;
        do {

            //Ввод искомой фамилии
            System.out.println("\nЕсли вы хотите завершить добавление по фамилии, напишите exit");
            System.out.println("\nВведите отдел: ");
            Scanner in = new Scanner(System.in);
            String search = in.nextLine();

            if (Objects.equals(search, "exit"))
            {
                enter = false;
                break;
            }
            else {
                InternetAddress address;
                String line;
                while ((line = txtReader.readLine()) != null) {

                    //Массив для разбития
                    String[] data;
                    data = line.split(";");

                    if (Objects.equals(data[7], search)) {
                        select.add(line);
                    }
                }
            }
        }while(enter);

        System.out.println(select);

        txtReader.close();
        return select;
    }

}
