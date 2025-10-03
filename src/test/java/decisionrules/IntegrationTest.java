package decisionrules;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import decisionrules.DecisionRulesEnums.FolderType;
import decisionrules.DecisionRulesEnums.RuleStatus;
import decisionrules.model.FindOptions;
import decisionrules.model.FolderData;
import decisionrules.model.FolderExport;
import decisionrules.model.FolderNode;
import decisionrules.model.Rule;

public class IntegrationTest {
    @Test
    void testFullWorkflow() {
        // Build rule
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Rule rule = new Rule.Builder()
                .setName("Integration Flow v2")
                .setDescription("")
                .setType("integration-flow")
                .setStatus("published")
                .setInputSchema(Map.of("input", Map.of()))
                .setOutputSchema(Map.of("output", Map.of()))
                .setTags(List.of())
                .setAuditLog(Map.of("active", false, "debug", Map.of("active", false), "ttl",
                        14))
                .setVisualEditorData(Map.of(
                        "scale", 1,
                        "rotate", 0,
                        "translate", Map.of("x", -67, "y", -1)))
                .setSelectedWebhookAliases(List.of("wh-E5d6EU8B"))
                .setWorkflowData(Map.of(
                        "nodes", List.of(
                                Map.of(
                                        "connectors", List.of(
                                                Map.of(
                                                        "type",
                                                        "out",
                                                        "maxConnections",
                                                        -1,
                                                        "subType",
                                                        "none",
                                                        "maxCount",
                                                        1,
                                                        "minCount",
                                                        1)),
                                        "id", "c2c8e47c",
                                        "version", 1,
                                        "type", "START",
                                        "position", Map.of("x", 167, "y", 325)),
                                Map.of(
                                        "connectors", List.of(
                                                Map.of(
                                                        "type",
                                                        "out",
                                                        "maxConnections",
                                                        -1,
                                                        "subType",
                                                        "none",
                                                        "maxCount",
                                                        1,
                                                        "minCount",
                                                        1),
                                                Map.of(
                                                        "type",
                                                        "in",
                                                        "maxConnections",
                                                        -1,
                                                        "subType",
                                                        "none",
                                                        "maxCount",
                                                        1,
                                                        "minCount",
                                                        1)),
                                        "id", "63e3801f",
                                        "version", 1,
                                        "type", "DATA_MANIPULATION",
                                        "position", Map.of("x", 535, "y", 285),
                                        "name", "assign",
                                        "data", Map.of(
                                                "mapping", List.of(
                                                        Map.of(
                                                                "source",
                                                                Map.of(
                                                                        "expression",
                                                                        Map.of(
                                                                                "type",
                                                                                1,
                                                                                "outputScalarValue",
                                                                                Map.of(
                                                                                        "type",
                                                                                        "function",
                                                                                        "value",
                                                                                        "Job will be run",
                                                                                        "stringValue",
                                                                                        "Job will be run"))),
                                                                "target",
                                                                Map.of("path", "output.output"))),
                                                "guiSettings",
                                                Map.of("showAll",
                                                        false)))),
                        "connections", List.of(
                                Map.of("type", 0))))
                .build();

        String host = System.getenv("HOST");
        String solverKey = System.getenv("SOLVER_KEY");
        String managementKey = System.getenv("MANAGEMENT_KEY");

        // init service
        DecisionRulesService dr = new DecisionRulesService(
                new DecisionRulesOptions(host, solverKey, managementKey));

        dr.management.createFolder("root", new FolderData.Builder().setType(FolderType.FOLDER)
                .setName("Folder Name").setChildren(List.of()).build());
        FolderData folder = dr.management.getFolderStructure("root").children.stream()
                .filter(f -> f.name.equals("Folder Name")).findFirst().orElse(null);
        dr.management.deleteFolder(folder.id, true);
        dr.management.createFolderByPath("/", new FolderData.Builder().setType(FolderType.FOLDER)
                .setName("Folder Name").setChildren(List.of()).build());
        folder = dr.management.getFolderStructure("root").children.stream()
                .filter(f -> f.name.equals("Folder Name")).findFirst().orElse(null);
        Rule createdRule = dr.management.createRule(rule, "/Folder Name");
        createdRule.description = "Updated description";
        dr.management.getRuleByPath("/Folder Name/" + rule.name);
        dr.management.updateRule(createdRule.ruleId, createdRule);
        dr.management.updateRuleStatus(createdRule.ruleId, RuleStatus.PENDING, 1);
        dr.management.updateRuleStatus(createdRule.ruleId, RuleStatus.PUBLISHED, 1);
        dr.management.lockRule(createdRule.ruleId, true);
        dr.management.lockRule(createdRule.ruleId, false);
        dr.management.lockRuleByPath("/Folder Name/" + rule.name, true, 1);
        dr.management.lockRuleByPath("/Folder Name/" + rule.name, false, 1);
        dr.management.createNewRuleVersion(createdRule.ruleId, rule);
        dr.management.getRule(createdRule.ruleId, 1);
        dr.management.getRule(createdRule.ruleId, 2);
        dr.management.getRuleByPath("/Folder Name/" + rule.name, 1);
        dr.management.getRuleByPath("/Folder Name/" + rule.name, 2);
        FolderExport folderExport = dr.management.exportFolder(folder.id);
        dr.management.deleteRule(createdRule.ruleId, 1);
        dr.management.getRule(createdRule.ruleId);
        dr.management.deleteRuleByPath("/Folder Name/" + rule.name, 2);
        dr.management.getRulesForSpace();
        dr.management.renameFolder(folder.id, "New Name");
        dr.management.importFolder("root", folderExport);
        FolderData folder2 = dr.management.getFolderStructure().children.stream()
                .filter(f -> f.name.equals("Folder Name")).findFirst().orElse(null);
        dr.management.getFolderStructure(folder2.id);
        dr.management.moveFolder(folder.id, new FolderNode[] {
                new FolderNode(folder2.id, FolderType.FOLDER)
        }, "/New Name");
        dr.management.findFolderOrRuleByAttribute(
                new FindOptions(null, null, null, null, null, null, null, FolderType.RULE,
                        null));
        dr.management.deleteFolderByPath("/New Name", true);
    }
};
