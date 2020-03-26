package nycuro.reports.api;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.player.Player;
import cn.nukkit.scheduler.AsyncTask;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.reports.commands.ReportsCommandManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.databaseAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ReportAPI {
    
    public void registerCommands() {
        ReportsCommandManager.registerAll(mainAPI);
    }

    public void createWindowReport(Player player) {
        FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("reports.form.first"),
                messageAPI.messagesObject.translateMessage("reports.form.top"));
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
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("reports.form.info.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("reports.form.info.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("reports.form.info.how")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("reports.form.info.how.discord")));
        player.showFormWindow(infoMenu);
    }

    private void sendReportForm(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("reports.form.mechanic.first"));
        infoMenu.addElement(new ElementInput(messageAPI.messagesObject.translateMessage("reports.form.mechanic.name")));
        infoMenu.addElement(new ElementInput(messageAPI.messagesObject.translateMessage("reports.form.mechanic.reason")));
        infoMenu.addElement(new ElementInput(messageAPI.messagesObject.translateMessage("reports.form.mechanic.contact")));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                databaseAPI.addNewReport(response.values().toArray()[0].toString(),
                        response.values().toArray()[1].toString(),
                        response.values().toArray()[2].toString(),
                        player.getName()
                );
                player.sendMessage(messageAPI.messagesObject.translateMessage("reports.success", response.values().toArray()[0].toString()));
            }
        }));
    }

    private void listReportsForm(Player player) {
        mainAPI.getServer().getScheduler().scheduleAsyncTask(mainAPI, new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("reports.form.list.first"),
                            messageAPI.messagesObject.translateMessage("reports.form.list.top"));
                    List<String> names = databaseAPI.getPlayerMap();
                    for (String name : names) {
                        int count = databaseAPI.getCountPlayerValueSetCount(name);
                        jobsMenu.addButton(new ElementButton(messageAPI.messagesObject.translateMessage("reports.form.list.button", name,
                                mainAPI.emptyNoSpace + count)));
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
            FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("reports.form.info.reported.first", name),
                    "");
            Collection<String> reasonsCollection = databaseAPI.getReasonsPlayerReport(name);
            Collection<String> contactCollection = databaseAPI.getContactPlayerReport(name);
            String reasons = String.join("\n\n", asStrings(reasonsCollection.toArray()));
            String contact = String.join("\n\n", asStrings(contactCollection.toArray()));
            jobsMenu.setContent(messageAPI.messagesObject.translateMessage("reports.form.info.reported.name"));
            jobsMenu.setContent(messageAPI.messagesObject.translateMessage("reports.form.info.reported.reason"));
            jobsMenu.setContent(messageAPI.messagesObject.translateMessage("reports.form.info.reported.contact"));
            jobsMenu.addButton(new ElementButton("Remove Report"));
            jobsMenu.addButton(new ElementButton("Close"));
            player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
                @Override
                public void accept(Map<Integer, Object> response) {
                    if (!response.isEmpty()) {
                        switch (response.entrySet().iterator().next().getKey()) {
                            case 0:
                                databaseAPI.deleteReport(name);
                                player.sendMessage(messageAPI.messagesObject.translateMessage("reports.deleted", name));
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
