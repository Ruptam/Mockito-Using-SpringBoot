/**
 * 
 */
package org.bapan.service;

import java.util.List;

import org.bapan.entity.UserEntity;
import org.bapan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ruptam
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	/**
	 * 
	 */
	public UserEntity saveUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}
	
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUserByAddress(String address) {
		return userRepository.findByAddress(address);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteByUserId(id);
	}

}
