package io.osiris.query.common.builder;

abstract class QueryComponent {

    boolean called = false;
    StringBuilder command;

    String retrieve() {
        return this.command == null ? "" : this.command.toString();
    }
}
