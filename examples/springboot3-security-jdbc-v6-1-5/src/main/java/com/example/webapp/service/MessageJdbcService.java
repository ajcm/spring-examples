package com.example.webapp.service;

import com.example.webapp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MessageJdbcService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Message> ROW_MAPPER = (rs, rowNum) -> {
        var message = new Message();

        var subject =  rs.getString("SUBJECT");
        var body = rs.getString("BODY");
        var sender =  rs.getString("SENDER");
        var id = rs.getLong("ID");

        var createdDate = rs.getDate("CREATED");

        LocalDate localDate = createdDate != null ? createdDate.toLocalDate() : null;

        message.setId(id);
        message.setBody(body);
        message.setSender(sender);
        message.setSubject(subject);
        message.setCreated(localDate);

        return message;
    };

    public List<Message> getMessages() {
        return jdbcTemplate.query("select * from message", (rs, rowNum) -> {
            var message = new Message();

            var subject =  rs.getString("SUBJECT");
            var body = rs.getString("BODY");
            var sender =  rs.getString("SENDER");
            var id = rs.getLong("ID");

            var createdDate = rs.getDate("CREATED");

            LocalDate localDate = createdDate != null ? createdDate.toLocalDate() : null;

            message.setId(id);
            message.setBody(body);
            message.setSender(sender);
            message.setSubject(subject);
            message.setCreated(localDate);

            return message;
        });
    }

    public int add(Message message) {
        var createdDate = LocalDate.now();

        return jdbcTemplate.update("INSERT INTO MESSAGE(CREATED,SENDER,SUBJECT,BODY) values(?,?,?,?)",
               createdDate,
                message.getSender(),
                message.getSubject(),
                message.getBody());

      //  return 1;
    }


    public Message get(String id) {
        return jdbcTemplate.queryForObject("select * from MESSAGE where ID = ?",ROW_MAPPER,id);
    }

    public int delete(String id) {
        return jdbcTemplate.update("delete from MESSAGE where ID = ?",id);
    }

}
