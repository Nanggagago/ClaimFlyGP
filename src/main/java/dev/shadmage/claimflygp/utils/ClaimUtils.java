package dev.shadmage.claimflygp.utils;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ClaimUtils {

	public static Claim getClaim(Player player) {
		return getClaim(player.getLocation());
	}

	public static Claim getClaim(Location location) {
		return GriefPrevention.instance.dataStore.getClaimAt(location, true, null);
	}

	public static boolean hasAccessTrust(Player player) {
        Supplier<String> supplier = getClaim(player).checkPermission(player, ClaimPermission.Access, null);
		return supplier == null;
	}

	public static boolean hasAccessTrust(Player player, Location location) {
		Supplier<String> supplier = getClaim(location).checkPermission(player, ClaimPermission.Access, null);
		return supplier == null;
	}

	public static boolean hasAccessTrust(Player player, @Nonnull Claim claim) {
		Supplier<String> supplier = claim.checkPermission(player, ClaimPermission.Access, null);
		return supplier == null;
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
