package com.stu.dao;

import com.stu.entity.Teacher;
import com.stu.util.DBConnection;
import com.stu.util.DBReflectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDBReflectDao implements TeacherDao {

    @Override
    public List<Teacher> findAll() {
        List<Teacher> list = new ArrayList<>();
        String sql = DBReflectUtil.generateSelectAllSQL(Teacher.class);
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
    public List<Teacher> findByPage(int page, int size) {
        List<Teacher> list = new ArrayList<>();
        String sql = DBReflectUtil.generatePageSQL(Teacher.class);
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
    public Teacher findById(Integer id) {
        String sql = DBReflectUtil.generateSelectByIdSQL(Teacher.class);
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
        String sql = DBReflectUtil.generateCountSQL(Teacher.class);
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
    public void insert(Teacher teacher) {
        String sql = DBReflectUtil.generateInsertSQL(teacher);
        Object[] params = DBReflectUtil.getInsertParams(teacher);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt);
        }
    }

    @Override
    public void update(Teacher teacher) {
        String sql = DBReflectUtil.generateUpdateSQL(teacher);
        Object[] params = DBReflectUtil.getUpdateParams(teacher);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, pstmt);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = DBReflectUtil.generateDeleteSQL(Teacher.class);
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

    private Teacher mapRow(ResultSet rs) throws Exception {
        Teacher t = new Teacher();
        t.setId(rs.getInt("id"));
        t.setName(rs.getString("name"));
        t.setAge(rs.getInt("age"));
        t.setSalary(rs.getDouble("salary"));
        return t;
    }
}
