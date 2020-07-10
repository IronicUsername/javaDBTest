package app;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import java.sql.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class Database {
    private Connection conn = null;
    protected String db;
    protected String user;
    protected String pass;

    public Database(String db, String user, String pass) throws SQLException{
        this.db = db;
        this.user = user;
        this.pass = pass;

        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/" + this.db, this.user, this.pass);
    }


    public ResultSet query(String sql) throws SQLException{
        Statement st = this.conn.createStatement();
        return st.executeQuery(sql);
    }


    public void initDatabase(){
        String s = new String();
        StringBuffer sb = new StringBuffer();

        try{
            System.out.println(System.getProperty("user.dir"));
            FileReader reader = new FileReader(new File(System.getProperty("user.dir") + "/src/db/schema.sql"));
            BufferedReader bReader = new BufferedReader(reader);

            while((s = bReader.readLine()) != null){
                sb.append(s);
            }
            bReader.close();

            String[] insert = sb.toString().split(";");
            Statement st = this.conn.createStatement();

            for(int i = 0; i < insert.length; i++){
                if(!insert[i].trim().equals("")){
                    st.executeUpdate(insert[i]);
                    System.out.println(">>" + insert[i]);
                }
            }
            System.out.println(System.getProperty("user.dir"));
            System.out.println("Database all setup.");
        } catch(MySQLIntegrityConstraintViolationException e){
            System.out.print("Database already initialized.");
        } catch(SQLException e){
            System.out.println("There was an error: " + e);
            System.out.println("Maybe check your database credentials");
        } catch(Exception e){
            System.out.println("Something wrong happened: " + e);
        }
    }


    @Override
    protected void finalize() throws Throwable{
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }
} // Database
