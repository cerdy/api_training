package fr.esiea.ex4A.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esiea.ex4A.user.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class HelloControllerIT {
    private final MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();
    @MockBean private HelloRepository repository;
    HelloControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test void hello_delegates_to_repository_when_name_param_is_present() throws Exception {
        String url = "/hello?name=test";
        HelloData data = new HelloData("test");
        String json = mapper.writeValueAsString(data);

        when(repository.getHelloFor(any())).thenReturn(data);

        mockMvc
            .perform(get(url))
            .andExpect(status().isOk())
            .andExpect(content().json(json));

        verify(repository).getHelloFor("test");
    }

    @Test
    void hello_delegates_to_random_when_name_param_is_absent() throws Exception {
        when(repository.randomHello()).thenReturn(new HelloData("randomtest"));

        mockMvc
            .perform(get("/hello"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type").value("hello"))
            .andExpect(jsonPath("$.name").value("randomtest"))
            .andExpect(jsonPath("$.completeSentence").value(allOf(startsWith("hello"), endsWith("!"))));

        verify(repository).randomHello();
    }
}
