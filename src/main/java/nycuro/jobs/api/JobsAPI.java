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
import nycuro.database.DatabaseMySQL;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.jobs.CommonJob;
import nycuro.jobs.NameJob;
import nycuro.jobs.TypeJob;
import nycuro.jobs.jobs.ButcherJob;
import nycuro.jobs.jobs.FarmerJob;
import nycuro.jobs.jobs.FishermanJob;
import nycuro.jobs.jobs.MinerJob;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class JobsAPI {

    public static Map<NameJob, CommonJob> jobs = new HashMap<>();

    static {
        jobs.put(NameJob.MINER, new MinerJob());
        jobs.put(NameJob.BUTCHER, new ButcherJob());
        jobs.put(NameJob.FARMER, new FarmerJob());
        jobs.put(NameJob.FISHERMAN, new FishermanJob());
    }

    private void sendInfoMessageJobs(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Jobs");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageJobs(player)));
        player.showFormWindow(infoMenu);
    }

    public void getJob(Player player) {
        FormWindowSimple jobsMenu = new FormWindowSimple("Jobs", API.getMessageAPI().sendJobPrincipalModal(player));
        jobsMenu.addButton(new ElementButton("Teleport to Jobs", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        jobsMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/nujWKR3.png")));
        jobsMenu.addButton(new ElementButton("Without Job", new ElementButtonImageData("url", "https://i.imgur.com/YXBNPBc.png")));
        jobsMenu.addButton(new ElementButton("Mission", new ElementButtonImageData("url", "https://i.imgur.com/YXBNPBc.png")));
        jobsMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    ProfileSkyblock profileSkyblock = DatabaseMySQL.profileSkyblock.get(player.getName());
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            player.teleport(new Location(27, 169, 0, API.getMainAPI().getServer().getDefaultLevel()));
                            return;
                        case 1:
                            sendInfoMessageJobs(player);
                            return;
                        case 2:
                            API.getMessageAPI().sendWithoutJobMessage(player);
                            return;
                        case 3:
                            if (profileSkyblock.getJob() == 0) {
                                API.getMessageAPI().sendNoJobMessage(player);
                            } else {
                                API.getDatabase().playerExistInJobs(player.getName(), bool -> {
                                    NameJob job = NameJob.valueOf(NameJob.getType(profileSkyblock.getJob()));
                                    if (bool) {
                                        switch (job) {
                                            case MINER:
                                                //succesfullyCreatedMissionMF(player, Database);
                                                break;
                                            case FARMER:
                                                //succesfullyCreatedMissionMF(player, Database);
                                                break;
                                            case BUTCHER:
                                                //succesfullyCreatedMissionB(player, Database);
                                                break;
                                            case FISHERMAN:
                                                //succesfullyCreatedMissionF(player, Database);
                                                break;
                                        }
                                    } else {
                                        switch (job) {
                                            case MINER:
                                                API.getJobsAPI().processMissionOnMiner(player);
                                                break;
                                            case FARMER:
                                                API.getJobsAPI().processMissionOnFarmer(player);
                                                break;
                                            case BUTCHER:
                                                API.getJobsAPI().processMissionOnButcher(player);
                                                break;
                                            case FISHERMAN:
                                                API.getJobsAPI().processMissionOnFisherman(player);
                                                break;
                                        }
                                    }
                                });
                            }
                            break;
                    }
                }
            }
        }));
    }

    public void handleMission(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Handle Mission");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendHandleMissions(player)));
        Collection<Item> items = API.getDatabase().getItemsFromJob(player.getName());
        int i = 0;
        for (Item item : items) {
            for (Item content : player.getInventory().getContents().values()) {
                if (content.getId() == item.getId()) {
                    i++;
                }
            }
            infoMenu.addElement(new ElementLabel("§6Item§e: §c" + item.getName() + "\n"));
            infoMenu.addElement(new ElementLabel("§6Count§e: " + "§7[§6" + item.getCount() + "/" + i + "§7]\n\n"));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    if (checkItems(player, items)) {
                        //DatabaseMySQL.mission.get(player.getName()).getReward();
                    }
                }
            }
        }));
    }

    private boolean checkItems(Player player, Collection<Item> items) {
        for (Item item : items) {
            for (Item content : player.getInventory().getContents().values()) {
                if (item.equals(content) && item.getCount() != content.getCount()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void processMissionOnMiner(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple("Process Miner JobPlayer", API.getMessageAPI().sendProcessMission(player));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.MINER).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + API.getMainAPI().empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.MINER).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            jobs.get(NameJob.MINER).processMission(player, TypeJob.EASY, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.MINER).getReward(TypeJob.EASY, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 1:
                            jobs.get(NameJob.MINER).processMission(player, TypeJob.MEDIUM, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.MINER).getReward(TypeJob.MEDIUM, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 2:
                            jobs.get(NameJob.MINER).processMission(player, TypeJob.HARD, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.MINER).getReward(TypeJob.HARD, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 3:
                            jobs.get(NameJob.MINER).processMission(player, TypeJob.EXTREME, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.MINER).getReward(TypeJob.EXTREME, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                            break;
                    }
                }
            }
        }));
    }

    public void processMissionOnButcher(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple("Process Butcher JobPlayer", API.getMessageAPI().sendProcessMission(player));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.BUTCHER).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + API.getMainAPI().empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.BUTCHER).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            jobs.get(NameJob.BUTCHER).processMission(player, TypeJob.EASY, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.BUTCHER).getReward(TypeJob.EASY, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionB(player, integers, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 1:
                            jobs.get(NameJob.FARMER).processMission(player, TypeJob.MEDIUM, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FARMER).getReward(TypeJob.MEDIUM, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionB(player, integers, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 2:
                            jobs.get(NameJob.FARMER).processMission(player, TypeJob.HARD, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FARMER).getReward(TypeJob.HARD, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionB(player, integers, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 3:
                            jobs.get(NameJob.FARMER).processMission(player, TypeJob.EXTREME, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FARMER).getReward(TypeJob.EXTREME, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionB(player, integers, reward);
                                        }
                                    });
                                }
                            });
                    }
                }
            }
        }));
    }

    public void processMissionOnFarmer(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple("Process Farmer JobPlayer", API.getMessageAPI().sendProcessMission(player));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.FARMER).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + API.getMainAPI().empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.FARMER).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            jobs.get(NameJob.FARMER).processMission(player, TypeJob.EASY, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FARMER).getReward(TypeJob.EASY, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 1:
                            jobs.get(NameJob.FARMER).processMission(player, TypeJob.MEDIUM, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FARMER).getReward(TypeJob.MEDIUM, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 2:
                            jobs.get(NameJob.FARMER).processMission(player, TypeJob.HARD, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FARMER).getReward(TypeJob.HARD, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 3:
                            jobs.get(NameJob.FARMER).processMission(player, TypeJob.EXTREME, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FARMER).getReward(TypeJob.EXTREME, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Item> items = (Collection<Item>) o;
                                            succesfullyCreatedMissionMF(player, items, reward);
                                        }
                                    });
                                }
                            });
                    }
                }
            }
        }));
    }

    public void processMissionOnFisherman(Player player) {
        FormWindowSimple infoMenu = new FormWindowSimple("Process Fisherman JobPlayer", API.getMessageAPI().sendProcessMission(player));
        TextFormat color;
        for (TypeJob typeJob : TypeJob.values()) {
            if (jobs.get(NameJob.FISHERMAN).isLocked(player, typeJob)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            infoMenu.addButton(new ElementButton(typeJob.getName() + API.getMainAPI().empty + TextFormat.GRAY + "[" + color + jobs.get(NameJob.FISHERMAN).getStatus(player, typeJob) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            jobs.get(NameJob.FISHERMAN).processMission(player, TypeJob.EASY, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FISHERMAN).getReward(TypeJob.EASY, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionF(player, integers, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 1:
                            jobs.get(NameJob.FISHERMAN).processMission(player, TypeJob.MEDIUM, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FISHERMAN).getReward(TypeJob.MEDIUM, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionF(player, integers, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 2:
                            jobs.get(NameJob.FISHERMAN).processMission(player, TypeJob.HARD, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FISHERMAN).getReward(TypeJob.HARD, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionF(player, integers, reward);
                                        }
                                    });
                                }
                            });
                            break;
                        case 3:
                            jobs.get(NameJob.FISHERMAN).processMission(player, TypeJob.EXTREME, new Consumer<Object>() {
                                @Override
                                public void accept(Object o) {
                                    jobs.get(NameJob.FISHERMAN).getReward(TypeJob.EXTREME, new Consumer<Double>() {
                                        @Override
                                        public void accept(Double reward) {
                                            Collection<Integer> integers = (Collection<Integer>) o;
                                            succesfullyCreatedMissionF(player, integers, reward);
                                        }
                                    });
                                }
                            });
                    }
                }
            }
        }));
    }

    private void succesfullyCreatedMissionB(Player player, Collection<Integer> integers, double reward) {
        FormWindowCustom infoMenu = new FormWindowCustom("JobPlayer About");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().succesfullyMissionCreated(player)));
        infoMenu.addElement(new ElementLabel("Kill:\n"));
        int i = 0;
        for (int integer : integers) {
            infoMenu.addElement(new ElementLabel("Name: " + getAnimal(i)));
            infoMenu.addElement(new ElementLabel("Count: " + integer));
            i++;
        }
        infoMenu.addElement(new ElementLabel("Reward: " + reward));
        player.showFormWindow(infoMenu);
    }

    private void succesfullyCreatedMissionF(Player player, Collection<Integer> integers, double reward) {
        FormWindowCustom infoMenu = new FormWindowCustom("JobPlayer About");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().succesfullyMissionCreated(player)));
        infoMenu.addElement(new ElementLabel("Get:\n"));
        int i = 0;
        for (int integer : integers) {
            infoMenu.addElement(new ElementLabel("Name: " + getFish(i)));
            infoMenu.addElement(new ElementLabel("Count: " + integer));
            i++;
        }
        infoMenu.addElement(new ElementLabel("Reward: " + reward));
        player.showFormWindow(infoMenu);
    }

    private void succesfullyCreatedMissionMF(Player player, Collection<Item> items, double reward) {
        FormWindowCustom infoMenu = new FormWindowCustom("JobPlayer About");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().succesfullyMissionCreated(player)));
        infoMenu.addElement(new ElementLabel("Get:\n"));
        for (Item item : items) {
            infoMenu.addElement(new ElementLabel("Item: " + item.getName()));
            infoMenu.addElement(new ElementLabel("Count: " + item.getCount()));
        }
        infoMenu.addElement(new ElementLabel("Reward: " + reward));
        player.showFormWindow(infoMenu);
    }

    private String getAnimal(int i) {
        String animal = "";
        switch (i) {
            case 0:
                animal = "Cow";
                break;
            case 1:
                animal = "Pig";
                break;
            case 2:
                animal = "Chicken";
                break;
            case 3:
                animal = "Sheep";
                break;
        }
        return animal;
    }

    private String getFish(int i) {
        String fish = "";
        switch (i) {
            case 0:
                fish = "Raw Cod";
                break;
            case 1:
                fish = "Raw Salmon";
                break;
        }
        return fish;
    }
}
