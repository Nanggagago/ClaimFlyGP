package dev.shadmage.claimflygp.settings;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.List;

public class Settings extends SimpleSettings {
	public static String SERVERCORE_PREFIX;

	private static void init() {
		setPathPrefix("");
		SERVERCORE_PREFIX = getString("Prefix");
		Common.setLogPrefix(SERVERCORE_PREFIX);
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

}
