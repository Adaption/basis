package io.osiris.query.common.builder;

public class SqlServerBuilder extends QueryBuilder {

    @Override
    public QueryBuilder limit(int limit) {
        if (this.limit.called) return this;

        this.limit.command = new StringBuilder("TOP ").append(limit).append(" ");

        this.limit.called = true;
        return this;
    }

    @Override
    public QueryBuilder paging(int itemPerPage, int page) {
        if (this.paging.called) return this;

        this.paging.command = new StringBuilder("OFFSET ").append((page - 1) * itemPerPage)
                .append(" ROWS FETCH NEXT ").append(itemPerPage).append(" ROWS ONLY ");

        this.paging.called = true;
        return this;
    }

    @Override
    public SqlServerBuilder build() {
        if (this.query != null) return this;

        this.query = new StringBuilder("SELECT ")
                .append(this.distinct.retrieve())
                .append(this.limit.retrieve())
                .append(columns)
                .append(this.from.retrieve())
                .append(this.join.retrieve())
                .append(this.where.retrieve())
                .append(this.groupBy.retrieve())
                .append(this.having.retrieve())
                .append(this.orderBy.retrieve())
                .append(this.paging.retrieve());

        this.params.addAll(this.where.params);
        this.params.addAll(this.having.params);

        return this;
    }
}
