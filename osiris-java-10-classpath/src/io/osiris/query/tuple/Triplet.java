package io.osiris.query.tuple;

public class Triplet extends QueryTuple {

    Triplet(Object value1, Object value2, Object value3) {
        super(value1, value2, value3);
    }

    public static Triplet create(Object value1, Object value2, Object value3) {
        return new Triplet(value1, value2, value3);
    }
}
