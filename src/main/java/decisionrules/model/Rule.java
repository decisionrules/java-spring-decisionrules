package decisionrules.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    public String ruleAliasInfo;
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

    public Rule() {
    }

    public Rule(String name, String description, Object inputSchema, Object outputSchema, int version, Date lastUpdate,
            Date createdIn, String status, String baseId, String ruleId, String type, List<String> tags,
            Map<String, Object> auditLog, String ruleAlias, Boolean locked, String ruleAliasInfo, String sessionId,
            Map<String, Object> decisionTable, Object visualEditorData, String compositionId, Object dataTree,
            List<Object> rules, List<Object> nodes, List<Object> userVariables, String previousBaseId, Object script,
            List<String> selectedWebhookAliases, Object workflowData) {
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
    }

    public static class Builder {
        private String name;
        private String description;
        private Object inputSchema;
        private Object outputSchema;
        private int version;
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

        public Builder setStatus(String status) {
            this.status = status;
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

        public Builder setVisualEditorData(Object visualEditorData) {
            this.visualEditorData = visualEditorData;
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

        public Rule build() {
            return new Rule(name, description, inputSchema, outputSchema, version,
                    new Date(), new Date(), status, baseId, ruleId, type, tags, auditLog,
                    ruleAlias, locked, ruleAliasInfo, sessionId, decisionTable,
                    visualEditorData, compositionId, dataTree, rules, nodes,
                    userVariables, previousBaseId, script, selectedWebhookAliases,
                    workflowData);
        }
    }
}
