package de.spexmc.mc.votesystem.listener;

import de.spexmc.mc.votesystem.util.objectmanager.VoteManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Lara on 26.02.2019 for votesystem
 */
public class TestEvent implements Listener {

  @EventHandler
  public void onClick(InventoryClickEvent clickEvent) {
    final ItemStack currentItem = clickEvent.getCurrentItem();
    if (currentItem.getItemMeta().getDisplayName().contains("Vote")) {
      new VoteManager((Player) clickEvent.getWhoClicked());
    }
  }
}
