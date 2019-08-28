/**
 * 
 */
package org.bapan.controller;

import java.util.List;

import org.bapan.entity.UserEntity;
import org.bapan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruptam
 *
 */
@RestController
public class UserController {
	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @param userEntity
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity) {
		UserEntity savedUser = userService.saveUser(userEntity);
		return new ResponseEntity<UserEntity>(savedUser,HttpStatus.OK);
	}
	/**
	 * 
	 * @return
	 */
	@GetMapping(value="/allUsers")
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping(value="/byAddress/{addr}")
	public ResponseEntity<UserEntity> getUserByAddress(@PathVariable("addr") String address) {
		return new ResponseEntity<UserEntity>(userService.getUserByAddress(address),HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{uid}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable("uid") String id) {
		userService.deleteUser(id);
	}
}
