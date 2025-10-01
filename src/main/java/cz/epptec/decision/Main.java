package cz.epptec.decision;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cz.epptec.decision.DecisionRulesEnums.RuleStatus;
import cz.epptec.decision.model.Rule;

public class Main {
        public static void main(String[] args) throws Exception {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);

                String host = "https://test.api.decisionrules.io";
                String solverKey = "ObZQp40R6-M2hBK8O6Qm0757X7T4XLlE2A5VXpbQde_MiWLP0t7iFmZe__mUZLR0";
                String managementKey = "cQJWhZUpyylFrde5OTO5IxGfN8klJZVrai0u5k63iNvEacZjq6BQq4vKlje839u9";

                // init service
                DecisionRulesService dr = new DecisionRulesService(
                                new DecisionRulesOptions(host, solverKey, managementKey));

                Rule rule = dr.management.getRule("16fbb1b7-5942-6637-15f5-5663c43601d5");

                rule.setBaseId(null);
                rule.setRuleId(null);
                rule.setSessionId(null);
                rule.setCompositionId(null);
                rule.setPreviousBaseId(null);
                rule.setRuleAlias(null);
                rule.setName("Created from SDK");

                System.out.println(mapper
                                .writeValueAsString(dr.management.createRule(rule)));
                System.out.println(mapper
                                .writeValueAsString(dr.management.createRule(rule, "/New Folder")));
        }
}
