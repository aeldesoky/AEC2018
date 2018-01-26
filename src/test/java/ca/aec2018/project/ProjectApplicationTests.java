package ca.aec2018.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectApplicationTests {

	@Autowired
	ProjectController projectController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void notNull() throws Exception {
		assertThat(projectController).isNotNull();
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testSolar() throws Exception {
		this.mockMvc.perform(get("/solar"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testWind() throws Exception {
		this.mockMvc.perform(get("/wind"))
				.andDo(print())
				.andExpect(status().isOk());
	}

    @Test
    public void testDocs() throws Exception {
        this.mockMvc.perform(get("/docs"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSolarData() throws Exception {
        this.mockMvc.perform(get("/solar/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testWindData() throws Exception {
        this.mockMvc.perform(get("/wind/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
