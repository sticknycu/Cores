package nycuro.teleport.api;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.permission.PermissionAttachmentInfo;
import nycuro.api.API;
import nycuro.teleport.objects.TPCooldown;
import nycuro.teleport.objects.TPRequest;
import nycuro.teleport.commands.TeleportationCommandManager;
import nycuro.teleport.handlers.TeleportationHandlers;
import nycuro.teleport.tasks.TeleportationExpireTask;
import nycuro.teleport.tasks.TeleportationTask;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeleportationAPI extends API {

    public static final long TP_EXPIRATION = TimeUnit.MINUTES.toMillis(1);
    private static final Pattern COOLDOWN_PATTERN = Pattern.compile("^nycuro\\.cooldown\\.([0-9]+)$");
    private static final Pattern TP_COOLDOWN_PATTERN = Pattern.compile("^nycuro\\.tp\\.cooldown\\.([0-9]+)$");
    private final List<TPCooldown> tpCooldowns = new ArrayList<>();
    private final Map<CommandSender, Long> cooldown = new HashMap<>();
    public Map<Integer, TPRequest> tpRequests = new ConcurrentHashMap<>();

    private static int getHashCode(Player from, Player to, boolean isTo) {
        return from.hashCode() + to.hashCode() + Boolean.hashCode(isTo);
    }

    @Override
    public void registerCommands() {
        TeleportationCommandManager.registerAll(getMainAPI());
    }

    public void registerTasks() {
        getMainAPI().getServer().getScheduler().scheduleRepeatingTask(new TeleportationTask(getTeleportationAPI()), 1, true);
        getMainAPI().getServer().getScheduler().scheduleDelayedRepeatingTask(getMainAPI(), new TeleportationExpireTask(),
                20, 20, true);
    }

    public void registerHandlers() {
        getMainAPI().getServer().getPluginManager().registerEvents(new TeleportationHandlers(this), getMainAPI());
    }

    public boolean hasCooldown(CommandSender sender) {
        long cooldown = Long.MAX_VALUE;
        for (PermissionAttachmentInfo info : sender.getEffectivePermissions().values()) {
            Matcher matcher = COOLDOWN_PATTERN.matcher(info.getPermission().toLowerCase());
            if (matcher.find()) {
                int time = Integer.parseInt(matcher.group(1));
                if (time < cooldown) {
                    cooldown = time;
                }
            }
        }

        if (!sender.isOp() && cooldown < Long.MAX_VALUE) {
            long currentTime = System.currentTimeMillis();
            long lastCooldown = this.cooldown.getOrDefault(sender, -1L) + TimeUnit.SECONDS.toMillis(cooldown);

            if (currentTime > lastCooldown) {
                this.cooldown.put(sender, currentTime);
            } else {
                long timeLeft = TimeUnit.MILLISECONDS.toSeconds(lastCooldown - currentTime);
                sender.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.generic.cooldown", toString() + timeLeft));
                return true;
            }
        }
        return false;
    }

    private OptionalInt hasTPCooldown(Player player) {
        int cooldown = Integer.MAX_VALUE;
        for (PermissionAttachmentInfo info : player.getEffectivePermissions().values()) {
            Matcher matcher = TP_COOLDOWN_PATTERN.matcher(info.getPermission().toLowerCase());
            if (matcher.find()) {
                int time = Integer.parseInt(matcher.group(1));
                if (time < cooldown) {
                    cooldown = time;
                }
            }
        }

        if (!player.isOp() && cooldown < Integer.MAX_VALUE) {
            return OptionalInt.of(cooldown);
        }
        return OptionalInt.empty();
    }

    public void onTP(Player player, Position position, String message) {
        this.onTP(player, position.getLocation(), message);
    }

    public void onTP(Player player, Location location, String message) {
        OptionalInt cooldown = hasTPCooldown(player);

        if (cooldown.isPresent()) {
            player.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.generic.teleporation.cooldown", toString() + cooldown.getAsInt()));
            tpCooldowns.add(new TPCooldown(player, location,
                    System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(cooldown.getAsInt()), message));
        } else {
            player.teleport(location);
            player.sendMessage(message);
        }
    }

    public List<TPCooldown> getTpCooldowns() {
        return tpCooldowns;
    }

    public void requestTP(Player from, Player to, boolean isTo) {
        this.tpRequests.put(getHashCode(from, to, isTo), new TPRequest(System.currentTimeMillis(), from, to, isTo ? to.getLocation() : from.getLocation(), isTo));
    }

    public TPRequest getLatestTPRequestTo(Player player) {
        TPRequest latest = null;
        for (TPRequest request : this.tpRequests.values()) {
            if (request.getTo() == player && (latest == null || request.getStartTime() > latest.getStartTime())) {
                latest = request;
            }
        }
        return latest;
    }

    public TPRequest getTPRequestBetween(Player from, Player to) {
        int key;
        if (this.tpRequests.containsKey(key = getHashCode(from, to, true)) || this.tpRequests.containsKey(key = getHashCode(from, to, false))) {
            return this.tpRequests.get(key);
        }
        return null;
    }

    public void removeTPRequestBetween(Player from, Player to) {
        this.tpRequests.remove(getHashCode(from, to, true));
        this.tpRequests.remove(getHashCode(from, to, false));

    }

    public void removeTPRequest(Player player) {
        this.tpRequests.values().removeIf(request -> request.getFrom() == player || request.getTo() == player);
    }
}
