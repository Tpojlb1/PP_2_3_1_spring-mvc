package web.sevice;

import web.models.User;

import java.util.List;

public interface UserService {

List<User> findAll();

User findById(long id);

void saveUser(User user);

void update(long id, User updatedUser);

void deleteUser(long id);
}

