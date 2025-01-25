# ClaimFly [GriefPrevention Addon]

- Plugin Available on [SpigotMC](https://www.spigotmc.org/resources/claimfly-griefprevention-addon.122058/)
- View Plugin Stats [bStats](https://bstats.org/plugin/bukkit/ClaimFly%20-%20GriefPrevention%20Addon/24525)

We've all seen it before... Players want to be able to fly, but limiting that flight can become an issue. With this addon you can limit player flight only to claims they own. This can also extend to staff and builders since you can restrict flight to admin claims or other player claims, but not in unclaimed areas

Features:

- Choose where players can fly by setting permissions:
  - Claims they own
  - Claims where they have Access Trust
  - Admin claims
  - Outside of claimed areas
> [!WARNING] Experimental Feature - has potential to cause lag?
> - Automatically enable or disable flight mode when players enter or leave a claimed area so that players don't need to use the /claimfly command
This feature can be enabled in the settings.yml file
If you enable this feature, players will still be able to use the command as well

Dependancies:
- GriefPrevention

Commands:
- /claimfly | /cfly | /fly

>[!IMPORTANT]
> Permissions:
> - `claimfly.use` - allows use of the command and flight in own claims
> - `claimfly.claims.admin` - allows flight in admin claims
> - `claimfly.claims.others` - allows flight in other players claims if they have Access Trust
> - `claimfly.claims.unclaimed` - allows flight outside of claimed areas

Configuration:
No additional config needed. Drop in, Set permissions and it just works.
Additional features and all messages can be customized in the config file though.