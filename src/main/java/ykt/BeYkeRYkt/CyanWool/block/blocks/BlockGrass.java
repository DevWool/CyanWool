package ykt.BeYkeRYkt.CyanWool.block.blocks;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;
import org.spacehq.mc.protocol.data.game.values.world.effect.ParticleEffect;

import ykt.BeYkeRYkt.CyanWool.api.Register;
import ykt.BeYkeRYkt.CyanWool.api.block.BlockSound;
import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;
import ykt.BeYkeRYkt.CyanWool.block.CyanBlockType;

public class BlockGrass extends CyanBlockType {

    public BlockGrass() {
        super(2, 0);
        BlockSound dig = new BlockSound(GenericSound.DIG_GRASS, 1.0F, 1.0F);
        BlockSound step = new BlockSound(GenericSound.GRASS_STEP, 1.0F, 1.0F);

        setBreakSound(dig);
        setPlaceSound(dig);
        setDigSound(dig);
        setStepSound(step);
        setDrop(true);
        setParticle(ParticleEffect.BREAK_BLOCK);// ?

        ItemStack item = Register.getItemStack(3);// dirt id
        getDrop().add(item);
    }

}