package io.osiris.query.tuple;

public class Quadruplet extends QueryTuple {

    Quadruplet(Object value1, Object value2, Object value3, Object value4) {
        super(value1, value2, value3, value4);
    }

    public static Quadruplet create(Object value1, Object value2, Object value3, Object value4) {
        return new Quadruplet(value1, value2, value3, value4);
    }
}
