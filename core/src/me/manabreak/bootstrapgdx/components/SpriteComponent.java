package me.manabreak.bootstrapgdx.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;
import com.badlogic.gdx.graphics.g2d.Sprite;

import me.manabreak.bootstrapgdx.Res;

@PooledWeaver
public class SpriteComponent extends Component {

    private Sprite sprite;


    public SpriteComponent() {

    }

    public SpriteComponent(String spriteName) {
        this.sprite = Res.createSprite(spriteName);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
