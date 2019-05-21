package nycuro.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.ProxyServer;
import nycuro.API;
import nycuro.database.objects.ProfileProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Database {

    public static Map<String, ProfileProxy> profileProxy = new HashMap<>();
    private static HikariDataSource DATASOURCE_PROXY;

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

        String query = "create table if not exists dates (`name` varchar(20), `language` int, `gems` REAL, `time` INTEGER, `votes` INTEGER)";

        try (Connection connection = DATASOURCE_PROXY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDatesPlayerFromHub(String name) {
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
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
            throw new RuntimeException(e);
        }
    }

    public static void addDatesPlayerProxy(String name) {
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
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
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
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

    public void addNewPlayer(String name) {
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
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

    /*public void setLanguage(Player player, int language) {
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

    public void setVotes(IPlayer player, int votes) {
        String uuid = player.getUniqueId().toString();
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try (Connection connection = DATASOURCE_HUB.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("UPDATE `dates` SET `votes` =? WHERE `uuid` =?")) {
                    preparedStatement.setDouble(1, votes);
                    preparedStatement.setString(2, uuid);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }*/
}
