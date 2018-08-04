
class Repository {

    //Класс описывающий отдельный репозиторий пользователя

    private String name;
    private String language;
    private int stargazers_count;

    public Repository(String name, String language, int stargazers_count) {
        this.name = name;
        this.language = language;
        this.stargazers_count = stargazers_count;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public int getStarsCount() {
        return stargazers_count;
    }


}
