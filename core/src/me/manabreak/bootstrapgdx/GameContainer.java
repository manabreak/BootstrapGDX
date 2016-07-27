package me.manabreak.bootstrapgdx;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Gdx;

import me.manabreak.bootstrapgdx.systems.StageSystem;

public class GameContainer {

    private final World world;

    public GameContainer() {

        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new StageSystem(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
                )
                .build();

        world = new World(config);

    }

    public void update(float dt) {
        world.setDelta(dt);
        world.process();
    }
}
