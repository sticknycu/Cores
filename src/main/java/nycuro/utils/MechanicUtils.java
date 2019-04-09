package nycuro.utils;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class MechanicUtils {


    public static void getTops() {

        Object2ObjectMap<String, Double> coinsMap = new Object2ObjectOpenHashMap<>();
        Object2ObjectMap<String, Integer> killsMap = new Object2ObjectOpenHashMap<>();
        Object2ObjectMap<String, Integer> deathsMap = new Object2ObjectOpenHashMap<>();
        Object2ObjectMap<String, Long> timeMap = new Object2ObjectOpenHashMap<>();

        ValueDoubleComparator bvcoins = new ValueDoubleComparator(coinsMap);
        TreeMap<String, Double> sorted_map_coins = new TreeMap<String, Double>(bvcoins);

        ValueIntegerComparator bvkills = new ValueIntegerComparator(killsMap);
        TreeMap<String, Integer> sorted_map_kills = new TreeMap<String, Integer>(bvkills);

        ValueIntegerComparator bvdeaths = new ValueIntegerComparator(deathsMap);
        TreeMap<String, Integer> sorted_map_deaths = new TreeMap<String, Integer>(bvdeaths);

        ValueLongComparator bvtime = new ValueLongComparator(timeMap);
        TreeMap<String, Long> sorted_map_time = new TreeMap<String, Long>(bvtime);

        for (Map.Entry<UUID, Profile> map : Database.profile.entrySet()) {
            coinsMap.put(map.getValue().getName(), map.getValue().getCoins());
            killsMap.put(map.getValue().getName(), map.getValue().getKills());
            deathsMap.put(map.getValue().getName(), map.getValue().getDeaths());
            timeMap.put(map.getValue().getName(), map.getValue().getTime());
        }

        sorted_map_coins.putAll(coinsMap);
        sorted_map_kills.putAll(killsMap);
        sorted_map_deaths.putAll(deathsMap);
        sorted_map_time.putAll(timeMap);


        Database.scoreboardcoinsName.clear();
        Database.scoreboardcoinsValue.clear();
        Database.scoreboardkillsName.clear();
        Database.scoreboardkillsValue.clear();
        Database.scoreboarddeathsName.clear();
        Database.scoreboarddeathsValue.clear();
        Database.scoreboardtimeName.clear();
        Database.scoreboardtimeValue.clear();

        for (int i = 0; i < sorted_map_coins.keySet().toArray().length; i++) {
            if (i == 10) break;
            Database.scoreboardcoinsName.put(i + 1, sorted_map_coins.keySet().toArray()[i].toString());
            Database.scoreboardcoinsValue.put(i + 1, Double.valueOf(sorted_map_coins.values().toArray()[i].toString()));
        }
        for (int i = 0; i < sorted_map_kills.keySet().toArray().length; i++) {
            if (i == 10) break;
            Database.scoreboardkillsName.put(i + 1, sorted_map_kills.keySet().toArray()[i].toString());
            Database.scoreboardkillsValue.put(i + 1, Integer.valueOf(sorted_map_kills.values().toArray()[i].toString()));
        }
        for (int i = 0; i < sorted_map_deaths.keySet().toArray().length; i++) {
            if (i == 10) break;
            Database.scoreboarddeathsName.put(i + 1, sorted_map_deaths.keySet().toArray()[i].toString());
            Database.scoreboarddeathsValue.put(i + 1, Integer.valueOf(sorted_map_deaths.values().toArray()[i].toString()));
        }
        for (int i = 0; i < sorted_map_time.keySet().toArray().length; i++) {
            if (i == 10) break;
            Database.scoreboardtimeName.put(i + 1, sorted_map_time.keySet().toArray()[i].toString());
            Database.scoreboardtimeValue.put(i + 1, Long.valueOf(sorted_map_time.values().toArray()[i].toString()));
        }
    }
}

class ValueDoubleComparator implements Comparator<String> {

    Map<String, Double> doubleMap;

    public ValueDoubleComparator(Map<String, Double> doubleMap) {
        this.doubleMap = doubleMap;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(String a, String b) {
        if (doubleMap.get(a) >= doubleMap.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}

class ValueIntegerComparator implements Comparator<String> {

    Map<String, Integer> integerMap;

    public ValueIntegerComparator(Map<String, Integer> integerMap) {
        this.integerMap = integerMap;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(String a, String b) {
        if (integerMap.get(a) >= integerMap.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}

class ValueLongComparator implements Comparator<String> {

    Map<String, Long> longMap;

    public ValueLongComparator(Map<String, Long> longMap) {
        this.longMap = longMap;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(String a, String b) {
        if (longMap.get(a) >= longMap.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
