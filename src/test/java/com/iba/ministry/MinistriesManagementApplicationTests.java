package com.iba.ministry;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MinistriesManagementApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void getAllMinistries() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/ministries")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void getOneMinistry() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/ministries/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

}
