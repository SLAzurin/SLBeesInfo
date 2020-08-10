package io.github.slazurin.slbeesinfo;

import io.github.slazurin.beesinfo.api.SLBeesInfoApi;
import io.github.slazurin.beesinfo.listeners.SLBeesListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SLBeesInfo extends JavaPlugin {
    private SLBeesInfoApi api;

    public SLBeesInfoApi getApi() {
        return api;
    } 
    
    @Override
    public void onEnable() {
        this.api = new SLBeesInfoApi(this);
        this.registerExecuters();
    }
    
    private void registerExecuters() {
        this.getServer().getPluginManager().registerEvents(new SLBeesListener(this), this);
    }
}
