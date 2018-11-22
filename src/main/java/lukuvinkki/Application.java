package lukuvinkki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


@SpringBootApplication
public class Application {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
