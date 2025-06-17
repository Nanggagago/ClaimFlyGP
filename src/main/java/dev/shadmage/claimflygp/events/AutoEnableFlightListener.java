package dev.shadmage.claimflygp.events;

import dev.shadmage.claimflygp.settings.DebugValues;
import dev.shadmage.claimflygp.settings.PermissionData;
import dev.shadmage.claimflygp.settings.Settings;
import dev.shadmage.claimflygp.utils.ClaimUtils;
import dev.shadmage.claimflygp.utils.PlayerUtils;
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
public final class AutoEnableFlightListener implements Listener {

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

		if(fromClaim != toClaim) {
			if(player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_BYPASS)) {
				PlayerUtils.TogglePlayerFlight(player, true);
				return;
			}
			handleChangedClaimArea(player, fromClaim, toClaim);
		}
	}

	private void handleChangedClaimArea(Player player, Claim fromClaim, Claim toClaim) {
		// check if player will be moving out of a claim
		if(toClaim == null) {
			if(Settings.DEBUG_SECTIONS.contains(DebugValues.AUTO_ALLOW_FLIGHT))
				Common.log("[" + DebugValues.AUTO_ALLOW_FLIGHT + "] Player " + player.getName() + " has entered an unclaimed area ");
			PlayerUtils.TogglePlayerFlight(player, player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_UNCLAIMED));
			return;
		}
		// Check if this is an admin claim and if player has adminclaimfly permission
		if(toClaim.isAdminClaim()) {
			if(Settings.DEBUG_SECTIONS.contains(DebugValues.AUTO_ALLOW_FLIGHT))
				Common.log("[" + DebugValues.AUTO_ALLOW_FLIGHT + "] Player " + player.getName() + " has entered an Admin Claim");
			PlayerUtils.TogglePlayerFlight(player, player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_ADMIN));
			return;
		}
		// Check if this claim is owned by the player and if player has claimfly permission
		if(toClaim.getOwnerID().equals(player.getUniqueId())) {
			if(Settings.DEBUG_SECTIONS.contains(DebugValues.AUTO_ALLOW_FLIGHT))
				Common.log("[" + DebugValues.AUTO_ALLOW_FLIGHT + "] Player " + player.getName() + " has entered their own claim");
			PlayerUtils.TogglePlayerFlight(player, player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_USE));
			return;
		}
		// Check if player is trusted in the claim and has otherclaimfly permission
		if(player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_OTHERS)) {
			if(Settings.DEBUG_SECTIONS.contains(DebugValues.AUTO_ALLOW_FLIGHT))
				Common.log("[" + DebugValues.AUTO_ALLOW_FLIGHT + "] Player " + player.getName() + " has entered another players claim");
			PlayerUtils.TogglePlayerFlight(player, ClaimUtils.hasAccessTrust(player, toClaim));
			return;
		}
		// If somehow this function hasn't returned a value. Disable flight
		if(Settings.DEBUG_SECTIONS.contains(DebugValues.AUTO_ALLOW_FLIGHT))
			Common.log("[" + DebugValues.AUTO_ALLOW_FLIGHT + "] Disabling players flight. Should have been set already.");
		PlayerUtils.TogglePlayerFlight(player, false);
	}

}
