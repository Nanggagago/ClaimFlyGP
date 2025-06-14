package dev.shadmage.claimflygp.events;

import dev.shadmage.claimflygp.settings.Settings;
import dev.shadmage.claimflygp.utils.FlightCheck;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.events.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.remain.CompPotionEffectType;
import org.mineacademy.fo.remain.Remain;

@AutoRegister
public final class GPEventListener implements Listener {
	FlightCheck flightCheck = new FlightCheck();

	@EventHandler
	public void onClaimDelete(ClaimDeletedEvent claimDeletedEvent){
		Common.logFramed("A claim has been deleted");
		CheckFlyingPlayers();
	}

	@EventHandler
	public void onClaimCreate(ClaimCreatedEvent claimCreatedEvent){
		CheckFlyingPlayers();
	}

	@EventHandler
	public void onClaimExtend(ClaimResizeEvent claimResizeEvent){
		CheckFlyingPlayers();
	}

	@EventHandler
	public void onClaimTransferEvent(ClaimTransferEvent claimTransferEvent){
		CheckFlyingPlayers();
	}

	@EventHandler
	public void onTrustChangedEvent(TrustChangedEvent trustChangedEvent){
		CheckFlyingPlayers();
	}

	private void CheckFlyingPlayers(){
		Common.runLater(() ->{
			for(Player player : Remain.getOnlinePlayers()){
				if(player.isFlying())
					CheckPlayerFlight(player);
			}
		});
	}

	private void CheckPlayerFlight(Player player) {
		String checkResult = flightCheck.check(player);
		if (!checkResult.equals(FlightCheck.FLIGHT_ALLOWED)) {
			player.addPotionEffect(new PotionEffect(CompPotionEffectType.SLOW_FALLING, 200, 1));
			player.setFlying(false);
			if(Settings.ClaimFly.MESSAGE_ON_ACTIONBAR) {
				Remain.sendActionBar(player, Common.colorize(Settings.Messages.FLIGHT_DISABLED));
			} else {
				Common.tellNoPrefix(player, Common.colorize(Settings.Messages.FLIGHT_DISABLED));
			}
		}
	}



}
