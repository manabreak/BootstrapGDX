package me.manabreak.bootstrapgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Res {

    public static final String ATLAS_PATH = "graphics/game.atlas";
    private static TextureAtlas atlas;

    public static Sprite createSprite(String name) {
        return atlas.createSprite(name);
    }

    public static TextureRegion findRegion(String name) {
        return atlas.findRegion(name);
    }

    public static void load() {
        if(!Gdx.files.internal(ATLAS_PATH).exists()) {
            atlas = new TextureAtlas();
        }else {
            atlas = new TextureAtlas(ATLAS_PATH);
        }
    }
}
