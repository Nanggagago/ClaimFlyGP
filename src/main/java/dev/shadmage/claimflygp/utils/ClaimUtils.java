package dev.shadmage.claimflygp.utils;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ClaimUtils {

	public static Claim getClaim(Player player) {
		return getClaim(player.getLocation());
	}

	public static Claim getClaim(Location location) {
		return GriefPrevention.instance.dataStore.getClaimAt(location, true, null);
	}

	public static boolean hasAccessTrust(Player player) {
        /*This is kinda odd but GP allowAccess returns null when a player has
        trust and a string when they do not*/
		String string = getClaim(player).allowAccess(player);
		return string == null;
	}

	public static boolean hasAccessTrust(Player player, Location location) {
		String string = getClaim(location).allowAccess(player);
		return string == null;
	}

	public static boolean isClaimOwner(Player player) {
		return player.getName().equals(getClaim(player).getOwnerName());
	}

	public static boolean isClaimOwner(Player player, Location location) {
		return player.getName().equals(getClaim(location).getOwnerName());
	}

	public static boolean isInClaim(Player player) {
		return getClaim(player) != null;
	}

	public static boolean isClaimed(Location location) {
		return getClaim(location) != null;
	}

	public static boolean isInAdminClaim(Player player) {
		Claim claim = getClaim(player);

		if (claim != null) {
			return claim.isAdminClaim();
		} else {
			return false;
		}
	}

	public static boolean isAnAdminClaim(Location location) {
		return getClaim(location).isAdminClaim();
	}

}
