package io.github.slazurin.beesinfo.api;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import io.github.slazurin.slbeesinfo.SLBeesInfo;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class SLBeesInfoApi {
    private final SLBeesInfo plugin;
    private final ChatColor[] colors;
    private int index;
    
    public SLBeesInfoApi(SLBeesInfo plugin) {
        this.plugin = plugin;
        this.index = 0;
        this.colors = new ChatColor[]{
            ChatColor.GOLD,
            ChatColor.WHITE,
        };
    }

    public ChatColor pushChatColor() {
        ChatColor c = this.colors[this.index];
        this.index++;
        
        if (this.index == this.colors.length) {
            this.index = 0;
        }
        return c;
    }
    
    public int getBeesCount(Block b) {
        if (b == null) {
            return -1;
        }
        
        if (!(b.getType() == Material.BEE_NEST || b.getType() == Material.BEEHIVE)) {
            return -1;
        }
        
        NBTTileEntity te = new NBTTileEntity(b.getState());
        return te.getCompoundList("Bees").size();
    }
    
    public int getBeesCount(ItemStack item) {
        if (item == null) {
            return -1;
        }
        
        if (item.getType() == Material.BEE_NEST || item.getType() == Material.BEEHIVE) {
            return -1;
        }
        
        NBTCompound c = new NBTItem(item).getCompound("BlockEntityTag");
        
        if (c == null) {
            return -1;
        }
        
        NBTCompoundList l = c.getCompoundList("Bees");
        
        if (l == null) {
            return -1;
        }
        
        return l.size();
    }
}
