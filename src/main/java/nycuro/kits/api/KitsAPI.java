package nycuro.kits.api;

import cn.nukkit.player.Player;
import nycuro.kits.CommonKit;
import nycuro.kits.commands.KitsCommandManager;
import nycuro.kits.data.clasic.*;
import nycuro.kits.data.premium.*;
import nycuro.kits.data.specific.*;
import nycuro.kits.type.NameKit;

import java.util.HashMap;
import java.util.Map;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class KitsAPI {

    public Map<NameKit, CommonKit> kits = new HashMap<>();

    public void registerCommands() {
        KitsCommandManager.registerAll(mainAPI);
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
        /*FormWindowSimple kitMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("kits.form.category.first"),
                messageAPI.messagesObject.translateMessage("kits.form.category.top"));
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
        }));*/
    }

    private void classicKits(Player player) {
        /*FormWindowSimple kitMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("kits.form.category.classic.first"),
                messageAPI.messagesObject.translateMessage("kits.form.category.classic.top"));
        kitMenu.addButton(new ElementButton("Info Classic Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        TextFormat color;
        for (NameKit kit : NameKit.values()) {
            if (!(kits.get(kit).getType().equals(TypeKit.CLASSIC)) || kit.equals(NameKit.STARTER)) continue;
            if (kits.get(kit).getStatus(player).equals(StatusKit.LOCKED)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            kitMenu.addButton(new ElementButton(kit.getName() + mainAPI.empty + TextFormat.GRAY + "[" + color + kits.get(kit).getStatus(player) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
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
        }));*/
    }

    private void premiumKits(Player player) {
        /*FormWindowSimple kitMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("kits.form.category.premium.first"),
                messageAPI.messagesObject.translateMessage("kits.form.category.premium.top"));
        kitMenu.addButton(new ElementButton("Info Premium Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        TextFormat color;
        for (NameKit kit : NameKit.values()) {
            if (!(kits.get(kit).getType().equals(TypeKit.PREMIUM)) || kit.equals(NameKit.STARTER)) continue;
            if (kits.get(kit).getStatus(player).equals(StatusKit.LOCKED)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            kitMenu.addButton(new ElementButton(kit.getName() + mainAPI.empty + TextFormat.GRAY + "[" + color + kits.get(kit).getStatus(player) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
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
        }));*/
    }

    private void specificKits(Player player) {
        /*FormWindowSimple kitMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("kits.form.category.specific.first"),
            messageAPI.messagesObject.translateMessage("kits.form.category.specific.top"));
        kitMenu.addButton(new ElementButton("Info Specific Kits", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        TextFormat color;
        for (NameKit kit : NameKit.values()) {
            if (!(kits.get(kit).getType().equals(TypeKit.SPECIFIC)) || kit.equals(NameKit.STARTER)) continue;
            if (kits.get(kit).getStatus(player).equals(StatusKit.LOCKED)) color = TextFormat.RED;
            else color = TextFormat.GREEN;
            kitMenu.addButton(new ElementButton(kit.getName() + mainAPI.empty + TextFormat.GRAY + "[" + color + kits.get(kit).getStatus(player) + TextFormat.GRAY + "]", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
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
        }));*/
    }

    private void sendInfoMessageKits(Player player) {
        /*FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("kits.form.category.info.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.info.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.info.classic")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.info.premium")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.info.specific")));
        player.showFormWindow(infoMenu);*/
    }

    private void sendInfoMessageClassicKits(Player player) {
        /*FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("kits.form.category.classic.info.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.classic.info.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.classic.info.ench_starter")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.classic.info.guardian")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.classic.info.knight")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.classic.info.paladin")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.classic.info.sparrow")));
        player.showFormWindow(infoMenu);*/
    }

    private void sendInfoMessagePremiumKits(Player player) {
        /*FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("kits.form.category.premium.info.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.premium.info.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.premium.info.viper")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.premium.info.master")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.premium.info.killer")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.premium.info.detonator")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.premium.info.turr_monkey")));
        player.showFormWindow(infoMenu);*/
    }

    private void sendInfoMessageSpecificKits(Player player) {
        /*FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.planter")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.stonner")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.lacker")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.woodie")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.push_up")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("kits.form.category.specific.info.digger")));
        player.showFormWindow(infoMenu);*/
    }
}
