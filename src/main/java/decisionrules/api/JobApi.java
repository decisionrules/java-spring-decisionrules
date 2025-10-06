package decisionrules.api;

import java.net.URI;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import decisionrules.DecisionRulesOptions;
import decisionrules.model.Job;
import decisionrules.utils.Utils;
import static decisionrules.utils.Utils.getBaseURL;

public class JobApi {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public JobApi(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    public URI getCategoryUrl(String host, String[] apiPath) throws Exception {
        try {
            String baseUrl = getBaseURL(host);
            String path = String.format("/job/%s", String.join("/",
                    Arrays.stream(apiPath).filter(pathParam -> pathParam != null && !pathParam.isEmpty())
                            .toArray(String[]::new)));
            return URI.create(baseUrl + path);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Job startJobAPI(DecisionRulesOptions options, String ruleIdOrAlias, Object inputData, Integer version)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.solverKey);
            URI url = getCategoryUrl(options.host,
                    new String[] { "start", ruleIdOrAlias, version != null ? Integer.toString(version) : "" });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST, inputData);
            return mapper.readValue(response, Job.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Job cancelJobAPI(DecisionRulesOptions options, String jobId) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.solverKey);
            URI url = getCategoryUrl(options.host, new String[] { "cancel", jobId });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST);
            return mapper.readValue(response, Job.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Job jobInfoAPI(DecisionRulesOptions options, String jobId) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.solverKey);
            URI url = getCategoryUrl(options.host, new String[] { jobId });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, Job.class);
        } catch (Exception e) {
            throw e;
        }
    }
}
