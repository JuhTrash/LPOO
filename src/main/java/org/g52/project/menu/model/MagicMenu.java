package org.g52.project.menu.model;

import org.g52.project.battle.model.MagicAttack;

import java.util.List;

public class MagicMenu extends Menu {
    public MagicMenu(List<MagicAttack> attacks){
        super();
        for (MagicAttack magicAttack: attacks){
            menuOptions.add(magicAttack.getName());
        }
    }
}
