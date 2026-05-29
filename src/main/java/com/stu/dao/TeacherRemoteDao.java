package com.stu.dao;

import com.stu.entity.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TeacherRemoteDao implements TeacherDao {

    private String host;
    private int port;

    public TeacherRemoteDao() {
        this.host = "127.0.0.1";
        this.port = 8888;
    }

    public TeacherRemoteDao(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public List<Teacher> findAll() {
        JSONObject req = new JSONObject();
        req.put("command", "findAll");
        req.put("entity", "teacher");
        JSONObject resp = sendRequest(req);
        return parseTeacherList(resp);
    }

    @Override
    public List<Teacher> findByPage(int page, int size) {
        JSONObject req = new JSONObject();
        req.put("command", "findByPage");
        req.put("entity", "teacher");
        req.put("page", page);
        req.put("size", size);
        JSONObject resp = sendRequest(req);
        return parseTeacherList(resp);
    }

    @Override
    public Teacher findById(Integer id) {
        JSONObject req = new JSONObject();
        req.put("command", "findById");
        req.put("entity", "teacher");
        req.put("id", id);
        JSONObject resp = sendRequest(req);
        if (resp != null && resp.optBoolean("success", false)) {
            return parseTeacher(resp.getJSONObject("data"));
        }
        return null;
    }

    @Override
    public int count() {
        JSONObject req = new JSONObject();
        req.put("command", "count");
        req.put("entity", "teacher");
        JSONObject resp = sendRequest(req);
        if (resp != null && resp.optBoolean("success", false)) {
            return resp.getInt("data");
        }
        return 0;
    }

    @Override
    public void insert(Teacher teacher) {
        JSONObject req = new JSONObject();
        req.put("command", "insert");
        req.put("entity", "teacher");
        req.put("name", teacher.getName());
        req.put("age", teacher.getAge());
        req.put("salary", teacher.getSalary());
        sendRequest(req);
    }

    @Override
    public void update(Teacher teacher) {
        JSONObject req = new JSONObject();
        req.put("command", "update");
        req.put("entity", "teacher");
        req.put("id", teacher.getId());
        req.put("name", teacher.getName());
        req.put("age", teacher.getAge());
        req.put("salary", teacher.getSalary());
        sendRequest(req);
    }

    @Override
    public void delete(Integer id) {
        JSONObject req = new JSONObject();
        req.put("command", "delete");
        req.put("entity", "teacher");
        req.put("id", id);
        sendRequest(req);
    }

    private JSONObject sendRequest(JSONObject request) {
        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println(request.toString());
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return JSONObject.fromObject(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Teacher> parseTeacherList(JSONObject resp) {
        List<Teacher> list = new ArrayList<>();
        if (resp != null && resp.optBoolean("success", false)) {
            JSONArray array = resp.getJSONArray("data");
            for (int i = 0; i < array.size(); i++) {
                list.add(parseTeacher(array.getJSONObject(i)));
            }
        }
        return list;
    }

    private Teacher parseTeacher(JSONObject obj) {
        Teacher t = new Teacher();
        t.setId(obj.getInt("id"));
        t.setName(obj.getString("name"));
        t.setAge(obj.getInt("age"));
        t.setSalary(obj.getDouble("salary"));
        return t;
    }
}
