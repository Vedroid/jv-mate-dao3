package ua.vedroid.dao3.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.exceptions.DataProcessingException;
import ua.vedroid.dao3.util.ConnectionUtil;

public class DeleteDataController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String query = "TRUNCATE TABLE taxi_service.manufacturers RESTART IDENTITY CASCADE; "
                + "TRUNCATE TABLE taxi_service.cars RESTART IDENTITY CASCADE; "
                + "TRUNCATE TABLE taxi_service.drivers RESTART IDENTITY CASCADE; ";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Truncate tables is failed", ex);
        }
        req.setAttribute("msg", "All data has been deleted from DB!");
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
