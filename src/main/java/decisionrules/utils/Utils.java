package decisionrules.utils;

import java.net.URI;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import decisionrules.DecisionRulesEnums.HostEnum;
import decisionrules.exceptions.DecisionRulesErrorException;

public class Utils {
    static ObjectMapper mapper = new ObjectMapper();

    public static String getBaseURL(String host) {
        for (HostEnum hostEnum : HostEnum.values()) {
            if (hostEnum.value.equalsIgnoreCase(host)) {
                switch (hostEnum) {
                    case GLOBAL_CLOUD:
                        return "https://api.decisionrules.io";
                    case REGION_EU:
                        return "https://eu.api.decisionrules.io";
                    case REGION_US:
                        return "https://us.api.decisionrules.io";
                    case REGION_AU:
                        return "https://au.api.decisionrules.io";
                    default:
                        return "https://api.decisionrules.io";
                }
            }
        }
        return host;
    }

    public static HttpHeaders createHeaders(String key) {
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

    public static MappingJackson2HttpMessageConverter createConverter() {
        final ObjectMapper objectMapper = new ObjectMapper();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        return converter;
    }

    public static DecisionRulesErrorException handleError(Exception e) {
        if (e.getMessage() != null && !e.getMessage().isEmpty()) {
            throw new DecisionRulesErrorException(e.getMessage(), Arrays.stream(e.getStackTrace())
                    .map(StackTraceElement::toString).reduce("", (a, b) -> a + "\n" + b));
        }
        throw new DecisionRulesErrorException(String.format("Call ended with status:%s", e.getMessage()),
                Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).reduce("", (a, b) -> a + "\n" + b));
    }

    public static String doCall(RestTemplate restTemplate, URI url, HttpHeaders headers, HttpMethod method) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(
                    headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    url, method, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String doCall(RestTemplate restTemplate, URI url, HttpHeaders headers, HttpMethod method,
            Object body) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(
                    mapper.writeValueAsString(body),
                    headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    url, method, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
