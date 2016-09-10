package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

import me.manabreak.bootstrapgdx.CollisionCallback;
import me.manabreak.bootstrapgdx.components.Collider;

/**
 * A rather simple collision system to detect collisions between two sprites.
 */
public class SpriteCollisionSystem extends BaseEntitySystem {

    private final List<CollisionCallback> collisionCallbacks = new ArrayList<>();
    private ComponentMapper<SpriteComponent> sprites;
    private ComponentMapper<Collider> colliders;

    /**
     * Creates a new sprite collision system
     */
    public SpriteCollisionSystem() {
        super(Aspect.all(Collider.class, SpriteComponent.class));
    }

    /**
     * Registers a new callback to invoke when two sprites collide
     *
     * @param callback Callback
     */
    public void addCallback(CollisionCallback callback) {
        if (!collisionCallbacks.contains(callback)) {
            collisionCallbacks.add(callback);
        }
    }

    /**
     * Removes a previously registered collision callback
     *
     * @param callback Callback
     */
    public void removeCallback(CollisionCallback callback) {
        collisionCallbacks.remove(callback);
    }

    /**
     * Processes the relevant entities.
     * <p/>
     * The entities are checked twice to support one-way collisions.
     */
    @Override
    protected void processSystem() {
        IntBag ids = getEntityIds();
        int size = ids.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i == j) continue;
                check(ids.get(i), ids.get(j));
            }
        }
    }

    /**
     * Checks whether or not two entities are colliding.
     * The collision will happen if and only if the 'other' entity's
     * category flag is set in 'first' entity's collision mask AND
     * if the sprites' bounding rectangles overlap.
     *
     * @param first entity
     * @param other entity
     */
    private void check(int first, int other) {
        Collider c0 = colliders.get(first);
        Collider c1 = colliders.get(other);

        // Check if the objects can collide based on their collision bits
        if ((c1.getCategory() & c0.getMask()) == 0) return;

        Sprite s0 = sprites.get(first).getSprite();
        Sprite s1 = sprites.get(other).getSprite();

        if (s0.getBoundingRectangle().overlaps(s1.getBoundingRectangle())) {
            for (CollisionCallback callback : collisionCallbacks) {
                callback.onCollision(s0, s1);
            }
        }
    }
}
