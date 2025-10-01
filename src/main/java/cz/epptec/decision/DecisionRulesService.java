package cz.epptec.decision;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import cz.epptec.decision.DecisionRulesEnums.RuleStatus;
import cz.epptec.decision.api.ManagementApi;
import cz.epptec.decision.api.SolveApi;
import cz.epptec.decision.model.FindOptions;
import cz.epptec.decision.model.FolderNode;
import cz.epptec.decision.model.FolderOptions;
import cz.epptec.decision.model.Rule;
import cz.epptec.decision.model.RuleOptions;
import cz.epptec.decision.utils.Utils;

public class DecisionRulesService {

    private final RestTemplate restTemplate;
    public final DecisionRulesOptions options;
    public final Management management = new Management();
    public final SolveApi solveApi;
    public final ManagementApi managementApi;

    public DecisionRulesService(DecisionRulesOptions options) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(Utils.createConverter());
        this.options = options;
        this.solveApi = new SolveApi(this.restTemplate, this.options);
        this.managementApi = new ManagementApi(this.restTemplate);
    }

    public String solve(final String ruleIdOrAlias, final String input) {
        return solveApi.solveAPI(ruleIdOrAlias, input, "");
    }

    public class Management {

        public Rule getRule(String ruleIdOrAlias, int version) throws Exception {
            try {
                return managementApi.getRuleAPI(options, ruleIdOrAlias, version, null);
            } catch (Exception e) {
                throw e;
            }
        }

        public Rule getRule(String ruleIdOrAlias) throws Exception {
            try {
                return managementApi.getRuleAPI(options, ruleIdOrAlias, null, null);
            } catch (Exception e) {
                throw e;
            }
        }

        public Rule getRuleByPath(String path, int version) throws Exception {
            try {
                return managementApi.getRuleAPI(options, "", version, new RuleOptions(path, version));
            } catch (Exception e) {
                throw e;
            }
        }

        public Rule getRuleByPath(String path) throws Exception {
            try {
                return managementApi.getRuleAPI(options, "", null, new RuleOptions(path, null));
            } catch (Exception e) {
                throw e;
            }
        }

        public Rule updateRuleStatus(String ruleIdOrAlias, RuleStatus status, int version) {
            try {
                return managementApi.updateRuleStatusAPI(options, ruleIdOrAlias, status, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule updateRule(String ruleIdOrAlias, Rule rule, int version) {
            try {
                return managementApi.updateRuleAPI(options, ruleIdOrAlias, rule, version);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule updateRule(String ruleIdOrAlias, Rule rule) {
            try {
                return managementApi.updateRuleAPI(options, ruleIdOrAlias, rule, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule createRule(Rule rule, String path) {
            try {
                return managementApi.createRuleAPI(options, rule, new RuleOptions(path, null));
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule createRule(Rule rule) {
            try {
                return createRule(rule, null);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        public Rule createNewRuleVersion(String ruleIdOrAlias, Object rule) {
            try {
                return managementApi.createNewRuleVersionAPI(options, ruleIdOrAlias, rule);
            } catch (Exception e) {
                throw Utils.handleError(e);
            }
        }

        // public void deleteRule(String ruleIdOrAlias, int version) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void deleteRule(String ruleIdOrAlias) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void lockRule(String ruleIdOrAlias, boolean lock, int version) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void lockRule(String ruleIdOrAlias, boolean lock) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void findDuplicates(String ruleIdOrAlias, int version) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void findDuplicates(String ruleIdOrAlias) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void findDependencies(String ruleIdOrAlias, int version) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void findDependencies(String ruleIdOrAlias) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void getRulesForSpace() {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public String[] getRulesByTags(String tags[]) {
        // return new String[0];
        // }

        // public String[] updateTags(String ruleIdOrAlias, String[] tags, int
        // version) {
        // return new String[0];
        // }

        // public String[] updateTags(String ruleIdOrAlias, String[] tags) {
        // return new String[0];
        // }

        // public void deleteTags(String ruleIdOrAlias, String[] tags, int version) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void deleteTags(String ruleIdOrAlias, String[] tags) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void createFolder(String targetNodeid, FolderOptions data) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void updateNodeFolderStructure(String targetNodeid, FolderOptions
        // data) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public Object exportFolder(String nodeId) {
        // return new Object();
        // }

        // public void importFolder(String targetNodeid, Object data) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void getNodeFolderStructure(String targetNodeid) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void deleteFolder(String targetNodeid, boolean deleteAll) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void renameFolder(String targetNodeid, String newName) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void moveFolder(String targetId, List<FolderNode> nodes) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }

        // public void findFolderOrRuleByAttribute(FindOptions data) {
        // try {
        // return managementApi.getRuleAPI(options, ruleIdOrAlias, version);
        // } catch (Exception e) {
        // throw Utils.handleError(e);
        // }
        // }
    }

}