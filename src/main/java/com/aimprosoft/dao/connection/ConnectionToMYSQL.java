package com.aimprosoft.dao.connection;
import java.sql.*;

public class ConnectionToMYSQL {

    //JDBC int url, user, password
    private static  final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "1";

    public static  Connection getCon() {
        Connection con = null;
        try{
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is MySQL JDBC Driver?");
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection (URL, USER, PASS );
        }
        catch (SQLException ignored){

        }

        return con;
    }
    public static  void closeCon(Connection con) throws SQLException {
        if(con!=null){
            con.close();
        }

    }


    //---------
/*        public Boolean deleteUser(Integer id){
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement pStm = connection.prepareStatement("DELETE FROM users WHERE id= ?");
                pStm.setInt(1, id);
                int executeUpdate = pStm.executeUpdate();

                return executeUpdate > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        System.out.println("it will never be");
                    }
                }
            }
            return false;
        }*/

}