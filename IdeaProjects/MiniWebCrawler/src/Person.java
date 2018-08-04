
public class Person {

    //Класс описывающий пользователя

    private String login;
    private String url;
    private String company;
    private String location;
    private String name;

    public Person(String login, String url, String company,
                  String location, String name) {
        this.login = login;
        this.url = url;
        this.company = company;
        this.location = location;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getUrl() {
        return url;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String s = "Ник - " + getLogin() + "\n"
                + "Полное имя - " +  getName() + "\n"
                + "Компания - " +  getCompany() + "\n"
                + "Локация - " + getLocation();
        return s;
    }
}

