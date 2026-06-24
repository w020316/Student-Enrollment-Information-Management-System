package com.stu.dao;

import com.stu.entity.Score;
import com.stu.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoreDBDao implements ScoreDao {

    @Override
    public List<Score> findAll() {
        List<Score> list = new ArrayList<>();
        String sql = "SELECT sc.*, s.name AS student_name, c.name AS course_name " +
                "FROM student_course sc " +
                "LEFT JOIN student s ON sc.student_id = s.id " +
                "LEFT JOIN course c ON sc.course_id = c.id " +
                "ORDER BY sc.id ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public List<Score> findByPage(int page, int size) {
        List<Score> list = new ArrayList<>();
        String sql = "SELECT sc.*, s.name AS student_name, c.name AS course_name " +
                "FROM student_course sc " +
                "LEFT JOIN student s ON sc.student_id = s.id " +
                "LEFT JOIN course c ON sc.course_id = c.id " +
                "ORDER BY sc.id ASC LIMIT ? OFFSET ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, size);
            pstmt.setInt(2, (page - 1) * size);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public List<Score> findByStudentId(Integer studentId) {
        List<Score> list = new ArrayList<>();
        String sql = "SELECT sc.*, s.name AS student_name, c.name AS course_name " +
                "FROM student_course sc " +
                "LEFT JOIN student s ON sc.student_id = s.id " +
                "LEFT JOIN course c ON sc.course_id = c.id " +
                "WHERE sc.student_id = ? ORDER BY sc.id ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public List<Score> findByCourseId(Integer courseId) {
        List<Score> list = new ArrayList<>();
        String sql = "SELECT sc.*, s.name AS student_name, c.name AS course_name " +
                "FROM student_course sc " +
                "LEFT JOIN student s ON sc.student_id = s.id " +
                "LEFT JOIN course c ON sc.course_id = c.id " +
                "WHERE sc.course_id = ? ORDER BY sc.id ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public Score findById(Integer id) {
        String sql = "SELECT sc.*, s.name AS student_name, c.name AS course_name " +
                "FROM student_course sc " +
                "LEFT JOIN student s ON sc.student_id = s.id " +
                "LEFT JOIN course c ON sc.course_id = c.id " +
                "WHERE sc.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt, rs);
        }
        return null;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM student_course";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt, rs);
        }
        return 0;
    }

    @Override
    public void insert(Score score) {
        String sql = "INSERT INTO student_course (student_id, course_id, score) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, score.getStudentId());
            pstmt.setInt(2, score.getCourseId());
            pstmt.setObject(3, score.getScore());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt);
        }
    }

    @Override
    public void update(Score score) {
        String sql = "UPDATE student_course SET student_id = ?, course_id = ?, score = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, score.getStudentId());
            pstmt.setInt(2, score.getCourseId());
            pstmt.setObject(3, score.getScore());
            pstmt.setInt(4, score.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM student_course WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt);
        }
    }

    private Score mapRow(ResultSet rs) throws Exception {
        Score s = new Score();
        s.setId(rs.getInt("id"));
        s.setStudentId(rs.getInt("student_id"));
        s.setCourseId(rs.getInt("course_id"));
        s.setScore(rs.getDouble("score"));
        s.setStudentName(rs.getString("student_name"));
        s.setCourseName(rs.getString("course_name"));
        return s;
    }
}
