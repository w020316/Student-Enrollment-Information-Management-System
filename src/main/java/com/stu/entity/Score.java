package com.stu.entity;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;

@Entity("student_course")
public class Score {
    @ID
    @Column(value = "id", label = "编号")
    private Integer id;

    @Column(value = "student_id", label = "学生ID")
    private Integer studentId;

    @Column(value = "course_id", label = "课程ID")
    private Integer courseId;

    @Column(value = "score", label = "成绩")
    private Double score;

    private String studentName;
    private String courseName;

    public Score() {}

    public Score(Integer id, Integer studentId, Integer courseId, Double score) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    @Override
    public String toString() {
        return "Score{id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", score=" + score + "}";
    }
}
