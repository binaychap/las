package com.lcs.rest.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LcsControllerTest {
	
	@InjectMocks
	private LcsController lcsController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(lcsController).build();

	}

	@Test
	public void createLcsForNotCorrectFormat() throws Exception {
		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"setOfStrings\": [{\"value\": \"sungard\"}, {\"value\": \"sungarder\"]")).andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void setOfStringEmpty() throws Exception {
		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"setOfStrings\": ")).andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void setOfStringUnique() throws Exception {
		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"setOfStrings\": [{\"value\": \"sungard\"}, {\"value\": \"sungard\"]")).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void createLcsCorrectFormat() throws Exception {
		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"setOfStrings\": [{\"value\": \"sungard\"}, {\"value\": \"sungarder\"}, {\"value\": \"garden\"}]}")).andDo(print())
				.andExpect(status().isOk());
	}

}
