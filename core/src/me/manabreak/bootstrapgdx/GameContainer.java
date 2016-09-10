package me.manabreak.bootstrapgdx;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import me.manabreak.bootstrapgdx.systems.StageSystem;

public class GameContainer {

    private final World world;

    public GameContainer() {

        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new StageSystem(new ExtendViewport(100f, 100f))
                )
                .build();

        world = new World(config);

    }

    public void update(float dt) {
        world.setDelta(dt);
        world.process();
    }
}
