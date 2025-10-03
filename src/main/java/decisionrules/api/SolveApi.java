package decisionrules.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import decisionrules.DecisionRulesOptions;
import decisionrules.model.SolverOptions;
import static decisionrules.utils.Utils.getBaseURL;

public class SolveApi {
    private final RestTemplate restTemplate;
    private final DecisionRulesOptions options; // change to private
    private final ObjectMapper mapper;

    public SolveApi(RestTemplate restTemplate, DecisionRulesOptions options, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.options = options;
        this.mapper = mapper;
    }

    public HttpHeaders createHeaders(String key, SolverOptions solverOptions) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (key.isEmpty()) {
                throw new IllegalArgumentException("Solver key is not set.");
            }
            headers.set("Authorization", "Bearer " + key);

            return headers;
        } catch (Exception e) {
            throw e;
        }
    }

    private String createUrl(DecisionRulesOptions options, String ruleId, String version) throws Exception {
        String url = String.format("/rule/solve/%s", ruleId);
        if (version != null) {
            url += version;
        }
        try {
            return getBaseURL(options.host) + url;
        } catch (Exception e) {
            throw e;
        }
    }

    public String solveAPI(final String ruleId, final Object data, final String version) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(
                    String.format("{ \"data\":  %s}", mapper.writeValueAsString(data)),
                    createHeaders(
                            options.solverKey,
                            new SolverOptions(
                                    false,
                                    null,
                                    false,
                                    null)));
            ResponseEntity<String> response = restTemplate.exchange(
                    java.net.URI.create(createUrl(options, ruleId, version)), HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
