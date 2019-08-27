/**
 * 
 */
package org.bapan.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ruptam
 *
 */
@Document(collection="CUSTOMER")
public class UserEntity {
	/**
	 * 
	 */
	private int userId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private int age;
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @param userId
	 * @param name
	 * @param address
	 * @param age
	 */
	public UserEntity(int userId, String name, String address, int age) {
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.age = age;
	}
	/**
	 * 
	 */
	public UserEntity() {
	}
}
