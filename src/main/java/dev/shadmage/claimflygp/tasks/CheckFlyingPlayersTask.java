package dev.shadmage.claimflygp.tasks;

import dev.shadmage.claimflygp.ClaimFlyGPPlugin;
import dev.shadmage.claimflygp.settings.Settings;
import dev.shadmage.claimflygp.utils.FlightCheck;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.Remain;

public class CheckFlyingPlayersTask extends BukkitRunnable {
	final FlightCheck flightCheck = new FlightCheck();

	@Override
	public void run() {
		Common.setTellPrefix(Settings.ClaimFly.CLAIMFLY_CHAT_PREFIX);

		for (Player player : Remain.getOnlinePlayers()) {
			GameMode playerGamemode = player.getGameMode();
			if(Settings.ClaimFly.IGNORE_CREATIVE && playerGamemode == GameMode.CREATIVE) return;
			if(Settings.ClaimFly.IGNORE_SPECTATOR && playerGamemode == GameMode.SPECTATOR) return;
			if (player.isFlying()) {
				String checkResult;
				checkResult = flightCheck.check(player);
				if (!checkResult.equals(FlightCheck.FLIGHT_ALLOWED)) {
					try { //give player 'slow falling effect
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 1));
					} catch (Exception ex) {
						//could not add effect to player.... ignore and continue disabling flight
					}
					player.setFlying(false);
					player.setAllowFlight(false);
					Common.tellNoPrefix(player, "Your flight has been disabled");
				}
			}
		}

	}
}
