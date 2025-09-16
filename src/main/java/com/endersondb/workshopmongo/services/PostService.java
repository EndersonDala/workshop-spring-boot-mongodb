package com.endersondb.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endersondb.workshopmongo.domain.Post;
import com.endersondb.workshopmongo.repository.PostRepository;
import com.endersondb.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository rep;
	
	public Post findById(String id) {
		Optional<Post> post = rep.findById(id);
		if (post.isPresent()) {
			return post.get();
		} else {
			throw new ObjectNotFoundException("Post não encontrado.");
		}
	}
	
	public Post insert(Post obj) {
		return rep.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		UpdateData(newObj, obj);
		return rep.save(newObj);
	}
	
	private void UpdateData(Post newObj, Post obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setDate(obj.getDate());
		newObj.setBody(obj.getBody());
		newObj.setAuthor(obj.getAuthor());
	}
	
	public List<Post> findByTitle(String text){
		return rep.findByTitle(text);
		//return rep.findByTitleContainingIgnoreCase(text);
	}

}
