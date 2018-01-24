package com.jtripled.wildlife.mob.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 *
 * @author jtripled
 */
public class ModelSquirrel extends ModelBase
{
    private final ModelRenderer body;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer head;
    private final ModelRenderer tail;
    private final ModelRenderer fluff1;
    private final ModelRenderer fluff2;
    private final ModelRenderer fluff3;

    public ModelSquirrel()
    {
        textureWidth = 32;
        textureHeight = 32;
        setTextureOffset("head.head", 0, 0);
        setTextureOffset("head.ear2", 16, 0);
        setTextureOffset("head.ear1", 16, 0);
        setTextureOffset("tail.fluff1", 0, 20);
        setTextureOffset("tail.base", 0, 18);
        setTextureOffset("tail.fluff2", 0, 20);
        setTextureOffset("tail.fluff3", 0, 26);

        body = new ModelRenderer(this, 0, 8);
        body.addBox(-2F, -1F, -2F, 4, 3, 5);
        body.setRotationPoint(0F, 21F, 0F);
        body.setTextureSize(32, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(0F, 0F, 0F, 1, 1, 1);
        leg1.setRotationPoint(-2F, 23F, 2F);
        leg1.setTextureSize(32, 32);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, 0F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(0F, 0F, 0F, 1, 1, 1);
        leg2.setRotationPoint(1F, 23F, 2F);
        leg2.setTextureSize(32, 32);
        leg2.mirror = true;
        setRotation(leg2, 0F, 0F, 0F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(0F, 0F, 0F, 1, 1, 1);
        leg3.setRotationPoint(-2F, 23F, -2F);
        leg3.setTextureSize(32, 32);

        setRotation(leg3, 0F, 0F, 0F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(0F, 0F, 0F, 1, 1, 1);
        leg4.setRotationPoint(1F, 23F, -2F);
        leg4.setTextureSize(32, 32);

        setRotation(leg4, 0F, 0F, 0F);
        head = new ModelRenderer(this, "head");
        head.setRotationPoint(0F, 22F, -2F);
        setRotation(head, 0F, 0F, 0F);

        head.addBox("head", -2F, -5F, -3F, 4, 4, 4);
        head.addBox("ear2", -2F, -6F, -0.5F, 1, 1, 1);
        head.addBox("ear1", 1F, -6F, -0.5F, 1, 1, 1);

        tail = new ModelRenderer(this, "tail");
        tail.setRotationPoint(0F, 21F, 2F);

        tail.addBox("base", -0.5F, -1.5F, 0.5F, 1, 1, 1);

        fluff1 = new ModelRenderer(this, 0, 20);
        fluff1.addBox(-1.5F, -4F, 1F, 3, 3, 3);
        tail.addChild(fluff1);

        fluff2 = new ModelRenderer(this, 0, 20);
        fluff2.addBox(0F, -3F, -1.5F, 3, 3, 3);
        fluff2.setRotationPoint(-1.5F, -4F, 2.5F);
        fluff1.addChild(fluff2);

        fluff3 = new ModelRenderer(this, 0, 26);
        fluff3.addBox(1.5F, -3F, -1.5F, 3, 3, 3);
        fluff3.setRotationPoint(-1.5F, -3F, 0F);
        fluff2.addChild(fluff3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        head.render(f5);
        tail.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;

        if (par2 > 0.2)
        {
            float wiggle = Math.min(par2, 0.6F);
            this.tail.rotateAngleX  = (MathHelper.cos(par3 * 0.6662F) * 1.0F - (float)Math.PI / 3)  * wiggle;
            this.fluff2.rotateAngleX = MathHelper.cos(par3 * 0.7774F) * 1.2F * wiggle;
            this.fluff3.rotateAngleX = MathHelper.cos(par3 * 0.8886F + (float)Math.PI / 2) * 1.4F * wiggle;
        }
        else
        {
            this.tail.rotateAngleX   = 0.2F + MathHelper.cos(par3 * 0.3335F) * 0.15F;
            this.fluff2.rotateAngleX = 0.1F + MathHelper.cos(par3 * 0.4445F) * 0.20F;
            this.fluff3.rotateAngleX = 0.1F + MathHelper.cos(par3 * 0.5555F) * 0.25F;
        }

    }
}