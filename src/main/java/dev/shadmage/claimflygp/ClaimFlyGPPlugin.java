package dev.shadmage.claimflygp;

import dev.shadmage.claimflygp.events.FlightToggleListener;
import dev.shadmage.claimflygp.tasks.CheckFlyingPlayersTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.List;
import java.util.Objects;

public class ClaimFlyGPPlugin extends SimplePlugin {
	private CheckFlyingPlayersTask checkFlyingPlayersTask;

	public static final int TASK_TIMER_TICKS = 5;

	public static final String PERMISSION_CLAIMFLY_USE = "servercore.claimfly.use";
	public static final String PERMISSION_CLAIMFLY_ADMIN = "servercore.claimfly.claims.admin";
	public static final String PERMISSION_CLAIMFLY_OTHERS = "servercore.claimfly.claims.others";
	public static final String PERMISSION_CLAIMFLY_UNCLAIMED = "servercore.claimfly.claims.unclaimed";

	public static ClaimFlyGPPlugin getInstance() {
		return (ClaimFlyGPPlugin) SimplePlugin.getInstance();
	}

	@Override
	protected void onPluginStart() {
		/* AutoRegister this
		Common.registerEvents(new FlightToggleListener());
		*/


	}

	@Override
	protected void onReloadablesStart() {
		//Any timed task should be added to this block
		// Broadcasters/etc

		//Add in the claimfly check
		checkFlyingPlayersTask = new CheckFlyingPlayersTask();
		checkFlyingPlayersTask.runTaskTimer(this, 0, TASK_TIMER_TICKS);
	}
}
