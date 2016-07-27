package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import me.manabreak.bootstrapgdx.components.ActorComponent;
import me.manabreak.bootstrapgdx.components.SpriteComponent;

@Wire
public class StageSystem extends IteratingSystem {

    private ComponentMapper<ActorComponent> actors;
    private ComponentMapper<SpriteComponent> sprites;

    private final Stage stage;

    public StageSystem(float width, float height) {
        super(Aspect.all(ActorComponent.class));
        stage = new Stage(new ExtendViewport(width, height));
    }

    @Override
    public void inserted(int e) {
        stage.addActor(actors.get(e).getActor());
    }

    @Override
    public void removed(int e) {
        actors.get(e).getActor().remove();
    }

    @Override
    protected void process(int entityId) {
        if (sprites.has(entityId)) {
            Actor a = actors.get(entityId).getActor();
            Sprite s = sprites.get(entityId).getSprite();
        }
    }

    @Override
    protected void end() {
        stage.act(world.getDelta());
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }
}
