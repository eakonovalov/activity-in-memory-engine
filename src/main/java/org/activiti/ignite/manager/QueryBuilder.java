package org.activiti.ignite.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ekonovalov on 15.05.2017.
 */
public class QueryBuilder {

    private String selectClause;
    private String fromClause;
    private StringBuilder conditions = new StringBuilder();
    private List<Object> args = new ArrayList<>();

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

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

    public String getQuery() {
        StringBuilder query = new StringBuilder();
        if(selectClause != null) {
            query.append("SELECT ").append(selectClause);
        }
        if(fromClause != null) {
            query.append(" FROM ").append(fromClause);
        }
        if(conditions.length() > 0) {
            query.append(" WHERE ").append(conditions.toString());
        }

        return query.toString();
    }

}
