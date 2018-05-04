package io.osiris.query.tuple;

public class Pair extends QueryTuple {

    Pair(Object value1, Object value2) {
        super(value1, value2);
    }

    public static Pair create(Object value1, Object value2) {
        return new Pair(value1, value2);
    }
}
