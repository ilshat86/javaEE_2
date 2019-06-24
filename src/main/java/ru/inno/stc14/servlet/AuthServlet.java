package ru.inno.stc14.servlet;

import ru.inno.stc14.service.PersonService;
import ru.inno.stc14.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/auth", name = "auth")
public class AuthServlet extends HttpServlet {
    private static String USERNAME = "root";
    private static String PASSWORD = "root";
    private static final String SELECT = "SELECT * from user where username = ?;";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/auth.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = (Connection) getServletContext().getAttribute("DBConnection");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1,login);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("Role"));
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("user", user);

        if (login.equals(user.getUsername()) && password.equals(user.getPassword()) && "ADMIN".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");}
         else if (login.equals(user.getUsername()) && password.equals(user.getPassword()) ) {
                resp.sendRedirect(req.getContextPath() + "/person/list");
        } else {
            req.setAttribute("errorMessage", "Login or password is incorrect");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}