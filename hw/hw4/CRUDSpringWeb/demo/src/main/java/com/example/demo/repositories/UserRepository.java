package com.example.demo.repositories;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbc;
    private final DatabaseConfig databaseConfig;

    // Получение всех пользователей из базы данных
    public List<User> findAll() {
        return jdbc.query(databaseConfig.getFindAllUsers(), new UserRowMapper());
    }

    // Сохранение пользователя в базе данных
    public User save(User user) {
        jdbc.update(databaseConfig.getSaveUser(), user.getFirstName(), user.getLastName());
        return user;
    }

    // Удаление пользователя по идентификатору
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id = ?";
        jdbc.update(sql, id);
    }

    // Обновление данных пользователя
    public User update(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

    // Получение пользователя по идентификатору
    public User getOne(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        return jdbc.queryForObject(sql, new Object[]{id}, new UserRowMapper());
    }

    // RowMapper для маппинга результата запроса к объекту User
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            return user;
        }
    }
}
