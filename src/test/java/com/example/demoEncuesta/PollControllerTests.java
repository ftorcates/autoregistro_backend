package com.example.demoEncuesta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import com.example.demoEncuesta.models.requests.PollCreationRequestModel;
import com.example.demoEncuesta.models.requests.UserLoginRequestModel;
import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;
import com.example.demoEncuesta.models.responses.ValidationErrors;
import com.example.demoEncuesta.repositories.PollRepository;
import com.example.demoEncuesta.repositories.UserRepository;
import com.example.demoEncuesta.services.UserService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
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
@TestInstance(Lifecycle.PER_CLASS)
public class PollControllerTests {

    private static final String API_LOGIN_RUL = "/users/login";
    private static final String API_URL = "/polls";

    private String token = "";

    @Autowired
    TestRestTemplate testRestTemplate;

    //para insertar user en BD
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PollRepository pollRepository;

    @BeforeAll
    public void initializeObjects() {
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

        //Devuelve el token
        this.token = body.get("token");        
    }

    @AfterAll
    public void cleanupAfter(){
        userRepository.deleteAll();
    }

    @AfterEach
    public void cleanup(){
        pollRepository.deleteAll();
    }

    @Test
    public void createPoll_sinAutenticacion_retornaForbidden() {
        //creacion de encuesta sin autenticacion
        ResponseEntity<Object> response = createPoll(new PollCreationRequestModel(), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void createPoll_ConAutenticacionSinDatos_retornaBadRequest() {
        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            new PollCreationRequestModel(), new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createPoll_ConAutenticacionSinContenidoDeEncuesta_retornaBadRequest() {
        //Generacion de encueta valida
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.setContent("");

        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            poll, new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createPoll_ConAutenticacionSinPreguntas_retornaBadRequest() {
        //Generacion de encueta valida
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.setQuestions(null);

        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            poll, new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createPoll_ConAutenticacionConPreguntaValidaSinContenido_retornaBadRequest() {
        //Generacion de encueta valida
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setContent("");

        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            poll, new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createPoll_ConAutenticacionConPreguntaValidaConOrderCero_retornaBadRequest() {
        //Generacion de encueta valida
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setQuestionOrder(0);

        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            poll, new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createPoll_ConAutenticacionConPreguntaValidaConTipoIncorrecto_retornaBadRequest() {
        //Generacion de encueta valida
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setType("type");

        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            poll, new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createPoll_ConAutenticacionConPreguntaValidaSinRespuestas_retornaBadRequest() {
        //Generacion de encueta valida
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setAnswers(null);

        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            poll, new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createPoll_ConAutenticacionSinContenidoEnRespuesta_retornaBadRequest() {
        //Generacion de encueta valida
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).getAnswers().get(0).setContent("");

        //creacion de encuesta con autenticacion
        ResponseEntity<ValidationErrors> response = createPoll(
            poll, new ParameterizedTypeReference<ValidationErrors>(){}            
        );
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }


    //createPoll sin autenticacion
    public <T> ResponseEntity<T> createPoll(PollCreationRequestModel data, Class<T> responseType) {

        return testRestTemplate.postForEntity(API_URL, data, responseType);
    }

    //createPoll con autenticacion
    public <T> ResponseEntity<T> createPoll(PollCreationRequestModel data, ParameterizedTypeReference<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<PollCreationRequestModel> entity = new HttpEntity<PollCreationRequestModel>(data,headers);

        return testRestTemplate.exchange(API_URL, HttpMethod.POST, entity, responseType);
    }

    public <T> ResponseEntity<T> login(UserLoginRequestModel data, ParameterizedTypeReference<T> responseType) {
        HttpEntity<UserLoginRequestModel> entity = new HttpEntity<UserLoginRequestModel>(data,new HttpHeaders());

        return testRestTemplate.exchange(API_LOGIN_RUL, HttpMethod.POST, entity, responseType);
    }

    

    
}
