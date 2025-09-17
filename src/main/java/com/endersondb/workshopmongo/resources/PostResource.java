package com.endersondb.workshopmongo.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.endersondb.workshopmongo.domain.Post;
import com.endersondb.workshopmongo.resources.util.URL;
import com.endersondb.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/post")
public class PostResource {

	@Autowired
	private PostService serv;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = serv.findById(id);
		return ResponseEntity.ok().body(post);	
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = serv.findByTitle(text);
		return ResponseEntity.ok().body(list);	
	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			                                     @RequestParam(value = "minDate", defaultValue = "") String minDate,
					                             @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = serv.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Post obj){
		Post post = serv.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		serv.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Post obj, @PathVariable String id){
		Post post = new Post(obj.getId(), obj.getDate(), obj.getTitle(), obj.getBody(), obj.getAuthor());
		post = serv.update(post);
		return ResponseEntity.noContent().build();
	}

}
