package com.naveenmereddi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.naveenmereddi.models.dao.StatusDao;
import com.naveenmereddi.models.dao.UserDao;
import com.naveenmereddi.models.entity.Status;
import com.naveenmereddi.models.entity.User;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	@Bean
	public CommandLineRunner initStatus(StatusDao statusDao) {
		return(args) -> {
			statusDao.save(new Status("Not Started"));
			statusDao.save(new Status("In Progress"));
			statusDao.save(new Status("Complete"));

			// Find All statuses
			for(Status status :statusDao.findAll()) {
				System.out.println(status);
			}
		};
	}

	@Bean
	CommandLineRunner init(UserDao userDao) {
		return (evt) -> {
			userDao.save(new User("user1"));
			userDao.save(new User("user2"));
			userDao.save(new User("user3"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}