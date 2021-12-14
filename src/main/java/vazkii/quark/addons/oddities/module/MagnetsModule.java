package vazkii.quark.addons.oddities.module;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.addons.oddities.block.MagnetBlock;
import vazkii.quark.addons.oddities.block.MovingMagnetizedBlock;
import vazkii.quark.addons.oddities.block.be.MagnetTileEntity;
import vazkii.quark.addons.oddities.block.be.MagnetizedBlockTileEntity;
import vazkii.quark.addons.oddities.client.render.MagnetizedBlockTileEntityRenderer;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.module.config.Config;

@LoadModule(category = ModuleCategory.ODDITIES)
public class MagnetsModule extends QuarkModule {
	
    public static BlockEntityType<MagnetTileEntity> magnetType;
    public static BlockEntityType<MagnetizedBlockTileEntity> magnetizedBlockType;
    
    @Config(description = "Any items you place in this list will be derived so that any block made of it will become magnetizable") 
    public static List<String> magneticDerivationList = Lists.newArrayList("minecraft:iron_ingot");
    
    @Config public static List<String> magneticWhitelist = Lists.newArrayList("minecraft:chipped_anvil", "minecraft:damaged_anvil");
    @Config public static List<String> magneticBlacklist = Lists.newArrayList("minecraft:tripwire_hook");
	
	public static Block magnet;
	public static Block magnetized_block;

	@Override
	public void construct() {
		magnet = new MagnetBlock(this);
		magnetized_block = new MovingMagnetizedBlock(this);
		
		magnetType = BlockEntityType.Builder.of(MagnetTileEntity::new, magnet).build(null);
		RegistryHelper.register(magnetType, "magnet");

		magnetizedBlockType = BlockEntityType.Builder.of(MagnetizedBlockTileEntity::new, magnetized_block).build(null);
		RegistryHelper.register(magnetizedBlockType, "magnetized_block");
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientSetup() {
		BlockEntityRenderers.register(magnetizedBlockType, MagnetizedBlockTileEntityRenderer::new);
	}
	
}