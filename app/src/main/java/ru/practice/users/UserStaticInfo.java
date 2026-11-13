package ru.practice.users;

import java.util.ArrayList;
import java.util.List;

/** Общий список и ключ позиции пользователя между Activity. */
public final class UserStaticInfo {
    public static final String POSITION_KEY = "user_position";
    public static final List<User> users = new ArrayList<>();

    public UserStaticInfo() {
        if (users.isEmpty()) {
            users.add(new User("Иван", "Я учусь в колледже", 19, 1));
            users.add(new User("Мария", "Готовлюсь к экзамену", 18, 2));
            users.add(new User("Алексей", "В пути", 20, 0));
            users.add(new User("Анна", "Читаю книгу", 19, 1));
            users.add(new User("Дмитрий", "На занятиях", 21, 0));
        }
    }
}
