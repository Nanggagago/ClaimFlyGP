package dev.shadmage.claimflygp.commands;

import dev.shadmage.claimflygp.utils.FlightCheck;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

@AutoRegister
public class claimflyCommand extends SimpleCommand {
	public claimflyCommand() {
		super("claimfly|cfly|fly");
		setPermission("servercore.command.claimfly");
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		FlightCheck flightCheck = new FlightCheck();
		String checkResult = flightCheck.check(player);
		if (checkResult.equals(FlightCheck.FLIGHT_ALLOWED)) {
			boolean newFlightStatus = !(player.getAllowFlight());
			player.setAllowFlight(newFlightStatus);
			tellInfo("Flight mode has been " + (newFlightStatus ? "enabled." : "disabled."));
		} else {
			Common.tell(player, checkResult);
		}
	}
}
