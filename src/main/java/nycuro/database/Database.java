package nycuro.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import nycuro.API;
import nycuro.database.objects.ProfileProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Consumer;

public class Database {

    public static Object2ObjectMap<UUID, ProfileProxy> profileProxy = new Object2ObjectOpenHashMap<>();
    private static HikariDataSource DATASOURCE_PROXY;

    public static void connectToDatabaseHub() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:/root/mcpe/databases/data_proxy.db");
        DATASOURCE_PROXY = new HikariDataSource(config);
        DATASOURCE_PROXY.setMaximumPoolSize(1);

        String query = "create table if not exists dates (`uuid` varchar, `name` varchar, `language` int, `gems` REAL, `time` INTEGER, `votes` INTEGER)";

        try (Connection connection = DATASOURCE_PROXY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDatesPlayerFromHub(ProxiedPlayer player) {
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
                saveUnAsyncDatesPlayerFromHub(player);
            }
        });
    }

    public static void saveUnAsyncDatesPlayerFromHub(ProxiedPlayer player) {
        ProfileProxy profileProxy = Database.profileProxy.get(player.getUniqueId());
        try (Connection connection = DATASOURCE_PROXY.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE `dates` SET `language` = ?, `gems` = ?, `time` = ?, `votes` = ? WHERE `uuid` = ?")) {
            preparedStatement.setInt(1, profileProxy.getLanguage());
            preparedStatement.setDouble(2, profileProxy.getGems());
            preparedStatement.setLong(3, profileProxy.getTime());
            preparedStatement.setInt(4, profileProxy.getVotes());
            preparedStatement.setString(5, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addDatesPlayerProxy(ProxiedPlayer player) {
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT * from `dates` WHERE `uuid` =?")) {
                    preparedStatement.setString(1, player.getUniqueId().toString());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            profileProxy.put(player.getUniqueId(), new ProfileProxy(
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

    public void playerExist(ProxiedPlayer player, Consumer<Boolean> consumer) {
        String uuid = player.getUniqueId().toString();
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
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

    public void addNewPlayer(ProxiedPlayer player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        ProxyServer.getInstance().getScheduler().runAsync(API.getMainAPI(), new Runnable() {
            @Override
            public void run() {
                try (Connection connection = DATASOURCE_PROXY.getConnection();
                     PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO `dates` (`uuid`, `name`, `language`, `gems`, `time`, `votes`) VALUES (?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, uuid);
                    preparedStatement.setString(2, name);
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setDouble(4, 0);
                    preparedStatement.setLong(5, 0);
                    preparedStatement.setInt(6, 0);
                    profileProxy.put(player.getUniqueId(), new ProfileProxy(
                            player.getName(),
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
