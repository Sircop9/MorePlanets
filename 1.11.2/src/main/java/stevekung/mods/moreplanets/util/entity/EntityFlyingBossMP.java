package stevekung.mods.moreplanets.util.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlyingBossMP extends EntityLiving implements IMob
{
    public EntityFlyingBossMP(World world)
    {
        super(world);
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    protected void updateFallState(double y, boolean onGround, IBlockState state, BlockPos pos) {}

    @Override
    public void moveEntityWithHeading(float strafe, float forward)
    {
        if (this.isInWater())
        {
            this.moveRelative(strafe, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.isInLava())
        {
            this.moveRelative(strafe, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float f2 = 0.91F;

            if (this.onGround)
            {
                f2 = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            this.moveRelative(strafe, forward, this.onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;

            if (this.onGround)
            {
                f2 = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
            }
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= f2;
            this.motionY *= f2;
            this.motionZ *= f2;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    @Override
    public boolean isOnLadder()
    {
        return false;
    }

    @Override
    public boolean isNonBoss()
    {
        return false;
    }
}