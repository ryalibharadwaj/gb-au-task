/***********************************************Main CLASS*********************************************
 * Spring Boot Application Starting Point
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String args[]) {
		SpringApplication sApp = new SpringApplication(Main.class);
		sApp.setBannerMode(Banner.Mode.OFF);
		sApp.run(args);
	}
}
