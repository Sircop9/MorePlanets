package stevekung.mods.moreplanets.client.renderer.ccl;

import java.util.HashMap;
import java.util.Map;

import codechicken.lib.render.CCModelState;
import codechicken.lib.util.TransformUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.TRSRTransformation;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.stevekunglib.utils.ClientRegistryUtils;

public class RenderBlackHoleStorage extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegistryUtils.renderTESR(new TileEntityBlackHoleStorage());
        GlStateManager.enableBlend();
    }

    @Override
    protected CCModelState getCustomTransforms()
    {
        Map<TransformType, TRSRTransformation> map = new HashMap<>();
        TRSRTransformation thirdPerson = TransformUtils.create(0, 2.5F, 0, 75, 45, 0, 0.375F);
        map.put(TransformType.GUI, TransformUtils.create(0, 0.35F, 0, 30, 45, 0, 0.675F));
        map.put(TransformType.GROUND, TransformUtils.create(0, 3, 0, 0, 0, 0, 0.25F));
        map.put(TransformType.FIXED, TransformUtils.create(0, 0, 0, 0, 0, 0, 0.5F));
        map.put(TransformType.THIRD_PERSON_RIGHT_HAND, thirdPerson);
        map.put(TransformType.THIRD_PERSON_LEFT_HAND, thirdPerson);
        map.put(TransformType.FIRST_PERSON_RIGHT_HAND, TransformUtils.create(0, 0, 0, 0, 45, 0, 0.4F));
        map.put(TransformType.FIRST_PERSON_LEFT_HAND, TransformUtils.create(0, 0, 0, 0, 225, 0, 0.4F));
        return new CCModelState(map);
    }
}