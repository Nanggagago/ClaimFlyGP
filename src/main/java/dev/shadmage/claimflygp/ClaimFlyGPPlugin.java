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
		SpigotUpdateChecker spigotUpdateChecker = new SpigotUpdateChecker(this, 122058);
	}

	@Override
	protected void onReloadablesStart() {
		Common.runTimer(0, 5, new CheckFlyingPlayersTask());
	}
}
