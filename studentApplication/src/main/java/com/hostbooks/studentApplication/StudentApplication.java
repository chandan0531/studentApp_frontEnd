package com.hostbooks.studentApplication;

import com.hostbooks.studentApplication.security.User;
import com.hostbooks.studentApplication.userrepo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {


		User user = new User();
		user.setUserName("deepak");
		user.setEmail("deepak@gmail.com");
		user.setPassword(this.bCryptPasswordEncoder.encode("deepak"));
		user.setRole("ROLE_ADMIN");
		this.userRepository.save(user);



		User user1 = new User();
		user1.setUserName("chandan");
		user1.setEmail("chandan@gmail.com");
		user1.setPassword(this.bCryptPasswordEncoder.encode("chandan"));
		user1.setRole("ROLE_NORMAL");
		this.userRepository.save(user1);
	}
}
