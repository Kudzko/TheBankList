package by.devincubator.firstTask.bean;

import by.devincubator.firstTask.model.dao.daointerface.IdDefinition;

public class Entity implements IdDefinition {
    protected int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
