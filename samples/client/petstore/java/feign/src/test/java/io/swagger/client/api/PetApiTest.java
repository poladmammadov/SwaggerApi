package io.swagger.client.api;

import io.swagger.TestUtils;

import io.swagger.client.ApiClient;
import io.swagger.client.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class PetApiTest {
    ApiClient apiClient;
    PetApi api;
    MockWebServer localServer;
    ApiClient localClient;

    @Before
    public void setup() {
        apiClient = new ApiClient();
        apiClient.setBasePath("http://petstore.swagger.io:80/v2");
        api = apiClient.buildClient(PetApi.class);
        localServer = new MockWebServer();
        localClient = new ApiClient();
    }

    @Test
    public void testApiClient() {
        // the default api client is used
        assertEquals("http://petstore.swagger.io:80/v2", apiClient.getBasePath());

        ApiClient newClient = new ApiClient();
        newClient.setBasePath("http://example.com");

        assertEquals("http://example.com", newClient.getBasePath());
    }

    @Test
    public void testCreateAndGetPet() throws Exception {
        Pet pet = createRandomPet();
        api.addPet(pet);
        // todo: replace remote calls with a mock server
//        Pet fetched = api.getPetById(pet.getId());
//        assertNotNull(fetched);
//        assertEquals(pet.getId(), fetched.getId());
//        assertNotNull(fetched.getCategory());
//        assertEquals(fetched.getCategory().getName(), pet.getCategory().getName());
    }

    @Test
    public void testUpdatePet() throws Exception {
        Pet pet = createRandomPet();
        pet.setName("programmer");

        api.updatePet(pet);

        // todo: replace remote calls with mock server
//        Pet fetched = api.getPetById(pet.getId());
//        assertNotNull(fetched);
//        assertEquals(pet.getId(), fetched.getId());
//        assertNotNull(fetched.getCategory());
//        assertEquals(fetched.getCategory().getName(), pet.getCategory().getName());
    }

    @Test
    public void testFindPetsByStatus() throws Exception {
        Pet pet = createRandomPet();
        pet.setName("programmer");
        pet.setStatus(Pet.StatusEnum.AVAILABLE);

        api.updatePet(pet);

        // todo: replace remote calls with mock server
//        List<Pet> pets = api.findPetsByStatus(Collections.singletonList("available"));
//        assertNotNull(pets);
//
//        boolean found = false;
//        for (Pet fetched : pets) {
//            if (fetched.getId().equals(pet.getId())) {
//                found = true;
//                break;
//            }
//        }
//
//        assertTrue(found);
//
//        PetApi.FindPetsByStatusQueryParams queryParams = new PetApi.FindPetsByStatusQueryParams()
//                .status(Arrays.asList(new String[]{"available"}));
//        pets = api.findPetsByStatus(queryParams);
//        assertNotNull(pets);
//
//        found = false;
//        for (Pet fetched : pets) {
//            if (fetched.getId().equals(pet.getId())) {
//                found = true;
//                break;
//            }
//        }
//
//        assertTrue(found);
    }

    @Test
    public void testFindPetsByTags() throws Exception {
        Pet pet = createRandomPet();
        pet.setName("monster");
        pet.setStatus(Pet.StatusEnum.AVAILABLE);

        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setName("friendly");
        tags.add(tag1);
        pet.setTags(tags);

        api.updatePet(pet);

        // todo: replace remote calls with mock server
//        List<Pet> pets = api.findPetsByTags(Collections.singletonList("friendly"));
//        assertNotNull(pets);
//
//        boolean found = false;
//        for (Pet fetched : pets) {
//            if (fetched.getId().equals(pet.getId())) {
//                found = true;
//                break;
//            }
//        }
//        assertTrue(found);

        // todo: replace remote calls with mock server

//        PetApi.FindPetsByTagsQueryParams queryParams = new PetApi.FindPetsByTagsQueryParams()
//                .tags(Arrays.asList(new String[]{"friendly"}));
//        pets = api.findPetsByTags(queryParams);
//        assertNotNull(pets);
//
//        found = false;
//        for (Pet fetched : pets) {
//            if (fetched.getId().equals(pet.getId())) {
//                found = true;
//                break;
//            }
//        }
//        assertTrue(found);
    }

    @Test
    public void testUpdatePetWithForm() throws Exception {
        Pet pet = createRandomPet();
        pet.setName("frank");
        api.addPet(pet);

        // todo: replace remote calls with mock server
//        Pet fetched = api.getPetById(pet.getId());
//
//        api.updatePetWithForm(fetched.getId(), "furt", null);
//        Pet updated = api.getPetById(fetched.getId());
//
//        assertEquals(updated.getName(), "furt");
    }

    @Test
    public void testDeletePet() throws Exception {
        Pet pet = createRandomPet();
        api.addPet(pet);

        // todo: replace remote calls with mock server
//        Pet fetched = api.getPetById(pet.getId());
//        api.deletePet(fetched.getId(), null);
//
//        try {
//            api.getPetById(fetched.getId());
//            fail("expected an error");
//        } catch (Exception e) {
////            assertEquals(404, e.getCode());
//        }
    }

    @Test
    public void testUploadFile() throws Exception {
        Pet pet = createRandomPet();
        api.addPet(pet);

        File file = new File("hello.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Hello world!");
        writer.close();

        api.uploadFile(pet.getId(), "a test file", new File(file.getAbsolutePath()));
    }

    @Test
    public void testEqualsAndHashCode() {
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        assertTrue(pet1.equals(pet2));
        assertTrue(pet2.equals(pet1));
        assertTrue(pet1.hashCode() == pet2.hashCode());
        assertTrue(pet1.equals(pet1));
        assertTrue(pet1.hashCode() == pet1.hashCode());

        pet2.setName("really-happy");
        pet2.setPhotoUrls(Arrays.asList("http://foo.bar.com/1", "http://foo.bar.com/2"));
        assertFalse(pet1.equals(pet2));
        assertFalse(pet2.equals(pet1));
        assertFalse(pet1.hashCode() == (pet2.hashCode()));
        assertTrue(pet2.equals(pet2));
        assertTrue(pet2.hashCode() == pet2.hashCode());

        pet1.setName("really-happy");
        pet1.setPhotoUrls(Arrays.asList("http://foo.bar.com/1", "http://foo.bar.com/2"));
        assertTrue(pet1.equals(pet2));
        assertTrue(pet2.equals(pet1));
        assertTrue(pet1.hashCode() == pet2.hashCode());
        assertTrue(pet1.equals(pet1));
        assertTrue(pet1.hashCode() == pet1.hashCode());
    }

    @Test
    public void testCSVDelimitedArray() throws Exception {
        localServer.enqueue(new MockResponse().setBody("[{\"id\":5,\"name\":\"rocky\"}]"));
        localServer.start();
        PetApi api = localClient.setBasePath(localServer.url("/").toString()).buildClient(PetApi.class);
        PetApi.FindPetsByTagsQueryParams queryParams = new PetApi.FindPetsByTagsQueryParams()
                .tags(Arrays.asList("friendly","energetic"));
        List<Pet> pets = api.findPetsByTags(queryParams);
        assertNotNull(pets);
        RecordedRequest request = localServer.takeRequest();
        assertThat(request.getPath()).contains("tags=friendly,energetic");
        localServer.shutdown();
    }

    private Pet createRandomPet() {
        Pet pet = new Pet();
        pet.setId(TestUtils.nextId());
        pet.setName("gorilla");

        Category category = new Category();
        category.setName("really-happy");

        pet.setCategory(category);
        pet.setStatus(Pet.StatusEnum.AVAILABLE);
        List<String> photos = Arrays.asList("http://foo.bar.com/1", "http://foo.bar.com/2");
        pet.setPhotoUrls(photos);

        return pet;
    }
}
