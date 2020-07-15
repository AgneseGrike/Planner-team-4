package DatabaseLayer;


import DatabaseLayer.Entities.Appointment;
import DatabaseLayer.Entities.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseManager {

    Connection conn;

    public DatabaseManager ()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/planner","serverConnection","e2e4RTRfm!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<Appointment> getAppointments (User user) {
        Set<Appointment> appointments = new HashSet<>();
        try {
            PreparedStatement statement = conn.prepareStatement("select * from appointments where userID = ?");
            statement.setLong(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getLong(1));
                appointment.setUserID(rs.getLong(2));
                appointment.setStartDateTime(rs.getTimestamp(3).toLocalDateTime());
                appointment.setEndDateTime(rs.getTimestamp(4).toLocalDateTime());
                appointment.setNotes(rs.getString(5));
                appointments.add(appointment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public User findUser(long id) {
        User user = new User(0, null, null, null, null);
        try {
            PreparedStatement statement = conn.prepareStatement("select * from users where id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setName(rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    }
