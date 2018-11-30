package me.taylor.taylorStaff.manager;

import me.taylor.taylorStaff.taylorStaff;
import me.taylor.taylorStaff.manager.managers.FrozenManager;
import me.taylor.taylorStaff.manager.managers.InventoryManager;
import me.taylor.taylorStaff.manager.managers.PlayerSnapshotManager;

public class ManagerHandler {

    private taylorStaff plugin;

    private InventoryManager inventoryManager;
    private FrozenManager frozenManager;
    private PlayerSnapshotManager playerSnapshotManager;

    public ManagerHandler(taylorStaff plugin) {
        this.plugin = plugin;
        loadManagers();
    }

    private void loadManagers() {
        inventoryManager = new InventoryManager(plugin);
        frozenManager = new FrozenManager(plugin);
        playerSnapshotManager = new PlayerSnapshotManager(plugin);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public FrozenManager getFrozenManager() {
        return frozenManager;
    }

    public PlayerSnapshotManager getPlayerSnapshotManager() {
        return playerSnapshotManager;
    }
}
