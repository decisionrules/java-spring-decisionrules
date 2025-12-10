package decisionrules.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rule {

    public String name;
    public String description;
    public Object inputSchema;
    public Object outputSchema;
    public int version;
    public Date lastUpdate;
    public Date createdIn;
    public String status;
    public String baseId;
    public String ruleId;
    public String type;
    public List<String> tags;
    public Map<String, Object> auditLog;
    public String ruleAlias;
    public Boolean locked;
    public Object ruleAliasInfo;
    public String sessionId;
    public Map<String, Object> decisionTable;
    public Object visualEditorData;
    public String compositionId;
    public Object dataTree;
    public List<Object> rules;
    public List<Object> nodes;
    public List<Object> userVariables;
    public String previousBaseId;
    public Object script;
    public List<String> selectedWebhookAliases;
    public Object workflowData;
    public List<Map<String, Object>> columns;
    public String primaryKeyColumn;
    private Object templateMetadata;
    private Object visualData;
    public Object data;

    // Added field found in JSON
    public List<Map<String, Object>> sourceData;

    public Rule() {
    }

    public Rule(String name, String description, Object inputSchema, Object outputSchema, int version, Date lastUpdate,
            Date createdIn, String status, String baseId, String ruleId, String type, List<String> tags,
            Map<String, Object> auditLog, String ruleAlias, Boolean locked, Object ruleAliasInfo, String sessionId,
            Map<String, Object> decisionTable, Object visualEditorData, String compositionId, Object dataTree,
            List<Object> rules, List<Object> nodes, List<Object> userVariables, String previousBaseId, Object script,
            List<String> selectedWebhookAliases, Object workflowData, List<Map<String, Object>> columns,
            String primaryKeyColumn, Object data, List<Map<String, Object>> sourceData, Object templateMetadata,
            Object visualData) {
        this.name = name;
        this.description = description;
        this.inputSchema = inputSchema;
        this.outputSchema = outputSchema;
        this.version = version;
        this.lastUpdate = lastUpdate;
        this.createdIn = createdIn;
        this.status = status;
        this.baseId = baseId;
        this.ruleId = ruleId;
        this.type = type;
        this.tags = tags;
        this.auditLog = auditLog;
        this.ruleAlias = ruleAlias;
        this.locked = locked;
        this.ruleAliasInfo = ruleAliasInfo;
        this.sessionId = sessionId;
        this.decisionTable = decisionTable;
        this.visualEditorData = visualEditorData;
        this.compositionId = compositionId;
        this.dataTree = dataTree;
        this.rules = rules;
        this.nodes = nodes;
        this.userVariables = userVariables;
        this.previousBaseId = previousBaseId;
        this.script = script;
        this.selectedWebhookAliases = selectedWebhookAliases;
        this.workflowData = workflowData;
        this.columns = columns;
        this.primaryKeyColumn = primaryKeyColumn;
        this.data = data;
        this.sourceData = sourceData;
        this.templateMetadata = templateMetadata;
        this.visualData = visualData;
    }

    // --- Getters and Setters ---

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

    public Map<String, Object> getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(Map<String, Object> auditLog) {
        this.auditLog = auditLog;
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

    public Object getRuleAliasInfo() {
        return ruleAliasInfo;
    }

    public void setRuleAliasInfo(Object ruleAliasInfo) {
        this.ruleAliasInfo = ruleAliasInfo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public List<Map<String, Object>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<String, Object>> columns) {
        this.columns = columns;
    }

    public String getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(String primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Map<String, Object>> getSourceData() {
        return sourceData;
    }

    public void setSourceData(List<Map<String, Object>> sourceData) {
        this.sourceData = sourceData;
    }

    // --- Builder Pattern ---

    public static class Builder {
        public String name;
        public String description;
        public Object inputSchema;
        public Object outputSchema;
        public int version;
        public Date lastUpdate;
        public Date createdIn;
        public String status;
        public String baseId;
        public String ruleId;
        public String type;
        public List<String> tags;
        public Map<String, Object> auditLog;
        public String ruleAlias;
        public Boolean locked;
        public Object ruleAliasInfo;
        public String sessionId;
        public Map<String, Object> decisionTable;
        public Object visualEditorData;
        public String compositionId;
        public Object dataTree;
        public List<Object> rules;
        public List<Object> nodes;
        public List<Object> userVariables;
        public String previousBaseId;
        public Object script;
        public List<String> selectedWebhookAliases;
        public Object workflowData;
        public List<Map<String, Object>> columns;
        public String primaryKeyColumn;
        public Object data;
        public List<Map<String, Object>> sourceData;
        public Object templateMetadata;
        public Object visualData;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setInputSchema(Object inputSchema) {
            this.inputSchema = inputSchema;
            return this;
        }

        public Builder setOutputSchema(Object outputSchema) {
            this.outputSchema = outputSchema;
            return this;
        }

        public Builder setVersion(int version) {
            this.version = version;
            return this;
        }

        public Builder setLastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public Builder setCreatedIn(Date createdIn) {
            this.createdIn = createdIn;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setBaseId(String baseId) {
            this.baseId = baseId;
            return this;
        }

        public Builder setRuleId(String ruleId) {
            this.ruleId = ruleId;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder setAuditLog(Map<String, Object> auditLog) {
            this.auditLog = auditLog;
            return this;
        }

        public Builder setRuleAlias(String ruleAlias) {
            this.ruleAlias = ruleAlias;
            return this;
        }

        public Builder setLocked(Boolean locked) {
            this.locked = locked;
            return this;
        }

        public Builder setRuleAliasInfo(Object ruleAliasInfo) {
            this.ruleAliasInfo = ruleAliasInfo;
            return this;
        }

        public Builder setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder setDecisionTable(Map<String, Object> decisionTable) {
            this.decisionTable = decisionTable;
            return this;
        }

        public Builder setVisualEditorData(Object visualEditorData) {
            this.visualEditorData = visualEditorData;
            return this;
        }

        public Builder setCompositionId(String compositionId) {
            this.compositionId = compositionId;
            return this;
        }

        public Builder setDataTree(Object dataTree) {
            this.dataTree = dataTree;
            return this;
        }

        public Builder setRules(List<Object> rules) {
            this.rules = rules;
            return this;
        }

        public Builder setNodes(List<Object> nodes) {
            this.nodes = nodes;
            return this;
        }

        public Builder setUserVariables(List<Object> userVariables) {
            this.userVariables = userVariables;
            return this;
        }

        public Builder setPreviousBaseId(String previousBaseId) {
            this.previousBaseId = previousBaseId;
            return this;
        }

        public Builder setScript(Object script) {
            this.script = script;
            return this;
        }

        public Builder setSelectedWebhookAliases(List<String> selectedWebhookAliases) {
            this.selectedWebhookAliases = selectedWebhookAliases;
            return this;
        }

        public Builder setWorkflowData(Object workflowData) {
            this.workflowData = workflowData;
            return this;
        }

        public Builder setColumns(List<Map<String, Object>> columns) {
            this.columns = columns;
            return this;
        }

        public Builder setPrimaryKeyColumn(String primaryKeyColumn) {
            this.primaryKeyColumn = primaryKeyColumn;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public Builder setSourceData(List<Map<String, Object>> sourceData) {
            this.sourceData = sourceData;
            return this;
        }

        public Builder setTemplateMetadata(Object templateMetadata) {
            this.templateMetadata = templateMetadata;
            return this;
        }

        public Builder setVisualData(Object visualData) {
            this.visualData = visualData;
            return this;
        }

        public Rule build() {
            return new Rule(name, description, inputSchema, outputSchema, version, lastUpdate, createdIn,
                    status, baseId, ruleId, type, tags, auditLog, ruleAlias, locked, ruleAliasInfo,
                    sessionId, decisionTable, visualEditorData, compositionId, dataTree, rules, nodes,
                    userVariables, previousBaseId, script, selectedWebhookAliases, workflowData,
                    columns, primaryKeyColumn, data, sourceData, templateMetadata, visualData);
        }
    }
}