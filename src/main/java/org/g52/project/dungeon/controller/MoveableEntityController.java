package org.g52.project.dungeon.controller;

import org.g52.project.dungeon.model.Dungeon;

public class MoveableEntityController {
    protected final Dungeon dungeon;

    MoveableEntityController(Dungeon dungeon){
        this.dungeon = dungeon;
    }
}
