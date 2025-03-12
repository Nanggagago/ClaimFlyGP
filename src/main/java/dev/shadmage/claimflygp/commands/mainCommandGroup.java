package dev.shadmage.claimflygp.commands;

import dev.shadmage.claimflygp.settings.PermissionData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.PermsCommand;
import org.mineacademy.fo.command.ReloadCommand;
import org.mineacademy.fo.command.SimpleCommandGroup;

@AutoRegister
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class mainCommandGroup extends SimpleCommandGroup {
	@Getter(value = AccessLevel.PRIVATE)
	private static final mainCommandGroup instance = new mainCommandGroup();

	@Override
	protected void registerSubcommands() {
		registerSubcommand(new ReloadCommand(PermissionData.Admin.PERMISSION_CLAIMFLY_RELOAD));
		registerSubcommand(new PermsCommand(PermissionData.class, PermissionData.Admin.PERMISSION_CLAIMFLY_PERMS));
	}

	@Override
	protected String getCredits() {
		return "&bVisit &dhttps://dirtydogsa.co.za";
	}

}
