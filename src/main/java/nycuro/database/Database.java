package nycuro.database;

import cn.nukkit.Player;
import cn.nukkit.scheduler.AsyncTask;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileHub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Consumer;

public class Database {

    public static Object2ObjectMap<Integer, String> scoreboardcoinsName = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, Double> scoreboardcoinsValue = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, String> scoreboardkillsName = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, Integer> scoreboardkillsValue = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, String> scoreboarddeathsName = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, Integer> scoreboarddeathsValue = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, String> scoreboardtimeName = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, Long> scoreboardtimeValue = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<UUID, ProfileHub> profileHub = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<UUID, ProfileFactions> profileFactions = new Object2ObjectOpenHashMap<>();
    private static HikariDataSource DATASOURCE_HUB;
    private static HikariDataSource DATASOURCE_FACTIONS;

    public static void connectToDatabaseHub() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:/root/mcpe/databases/data_hub.db");
        DATASOURCE_HUB = new HikariDataSource(config);
        DATASOURCE_HUB.setMaximumPoolSize(1);

        String query = "create table if not exists dates (`uuid` varchar, `name` varchar, `language` int, `gems` REAL, `time` INTEGER, `dollars` REAL)";

        try (Connection connection = DATASOURCE_HUB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addDatesPlayerHub(Player player) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_HUB.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, player.getUniqueId().toString());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            profileHub.put(player.getUniqueId(), new ProfileHub(
                                    resultSet.getString("name"),
                                    resultSet.getInt("language"),
                                    resultSet.getDouble("gems"),
                                    resultSet.getLong("time")
                            ));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void saveDatesPlayerFromHub(Player player) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                saveUnAsyncDatesPlayerFromHub(player);
            }
        });
    }

    public static void saveUnAsyncDatesPlayerFromHub(Player player) {
        ProfileHub profileHub = Database.profileHub.get(player.getUniqueId());
        try (Connection connection = DATASOURCE_HUB.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE `dates` SET `language` = ?, `gems` = ?, `time` = ? WHERE `uuid` = ?")) {
            preparedStatement.setInt(1, profileHub.getLanguage());
            preparedStatement.setDouble(2, profileHub.getGems());
            preparedStatement.setLong(3, profileHub.getTime());
            preparedStatement.setString(4, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void connectToDatabaseFactions() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:/root/mcpe/databases/data_factions.db");
        DATASOURCE_FACTIONS = new HikariDataSource(config);
        DATASOURCE_FACTIONS.setMaximumPoolSize(1);

        String query = "create table if not exists dates (`uuid` varchar, `name` varchar, `job` int, `kills` int, `deaths` int, `cooldown` INTEGER, `experience` INTEGER, `level` int, `necesary` INTEGER, `time` INTEGER, `dollars` REAL)";

        try (Connection connection = DATASOURCE_FACTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Loader.registerTops();
    }

    public static void addDatesPlayerFactions(Player player) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, player.getUniqueId().toString());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            profileFactions.put(player.getUniqueId(), new ProfileFactions(
                                    resultSet.getString("name"),
                                    resultSet.getInt("job"),
                                    resultSet.getInt("kills"),
                                    resultSet.getInt("deaths"),
                                    resultSet.getLong("cooldown"),
                                    resultSet.getDouble("experience"),
                                    resultSet.getInt("level"),
                                    resultSet.getDouble("necesary"),
                                    resultSet.getLong("time"),
                                    resultSet.getDouble("dollars")
                            ));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void getTopDollars() {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `dollars` from `dates` ORDER BY `dollars` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            if (!scoreboardcoinsValue.isEmpty()) scoreboardcoinsValue.clear();
                            if (!scoreboardcoinsName.isEmpty()) scoreboardcoinsName.clear();
                            switch (resultSet.getRow()) {
                                case 1:
                                    scoreboardcoinsName.put(1, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(1, resultSet.getDouble("dollars"));
                                    break;
                                case 2:
                                    scoreboardcoinsName.put(2, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(2, resultSet.getDouble("dollars"));
                                    break;
                                case 3:
                                    scoreboardcoinsName.put(3, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(3, resultSet.getDouble("dollars"));
                                    break;
                                case 4:
                                    scoreboardcoinsName.put(4, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(4, resultSet.getDouble("dollars"));
                                    break;
                                case 5:
                                    scoreboardcoinsName.put(5, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(5, resultSet.getDouble("dollars"));
                                    break;
                                case 6:
                                    scoreboardcoinsName.put(6, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(6, resultSet.getDouble("dollars"));
                                    break;
                                case 7:
                                    scoreboardcoinsName.put(7, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(7, resultSet.getDouble("dollars"));
                                    break;
                                case 8:
                                    scoreboardcoinsName.put(8, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(8, resultSet.getDouble("dollars"));
                                    break;
                                case 9:
                                    scoreboardcoinsName.put(9, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(9, resultSet.getDouble("dollars"));
                                    break;
                                case 10:
                                    scoreboardcoinsName.put(10, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(10, resultSet.getDouble("dollars"));
                                    break;
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void getTopKills() {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `kills` from `dates` ORDER BY `kills` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            if (!scoreboardkillsValue.isEmpty()) scoreboardkillsValue.clear();
                            if (!scoreboardkillsName.isEmpty()) scoreboardkillsName.clear();
                            switch (resultSet.getRow()) {
                                case 1:
                                    scoreboardkillsName.put(1, resultSet.getString("name"));
                                    scoreboardkillsValue.put(1, resultSet.getInt("kills"));
                                    break;
                                case 2:
                                    scoreboardkillsName.put(2, resultSet.getString("name"));
                                    scoreboardkillsValue.put(2, resultSet.getInt("kills"));
                                    break;
                                case 3:
                                    scoreboardkillsName.put(3, resultSet.getString("name"));
                                    scoreboardkillsValue.put(3, resultSet.getInt("kills"));
                                    break;
                                case 4:
                                    scoreboardkillsName.put(4, resultSet.getString("name"));
                                    scoreboardkillsValue.put(4, resultSet.getInt("kills"));
                                    break;
                                case 5:
                                    scoreboardkillsName.put(5, resultSet.getString("name"));
                                    scoreboardkillsValue.put(5, resultSet.getInt("kills"));
                                    break;
                                case 6:
                                    scoreboardkillsName.put(6, resultSet.getString("name"));
                                    scoreboardkillsValue.put(6, resultSet.getInt("kills"));
                                    break;
                                case 7:
                                    scoreboardkillsName.put(7, resultSet.getString("name"));
                                    scoreboardkillsValue.put(7, resultSet.getInt("kills"));
                                    break;
                                case 8:
                                    scoreboardkillsName.put(8, resultSet.getString("name"));
                                    scoreboardkillsValue.put(8, resultSet.getInt("kills"));
                                    break;
                                case 9:
                                    scoreboardkillsName.put(9, resultSet.getString("name"));
                                    scoreboardkillsValue.put(9, resultSet.getInt("kills"));
                                    break;
                                case 10:
                                    scoreboardkillsName.put(10, resultSet.getString("name"));
                                    scoreboardkillsValue.put(10, resultSet.getInt("kills"));
                                    break;
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void getTopDeaths() {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `deaths` from `dates` ORDER BY `deaths` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            if (!scoreboarddeathsValue.isEmpty()) scoreboarddeathsValue.clear();
                            if (!scoreboarddeathsName.isEmpty()) scoreboarddeathsName.clear();
                            switch (resultSet.getRow()) {
                                case 1:
                                    scoreboarddeathsName.put(1, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(1, resultSet.getInt("deaths"));
                                    break;
                                case 2:
                                    scoreboarddeathsName.put(2, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(2, resultSet.getInt("deaths"));
                                    break;
                                case 3:
                                    scoreboarddeathsName.put(3, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(3, resultSet.getInt("deaths"));
                                    break;
                                case 4:
                                    scoreboarddeathsName.put(4, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(4, resultSet.getInt("deaths"));
                                    break;
                                case 5:
                                    scoreboarddeathsName.put(5, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(5, resultSet.getInt("deaths"));
                                    break;
                                case 6:
                                    scoreboarddeathsName.put(6, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(6, resultSet.getInt("deaths"));
                                    break;
                                case 7:
                                    scoreboarddeathsName.put(7, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(7, resultSet.getInt("deaths"));
                                    break;
                                case 8:
                                    scoreboarddeathsName.put(8, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(8, resultSet.getInt("deaths"));
                                    break;
                                case 9:
                                    scoreboarddeathsName.put(9, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(9, resultSet.getInt("deaths"));
                                    break;
                                case 10:
                                    scoreboarddeathsName.put(10, resultSet.getString("name"));
                                    scoreboarddeathsValue.put(10, resultSet.getInt("deaths"));
                                    break;
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void getTopTime() {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `time` from `dates` ORDER BY `time` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            if (!scoreboardtimeValue.isEmpty()) scoreboardtimeValue.clear();
                            if (!scoreboardtimeName.isEmpty()) scoreboardtimeName.clear();
                            switch (resultSet.getRow()) {
                                case 1:
                                    scoreboardtimeName.put(1, resultSet.getString("name"));
                                    scoreboardtimeValue.put(1, resultSet.getLong("time"));
                                    break;
                                case 2:
                                    scoreboardtimeName.put(2, resultSet.getString("name"));
                                    scoreboardtimeValue.put(2, resultSet.getLong("time"));
                                    break;
                                case 3:
                                    scoreboardtimeName.put(3, resultSet.getString("name"));
                                    scoreboardtimeValue.put(3, resultSet.getLong("time"));
                                    break;
                                case 4:
                                    scoreboardtimeName.put(4, resultSet.getString("name"));
                                    scoreboardtimeValue.put(4, resultSet.getLong("time"));
                                    break;
                                case 5:
                                    scoreboardtimeName.put(5, resultSet.getString("name"));
                                    scoreboardtimeValue.put(5, resultSet.getLong("time"));
                                    break;
                                case 6:
                                    scoreboardtimeName.put(6, resultSet.getString("name"));
                                    scoreboardtimeValue.put(6, resultSet.getLong("time"));
                                    break;
                                case 7:
                                    scoreboardtimeName.put(7, resultSet.getString("name"));
                                    scoreboardtimeValue.put(7, resultSet.getLong("time"));
                                    break;
                                case 8:
                                    scoreboardtimeName.put(8, resultSet.getString("name"));
                                    scoreboardtimeValue.put(8, resultSet.getLong("time"));
                                    break;
                                case 9:
                                    scoreboardtimeName.put(9, resultSet.getString("name"));
                                    scoreboardtimeValue.put(9, resultSet.getLong("time"));
                                    break;
                                case 10:
                                    scoreboardtimeName.put(10, resultSet.getString("name"));
                                    scoreboardtimeValue.put(10, resultSet.getLong("time"));
                                    break;
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addNewPlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO `dates` (`uuid`, `name`, `job`, `kills`, `deaths`, `cooldown`, `experience`, `level`, `necesary`, `time`, `dollars`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, uuid);
                    preparedStatement.setString(2, name);
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setInt(4, 0);
                    preparedStatement.setInt(5, 0);
                    preparedStatement.setLong(6, 0);
                    preparedStatement.setDouble(7, 0);
                    preparedStatement.setInt(8, 0);
                    preparedStatement.setDouble(9, 250);
                    preparedStatement.setLong(10, 0);
                    preparedStatement.setDouble(11, 0);
                    profileFactions.put(player.getUniqueId(), new ProfileFactions(
                            player.getName(),
                            0,
                            0,
                            0,
                            0,
                            0.0,
                            0,
                            250.0,
                            0,
                            0.0
                    ));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void saveDatesPlayerFromFactions(Player player) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                saveUnAsyncDatesPlayerFromFactions(player);
            }
        });
    }

    public void playerExist(Player player, Consumer<Boolean> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.next());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void saveUnAsyncDatesPlayerFromFactions(Player player) {
        ProfileFactions profileFactions = Database.profileFactions.get(player.getUniqueId());
        try (Connection connection = DATASOURCE_FACTIONS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE `dates` SET `job` = ?, `kills` = ?, `deaths` = ?, `cooldown` = ?, `experience` = ?, `level` = ?, `necesary` = ?, `time` = ?, `dollars` = ? WHERE `uuid` = ?")) {
            preparedStatement.setInt(1, profileFactions.getJob());
            preparedStatement.setInt(2, profileFactions.getKills());
            preparedStatement.setInt(3, profileFactions.getDeaths());
            preparedStatement.setLong(4, profileFactions.getCooldown());
            preparedStatement.setDouble(5, profileFactions.getExperience());
            preparedStatement.setInt(6, profileFactions.getLevel());
            preparedStatement.setDouble(7, profileFactions.getNecesary());
            preparedStatement.setLong(8, profileFactions.getTime());
            preparedStatement.setDouble(9, profileFactions.getDollars());
            preparedStatement.setString(10, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLanguage(Player player, int language) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_HUB.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `language` =? WHERE `uuid` =?")) {
                    preparedStatement.setInt(1, language);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setGems(Player player, double gems) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_HUB.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `gems` =? WHERE `uuid` =?")) {
                    preparedStatement.setDouble(1, gems);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
