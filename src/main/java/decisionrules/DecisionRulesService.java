package decisionrules;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import decisionrules.DecisionRulesEnums.RuleStatus;
import decisionrules.api.JobApi;
import decisionrules.api.ManagementApi;
import decisionrules.api.SolveApi;
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

public class DecisionRulesService {

    public final Management management = new Management();
    public final Job job = new Job();

    private final RestTemplate restTemplate;
    private final DecisionRulesOptions options;
    private final ObjectMapper mapper = new ObjectMapper();
    private final SolveApi solveApi;
    private final ManagementApi managementApi;
    private final JobApi jobApi;

    public DecisionRulesService(DecisionRulesOptions options) {
        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
        this.restTemplate.getMessageConverters().add(Utils.createConverter());
        this.options = options;
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.solveApi = new SolveApi(this.restTemplate, this.options, this.mapper);
        this.managementApi = new ManagementApi(this.restTemplate, this.mapper);
        this.jobApi = new JobApi(this.restTemplate, this.mapper);
    }

    /**
     * @param ruleIdOrAlias
     * @param input         - can be any object, it will be serialized to JSON, if
     *                      string is passed it will be used as is
     * @return JSON string response from the API - further processing is up to the
     *         user
     */
    public String solve(final String ruleIdOrAlias, final Object input) {
        return solveApi.solveAPI(ruleIdOrAlias, input, "");
    }

    public class Management {

        public Rule getRule(String ruleIdOrAlias, Integer version) {
            try {
                return managementApi.getRuleAPI(options, ruleIdOrAlias, version, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule getRule(String ruleIdOrAlias) {
            try {
                return managementApi.getRuleAPI(options, ruleIdOrAlias, null, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule getRuleByPath(String path, Integer version) {
            try {
                return managementApi.getRuleAPI(options, "", version, new RuleOptions(path, version));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule getRuleByPath(String path) {
            try {
                return managementApi.getRuleAPI(options, "", null, new RuleOptions(path, null));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule updateRuleStatus(String ruleIdOrAlias, RuleStatus status, Integer version) {
            try {
                return managementApi.updateRuleStatusAPI(options, ruleIdOrAlias, status, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule updateRule(String ruleIdOrAlias, Rule rule, Integer version) {
            try {
                return managementApi.updateRuleAPI(options, ruleIdOrAlias, rule, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule updateRule(String ruleIdOrAlias, Rule rule) {
            try {
                return managementApi.updateRuleAPI(options, ruleIdOrAlias, rule, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule createRule(Rule rule, String path) {
            try {
                return managementApi.createRuleAPI(options, rule, new RuleOptions(path, null));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule createRule(Rule rule) {
            try {
                return createRule(rule, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule createNewRuleVersion(String ruleIdOrAlias, Rule rule) {
            try {
                return managementApi.createNewRuleVersionAPI(options, ruleIdOrAlias, rule);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteRule(String ruleIdOrAlias, Integer version) {
            try {
                managementApi.deleteRuleAPI(options, ruleIdOrAlias, version, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteRule(String ruleIdOrAlias) {
            try {
                deleteRule(ruleIdOrAlias, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteRuleByPath(String path, Integer version) {
            try {
                managementApi.deleteRuleAPI(options, "", null, new RuleOptions(path, version));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteRuleByPath(String path) {
            try {
                deleteRuleByPath(path, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void lockRule(String ruleIdOrAlias, boolean lock, Integer version) {
            try {
                managementApi.lockRuleAPI(options, ruleIdOrAlias, lock, version, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void lockRule(String ruleIdOrAlias, boolean lock) {
            try {
                managementApi.lockRuleAPI(options, ruleIdOrAlias, lock, null, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void lockRuleByPath(String path, boolean lock, Integer version) {
            try {
                managementApi.lockRuleAPI(options, "", lock, version, new RuleOptions(path, version));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void lockRuleByPath(String path, boolean lock) {
            try {
                managementApi.lockRuleAPI(options, "", lock, null, new RuleOptions(path, null));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Duplicates findDuplicates(String ruleIdOrAlias, Integer version) {
            try {
                return managementApi.findDuplicatesAPI(options, ruleIdOrAlias, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Duplicates findDuplicates(String ruleIdOrAlias) {
            try {
                return managementApi.findDuplicatesAPI(options, ruleIdOrAlias, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Dependencies findDependencies(String ruleIdOrAlias, Integer version) {
            try {
                return managementApi.findDependenciesAPI(options, ruleIdOrAlias, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Dependencies findDependencies(String ruleIdOrAlias) {
            try {
                return managementApi.findDependenciesAPI(options, ruleIdOrAlias, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule[] getRulesForSpace() {
            try {
                return managementApi.getRulesForSpaceAPI(options);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule[] getRulesByTags(String tags[]) {
            try {
                return managementApi.getRulesByTagsAPI(options, tags);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public String[] updateTags(String ruleIdOrAlias, String[] tags, Integer version) {
            try {
                return managementApi.addTagsAPI(options, ruleIdOrAlias, tags, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public String[] updateTags(String ruleIdOrAlias, String[] tags) {
            try {
                return managementApi.addTagsAPI(options, ruleIdOrAlias, tags, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteTags(String ruleIdOrAlias, String[] tags, Integer version) {
            try {
                managementApi.deleteTagsAPI(options, ruleIdOrAlias, tags, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteTags(String ruleIdOrAlias, String[] tags) {
            try {
                managementApi.deleteTagsAPI(options, ruleIdOrAlias, tags, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void createFolder(String targetNodeid, FolderData data) {
            try {
                managementApi.createFolderAPI(options, targetNodeid, data, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void createFolderByPath(String path, FolderData data) {
            try {
                managementApi.createFolderAPI(options, "", data, new FolderOptions(path));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderData updateNodeFolderStructure(String targetNodeid, FolderData data) {
            try {
                return managementApi.updateNodeFolderStructureAPI(options, targetNodeid, data, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderData updateNodeFolderStructureByPath(String path, FolderData data) {
            try {
                return managementApi.updateNodeFolderStructureAPI(options, "", data, new FolderOptions(path));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderExport exportFolder(String nodeId) {
            try {
                return managementApi.exportFolderAPI(options, nodeId, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderExport exportFolderByPath(String path) {
            try {
                return managementApi.exportFolderAPI(options, "", new FolderOptions(path));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderImport importFolder(String targetNodeid, Object data) {
            try {
                return managementApi.importFolderAPI(options, targetNodeid, data, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderImport importFolderToPath(String path, Object data) {
            try {
                return managementApi.importFolderAPI(options, "", data, new FolderOptions(path));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderData getFolderStructure(String targetNodeid) {
            try {
                return managementApi.getFolderStructureAPI(options, targetNodeid, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderData getFolderStructure() {
            try {
                return managementApi.getFolderStructureAPI(options, "", null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public FolderData getFolderStructureByPath(String path) {
            try {
                return managementApi.getFolderStructureAPI(options, null, new FolderOptions(path));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteFolder(String targetNodeid, boolean deleteAll) {
            try {
                managementApi.deleteFolderAPI(options, targetNodeid, deleteAll, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void deleteFolderByPath(String path, boolean deleteAll) {
            try {
                managementApi.deleteFolderAPI(options, "", deleteAll, new FolderOptions(path));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void renameFolder(String targetNodeid, String newName) {
            try {
                managementApi.renameFolderAPI(options, targetNodeid, newName, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void renameFolderByPath(String path, String newName) {
            try {
                managementApi.renameFolderAPI(options, "", newName, new FolderOptions(path));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public void moveFolder(String targetId, FolderNode[] nodes, String targetPath) {
            try {
                managementApi.moveFolderAPI(options, targetId, nodes, targetPath);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        /**
         * @param data
         * @return JSON string response from the API of a Rule or FolderData - further
         *         processing is up to the
         *         user
         */
        public String findFolderOrRuleByAttribute(FindOptions data) {
            try {
                return managementApi.findFolderOrRuleByAttributeAPI(options, data);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }
    }

    public class Job {
        public decisionrules.model.Job start(String ruleIdOrAlias, Object inputData, Integer version) {
            try {
                return jobApi.startJobAPI(options, ruleIdOrAlias, inputData, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public decisionrules.model.Job start(String ruleIdOrAlias, Object inputData) {
            try {
                return jobApi.startJobAPI(options, ruleIdOrAlias, inputData, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public decisionrules.model.Job cancel(String jobId) {
            try {
                return jobApi.cancelJobAPI(options, jobId);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public decisionrules.model.Job info(String jobId) {
            try {
                return jobApi.jobInfoAPI(options, jobId);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }
    }

    public boolean validateWebhookSignature(String payload, String signature, String secret) {
        try {
            // Create HMAC-SHA256 key
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmac.init(secretKey);

            // Compute HMAC digest
            byte[] hash = hmac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String expectedSignature = bytesToHex(hash);

            // Timing-safe comparison
            return MessageDigest.isEqual(signature.getBytes(StandardCharsets.UTF_8),
                    expectedSignature.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw Utils.handleError(e);
        }
    }

    // Helper to convert byte array to hex string
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}