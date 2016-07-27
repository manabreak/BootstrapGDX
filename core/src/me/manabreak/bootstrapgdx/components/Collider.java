package me.manabreak.bootstrapgdx.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;

@PooledWeaver
public class Collider extends Component {

    // Mask bits; feel free to edit
    public static final int MASK_NONE = 1;
    public static final int MASK_PLAYER = 2;
    public static final int MASK_ENEMY = 4;
    public static final int MASK_BULLET = 8;
    public static final int MASK_COLLECTABLE = 16;
    public static final int MASK_WORLD = 32;
    public static final int MASK_ALL = -1;

    public int category = MASK_NONE;
    public int mask = MASK_ALL;

    public Collider() {

    }

    public Collider(int category, int mask) {
        this.category = category;
        this.mask = mask;
    }

    public void set(int category, int mask) {
        this.category = category;
        this.mask = mask;
    }

}
