package me.manabreak.bootstrapgdx.components;

import com.artemis.Component;
import com.artemis.annotations.EntityId;
import com.artemis.annotations.PooledWeaver;
import com.badlogic.gdx.physics.box2d.Body;

@PooledWeaver
public class PhysicsBody extends Component {

    public static final int MASK_ALL = -1;
    public static final int MASK_NONE = 1;
    public static final int MASK_PLAYER = 1 << 1;
    public static final int MASK_ENEMY = 1 << 2;
    public static final int MASK_BULLET = 1 << 3;
    public static final int MASK_COLLECTABLE = 1 << 4;
    public static final int MASK_WORLD = 1 << 5;

    @EntityId
    public int id;
    public Body body;
    public int category = PhysicsBody.MASK_NONE;
    public int mask = PhysicsBody.MASK_ALL;
    public String tag = "";
}
