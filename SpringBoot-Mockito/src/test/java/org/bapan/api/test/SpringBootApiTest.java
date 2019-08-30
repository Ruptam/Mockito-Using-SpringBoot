	/**
 * 
 */
package org.bapan.api.test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bapan.controller.UserController;
import org.bapan.entity.UserEntity;
import org.bapan.repository.UserRepository;
import org.bapan.service.UserService;
import org.bapan.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ch.qos.logback.core.status.Status;

/**
 * 
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class SpringBootApiTest {
	/**
	 * 
	 */
	@Autowired
	private MockMvc mockMvc;
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
	/**
	 * 
	 * @author Ruptam
	 *
	 */
	@TestConfiguration
	static class UserServiceImplTestConfiguration {
		/**
		 * 
		 * @return
		 */
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}
	/**
	 * 
	 */
	private static final String MOCK_ALL_USERS = "[{\"userId\":200,\"name\":\"Mark\",\"address\":\"Delhi\",\"age\":53},"
			+ "{\"userId\":201,\"name\":\"Mary\",\"address\":\"Chennai\",\"age\":25}]";
	/**
	 * 
	 */
	private static final String SINGLE_USER = "{\"userId\":56,\"name\":\"James\",\"address\":\"Hindmotor\",\"age\":34}";
	/**
	 * 
	 */
	private static final String ADDRESS="Hindmotor";
	/**
	 * 
	 */
	private static final String INVALID_URL = "/user/{uid}";
	/**
	 * 
	 */
	private static final String URL = "/user/uid";
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test
	public void getAllUsersTest() throws Exception {
		Mockito.when(userRepository.findAll()).thenReturn(
				Stream.of(new UserEntity(200, "Mark", "Delhi", 53),
						new UserEntity(201, "Mary", "Chennai", 25))
				.collect(Collectors.toList()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allUsers");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		Assert.assertEquals(MOCK_ALL_USERS, result.getResponse().getContentAsString());
	}
	
	@Test
	public void getUserByAddress() throws Exception {
		UserEntity testUser = new UserEntity();//56,"James","Hindmotor",34
		testUser.setUserId(56);
		testUser.setName("James");
		testUser.setAge(34);
		testUser.setAddress("Hindmotor");
		Mockito.when(userRepository.findByAddress(ADDRESS)).thenReturn(testUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/byAddress/"+ADDRESS);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(SINGLE_USER, result.getResponse().getContentAsString());
		testUser.getAddress();
		testUser.getAge();
		testUser.getName();
		testUser.getUserId();
	}

	@Test
	public void saveUser() throws Exception {
		UserEntity testUser = new UserEntity(56,"James","Hindmotor",34); 
		Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
							.contentType(MediaType.APPLICATION_JSON).content(SINGLE_USER);
		this.mockMvc.perform(requestBuilder)
							.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void deleteUser() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/"+"56");
		//Mockito.verify(userRepository,Mockito.times(1)).deleteByUserId(56);
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteUserByNullId() {
		userService.deleteUser(null);
	}
	
	@Test
	public void testDeleteUserByAlphaId() {
		userService.deleteUser("A");
	}
	
	
}
