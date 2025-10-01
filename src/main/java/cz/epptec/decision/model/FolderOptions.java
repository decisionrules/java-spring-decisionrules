package cz.epptec.decision.model;

import java.util.List;

import cz.epptec.decision.DecisionRulesEnums.FolderType;

public class FolderOptions {
    public FolderType type;
    public String name;
    public String id;
    public String baseId;
    public Integer version;
    public List<Object> children;

    public FolderOptions(String baseId, List<Object> children, String id, String name, FolderType type,
            Integer version) {
        this.baseId = baseId;
        this.children = children;
        this.id = id;
        this.name = name;
        this.type = type;
        this.version = version;
    }
}
