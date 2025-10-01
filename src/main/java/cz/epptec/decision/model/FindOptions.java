package cz.epptec.decision.model;

import cz.epptec.decision.DecisionRulesEnums.FolderType;

public class FindOptions {
    public String name;
    public String id;
    public String baseId;
    public String ruleAlias;
    public String ruleType;
    public String[] tags;
    public String ruleState;
    public FolderType type;
    public Integer version;

    public FindOptions(String name, String id, String baseId, String ruleAlias, String ruleType, String[] tags,
            String ruleState, FolderType type, Integer version) {
        this.name = name;
        this.id = id;
        this.baseId = baseId;
        this.ruleAlias = ruleAlias;
        this.ruleType = ruleType;
        this.tags = tags;
        this.ruleState = ruleState;
        this.type = type;
        this.version = version;
    }
}