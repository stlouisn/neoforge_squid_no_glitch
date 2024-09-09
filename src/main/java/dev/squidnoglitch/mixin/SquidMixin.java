package dev.squidnoglitch.mixin;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Squid.class)
public class SquidMixin extends WaterAnimal {

  SquidMixin() {
    super(null, null);
  }

  /**
   * Fix for <a href="https://bugs.mojang.com/browse/MC-132473">MC-132473</a>
   * Decreasing Y movement vector to {@code 0.15f} which should stop squids become stuck on land.
   */
  @Mixin(targets = "net.minecraft.world.entity.animal.Squid$SquidRandomMovementGoal", priority = 1001)
  public static class SquidRandomMovementGoal_MC132473 {

    @ModifyConstant(method = "tick", constant = @Constant(floatValue = 0.2F, ordinal = 1))
    private float squidnoglitch$modifyYMovementSpeed(float oldSpeed) {
      return 0.15F;
    }
  }

  /**
   * Fix <a href="https://bugs.mojang.com/browse/MC-212687">MC-212687</a>
   * Removing {@link net.minecraft.world.entity.LivingEntity#getNoActionTime()} check will restore the movement of squid if the player is far from them.</p>
   */
  @Mixin(targets = "net.minecraft.world.entity.animal.Squid$SquidRandomMovementGoal")
  public abstract static class SquidRandomMovementGoal_MC212687 extends Goal {

    @Shadow
    @Final
    private Squid squid;

    @Override
    public void tick() {
      if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.isInWater() || !this.squid.hasMovementVector()) {
        var f = this.squid.getRandom().nextFloat() * (float) (Math.PI * 2);
        var tx = Mth.cos(f) * 0.2F;
        var ty = -0.1F + this.squid.getRandom().nextFloat() * 0.2F; /* This constant will be replaced by `SquidRandomMovementGoal_MC132473` */
        var tz = Mth.sin(f) * 0.2F;
        this.squid.setMovementVector(tx, ty, tz);
      }
    }
  }
}