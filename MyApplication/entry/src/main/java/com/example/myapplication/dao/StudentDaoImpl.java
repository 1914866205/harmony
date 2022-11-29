package com.example.myapplication.dao;

import com.example.myapplication.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl {
    public Connection conn = null;

    public StudentDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public Student login(String username, String password) {
        Student s = null;
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM student WHERE username=? AND password=?";
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setUsername(username);
                s.setPassword(password);
                s.setSex(rs.getString("sex"));
                s.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return s;
    }

    public boolean reg(Student student) throws SQLException {
        boolean flag = false;
        PreparedStatement pstmt = null;
        String sql = "insert into student(id,username,password,name,age,sex)"
                + " values(?,?,?,?,?,?)";
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getUsername());
            pstmt.setString(3, student.getPassword());
            pstmt.setString(4, student.getName());
            pstmt.setInt(5, student.getAge());
            pstmt.setString(6, student.getSex());
            int count = pstmt.executeUpdate(); //
            if (count > 0) {
                flag = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return flag;
    }
}
