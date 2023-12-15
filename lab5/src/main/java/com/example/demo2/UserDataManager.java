package com.example.demo2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    private static final String USER_DATA_FILE = "userdata.dat";

    public static void saveUser(User user) {
        List<User> userList = loadUsers();
        if (userList == null) {
            userList = new ArrayList<>();
        }
        // Поиск пользователя в списке по имени пользователя
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(user.getUsername())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            // Если пользователь найден, заменяем его в списке
            userList.set(index, user);
        } else {
            // Если пользователь не найден, добавляем его в список
            userList.add(user);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<User> loadUsers() {
        List<User> userList = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            userList = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static boolean isUserExists(String username) {
        List<User> userList = loadUsers();
        if (userList != null) {
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }
}
