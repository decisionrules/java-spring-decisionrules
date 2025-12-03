
package decisionrules;

import decisionrules.DecisionRulesEnums.HostEnum;

/**
 * Configuration options for the DecisionRules service.
 */
public class DecisionRulesOptions {
    public String host;
    public String solverKey;
    public String managementKey;

    /**
     * Creates a new instance with a custom host URL.
     *
     * @param baseUrl The base URL of the DecisionRules API
     * @param solverKey Your solver API key (can be null)
     * @param managementKey Your management API key (can be null)
     */
    public DecisionRulesOptions(
            String baseUrl,
            String solverKey,
            String managementKey) {
        this.host = baseUrl;
        this.solverKey = solverKey;
        this.managementKey = managementKey;
    }

    /**
     * Creates a new instance with a predefined host region.
     *
     * @param baseUrl The predefined HostEnum region
     * @param solverKey Your solver API key (can be null)
     * @param managementKey Your management API key (can be null)
     */
    public DecisionRulesOptions(
            HostEnum baseUrl,
            String solverKey,
            String managementKey) {
        this.host = baseUrl.value;
        this.solverKey = solverKey;
        this.managementKey = managementKey;
    }

    /**
     * public constructor for builder pattern.
     */
    public DecisionRulesOptions(Builder builder) {
        this.host = builder.host;
        this.solverKey = builder.solverKey;
        this.managementKey = builder.managementKey;
    }

    /**
     * Creates a new builder for constructing DecisionRulesOptions.
     *
     * @return A new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for creating DecisionRulesOptions instances.
     */
    public static class Builder {
        public String host;
        public String solverKey;
        public String managementKey;

        /**
         * public constructor to enforce factory method usage.
         */
        public Builder() {
        }

        /**
         * Sets the host using a custom URL string.
         *
         * @param host The base URL of the DecisionRules API
         * @return This builder instance
         */
        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        /**
         * Sets the host using a predefined HostEnum region.
         *
         * @param host The predefined HostEnum region
         * @return This builder instance
         */
        public Builder withHost(HostEnum host) {
            this.host = host.value;
            return this;
        }

        /**
         * Sets the Solver API key.
         *
         * @param solverKey Your solver API key
         * @return This builder instance
         */
        public Builder withSolverKey(String solverKey) {
            this.solverKey = solverKey;
            return this;
        }

        /**
         * Sets the Management API key.
         *
         * @param managementKey Your management API key
         * @return This builder instance
         */
        public Builder withManagementKey(String managementKey) {
            this.managementKey = managementKey;
            return this;
        }

        /**
         * Builds and returns the DecisionRulesOptions instance.
         *
         * @return A new DecisionRulesOptions instance
         * @throws IllegalStateException if host is not set
         */
        public DecisionRulesOptions build() {
            if (host == null || host.trim().isEmpty()) {
                throw new IllegalStateException("Host must be set before building DecisionRulesOptions");
            }
            return new DecisionRulesOptions(this);
        }
    }
}