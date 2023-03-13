import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException {

        //Главное меню
        Vector<String> select = new Vector<String>();
        System.out.println("Приветствуем Вас в TUDA!");
        int choice;
        do {
            System.out.println("1 - Записать сообщение");
            System.out.println("2 - Просмотреть сообщение");
            System.out.println("3 - Отправить сообщение через email");
            System.out.println("4 - Отправить сообщение через telegram");
            System.out.println("5 - Выбрать получателей");
            System.out.println("6 - Ввести контактные данные");
            System.out.println("7 - Завершить приложение");
            Scanner in = new Scanner(System.in);
            System.out.println("Выберите действие: ");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    writeMessage();
                    break;
                case 2:
                    readMessage("resource/message.txt");
                    break;
                case 3:
                    gMailSender.Send_Gmail(select);
                    break;
                case 4:

                    SendTelegramMessage.sendMessageTelegram(select);
                    break;
                case 5:
                    select = Select.selectingAddress();
                    break;
                case 6:
                    enterContacts();
                    break;

                default:
                    System.out.println("Выбрано несуществующее действие");
                    break;
            }
        } while (choice != 7);
    }
//Функция для записи текста в файл
    public static void writeMessage()
    {
        try(FileWriter writer = new FileWriter("resource/message.txt", false))
        {
            System.out.println("\nВведите сообщение: ");
            Scanner in = new Scanner(System.in);
            String text = in.nextLine();
            writer.write(text);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
//Функция для чтения файлов
    public static void readMessage(String ref)
    {
        try(FileReader reader = new FileReader(ref))
        {
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
            System.out.println("\n");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }



    //Функция для ввода контактных данных в ручную
    public static void enterContacts()
    {
        System.out.println("""
                Введите контактные данные по шаблону: Ф; И; О; email; номер телефона; ID chat Telegram; должность; отдел;
                После каждого контакта нажимайте Enter
                Чтобы завершить заполнение списка контактов, введите 'exit'""");

        boolean enter = true;

            try(FileWriter writer = new FileWriter("resource/contacts.txt", true))
            {
                do {
                    System.out.println("\nВведите данные: ");
                    Scanner in = new Scanner(System.in);
                    String text = in.nextLine();

                    if (Objects.equals(text, "exit"))
                    {
                        enter = false;
                    }
                    if (!Objects.equals(text, "exit"))
                    {
                        writer.write("\n");
                        writer.write(text);
                    }
                } while(enter);
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }

        readMessage("resource/contacts.txt");

    }



}

