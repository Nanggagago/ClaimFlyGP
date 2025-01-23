package dev.shadmage.claimflygp.settings;

import com.bekvon.bukkit.residence.commands.current;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.List;

public class Settings extends SimpleSettings {
	public static String LOG_PREFIX;

	private static void init() {
		setPathPrefix("");
		LOG_PREFIX = getString("Prefix");
	}

	@Override
	protected int getConfigVersion() {
		setPathPrefix("");
		return getInteger("Version");
	}

	public static class ClaimFly {
		public static String CLAIMFLY_CHAT_PREFIX;

		private static void init() {
			setPathPrefix("ClaimFly");
			CLAIMFLY_CHAT_PREFIX = getString("ChatPrefix");
		}
	}

	public static class Messages {
		public static String NO_FLY;
		public static String NO_FLY_THIS_CLAIM;
		public static String NO_FLY_OUTSIDE_CLAIM;
		public static String FLIGHT_ENABLED;
		public static String FLIGHT_DISABLED;

		private static void init() {
			setPathPrefix("Messages");
			NO_FLY = getString("noFlyPerms");
			NO_FLY_THIS_CLAIM = getString("noFlyThisClaim");
			NO_FLY_OUTSIDE_CLAIM = getString("noFlyOutsideClaims");
			FLIGHT_ENABLED = getString("FlightEnabled");
			FLIGHT_DISABLED = getString("FlightDisabled");
		}
	}

}
