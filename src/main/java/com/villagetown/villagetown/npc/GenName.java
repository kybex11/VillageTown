package com.villagetown.villagetown.npc;

import java.util.List;
import java.util.Random;

public class GenName {

    private static final Random RANDOM = new Random();

    private static final List<String> MALE_FIRSTNAMES = List.of(
            "Алексей", "Александр", "Артём", "Аркадий", "Андрей", "Антон", "Анатолий", "Арсений", "Адам", "Август",
            "Азарий", "Аким", "Альберт", "Арт", "Артур", "Арсен", "Артемий", "Аскольд", "Афанасий", "Борис",
            "Богдан", "Валентин", "Владислав", "Владимир", "Василий", "Вячеслав", "Виталий", "Виктор", "Гавриил", "Григорий",
            "Георгий", "Денис", "Даниил", "Дмитрий", "Евгений", "Егор", "Елисей", "Захар", "Иван", "Игнат",
            "Илья", "Кирилл", "Константин", "Лев", "Леонид", "Макар", "Михаил", "Николай", "Никита", "Олег",
            "Павел", "Пётр", "Роман", "Руслан", "Савелий", "Семён", "Сергей", "Станислав", "Степан", "Тимофей",
            "Тимур", "Фёдор", "Юрий", "Ярослав"
    );

    private static final List<String> FEMALE_FIRSTNAMES = List.of(
            "Александра", "Анастасия", "Анна", "Алиса", "Алёна", "Алина", "Арина", "Агата", "Агафья", "Аделина",
            "Аделия", "Алевтина", "Алла", "Амелия", "Амина", "Ангелина", "Анжела", "Ариадна", "Валерия", "Виктория",
            "Вера", "Вероника", "Виолетта", "Галина", "Дарья", "Диана", "Екатерина", "Ева", "Елизавета", "Жанна",
            "Злата", "Зоя", "Ирина", "Инна", "Инесса", "Карина", "Кира", "Ксения", "Лариса", "Лидия",
            "Любовь", "Людмила", "Маргарита", "Марина", "Мария", "Милана", "Надежда", "Наталья", "Нина", "Оксана",
            "Ольга", "Полина", "Раиса", "Регина", "Светлана", "София", "Татьяна", "Ульяна", "Юлия", "Яна", "Ярослава"
    );

    private static final List<String> SURNAMES = List.of(
            "Александров", "Андреев", "Анисимов", "Артамонов", "Афанасьев", "Богданов", "Борисов", "Власов", "Волков", "Васильев",
            "Воронцов", "Гаврилов", "Григорьев", "Демидов", "Дмитриев", "Евдокимов", "Ефимов", "Жуков", "Зайцев", "Иванов",
            "Игнатов", "Карпов", "Кузнецов", "Киселёв", "Ковалёв", "Кондратьев", "Королёв", "Крылов", "Лебедев", "Лазарев",
            "Максимов", "Марков", "Миронов", "Михайлов", "Назаров", "Николаев", "Орлов", "Павлов", "Петров", "Попов",
            "Романов", "Савельев", "Семёнов", "Сергеев", "Соколов", "Соловьёв", "Фёдоров", "Харитонов", "Чернов", "Шестаков",
            "Щербаков", "Юрьев", "Яковлев"
    );

    private final boolean isMale;
    private final String firstname;
    private final String surname;

    public GenName() {
        this.isMale = RANDOM.nextBoolean();
        this.firstname = generateFirstname(isMale);
        this.surname = generateSurname(isMale);
    }

    private String generateFirstname(boolean isMale) {
        List<String> names = isMale ? MALE_FIRSTNAMES : FEMALE_FIRSTNAMES;
        return names.get(RANDOM.nextInt(names.size()));
    }

    private String generateSurname(boolean isMale) {
        String surname = SURNAMES.get(RANDOM.nextInt(SURNAMES.size()));
        if (!isMale) {
            surname += "а";
        }
        return surname;
    }

    public int generateSkin() {
        return RANDOM.nextInt(9) + 1;
    }

    public String getFullName() {
        return firstname + " " + surname;
    }

    public boolean isMale() {
        return isMale;
    }
}
