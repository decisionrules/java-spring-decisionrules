package cz.epptec.decision.model;

public class SolverOptions {
    public Boolean debug;
    public String corrId;
    public Boolean audit;
    public Integer auditTtl;

    public SolverOptions(Boolean debug, String corrId, Boolean audit, Integer auditTtl) {
        this.debug = debug;
        this.corrId = corrId;
        this.audit = audit;
        this.auditTtl = auditTtl;
    }
}
