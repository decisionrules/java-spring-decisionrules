package cz.epptec.decision;

import cz.epptec.decision.model.ExampleRuleInput;
import cz.epptec.decision.model.ExampleRuleOutput;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        String ruleId = "***YOUR DESIRED RULE ID HERE***";
        String versionId = "1";
        String baseUrl = "https://api.decisionrules.io/rule/solve";
        String bearerToken = "***YOUR TOKEN BEARER HERE***";

        //init service
        DecisionRulesService service = new DecisionRulesService(baseUrl,bearerToken);

        //call api using string containing json, response is also json in string
        String jsonIntput = "***YOUR JSON STRING INPUT HERE***";
        String jsonResult = service.solveRule(ruleId, versionId,jsonIntput);
        System.out.println("Result of simple call with json string:");
        System.out.println(jsonResult);

        //call api using java model
        ExampleRuleInput inputModel = createTestInput();
        List<ExampleRuleOutput> outputModel = service.solveRuleWithModel(ruleId, versionId,inputModel);
        System.out.println("Result of call with java model:");
        System.out.println(outputModel);

    }

    private static ExampleRuleInput createTestInput() {
        ExampleRuleInput input = new ExampleRuleInput();
        ExampleRuleInput.Delivery delivery = new ExampleRuleInput.Delivery();
        delivery.setDistance(40);
        delivery.setTariff("basic");
        input.setDelivery(delivery);
        ExampleRuleInput.Pack pack = new ExampleRuleInput.Pack();
        pack.setLongestSide(50);
        pack.setWeight(4);
        input.setPack(pack);
        return input;
    }
}
