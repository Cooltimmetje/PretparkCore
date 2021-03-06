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

package me.Cooltimmetje.PretparkCore.Events;

import me.Cooltimmetje.PretparkCore.Events.UserInterfaces.ControlUI;
import me.Cooltimmetje.PretparkCore.Events.UserInterfaces.ProfileUI;
import me.Cooltimmetje.PretparkCore.Events.UserInterfaces.RideUI;
import me.Cooltimmetje.PretparkCore.Events.UserInterfaces.SwagUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 29-7-2015 at 15:55 by cooltimmetje.
 */
public class InventoryTriggers implements Listener {

    @EventHandler
    public void onRightClickItem(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getItem() != null){
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().hasItemMeta()) {
                    switch (event.getItem().getType()) {
                        default:
                            break;
                        case SKULL_ITEM:
                            SkullMeta skullMeta = (SkullMeta) event.getItem().getItemMeta();
                            if(skullMeta.getOwner().equals(p.getName())) {
                                event.setCancelled(true);
                                ProfileUI.openUI(p);
                            }
                            break;
                        case MINECART:
                            event.setCancelled(true);
                            RideUI.openUI(p);
                            break;
                        case COMMAND_MINECART:
                            event.setCancelled(true);
                            ControlUI.openUI(p);
                            break;
                        case CHEST:
                            event.setCancelled(true);
                            SwagUI.openUI(p);
                            break;
                    }
                }
            }
        }
    }

}
