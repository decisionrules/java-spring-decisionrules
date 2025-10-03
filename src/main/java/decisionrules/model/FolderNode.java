package decisionrules.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import decisionrules.DecisionRulesEnums.FolderType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FolderNode {
    public String id;
    public FolderType type;

    public FolderNode(String id, FolderType type) {
        this.id = id;
        this.type = type;
    }
}