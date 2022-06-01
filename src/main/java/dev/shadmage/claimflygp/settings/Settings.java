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

	public static class EggCatcher {
		public static String EGGCATCHER_GENERAL_CHAT_PREFIX;
		public static Boolean EGGCATCHER_GENERAL_UsePermissions;
		public static Boolean EGGCATCHER_GENERAL_UseCatchChance;
		public static Boolean EGGCATCHER_GENERAL_LooseEggOnFail;
		public static Boolean EGGCATCHER_GENERAL_UseHealthPercentage;
		public static Boolean EGGCATCHER_GENERAL_ExplosionEffect;
		public static Boolean EGGCATCHER_GENERAL_SmokeEffect;
		public static Boolean EGGCATCHER_GENERAL_NonPlayerCatching;
		public static Boolean EGGCATCHER_GENERAL_PreventCatchingBabyAnimals;
		public static Boolean EGGCATCHER_GENERAL_PreventCatchingTamedAnimals;
		public static Boolean EGGCATCHER_GENERAL_PreventCatchingShearedSheeps;
		public static Boolean EGGCATCHER_GENERAL_SpawnChickenOnSuccess;
		public static Boolean EGGCATCHER_GENERAL_SpawnChickenOnFail;
		public static Boolean EGGCATCHER_GENERAL_DeleteVillagerInventoryOnCatch;
		public static Double EGGCATCHER_GENERAL_CatchChance;
		public static Double EGGCATCHER_GENERAL_HealthPercent;
		public static String EGGCATCHER_MESSAGE_No_Permissions;
		public static String EGGCATCHER_MESSAGE_ChanceFail;
		public static String EGGCATCHER_MESSAGE_ChanceSuccess;
		public static String EGGCATCHER_MESSAGE_HealthFail;
		public static Boolean EGGCATCHER_TRAILS_Enabled;
		public static List<String> EGGCATCHER_TRAILS_DisabledWorlds;

		private static void init() {
			setPathPrefix("EggCatcher");
			EGGCATCHER_GENERAL_CHAT_PREFIX = getString("ChatPrefix");
			EGGCATCHER_GENERAL_UsePermissions = getBoolean("UsePermissions");
			EGGCATCHER_GENERAL_UseCatchChance = getBoolean("UseCatchChance");
			EGGCATCHER_GENERAL_CatchChance = getDouble("CatchChance");
			EGGCATCHER_GENERAL_LooseEggOnFail = getBoolean("LooseEggOnFail");
			EGGCATCHER_GENERAL_UseHealthPercentage = getBoolean("UseHealthPercentage");
			EGGCATCHER_GENERAL_HealthPercent = getDouble("HealthPercentage");
			EGGCATCHER_GENERAL_ExplosionEffect = getBoolean("ExplosionEffect");
			EGGCATCHER_GENERAL_SmokeEffect = getBoolean("SmokeEffect");
			EGGCATCHER_GENERAL_NonPlayerCatching = getBoolean("NonPlayerCatching");
			EGGCATCHER_GENERAL_PreventCatchingBabyAnimals = getBoolean("PreventCatchingBabyAnimals");
			EGGCATCHER_GENERAL_PreventCatchingTamedAnimals = getBoolean("PreventCatchingTamedAnimals");
			EGGCATCHER_GENERAL_PreventCatchingShearedSheeps = getBoolean("PreventCatchingShearedSheeps");
			EGGCATCHER_GENERAL_SpawnChickenOnSuccess = getBoolean("SpawnChickenOnSuccess");
			EGGCATCHER_GENERAL_SpawnChickenOnFail = getBoolean("SpawnChickenOnFail");
			EGGCATCHER_GENERAL_DeleteVillagerInventoryOnCatch = getBoolean("DeleteVillagerInventoryOnCatch");
			setPathPrefix("EggCatcher.Messages");
			EGGCATCHER_MESSAGE_No_Permissions = getString("PermissionFail");
			EGGCATCHER_MESSAGE_ChanceFail = getString("CatchChanceFail");
			EGGCATCHER_MESSAGE_ChanceSuccess = getString("CatchChanceSuccess");
			EGGCATCHER_MESSAGE_HealthFail = getString("HealthPercentageFail");
			setPathPrefix("EggCatcher.Trails");
			EGGCATCHER_TRAILS_Enabled = getBoolean("Enabled");
			EGGCATCHER_TRAILS_DisabledWorlds = getList("Disabled-Worlds", String.class);
		}
	}

}
