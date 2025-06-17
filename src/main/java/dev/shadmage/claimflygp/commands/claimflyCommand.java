package dev.shadmage.claimflygp.commands;

import dev.shadmage.claimflygp.settings.PermissionData;
import dev.shadmage.claimflygp.settings.Settings;
import dev.shadmage.claimflygp.utils.FlightCheck;
import dev.shadmage.claimflygp.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

@AutoRegister
public final class claimflyCommand extends SimpleCommand {
	public claimflyCommand() {
		super("claimfly|cfly|fly");
		setPermission(PermissionData.PERMISSION_CLAIMFLY_USE);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		FlightCheck flightCheck = new FlightCheck();
		String checkResult = flightCheck.check(player);

		if (checkResult.equals(FlightCheck.FLIGHT_ALLOWED)) {
			boolean newFlightStatus = !(player.getAllowFlight());
			PlayerUtils.TogglePlayerFlight(player, newFlightStatus);
		} else {
			PlayerUtils.PlayerNotification(player, checkResult);
		}
	}
}
