package cz.epptec.decision;

public class DecisionRulesEnums {
    public enum HostEnum {
        GLOBAL_CLOUD("global_cloud"),
        REGION_EU("region_eu"),
        REGION_US("region_us"),
        REGION_AU("region_au");

        public final String value;

        HostEnum(String value) {
            this.value = value;
        }
    }

    public enum FolderType {
        ROOT,
        FOLDER,
        RULE,
    }

    public enum RuleStatus {
        PENDING("pending"),
        PUBLISHED("published");

        public final String value;

        RuleStatus(String value) {
            this.value = value;
        }
    }

    public enum MngCategoryEnum {
        RULE("rule"),
        SPACE("space"),
        TAGS("tags"),
        FOLDER("folder"),
        TOOLS("tools"),
        RULE_FLOW("rule-flow");

        public final String value;

        MngCategoryEnum(String value) {
            this.value = value;
        }
    }
}
