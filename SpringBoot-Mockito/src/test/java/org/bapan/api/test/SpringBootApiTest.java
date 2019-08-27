	/**
 * 
 */
package org.bapan.api.test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bapan.entity.UserEntity;
import org.bapan.repository.UserRepository;
import org.bapan.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApiTest {
	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	/**
	 * 
	 */
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void getAllUsers() {
		Mockito.when(userRepository.findAll()).thenReturn(
				Stream.of(new UserEntity(200, "Mark", "Delhi", 53),
						new UserEntity(200, "Mark", "Delhi", 53))
				.collect(Collectors.toList()));
		Assert.assertEquals(2, userService.getAllUsers().size());
	}
	
	@Test
	public void getUserByAddress() {
		String address = "Hindmotor";
		UserEntity testUser = new UserEntity(56,"James","Hindmotor",34);
		Mockito.when(userRepository.findByAddress(address)).thenReturn(testUser);
		Assert.assertEquals(testUser, userRepository.findByAddress(address));
	}

	@Test
	public void saveUser() {
		UserEntity testUser = new UserEntity(56,"James","Hindmotor",34); 
		Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
		Assert.assertEquals(testUser, userRepository.save(testUser));
	}
	
	@Test
	public void deleteUser() {
		UserEntity testUser = new UserEntity(56,"James","Hindmotor",34);
		userService.deleteUser(56);
		Mockito.verify(userRepository,Mockito.times(1)).deleteByUserId(56);
	}
}
