import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Crawler {

    private String userNickName = "";

    private static int requestDelayMs = 0;
    private static int maxParallelRequests = 0;

    private Gson GSON = new GsonBuilder().create();

    public Crawler(String userUrlString) {
        String nickName = userUrlString.replace("https://github.com/", "");
        nickName = nickName.replaceAll("/", "").replaceAll("\\s", "")
                           .replace(Character.toString('\uFEFF'), "");
        this.userNickName = nickName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public static void main(String[] args) {

       /*
       * Читаем файл с url-ами и на основании каждого url-а создаём объект
       * класса Crawler и вызываем методы сбора инфы.
       */

        ArrayList<String> filesURLList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                                        new FileInputStream("C:\\urls.txt"), "UTF-8"));
            while (reader.ready()) {
                filesURLList.add(reader.readLine());
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (String filesURL : filesURLList) {

            Crawler crawler = new Crawler(filesURL);
            try {
                crawler.searchUserInfo();
                crawler.searchRepositoriesInfo();
                Thread.currentThread().sleep(requestDelayMs);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


    public void searchRepositoriesInfo() throws Exception {

        /*
        * Собирам инфу по репозиториям пользователя.
        * Запрос к api возвращает данные ввиде файла JSON типа. Используя библиотеку
        * преобразовываем этот файл к массиву елементов JSON и вызываем конструктор специально
        * для этого созданного класса в котором происходят расчёты.
        */

        JsonParser parser = new com.google.gson.JsonParser();
        JsonArray jsonElements = null;

        URLConnection connection = new URL("https://api.github.com/users/" + getUserNickName() + "/repos").openConnection();
        String textFromConection = readingConecntion(connection);

        try {
            jsonElements = parser.parse(textFromConection).getAsJsonArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (jsonElements != null) {
            Repositories repositories = new Repositories(jsonElements);
            System.out.println(repositories);
        } else {
            System.out.println("Репозитории отсутствуют." + "\n");
        }
    }


    public void searchUserInfo() throws Exception {

        /*
        * Собирам инфу по пользователю.
        * Запрос к api возвращает данные ввиде файла JSON типа.
        * Используя библиотеку преобразовываем этот файл к объекту
        * специально для этого созданного класса Person и выводим его данные на экран.
        */

        URLConnection connection = new URL("https://api.github.com/users/" + getUserNickName()).openConnection();
        String textFromConection = readingConecntion(connection);

        Person person = GSON.fromJson(textFromConection, Person.class);
        System.out.println(person.toString());
    }


    public String readingConecntion(URLConnection connection) throws Exception {

        //Вспомогательная функция, которая производит чтение информации через соединение.

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
            }
        }
        return stringBuilder.toString();
    }
}
