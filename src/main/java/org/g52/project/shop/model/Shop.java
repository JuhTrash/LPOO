package org.g52.project.shop.model;

public class Shop {
    ShopPlayer player;
    ShopShopkeeper shopkeeper;

    public Shop(ShopPlayer player, ShopShopkeeper shopkeeper){
        this.player = player;
        this.shopkeeper = shopkeeper;
    }

    public ShopPlayer getPlayer() {
        return player;
    }
    public ShopShopkeeper getShopkeeper() {
        return shopkeeper;
    }

    public void setPlayer(ShopPlayer player) {
        this.player = player;
    }
    public void setShopkeeper(ShopShopkeeper shopkeeper) {
        this.shopkeeper = shopkeeper;
    }
}
