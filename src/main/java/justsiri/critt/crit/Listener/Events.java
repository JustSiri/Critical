package justsiri.critt.crit.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.util.Random;

public class Events implements Listener {

    Random r = new Random();
    public double crit_chance = 0;

    public boolean OverCritChance(double currentCC) {
        if (currentCC > 99) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        int p = r.nextInt(100);
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (damager instanceof Player) {
            Player player = (Player) e.getDamager();
            if (!(e.getEntity() instanceof ArmorStand)) {
                ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(victim.getLocation(), EntityType.ARMOR_STAND);
                hologram.setVisible(false);
                hologram.setCustomNameVisible(true);
                if (p <= crit_chance || player.isOp()) {
                    e.setDamage(e.getDamage() * 1.5);
                    hologram.setCustomName(ChatColor.RED + "✧ " + e.getDamage());
                } else {
                    hologram.setCustomName(ChatColor.WHITE + String.valueOf(e.getDamage()));
                }
                hologram.setHealth(0);
            }
        }
    }

    @EventHandler
    public void getExp(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        if (event.getNewLevel() >= 1) {
            if (OverCritChance(crit_chance)) {
                player.sendMessage("이미 크리티컬 기회가 100%여서, 더 이상 레벨업 할 수 없습니다!");
            } else {
                player.sendMessage("당신은 레벨업을 하였고, 크리티컬 기회가 0.5 올랐습니다!");
                crit_chance = crit_chance + 0.5;
                player.sendMessage("크리티컬 기회: " + crit_chance + "/100");
            }
        }
    }
}
