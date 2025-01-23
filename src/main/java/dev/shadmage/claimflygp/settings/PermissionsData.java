package dev.shadmage.claimflygp.settings;

import org.mineacademy.fo.command.annotation.Permission;

public class PermissionsData {
	@Permission
	public static final String PERMISSION_CLAIMFLY_USE = "claimfly.use";
	@Permission
	public static final String PERMISSION_CLAIMFLY_ADMIN = "claimfly.claims.admin";
	@Permission
	public static final String PERMISSION_CLAIMFLY_OTHERS = "claimfly.claims.others";
	@Permission
	public static final String PERMISSION_CLAIMFLY_UNCLAIMED = "claimfly.claims.unclaimed";
}
