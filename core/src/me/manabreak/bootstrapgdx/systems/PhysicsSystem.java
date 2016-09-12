package me.manabreak.bootstrapgdx.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import me.manabreak.bootstrapgdx.SpriteActor;
import me.manabreak.bootstrapgdx.components.ActorComponent;
import me.manabreak.bootstrapgdx.components.PhysicsBody;
import me.manabreak.bootstrapgdx.factories.BodyFactory;

public class PhysicsSystem extends IteratingSystem implements ContactListener, MapSystem.Listener {

    public static final float SCALE = 24f;
    public static final float INV_SCALE = 1f / SCALE;

    private final World world;
    int count = 0;
    private ComponentMapper<ActorComponent> actors;
    private ComponentMapper<PhysicsBody> physicsBodies;
    private PolygonShape polygonShape;
    private StageSystem stageSystem;
    private List<Body> wallBodies = new ArrayList<>();
    private boolean debugDraw = false;

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

    public Body createBody(World world, BodyDef.BodyType type, float width, float height) {
        Body body = createBody(world, type);
        BodyFactory.createBoxFixture(polygonShape, width, height, body, false);
        return body;
    }

    public Body createWall(World world, float width, float height) {
        Body body = createBody(world, BodyDef.BodyType.StaticBody, width, height);
        return body;
    }

    public Body createSensor(World world, float width, float height) {
        Body body = createBody(world, BodyDef.BodyType.StaticBody);
        BodyFactory.createBoxFixture(polygonShape, width, height, body, true);
        return body;
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        PhysicsBody p0 = (PhysicsBody) bodyA.getUserData();
        PhysicsBody p1 = (PhysicsBody) bodyB.getUserData();

        if (p0 != null && p0.handler != null) {
            p0.handler.onBeginCollision(contact, p1);
        }

        if (p1 != null && p1.handler != null) {
            p1.handler.onBeginCollision(contact, p0);
        }

    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        PhysicsBody p0 = (PhysicsBody) bodyA.getUserData();
        PhysicsBody p1 = (PhysicsBody) bodyB.getUserData();

        if (p0 != null && p0.handler != null) {
            p0.handler.onEndCollision(contact, p1);
        }
        if (p1 != null && p1.handler != null) {
            p1.handler.onEndCollision(contact, p0);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void onMapLoaded(MapSystem mapSystem) {
        clearWalls();
        MapLayer layer = mapSystem.getCurrentMap().getLayers().get("P");
        for (MapObject obj : layer.getObjects()) {
            float x = (float) obj.getProperties().get("x");
            float y = (float) obj.getProperties().get("y");
            float w = (float) obj.getProperties().get("width");
            float h = (float) obj.getProperties().get("height");
            Body b = createWall(world, w, h);
            PhysicsBody physicsBody = new PhysicsBody();
            physicsBody.category = PhysicsBody.MASK_WORLD;
            physicsBody.tag = "WALL";
            b.setUserData(physicsBody);
            float bx = (x + w / 2f) * INV_SCALE;
            float by = (y + h / 2f) * INV_SCALE;
            b.setTransform(bx, by, 0f);
            wallBodies.add(b);
        }
    }

    private void clearWalls() {
        for (Body body : wallBodies) {
            world.destroyBody(body);
        }
        wallBodies.clear();
    }


}
