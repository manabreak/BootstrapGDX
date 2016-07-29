package me.manabreak.bootstrapgdx.factories;

import com.artemis.BaseSystem;

/**
 * Base class for all passive factories.
 * <p/>
 * Making factories as systems helps with the dependency injection
 * and keeps everything "contained" neatly in the world. This way,
 * every other system gets the required factories injected.
 */
public abstract class BaseFactory extends BaseSystem {

    /**
     * Constructs a new factory
     */
    public BaseFactory() {

    }

    /**
     * Disables this system (i.e. makes it passive) calls the initialization functions.
     */
    @Override
    protected final void initialize() {
        setEnabled(false);
        createArchetypes();
    }

    /**
     * Creates the archetypes for the factory.
     */
    protected abstract void createArchetypes();

    @Override
    protected void processSystem() {
        // Do nothing.
    }
}
