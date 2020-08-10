package io.github.slazurin.beesinfo.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import io.github.slazurin.slbeesinfo.SLBeesInfo;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; 
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class SLBeesListener implements Listener {
    private final SLBeesInfo plugin;
    
    public SLBeesListener(SLBeesInfo plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        ItemStack holdingItem = event.getPlayer().getInventory().getItemInMainHand();
        
        // Check if using main hand to right click, while holding no item
        if (EquipmentSlot.HAND == event.getHand() && event.getAction() == Action.RIGHT_CLICK_BLOCK && holdingItem.getType() == Material.AIR) {
            Block b = event.getClickedBlock();
            if (b != null) {
                if (b.getType() == Material.BEEHIVE || b.getType() == Material.BEE_NEST) {
                    int count = this.plugin.getApi().getBeesCount(event.getClickedBlock());
                    if (count >= 0) {
                        event.getPlayer().sendMessage(this.plugin.getApi().pushChatColor() + "Bees inside: " + count);
                    }
                }
            }
        }
        
        // Check if right clicking air with either bee nest/hive
        if (event.getAction() == Action.RIGHT_CLICK_AIR && holdingItem.getType() == Material.BEE_NEST || holdingItem.getType() == Material.BEEHIVE) {
            int count = this.plugin.getApi().getBeesCount(holdingItem);
            if (count >= 0) {
                event.getPlayer().sendMessage(this.plugin.getApi().pushChatColor() + "Bees inside: " + count);
            }
        }
    }
}
