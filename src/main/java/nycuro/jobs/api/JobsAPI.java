package nycuro.jobs.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.jobs.CommonJob;
import nycuro.jobs.NameJob;
import nycuro.jobs.TypeJob;
import nycuro.jobs.commands.JobsCommandManager;
import nycuro.jobs.data.ButcherJob;
import nycuro.jobs.data.FarmerJob;
import nycuro.jobs.data.FishermanJob;
import nycuro.jobs.data.MinerJob;
import nycuro.jobs.objects.JobsObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.mechanicAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class JobsAPI {

    public Map<NameJob, CommonJob> jobs = new HashMap<>();

    private Location jobsLocation = new Location(4, 162, -116, mainAPI.getServer().getDefaultLevel());

    public void registerCommands() {
        JobsCommandManager.registerAll(mainAPI);
    }

    public void addJobs() {
        jobs.put(NameJob.MINER, new MinerJob());
        jobs.put(NameJob.BUTCHER, new ButcherJob());
        jobs.put(NameJob.FARMER, new FarmerJob());
        jobs.put(NameJob.FISHERMAN, new FishermanJob());
    }

    private void sendInfoMessageJobs(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("jobs.form.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("jobs.form.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("jobs.form.miner")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("jobs.form.butcher")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("jobs.form.farmer")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("jobs.form.fisherman")));
        player.showFormWindow(infoMenu);
    }

    public void getJob(Player player) {
        FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("jobs.form.get.first"),
                messageAPI.messagesObject.translateMessage("jobs.form.get.top"));
        jobsMenu.addButton(new ElementButton("Teleport to Jobs", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        jobsMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/nujWKR3.png")));
        jobsMenu.addButton(new ElementButton("Without Job", new ElementButtonImageData("url", "https://i.imgur.com/YXBNPBc.png")));
        jobsMenu.addButton(new ElementButton("Mission", new ElementButtonImageData("url", "https://i.imgur.com/YXBNPBc.png")));
        jobsMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            player.teleport(jobsLocation);
                            return;
                        case 1:
                            sendInfoMessageJobs(player);
                            return;
                        case 2:
                            player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.deleted"));
                            return;
                        case 3:
                            if (profileSkyblock.getJob() == 0) {
                                player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.empty"));
                            } else {
                                NameJob job = NameJob.getType(profileSkyblock.getJob());
                                JobsObject jobsObject = mainAPI.jobsObject.get(player.getUniqueId());
                                if (jobsObject != null) {
                                    handleMission(player);
                                } else {
                                    switch (job) {
                                        case MINER:
                                            processMissionOnMiner(player);
                                            break;
                                        case FARMER:
                                            processMissionOnFarmer(player);
                                            break;
                                        case BUTCHER:
                                            processMissionOnButcher(player);
                                            break;
                                        case FISHERMAN:
                                            processMissionOnFisherman(player);
                                            break;
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }));
    }

    public void handleMission(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Handle Mission");
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        int job = profileSkyblock.getJob();
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("jobs.form.handle.first")));
        JobsObject jobsObject = mainAPI.jobsObject.get(player.getUniqueId());
        if (job != 2) {
                Item[] items = jobsObject.getItems();
                for (Item item : jobsObject.items) {
                    mainAPI.getServer().broadcastMessage(item.getName() + ":" + item.getCount());
                }
                int i = 0;
                for (Item item : items) {
                    for (Item content : player.getInventory().getContents().values()) {
                        if (!content.getName().equals("JOB")) continue;
                        if (content.getId() == item.getId()) {
                            i = content.getCount();
                        }
                    }
                    infoMenu.addElement(new ElementLabel(
                            messageAPI.messagesObject.translateMessage("jobs.form.handle.item", item.getName()) + "\n" +
                                    messageAPI.messagesObject.translateMessage("jobs.form.handle.count",
                                            mainAPI.emptyNoSpace + i, mainAPI.emptyNoSpace + item.getCount())
                    ));
                    i = 0;
                }
                infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("jobs.form.handle.reward",
                        mainAPI.emptyNoSpace + API.round(jobsObject.getReward(), 2))));
                player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
                    @Override
                    public void accept(Map<Integer, Object> response) {
                        if (!response.isEmpty()) {
                            if (!mechanicAPI.checkItems(player, items)) {
                                player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.enough.materials"));
                            } else {
                                ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                                profileSkyblock.setDollars(profileSkyblock.getDollars() + jobsObject.getReward());
                                for (Item item : player.getInventory().getContents().values()) {
                                    for (Item i : items) {
                                        if (i.getId() == item.getId()) {
                                            player.getInventory().remove(item);
                                        }
                                    }
                                }
                                player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.finished",
                                        mainAPI.emptyNoSpace + jobsObject.getReward()));
                                mainAPI.jobsObject.remove(player.getUniqueId());
                            }
                        }
                    }
                }));
            } else {
            infoMenu.addElement(new ElementLabel("Cow: " + jobsObject.getCountAnimals()[0] + "\n" +
                    "Pig: " + jobsObject.getCountAnimals()[1] + "\n" +
                    "Sheep: " + jobsObject.getCountAnimals()[2] + "\n" +
                    "Chicken: " + jobsObject.getCountAnimals()[3])
            );
            infoMenu.addElement(new ElementLabel("§aReward: §e" + API.round(jobsObject.getReward(), 2) + " Dollars"));
            player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
                @Override
                public void accept(Map<Integer, Object> response) {
                    if (!response.isEmpty()) {
                        if (jobsObject.getCountAnimals()[0] == 0 &&
                                jobsObject.getCountAnimals()[1] == 0 &&
                                jobsObject.getCountAnimals()[2] == 0 &&
                                jobsObject.getCountAnimals()[3] == 0) {
                            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                            profileSkyblock.setDollars(profileSkyblock.getDollars() + jobsObject.getReward());
                            player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.finished",
                                    mainAPI.emptyNoSpace + jobsObject.getReward()));
                            mainAPI.jobsObject.remove(player.getUniqueId());
                        } else {
                            player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.enough.kills"));
                        }
                    }
                }
            }));
        }
    }

    public void processMissionOnMiner(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process", "Miner"),
                messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process.form"));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.MINER).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + mainAPI.empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.MINER).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            handleMission(NameJob.MINER, player, TypeJob.EASY);
                            break;
                        case 1:
                            handleMission(NameJob.MINER, player, TypeJob.MEDIUM);
                            break;
                        case 2:
                            handleMission(NameJob.MINER, player, TypeJob.HARD);
                            break;
                        case 3:
                            handleMission(NameJob.MINER, player, TypeJob.EXTREME);
                            break;
                    }
                }
            }
        }));
    }

    public void processMissionOnButcher(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process", "Butcher"),
                messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process.form"));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.BUTCHER).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + mainAPI.empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.BUTCHER).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            handleMission(NameJob.BUTCHER, player, TypeJob.EASY);
                            break;
                        case 1:
                            handleMission(NameJob.BUTCHER, player, TypeJob.MEDIUM);
                            break;
                        case 2:
                            handleMission(NameJob.BUTCHER, player, TypeJob.HARD);
                            break;
                        case 3:
                            handleMission(NameJob.BUTCHER, player, TypeJob.EXTREME);
                            break;
                    }
                }
            }
        }));
    }

    public void processMissionOnFarmer(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process", "Farmer"),
                messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process.form"));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.FARMER).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + mainAPI.empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.FARMER).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            handleMission(NameJob.FARMER, player, TypeJob.EASY);
                            break;
                        case 1:
                            handleMission(NameJob.FARMER, player, TypeJob.MEDIUM);
                            break;
                        case 2:
                            handleMission(NameJob.FARMER, player, TypeJob.HARD);
                            break;
                        case 3:
                            handleMission(NameJob.FARMER, player, TypeJob.EXTREME);
                            break;
                    }
                }
            }
        }));
    }

    public void processMissionOnFisherman(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process", "Fisherman"),
                messageAPI.messagesObject.translateMessage("jobs.form.handle.mission.process.form"));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.FISHERMAN).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + mainAPI.empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.FISHERMAN).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            handleMission(NameJob.FISHERMAN, player, TypeJob.EASY);
                            break;
                        case 1:
                            handleMission(NameJob.FISHERMAN, player, TypeJob.MEDIUM);
                            break;
                        case 2:
                            handleMission(NameJob.FISHERMAN, player, TypeJob.HARD);
                            break;
                        case 3:
                            handleMission(NameJob.FISHERMAN, player, TypeJob.EXTREME);
                            break;
                    }
                }
            }
        }));
    }

    private void handleMission(NameJob nameJob, Player player, TypeJob typeJob) {
        jobs.get(nameJob).processMission(player, typeJob, new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                jobs.get(nameJob).getReward(typeJob, new Consumer<Double>() {
                    @Override
                    public void accept(Double reward) {
                        if (nameJob.getId() != 2) {
                            Item[] items = (Item[]) o;
                            succesfullyCreatedMissionMF(player, items, reward);
                        } else {
                            int[] integers = (int[]) o;
                            succesfullyCreatedMissionB(player, integers, reward);
                        }
                    }
                });
            }
        });
    }

    private void succesfullyCreatedMissionB(Player player, int[] integers, double reward) {
        mainAPI.jobsObject.put(player.getUniqueId(), new JobsObject(player.getUniqueId(), 2, new Item[0], integers, reward));
        handleMission(player);
    }

    private void succesfullyCreatedMissionMF(Player player, Item[] items, double reward) {
        mainAPI.jobsObject.put(player.getUniqueId(), new JobsObject(player.getUniqueId(), 2, items, new int[0], reward));
        handleMission(player);
    }
}
