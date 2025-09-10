package com.endersondb.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endersondb.workshopmongo.domain.User;
import com.endersondb.workshopmongo.dto.UserDTO;
import com.endersondb.workshopmongo.repository.UserRepository;
import com.endersondb.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository rep;
	
	public List<User> findAll(){
		return rep.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = rep.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new ObjectNotFoundException("Usuário não encontrado.");
		}
	}
	
	public User insert(User obj) {
		return rep.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		UpdateData(newObj, obj);
		return rep.save(newObj);
	}
	
	private void UpdateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
