package me.manabreak.bootstrapgdx.components;

import com.artemis.Component;
import com.artemis.annotations.EntityId;
import com.artemis.annotations.PooledWeaver;

@PooledWeaver
public class Player extends Component {
    @EntityId
    int player = -1;

    public int getId() {
        return player;
    }
}
