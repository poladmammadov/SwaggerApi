/*
 * Swagger Petstore
 * This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * OpenAPI spec version: 1.0.5
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.swagger.client.model.Pet;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * API tests for PetApi
 */
public class PetApiTest {

    private final PetApi api = new PetApi();
    private static WireMockServer wireMockServer;

    public PetApiTest() {
        api.getApiClient().setBasePath("http://localhost:" + wireMockServer.port());
    }

    @BeforeClass
    public static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();

        configureFor(wireMockServer.port());

        stubFor(post(urlPathMatching("/pet"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)));

        stubFor(put(urlPathMatching("/pet"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)));

        stubFor(get(urlPathMatching("/pet/10"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withBody("{\"id\":10,\"category\":{\"id\":7007,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":7007,\"name\":\"string\"}],\"status\":\"available\"}")));

        stubFor(delete(urlPathMatching("/pet/10"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)));

        stubFor(get(urlPathMatching("/pet/findByStatus"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withBody("[{\"id\":7007,\"category\":{\"id\":7007,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":7007,\"name\":\"string\"}],\"status\":\"available\"},{\"id\":44550,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"SuperHund\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"},{\"id\":3377,\"category\":{\"id\":0,\"name\":\"Lambo\"},\"name\":\"Lambo\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"Lambo\"}],\"status\":\"available\"}]")
                ));
    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }


    /**
     * Add a new pet to the store
     *
     * @throws Exception if the Api call fails
     */
    @Test
    public void addPetTest() throws Exception {
        Pet body = new Pet()
                .id(10L)
                .name("doggie")
                .status(Pet.StatusEnum.AVAILABLE)
                .photoUrls(Arrays.asList("http://some.pic"));
        api.addPet(body);

        verify(exactly(1), postRequestedFor(urlEqualTo("/pet")));
    }

    /**
     * Deletes a pet
     *
     * @throws Exception if the Api call fails
     */
    @Test
    public void deletePetTest() throws Exception {
        Long petId = 10L;
        String apiKey = "abc123";
        api.deletePet(petId, apiKey);

        verify(exactly(1), deleteRequestedFor(urlEqualTo("/pet/10")));
    }

    /**
     * Finds Pets by status
     * <p>
     * Multiple status values can be provided with comma separated strings
     *
     * @throws Exception if the Api call fails
     */
    @Test
    public void findPetsByStatusTest() throws Exception {
        List<String> status = Arrays.asList("available");
        List<Pet> response = api.findPetsByStatus(status);

        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

        verify(exactly(1), getRequestedFor(urlEqualTo("/pet/findByStatus?status=available")));
    }

    /**
     * Find pet by ID
     * <p>
     * Returns a single pet
     *
     * @throws Exception if the Api call fails
     */
    @Test
    public void getPetByIdTest() throws Exception {
        Long petId = 10L;
        Pet response = api.getPetById(petId);

        Assert.assertNotNull(response);
        Assert.assertEquals(10L, response.getId().longValue());
        Assert.assertEquals("doggie", response.getName());

        verify(exactly(1), getRequestedFor(urlEqualTo("/pet/10")));
    }

    /**
     * Update an existing pet
     *
     * @throws Exception if the Api call fails
     */
    @Test
    public void updatePetTest() throws Exception {
        Pet body = new Pet()
                .id(10L)
                .name("doggie")
                .status(Pet.StatusEnum.AVAILABLE)
                .photoUrls(Arrays.asList("http://some.pic"));

        api.updatePet(body);

        verify(exactly(1), putRequestedFor(urlEqualTo("/pet")));
    }
}
