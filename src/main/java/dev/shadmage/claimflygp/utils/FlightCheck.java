package dev.shadmage.claimflygp.utils;

import dev.shadmage.claimflygp.settings.DebugValues;
import dev.shadmage.claimflygp.settings.PermissionData;
import dev.shadmage.claimflygp.settings.Settings;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.CompParticle;

public class FlightCheck {
	public final static String FLIGHT_ALLOWED = "allow";


	public String check(Player player) {
		if (!player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_USE)) {
			return Settings.Messages.NO_FLY;
		}

		if(!player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_BYPASS)) {
			if(Settings.DEBUG_SECTIONS.contains(DebugValues.FLIGHTCHECK_COMMAND))
				Common.logFramed(
						"Owner: " + ClaimUtils.isClaimOwner(player),
						"hasTrust: " + ClaimUtils.hasAccessTrust(player),
						"FlyOthersPerm: " + player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_OTHERS)
				);

			if (ClaimUtils.isInAdminClaim(player)) { //Player is in a admin claim
				if (!(ClaimUtils.hasAccessTrust(player) || player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_ADMIN))) {
					return Settings.Messages.NO_FLY_THIS_CLAIM.replace("%ClaimOwner%", ClaimUtils.getClaim(player).getOwnerName());
				}
			} else if (ClaimUtils.isInClaim(player)) { //Player is in a player claim
				if(!ClaimUtils.isClaimOwner(player)) {
					//Player not in their own claim
					//Check if they have perms to fly in other player claims
					if(!player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_OTHERS)){
						return Settings.Messages.NO_FLY_OUTSIDE_CLAIM;
					}
					// Check if they have trust in this claim
					if(!ClaimUtils.hasAccessTrust(player)){
						return Settings.Messages.NO_FLY_THIS_CLAIM.replace("%ClaimOwner%", ClaimUtils.getClaim(player).getOwnerName());
					}
				}
			} else if (!player.hasPermission(PermissionData.PERMISSION_CLAIMFLY_UNCLAIMED)) { // player isnt in a claim...
				return Settings.Messages.NO_FLY_OUTSIDE_CLAIM;
			}
			showFlightBoundaries(player);
		}

		return FLIGHT_ALLOWED;
	}

	private void showFlightBoundaries(Player player) {

		if(player.getGameMode() == GameMode.SURVIVAL) {
			Claim claimAtPlayer = ClaimUtils.getClaim(player);
			Location playerLoc = player.getLocation();

			if (claimAtPlayer != null) {
				Location[] locs = new Location[4];
				locs[0] = new Location(player.getWorld(), playerLoc.getBlockX() + .5, playerLoc.getBlockY() + 2, claimAtPlayer.getLesserBoundaryCorner().getBlockZ() + .5);
				locs[1] = new Location(player.getWorld(), claimAtPlayer.getLesserBoundaryCorner().getBlockX() + .5, playerLoc.getBlockY() + 2, playerLoc.getBlockZ() + .5);
				locs[2] = new Location(player.getWorld(), playerLoc.getBlockX() + .5, playerLoc.getBlockY() + 2, claimAtPlayer.getGreaterBoundaryCorner().getBlockZ() + .5);
				locs[3] = new Location(player.getWorld(), claimAtPlayer.getGreaterBoundaryCorner().getBlockX() + .5, playerLoc.getBlockY() + 2, playerLoc.getBlockZ() + .5);

				for (int i = 0; i <= 3; i++) {
					int checkDistance = 6;
					if (playerLoc.distance(locs[i]) <= checkDistance) {
						CompParticle.COMPOSTER.spawn(player, locs[i]);
						CompParticle.COMPOSTER.spawn(player, locs[i].subtract(0, 1, 0));
						CompParticle.COMPOSTER.spawn(player, locs[i].add(0, 1, 0));
					}
				}


			}
		}
	}
}
