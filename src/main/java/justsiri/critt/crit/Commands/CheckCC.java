package justsiri.critt.crit.Commands;

import justsiri.critt.crit.Listener.Events;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckCC implements CommandExecutor {

    Events ev = new Events();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("당신의 크리티컬 기회는 " + ev.crit_chance + "% 입니다");
            if (player.isOp()) {
                player.sendMessage("하지만 당신은 관리자이기 때문에, 항상 크리티컬 데미지가 들어갑니다.");
            }

        }
        return true;
    }
}