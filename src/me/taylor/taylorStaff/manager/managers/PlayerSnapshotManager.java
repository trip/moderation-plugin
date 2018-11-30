package me.taylor.taylorStaff.manager.managers;

import me.taylor.taylorStaff.taylorStaff;
import me.taylor.taylorStaff.manager.Manager;
import me.taylor.taylorStaff.player.PlayerSnapshot;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerSnapshotManager extends Manager {

    private Map<UUID, PlayerSnapshot> playerSnapshotMap = new HashMap<>();

    public PlayerSnapshotManager(taylorStaff plugin) {
        super(plugin);
    }

    public void takeSnapshot(Player player) {
        playerSnapshotMap.put(player.getUniqueId(), new PlayerSnapshot(player));
    }

    public void restorePlayer(Player player) {
        PlayerSnapshot playerSnapshot = getSnapshot(player);
        if(playerSnapshot != null) {
            player.getInventory().clear();
            player.getInventory().setContents(playerSnapshot.getMainContent());
            player.getInventory().setArmorContents(playerSnapshot.getArmorContent());
            player.setWalkSpeed(playerSnapshot.getWalkSpeed());
            player.addPotionEffects(playerSnapshot.getPotionEffects());
            player.updateInventory();
            removeSnapshot(player);
        }
    }

    private void removeSnapshot(Player player) {
        playerSnapshotMap.remove(player.getUniqueId());
    }

    private PlayerSnapshot getSnapshot(Player player) {
        return playerSnapshotMap.get(player.getUniqueId());
    }
}
