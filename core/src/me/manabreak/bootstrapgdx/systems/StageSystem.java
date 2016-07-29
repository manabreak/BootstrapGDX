package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.manabreak.bootstrapgdx.components.ActorComponent;
import me.manabreak.bootstrapgdx.components.SpriteComponent;

/**
 * A simple system that wraps a stage and handles
 * insertion and removal of actors.
 */
@Wire
public class StageSystem extends IteratingSystem {

    private final Stage stage;
    private ComponentMapper<ActorComponent> actors;
    private ComponentMapper<SpriteComponent> sprites;

    /**
     * Constructs a new stage system with the given viewport.
     *
     * @param viewport Viewport to use
     */
    public StageSystem(Viewport viewport) {
        super(Aspect.all(ActorComponent.class));
        stage = new Stage(viewport);
    }

    /**
     * Adds a newly created actor component to the stage.
     *
     * @param e ID of the entity that owns the actor
     */
    @Override
    public void inserted(int e) {
        stage.addActor(actors.get(e).getActor());
    }

    /**
     * Removes an actor from the stage.
     *
     * @param e ID of the entity that owns the actor
     */
    @Override
    public void removed(int e) {
        actors.get(e).getActor().remove();
    }

    /**
     * If an entity has both an ActorComponent and a SpriteComponent,
     * the sprite is updated to match the actor's parameters.
     *
     * @param entityId ID of the entity to process
     */
    @Override
    protected void process(int entityId) {
        if (sprites.has(entityId)) {
            Actor a = actors.get(entityId).getActor();
            Sprite s = sprites.get(entityId).getSprite();
            s.setPosition(a.getX(), a.getY());
            s.setScale(a.getScaleX(), a.getScaleY());
            s.setOrigin(a.getOriginX(), a.getOriginY());
            s.setRotation(a.getRotation());
            s.setColor(a.getColor());
        }
    }

    /**
     * Called after all the relevant entities are processed.
     * Will update the stage with the world's delta and draws the stage.
     */
    @Override
    protected void end() {
        stage.act(world.getDelta());
        stage.draw();
    }

    /**
     * Retrieves the stage
     *
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }
}
