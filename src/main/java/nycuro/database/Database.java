package nycuro.database;

import cn.nukkit.Player;
import cn.nukkit.scheduler.AsyncTask;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import nycuro.API;
import nycuro.Core;
import nycuro.database.objects.Profile;

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
    public static Object2ObjectMap<UUID, Profile> profile = new Object2ObjectOpenHashMap<>();
    private static HikariDataSource DATASOURCE;

    public static void connectToDatabase() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:plugins/NycuRO-Core/data.db");
        DATASOURCE = new HikariDataSource(config);
        DATASOURCE.setMaximumPoolSize(1);

        String query = "create table if not exists dates (`uuid` varchar, `name` varchar, `language` int, `job` int, `kills` int, `deaths` int, `cooldown` INTEGER, `experience` INTEGER, `level` int, `necesary` INTEGER, `coins` REAL, `time` INTEGER)";

        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Core.registerTops();
        addDatesPlayer();
    }

    public static void addDatesPlayer() {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates`")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            profile.put(UUID.fromString(resultSet.getString("UUID")), new Profile(
                                    resultSet.getString("name"),
                                    resultSet.getInt("language"),
                                    resultSet.getInt("job"),
                                    resultSet.getInt("kills"),
                                    resultSet.getInt("deaths"),
                                    resultSet.getLong("cooldown"),
                                    resultSet.getDouble("experience"),
                                    resultSet.getInt("level"),
                                    resultSet.getDouble("necesary"),
                                    resultSet.getDouble("coins"),
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

    public static void getTopCoins() {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT `name`, `coins` from `dates` ORDER BY `coins` DESC LIMIT 10")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            if (!scoreboardcoinsValue.isEmpty()) scoreboardcoinsValue.clear();
                            if (!scoreboardcoinsName.isEmpty()) scoreboardcoinsName.clear();
                            switch (resultSet.getRow()) {
                                case 1:
                                    scoreboardcoinsName.put(1, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(1, resultSet.getDouble("coins"));
                                    break;
                                case 2:
                                    scoreboardcoinsName.put(2, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(2, resultSet.getDouble("coins"));
                                    break;
                                case 3:
                                    scoreboardcoinsName.put(3, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(3, resultSet.getDouble("coins"));
                                    break;
                                case 4:
                                    scoreboardcoinsName.put(4, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(4, resultSet.getDouble("coins"));
                                    break;
                                case 5:
                                    scoreboardcoinsName.put(5, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(5, resultSet.getDouble("coins"));
                                    break;
                                case 6:
                                    scoreboardcoinsName.put(6, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(6, resultSet.getDouble("coins"));
                                    break;
                                case 7:
                                    scoreboardcoinsName.put(7, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(7, resultSet.getDouble("coins"));
                                    break;
                                case 8:
                                    scoreboardcoinsName.put(8, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(8, resultSet.getDouble("coins"));
                                    break;
                                case 9:
                                    scoreboardcoinsName.put(9, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(9, resultSet.getDouble("coins"));
                                    break;
                                case 10:
                                    scoreboardcoinsName.put(10, resultSet.getString("name"));
                                    scoreboardcoinsValue.put(10, resultSet.getDouble("coins"));
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
                try (Connection connection = DATASOURCE.getConnection();
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
                try (Connection connection = DATASOURCE.getConnection();
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
                try (Connection connection = DATASOURCE.getConnection();
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

    public void playerExist(Player player, Consumer<Boolean> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
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

    public void addNewPlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO `dates` (`uuid`, `name`, `language`, `job`, `kills`, `deaths`, `cooldown`, `experience`, `level`, `necesary`, `coins`, `time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, uuid);
                    preparedStatement.setString(2, name);
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setInt(4, 0);
                    preparedStatement.setInt(5, 0);
                    preparedStatement.setInt(6, 0);
                    preparedStatement.setLong(7, 0);
                    preparedStatement.setDouble(8, 0);
                    preparedStatement.setInt(9, 0);
                    preparedStatement.setDouble(10, 0);
                    preparedStatement.setDouble(11, 0);
                    preparedStatement.setLong(12, 0);
                    profile.put(player.getUniqueId(), new Profile(
                            player.getName(),
                            0,
                            0,
                            0,
                            0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0.0,
                            0
                    ));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void saveDatesPlayer(UUID uuid, Profile profile) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `language` = ?, `job` = ?, `kills` = ?, `deaths` = ?, `cooldown` = ?, `experience` = ?, `level` = ?, `necesary` = ?, `coins` = ?, `time` = ? WHERE `uuid` = ?")) {
                    preparedStatement.setInt(1, profile.getLanguage());
                    preparedStatement.setInt(2, profile.getJob());
                    preparedStatement.setInt(3, profile.getKills());
                    preparedStatement.setInt(4, profile.getDeaths());
                    preparedStatement.setLong(5, profile.getCooldown());
                    preparedStatement.setDouble(6, profile.getExperience());
                    preparedStatement.setInt(7, profile.getLevel());
                    preparedStatement.setDouble(8, profile.getNecesary());
                    preparedStatement.setDouble(9, profile.getCoins());
                    preparedStatement.setLong(10, profile.getTime());
                    preparedStatement.setString(11, uuid.toString());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    Core.log("Saving Dates of Player was a succesful!");
                }
            }
        });
    }

    public void setLanguage(Player player, int language) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
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

    public void getLanguage(Player player, Consumer<Integer> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getInt("language"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setJob(Player player, int job) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `job` =? WHERE `uuid` =?")) {
                    preparedStatement.setInt(1, job);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void getJob(Player player, Consumer<Integer> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getInt("job"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setKills(Player player, int kills) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `kills` =? WHERE `uuid` =?")) {
                    preparedStatement.setInt(1, kills);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addKills(Player player, int kills) {
        getKills(player, bool -> {
            int i = bool;
            setKills(player, i + kills);
        });
    }

    public void getKills(Player player, Consumer<Integer> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getInt("kills"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addDeaths(Player player, int deaths) {
        getDeaths(player, bool -> {
            int i = bool;
            setDeaths(player, i + deaths);
        });
    }

    public void setDeaths(Player player, int deaths) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `deaths` =? WHERE `uuid` =?")) {
                    preparedStatement.setInt(1, deaths);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void getDeaths(Player player, Consumer<Integer> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getInt("deaths"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setCooldown(Player player, long cooldown) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `cooldown` =? WHERE `uuid` =?")) {
                    preparedStatement.setLong(1, cooldown);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void getCooldown(Player player, Consumer<Long> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getLong("cooldown"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setExperience(Player player, double experience) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `experience` =? WHERE `uuid` =?")) {
                    preparedStatement.setDouble(1, experience);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void getExperience(Player player, Consumer<Double> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getDouble("experience"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setLevel(Player player, int level) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `level` =? WHERE `uuid` =?")) {
                    preparedStatement.setInt(1, level);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void getLevel(Player player, Consumer<Integer> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getInt("level"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setCoins(Player player, double coins) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `coins` =? WHERE `uuid` =?")) {
                    preparedStatement.setDouble(1, coins);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addCoins(Player player, double coins) {
        getCoins(player, bool -> {
            double i = bool;
            setCoins(player, i + coins);
        });
    }

    public void reduceCoins(Player player, double coins) {
        getCoins(player, bool -> {
            double i = bool;
            setCoins(player, i - coins);
        });
    }

    public void getCoins(Player player, Consumer<Double> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getDouble("coins"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void getNecesary(Player player, Consumer<Double> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getDouble("necesary"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setNecesary(Player player, double necesary) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `necesary` =? WHERE `uuid` =?")) {
                    preparedStatement.setDouble(1, necesary);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setTime(Player player, long time) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `time` =? WHERE `uuid` =?")) {
                    preparedStatement.setLong(1, time);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void getTime(Player player, Consumer<Long> consumer) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        consumer.accept(resultSet.getLong("time"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
