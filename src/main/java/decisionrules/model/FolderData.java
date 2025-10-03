package decisionrules.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import decisionrules.DecisionRulesEnums.FolderType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FolderData {
    public FolderType type;
    public String name;
    public String id;
    public String baseId;
    public String ruleAlias;
    public Integer version;
    public List<FolderData> children;

    public FolderData() {
    }

    public FolderData(String baseId, List<FolderData> children, String id, String name, FolderType type,
            Integer version, String ruleAlias) {
        this.baseId = baseId;
        this.children = children;
        this.id = id;
        this.name = name;
        this.type = type;
        this.version = version;
        this.ruleAlias = ruleAlias;
    }

    public static class Builder {
        private FolderType type;
        private String name;
        private List<FolderData> children;

        public Builder setType(FolderType type) {
            this.type = type;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setChildren(List<FolderData> children) {
            this.children = children;
            return this;
        }

        public FolderData build() {
            return new FolderData(
                    null, // baseId not set in builder
                    children,
                    null, // id not set in builder
                    name,
                    type,
                    null, // version not set in builder
                    null // ruleAlias not set in builder
            );
        }
    }
}
