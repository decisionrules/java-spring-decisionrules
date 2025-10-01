package cz.epptec.decision.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rule {
    private String name;
    private String description;
    private Object inputSchema;
    private Object outputSchema;
    private int version;
    private Date lastUpdate;
    private Date createdIn;
    private String status;
    private String baseId;
    private String ruleId;
    private String type;
    private List<String> tags;
    private Map<String, Object> auditLog;
    private String ruleAlias;
    private Boolean locked;
    private String ruleAliasInfo;
    private String sessionId;
    private Map<String, Object> decisionTable;
    private Object visualEditorData;
    private String compositionId;
    private Object dataTree;
    private List<Object> rules;
    private List<Object> nodes;
    private List<Object> userVariables;
    private String previousBaseId;
    private Object script;
    private List<String> selectedWebhookAliases;
    private Object workflowData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getInputSchema() {
        return inputSchema;
    }

    public void setInputSchema(Object inputSchema) {
        this.inputSchema = inputSchema;
    }

    public Object getOutputSchema() {
        return outputSchema;
    }

    public void setOutputSchema(Object outputSchema) {
        this.outputSchema = outputSchema;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(Date createdIn) {
        this.createdIn = createdIn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getRuleAlias() {
        return ruleAlias;
    }

    public void setRuleAlias(String ruleAlias) {
        this.ruleAlias = ruleAlias;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getRuleAliasInfo() {
        return ruleAliasInfo;
    }

    public void setRuleAliasInfo(String ruleAliasInfo) {
        this.ruleAliasInfo = ruleAliasInfo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<String, Object> getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(Map<String, Object> auditLog) {
        this.auditLog = auditLog;
    }

    public Map<String, Object> getDecisionTable() {
        return decisionTable;
    }

    public void setDecisionTable(Map<String, Object> decisionTable) {
        this.decisionTable = decisionTable;
    }

    public Object getVisualEditorData() {
        return visualEditorData;
    }

    public void setVisualEditorData(Object visualEditorData) {
        this.visualEditorData = visualEditorData;
    }

    public String getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(String compositionId) {
        this.compositionId = compositionId;
    }

    public Object getDataTree() {
        return dataTree;
    }

    public void setDataTree(Object dataTree) {
        this.dataTree = dataTree;
    }

    public List<Object> getRules() {
        return rules;
    }

    public void setRules(List<Object> rules) {
        this.rules = rules;
    }

    public List<Object> getNodes() {
        return nodes;
    }

    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }

    public List<Object> getUserVariables() {
        return userVariables;
    }

    public void setUserVariables(List<Object> userVariables) {
        this.userVariables = userVariables;
    }

    public String getPreviousBaseId() {
        return previousBaseId;
    }

    public void setPreviousBaseId(String previousBaseId) {
        this.previousBaseId = previousBaseId;
    }

    public Object getScript() {
        return script;
    }

    public void setScript(Object script) {
        this.script = script;
    }

    public List<String> getSelectedWebhookAliases() {
        return selectedWebhookAliases;
    }

    public void setSelectedWebhookAliases(List<String> selectedWebhookAliases) {
        this.selectedWebhookAliases = selectedWebhookAliases;
    }

    public Object getWorkflowData() {
        return workflowData;
    }

    public void setWorkflowData(Object workflowData) {
        this.workflowData = workflowData;
    }

}
