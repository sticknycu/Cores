package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Location;
import cn.nukkit.scheduler.AsyncTask;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.HomeObject;
import nycuro.gui.list.ResponseFormWindow;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class HomeAPI {

    public void createWindowHome(Player player) {
        FormWindowSimple jobsMenu = new FormWindowSimple("Homes", API.getMessageAPI().sendHomesPrincipalModal(player));
        jobsMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        jobsMenu.addButton(new ElementButton("Create a Home", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        jobsMenu.addButton(new ElementButton("Manage Homes", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageHomes(player);
                            return;
                        case 1:
                            sendCreateHomeForm(player);
                            return;
                        case 2:
                            listHomesForm(player);
                            break;
                    }
                }
            }
        }));
    }

    private void sendCreateHomeForm(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Create a Home");
        infoMenu.addElement(new ElementInput(API.getMessageAPI().sendInputNameHome(player)));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                int count = API.getDatabase().getCountPlayerHomes(player.getName());
                if (count > 1) {
                    player.sendMessage(API.getMessageAPI().sendTooMuchHomesMessage(player, count));
                } else {
                    API.getDatabase().homeExist(response.values().toArray()[0].toString(), bool -> {
                        if (!bool) {
                            API.getDatabase().addNewHome(player.getName(), (int) player.getX(), (int) player.getY(), (int) player.getZ(), player.getLevel().getFolderName(), response.values().toArray()[0].toString());
                            API.getMessageAPI().sendCreatedHomeSuccesfully(player, (int) player.getX(), (int) player.getY(), (int) player.getZ());
                        } else {
                            player.sendMessage(API.getMessageAPI().sendHomeExistsMessage(player));
                        }
                    });
                }
            }
        }));
    }

    private void listHomesForm(Player player) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    FormWindowSimple jobsMenu = new FormWindowSimple("Home List", API.getMessageAPI().sendHomeList(player));
                    List<String> homes = API.getDatabase().getHomesPlayer(player.getName());
                    for (String name : homes) {
                        jobsMenu.addButton(new ElementButton(name));
                    }
                    player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
                        @Override
                        public void accept(Map<Integer, Object> response) {
                            if (!response.isEmpty()) {
                                String s = response.entrySet().iterator().next().getValue().toString();
                                getInfoHome(player, s);
                            }
                        }
                    }));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void getInfoHome(Player player, String homename) {
        try {
            FormWindowSimple jobsMenu = new FormWindowSimple("Home " + homename, "");
            jobsMenu.setContent(API.getMessageAPI().sendInfoMessageHome(player, homename));
            jobsMenu.addButton(new ElementButton("Teleport"));
            int lang = Database.profileProxy.get(player.getName()).getLanguage();
            switch (lang) {
                case 0:
                    jobsMenu.addButton(new ElementButton("Delete Home"));
                    break;
                case 1:
                    jobsMenu.addButton(new ElementButton("Sterge Home"));
                    break;

            }
            player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
                @Override
                public void accept(Map<Integer, Object> response) {
                    if (!response.isEmpty()) {
                        switch (response.entrySet().iterator().next().getKey()) {
                            case 0:
                                HomeObject homeObject = API.getDatabase().getDatesHomePlayer(player.getName());
                                player.teleport(new Location(homeObject.getX(), homeObject.getY(), homeObject.getZ(), API.getMainAPI().getServer().getLevelByName(homeObject.getWorldName())));
                                player.sendMessage(API.getMessageAPI().sendTeleportedHomeSuccesfully(player, homeObject.getX(), homeObject.getY(), homeObject.getZ()));
                                return;
                            case 1:
                                API.getDatabase().deleteHome(homename);
                                player.sendMessage(API.getMessageAPI().sendRemoveHomeSuccesfully(player, homename));
                                break;
                        }
                    }
                }
            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInfoMessageHomes(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Home Info");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageHomes(player)));
        player.showFormWindow(infoMenu);
    }

}
