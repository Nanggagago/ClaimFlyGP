package dev.shadmage.claimflygp.events;

import dev.shadmage.claimflygp.settings.PermissionsData;
import dev.shadmage.claimflygp.settings.Settings;
import dev.shadmage.claimflygp.utils.ClaimUtils;
import io.papermc.paper.event.entity.EntityMoveEvent;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;

@AutoRegister
public final class ClaimEntryEvent implements Listener {

	@EventHandler
	private void AutoEnableFlightOnClaimEnter(PlayerMoveEvent event) {
		if(event.isCancelled()) return;
		if(!Settings.ClaimFly.AUTO_ALLOW_FLIGHT) return;

		Player player = event.getPlayer();
		GameMode playerGameMode = player.getGameMode();

		if(Settings.ClaimFly.IGNORE_CREATIVE && playerGameMode == GameMode.CREATIVE) return;
		if(Settings.ClaimFly.IGNORE_SPECTATOR && playerGameMode == GameMode.SPECTATOR) return;

		Location locTo = event.getTo();
		Location locFrom = event.getFrom();
		Claim fromClaim = ClaimUtils.getClaim(locFrom);
		Claim toClaim = ClaimUtils.getClaim(locTo);




		// check if player will be moving out of a claim
		if(toClaim == null) {
			// Check if player was previously in a claim
			if(fromClaim == null) return;

			// Check for permissions to fly in unclaimed space
			if(player.hasPermission(PermissionsData.PERMISSION_CLAIMFLY_UNCLAIMED)) {
				EnablePlayerFlight(player);
			} else {
				DisablePlayerFlight(player);
			}
			return;
		}

		// Player is moving into a claim
		// Check if they were previously in the same claim
		if(fromClaim != null) {
			if(fromClaim == toClaim) return;
		}

		// Check if this is an admin claim and if player has adminclaimfly permission
		if(toClaim.isAdminClaim() && player.hasPermission(PermissionsData.PERMISSION_CLAIMFLY_ADMIN)) {
			EnablePlayerFlight(player);
			return;
		}

		// Check if this claim is owned by the player and if player has claimfly permission
		if(ClaimUtils.isClaimOwner(player, locTo) && player.hasPermission(PermissionsData.PERMISSION_CLAIMFLY_USE)) {
			EnablePlayerFlight(player);
			return;
		}

		// Check if player is trusted in the claim and has otherclaimfly permission
		if(player.hasPermission(PermissionsData.PERMISSION_CLAIMFLY_OTHERS)) {
			if(ClaimUtils.hasAccessTrust(player, locTo)) {
				EnablePlayerFlight(player);
			}
		}

		DisablePlayerFlight(player);
	}

	private void EnablePlayerFlight(Player player) {
		if(player.getAllowFlight()) return;
		player.setAllowFlight(true);
		Common.tellNoPrefix(player, Common.colorize("&8&l[&9&li&8&l]&7 " + Settings.Messages.FLIGHT_ENABLED));
	}

	private void DisablePlayerFlight(Player player) {
		if(!player.getAllowFlight()) return;
		player.setAllowFlight(false);
		Common.tellNoPrefix(player, Common.colorize("&8&l[&9&li&8&l]&7 " + Settings.Messages.FLIGHT_DISABLED));
	}
}
