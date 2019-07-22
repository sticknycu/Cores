package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.scheduler.AsyncTask;
import nycuro.API;
import nycuro.gui.list.ResponseFormWindow;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ReportAPI {

    public void createWindowReport(Player player) {
        FormWindowSimple jobsMenu = new FormWindowSimple("Reports", API.getMessageAPI().sendReportPrincipalModal(player));
        jobsMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        jobsMenu.addButton(new ElementButton("Report a Player", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        if (player.hasPermission("core.reports")) {
            jobsMenu.addButton(new ElementButton("Report List", new ElementButtonImageData("url", "https://i.imgur.com/otMDlEU.png")));
        }
        player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageReports(player);
                            return;
                        case 1:
                            sendReportForm(player);
                            return;
                        case 2:
                            listReportsForm(player);
                            break;
                    }
                }
            }
        }));
    }

    private void sendInfoMessageReports(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Reports Info");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageReports(player)));
        player.showFormWindow(infoMenu);
    }

    private void sendReportForm(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Report Player");
        infoMenu.addElement(new ElementInput(API.getMessageAPI().sendInputNameReport(player)));
        infoMenu.addElement(new ElementInput(API.getMessageAPI().sendInputReasonReport(player)));
        infoMenu.addElement(new ElementInput(API.getMessageAPI().sendInputContactReport(player)));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                API.getDatabase().addNewReport(response.values().toArray()[0].toString(),
                        response.values().toArray()[1].toString(),
                        response.values().toArray()[2].toString(),
                        player.getName()
                );
                player.sendMessage(API.getMessageAPI().sendPlayerSuccesReport(player, response.values().toArray()[0].toString()));
            }
        }));
    }

    private void listReportsForm(Player player) {
        API.getMainAPI().getServer().getScheduler().scheduleAsyncTask(API.getMainAPI(), new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    FormWindowSimple jobsMenu = new FormWindowSimple("Report List", API.getMessageAPI().sendReportList(player));
                    List<String> names = API.getDatabase().getPlayerMap();
                    for (String name : names) {
                        int count = API.getDatabase().getCountPlayerValueSetCount(name);
                        jobsMenu.addButton(new ElementButton(name + " §7[§6" + count + "§7]"));
                    }
                    player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
                        @Override
                        public void accept(Map<Integer, Object> response) {
                            if (!response.isEmpty()) {
                                String s = response.entrySet().iterator().next().getValue().toString();
                                String[] all = s.split(" ");
                                getInfoReported(player, all[0]);
                            }
                        }
                    }));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void getInfoReported(Player player, String name) {
        try {
            FormWindowSimple jobsMenu = new FormWindowSimple("Report " + name, "");
            Collection<String> reasonsCollection = API.getDatabase().getReasonsPlayerReport(name);
            Collection<String> contactCollection = API.getDatabase().getContactPlayerReport(name);
            String reasons = String.join("\n\n", asStrings(reasonsCollection.toArray()));
            String contact = String.join("\n\n", asStrings(contactCollection.toArray()));
            jobsMenu.setContent(API.getMessageAPI().sendInfoMessageReport(player, name, reasons, contact));
            jobsMenu.addButton(new ElementButton("Remove Report"));
            jobsMenu.addButton(new ElementButton("Close"));
            player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
                @Override
                public void accept(Map<Integer, Object> response) {
                    if (!response.isEmpty()) {
                        switch (response.entrySet().iterator().next().getKey()) {
                            case 0:
                                API.getDatabase().deleteReport(name);
                                player.sendMessage(API.getMessageAPI().sendDeleteReportMessage(player, name));
                                return;
                            case 1:
                                break;
                        }
                    }
                }
            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String[] asStrings(Object... objArray) {
        String[] strArray = new String[objArray.length];
        for (int i = 0; i < objArray.length; i++)
            strArray[i] = String.valueOf(objArray[i]);
        return strArray;
    }
}
