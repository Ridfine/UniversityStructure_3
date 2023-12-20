package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Objects;

@WebServlet("/hello")
public class MainServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String title = "Database Demo";

        String docType = "<!DOCTYPE html>";

        String GET = "SELECT " + request.getParameter("SELECT") + " FROM " + request.getParameter("FROM") + " WHERE " + request.getParameter("WHERE");
        writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
        writer.println("<h1>DEVELOPERS DATA " + request.getParameter("FROM") + "</h1>");
        writer.println("<br/>");

        if (Objects.equals(request.getParameter("FROM"), "universities")) {
            writer.println( CRUD.readUniversitiesData(GET) + "<br/>");
        } else if (Objects.equals(request.getParameter("FROM"), "faculties")) {
            writer.println(CRUD.readFacultiesData(GET) + "<br/>");
        } else if (Objects.equals(request.getParameter("FROM"), "departments")) {
            writer.println(CRUD.readDepartmentsitiesData(GET) + "<br/>");
        } else if (Objects.equals(request.getParameter("FROM"), "groups")) {
            writer.println(CRUD.readGroupsData(GET) + "<br/>");
        } else if (Objects.equals(request.getParameter("FROM"), "teachers")) {
            writer.println(CRUD.readTeachersData(GET) + "<br/>");
        } else if (Objects.equals(request.getParameter("FROM"), "students")) {
            writer.println(CRUD.readStudentsData(GET) + "<br/>");
        }
        writer.println("</body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (Objects.equals(request.getParameter("INSERT"), "universities")) {
            try {
                CRUD.createUniversity(new University(Integer.parseInt(request.getParameter("universityId")), request.getParameter("universityName")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(request.getParameter("INSERT"), "faculties")) {
            try {
                CRUD.createFaculty(new Faculty(Integer.parseInt(request.getParameter("facultiId")), request.getParameter("facultiName"),Integer.parseInt(request.getParameter("university_id"))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(request.getParameter("INSERT"), "departments")) {
            try {
                CRUD.createDepartment(new Department(Integer.parseInt(request.getParameter("department_id")), request.getParameter("departmentName"),Integer.parseInt(request.getParameter("faculti_id"))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(request.getParameter("INSERT"), "groups")) {
            try {
                CRUD.createGroup(new Group(Integer.parseInt(request.getParameter("group_id")), Integer.parseInt(request.getParameter("faculti_id")),Integer.parseInt(request.getParameter("headman_student_id"))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(request.getParameter("INSERT"), "teachers")) {
            try {
                CRUD.createTeacher(new Teacher(Integer.parseInt(request.getParameter("teacher_id")), Integer.parseInt(request.getParameter("university_id")),Integer.parseInt(request.getParameter("faculti_id")),Integer.parseInt(request.getParameter("department_id")),request.getParameter("name"),request.getParameter("surname"),request.getParameter("fatherName"),Integer.parseInt(request.getParameter("age"))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(request.getParameter("INSERT"), "students")) {
            try {
                CRUD.createStudent(new Student(Integer.parseInt(request.getParameter("student_id")), Integer.parseInt(request.getParameter("university_id")),Integer.parseInt(request.getParameter("faculti_id")),Integer.parseInt(request.getParameter("group_id")),request.getParameter("name"),request.getParameter("surname"),request.getParameter("fatherName"),Integer.parseInt(request.getParameter("age"))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Objects.equals(request.getParameter("UPDATE"), "universities")) {
            CRUD.updateUniversity(new University(Integer.parseInt(request.getParameter("universityId")), request.getParameter("universityName")));
        } else if (Objects.equals(request.getParameter("UPDATE"), "faculties")) {
            CRUD.updateFaculty(new Faculty(Integer.parseInt(request.getParameter("facultiId")), request.getParameter("facultiName"),Integer.parseInt(request.getParameter("university_id"))));
        } else if (Objects.equals(request.getParameter("UPDATE"), "departments")) {
            CRUD.updateDepartment(new Department(Integer.parseInt(request.getParameter("department_id")), request.getParameter("departmentName"),Integer.parseInt(request.getParameter("faculti_id"))));
        } else if (Objects.equals(request.getParameter("UPDATE"), "groups")) {
            CRUD.updateGroup(new Group(Integer.parseInt(request.getParameter("group_id")), Integer.parseInt(request.getParameter("faculti_id")),Integer.parseInt(request.getParameter("headman_student_id"))));
        } else if (Objects.equals(request.getParameter("UPDATE"), "teachers")) {
            CRUD.updateTeacher(new Teacher(Integer.parseInt(request.getParameter("teacher_id")), Integer.parseInt(request.getParameter("university_id")),Integer.parseInt(request.getParameter("faculti_id")),Integer.parseInt(request.getParameter("department_id")),request.getParameter("name"),request.getParameter("surname"),request.getParameter("fatherName"),Integer.parseInt(request.getParameter("age"))));
        } else if (Objects.equals(request.getParameter("UPDATE"), "students")) {
            CRUD.updateStudent(new Student(Integer.parseInt(request.getParameter("student_id")), Integer.parseInt(request.getParameter("university_id")),Integer.parseInt(request.getParameter("faculti_id")),Integer.parseInt(request.getParameter("group_id")),request.getParameter("name"),request.getParameter("surname"),request.getParameter("fatherName"),Integer.parseInt(request.getParameter("age"))));
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Objects.equals(request.getParameter("DELETE"), "universities")) {
            CRUD.deleteUniversity(Integer.parseInt(request.getParameter("universityId")));
        } else if (Objects.equals(request.getParameter("DELETE"), "faculties")) {
            CRUD.deleteFaculty(Integer.parseInt(request.getParameter("facultiId")));
        } else if (Objects.equals(request.getParameter("DELETE"), "departments")) {
            CRUD.deleteDepartment(Integer.parseInt(request.getParameter("department_id")));
        } else if (Objects.equals(request.getParameter("DELETE"), "groups")) {
            CRUD.deleteGroup(Integer.parseInt(request.getParameter("group_id")));
        } else if (Objects.equals(request.getParameter("DELETE"), "teachers")) {
            CRUD.deleteTeacher(Integer.parseInt(request.getParameter("teacher_id")));
        } else if (Objects.equals(request.getParameter("DELETE"), "students")) {
            CRUD.deleteStudent(Integer.parseInt(request.getParameter("student_id")));
        }
    }
}
