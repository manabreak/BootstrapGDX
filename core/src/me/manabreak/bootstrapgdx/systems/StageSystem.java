package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.manabreak.bootstrapgdx.components.ActorComponent;
import me.manabreak.bootstrapgdx.components.GroupComponent;

/**
 * A simple system that wraps a stage and handles
 * insertion and removal of actors.
 */
@Wire
public class StageSystem extends IteratingSystem {

    private final Stage stage;
    private ComponentMapper<ActorComponent> actors;
    private ComponentMapper<GroupComponent> groups;

    public StageSystem(Viewport viewport) {
        super(Aspect.one(ActorComponent.class, GroupComponent.class));
        stage = new Stage(viewport);
    }

    @Override
    public void inserted(int e) {
        if (actors.has(e)) {
            stage.getRoot().addActorAt(0, actors.get(e).actor);
        } else if (groups.has(e)) {
            stage.getRoot().addActorAt(0, groups.get(e).getGroup());
        }
    }

    @Override
    public void removed(int e) {
        if (actors.has(e)) {
            actors.get(e).getActor().remove();
        } else if (groups.has(e)) {
            groups.get(e).getGroup().remove();
        }
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
