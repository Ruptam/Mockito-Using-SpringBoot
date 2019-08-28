/**
 * 
 */
package org.bapan.service;

import java.util.List;

import org.bapan.entity.UserEntity;

/**
 * @author Ruptam
 *
 */
public interface UserService {
	/**
	 * 
	 * @param userEntity
	 * @return
	 */
	UserEntity saveUser(UserEntity userEntity);
	/**
	 * 
	 * @return
	 */
	List<UserEntity> getAllUsers();
	/**
	 * 
	 * @param userId
	 * @return
	 */
	UserEntity getUserByAddress(String address);
	/**
	 * 
	 * @param id
	 */
	void deleteUser(String id);
}
