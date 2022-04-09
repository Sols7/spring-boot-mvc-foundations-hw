package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class SpringDataFoundationsHwApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(SpringDataFoundationsHwApplication.class, args);

	}
}
