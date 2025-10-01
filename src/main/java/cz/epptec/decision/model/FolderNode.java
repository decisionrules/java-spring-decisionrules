package cz.epptec.decision.model;

import cz.epptec.decision.DecisionRulesEnums.FolderType;

public class FolderNode {
    public String id;
    public FolderType type;

    public FolderNode(String id, FolderType type) {
        this.id = id;
        this.type = type;
    }
}