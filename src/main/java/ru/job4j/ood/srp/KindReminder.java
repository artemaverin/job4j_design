package ru.job4j.ood.srp;

public class KindReminder {
    public boolean sendReminder(User user) {
        System.out.println("Дорогой " + user + " Вам направлено напоминание о необходимости погасить задолженность");
        reminderNotification();
        return true;
    }

    public void reminderNotification() {
        System.out.println("Вашему клиенту отправлено уведомление о платеже");
    }

    class User {

    }
}
