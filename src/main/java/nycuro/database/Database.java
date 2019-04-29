package nycuro.database;

import cn.nukkit.scheduler.AsyncTask;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.unimi.dsi.fastutil.ints.*;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Database {

    public static Int2ObjectMap<String> scoreboardcoinsName = new Int2ObjectOpenHashMap<>();
    public static Int2DoubleMap scoreboardcoinsValue = new Int2DoubleOpenHashMap();
    public static Int2ObjectMap<String> scoreboardkillsName = new Int2ObjectOpenHashMap<>();
    public static Int2IntMap scoreboardkillsValue = new Int2IntOpenHashMap();
    public static Int2ObjectMap<String> scoreboarddeathsName = new Int2ObjectOpenHashMap<>();
    public static Int2IntMap scoreboarddeathsValue = new Int2IntOpenHashMap();
    public static Int2ObjectMap<String> scoreboardtimeName = new Int2ObjectOpenHashMap<>();
    public static Int2LongMap scoreboardtimeValue = new Int2LongOpenHashMap();
    public static Int2ObjectMap<String> scoreboardvotesName = new Int2ObjectOpenHashMap<>();
    public static Int2IntMap scoreboardvotesValue = new Int2IntOpenHashMap();
    public static Map<String, ProfileProxy> profileProxy = new HashMap<>();
    public static Map<String, ProfileFactions> profileFactions = new HashMap<>();
    private static HikariDataSource DATASOURCE_PROXY;
    private static HikariDataSource DATASOURCE_FACTIONS;

    public static void connectToDatabaseHub() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:/root/mcpe/databases/data_proxy.db");
        DATASOURCE_PROXY = new HikariDataSource(config);
        DATASOURCE_PROXY.setMaximumPoolSize(1);

        String query = "create table if not exists dates (`name` varchar, `language` int, `gems` REAL, `time` INTEGER, `votes` INTEGER)";

        try (Connection connection = DATASOURCE_PROXY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addDatesPlayerHub(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `name` =?")) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            profileProxy.put(name, new ProfileProxy(
                                    resultSet.getString("name"),
                                    resultSet.getInt("language"),
                                    resultSet.getDouble("gems"),
                                    resultSet.getLong("time"),
                                    resultSet.getInt("votes")
                            ));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void playerExist(String name, Consumer<Boolean> consumer) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `name` =?")) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.next());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void connectToDatabaseFactions() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:/root/mcpe/databases/data_factions.db");
        DATASOURCE_FACTIONS = new HikariDataSource(config);
        DATASOURCE_FACTIONS.setMaximumPoolSize(1);

        String query = "create table if not exists dates (`name` varchar, `job` int, `kills` int, `deaths` int, `cooldown` INTEGER, `experience` INTEGER, `level` int, `necesary` INTEGER, `time` INTEGER, `dollars` REAL)";

        try (Connection connection = DATASOURCE_FACTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Loader.registerTops();
    }

    public static void addDatesPlayerFactions(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `name` =?")) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            profileFactions.put(name, new ProfileFactions(
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
                try (Connection connection = DATASOURCE_PROXY.getConnection();
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

    public static void getTopVotes() {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `votes` from `dates` ORDER BY `time` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            if (!scoreboardvotesValue.isEmpty()) scoreboardvotesValue.clear();
                            if (!scoreboardvotesName.isEmpty()) scoreboardvotesName.clear();
                            switch (resultSet.getRow()) {
                                case 1:
                                    scoreboardvotesName.put(1, resultSet.getString("name"));
                                    scoreboardvotesValue.put(1, resultSet.getInt("votes"));
                                    break;
                                case 2:
                                    scoreboardvotesName.put(2, resultSet.getString("name"));
                                    scoreboardvotesValue.put(2, resultSet.getInt("votes"));
                                    break;
                                case 3:
                                    scoreboardvotesName.put(3, resultSet.getString("name"));
                                    scoreboardvotesValue.put(3, resultSet.getInt("votes"));
                                    break;
                                case 4:
                                    scoreboardvotesName.put(4, resultSet.getString("name"));
                                    scoreboardvotesValue.put(4, resultSet.getInt("votes"));
                                    break;
                                case 5:
                                    scoreboardvotesName.put(5, resultSet.getString("name"));
                                    scoreboardvotesValue.put(5, resultSet.getInt("votes"));
                                    break;
                                case 6:
                                    scoreboardvotesName.put(6, resultSet.getString("name"));
                                    scoreboardvotesValue.put(6, resultSet.getInt("votes"));
                                    break;
                                case 7:
                                    scoreboardvotesName.put(7, resultSet.getString("name"));
                                    scoreboardvotesValue.put(7, resultSet.getInt("votes"));
                                    break;
                                case 8:
                                    scoreboardvotesName.put(8, resultSet.getString("name"));
                                    scoreboardvotesValue.put(8, resultSet.getInt("votes"));
                                    break;
                                case 9:
                                    scoreboardvotesName.put(9, resultSet.getString("name"));
                                    scoreboardvotesValue.put(9, resultSet.getInt("votes"));
                                    break;
                                case 10:
                                    scoreboardvotesName.put(10, resultSet.getString("name"));
                                    scoreboardvotesValue.put(10, resultSet.getInt("votes"));
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

    public static void saveDatesPlayerFromProxy(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                saveUnAsyncDatesPlayerFromProxy(name);
            }
        });
    }

    public static void saveUnAsyncDatesPlayerFromProxy(String name) {
        ProfileProxy profile = profileProxy.get(name);
        try (Connection connection = DATASOURCE_PROXY.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE `dates` SET `language` = ?, `gems` = ?, `time` = ?, `votes` = ? WHERE `name` = ?")) {
            preparedStatement.setInt(1, profile.getLanguage());
            preparedStatement.setDouble(2, profile.getGems());
            preparedStatement.setLong(3, profile.getTime());
            preparedStatement.setInt(4, profile.getVotes());
            preparedStatement.setString(5, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewPlayer(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO `dates` (`name`, `language`, `gems`, `time`, `votes`) VALUES (?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, 0);
                    preparedStatement.setDouble(3, 0);
                    preparedStatement.setLong(4, 0);
                    preparedStatement.setInt(5, 0);
                    profileProxy.put(name, new ProfileProxy(
                            name,
                            0,
                            0,
                            0,
                            0
                    ));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setLanguage(String name, int language) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `language` =? WHERE `name` =?")) {
                    preparedStatement.setInt(1, language);
                    preparedStatement.setString(2, name);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setGems(String name, double gems) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `gems` =? WHERE `name` =?")) {
                    preparedStatement.setDouble(1, gems);
                    preparedStatement.setString(2, name);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setVotes(String name, int votes) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `votes` =? WHERE `name` =?")) {
                    preparedStatement.setDouble(1, votes);
                    preparedStatement.setString(2, name);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
