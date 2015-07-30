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

package me.Cooltimmetje.PretparkCore.Events.UserInterfaces;

import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 30-7-2015 at 09:52 by cooltimmetje.
 */
public class RideUI implements Listener {

    public static void openUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Attracties");

        int slot = 1;
        for(int i : Vars.rideName.keySet()){
            int color = 14;
            String status = "&cdicht";
            if(Vars.rideStatus.get(i)){
                color = 5;
                status = "&2open";
            }
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) color, "&a" + Vars.rideName.get(i) + " &8\u00BB " + status, "&aKlik om te warpen!", inv, slot);
            slot = slot + 1;
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Attracties")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                switch (event.getCurrentItem().getType()){
                    default:
                        break;
                    case STAINED_CLAY:
                        p.closeInventory();
                        if(Vars.rideSlot.containsKey(event.getSlot())) {
                            int id = Vars.rideSlot.get(event.getSlot());
                            Location loc = Vars.rideLocation.get(id);
                            String name = Vars.rideName.get(id);
                            p.teleport(loc.add(0.5, 0, 0.5));
                            ChatUtils.sendMsgTag(p, "Teleport", "Je bent geteleporteerd naar de &6" + name + "&a!");
                        } else {
                            break;
                        }
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

}
