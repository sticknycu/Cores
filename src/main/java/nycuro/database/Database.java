package nycuro.database;

import cn.nukkit.scheduler.AsyncTask;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.unimi.dsi.fastutil.ints.*;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.objects.HomeObject;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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
    public static Map<String, ProfileProxy> profileProxy = new HashMap<>();
    public static Map<String, ProfileFactions> profileFactions = new HashMap<>();
    private static HikariDataSource DATASOURCE_PROXY;
    private static HikariDataSource DATASOURCE_FACTIONS;
    private static HikariDataSource DATASOURCE_REPORTS;
    private static HikariDataSource DATASOURCE_HOMESF;

    public static void connectToDatabaseHomesF() {
        String address = "hosting3.gazduirejocuri.ro";
        String name = "chzoneeu_homefactions";
        String username = "chzoneeu_nycu";
        String password = "unprost2019";

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", address);
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", name);
        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password);
        DATASOURCE_HOMESF = new HikariDataSource(config);

        DATASOURCE_HOMESF.setMaximumPoolSize(10);

        String query = "create table if not exists homes (`name` varchar(20), `x` int, `y` int, `z` int, `worldname` varchar(20), `homename` varchar(20))";

        try (Connection connection = DATASOURCE_HOMESF.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connectToDatabaseReports() {
        String address = "hosting3.gazduirejocuri.ro";
        String name = "chzoneeu_factionsreports";
        String username = "chzoneeu_nycu";
        String password = "unprost2019";

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", address);
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", name);
        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password);
        DATASOURCE_REPORTS = new HikariDataSource(config);

        DATASOURCE_REPORTS.setMaximumPoolSize(10);

        String query = "create table if not exists reports (`name` varchar(20), `reason` text, `contact` text, `reporter` varchar(20))";

        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                            profileProxy.putIfAbsent(name, new ProfileProxy(
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

    public static void saveDatesPlayerFromHub(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                saveUnAsyncDatesPlayerFromHub(name);
            }
        });
    }

    public static void saveUnAsyncDatesPlayerFromHub(String name) {
        ProfileProxy profileProxy = Database.profileProxy.get(name);
        try (Connection connection = DATASOURCE_PROXY.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE `dates` SET `language` = ?, `gems` = ?, `time` = ?, `votes` = ? WHERE `name` = ?")) {
            preparedStatement.setInt(1, profileProxy.getLanguage());
            preparedStatement.setDouble(2, profileProxy.getGems());
            preparedStatement.setLong(3, profileProxy.getTime());
            preparedStatement.setInt(4, profileProxy.getVotes());
            preparedStatement.setString(5, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        Loader.registerTops();
    }

    public static void addUnAsyncDatesPlayerFactions(String name) {
        try (Connection connection = DATASOURCE_FACTIONS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * from `dates` WHERE `name` =?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    profileFactions.putIfAbsent(name, new ProfileFactions(
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

    public static void addDatesPlayerFactions(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                addUnAsyncDatesPlayerFactions(name);
            }
        });
    }

    public HomeObject getDatesHomePlayer(String name) {
        HomeObject homeObject = new HomeObject("", 0, 0 ,0, "", "");
        try (Connection connection = DATASOURCE_HOMESF.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * from `homes` WHERE `name` =?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    homeObject.setName(resultSet.getString("name"));
                    homeObject.setX(resultSet.getInt("x"));
                    homeObject.setY(resultSet.getInt("y"));
                    homeObject.setZ(resultSet.getInt("z"));
                    homeObject.setWorldName(resultSet.getString("worldname"));
                    homeObject.setHomeName(resultSet.getString("homename"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeObject;
    }

    public static void getTopDollars() {
        if (!scoreboardcoinsValue.isEmpty()) scoreboardcoinsValue.clear();
        if (!scoreboardcoinsName.isEmpty()) scoreboardcoinsName.clear();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `dollars` from `dates` ORDER BY `dollars` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
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
                    e.printStackTrace();
                }
            }
        });
    }

    public static void getTopKills() {
        if (!scoreboardkillsValue.isEmpty()) scoreboardkillsValue.clear();
        if (!scoreboardkillsName.isEmpty()) scoreboardkillsName.clear();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `kills` from `dates` ORDER BY `kills` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
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
                    e.printStackTrace();
                }
            }
        });
    }

    public static void getTopDeaths() {
        if (!scoreboarddeathsValue.isEmpty()) scoreboarddeathsValue.clear();
        if (!scoreboarddeathsName.isEmpty()) scoreboarddeathsName.clear();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `deaths` from `dates` ORDER BY `deaths` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
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
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
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

    public void addNewReport(String name, String reason, String contact, String reporter) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_REPORTS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO reports (`name`, `reason`, `contact`, `reporter`) VALUES (?, ?, ?, ?)")) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, reason);
                    preparedStatement.setString(3, contact);
                    preparedStatement.setString(4, reporter);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addNewHome(String name, int x, int y, int z, String worldName, String homename) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_HOMESF.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO homes (`name`, `x`, `y`, `z`, `worldname`, `homename`) VALUES (?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, x);
                    preparedStatement.setInt(3, y);
                    preparedStatement.setInt(4, z);
                    preparedStatement.setString(5, worldName);
                    preparedStatement.setString(6, homename);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addNewPlayer(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO dates (`name`, `job`, `kills`, `deaths`, `cooldown`, `experience`, `level`, `necesary`, `time`, `dollars`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, 0);
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setInt(4, 0);
                    preparedStatement.setLong(5, 0);
                    preparedStatement.setDouble(6, 0);
                    preparedStatement.setInt(7, 0);
                    preparedStatement.setDouble(8, 250);
                    preparedStatement.setLong(9, 0);
                    preparedStatement.setDouble(10, 0);
                    profileFactions.put(name, new ProfileFactions(
                            name,
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
                    e.printStackTrace();
                }
            }
        });
    }

    public static void saveDatesPlayerFromFactions(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                saveUnAsyncDatesPlayerFromFactions(name);
            }
        });
    }

    public void playerExist(String name, Consumer<Boolean> consumer) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_FACTIONS.getConnection();
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

    public void homeExist(String name, Consumer<Boolean> consumer) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_HOMESF.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `homes` WHERE `homename` =?")) {
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


    public static void saveUnAsyncDatesPlayerFromFactions(String name) {
        ProfileFactions profileFactions = Database.profileFactions.get(name);
        try (Connection connection = DATASOURCE_FACTIONS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE `dates` SET `job` = ?, `kills` = ?, `deaths` = ?, `cooldown` = ?, `experience` = ?, `level` = ?, `necesary` = ?, `time` = ?, `dollars` = ? WHERE `name` = ?")) {
            preparedStatement.setInt(1, profileFactions.getJob());
            preparedStatement.setInt(2, profileFactions.getKills());
            preparedStatement.setInt(3, profileFactions.getDeaths());
            preparedStatement.setLong(4, profileFactions.getCooldown());
            preparedStatement.setDouble(5, profileFactions.getExperience());
            preparedStatement.setInt(6, profileFactions.getLevel());
            preparedStatement.setDouble(7, profileFactions.getNecesary());
            preparedStatement.setLong(8, profileFactions.getTime());
            preparedStatement.setDouble(9, profileFactions.getDollars());
            preparedStatement.setString(10, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public int getCountPlayerValueSetCount(String name) {
        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT count(name) from `reports` WHERE `name` =?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCountPlayerHomes(String name) {
        try (Connection connection = DATASOURCE_HOMESF.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT count(name) from `homes` WHERE `name` =?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCountOfAllPlayersReport() {
        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT count(name) as cnt from `reports`")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("cnt");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Collection<String> getReasonsPlayerReport(String name) {
        Collection<String> strings = new HashSet<>();
        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT reason as res from `reports` WHERE `name` =?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    strings.add("§6" + getReporterPlayerReportReason(resultSet.getString("res")) + ":§r" + "\n" + resultSet.getString("res"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public Collection<String> getReporterPlayerReportReason(String reason) {
        Collection<String> strings = new HashSet<>();
        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT reporter as rep from `reports` WHERE `reason` =?")) {
            preparedStatement.setString(1, reason);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    strings.add(resultSet.getString("rep"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public Collection<String> getReporterPlayerReportContact(String contact) {
        Collection<String> strings = new HashSet<>();
        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT reporter as rep from `reports` WHERE `contact` =?")) {
            preparedStatement.setString(1, contact);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    strings.add(resultSet.getString("rep"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public Collection<String> getContactPlayerReport(String name) {
        Collection<String> strings = new HashSet<>();
        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT contact as con from `reports` WHERE `name` =?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    strings.add("§6" + getReporterPlayerReportContact(resultSet.getString("con")) + ":§r" + "\n" + resultSet.getString("con"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public void deleteReport(String name) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_REPORTS.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("DELETE FROM `reports` WHERE `name` =?")) {
                    preparedStatement.setString(1, name);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void deleteHome(String homeName) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_HOMESF.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("DELETE FROM `homes` WHERE `homename` =?")) {
                    preparedStatement.setString(1, homeName);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public List<String> getPlayerMap() {
        List<String> names = new ArrayList<>();
        try (Connection connection = DATASOURCE_REPORTS.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT name as size from `reports`")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    names.add(resultSet.getString("size"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public List<String> getHomesPlayer(String name) {
        List<String> strs = new ArrayList<>();
        try (Connection connection = DATASOURCE_HOMESF.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT homename as size from `homes` WHERE `name` =?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    strs.add(resultSet.getString("size"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strs;
    }

}
