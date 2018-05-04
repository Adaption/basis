package io.osiris.query.tuple;

public interface Tuple {

    int size();

    Object objectAt(int index);

    <T> T valueAt(int index);
}
