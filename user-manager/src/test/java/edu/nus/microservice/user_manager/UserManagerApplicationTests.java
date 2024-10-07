package edu.nus.microservice.user_manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nus.microservice.user_manager.model.EventUser;
import edu.nus.microservice.user_manager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class UserManagerApplicationTests {



	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserRepository userRepository;
	@Test
	public void givenEventUser_thenReturnJsonArray()
			throws Exception {
		EventUser mokuser = new EventUser();
		UUID mokid = UUID.randomUUID();
		mokuser.setUserId(mokid);
		mokuser.setActiveStatus(1);
		mokuser.setPassword("a");
		mokuser.setCreateDt(new Date());
		mokuser.setEmailAddress("a@b.com");
		mokuser.setUserName("a");
		mokuser.setRoleId(1);
		List<EventUser> allEventUser = Arrays.asList(mokuser);

		when(userRepository.findAll()).thenReturn(allEventUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/user-manager/user/all").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);


		mockMvc.perform(MockMvcRequestBuilders.get("/api/user-manager/user/a"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());



	}



}
