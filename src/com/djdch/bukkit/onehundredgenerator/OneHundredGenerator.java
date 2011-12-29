package com.djdch.bukkit.onehundredgenerator;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.generator.ChunkGenerator;

import com.djdch.bukkit.onehundredgenerator.configuration.WorldConfiguration;
import com.djdch.bukkit.onehundredgenerator.generator.ChunkProviderGenerate;
import com.djdch.bukkit.onehundredgenerator.listener.WorldListener;
import com.djdch.bukkit.onehundredgenerator.mc100.WorldChunkManager;
import com.djdch.bukkit.utils.Logger;

/**
 * Main class of the <b>OneHundredGenerator</b> plugin for Bukkit.
 * <p>
 * Minecraft 1.0.0 terrain generator plugin for Bukkit.
 * 
 * @author DjDCH
 */
public class OneHundredGenerator extends JavaPlugin {
    /**
     * Contains the Logger instance.
     */
    protected final Logger logger = new Logger();

    /**
     * Contains the deathListener instance.
     */
    protected final WorldListener worldListener = new WorldListener(this);

    /**
	 *
	 */
    protected final HashMap<String, WorldConfiguration> worldsSettings = new HashMap<String, WorldConfiguration>();

    /**
     * Method execute when the plugin is enable.
     */
    public void onEnable() {
        this.logger.setPrefix(getDescription().getName());

        // Register the plugin events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.WORLD_INIT, this.worldListener, Event.Priority.High, this);

        this.logger.info("Version " + getDescription().getVersion() + " enable.");
    }

    /**
     * Method execute when the plugin is disable.
     */
    public void onDisable() {
        this.logger.info("Version " + getDescription().getVersion() + " disable.");
    }

    /**
	 *
	 */
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        if (this.worldsSettings.containsKey(worldName)) {
            this.logger.info("Enabled for '" + worldName + "'");
            return ((WorldConfiguration) this.worldsSettings.get(worldName)).chunkProvider;
        }

        WorldConfiguration worldSetting = new WorldConfiguration(this);
        this.worldsSettings.put(worldName, worldSetting);

        ChunkProviderGenerate prov = new ChunkProviderGenerate(worldSetting);

        this.logger.info("Enabled for '" + worldName + "'");
        return prov;
    }

    /**
     * 
     * @param world
     */
    public void WorldInit(World world) {
        if (this.worldsSettings.containsKey(world.getName())) {
            WorldConfiguration worldSetting = (WorldConfiguration) this.worldsSettings.get(world.getName());
            if (worldSetting.isInit) {
                return;
            }
            net.minecraft.server.World workWorld = ((CraftWorld) world).getHandle();

            WorldChunkManager chunkManager = new WorldChunkManager(workWorld);
            workWorld.worldProvider.b = chunkManager;
            worldSetting.chunkProvider.Init(workWorld, workWorld.getSeed(), false);
            worldSetting.isInit = true;

            System.out.println("PTM173: world seed is " + workWorld.getSeed());
        }
    }

    /**
     * Accessor who return the logger instance.
     * 
     * @return Logger instance.
     */
    public Logger getLogger() {
        return this.logger;
    }
}
