package matteroverdrive.blocks;

import matteroverdrive.Reference;
import matteroverdrive.blocks.includes.MOMatterEnergyStorageBlock;
import matteroverdrive.init.MatterOverdriveIcons;
import matteroverdrive.tile.TileEntityMachineSolarPanel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Simeon on 4/9/2015.
 */
public class BlockSolarPanel extends MOMatterEnergyStorageBlock
{
    IIcon iconTop;

    public BlockSolarPanel(Material material, String name) {
        super(material, name, true, false);

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        setHardness(20.0F);
        this.setResistance(5.0f);
        this.setHarvestLevel("pickaxe",2);
        setHasGui(true);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityMachineSolarPanel();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.iconTop = iconRegister.registerIcon(Reference.MOD_ID + ":" + "solar_panel");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            return this.iconTop;
        }

        return MatterOverdriveIcons.Base;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock(){return false;}
}