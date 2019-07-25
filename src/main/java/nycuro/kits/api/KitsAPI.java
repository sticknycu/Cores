package nycuro.kits.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import nycuro.api.API;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.kits.CommonKit;
import nycuro.kits.data.clasic.*;
import nycuro.kits.data.specific.*;
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
        // classic
        kits.put(TypeKit.STARTER, new StarterKit()); // StartKit - Only for new people (Cannot be buyed or accesed)
        kits.put(TypeKit.ENCHANTED_STARTER, new EnchantedStarterKit());
        kits.put(TypeKit.GUARDIAN, new GuardianKit());
        kits.put(TypeKit.KNIGHT, new KnightKit());
        kits.put(TypeKit.PALADIN, new PaladinKit());
        kits.put(TypeKit.SPARROW, new SparrowKit());

        // premium


        // specific
        kits.put(TypeKit.PLANTER, new PlanterKit());
        kits.put(TypeKit.STONNER, new StonnerKit());
        kits.put(TypeKit.LACKER, new LackerKit());
        kits.put(TypeKit.WOODIE, new WoodieKit());
        kits.put(TypeKit.PUSH_UP, new PushUpKit());
        kits.put(TypeKit.DIGGER, new DiggerKit());
    }

    public void sendKit(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Kit Category", API.getMessageAPI().sendKitPrincipalModal(player));
        kitMenu.addButton(new ElementButton("Info Categories", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        kitMenu.addButton(new ElementButton("Classic Kits", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Premium Kits", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Specific Kits", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        player.showFormWindow(new ResponseFormWindow(kitMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageKits(player);
                            return;
                        case 1:
                            classicKits(player);
                            return;
                        case 2:
                            premiumKits(player);
                            return;
                        case 3:
                            specificKits(player);
                            break;
                    }
                }
            }
        }));
    }

    private void classicKits(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Classic Kit Category", API.getMessageAPI().sendKitPrincipalClassicModal(player));
        kitMenu.addButton(new ElementButton("Info Classic Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
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
                            sendInfoMessageClassicKits(player);
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

    private void premiumKits(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Specific Kit Category", API.getMessageAPI().sendKitPrincipalPremiumModal(player));
        kitMenu.addButton(new ElementButton("Info Specific Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        kitMenu.addButton(new ElementButton("Viper", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        player.showFormWindow(new ResponseFormWindow(kitMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessagePremiumKits(player);
                            return;
                        /*case 1:
                            return;
                        case 2:
                            return;
                        case 3:
                            return;
                        case 4:
                            return;
                        case 5:
                            break;*/
                    }
                }
            }
        }));
    }

    private void specificKits(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Specific Kit Category", API.getMessageAPI().sendKitPrincipalSpecificModal(player));
        kitMenu.addButton(new ElementButton("Info Specific Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        kitMenu.addButton(new ElementButton("Planter", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Stonner", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Lacker", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Woodie", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Push Up", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        kitMenu.addButton(new ElementButton("Digger", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        player.showFormWindow(new ResponseFormWindow(kitMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageSpecificKits(player);
                            return;
                        case 1:
                            kits.get(TypeKit.PLANTER).sendKit(player);
                            return;
                        case 2:
                            kits.get(TypeKit.STONNER).sendKit(player);
                            return;
                        case 3:
                            kits.get(TypeKit.LACKER).sendKit(player);
                            return;
                        case 4:
                            kits.get(TypeKit.WOODIE).sendKit(player);
                            return;
                        case 5:
                            kits.get(TypeKit.PUSH_UP).sendKit(player);
                            return;
                        case 6:
                            kits.get(TypeKit.DIGGER).sendKit(player);
                            break;
                    }
                }
            }
        }));
    }

    private void sendInfoMessageKits(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Category Kits");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageCategoryKits(player)));
        player.showFormWindow(infoMenu);
    }

    private void sendInfoMessageClassicKits(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Classic Kits");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageClassicKits(player)));
        player.showFormWindow(infoMenu);
    }

    private void sendInfoMessagePremiumKits(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Premium Kits");
        //infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessagePremiumKits(player)));
        player.showFormWindow(infoMenu);
    }

    private void sendInfoMessageSpecificKits(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Specific Kits");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageSpecificKits(player)));
        player.showFormWindow(infoMenu);
    }
}
