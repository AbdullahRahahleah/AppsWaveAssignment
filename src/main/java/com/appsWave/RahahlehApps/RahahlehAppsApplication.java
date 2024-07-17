package com.appsWave.RahahlehApps;

import com.appsWave.RahahlehApps.entities.Role;
import com.appsWave.RahahlehApps.entities.User;
import com.appsWave.RahahlehApps.repositorty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class RahahlehAppsApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RahahlehAppsApplication.class, args);
	}

	public void run(String... args){
		//Create default admin user
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFullName("admin");
			user.setRole(Role.ADMIN);
			user.setDateOfBirth("1992-04-26");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
