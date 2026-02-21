package com.auth.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.auth.entity.User;

public class UserRepo {
    private final Connection conn;

    public UserRepo (Connection conn) {
        this.conn = conn;
    }

    public User save(User user) throws SQLException {
        String qStr = """
        INSERT INTO users (name, userName, password)
        VALUES (?, ?, ?)
        """;

        PreparedStatement st = conn.prepareStatement (qStr);
        st.setString(1, user.getName());
        st.setString(2, user.getUserName());
        st.setString(3, user.getPassword());

        st.executeUpdate();

        ResultSet keys = st.getGeneratedKeys();
        if (keys.next()) {
            user.setId(keys.getInt(1));
        }

        return user;
    }


};
