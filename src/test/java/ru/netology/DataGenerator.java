package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static Meeting generate() {
            Faker faker = new Faker(new Locale("ru"));
            String futuredateFirstMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String futuredateSecondMeeting = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            return new Meeting(
                    faker.address().city(),
                    futuredateFirstMeeting,
                    faker.name().name(),
                    faker.phoneNumber().cellPhone(),
                    futuredateSecondMeeting);
        }
    }
}