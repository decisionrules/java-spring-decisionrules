package decisionrules.model;

import java.util.Map;

public class Job {
    private RuleReference ruleReference;
    private Map<String, Object> inputData;
    private String jobId;
    private Context context;
    private Status status;
    private String correlationId;
    private String createdAt;
    private String updatedAt;

    // Nested classes
    public static class RuleReference {
        private String baseId;
        private int version;
        private String type;

        // Getters and Setters
        public String getBaseId() {
            return baseId;
        }

        public void setBaseId(String baseId) {
            this.baseId = baseId;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Context {
        private String spaceId;
        private String billingUserId;
        private String executorUser; // nullable
        private String usedApiKeyId;
        private String environmentGroup;
        private int priority;
        private int parallelLimit;

        // Getters and Setters
        public String getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(String spaceId) {
            this.spaceId = spaceId;
        }

        public String getBillingUserId() {
            return billingUserId;
        }

        public void setBillingUserId(String billingUserId) {
            this.billingUserId = billingUserId;
        }

        public String getExecutorUser() {
            return executorUser;
        }

        public void setExecutorUser(String executorUser) {
            this.executorUser = executorUser;
        }

        public String getUsedApiKeyId() {
            return usedApiKeyId;
        }

        public void setUsedApiKeyId(String usedApiKeyId) {
            this.usedApiKeyId = usedApiKeyId;
        }

        public String getEnvironmentGroup() {
            return environmentGroup;
        }

        public void setEnvironmentGroup(String environmentGroup) {
            this.environmentGroup = environmentGroup;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getParallelLimit() {
            return parallelLimit;
        }

        public void setParallelLimit(int parallelLimit) {
            this.parallelLimit = parallelLimit;
        }
    }

    public static class Status {
        private String state;
        private String code;
        private String message;

        // Getters and Setters
        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Getters and Setters
    public RuleReference getRuleReference() {
        return ruleReference;
    }

    public void setRuleReference(RuleReference ruleReference) {
        this.ruleReference = ruleReference;
    }

    public Map<String, Object> getInputData() {
        return inputData;
    }

    public void setInputData(Map<String, Object> inputData) {
        this.inputData = inputData;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
