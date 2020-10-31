package net.baumarkt.advanced.essentials.utils.teleport.ask.manager;

import com.google.common.collect.Lists;
import net.baumarkt.advanced.essentials.utils.teleport.ask.objects.TeleportAsk;

import java.util.Collection;
import java.util.UUID;

public class TeleportAskManager {

    public static final Collection<TeleportAsk> TELEPORT_ASKS = Lists.newArrayList();

    public static TeleportAsk getAsk(final UUID uuid, final UUID to){
        for (TeleportAsk teleportAsk : TELEPORT_ASKS) {
            if(teleportAsk.getPlayer().getPlayer().getUniqueId().equals(uuid)
                    && teleportAsk.getTo().getPlayer().getUniqueId().equals(to)){

                return teleportAsk;
            }
        }

        return null;
    }

}
