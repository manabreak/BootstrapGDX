package me.manabreak.bootstrapgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Res {

    private static TextureAtlas atlas;

    public static Sprite createSprite(String name) {
        return atlas.createSprite(name);
    }

    public static TextureRegion findRegion(String name) {
        return atlas.findRegion(name);
    }

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("graphics/game.atlas"));
    }
}
