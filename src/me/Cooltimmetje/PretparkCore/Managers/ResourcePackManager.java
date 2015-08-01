/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Tim Medema
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.Cooltimmetje.PretparkCore.Managers;

import de.inventivegames.rpapi.ResourcePackStatusEvent;
import de.inventivegames.rpapi.Status;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * This class has been created on 31-7-2015 at 17:52 by cooltimmetje.
 */
public class ResourcePackManager implements Listener{

    public static void setRP(Player p){
        ChatUtils.sendMsgTag(p, "ResourcePack", "ResourcePack versturen... Druk op &lJA &aals er om een bevestiging word gevraagd!");
        p.setResourcePack("https://www.dropbox.com/s/cs15399fk8d35sy/PingFinity.zip?dl=1");
    }

    @EventHandler
    public void onResourcePackStatus(ResourcePackStatusEvent event){
        Player p = event.getPlayer();
        final Player pfinal = event.getPlayer();
        Status status = event.getStatus();
        if(status == Status.ACCEPTED){
            ChatUtils.sendMsgTag(p, "ResourcePack", "Bevestiging ontvangen! De download start nu!");
        } else if(status == Status.DECLINED){
            ChatUtils.sendMsgTag(p, "ResourcePack", ChatUtils.error + "Je hebt de ResourcePack niet geaccepteerd, je moet dit doen om te kunnen spelen! " +
                    "Klik op de volgende link voor meer informatie: [TODO]");
            ChatUtils.sendMsgTag(p, "ResourcePack", ChatUtils.error + "Je zal over 10 seconden gekickt worden!");
            ScheduleUtils.scheduleTask(200, new Runnable() {
                @Override
                public void run() {
                    pfinal.kickPlayer(MiscUtils.color("&9ResourcePacks> &aJe hebt de ResourcePack niet geaccepteerd, je moet dit doen om te kunnen spelen! " +
                            "Ga naar de volgende link voor meer informatie: [TODO]"));
                }
            });
        } else if(status == Status.SUCCESSFULLY_LOADED){
            ChatUtils.sendMsgTag(p, "ResourcePack", "Download geslaagd! Applausje voor je zelf! ^.^ &lVeel plezier!");
        } else if(status == Status.FAILED_DOWNLOAD){
            ChatUtils.sendMsgTag(p, "ResourcePack", ChatUtils.error + "Oeps! Er ging iets mis, we gaan je over 10 seconden kicken, daarna kun je opnieuw joinen om het nog eens te proberen!");
            ScheduleUtils.scheduleTask(200, new Runnable() {
                @Override
                public void run() {
                    pfinal.kickPlayer(MiscUtils.color("&9ResourcePacks> &aEr ging iets mis tijdens het downloaden van de resourcepack. Sorry! Join opnieuw om het nog eens te proberen!"));
                }
            });
        }
    }

}
