package nycuro.database;

import cn.nukkit.scheduler.AsyncTask;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.unimi.dsi.fastutil.ints.*;
import nycuro.API;
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
        String address = "hosting3.gazduirejocuri.ro";
        String name = "chzoneeu_proxy";
        String username = "chzoneeu_nycu";
        String password = "unprost2019";

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", address);
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", name);
        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password);
        DATASOURCE_PROXY = new HikariDataSource(config);

        DATASOURCE_PROXY.setMaximumPoolSize(10);

        String query = "create table if not exists dates (`name` varchar(20), `language` int, `gems` FLOAT, `time` BIGINT, `votes` INTEGER)";

        try (Connection connection = DATASOURCE_PROXY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        });
    }

    public static void connectToDatabaseFactions() {
        String address = "hosting3.gazduirejocuri.ro";
        String name = "chzoneeu_factions";
        String username = "chzoneeu_nycu";
        String password = "unprost2019";

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", address);
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", name);
        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password);
        DATASOURCE_FACTIONS = new HikariDataSource(config);

        DATASOURCE_FACTIONS.setMaximumPoolSize(10);

        String query = "create table if not exists dates (`name` varchar(20), `job` int, `kills` int, `deaths` int, `cooldown` BIGINT, `experience` FLOAT, `level` int, `necesary` FLOAT, `time` BIGINT, `dollars` FLOAT)";

        try (Connection connection = DATASOURCE_FACTIONS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        });
    }

    public static void getTopTime() {
        if (!scoreboardtimeValue.isEmpty()) scoreboardtimeValue.clear();
        if (!scoreboardtimeName.isEmpty()) scoreboardtimeName.clear();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `time` from `dates` ORDER BY `time` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
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
                    e.printStackTrace();
                }
            }
        });
    }

    public static void getTopVotes() {
        if (!scoreboardvotesValue.isEmpty()) scoreboardvotesValue.clear();
        if (!scoreboardvotesName.isEmpty()) scoreboardvotesName.clear();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `votes` from `dates` ORDER BY `time` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
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
                    e.printStackTrace();
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
            e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        });
    }
}
