package me.manabreak.bootstrapgdx.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;
import com.badlogic.gdx.scenes.scene2d.Group;

@PooledWeaver
public class GroupComponent extends Component {

    private Group group;

    public GroupComponent() {
        this.group = new Group();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
