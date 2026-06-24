package com.stu.dao;

import com.stu.entity.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StudentRemoteDao implements StudentDao {

    private String host;
    private int port;

    public StudentRemoteDao() {
        this.host = "127.0.0.1";
        this.port = 8888;
    }

    public StudentRemoteDao(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public List<Student> findAll() {
        JSONObject req = new JSONObject();
        req.put("command", "findAll");
        req.put("entity", "student");
        JSONObject resp = sendRequest(req);
        return parseStudentList(resp);
    }

    @Override
    public List<Student> findByPage(int page, int size) {
        JSONObject req = new JSONObject();
        req.put("command", "findByPage");
        req.put("entity", "student");
        req.put("page", page);
        req.put("size", size);
        JSONObject resp = sendRequest(req);
        return parseStudentList(resp);
    }

    @Override
    public List<Student> findByName(String name) {
        JSONObject req = new JSONObject();
        req.put("command", "findByName");
        req.put("entity", "student");
        req.put("name", name);
        JSONObject resp = sendRequest(req);
        return parseStudentList(resp);
    }

    @Override
    public Student findById(Integer id) {
        JSONObject req = new JSONObject();
        req.put("command", "findById");
        req.put("entity", "student");
        req.put("id", id);
        JSONObject resp = sendRequest(req);
        if (resp != null && resp.optBoolean("success", false)) {
            return parseStudent(resp.getJSONObject("data"));
        }
        return null;
    }

    @Override
    public int count() {
        JSONObject req = new JSONObject();
        req.put("command", "count");
        req.put("entity", "student");
        JSONObject resp = sendRequest(req);
        if (resp != null && resp.optBoolean("success", false)) {
            return resp.getInt("data");
        }
        return 0;
    }

    @Override
    public void insert(Student student) {
        JSONObject req = new JSONObject();
        req.put("command", "insert");
        req.put("entity", "student");
        req.put("name", student.getName());
        req.put("gender", student.getGender());
        req.put("age", student.getAge());
        req.put("status", student.getStatus() != null ? student.getStatus() : "在读");
        sendRequest(req);
    }

    @Override
    public void update(Student student) {
        JSONObject req = new JSONObject();
        req.put("command", "update");
        req.put("entity", "student");
        req.put("id", student.getId());
        req.put("name", student.getName());
        req.put("gender", student.getGender());
        req.put("age", student.getAge());
        req.put("status", student.getStatus() != null ? student.getStatus() : "在读");
        sendRequest(req);
    }

    @Override
    public void delete(Integer id) {
        JSONObject req = new JSONObject();
        req.put("command", "delete");
        req.put("entity", "student");
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

    private List<Student> parseStudentList(JSONObject resp) {
        List<Student> list = new ArrayList<>();
        if (resp != null && resp.optBoolean("success", false)) {
            JSONArray array = resp.getJSONArray("data");
            for (int i = 0; i < array.size(); i++) {
                list.add(parseStudent(array.getJSONObject(i)));
            }
        }
        return list;
    }

    private Student parseStudent(JSONObject obj) {
        Student s = new Student();
        s.setId(obj.getInt("id"));
        s.setName(obj.getString("name"));
        s.setGender(obj.optString("gender", "男"));
        s.setAge(obj.getInt("age"));
        s.setStatus(obj.optString("status", "在读"));
        return s;
    }
}
