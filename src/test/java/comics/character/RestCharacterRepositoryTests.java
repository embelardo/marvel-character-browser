package comics.character;

import config.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class RestCharacterRepositoryTests {
    private RestCharacterRepository repository;
    private RestTemplate restTemplate = new RestTemplate();
    private Hasher hasher = new TestHasher();
    private MockRestServiceServer restService = MockRestServiceServer.bindTo(restTemplate).build();
    @Value("${urlFindCharacter}") private String urlFindCharacter;
    @Value("${urlGetCharacter}") private String urlGetCharacter;
    @Value("${publicKey}") private String publicKey;
    @Value("${privateKey}") private String privateKey;

    @Before
    public void setUp() throws Exception {
        repository = new RestCharacterRepository(restTemplate, hasher, urlFindCharacter, urlGetCharacter,
            publicKey, privateKey);
    }

    @Test
    public void findCharacterShouldProperlyConvertJsonResponse() throws Exception {
        String searchString = "spidey";
        UriTemplate uriTemplate = new UriTemplate(this.urlFindCharacter);
        String url = uriTemplate.expand(hasher.getTimeStamp(), publicKey, hasher.getHash(0L),
            searchString).toString();
        String searchResultsAsJson = getFile("comics/character/searchResultList.txt");

        restService.expect(once(), requestTo(url)).andExpect(method(HttpMethod.GET)).
            andRespond(withSuccess(searchResultsAsJson, MediaType.APPLICATION_JSON));
        List<Character> searchResults = repository.findCharacter(searchString);

        restService.verify();
        org.junit.Assert.assertNotNull("results should not be null", searchResults);
        org.junit.Assert.assertEquals("number of results should be 20", 20, searchResults.size());
        org.junit.Assert.assertEquals("first result should be Spider-dok (id=1010727)", 1010727,
            searchResults.get(0).getId());
    }

    // reads a file containing test data
    private String getFile(String filePath) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = (classLoader.getResourceAsStream(filePath));
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
