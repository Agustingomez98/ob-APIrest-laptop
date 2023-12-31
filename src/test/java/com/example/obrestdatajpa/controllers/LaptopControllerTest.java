package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private  int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+ port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]>  response =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        List<Laptop> laptops = Arrays.asList(response.getBody());
    }

    @Test
    void findOneId() {
        ResponseEntity<Laptop>  response =
                testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @DisplayName("Comprobar Test para crear una entidad")
    @Test
    public void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "marca": "Noblex",
                    "modelo": "999",
                    "precio": 1400,
                    "stock": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/crearlaptop", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();

        assertEquals(1L,result.getId());
        assertEquals("Noblex",result.getMarca());
    }
}