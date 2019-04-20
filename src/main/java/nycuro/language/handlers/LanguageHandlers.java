package nycuro.language.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerSettingsRespondedEvent;
import cn.nukkit.form.response.FormResponseCustom;
import gt.creeperface.holograms.Holograms;
import nycuro.API;
import nycuro.database.Database;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class LanguageHandlers implements Listener {

    @EventHandler
    public void onModals(PlayerSettingsRespondedEvent event) {
        Player player = event.getPlayer();
        Holograms holograms = new Holograms();
        int form = ((FormResponseCustom) event.getResponse()).getDropdownResponse(4).getElementID();
        //int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (form) {
            case 0:
                Database.profileHub.get(player.getUniqueId()).setLanguage(0);
                API.getMessageAPI().sendLangMessage(player);
                //holograms.setLanguageSelector(p -> lang);
                ///holograms.onLanguageChanged(player);
                break;
            case 1:
                Database.profileHub.get(player.getUniqueId()).setLanguage(1);
                API.getMessageAPI().sendLangMessage(player);
                //holograms.setLanguageSelector(p -> lang);
                //holograms.onLanguageChanged(player);
                break;
        }
    }
}
