package me.manabreak.bootstrapgdx.systems;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

@Wire
public class PhysicsRenderingSystem extends BaseSystem {

    private static final float CONTACT_SIZE = 0.01f;
    private PhysicsSystem physicsSystem;
    private StageSystem stageSystem;
    private Box2DDebugRenderer debugRenderer;

    public PhysicsRenderingSystem() {
        debugRenderer = new DebugRenderer(true, false, false, false, false, true);
    }

    @Override
    protected void processSystem() {

        Matrix4 m = stageSystem.getCamera().combined.cpy();
        m.scl(PhysicsSystem.SCALE);
        debugRenderer.render(physicsSystem.getPhysicsWorld(), m);
    }

    private static class DebugRenderer extends Box2DDebugRenderer {

        public DebugRenderer(boolean drawBodies, boolean drawJoints, boolean drawAABBs, boolean drawInactiveBodies, boolean drawVelocities, boolean drawContacts) {
            super(drawBodies, drawJoints, drawAABBs, drawInactiveBodies, drawVelocities, drawContacts);


        }

        @Override
        public void render(World world, Matrix4 projMatrix) {
            super.render(world, projMatrix);

            if (isDrawContacts()) {
                renderer.begin(ShapeRenderer.ShapeType.Line);
                for (Contact contact : world.getContactList()) {
                    drawSquareContact(contact);
                }
                renderer.end();
            }
        }

        private void drawSquareContact(Contact contact) {
            WorldManifold m = contact.getWorldManifold();
            for (Vector2 p : m.getPoints()) {
                renderer.setColor(0.5f, 1f, 0.5f, 1f);
                renderer.rect(p.x - CONTACT_SIZE, p.y - CONTACT_SIZE, CONTACT_SIZE * 2f, CONTACT_SIZE * 2f);
            }
        }
    }
}
