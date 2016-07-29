package me.manabreak.bootstrapgdx;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface CollisionCallback {
    void onCollision(Sprite first, Sprite other);
}
