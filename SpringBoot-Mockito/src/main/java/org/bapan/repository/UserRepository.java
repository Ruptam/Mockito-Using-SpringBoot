/**
 * 
 */
package org.bapan.repository;

import org.bapan.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Ruptam
 *
 */
public interface UserRepository extends MongoRepository<UserEntity, Integer> {
	/**
	 * 
	 * @param address
	 * @return
	 */
	UserEntity findByAddress(String address);
	/**
	 * 
	 * @param id
	 */
	void deleteByUserId(int id);
}
