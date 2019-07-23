package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import nycuro.API;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.kits.CommonKit;
import nycuro.kits.data.EnchantedStarterKit;
import nycuro.kits.data.GuardianKit;
import nycuro.kits.type.TypeKit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class KitsAPI {

    public static Map<TypeKit, CommonKit> kits = new HashMap<>();

    static {
        kits.put(TypeKit.ENCHANTED_STARTER, new EnchantedStarterKit());
        kits.put(TypeKit.GUARDIAN, new GuardianKit());
    }

    public void sendKit(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Arena Category", API.getMessageAPI().sendKitPrincipalModal(player));
        kitMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        kitMenu.addButton(new ElementButton("Enchanted Starter", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Guardian", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        player.showFormWindow(new ResponseFormWindow(kitMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageKits(player);
                            return;
                        case 1:
                            kits.get(TypeKit.ENCHANTED_STARTER).sendKit(player);
                            return;
                        case 2:
                            kits.get(TypeKit.GUARDIAN).sendKit(player);
                            return;
                    }
                }
            }
        }));
    }

    private void sendInfoMessageKits(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Kits");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageKits(player)));
        player.showFormWindow(infoMenu);
    }
}
