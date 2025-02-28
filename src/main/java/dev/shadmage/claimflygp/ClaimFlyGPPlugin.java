package dev.shadmage.claimflygp;

import dev.shadmage.claimflygp._external.Metrics;
import dev.shadmage.claimflygp._external.SpigotUpdateChecker;
import dev.shadmage.claimflygp.settings.Settings;
import dev.shadmage.claimflygp.tasks.CheckFlyingPlayersTask;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;

public class ClaimFlyGPPlugin extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		Common.setLogPrefix(Settings.LOG_PREFIX);
		Common.setTellPrefix(Settings.ClaimFly.CLAIMFLY_CHAT_PREFIX);
		Metrics metrics = new Metrics(this, 24525);
		metrics.addCustomChart(new Metrics.SimplePie("using_autoenable_claimfly", () -> {
			return Settings.ClaimFly.AUTO_ALLOW_FLIGHT.toString();
		}));
		metrics.addCustomChart(new Metrics.SimplePie("ignoring_creative", () -> {
			return Settings.ClaimFly.IGNORE_CREATIVE.toString();
		}));
		metrics.addCustomChart(new Metrics.SimplePie("ignoring_spectators", () -> {
			return Settings.ClaimFly.IGNORE_SPECTATOR.toString();
		}));
		SpigotUpdateChecker spigotUpdateChecker = new SpigotUpdateChecker(this, 122058);
	}

	@Override
	protected void onReloadablesStart() {
		if(!Settings.ClaimFly.AUTO_ALLOW_FLIGHT)
			Common.runTimer(0, 5, new CheckFlyingPlayersTask());
	}
}
