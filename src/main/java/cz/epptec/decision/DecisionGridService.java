package cz.epptec.decision;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.epptec.decision.model.ExampleRuleInput;
import cz.epptec.decision.model.ExampleRuleOutput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DecisionGridService {

    private String connectionUrl;
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public DecisionGridService(final String connectionUrl, final String bearerToken) {
        this.connectionUrl = connectionUrl;
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(createConverter());
        this.headers = createHttpHeaders(bearerToken);
    }

    public String solveRule(final String ruleId, final String version, final String paramsAsJsonInString) {
        HttpEntity<String> entity = new HttpEntity<String>(paramsAsJsonInString, headers);
        ResponseEntity<String> response = restTemplate.exchange(formatUrl(connectionUrl, ruleId, version), HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    public List<ExampleRuleOutput> solveRuleWithModel(final String ruleId, final String version, final ExampleRuleInput input) {
        HttpEntity<ExampleRuleInput> entity = new HttpEntity<ExampleRuleInput>(input, headers);
        final List<ExampleRuleOutput> output = restTemplate.postForObject(formatUrl(connectionUrl, ruleId, version), entity, List.class);
        return output;
    }



    private HttpHeaders createHttpHeaders(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearerToken);
        return headers;
    }

    private String formatUrl(final String baseUrl, final String ruleId, final String versionId) {
        return baseUrl + "/" + ruleId + "/" + versionId;
    }

    private MappingJackson2HttpMessageConverter createConverter() {
        final ObjectMapper objectMapper = new ObjectMapper();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        return converter;
    }
}
