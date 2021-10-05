package org.g52.project;

public interface Player {
    int getHp();
    int getMaxHp();

    void setHp(int hp);
    void setMaxHp(int maxHp);

    int getMp();
    int getMaxMp();

    void setMp(int mp);
    void setMaxMp(int maxMp);

    Elements getElement();
    void setElement(Elements element);
}
