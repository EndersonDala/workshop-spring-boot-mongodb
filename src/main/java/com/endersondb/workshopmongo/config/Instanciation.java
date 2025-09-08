package com.endersondb.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.endersondb.workshopmongo.domain.User;
import com.endersondb.workshopmongo.repository.UserRepository;

@Configuration
public class Instanciation implements CommandLineRunner {

	@Autowired
	private UserRepository rep;
	
	@Override
	public void run(String... args) throws Exception {
		rep.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		rep.saveAll(Arrays.asList(maria, alex, bob));
	}

}
