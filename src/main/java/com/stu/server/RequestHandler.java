package com.stu.server;

import com.stu.dao.StudentDBDao;
import com.stu.dao.TeacherDBDao;
import com.stu.entity.Student;
import com.stu.entity.Teacher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class RequestHandler implements Runnable {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            String line = reader.readLine();
            if (line == null || line.isEmpty()) return;

            JSONObject request = JSONObject.fromObject(line);
            String command = request.getString("command");
            String entity = request.optString("entity", "student");

            JSONObject response = new JSONObject();

            if ("student".equals(entity)) {
                response = handleStudent(command, request);
            } else if ("teacher".equals(entity)) {
                response = handleTeacher(command, request);
            } else {
                response.put("success", false);
                response.put("message", "未知实体类型");
            }

            writer.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { socket.close(); } catch (Exception ignored) {}
        }
    }

    private JSONObject handleStudent(String command, JSONObject request) {
        StudentDBDao dao = new StudentDBDao();
        JSONObject response = new JSONObject();

        try {
            switch (command) {
                case "findAll": {
                    List<Student> list = dao.findAll();
                    JSONArray array = new JSONArray();
                    for (Student s : list) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", s.getId());
                        obj.put("name", s.getName());
                        obj.put("age", s.getAge());
                        array.add(obj);
                    }
                    response.put("success", true);
                    response.put("data", array);
                    break;
                }
                case "findByPage": {
                    int page = request.getInt("page");
                    int size = request.getInt("size");
                    List<Student> list = dao.findByPage(page, size);
                    JSONArray array = new JSONArray();
                    for (Student s : list) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", s.getId());
                        obj.put("name", s.getName());
                        obj.put("age", s.getAge());
                        array.add(obj);
                    }
                    response.put("success", true);
                    response.put("data", array);
                    break;
                }
                case "findById": {
                    int id = request.getInt("id");
                    Student s = dao.findById(id);
                    if (s != null) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", s.getId());
                        obj.put("name", s.getName());
                        obj.put("age", s.getAge());
                        response.put("success", true);
                        response.put("data", obj);
                    } else {
                        response.put("success", false);
                    }
                    break;
                }
                case "count": {
                    int count = dao.count();
                    response.put("success", true);
                    response.put("data", count);
                    break;
                }
                case "insert": {
                    Student s = new Student();
                    s.setName(request.getString("name"));
                    s.setAge(request.getInt("age"));
                    dao.insert(s);
                    response.put("success", true);
                    break;
                }
                case "update": {
                    Student s = new Student();
                    s.setId(request.getInt("id"));
                    s.setName(request.getString("name"));
                    s.setAge(request.getInt("age"));
                    dao.update(s);
                    response.put("success", true);
                    break;
                }
                case "delete": {
                    dao.delete(request.getInt("id"));
                    response.put("success", true);
                    break;
                }
                default:
                    response.put("success", false);
                    response.put("message", "未知命令");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    private JSONObject handleTeacher(String command, JSONObject request) {
        TeacherDBDao dao = new TeacherDBDao();
        JSONObject response = new JSONObject();

        try {
            switch (command) {
                case "findAll": {
                    List<Teacher> list = dao.findAll();
                    JSONArray array = new JSONArray();
                    for (Teacher t : list) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", t.getId());
                        obj.put("name", t.getName());
                        obj.put("age", t.getAge());
                        obj.put("salary", t.getSalary());
                        array.add(obj);
                    }
                    response.put("success", true);
                    response.put("data", array);
                    break;
                }
                case "findByPage": {
                    int page = request.getInt("page");
                    int size = request.getInt("size");
                    List<Teacher> list = dao.findByPage(page, size);
                    JSONArray array = new JSONArray();
                    for (Teacher t : list) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", t.getId());
                        obj.put("name", t.getName());
                        obj.put("age", t.getAge());
                        obj.put("salary", t.getSalary());
                        array.add(obj);
                    }
                    response.put("success", true);
                    response.put("data", array);
                    break;
                }
                case "findById": {
                    int id = request.getInt("id");
                    Teacher t = dao.findById(id);
                    if (t != null) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", t.getId());
                        obj.put("name", t.getName());
                        obj.put("age", t.getAge());
                        obj.put("salary", t.getSalary());
                        response.put("success", true);
                        response.put("data", obj);
                    } else {
                        response.put("success", false);
                    }
                    break;
                }
                case "count": {
                    int count = dao.count();
                    response.put("success", true);
                    response.put("data", count);
                    break;
                }
                case "insert": {
                    Teacher t = new Teacher();
                    t.setName(request.getString("name"));
                    t.setAge(request.getInt("age"));
                    t.setSalary(request.getDouble("salary"));
                    dao.insert(t);
                    response.put("success", true);
                    break;
                }
                case "update": {
                    Teacher t = new Teacher();
                    t.setId(request.getInt("id"));
                    t.setName(request.getString("name"));
                    t.setAge(request.getInt("age"));
                    t.setSalary(request.getDouble("salary"));
                    dao.update(t);
                    response.put("success", true);
                    break;
                }
                case "delete": {
                    dao.delete(request.getInt("id"));
                    response.put("success", true);
                    break;
                }
                default:
                    response.put("success", false);
                    response.put("message", "未知命令");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
