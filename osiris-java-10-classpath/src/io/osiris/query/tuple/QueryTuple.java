package io.osiris.query.tuple;

import java.util.List;
import java.util.stream.Stream;

public class QueryTuple implements Tuple {

    private List<Object> values;

    QueryTuple(Object... values) {
        Class<?>[] types = Stream.of(values)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);
        this.values = List.of(values);

        if ((this.values.size() < 1 && types.length == 0)
                || (this.values.size() < 1 && values.length != types.length))
            throw new IllegalArgumentException(
                    "Expected " + types.length + " values. But there are " + values.length + "values");

        if (this.values.size() > 1) {
            for (int i = 0; i < types.length; i++) {
                Class<?> type = types[i];
                Object value = this.values.get(i);
                if (value != null && !type.isAssignableFrom(value.getClass()))
                    throw new IllegalArgumentException(
                            "Expected value of '" + value + "' to be '" + type + "'. " +
                                    "But it shows to be '" + value.getClass() + "' instead.");
            }
        }
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public Object objectAt(int index) {
        return this.values.get(index);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T valueAt(int index) {
        return (T) this.values.get(index);
    }
}


