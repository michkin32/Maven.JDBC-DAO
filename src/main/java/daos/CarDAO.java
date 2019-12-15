package daos;

import models.Car;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CarDAO implements DAO{

    public Car findById(int id) {
        Connection connection = MakeConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car WHERE carId=" + id);
            if(rs.next())
            {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    private Car extractCarFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarId(rs.getInt("Carid"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setColor(rs.getString("color"));
        car.setYear(rs.getString("year"));
        car.setVin(rs.getString("vin"));
        return car;
    }


    public Set<Car> findAll() {
        Connection connection = MakeConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car");
            Set carSet = new HashSet<Car>();
            while (rs.next()) {
                Car car = extractCarFromResultSet(rs);
                carSet.add(car);
            }   return carSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object update(Object dto) {
        return null;
    }

    public Object create(Object dto) {
        return null;
    }

    public Boolean update(Car car) {
        Connection connection = MakeConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE car SET make=?, model=?, year=?, color=?, vin=? WHERE carId=?");
            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getVin());
            ps.setInt(6, car.getCarId());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    public Boolean Create(Car car) {
        Connection connection = MakeConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO car VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getVin());
            ps.setString(6, car.getVin());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public Boolean delete(int id) {

        Connection connection = MakeConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("Delete FROM car WHERE carId=" + id);

            if (i == 1) {
                return true;
            }
        }   catch (SQLException e)  {
            e.printStackTrace();
        }
        return false;
    }
}


