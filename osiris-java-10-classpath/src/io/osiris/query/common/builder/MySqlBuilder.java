package io.osiris.query.common.builder;

public class MySqlBuilder extends QueryBuilder {

    @Override
    public QueryBuilder limit(int limit) {
        if (this.limit.called) return this;

        this.limit.command = new StringBuilder("LIMIT ").append(limit).append(" ");

        this.limit.called = true;
        return this;
    }

    @Override
    public QueryBuilder paging(int itemPerPage, int page) {
        if (this.paging.called) return this;

        this.paging.command = new StringBuilder("LIMIT ? OFFSET ? ");
        this.paging.command = new StringBuilder("LIMIT ").append(itemPerPage)
                .append(" OFFSET ").append((page - 1) * itemPerPage);

        this.paging.called = true;
        return this;
    }

    @Override
    public MySqlBuilder build() {
        if (this.query != null) return this;

        this.query = new StringBuilder("SELECT ")
                .append(this.distinct.retrieve())
                .append(columns)
                .append(this.from.retrieve())
                .append(this.join.retrieve())
                .append(this.where.retrieve())
                .append(this.groupBy.retrieve())
                .append(this.having.retrieve())
                .append(this.orderBy.retrieve())
                .append(this.limit.retrieve())
                .append(this.paging.retrieve());

        this.params.addAll(this.where.params);
        this.params.addAll(this.having.params);

        return this;
    }
}
