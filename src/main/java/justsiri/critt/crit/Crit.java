package justsiri.critt.crit;

import justsiri.critt.crit.Commands.CheckCC;
import justsiri.critt.crit.Listener.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Crit extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getCommand("critical").setExecutor(new CheckCC());

        //구성 파일을 설정합니다.
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }
}
