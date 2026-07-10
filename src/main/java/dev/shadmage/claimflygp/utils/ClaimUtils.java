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
        Claim claim = getClaim(player);
        if (claim == null) return false;

        Supplier<String> supplier = claim.checkPermission(player, ClaimPermission.Access, null);
        return supplier == null;
    }

    public static boolean hasAccessTrust(Player player, Location location) {
        Claim claim = getClaim(location);
        if (claim == null) return false;

        Supplier<String> supplier = claim.checkPermission(player, ClaimPermission.Access, null);
        return supplier == null;
    }

    public static boolean hasAccessTrust(Player player, @Nonnull Claim claim) {
        Supplier<String> supplier = claim.checkPermission(player, ClaimPermission.Access, null);
        return supplier == null;
    }

    public static boolean isClaimOwner(Player player) {
        Claim claim = getClaim(player);
        return claim != null && player.getName().equals(claim.getOwnerName());
    }

    public static boolean isClaimOwner(Player player, Location location) {
        Claim claim = getClaim(location);
        return claim != null && player.getName().equals(claim.getOwnerName());
    }

    public static boolean isInClaim(Player player) {
        return getClaim(player) != null;
    }

    public static boolean isClaimed(Location location) {
        return getClaim(location) != null;
    }

    public static boolean isInAdminClaim(Player player) {
        Claim claim = getClaim(player);
        return claim != null && claim.isAdminClaim();
    }

    public static boolean isAnAdminClaim(Location location) {
        Claim claim = getClaim(location);
        return claim != null && claim.isAdminClaim();
    }

    public static boolean isInTrustedClaim(Player player, Location location) {
        Claim claim = getClaim(location);

        if (claim == null) {
            return false;
        }

        return claim.checkPermission(player, ClaimPermission.Access, null) == null;
    }
}
    public static boolean hasAccessTrust(Player player, @Nonnull Claim claim) {
        Supplier<String> supplier = claim.checkPermission(player, ClaimPermission.Access, null);
        return supplier == null;
    }

    public static boolean isClaimOwner(Player player) {
        Claim claim = getClaim(player);
        return claim != null && player.getName().equals(claim.getOwnerName());
    }

    public static boolean isClaimOwner(Player player, Location location) {
        Claim claim = getClaim(location);
        return claim != null && player.getName().equals(claim.getOwnerName());
    }

    public static boolean isInClaim(Player player) {
        return getClaim(player) != null;
    }

    public static boolean isClaimed(Location location) {
        return getClaim(location) != null;
    }

    public static boolean isInAdminClaim(Player player) {
        Claim claim = getClaim(player);
        return claim != null && claim.isAdminClaim();
    }

    public static boolean isAnAdminClaim(Location location) {
        Claim claim = getClaim(location);
        return claim != null && claim.isAdminClaim();
    }

    public static boolean isInTrustedClaim(Player player, Location location) {
        Claim claim = getClaim(location);

        if (claim == null) {
            return false;
        }

        return claim.checkPermission(player, ClaimPermission.Access, null) == null;
    }
}	public static boolean isClaimOwner(Player player) {
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
