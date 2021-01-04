package justsiri.critt.crit.Listener;

import justsiri.critt.crit.Crit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class PlayerJoin implements Listener {

    FileConfiguration userconfig = null;
    Crit plugin;

    Events ev;

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerJoins(PlayerJoinEvent event) throws IOException {
        File userfile = new File(plugin.getDataFolder() + File.separator + "userdata" + File.separator + event.getPlayer().getName() + ".yml");
        if (!userfile.exists()) {
            userfile.createNewFile();
            userconfig = YamlConfiguration.loadConfiguration(userfile);
            userconfig.set("critical-chance", ev.crit_chance);
            userconfig.save(userfile);
        }
    }
}
