package io.osiris.query.tuple;

import java.util.List;

public class TupleFactory {

    public static Tuple create(List<Object> values) {
        switch (values.size()) {
            case 2:
                return new Pair(values.get(0), values.get(1));
            case 3:
                return new Triplet(values.get(0), values.get(1), values.get(2));
            case 4:
                return new Quadruplet(values.get(0), values.get(1), values.get(2), values.get(3));
            default:
                return new QueryTuple(values);
        }
    }
}
