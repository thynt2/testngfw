package common;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);

    public static DataHelper getData() {
        return new DataHelper();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }
}
