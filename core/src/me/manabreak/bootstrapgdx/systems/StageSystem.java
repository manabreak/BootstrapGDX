package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.manabreak.bootstrapgdx.components.ActorComponent;

/**
 * A simple system that wraps a stage and handles
 * insertion and removal of actors.
 */
@Wire
public class StageSystem extends IteratingSystem {

    private final Stage stage;
    private ComponentMapper<ActorComponent> actors;

    public StageSystem(Viewport viewport) {
        super(Aspect.all(ActorComponent.class));
        stage = new Stage(viewport);
    }

    @Override
    public void inserted(int e) {
        stage.getRoot().addActorAt(0, actors.get(e).actor);
    }

    @Override
    public void removed(int e) {
        actors.get(e).getActor().remove();
    }

    @Override
    protected void process(int id) {

    }

    @Override
    protected void end() {
        stage.act(world.getDelta());
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public OrthographicCamera getCamera() {
        return (OrthographicCamera) stage.getCamera();
    }
}
