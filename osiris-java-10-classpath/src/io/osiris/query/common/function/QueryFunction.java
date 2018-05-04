package io.osiris.query.common.function;

@FunctionalInterface
public interface QueryFunction<Tuple, T, U> {

    T apply(Tuple tuple, U u);
}