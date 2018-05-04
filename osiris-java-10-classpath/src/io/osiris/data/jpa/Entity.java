package io.osiris.data.jpa;

import io.osiris.data.common.binding.function.DataBindingHandler;
import io.osiris.data.jpa.binding.EntityBindingsFactory;
import io.osiris.data.jpa.binding.EntityDataBindings;
import io.osiris.data.jpa.binding.EntityRelationBindings;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class Entity extends JpaDTO {

    private final EntityRelationBindings relationBindings;
    private final List<String> idColumns;

    protected Entity() {
        EntityBindingsFactory bindingsFactory = new EntityBindingsFactory(this.getClass());
        EntityDataBindings dataBindings = bindingsFactory.createDataBindings();
        this.relationBindings = bindingsFactory.createRelationBindings();
        this.idColumns = dataBindings.idColumns();
    }

    public Map<String, Serializable> idMap() {
        Map<String, Serializable> idMap = new HashMap<>();

        List idValues = DataBindingHandler.fetchIds(this.getClass(), this);

        for (int i = 0; i < idColumns.size(); i++) {
            idMap.put(idColumns.get(i), (Serializable) idValues.get(i));
        }

        return idMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Optional<? extends Entity> manyToOne() {
        List idValues = DataBindingHandler.fetchIds(this.getClass(), this);
        return relationBindings.manyToOne(idValues);
    }

    @Override
    protected List<? extends Entity> oneToMany() {
        List idValues = DataBindingHandler.fetchIds(this.getClass(), this);
        return relationBindings.oneToMany((Serializable) idValues.get(0));
    }
}
