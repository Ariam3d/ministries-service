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

	// Testing API "Get all ministries", expected response: OK
	@Test
	public void getAllMinistries() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/ministries")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	// Testing response API "Get a specific ministry", expected: OK
	@Test
	public void getOneMinistry() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/ministries/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	// Testing response API "Get a specific ministry", expected: NotFound
	@Test
	public void returnNotFoundForInvalidSingleMinistry() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/ministries/7")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}

	// Testing response API "Create a ministry", expected: OK
	@Test
	public void addNewMinistry() throws Exception {
		String ministry = "{\"name\":\"Adulto mayor\",\"description\":\"Ministerio de ayuda a los ancianos.\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/ministries")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ministry)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

}
