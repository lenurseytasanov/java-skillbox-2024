package com.app.skillbox_laba3;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactDao {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Contact> contactRowMapper = new RowMapper<>() {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contact contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setFirstName(rs.getString("first_name"));
            contact.setLastName(rs.getString("last_name"));
            contact.setEmail(rs.getString("email"));
            contact.setPhone(rs.getString("phone"));
            return contact;
        }
    };

    public List<Contact> findAll() {
        String sql = "SELECT * FROM contact";
        return jdbcTemplate.query(sql, contactRowMapper);
    }

    public Contact findById(Long id) {
        String sql = "SELECT * FROM contact WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, contactRowMapper, id);
    }

    public int save(Contact contact) {
        String sql = "INSERT INTO contact (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
    }

    public int update(Contact contact) {
        String sql = "UPDATE contact SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
        return jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
