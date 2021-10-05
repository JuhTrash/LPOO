package org.g52.project.menu.controller;

import org.g52.project.Options;

import java.io.IOException;

public interface MenuController {
    public abstract Options step(MenuInput input) throws IOException;
}
