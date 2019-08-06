package nycuro.kits.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;
import nycuro.api.API;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.kits.CommonKit;
import nycuro.kits.commands.KitsCommandManager;
import nycuro.kits.data.clasic.*;
import nycuro.kits.data.premium.*;
import nycuro.kits.data.specific.*;
import nycuro.kits.type.NameKit;
import nycuro.kits.type.StatusKit;
import nycuro.kits.type.TypeKit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class KitsAPI extends API {

    public Map<NameKit, CommonKit> kits = new HashMap<>();

    @Override
    public void registerCommands() {
        KitsCommandManager.registerAll(getMainAPI());
    }

    public void addKits() {
        // classic
        kits.put(NameKit.STARTER, new StarterKit()); // StartKit - Only for new people (Cannot be buyed or accesed)
        kits.put(NameKit.ENCHANTED_STARTER, new EnchantedStarterKit());
        kits.put(NameKit.GUARDIAN, new GuardianKit());
        kits.put(NameKit.KNIGHT, new KnightKit());
        kits.put(NameKit.PALADIN, new PaladinKit());
        kits.put(NameKit.SPARROW, new SparrowKit());

        // premium
        kits.put(NameKit.VIPER, new ViperKit());
        kits.put(NameKit.MASTER, new MasterKit());
        kits.put(NameKit.KILLER, new KillerKit());
        kits.put(NameKit.DETONATOR, new DetonatorKit());
        kits.put(NameKit.TURRET_MONKEY, new TurretMonkeyKit());

        // specific
        kits.put(NameKit.PLANTER, new PlanterKit());
        kits.put(NameKit.STONNER, new StonnerKit());
        kits.put(NameKit.LACKER, new LackerKit());
        kits.put(NameKit.WOODIE, new WoodieKit());
        kits.put(NameKit.PUSH_UP, new PushUpKit());
        kits.put(NameKit.DIGGER, new DiggerKit());
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
        TextFormat color;
        for (NameKit kit : NameKit.values()) {
            if (!(kits.get(kit).getType().equals(TypeKit.CLASSIC)) || kit.equals(NameKit.STARTER)) continue;
            if (kits.get(kit).getStatus(player).equals(StatusKit.LOCKED)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            kitMenu.addButton(new ElementButton(kit.getName() + API.getMainAPI().empty + TextFormat.GRAY + "[" + color + kits.get(kit).getStatus(player) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(kitMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageClassicKits(player);
                            return;
                        case 1:
                            kits.get(NameKit.ENCHANTED_STARTER).sendKit(player);
                            return;
                        case 2:
                            kits.get(NameKit.GUARDIAN).sendKit(player);
                            return;
                        case 3:
                            kits.get(NameKit.KNIGHT).sendKit(player);
                            return;
                        case 4:
                            kits.get(NameKit.PALADIN).sendKit(player);
                            return;
                        case 5:
                            kits.get(NameKit.SPARROW).sendKit(player);
                            break;
                    }
                }
            }
        }));
    }

    private void premiumKits(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Premium Kit Category", API.getMessageAPI().sendKitPrincipalPremiumModal(player));
        kitMenu.addButton(new ElementButton("Info Premium Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        TextFormat color;
        for (NameKit kit : NameKit.values()) {
            if (!(kits.get(kit).getType().equals(TypeKit.PREMIUM)) || kit.equals(NameKit.STARTER)) continue;
            if (kits.get(kit).getStatus(player).equals(StatusKit.LOCKED)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            kitMenu.addButton(new ElementButton(kit.getName() + API.getMainAPI().empty + TextFormat.GRAY + "[" + color + kits.get(kit).getStatus(player) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(kitMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessagePremiumKits(player);
                            return;
                        case 1:
                            kits.get(NameKit.VIPER).sendKit(player);
                            return;
                        case 2:
                            kits.get(NameKit.MASTER).sendKit(player);
                            return;
                        case 3:
                            kits.get(NameKit.KILLER).sendKit(player);
                            return;
                        case 4:
                            kits.get(NameKit.DETONATOR).sendKit(player);
                            return;
                        case 5:
                            kits.get(NameKit.TURRET_MONKEY).sendKit(player);
                            break;
                    }
                }
            }
        }));
    }

    private void specificKits(Player player) {
        FormWindowSimple kitMenu = new FormWindowSimple("Specific Kit Category", API.getMessageAPI().sendKitPrincipalSpecificModal(player));
        kitMenu.addButton(new ElementButton("Info Specific Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        TextFormat color;
        for (NameKit kit : NameKit.values()) {
            if (!(kits.get(kit).getType().equals(TypeKit.SPECIFIC)) || kit.equals(NameKit.STARTER)) continue;
            if (kits.get(kit).getStatus(player).equals(StatusKit.LOCKED)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            kitMenu.addButton(new ElementButton(kit.getName() + API.getMainAPI().empty + TextFormat.GRAY + "[" + color + kits.get(kit).getStatus(player) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        }
        player.showFormWindow(new ResponseFormWindow(kitMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageSpecificKits(player);
                            return;
                        case 1:
                            kits.get(NameKit.PLANTER).sendKit(player);
                            return;
                        case 2:
                            kits.get(NameKit.STONNER).sendKit(player);
                            return;
                        case 3:
                            kits.get(NameKit.LACKER).sendKit(player);
                            return;
                        case 4:
                            kits.get(NameKit.WOODIE).sendKit(player);
                            return;
                        case 5:
                            kits.get(NameKit.PUSH_UP).sendKit(player);
                            return;
                        case 6:
                            kits.get(NameKit.DIGGER).sendKit(player);
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
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessagePremiumKits(player)));
        player.showFormWindow(infoMenu);
    }

    private void sendInfoMessageSpecificKits(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Specific Kits");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageSpecificKits(player)));
        player.showFormWindow(infoMenu);
    }
}
