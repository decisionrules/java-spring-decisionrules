package cz.epptec.decision.api;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static cz.epptec.decision.utils.Utils.getBaseURL;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;

import cz.epptec.decision.DecisionRulesEnums.MngCategoryEnum;
import cz.epptec.decision.DecisionRulesEnums.RuleStatus;
import cz.epptec.decision.DecisionRulesOptions;
import cz.epptec.decision.model.FolderNode;
import cz.epptec.decision.model.FolderOptions;
import cz.epptec.decision.model.Rule;
import cz.epptec.decision.model.RuleOptions;
import cz.epptec.decision.utils.Utils;

public class ManagementApi {

    private final RestTemplate restTemplate;
    ObjectMapper mapper = new ObjectMapper();

    public ManagementApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public URI getCategoryUrl(String host, MngCategoryEnum category,
            String[] apiPath,
            Map<String, String> queryParams) throws Exception {
        try {
            String baseUrl = getBaseURL(host);
            String path = String.format("/api/%s/%s", category.value, String.join("/",
                    Arrays.stream(apiPath).filter(pathParam -> !pathParam.isEmpty()).toArray(String[]::new)));

            String queryString = queryParams.entrySet().stream()
                    .filter(entry -> entry.getValue() != null)
                    .map(entry -> entry.getKey() + "="
                            + String.valueOf(entry.getValue()))
                    .collect(Collectors.joining("&"));
            if (!queryString.isEmpty()) {
                path += UriUtils.encodeQuery("?" + queryString, StandardCharsets.UTF_8);
            }

            return URI.create(baseUrl + path);
        } catch (Exception e) {
            throw e;
        }
    }

    public URI getCategoryUrl(String host, MngCategoryEnum category,
            String[] apiPath,
            String[] queryParams) throws Exception {
        try {
            String baseUrl = getBaseURL(host);
            String path = String.format("/api/%s/%s", category.value, String.join("/",
                    Arrays.stream(apiPath).filter(pathParam -> !pathParam.isEmpty()).toArray(String[]::new)));
            if (category == MngCategoryEnum.TAGS) {
                if (queryParams.length > 0) {
                    path += UriUtils.encodeQuery("/?tags=" + Arrays.stream(queryParams)
                            .map(tag -> tag.trim())
                            .collect(Collectors.joining(",")), StandardCharsets.UTF_8);
                }
                return URI.create(baseUrl + path);
            } else {
                throw new Exception("This method can be used only for TAGS category.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public URI getCategoryUrl(String host, MngCategoryEnum category, String[] apiPath) throws Exception {
        try {
            String baseUrl = getBaseURL(host);
            String path = String.format("/api/%s/%s", category.value, String.join("/",
                    Arrays.stream(apiPath).filter(pathParam -> !pathParam.isEmpty()).toArray(String[]::new)));
            return URI.create(baseUrl + path);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Rule getRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Number version, RuleOptions ruleOptions)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, ruleOptions);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { ruleIdOrAlias, versionString },
                    createDataMap(ruleOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, Rule.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Rule updateRuleStatusAPI(DecisionRulesOptions options, String ruleIdOrAlias, RuleStatus status,
            Number version) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { "status", ruleIdOrAlias, status.value,
                            (version == null) ? "" : version.toString() });
            System.err.println("URL: " + url);
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PUT);
            return mapper.readValue(response, Rule.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Rule updateRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Rule data, Number version)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { ruleIdOrAlias, (version == null) ? "" : version.toString() });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PUT, data);
            return mapper.readValue(response, Rule.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Rule createRuleAPI(DecisionRulesOptions options, Object data, RuleOptions ruleOptions) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] {},
                    createDataMap(ruleOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST, data);
            return mapper.readValue(response, Rule.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Rule createNewRuleVersionAPI(DecisionRulesOptions options, String ruleIdOrAlias, Object data)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { ruleIdOrAlias, "new-version" });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST, data);
            return mapper.readValue(response, Rule.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Number version,
            RuleOptions ruleOptions) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { ruleIdOrAlias, (version == null) ? "" : version.toString() },
                    createDataMap(ruleOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.DELETE);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String lockRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Boolean locked, Number version,
            RuleOptions ruleOptions) throws Exception {
        try {
            String versionString = getRuleVersion(version, ruleOptions);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { "lock", ruleIdOrAlias, versionString }, mapper.convertValue(ruleOptions, Map.class));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PATCH, Map.of("locked", locked));
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String findDuplicatesAPI(DecisionRulesOptions options, String ruleIdOrAlias, Number version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TOOLS,
                    new String[] { "duplicates", ruleIdOrAlias, versionString });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String findDependenciesAPI(DecisionRulesOptions options, String ruleIdOrAlias, Number version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TOOLS,
                    new String[] { "dependencies", ruleIdOrAlias, versionString });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String getRulesForSpaceAPI(DecisionRulesOptions options)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.SPACE, new String[] { "items" });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String getRulesByTagsAPI(DecisionRulesOptions options, String[] tags) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TAGS, new String[] { "items" }, tags);
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String addTagsAPI(DecisionRulesOptions options, String ruleIdOrAlias, String[] tags, Number version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { ruleIdOrAlias, versionString });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PATCH, tags);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteTagsAPI(DecisionRulesOptions options, String ruleIdOrAlias, String[] tags, Number version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { ruleIdOrAlias, versionString },
                    tags);
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.DELETE);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String createFolderAPI(DecisionRulesOptions options, String targetNodeId, Object data,
            FolderOptions folderOptions) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { targetNodeId },
                    mapper.convertValue(folderOptions, Map.class));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST, data);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String updateNodeFolderStructureAPI(DecisionRulesOptions options, String targetNodeId, Object data,
            FolderOptions folderOptions)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { targetNodeId },
                    mapper.convertValue(folderOptions, Map.class));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PUT, data);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String exportFolderAPI(DecisionRulesOptions options, String targetNodeId, FolderOptions folderOptions)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { "export", targetNodeId },
                    mapper.convertValue(folderOptions, Map.class));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String importFolderAPI(DecisionRulesOptions options, String targetNodeId, Object data,
            FolderOptions folderOptions) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { "import", targetNodeId },
                    mapper.convertValue(folderOptions, Map.class));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST, data);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String getNodeFolderStructureAPI(DecisionRulesOptions options, String targetNodeId,
            FolderOptions folderOptions)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { targetNodeId },
                    mapper.convertValue(folderOptions, Map.class));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteFolderAPI(DecisionRulesOptions options, String targetNodeId, Boolean deleteAll,
            FolderOptions folderOptions) throws Exception {
        try {
            var folderOptionsMap = mapper.convertValue(folderOptions, Map.class);
            folderOptionsMap.put("deleteAll", deleteAll.toString());
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE, new String[] { targetNodeId },
                    folderOptionsMap);
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.DELETE);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String renameFolderAPI(DecisionRulesOptions options, String targetNodeId, String name,
            FolderOptions folderOptions) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { "rename", targetNodeId },
                    mapper.convertValue(folderOptions, Map.class));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PATCH, Map.of("name", name));
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String moveFolderAPI(DecisionRulesOptions options, String targetNodeId, FolderNode[] nodes,
            String targetPath) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { "move" });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PUT,
                    targetPath != null ? Map.of("nodes", nodes, "targetPath", targetPath)
                            : Map.of("nodes", nodes, "targetPath", targetNodeId));
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String findFolderOrRuleByAttributeAPI(DecisionRulesOptions options, String ruleIdOrAlias, Number version)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { "find" });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    String getRuleVersion(Number version, RuleOptions ruleOptions) {
        if (ruleOptions != null && ruleOptions.version != null) {
            return "";
        }
        return version != null ? (version == null) ? "" : version.toString() : "";
    }

    Map<String, String> createDataMap(Object data) {
        if (data != null) {
            return mapper.convertValue(data, Map.class);
        } else {
            return Collections.emptyMap();
        }
    }
}
