package me.manabreak.bootstrapgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import java.io.File;

import me.manabreak.bootstrapgdx.GdxGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.height = 960;
        config.width = 576;

        File f = new File("../../images");
        if (f.exists() && f.isDirectory() && f.listFiles().length > 0) {
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
        } else {
            System.out.println("No images to pack");
        }

        new LwjglApplication(new GdxGame(), config);
    }
}
