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

package org.screamingsandals.bedwars.api.upgrades;

import org.screamingsandals.bedwars.api.game.Game;

/**
 * @author Bedwars Team
 *
 */
public interface Upgrade {
	
	/**
	 * 
	 * @return registered name of this upgrade
	 */
	String getName();
	
	/**
	 * 
	 * @return identificator of this upgrade instance
	 */
	String getInstanceName();
	
	/**
	 * 
	 * @return current level of upgrade
	 */
	double getLevel();
	
	/**
	 * Sets level of this upgrade
	 * 
	 * @param level Current level
	 */
	void setLevel(double level);
	
	/**
	 * Add levels to this upgrade
	 * 
	 * @param level Levels that will be added to current level
	 */
	void increaseLevel(double level);
	
	/**
	 * 
	 * @return initial level of upgrade
	 */
	double getInitialLevel();

	/**
	 * Called when upgrade is registered
	 * 
	 * @param game Game when upgrade is activated
	 */
	default void onUpgradeRegistered(Game game) {
		
	}
	
	/**
	 * Called when upgrade is unregistered
	 * 
	 * @param game Game when upgrade is deactivated
	 */
	default void onUpgradeUnregistered(Game game) {
		
	}
}
