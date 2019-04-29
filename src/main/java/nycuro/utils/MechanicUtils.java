package nycuro.utils;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import nycuro.Loader;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MechanicUtils {

    public static void getTops() {
        Object2ObjectMap<String, Double> powerMap = new Object2ObjectOpenHashMap<>();

        ValueDoubleComparator bvpower = new ValueDoubleComparator(powerMap);
        TreeMap<String, Double> sorted_map_power = new TreeMap<String, Double>(bvpower);

        for (final Faction map : Factions.i.get()) {
            powerMap.put(map.getTag(), map.getPower());
        }

        sorted_map_power.putAll(powerMap);

        Loader.scoreboardPowerName.clear();
        Loader.scoreboardPowerValue.clear();
        for (int i = 0; i < sorted_map_power.keySet().toArray().length && i != 10; ++i) {
            Loader.scoreboardPowerName.put(i + 1, sorted_map_power.keySet().toArray()[i].toString());
            Loader.scoreboardPowerValue.put(i + 1, Double.valueOf(sorted_map_power.values().toArray()[i].toString()));
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
