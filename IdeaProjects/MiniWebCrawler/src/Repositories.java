import com.google.gson.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Repositories {

    /*
    * Класс описывающий сумарный репозиторий пользователя,
    * то есть все репозитории пользователя.
    * Это позволяет собрать в этот класс суммарную информацию,
    * общую для сумммарного репозитория одного пользователя такую, как
    * максимальное количество звёзд, имя и язык самого популярного репозитория,
    * а также произвести расчёт основного языка программирования пользователя.
    */

    private ArrayList<Repository> repositories = new ArrayList<>();

    private int maxStarsCount = 0;
    private String mostPopularRepositoryName = "";
    private String languageThatRepository = "";

    private Map<String, Integer> languageMap = new HashMap<>(); //с помощью Мар будем считать частоту используемых языков
    private Gson GSON = new GsonBuilder().create();
    private String mainLanguage;

    public Repositories(JsonArray jsonElements) {

        /*
        * Конструкор класса, преобразовывает каждый JSON елемент полученный
        * из массива в объект одиночного элемента - репозитория и обновляет общие для
        * суммарного репозитория пользователя значения
        */

        for (JsonElement jsonElement : jsonElements) {
            Repository repository = GSON.fromJson(jsonElement, Repository.class);
            repositories.add(repository);

            if (repository.getStarsCount() > maxStarsCount) {
                maxStarsCount = repository.getStarsCount();
                mostPopularRepositoryName = repository.getName();
                languageThatRepository = repository.getLanguage();
            }

            if (languageMap.keySet().contains(repository.getLanguage())) {
                languageMap.put(repository.getLanguage(), languageMap.get(repository.getLanguage()) + 1);
            } else {
                languageMap.put(repository.getLanguage(), 1);
            }

        }
        mainLanguage = getMainLanguage();
    }

    public ArrayList<Repository> getRepositories() {
        return repositories;
    }

    public int getMaxStarsCount() {
        return maxStarsCount;
    }

    public String getMainLanguage() {

        /*
        * Метод производит расчёт основного языка программирования пользователя
        * путём простого сравнения элементов "карты частот языков" с максимальным значением частоты.
        * Также, в случае если будет встречена одинаковая частота у двух и
        * более языков, метод выведет их всех.
        * */

        String out = "";
        languageMap.remove("");
        int maxFrequencyLanguage = Collections.max(languageMap.values());
        for (Map.Entry<String, Integer> pair : languageMap.entrySet()) {
            if (pair.getValue() == maxFrequencyLanguage) {
                if (!out.isEmpty()) {
                    out += ", ";
                }
                out += pair.getKey();
            }
        }
        return out;
    }

    @Override
    public String toString() {
        String s =
                "Основной язык программирования (чаще встречается) - " +  mainLanguage + "\n"
                        + "Название самого популярного репозитория - " + mostPopularRepositoryName + "\n"
                        + "Количество звёзд в этом репозитории - " +  maxStarsCount + "\n"
                        + "Основной язык этого репозитория - " +  languageThatRepository + "\n";
        return s;
    }

}
