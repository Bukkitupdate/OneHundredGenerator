package com.djdch.bukkit.onehundredgenerator.configuration;

import com.djdch.bukkit.onehundredgenerator.OneHundredGenerator;
import com.djdch.bukkit.onehundredgenerator.generator.ChunkProviderGenerate;

/**
 * 
 * @author DjDCH
 */
public class WorldConfiguration {
    /**
	 *
	 */
    protected OneHundredGenerator plugin;

    /**
	 *
	 */
    protected ChunkProviderGenerate chunkProvider;

    /**
	 *
	 */
    protected boolean init = false;

    /**
	 * 
	 */
    protected boolean levelStructures = true;

    /**
     * 
     * @param plug
     */
    public WorldConfiguration(OneHundredGenerator plug) {
        this.plugin = plug;
    }

    /**
     * 
     * @return
     */
    public OneHundredGenerator getPlugin() {
        return this.plugin;
    }

    /**
     * 
     * @return
     */
    public ChunkProviderGenerate getChunkProvider() {
        return this.chunkProvider;
    }

    /**
     * 
     * @param chunkProvider
     */
    public void setChunkProvider(ChunkProviderGenerate chunkProvider) {
        this.chunkProvider = chunkProvider;
    }

    /**
     * 
     * @return
     */
    public boolean isInit() {
        return this.init;
    }

    /**
     * 
     * @param isInit
     */
    public void setInit(boolean init) {
        this.init = init;
    }

    /**
     * 
     * @return
     */
    public boolean getLevelStructures() {
        return this.levelStructures;
    }

    /**
     * 
     * @param levelStructures
     */
    public void setLevelStructures(boolean levelStructures) {
        this.levelStructures = levelStructures;
    }
}
