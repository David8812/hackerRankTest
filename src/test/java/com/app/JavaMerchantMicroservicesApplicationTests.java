package com.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.app.controllers.OffersRestfullController;
import com.app.services.OffersService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OffersRestfullController.class)
public class JavaMerchantMicroservicesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	OffersService offersService;
	
	@Test
	public void shouldReturnHttpCode200OnGet() throws Exception {
		mockMvc.perform(get("/api/offers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
