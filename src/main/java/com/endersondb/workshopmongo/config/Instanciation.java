package com.endersondb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.endersondb.workshopmongo.domain.Post;
import com.endersondb.workshopmongo.domain.User;
import com.endersondb.workshopmongo.repository.PostRepository;
import com.endersondb.workshopmongo.repository.UserRepository;

@Configuration
public class Instanciation implements CommandLineRunner {

	@Autowired
	private UserRepository rep;
	
	@Autowired
	private PostRepository prep;
	
	@Override
	public void run(String... args) throws Exception {
		rep.deleteAll();
		prep.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		rep.saveAll(Arrays.asList(maria, alex, bob));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Post post1 = new Post(null, sdf.parse("09/09/2025"), "Partiu Viagem", "Vou viajar para São Paulo, abraços !", maria);
		Post post2 = new Post(null, sdf.parse("10/09/2025"), "Bom dia", "Acordei feliz hoje", maria);
		
		prep.saveAll(Arrays.asList(post1, post2));
	}

}
