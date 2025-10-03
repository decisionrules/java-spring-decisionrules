package decisionrules.api;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import decisionrules.DecisionRulesEnums.MngCategoryEnum;
import decisionrules.DecisionRulesEnums.RuleStatus;
import decisionrules.DecisionRulesOptions;
import decisionrules.model.Dependencies;
import decisionrules.model.Duplicates;
import decisionrules.model.FindOptions;
import decisionrules.model.FolderData;
import decisionrules.model.FolderExport;
import decisionrules.model.FolderImport;
import decisionrules.model.FolderNode;
import decisionrules.model.FolderOptions;
import decisionrules.model.Rule;
import decisionrules.model.RuleOptions;
import decisionrules.utils.Utils;
import static decisionrules.utils.Utils.getBaseURL;

public class ManagementApi {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public ManagementApi(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
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
                    Arrays.stream(apiPath).filter(pathParam -> pathParam != null && !pathParam.isEmpty())
                            .toArray(String[]::new)));
            return URI.create(baseUrl + path);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Rule getRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Integer version, RuleOptions ruleOptions)
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
            Integer version) throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { "status", ruleIdOrAlias, status.value,
                            versionString });
            System.err.println("URL: " + url);
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PUT);
            return mapper.readValue(response, Rule.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Rule updateRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Rule data, Integer version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { ruleIdOrAlias, versionString });
            System.err.println("URL: " + url);
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

    public Rule createNewRuleVersionAPI(DecisionRulesOptions options, String ruleIdOrAlias, Rule data)
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

    public String deleteRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Integer version,
            RuleOptions ruleOptions) throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { ruleIdOrAlias, versionString },
                    createDataMap(ruleOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.DELETE);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String lockRuleAPI(DecisionRulesOptions options, String ruleIdOrAlias, Boolean locked, Integer version,
            RuleOptions ruleOptions) throws Exception {
        try {
            String versionString = getRuleVersion(version, ruleOptions);
            System.err.println("Version string: " + versionString);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.RULE,
                    new String[] { "lock", ruleIdOrAlias, versionString }, createDataMap(ruleOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PATCH, Map.of("locked", locked));
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public Duplicates findDuplicatesAPI(DecisionRulesOptions options, String ruleIdOrAlias, Integer version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TOOLS,
                    new String[] { "duplicates", ruleIdOrAlias, versionString });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, Duplicates.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Dependencies findDependenciesAPI(DecisionRulesOptions options, String ruleIdOrAlias, Integer version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TOOLS,
                    new String[] { "dependencies", ruleIdOrAlias, versionString });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, Dependencies.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Rule[] getRulesForSpaceAPI(DecisionRulesOptions options)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.SPACE, new String[] { "items" });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, Rule[].class);
        } catch (Exception e) {
            throw e;
        }
    }

    public Rule[] getRulesByTagsAPI(DecisionRulesOptions options, String[] tags) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TAGS, new String[] { "items" }, tags);
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, Rule[].class);
        } catch (Exception e) {
            throw e;
        }
    }

    public String[] addTagsAPI(DecisionRulesOptions options, String ruleIdOrAlias, String[] tags, Integer version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TAGS, new String[] { ruleIdOrAlias, versionString });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PATCH, tags);
            return mapper.readValue(response, String[].class);
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteTagsAPI(DecisionRulesOptions options, String ruleIdOrAlias, String[] tags, Integer version)
            throws Exception {
        try {
            String versionString = getRuleVersion(version, null);
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.TAGS, new String[] { ruleIdOrAlias, versionString },
                    tags);
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.DELETE);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public String createFolderAPI(DecisionRulesOptions options, String targetNodeId, FolderData data,
            FolderOptions folderOptions) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { targetNodeId },
                    createDataMap(folderOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST, data);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public FolderData updateNodeFolderStructureAPI(DecisionRulesOptions options, String targetNodeId, FolderData data,
            FolderOptions folderOptions)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { targetNodeId },
                    createDataMap(folderOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.PUT, data);
            return mapper.readValue(response, FolderData.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public FolderExport exportFolderAPI(DecisionRulesOptions options, String targetNodeId, FolderOptions folderOptions)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { "export", targetNodeId },
                    createDataMap(folderOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, FolderExport.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public FolderImport importFolderAPI(DecisionRulesOptions options, String targetNodeId, Object data,
            FolderOptions folderOptions) throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { "import", targetNodeId },
                    createDataMap(folderOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST, data);
            return mapper.readValue(response, FolderImport.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public FolderData getFolderStructureAPI(DecisionRulesOptions options, String targetNodeId,
            FolderOptions folderOptions)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { targetNodeId },
                    createDataMap(folderOptions));
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.GET);
            return mapper.readValue(response, FolderData.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteFolderAPI(DecisionRulesOptions options, String targetNodeId, Boolean deleteAll,
            FolderOptions folderOptions) throws Exception {
        try {
            Map<String, String> folderOptionsMap = new java.util.HashMap<>(createDataMap(folderOptions));
            folderOptionsMap.put("deleteAll", deleteAll.toString());
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { targetNodeId },
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
                    createDataMap(folderOptions));
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

    public String findFolderOrRuleByAttributeAPI(DecisionRulesOptions options, FindOptions findOptions)
            throws Exception {
        try {
            HttpHeaders headers = Utils.createHeaders(options.managementKey);
            URI url = getCategoryUrl(options.host, MngCategoryEnum.FOLDER, new String[] { "find" });
            String response = Utils.doCall(this.restTemplate, url, headers, HttpMethod.POST,
                    findOptions);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    String getRuleVersion(Integer version, RuleOptions ruleOptions) {
        if (ruleOptions != null && ruleOptions.version != null) {
            return "";
        }
        return version != null ? Integer.toString(version) : "";
    }

    @SuppressWarnings("unchecked")
    Map<String, String> createDataMap(Object data) {
        if (data != null) {
            return mapper.convertValue(data, Map.class);
        } else {
            return Collections.emptyMap();
        }
    }
}
