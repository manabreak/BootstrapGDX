package me.manabreak.bootstrapgdx;

import com.badlogic.gdx.Game;

public class GdxGame extends Game {

    private GameScreen gameScreen;

    @Override
    public void create() {
        Res.load();
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
