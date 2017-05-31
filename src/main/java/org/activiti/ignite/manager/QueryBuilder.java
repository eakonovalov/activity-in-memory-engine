package org.activiti.ignite.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ekonovalov on 15.05.2017.
 */
public class QueryBuilder {

    private String fromClause;
    private StringBuilder conditions = new StringBuilder();
    private List<Object> args = new ArrayList<>();

    public String getFromClause() {
        return fromClause;
    }

    public void setFromClause(String fromClause) {
        this.fromClause = fromClause;
    }

    public String getConditions() {
        return conditions.toString();
    }

    public void appendCondition(String condition) {
        this.conditions.append(this.conditions.length() > 0 ? " AND " : "").append(condition);
    }

    public List<Object> getArgs() {
        return args;
    }

    public void appendArgs(Object... args) {
        Collections.addAll(this.args, args);
    }

}
