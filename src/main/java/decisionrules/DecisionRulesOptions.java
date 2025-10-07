package decisionrules;

import decisionrules.DecisionRulesEnums.HostEnum;

public class DecisionRulesOptions {
    public String host;
    public String solverKey;
    public String managementKey;

    public DecisionRulesOptions(
            String baseUrl,
            String solverKey,
            String managementKey) {
        this.host = baseUrl;
        this.solverKey = solverKey;
        this.managementKey = managementKey;
    }

    public DecisionRulesOptions(
            HostEnum baseUrl,
            String solverKey,
            String managementKey) {
        this.host = baseUrl.value;
        this.solverKey = solverKey;
        this.managementKey = managementKey;
    }
}