import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class SendTelegramMessage {
    public static void sendMessageTelegram(Vector<String> select) {
        HttpURLConnection con = null;
        String tgToken = "5934805019:AAGzIHq9WhHbfaVXtTghwUd0MHi4Y4b5VAA";
        // В качестве chatID может выступать и уникальное имя пользователя, например @sukhodunova
        int chatId = 1022433076;
        //Сам запрос. Он в интересном виде, потому что все запросы к Telegram Bot API должны обслуживаться через HTTPS
        // и должны быть представлены в следующем виде: https://api.telegram.org/bot<token>/METHOD_NAME
        //Для отправки сообщений используется метод sendMessage
        String urlToken = "https://api.telegram.org/bot" + tgToken + "/sendMessage";



        //текст сообщения
        System.out.println("\nВведите сообщение: ");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();

       // for(int i = 0; i < select.size(); i++)
        {
            //String[] data = new String[select.size()];
            //data[i] = String.valueOf(select.get(i).split(";"));
           // chatId = data[5];
            String urlParameters = "chat_id=" + chatId + "&text=" + text;
            //Все запросы должны выполняться с использованием UTF-8.
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

            try {
                //Реализация подключения Post к телеграму
                URL url = new URL(urlToken);
                con = (HttpURLConnection) url.openConnection();

                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Java upread.ru client");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                    wr.write(postData);
                }

                StringBuilder content;

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    String line;
                    content = new StringBuilder();

                    while ((line = br.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }
                System.out.println(content);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                Objects.requireNonNull(con).disconnect();
            }
        }


    }
}
