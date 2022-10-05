package pene.gc.nuzlocke;

import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.game.ReceivePacketEvent;

/**
 * The Grasscutter plugin template.
 * This is the main class for the plugin.
 */
public final class NuzlockePlugin extends Plugin {
    /* Turn the plugin into a singleton. */
    private static NuzlockePlugin instance;

    /**
     * Gets the plugin instance.
     * @return A plugin singleton.
     */
    public static NuzlockePlugin getInstance() {
        return instance;
    }

    /**
     * This method is called immediately after the plugin is first loaded into system memory.
     */
    @Override public void onLoad() {
        // Set the plugin instance.
        instance = this;
    }

    /**
     * This method is called before the servers are started, or when the plugin enables.
     */
    @Override public void onEnable() {
        // Register event listeners.
        new EventHandler<>(ReceivePacketEvent.class)
                .priority(HandlerPriority.LOW)
                .listener(EventListeners::onDeath)
                .register(this);

        // Log a plugin status message.
        this.getLogger().info("The Nuzlocke has been enabled.");
    }
}
