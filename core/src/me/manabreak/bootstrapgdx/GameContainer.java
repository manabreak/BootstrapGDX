package me.manabreak.bootstrapgdx;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import me.manabreak.bootstrapgdx.components.GroupComponent;
import me.manabreak.bootstrapgdx.components.Player;
import me.manabreak.bootstrapgdx.systems.PlayerSystem;
import me.manabreak.bootstrapgdx.systems.StageSystem;

public class GameContainer {

    private final World world;

    public GameContainer() {

        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new PlayerSystem(),
                        new StageSystem(new ExtendViewport(100f, 100f))
                )
                .build();

        world = new World(config);

        Archetype a = new ArchetypeBuilder()
                .add(
                        GroupComponent.class,
                        Player.class
                ).build(world);

        world.create(a);
    }

    public void update(float dt) {
        world.setDelta(dt);
        world.process();
    }
}
