package decisionrules;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import decisionrules.DecisionRulesEnums.FolderType;
import decisionrules.DecisionRulesEnums.LookupMethodOptions;
import decisionrules.DecisionRulesEnums.RuleStatus;
import decisionrules.DecisionRulesEnums.StrategyOptions;
import decisionrules.model.FindOptions;
import decisionrules.model.FolderData;
import decisionrules.model.FolderExport;
import decisionrules.model.FolderNode;
import decisionrules.model.Job;
import decisionrules.model.Rule;
import decisionrules.model.SolverOptions;

public class IntegrationTest {
        @Test
        @Disabled("Run only when ENV variables are set")
        public void testFullWorkflow() throws InterruptedException, JsonProcessingException {
                // Build rule
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);

                Rule rule = new Rule.Builder()
                                .setName("Integration Flow")
                                .setDescription("")
                                .setType("integration-flow")
                                .setStatus("published")
                                .setRuleAlias("bizarre-jay")
                                .setTags(List.of())
                                // 1. Template Metadata
                                .setTemplateMetadata(Map.of())

                                // 2. Input/Output Schemas
                                .setInputSchema(Map.of("input", Map.of()))
                                .setOutputSchema(Map.of("output", Map.of()))

                                // 3. Workflow Data (The heavy lifting)
                                .setWorkflowData(Map.of(
                                                "nodes", List.of(
                                                                // Node 1: START
                                                                Map.of(
                                                                                "id", "c2c8e47c",
                                                                                "version", 1,
                                                                                "type", "START",
                                                                                "position", Map.of("x", 167, "y", 325),
                                                                                "connectors", List.of(
                                                                                                Map.of(
                                                                                                                "type",
                                                                                                                "out",
                                                                                                                "maxConnections",
                                                                                                                -1,
                                                                                                                "subType",
                                                                                                                "none",
                                                                                                                "id",
                                                                                                                "fbd5a179-588e-4e75-b1e3-73ef458a84a2",
                                                                                                                "maxCount",
                                                                                                                1,
                                                                                                                "minCount",
                                                                                                                1))),
                                                                // Node 2: DATA_MANIPULATION (Assign)
                                                                Map.of(
                                                                                "id", "ea510873",
                                                                                "version", 1,
                                                                                "type", "DATA_MANIPULATION",
                                                                                "name", "assign",
                                                                                "position", Map.of("x", 582, "y", 263),
                                                                                "connectors", List.of(
                                                                                                Map.of(
                                                                                                                "type",
                                                                                                                "out",
                                                                                                                "maxConnections",
                                                                                                                -1,
                                                                                                                "subType",
                                                                                                                "none",
                                                                                                                "id",
                                                                                                                "db64b725-6474-4cd9-affc-523c34c00233",
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
                                                                                                                "id",
                                                                                                                "7d0c5894-4981-4cc4-ae32-bb4b9dde5995",
                                                                                                                "maxCount",
                                                                                                                1,
                                                                                                                "minCount",
                                                                                                                1)),
                                                                                "data", Map.of(
                                                                                                "guiSettings",
                                                                                                Map.of("showAll",
                                                                                                                false),
                                                                                                "mapping", List.of(
                                                                                                                Map.of(
                                                                                                                                "target",
                                                                                                                                Map.of("path", "output.output"),
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
                                                                                                                                                                                "Hello",
                                                                                                                                                                                "stringValue",
                                                                                                                                                                                "Hello")))))))),
                                                "connections", List.of(
                                                                Map.of(
                                                                                "source",
                                                                                "fbd5a179-588e-4e75-b1e3-73ef458a84a2",
                                                                                "target",
                                                                                "7d0c5894-4981-4cc4-ae32-bb4b9dde5995",
                                                                                "type", 0,
                                                                                "id",
                                                                                "b9693373-bb0b-40b5-ab6b-a8c4420066bd"))))

                                // 4. Visual Data
                                .setVisualData(Map.of(
                                                "scale", 1,
                                                "rotate", 0,
                                                "translate", Map.of("x", -67, "y", -18)))

                                // 5. Audit Log
                                .setAuditLog(Map.of(
                                                "active", false,
                                                "ttl", 14,
                                                "debug", Map.of("active", false)))

                                // 6. Dates (Using current date for simplicity)
                                .setCreatedIn(new java.util.Date())
                                .setLastUpdate(new java.util.Date())
                                .build();

                Rule lookupTableRule = new Rule.Builder()
                                .setName("Testing table 2")
                                .setDescription("")
                                .setType("lookup-table")
                                .setInputSchema(Map.of(
                                                "primaryKey", Map.of(),
                                                "outputColumn", Map.of(),
                                                "method", Map.of()))
                                .setOutputSchema(Map.of(
                                                "output", Map.of()))
                                .setTags(List.of())
                                .setStatus("published")
                                .setVersion(4)
                                .setAuditLog(Map.of(
                                                "active", false,
                                                "debug", Map.of("active", false),
                                                "ttl", 14))
                                .setVisualEditorData(Map.of(
                                                "columns", List.of(
                                                                Map.of(
                                                                                "name", "Primary Key",
                                                                                "alias", "pk",
                                                                                "order", 0,
                                                                                "isPrimaryKey", true),
                                                                Map.of(
                                                                                "name", "id",
                                                                                "alias", "03EOXNMB")),
                                                "primaryKeyColumn", "pk",
                                                "data", Map.of(
                                                                "Orange",
                                                                Map.of("pk", "Orange", "03EOXNMB", "1", "_position", 0),
                                                                "Door hinge",
                                                                Map.of("pk", "Door hinge", "03EOXNMB", "2", "_position",
                                                                                1),
                                                                "Porridge",
                                                                Map.of("pk", "Porridge", "03EOXNMB", "3", "_position",
                                                                                2),
                                                                "Four inch",
                                                                Map.of("pk", "Four inch", "03EOXNMB", "4", "_position",
                                                                                3),
                                                                "Forage",
                                                                Map.of("pk", "Forage", "03EOXNMB", "5", "_position", 4),
                                                                "Storage",
                                                                Map.of("pk", "Storage", "03EOXNMB", "6", "_position",
                                                                                5)),
                                                "sourceData", List.of(
                                                                Map.of("pk", "Orange", "03EOXNMB", "1", "_position", 0),
                                                                Map.of("pk", "Door hinge", "03EOXNMB", "2", "_position",
                                                                                1),
                                                                Map.of("pk", "Porridge", "03EOXNMB", "3", "_position",
                                                                                2),
                                                                Map.of("pk", "Four inch", "03EOXNMB", "4", "_position",
                                                                                3),
                                                                Map.of("pk", "Forage", "03EOXNMB", "5", "_position", 4),
                                                                Map.of("pk", "Storage", "03EOXNMB", "6", "_position",
                                                                                5))))
                                .setColumns(List.of(
                                                Map.of(
                                                                "name", "Primary Key",
                                                                "alias", "pk",
                                                                "order", 0,
                                                                "isPrimaryKey", true),
                                                Map.of(
                                                                "name", "id",
                                                                "alias", "03EOXNMB")))
                                .setPrimaryKeyColumn("pk")
                                .setData(Map.of(
                                                "Orange", Map.of("pk", "Orange", "03EOXNMB", "1", "_position", 0),
                                                "Door hinge",
                                                Map.of("pk", "Door hinge", "03EOXNMB", "2", "_position", 1),
                                                "Porridge", Map.of("pk", "Porridge", "03EOXNMB", "3", "_position", 2),
                                                "Four inch", Map.of("pk", "Four inch", "03EOXNMB", "4", "_position", 3),
                                                "Forage", Map.of("pk", "Forage", "03EOXNMB", "5", "_position", 4),
                                                "Storage", Map.of("pk", "Storage", "03EOXNMB", "6", "_position", 5)))
                                .build();

                String host = System.getenv("HOST");
                String solverKey = System.getenv("SOLVER_KEY");
                String managementKey = System.getenv("MANAGEMENT_KEY");

                assertNotNull(host);
                assertNotNull(solverKey);
                assertNotNull(managementKey);

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
                Rule createdlookupTableRule = dr.management.createRule(lookupTableRule, "/Folder Name");
                String requestBody = "{\"primaryKey\":\"Four inch\",\"outputColumn\":{},\"method\":{}}";
                dr.solve(
                                createdlookupTableRule.baseId,
                                requestBody,
                                createdlookupTableRule.version,
                                new SolverOptions.Builder()
                                                .lookupMethod(LookupMethodOptions.LOOKUP_EXISTS)
                                                .build());
                dr.solve(
                                createdlookupTableRule.baseId,
                                requestBody,
                                createdlookupTableRule.version,
                                new SolverOptions.Builder()
                                                .lookupMethod(LookupMethodOptions.LOOKUP_VALUE)
                                                .build());
                dr.solve(
                                createdlookupTableRule.baseId,
                                requestBody);
                createdRule.description = "Updated description";
                dr.management.getRuleByPath("/Folder Name/" + rule.name);
                Job job = dr.job.start(createdRule.ruleId, Map.of("input", Map.of()));
                dr.job.info(job.jobId);
                dr.job.cancel(job.jobId);
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
