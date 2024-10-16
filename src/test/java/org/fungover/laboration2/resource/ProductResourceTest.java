package org.fungover.laboration2.resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.assertj.core.api.Assertions;
import org.fungover.laboration2.entities.Category;
import org.fungover.laboration2.entities.Product;
import org.fungover.laboration2.exceptionmapper.IllegalPersonLibraryStateException;
import org.fungover.laboration2.exceptionmapper.IllegalPersonLibraryStateExceptionMapper;
import org.fungover.laboration2.service.ImplWarehouseService;
import org.fungover.laboration2.service.WarehouseService;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.spi.Dispatcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductResourceTest {

    Dispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        WarehouseService service = new ImplWarehouseService();
        ProductResource productResource = new ProductResource(service);
        dispatcher = MockDispatcherFactory.createDispatcher();
        //dispatcher.getRegistry().addPerRequestResource(PersonResource.class);
        dispatcher.getRegistry().addSingletonResource(productResource);

        ExceptionMapper<IllegalPersonLibraryStateException> exMapper =
                new IllegalPersonLibraryStateExceptionMapper();
        dispatcher.getProviderFactory().registerProviderInstance(exMapper);
    }

    @Test
    void callingWarehouseServiceShouldReturnThisIsWarehouseService() throws URISyntaxException, UnsupportedEncodingException {
        MockHttpRequest request = MockHttpRequest.get("/warehouse");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        assertEquals(200, response.getStatus());
        assertEquals("This is warehouse service!", response.getContentAsString());
    }


//    @Test
//    void whenPostingJsonRepresentingPersonThenShouldGet201Created() throws URISyntaxException, JsonProcessingException {
//        MockHttpRequest request = MockHttpRequest.post("/product");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String json = new ObjectMapper().writeValueAsString(
//                new Product("3", "Milk", Category.drink, 10,
//                        LocalDate.of(2024, 1, 1),
//                        LocalDate.of(2024, 1, 1)));
//        request.content(json.getBytes());
//        request.contentType(MediaType.APPLICATION_JSON);
//        MockHttpResponse response = new MockHttpResponse();
//        dispatcher.invoke(request, response);
//        // Assert the response status code and content
//        assertEquals(201, response.getStatus());
//    }

//    @Test
//    void getAllPersonsGivesJsonObject() throws URISyntaxException, UnsupportedEncodingException, JSONException {
//        MockHttpRequest request = MockHttpRequest.get("/products");
//        MockHttpResponse response = new MockHttpResponse();
//        dispatcher.invoke(request, response);
//        // Assert the response status code and content
//        assertEquals(200, response.getStatus());
//        String jsonBody = response.getContentAsString();
//        String expectedJson = """
//                {"products":[{"id": "1",
//                                   "name": "iPhone",
//                                   "category": "electronics",
//                                   "rating": 10,
//                                   "createdDate": [
//                                     2024,
//                                     1,
//                                     1
//                                   ],
//                                   "lastModifiedDate": [
//                                     2024,
//                                     1,
//                                     1
//                                   ]}
//                ],"statusCode":"Updated"}""";
//        //https://www.baeldung.com/jsonassert
//        //Assert on json content
//        JSONObject expected = new JSONObject();
//        JSONObject actual = new JSONObject(jsonBody);
//        expected.put("statusCode", "Updated");
//        JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
//    }


    @Test
    void shouldHaveVerifyAnnotationOnPersonParameter() throws NoSuchMethodException {
        // Get the method
        Method method = ProductResource.class.getMethod("names", Product.class, String.class);

        // Get the parameters of the method
        Parameter[] parameters = method.getParameters();

        // Check if the first parameter has the @Verify annotation
        boolean isAnnotationPresent = parameters[0].isAnnotationPresent(Valid.class);

        // Assert using AssertJ
        Assertions.assertThat(isAnnotationPresent).isTrue();
    }
}
