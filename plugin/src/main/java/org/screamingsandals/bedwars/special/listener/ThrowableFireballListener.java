/*
 * Copyright (C) 2022 ScreamingSandals
 *
 * This file is part of Screaming BedWars.
 *
 * Screaming BedWars is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Screaming BedWars is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Screaming BedWars. If not, see <https://www.gnu.org/licenses/>.
 */

package org.screamingsandals.bedwars.special.listener;

import org.screamingsandals.bedwars.entities.EntitiesManagerImpl;
import org.screamingsandals.bedwars.events.ApplyPropertyToBoughtItemEventImpl;
import org.screamingsandals.bedwars.player.PlayerManagerImpl;
import org.screamingsandals.bedwars.utils.ItemUtils;
import org.screamingsandals.bedwars.utils.MiscUtils;
import org.screamingsandals.lib.event.OnEvent;
import org.screamingsandals.lib.event.player.SPlayerInteractEvent;
import org.screamingsandals.lib.item.builder.ItemFactory;
import org.screamingsandals.lib.utils.annotations.Service;

@Service
public class ThrowableFireballListener {
    private static final String THROWABLE_FIREBALL_PREFIX = "Module:ThrowableFireball:";

    @OnEvent
    public void onThrowableFireballRegistered(ApplyPropertyToBoughtItemEventImpl event) {
        if (event.getPropertyName().equalsIgnoreCase("throwablefireball")) {
            event.setStack(ItemUtils.saveData(event.getStack(), applyProperty(event)));
        }
    }

    @OnEvent
    public void onFireballThrow(SPlayerInteractEvent event) {
        var player = event.player();

        if (!PlayerManagerImpl.getInstance().isPlayerInGame(player)) {
            return;
        }

        final var item = event.item();
        if (item != null) {
            var unhash = ItemUtils.getIfStartsWith(item, THROWABLE_FIREBALL_PREFIX);
            if (unhash != null && (event.action() == SPlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.action() == SPlayerInteractEvent.Action.RIGHT_CLICK_AIR)) {
                var propertiesSplit = unhash.split(":");
                var explosion = (float) Double.parseDouble(propertiesSplit[2]);

                var fireball = player.launchProjectile("minecraft:fireball").orElseThrow();
                fireball.setMetadata("is_incendiary", false);
                fireball.setMetadata("yield", explosion);
                EntitiesManagerImpl.getInstance().addEntityToGame(fireball, PlayerManagerImpl.getInstance().getGameOfPlayer(player).orElseThrow());

                event.cancelled(true);

                var stack2 = item.withAmount(1);
                try {
                    if (player.getPlayerInventory().getItemInOffHand().equals(stack2)) {
                        player.getPlayerInventory().setItemInOffHand(ItemFactory.getAir());
                    } else {
                        player.getPlayerInventory().removeItem(stack2);
                    }
                } catch (Throwable e) {
                    player.getPlayerInventory().removeItem(stack2);
                }

                player.forceUpdateInventory();
            }
        }
    }

    private String applyProperty(ApplyPropertyToBoughtItemEventImpl event) {
        return THROWABLE_FIREBALL_PREFIX
                + MiscUtils.getDoubleFromProperty(
                "explosion", "specials.throwable-fireball.explosion", event);
    }



}
