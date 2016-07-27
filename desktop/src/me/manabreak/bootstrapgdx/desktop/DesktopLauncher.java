package me.manabreak.bootstrapgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import me.manabreak.bootstrapgdx.GdxGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 960;
        config.height = 576;

        TexturePacker.Settings s = new TexturePacker.Settings();
        s.filterMag = Texture.TextureFilter.Nearest;
        s.filterMin = Texture.TextureFilter.Nearest;
        s.maxWidth = 1024;
        s.maxHeight = 1024;
        s.paddingX = 3;
        s.paddingY = 3;
        s.edgePadding = true;
        s.alias = false;
        s.bleed = true;
        s.duplicatePadding = true;
        s.useIndexes = false;

        TexturePacker.process(s, "../../images", "../../android/assets/graphics", "game");

        new LwjglApplication(new GdxGame(), config);
    }
}
