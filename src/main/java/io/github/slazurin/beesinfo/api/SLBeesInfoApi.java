package io.github.slazurin.beesinfo.api;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class SLBeesInfoApi {
    private final ChatColor[] colors;
    private final Map<UUID, Integer> playerIndices;
    
    public SLBeesInfoApi() {
        this.playerIndices = new HashMap<>();
        this.colors = new ChatColor[]{
            ChatColor.GOLD,
            ChatColor.WHITE,
        };
    }

    public ChatColor pushChatColor(UUID uid) {
        if (!this.playerIndices.containsKey(uid)) {
            this.playerIndices.put(uid, 0);
        }
        int index = this.playerIndices.get(uid);
        ChatColor c = this.colors[index];
        index+=1;
        if (index == this.colors.length) {
            index = 0;
        }
        this.playerIndices.replace(uid, index);

        return c;
    }
    
    public void removeUid(UUID uid) {
        if (this.playerIndices.containsKey(uid)) {
            this.playerIndices.remove(uid);
        }
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
        
        if (!(item.getType() == Material.BEE_NEST || item.getType() == Material.BEEHIVE)) {
            return -1;
        }
        
        NBTCompound c = new NBTItem(item).getCompound("BlockEntityTag");
        
        // No nbt tags, basically no bees inside
        if (c == null) {
            return 0;
        }
        
        NBTCompoundList l = c.getCompoundList("Bees");
        
        if (l == null) {
            return -1;
        }
        return l.size();
    }
}
