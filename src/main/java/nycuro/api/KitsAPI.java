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
import nycuro.kits.data.clasic.*;
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
        kits.put(TypeKit.STARTER, new StarterKit());
        kits.put(TypeKit.ENCHANTED_STARTER, new EnchantedStarterKit());
        kits.put(TypeKit.GUARDIAN, new GuardianKit());
        kits.put(TypeKit.KNIGHT, new KnightKit());
        kits.put(TypeKit.PALADIN, new PaladinKit());
        kits.put(TypeKit.SPARROW, new SparrowKit());
    }

    public void sendKit(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Arena Category", API.getMessageAPI().sendKitPrincipalModal(player));
        kitMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        kitMenu.addButton(new ElementButton("Enchanted Starter", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Guardian", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Knight", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Paladin", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Sparrow", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
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
                        case 3:
                            kits.get(TypeKit.KNIGHT).sendKit(player);
                            return;
                        case 4:
                            kits.get(TypeKit.PALADIN).sendKit(player);
                            return;
                        case 5:
                            kits.get(TypeKit.SPARROW).sendKit(player);
                            break;
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
