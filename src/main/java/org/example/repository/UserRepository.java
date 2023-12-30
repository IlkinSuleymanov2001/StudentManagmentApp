package org.example.repository;
import org.example.entity.User;
import java.util.Arrays;
import java.util.Objects;

public class UserRepository {

    User[] users = new User[10];
    public int userCount = 0;


    public User[] getUsers() {
        return users;
    }

    public User addUser(User u) {

        if (users.length == userCount)
        {
         getResizeableArr();
         System.out.println(users.length);
        }
        users[userCount++] = u;
        return u;
    }

    private void getResizeableArr() {
        User[] data = new User[users.length * 2];
        System.arraycopy(users, 0, data, 0, userCount);
        users = data ;
    }

    public User updateUser(Long id, User u) {
        User user = findUsersById(id);
        if (user == null) {
            System.out.println("User not found by id " + id);
            return null;
        }
        user.setName(u.getName());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        return user;
    }

    public User removeUser(Long id) {
        User u = findUsersById(id);
        if (u == null) {
            System.out.println("no user this id" + id);
            return null;
        }

        for (int i = 0; i < userCount; i++) {
            if (u.getId().equals(users[i].getId())) {

                //remove operation for last user
                users[i] = null;
                // user remove operation any index
                if (i != userCount - 1) {
                    users[i] = users[userCount - 1];
                    users[userCount-1] = null;
                }
                userCount--;
                break;

            }
        }
        return u;

    }

    public User findUsersById(Long id) {
        if (userCount==0){
            return  null;
        }
        for (int i = 0; i < userCount; i++) {
                if (id.equals(users[i].getId())) {
                    return users[i];
            }
        }
        return null;
    }

    public User findByEmail(String email) {
        return Arrays.stream(users).
                filter(Objects::nonNull)
                        .
                filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
