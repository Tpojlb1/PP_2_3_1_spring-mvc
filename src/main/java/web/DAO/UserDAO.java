package web.DAO;

import web.models.User;

import java.util.List;

public interface UserDAO {

List<User> findAll();

User findById(long id);

void saveUser(User user);

void update(long id, User updatedUser);

void deleteUser(long id);
}