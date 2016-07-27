package me.manabreak.bootstrapgdx.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;
import com.badlogic.gdx.scenes.scene2d.Actor;

@PooledWeaver
public class ActorComponent extends Component {
    private Actor actor;

    public ActorComponent() {
        actor = new Actor();
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
