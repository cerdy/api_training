package fr.esiea.ex4A.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerIT {
    private final MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();
    @MockBean private UserRepository repository;
    UserControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test void addMember () throws Exception {
        String url = "/api/inscription";
        UserData data = new UserData("mail@gmail.com", "username", "userTweeter", "FR", "M", "F");
        String json = mapper.writeValueAsString(data);
        when(repository.getUserFromUserName("username")).thenReturn(data);

        mockMvc
            .perform(post(url).contentType(APPLICATION_JSON).content(json))
            .andExpect(status().isOk());
    }
    @Test void sayHello () throws Exception {
        String url = "/api/matches?userName=pierre&userCountry=FR";
        UserData data = repository.getUserFromUserName("pierre");
        String json = mapper.writeValueAsString(data);
        when(repository.getUserFromUserName(any())).thenReturn(data);

        mockMvc
            .perform(get(url))
            .andExpect(status().isOk());
    }
}
