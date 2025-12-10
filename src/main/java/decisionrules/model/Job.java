package decisionrules.model;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// 1. Add this annotation to prevent crashes on future unknown fields
@JsonIgnoreProperties(ignoreUnknown = true) 
public class Job {
    public RuleReference ruleReference;
    public Map<String, Object> inputData;
    public String jobId;
    public Context context;
    public Status status;
    public String correlationId;
    public String createdAt;
    public String updatedAt;
    
    // 2. Add the missing output field
    // Using List<Object> allows it to catch multiple result rows from decision tables
    public List<Object> output; 

    // Nested classes
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RuleReference {
        public String baseId;
        public int version;
        public String type;

        public String getBaseId() { return baseId; }
        public void setBaseId(String baseId) { this.baseId = baseId; }
        public int getVersion() { return version; }
        public void setVersion(int version) { this.version = version; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Context {
        private String spaceId;
        private String billingUserId;
        private String executorUser; 
        private String usedApiKeyId;
        private String environmentGroup;
        private int priority;
        private int parallelLimit;

        public String getSpaceId() { return spaceId; }
        public void setSpaceId(String spaceId) { this.spaceId = spaceId; }
        public String getBillingUserId() { return billingUserId; }
        public void setBillingUserId(String billingUserId) { this.billingUserId = billingUserId; }
        public String getExecutorUser() { return executorUser; }
        public void setExecutorUser(String executorUser) { this.executorUser = executorUser; }
        public String getUsedApiKeyId() { return usedApiKeyId; }
        public void setUsedApiKeyId(String usedApiKeyId) { this.usedApiKeyId = usedApiKeyId; }
        public String getEnvironmentGroup() { return environmentGroup; }
        public void setEnvironmentGroup(String environmentGroup) { this.environmentGroup = environmentGroup; }
        public int getPriority() { return priority; }
        public void setPriority(int priority) { this.priority = priority; }
        public int getParallelLimit() { return parallelLimit; }
        public void setParallelLimit(int parallelLimit) { this.parallelLimit = parallelLimit; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        private String state;
        private String code;
        private String message;

        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    // --- Getters and Setters for Job ---

    // 3. Add Getter and Setter for Output
    public List<Object> getOutput() {
        return output;
    }

    public void setOutput(List<Object> output) {
        this.output = output;
    }

    public RuleReference getRuleReference() { return ruleReference; }
    public void setRuleReference(RuleReference ruleReference) { this.ruleReference = ruleReference; }
    public Map<String, Object> getInputData() { return inputData; }
    public void setInputData(Map<String, Object> inputData) { this.inputData = inputData; }
    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }
    public Context getContext() { return context; }
    public void setContext(Context context) { this.context = context; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getCorrelationId() { return correlationId; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}