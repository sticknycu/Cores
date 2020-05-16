package nycuro.homes.api;

import cn.nukkit.player.Player;
import nycuro.homes.commands.HomesCommandManager;

import static nycuro.api.API.mainAPI;

public class HomeAPI {

    public void registerCommands() {
        HomesCommandManager.registerAll(mainAPI);
    }

    public void createWindowHome(Player player) {
        /*FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("homes.form.first"),
                messageAPI.messagesObject.translateMessage("homes.form.top"));
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
         */
    }

    private void sendCreateHomeForm(Player player) {
        /*FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("homes.form.create.first"));
        infoMenu.addElement(new ElementInput(messageAPI.messagesObject.translateMessage("homes.form.create.input")));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                int count = databaseAPI.getCountPlayerHomes(player.getName());
                if (count > 1) {
                    player.sendMessage(messageAPI.messagesObject.translateMessage("homes.form.create.much", mainAPI.emptyNoSpace + count));
                } else {
                    databaseAPI.homeExist(response.values().toArray()[0].toString(), bool -> {
                        if (!bool) {
                            databaseAPI.addNewHome(player.getName(), (int) player.getX(), (int) player.getY(), (int) player.getZ(), player.getLevel().getName(), response.values().toArray()[0].toString());
                            player.sendMessage(messageAPI.messagesObject.translateMessage("homes.form.create.succes",
                                    mainAPI.emptyNoSpace + (int) player.getX(), mainAPI.emptyNoSpace + (int) player.getY(),
                                    mainAPI.emptyNoSpace + (int) player.getZ()));
                        } else {
                            player.sendMessage(messageAPI.messagesObject.translateMessage("homes.form.create.exists"));
                        }
                    });
                }
            }
        }));*/
    }

    private void listHomesForm(Player player) {
        /*mainAPI.getServer().getScheduler().scheduleAsyncTask(mainAPI, new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("homes.form.list.first"),
                            messageAPI.messagesObject.translateMessage("homes.form.list.top"));
                    List<String> homes = databaseAPI.getHomesPlayer(player.getName());
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
        });*/
    }

    private void getInfoHome(Player player, String homename) {
        /*try {
            FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("homes.form.info.first", homename),
                    messageAPI.messagesObject.translateMessage("homes.form.info.top"));
            jobsMenu.setContent(messageAPI.messagesObject.translateMessage("homes.form.info.message"));
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
                                HomeObject homeObject = databaseAPI.getDatesHomePlayer(player.getName());
                                player.teleport(Location.from(homeObject.getX(), homeObject.getY(), homeObject.getZ(), mainAPI.getServer().getLevelByName(homeObject.getWorldName())));
                                player.sendMessage(messageAPI.messagesObject.translateMessage("homes.form.teleport.success",
                                        mainAPI.emptyNoSpace + homeObject.getX(),
                                        mainAPI.emptyNoSpace + homeObject.getY(),
                                        mainAPI.emptyNoSpace + homeObject.getZ()));
                                return;
                            case 1:
                                databaseAPI.deleteHome(homename);
                                player.sendMessage(messageAPI.messagesObject.translateMessage("homes.form.deleted.success", homename));
                                break;
                        }
                    }
                }
            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    private void sendInfoMessageHomes(Player player) {
        /*FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("homes.form.mechanic.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("homes.form.mechanic.message")));
        player.showFormWindow(infoMenu);*/
    }

}
