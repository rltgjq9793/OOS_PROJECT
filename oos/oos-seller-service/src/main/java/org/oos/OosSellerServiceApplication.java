package org.oos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("org.oos.mapper")
@EnableScheduling
public class OosSellerServiceApplication {



	
	public static void main(String[] args) {
		SpringApplication.run(OosSellerServiceApplication.class, args);
	}
}
