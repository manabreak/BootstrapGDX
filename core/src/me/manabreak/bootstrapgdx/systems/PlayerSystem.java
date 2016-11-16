package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

import me.manabreak.bootstrapgdx.Res;
import me.manabreak.bootstrapgdx.SpriteActor;
import me.manabreak.bootstrapgdx.components.ActorComponent;
import me.manabreak.bootstrapgdx.components.GroupComponent;
import me.manabreak.bootstrapgdx.components.Player;

public class PlayerSystem extends IteratingSystem {

    private static final float LAYER_SIZE = 0.5f;

    private float vel = 0f;

    private ComponentMapper<ActorComponent> actors;
    private ComponentMapper<GroupComponent> groups;

    public PlayerSystem() {
        super(Aspect.all(Player.class, GroupComponent.class));
    }

    @Override
    protected void inserted(int id) {
        GroupComponent g = groups.get(id);

        for (int i = 0; i < 3; ++i) {
            SpriteActor a = new SpriteActor(Res.createSprite("white"));
            a.setSize(16f, 24f - i);
            a.setPosition(0f, i * LAYER_SIZE);
            a.setOrigin(8f, 12f);
            float v = 1f - (i * 0.05f);
            a.setColor(v, 0.3f, 0.2f, 1f);
            g.getGroup().addActor(a);
        }

        for (int i = 0; i < 3; ++i) {
            SpriteActor a = new SpriteActor(Res.createSprite("white"));
            a.setSize(14f, 16f - i);
            a.setPosition(0f, 4f + i * LAYER_SIZE);
            a.setOrigin(7f, 8f);
            g.getGroup().addActor(a);
        }

        g.getGroup().setOrigin(8f, 12f);
        g.getGroup().setPosition(20f, 16f);
    }

    @Override
    protected void process(int id) {
        GroupComponent g = groups.get(id);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            System.out.println("LEFT");
            g.getGroup().rotateBy(180f * world.delta);
            updateGroup(g);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            System.out.println("RIGHT");
            g.getGroup().rotateBy(-180f * world.delta);
            updateGroup(g);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            vel += world.delta * 10f;
            if (vel > 10f) vel = 10f;
        } else {
            vel *= 0.8f;
        }

        if (vel > 0f) {
            Group group = g.getGroup();
            group.moveBy(-MathUtils.sinDeg(group.getRotation()) * vel, MathUtils.cosDeg(group.getRotation()) * vel);
        }
    }

    private void updateGroup(GroupComponent g) {
        Group group = g.getGroup();

        float sin = MathUtils.sinDeg(group.getRotation());
        float cos = MathUtils.cosDeg(group.getRotation());

        SnapshotArray<Actor> children = group.getChildren();
        for (int i = 1, c = children.size; i < c; ++i) {
            Actor a = children.get(i);
            a.setPosition(sin * i * 1.414f * LAYER_SIZE, cos * i * LAYER_SIZE);
        }
    }
}
