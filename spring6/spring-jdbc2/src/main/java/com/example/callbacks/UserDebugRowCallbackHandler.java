package com.example.callbacks;

import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDebugRowCallbackHandler implements RowCallbackHandler {
    @Override
    public void processRow(ResultSet rs) throws SQLException {
        var id = rs.getString("ID");
        var name = rs.getString("NAME");

        System.out.println("get " + id + ", " + name);

    }
}
