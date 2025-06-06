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

package org.screamingsandals.bedwars.api.utils;

import org.jetbrains.annotations.ApiStatus;
import org.screamingsandals.bedwars.api.player.BWPlayer;
import org.screamingsandals.bedwars.api.special.SpecialItem;

/**
 * @author ScreamingSandals
 */
@ApiStatus.NonExtendable
public interface DelayFactory {

    /**
     * @return is delay active
     */
    boolean isDelayActive();

    /**
     * @return special item for which this delay is used
     */
    SpecialItem<?,?,?> getSpecialItem();

    /**
     * @return remaining delay
     */
    int getRemainDelay();

    /**
     * @return the game participant using this delay factory
     */
    BWPlayer getPlayer();
}
