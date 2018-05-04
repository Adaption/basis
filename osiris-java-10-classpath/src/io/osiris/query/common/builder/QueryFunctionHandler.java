package io.osiris.query.common.builder;

import io.osiris.query.common.function.QueryFunction;
import io.osiris.query.tuple.Pair;
import io.osiris.query.tuple.Quadruplet;
import io.osiris.query.tuple.Triplet;
import io.osiris.query.tuple.Tuple;

import java.util.function.Function;

class QueryFunctionHandler {

    static String conditionHandler(Tuple tuple, QueryCondition queryCondition) {
        if (tuple instanceof Triplet) return tripletHandler.apply((Triplet) tuple, queryCondition);

        if (tuple instanceof Quadruplet) return quadrupletHandler.apply((Quadruplet) tuple, queryCondition);

        return null;
    }

    static Function<Pair, String> pairHandler =
            pair -> String.valueOf(pair.objectAt(0)) + " " + String.valueOf(pair.objectAt(1));

    private static QueryFunction<Triplet, String, QueryCondition> tripletHandler =
            (triplet, queryCondition) -> {
                StringBuilder builder = new StringBuilder("(");
                for (int i = 0; i < triplet.size() - 1; i++) {
                    builder.append(String.valueOf((triplet.objectAt(i))))
                            .append(" ");
                }
                builder.append("?) ");

                queryCondition.params.add(String.valueOf(triplet.objectAt(triplet.size() - 1)));

                return builder.toString();
            };

    private static QueryFunction<Quadruplet, String, QueryCondition> quadrupletHandler =
            (quadruplet, queryCondition) -> {
                StringBuilder builder = new StringBuilder("(");
                for (int i = 0; i < 2; i++) {
                    builder.append(String.valueOf((quadruplet.objectAt(i))))
                            .append(" ");
                }
                builder.append("? AND ?) ");

                queryCondition.params.add(String.valueOf(quadruplet.objectAt(quadruplet.size() - 2)));
                queryCondition.params.add(String.valueOf(quadruplet.objectAt(quadruplet.size() - 1)));

                return builder.toString();
            };
}
