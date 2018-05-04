package io.osiris.data.common.binding;

import java.io.Serializable;

public interface DtoBindingsFactory<T extends DataBindings, R extends RelationBindings> extends Serializable {

    T createDataBindings();

    R createRelationBindings();
}
