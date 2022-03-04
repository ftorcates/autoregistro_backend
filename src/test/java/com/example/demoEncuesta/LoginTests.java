package com.example.demoEncuesta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import com.example.demoEncuesta.models.requests.UserLoginRequestModel;
import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;
import com.example.demoEncuesta.repositories.UserRepository;
import com.example.demoEncuesta.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class LoginTests {

    private static final String API_LOGIN_RUL = "/users/login";

    @Autowired
    TestRestTemplate testRestTemplate;

    //para insertar user en BD
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    /*@BeforeEach //esta funcion se ejecutara antes de cada uno de los tests
    public void cleanup() {
        userRepository.deleteAll();
    }*/

    @Test
    public void postLogin_sinCredenciales_retornaForbidden() {
        ResponseEntity<Object> response = login(null, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void postLogin_conCredencialesIncorrectas_retornaForbidden() {
        //inserta user en BD
        UserRegisterRequestModel user = TestUtil.createValidUser();
        userService.createUser(user);

        //datos para la prueba
        UserLoginRequestModel model = new UserLoginRequestModel();
        model.setEmail("fsth@gmail.com");
        model.setPassword("password");

        ResponseEntity<Object> response = login(model, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void postLogin_conCredencialesCorrectas_retornaOK() {
        //inserta user en BD
        UserRegisterRequestModel user = TestUtil.createValidUser();
        userService.createUser(user);

        //datos para la prueba
        UserLoginRequestModel model = new UserLoginRequestModel();
        model.setEmail(user.getEmail());
        model.setPassword(user.getPassword());

        ResponseEntity<Object> response = login(model, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    //Que devuelva el token
    public void postLogin_conCredencialesCorrectas_retornaAuthToken() {
        //inserta user en BD
        UserRegisterRequestModel user = TestUtil.createValidUser();
        userService.createUser(user);

        //datos para la prueba
        UserLoginRequestModel model = new UserLoginRequestModel();
        model.setEmail(user.getEmail());
        model.setPassword(user.getPassword());

        ResponseEntity<Map<String, String>> response = 
            login(model, new ParameterizedTypeReference<Map<String, String>>(){});
        Map<String, String> body = response.getBody();

        String token = body.get("token");

        assertTrue(token.contains("Bearer"));
    }
    
    public <T> ResponseEntity<T> login(UserLoginRequestModel data, Class<T> responseType) {

        return testRestTemplate.postForEntity(API_LOGIN_RUL, data, responseType);
    }

    public <T> ResponseEntity<T> login(UserLoginRequestModel data, ParameterizedTypeReference<T> responseType) {
        HttpEntity<UserLoginRequestModel> entity = new HttpEntity<UserLoginRequestModel>(data,new HttpHeaders());

        return testRestTemplate.exchange(API_LOGIN_RUL, HttpMethod.POST, entity, responseType);
    }

}
