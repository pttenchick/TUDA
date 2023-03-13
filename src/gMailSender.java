import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

public class gMailSender {

    public static void Send_Gmail(Vector<String> select) throws IOException, MessagingException {
//        Путь к файлу с настройками
        FileInputStream is = new FileInputStream("C:\\Users\\tsuho\\IdeaProjects\\ConsoleTUDA\\resource\\Config.properties");
//        создаем объект с настройками и читаем в него настройки из файла
        Properties properties = new Properties();
        properties.load(is);


//  Создаем подключение/сессию
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.user"),
                        properties.getProperty("mail.password"));
            }
        });

        System.out.println("\nВведите сообщение: ");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();


      //  for (int i = 0; i < select.size(); i++) {
//  Создаем сообщение
            Message msg = new MimeMessage(session);
//  Указываем от кого сообщение (адрес)
            msg.setFrom(new InternetAddress(properties.getProperty("mail.user")));
            //  Указываем кому сообщение (массив адресов)
          //  String[] data = new String[select.size()];
        String[] data;
       // data = select.get(i).split(";");
            InternetAddress[] addresses = {new InternetAddress("tsuho03@gmail.com")};
            msg.setRecipients(Message.RecipientType.TO, addresses);
//        Тема письма
            msg.setSubject("Внимание!");
//        Дата и время отправки
            msg.setSentDate(new Date());
//        Текст/содержимое письма
            msg.setText(text);
//  Отправляем настроенное письмо
            Transport.send(msg);
        }
    }



