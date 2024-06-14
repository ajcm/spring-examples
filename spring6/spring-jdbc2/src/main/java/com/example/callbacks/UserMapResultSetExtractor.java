package com.example.callbacks;

import com.example.model.User;
import com.example.model.UserMap;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapResultSetExtractor implements ResultSetExtractor<UserMap> {
    @Override
    public UserMap extractData(ResultSet rs) throws SQLException, DataAccessException {

        UserMap userMap = new UserMap();
        while (rs.next()) {
            var id = rs.getString("ID");
            var name = rs.getString("NAME");
            var email = rs.getString("EMAIL");
            User user = new User(id, name, email);

            userMap.getUserMap().put(id, user);

        }
        return userMap;
    }
}
