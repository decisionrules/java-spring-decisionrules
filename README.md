# DecisionRules for Java

![Jest CI](https://github.com/decisionrules/decisionrules/actions/workflows/main.yml/badge.svg)

A [Decisionrules.io](https://decisionrules.io) library that allows you to integrate the DecisionRules Solver, Management, and Job APIs into your Java application as easily as possible.

---

## Table of contents
1.  [Installation](#installation)
2.  [Usage and Examples](#usage-and-examples)
3.  [API](#api)

---

<a name="installation"></a>
## Installation

### Maven
Add the following dependency to your `pom.xml`:
```xml
<dependency>
    <groupId>io.decisionrules</groupId>
    <artifactId>decisionrules-java</artifactId>
    <version>LATEST</version>
</dependency>
````

### Gradle

Add the following to your `build.gradle` file:

```groovy
implementation 'io.decisionrules:decisionrules-java:LATEST'
```

-----

\<a name="usage-and-examples"\>\</a\>

## Usage and Examples

You can start using the library by creating a `DecisionRulesService` instance and providing valid `DecisionRulesOptions`.

### Initialization Example

```java
import decisionrules.DecisionRulesOptions;
import decisionrules.DecisionRulesService;

// Configure options with your credentials
DecisionRulesOptions options = new DecisionRulesOptions("YOUR_HOST", "YOUR_SOLVER_KEY", "YOUR_MANAGEMENT_KEY");

// Initialize the service
DecisionRulesService dr = new DecisionRulesService(options);
```

### Solver API Call Example

Calls can be made with the top-level `solve` method. It returns a raw JSON string for you to process.

```java
import java.util.Map;

// Prepare input data (can be any serializable object)
Map<String, Object> inputData = Map.of(
    "tripDetails", Map.of(
        "origin", "ATL",
        "destination", "DXB"
    )
);

// Solve the rule and get the raw JSON string result
String resultJson = dr.solve("ruleIdOrAlias", inputData);

// You can then parse the JSON string as needed
// e.g., using ObjectMapper
```

### Management API Example

The Management API can be used through the `management` object on the `DecisionRulesService` instance.

```java
import decisionrules.model.Rule;

// Get a rule by its ID
Rule rule = dr.management.getRule("ruleIdOrAlias");
```

### Job API Example

The Job API is used to run input data against an Integration Flow asynchronously.

```java
import decisionrules.model.Job;
import java.util.Map;

// Prepare input data
Map<String, Object> inputData = Map.of(
    "tripDetails", Map.of(
        "origin", "ATL",
        "destination", "DXB"
    )
);

// Start the job
Job job = dr.job.start("integrationFlowIdOrAlias", inputData);
```

-----

\<a name="api"\>\</a\>

## API

All methods described below are exposed on the `DecisionRulesService` class.

### Solver API

#### **DecisionRules.solve**

Solves a rule with the given input data and returns a raw JSON string as the result.

```java
String result = dr.solve(ruleIdOrAlias, inputData);
```

**Arguments:**

| **arg** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `inputData` | `Object` | no |

-----

### Management API

All management methods are available under the `dr.management` object.

#### **DecisionRules.management.getRule / getRuleByPath**

Gets all information about a rule. If version is not specified, it gets the latest published version.

```java
Rule result = dr.management.getRule(ruleIdOrAlias, version);
Rule result = dr.management.getRuleByPath(path, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` / `path` | `String` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.updateRuleStatus**

Changes a rule's status (e.g., from `PENDING` to `PUBLISHED`).

```java
Rule result = dr.management.updateRuleStatus(ruleIdOrAlias, status, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `status` | `RuleStatus` | no |
| `version` | `Integer` | no |

#### **DecisionRules.management.updateRule**

Changes a rule according to the provided `Rule` object.

```java
Rule result = dr.management.updateRule(ruleIdOrAlias, rule, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `rule` | `Rule` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.createRule**

Creates a new rule based on the provided `Rule` object.

```java
Rule result = dr.management.createRule(rule, path);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `rule` | `Rule` | no |
| `path` | `String` | yes |

#### **DecisionRules.management.createNewRuleVersion**

Creates a new version of an existing rule.

```java
Rule result = dr.management.createNewRuleVersion(ruleIdOrAlias, rule);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `rule` | `Rule` | no |

#### **DecisionRules.management.deleteRule / deleteRuleByPath**

Deletes a rule.

```java
dr.management.deleteRule(ruleIdOrAlias, version);
dr.management.deleteRuleByPath(path, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` / `path` | `String` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.lockRule / lockRuleByPath**

Locks or unlocks a rule.

```java
dr.management.lockRule(ruleIdOrAlias, lock, version);
dr.management.lockRuleByPath(path, lock, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` / `path` | `String` | no |
| `lock` | `boolean` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.findDuplicates**

Finds a decision table and returns it with an array of its duplicates.

```java
Duplicates result = dr.management.findDuplicates(ruleIdOrAlias, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.findDependencies**

Finds a rule and returns it with an array of its dependencies.

```java
Dependencies result = dr.management.findDependencies(ruleIdOrAlias, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.getRulesForSpace**

Gets all rules and ruleflows in the space determined by the Management API Key.

```java
Rule[] result = dr.management.getRulesForSpace();
```

#### **DecisionRules.management.getRulesByTags**

Gets all rules/rule flows with certain tags.

```java
Rule[] result = dr.management.getRulesByTags(tags);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `tags` | `String[]` | no |

#### **DecisionRules.management.updateTags**

Adds a tag or tags to a specific rule version or all versions of a rule.

```java
String[] result = dr.management.updateTags(ruleIdOrAlias, tags, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `tags` | `String[]` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.deleteTags**

Deletes a tag or tags from a specific rule version or all versions of a rule.

```java
dr.management.deleteTags(ruleIdOrAlias, tags, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `tags` | `String[]` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.management.createFolder / createFolderByPath**

Creates a new folder under a specified target.

```java
dr.management.createFolder(targetNodeId, data);
dr.management.createFolderByPath(path, data);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `targetNodeId` / `path` | `String` | no |
| `data` | `FolderData` | no |

#### **DecisionRules.management.updateNodeFolderStructure / updateNodeFolderStructureByPath**

Creates folders and moves rules into the new structure under a specified target.

```java
FolderData result = dr.management.updateNodeFolderStructure(targetNodeId, data);
FolderData result = dr.management.updateNodeFolderStructureByPath(path, data);
```

**Arguments:**

| **args** | **type** | **optional** |
|:---|:---|:---|
| `targetNodeId` / `path` | `String` | no |
| `data` | `FolderData` | no |

#### **DecisionRules.management.exportFolder / exportFolderByPath**

Exports a folder with all its rules.

```java
FolderExport result = dr.management.exportFolder(nodeId);
FolderExport result = dr.management.exportFolderByPath(path);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `nodeId` / `path` | `String` | no |

#### **DecisionRules.management.importFolder / importFolderToPath**

Imports a folder structure into a specific folder.

```java
FolderImport result = dr.management.importFolder(targetNodeId, data);
FolderImport result = dr.management.importFolderToPath(path, data);
```

**Arguments:**

| **args** | **type** | **optional** |
|:---|:---|:---|
| `targetNodeId` / `path` | `String` | no |
| `data` | `Object` | no |

#### **DecisionRules.management.getFolderStructure / getFolderStructureByPath**

Retrieves the folder structure for a given node. If no ID/path is provided, retrieves from the root.

```java
FolderData result = dr.management.getFolderStructure(targetNodeId);
FolderData result = dr.management.getFolderStructureByPath(path);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `targetNodeId` / `path` | `String` | yes |

#### **DecisionRules.management.deleteFolder / deleteFolderByPath**

Deletes a folder and all its contents.

```java
dr.management.deleteFolder(targetNodeId, deleteAll);
dr.management.deleteFolderByPath(path, deleteAll);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `targetNodeId` / `path` | `String` | no |
| `deleteAll` | `boolean` | no |

#### **DecisionRules.management.renameFolder / renameFolderByPath**

Renames a folder.

```java
dr.management.renameFolder(targetNodeId, newName);
dr.management.renameFolderByPath(path, newName);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `targetNodeId` / `path` | `String` | no |
| `newName` | `String` | no |

#### **DecisionRules.management.moveFolder**

Moves folders and/or rules under a new parent folder.

```java
dr.management.moveFolder(targetId, nodes, targetPath);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `targetId` | `String` | no |
| `nodes` | `FolderNode[]` | no |
| `targetPath` | `String` | no |

#### **DecisionRules.management.findFolderOrRuleByAttribute**

Finds folders and rules that match the specified criteria. Returns a raw JSON string.

```java
String result = dr.management.findFolderOrRuleByAttribute(data);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `data` | `FindOptions` | no |

-----

### Job API

All job methods are available under the `dr.job` object.

#### **DecisionRules.job.start**

Starts a new asynchronous job for a specific Integration Flow.

```java
Job result = dr.job.start(ruleIdOrAlias, inputData, version);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `ruleIdOrAlias` | `String` | no |
| `inputData` | `Object` | no |
| `version` | `Integer` | yes |

#### **DecisionRules.job.cancel**

Attempts to cancel a running job by its ID.

```java
Job result = dr.job.cancel(jobId);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `jobId` | `String` | no |

#### **DecisionRules.job.info**

Retrieves detailed information about a job, including its status and output.

```java
Job result = dr.job.info(jobId);
```

**Arguments:**

| **args** | **type** | **optional** |
| :--- | :--- | :--- |
| `jobId` | `String` | no |

```
```