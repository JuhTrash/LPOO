package org.g52.project.dungeon.model;

import org.g52.project.Elements;

abstract public class MovableEntity extends Entity{
    protected Elements element;
    MovableEntity(int x, int y, Elements element) {
        super(x, y);
        this.element = element;
    }

    MovableEntity(Dungeon dungeon) {
        super(dungeon);
    }

    public Elements getElement() {
        return element;
    }
    public void setElement(Elements element) {
        this.element = element;
    }
}
