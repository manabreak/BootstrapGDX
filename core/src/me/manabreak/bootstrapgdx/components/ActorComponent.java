package me.manabreak.bootstrapgdx.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;
import com.badlogic.gdx.graphics.g2d.Sprite;

import me.manabreak.bootstrapgdx.SpriteActor;

@PooledWeaver
public class ActorComponent extends Component {
    public SpriteActor actor;

    public ActorComponent() {
        actor = new SpriteActor();
    }

    public SpriteActor getActor() {
        return actor;
    }

    public Sprite getSprite() {
        return actor.getSprite();
    }

    public void setSprite(Sprite sprite) {
        actor.setSprite(sprite);
    }
}
