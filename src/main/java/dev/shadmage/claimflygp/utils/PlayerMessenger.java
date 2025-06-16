package dev.shadmage.claimflygp.utils;

import dev.shadmage.claimflygp.settings.Settings;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.Remain;

public final class PlayerMessenger {

	public static void PlayerNotification(Player player, String message){
		Common.runLater(5, ()-> {
			if (Settings.ClaimFly.MESSAGE_ON_ACTIONBAR) {
				Remain.sendActionBar(player, Common.colorize(message));
			} else {
				Common.tellNoPrefix(player, Common.colorize(message));
			}
		});
	}
}
