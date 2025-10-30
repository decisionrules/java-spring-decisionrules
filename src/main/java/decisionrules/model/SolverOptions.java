package decisionrules.model;

import java.util.List;

import decisionrules.DecisionRulesEnums.StrategyOptions;

public class SolverOptions {
    private Boolean debug;
    private String corrId;
    private Boolean audit;
    private Integer auditTtl;
    private String aliasConflictPath;
    private StrategyOptions strategy;
    private ColsOptions cols;

    // Default constructor (for deserialization)
    public SolverOptions() {
    }

    // Full constructor
    public SolverOptions(Boolean debug, String corrId, Boolean audit, Integer auditTtl,
            String aliasConflictPath, StrategyOptions strategy, ColsOptions cols) {
        this.debug = debug;
        this.corrId = corrId;
        this.audit = audit;
        this.auditTtl = auditTtl;
        this.aliasConflictPath = aliasConflictPath;
        this.strategy = strategy;
        this.cols = cols;
    }

    // Getters
    public Boolean getDebug() {
        return debug;
    }

    public String getCorrId() {
        return corrId;
    }

    public Boolean getAudit() {
        return audit;
    }

    public Integer getAuditTtl() {
        return auditTtl;
    }

    public String getAliasConflictPath() {
        return aliasConflictPath;
    }

    public StrategyOptions getStrategy() {
        return strategy;
    }

    public ColsOptions getCols() {
        return cols;
    }

    // Builder Pattern
    public static class Builder {
        private Boolean debug;
        private String corrId;
        private Boolean audit;
        private Integer auditTtl;
        private String aliasConflictPath;
        private StrategyOptions strategy;
        private ColsOptions cols;

        public Builder debug(Boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder corrId(String corrId) {
            this.corrId = corrId;
            return this;
        }

        public Builder audit(Boolean audit) {
            this.audit = audit;
            return this;
        }

        public Builder auditTtl(Integer auditTtl) {
            this.auditTtl = auditTtl;
            return this;
        }

        public Builder aliasConflictPath(String aliasConflictPath) {
            this.aliasConflictPath = aliasConflictPath;
            return this;
        }

        public Builder strategy(StrategyOptions strategy) {
            this.strategy = strategy;
            return this;
        }

        public Builder cols(ColsOptions cols) {
            this.cols = cols;
            return this;
        }

        public SolverOptions build() {
            return new SolverOptions(debug, corrId, audit, auditTtl, aliasConflictPath, strategy, cols);
        }
    }

    // Nested ColsOptions class
    public static class ColsOptions {
        private List<String> includedConditionCols;
        private List<String> excludedConditionCols;

        public ColsOptions() {
        }

        public ColsOptions(List<String> includedConditionCols, List<String> excludedConditionCols) {
            this.includedConditionCols = includedConditionCols;
            this.excludedConditionCols = excludedConditionCols;
        }

        public List<String> getIncludedConditionCols() {
            return includedConditionCols;
        }

        public List<String> getExcludedConditionCols() {
            return excludedConditionCols;
        }

        public static class Builder {
            private List<String> includedConditionCols;
            private List<String> excludedConditionCols;

            public Builder includedConditionCols(List<String> includedConditionCols) {
                this.includedConditionCols = includedConditionCols;
                return this;
            }

            public Builder excludedConditionCols(List<String> excludedConditionCols) {
                this.excludedConditionCols = excludedConditionCols;
                return this;
            }

            public ColsOptions build() {
                return new ColsOptions(includedConditionCols, excludedConditionCols);
            }
        }
    }
}
