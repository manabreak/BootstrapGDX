package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import me.manabreak.bootstrapgdx.SpriteActor;
import me.manabreak.bootstrapgdx.components.ActorComponent;
import me.manabreak.bootstrapgdx.components.PhysicsBody;

public class PhysicsSystem extends IteratingSystem implements ContactListener {

    public static final float SCALE = 24f;
    public static final float INV_SCALE = 1f / SCALE;

    private final World world;
    int count = 0;
    private ComponentMapper<ActorComponent> actors;
    private ComponentMapper<PhysicsBody> physicsBodies;

    private PolygonShape polygonShape;

    public PhysicsSystem() {
        super(Aspect.all(PhysicsBody.class));
        world = new World(new Vector2(0f, -15f), false);
        world.setContactListener(this);
        polygonShape = new PolygonShape();
    }

    @Override
    protected void dispose() {
        polygonShape.dispose();
        super.dispose();
    }

    @Override
    protected void begin() {
        world.step(getWorld().getDelta(), 6, 2);
    }

    public World getPhysicsWorld() {
        return world;
    }

    @Override
    protected void inserted(int id) {
        count++;
    }

    @Override
    protected void removed(int id) {
        count--;
        PhysicsBody physicsBody = physicsBodies.get(id);
        if (physicsBody != null && physicsBody.body != null) {
            world.destroyBody(physicsBody.body);
        }
    }

    @Override
    protected void process(int id) {
        if (actors.has(id)) {
            SpriteActor a = actors.get(id).getActor();
            Body body = physicsBodies.get(id).body;
            a.setPosition(body.getPosition().x * SCALE, body.getPosition().y * SCALE);
        }
    }

    private Body createBody(World world, BodyDef.BodyType type) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        return world.createBody(bodyDef);
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        PhysicsBody p0 = (PhysicsBody) bodyA.getUserData();
        PhysicsBody p1 = (PhysicsBody) bodyB.getUserData();
    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        PhysicsBody p0 = (PhysicsBody) bodyA.getUserData();
        PhysicsBody p1 = (PhysicsBody) bodyB.getUserData();
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
