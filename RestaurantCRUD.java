import java.sql.*;

public class RestaurantCRUD {

    static final String URL = "jdbc:mysql://localhost:3306/restaurant_db?useSSL=false&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "Nusb@04082006";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            Statement stmt = con.createStatement();

            //  INSERT 
            System.out.println("Inserting Records...");

            // Insert Restaurants
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (1,'Cafe Java','Pune')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (2,'Food Hub','Mumbai')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (3,'Spice Villa','Delhi')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (4,'Urban Tadka','Pune')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (5,'Green Bowl','Bangalore')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (6,'Pizza Town','Chennai')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (7,'Burger Point','Hyderabad')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (8,'Cafe Coffee','Pune')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (9,'Royal Dine','Jaipur')");
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (10,'Food Court','Nagpur')");

            // Insert MenuItems
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (1,'Pasta',80,1)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (2,'Pizza',150,1)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (3,'Burger',90,2)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (4,'Noodles',120,3)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (5,'Paneer',200,4)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (6,'Pav Bhaji',70,1)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (7,'Sandwich',60,2)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (8,'Paratha',50,3)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (9,'Pulao',110,4)");
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (10,'Fries',40,1)");

            //  SELECT (price <= 100)
            System.out.println("\nMenu Items with price <= 100:");
            ResultSet rs1 = stmt.executeQuery(
                    "SELECT * FROM MenuItem WHERE Price <= 100");

            printResultSet(rs1);

            //  SELECT (Cafe Java) 
            System.out.println("\nMenu Items in Cafe Java:");
            ResultSet rs2 = stmt.executeQuery(
                    "SELECT m.* FROM MenuItem m JOIN Restaurant r " +
                    "ON m.ResId = r.Id WHERE r.Name='Cafe Java'");

            printResultSet(rs2);

            //  UPDATE 
            System.out.println("\nUpdating price <=100 to 200...");
            int updated = stmt.executeUpdate(
                    "UPDATE MenuItem SET Price=200 WHERE Price <= 100");
            System.out.println("Rows Updated: " + updated);

            // Show updated table
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM MenuItem");
            printResultSet(rs3);

            //  DELETE 
            System.out.println("\nDeleting items starting with 'P'...");
            int deleted = stmt.executeUpdate(
                    "DELETE FROM MenuItem WHERE Name LIKE 'P%'");
            System.out.println("Rows Deleted: " + deleted);

            // Show remaining data
            ResultSet rs4 = stmt.executeQuery("SELECT * FROM MenuItem");
            printResultSet(rs4);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  TABULAR PRINT METHOD 
    public static void printResultSet(ResultSet rs) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();

        // Print column names
        for (int i = 1; i <= columns; i++) {
            System.out.print(md.getColumnName(i) + "\t");
        }
        System.out.println();

        // Print rows
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
    }
}