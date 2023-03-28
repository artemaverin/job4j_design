package ru.job4j.ood.srp;

public class UserSettingService {
    public void updateEmail(User user) {
        if (checkAccess(user)) {
            System.out.println("option to change email for user: " + user);
        }
    }

    public boolean checkAccess(User user) {
        System.out.println("verify if the user is valid or not.");
        return false;
    }

    class User {

    }
}