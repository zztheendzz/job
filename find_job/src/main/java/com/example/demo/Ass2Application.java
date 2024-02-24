package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class} )
public class Ass2Application {

	public static void main(String[] args) {
		SpringApplication.run(Ass2Application.class, args);
		
		
//		@Bean
//		public CommandLineRunner commandLineRunner(
//				AuthenticationService service
//		) {
//			return args -> {
//				var admin = RegisterRequest.builder()
//						.firstname("Admin")
//						.lastname("Admin")
//						.email("admin@mail.com")
//						.password("password")
//						.role(ADMIN)
//						.build();
//				System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//				var manager = RegisterRequest.builder()
//						.firstname("Admin")
//						.lastname("Admin")
//						.email("manager@mail.com")
//						.password("password")
//						.role(MANAGER)
//						.build();
//				System.out.println("Manager token: " + service.register(manager).getAccessToken());
//
//			};
	}

}
