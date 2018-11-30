package me.taylor.taylorStaff.manager.managers;

import me.taylor.taylorStaff.taylorStaff;
import me.taylor.taylorStaff.manager.Manager;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager extends Manager {

    private Inventory frozenInv;

    public InventoryManager(taylorStaff plugin) {
        super(plugin);
        initiateFrozenInv();
    }

    private void initiateFrozenInv() {
        frozenInv = plugin.getServer().createInventory(null, 9, "You have been Frozen.");
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = paper.getItemMeta();
        List<String> lores = new ArrayList<>();
        lores.add(0, "DO NOT LOG OUT YOU HAVE 5 MINUTES");
        lores.add(1, "LOGGING OUT WILL RESULT IN A BAN");
        lores.add(2, "CONNECT TO [ts.nasa.ga] IN TEAMSPEAK");
        itemMeta.setLore(lores);
        itemMeta.setDisplayName("ts.nasa.ga");
        paper.setItemMeta(itemMeta);

        frozenInv.setItem(4, paper);
    }

    public Inventory getFrozenInv() {
        return frozenInv;
    }


}
