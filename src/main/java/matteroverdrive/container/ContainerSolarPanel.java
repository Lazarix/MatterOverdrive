/*
 * This file is part of Matter Overdrive
 * Copyright (c) 2015., Simeon Radivoev, All rights reserved.
 *
 * Matter Overdrive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matter Overdrive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

package matteroverdrive.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matteroverdrive.tile.TileEntityMachineSolarPanel;
import matteroverdrive.util.MOContainerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

/**
 * Created by Simeon on 4/9/2015.
 */
public class ContainerSolarPanel extends ContainerMachine<TileEntityMachineSolarPanel>
{
    int lastChargeAmount;

    public ContainerSolarPanel(InventoryPlayer inventory, TileEntityMachineSolarPanel machine)
    {
        super(inventory, machine);
    }

    @Override
    public void init(InventoryPlayer inventory)
    {
        addAllSlotsFromInventory(machine.getInventoryContainer());
        MOContainerHelper.AddPlayerSlots(inventory, this, 45, 89, true, true);
    }

    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 1, this.machine.getChargeAmount());
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (Object crafter : this.crafters) {
            ICrafting icrafting = (ICrafting) crafter;

            if (this.lastChargeAmount != this.machine.getChargeAmount()) {
                icrafting.sendProgressBarUpdate(this, 1, this.machine.getChargeAmount());
            }
        }

        this.lastChargeAmount = this.machine.getChargeAmount();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot,int newValue)
    {
        super.updateProgressBar(slot,newValue);
        if(slot == 1)
            this.machine.setChargeAmount((byte)newValue);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
