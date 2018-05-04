package io.osiris.query.common.builder;

import io.osiris.query.tuple.Tuple;

import java.util.ArrayList;
import java.util.List;

import static io.osiris.query.common.builder.QueryFunctionHandler.conditionHandler;

abstract class QueryCondition extends QueryComponent {

    static final String AND = "AND ";
    static final String OR = "OR ";

    List<String> params = new ArrayList<>();

    void process(Tuple tuple, String disjunction, String keyword) {
        if (!this.called) this.command = new StringBuilder(keyword + " ");

        if (disjunction != null) this.command.append(disjunction);

        this.command.append(conditionHandler(tuple, this));
        this.called = true;
    }
}
