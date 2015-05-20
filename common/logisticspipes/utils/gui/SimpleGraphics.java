/*
 * Copyright (c) 2015  RS485
 *
 * "LogisticsPipes" is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://github.com/RS485/LogisticsPipes/blob/mc16/LICENSE.md
 */

package logisticspipes.utils.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

/**
 * Utils class for simple drawing methods.
 */
public final class SimpleGraphics {

	private SimpleGraphics() {
	}

	/**
	 * Draws a horizontal line from x1 to x2.
	 *
	 * @param x1 the start coordinate
	 * @param x2 the end coordinate
	 * @param y the y-coordinate the line is on
	 * @param color the color, which the line will have
	 * @param thickness the thickness, which the line will have
	 * @see net.minecraft.client.gui.Gui method drawHorizontalLine(int, int, int, int)
	 */
	public static void drawHorizontalLine(int x1, int x2, int y, int color, int thickness) {
		if (x2 < x1) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}

		Gui.drawRect(x1, y, x2 + 1, y + thickness, color);
	}

	/**
	 * Draws a vertical line from y1 to y2.
	 *
	 * @param x the x-coordinate the line is on
	 * @param y1 the start coordinate
	 * @param y2 the end coordinate
	 * @param color the color, which the line will have
	 * @param thickness the thickness, which the line will have
	 * @see net.minecraft.client.gui.Gui method drawVerticalLine(int, int, int, int)
	 */
	public static void drawVerticalLine(int x, int y1, int y2, int color, int thickness) {
		if (y2 < y1) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}

		Gui.drawRect(x, y1 + 1, x + thickness, y2, color);
	}

	/**
	 * Draws a rectangle with a vertical gradient between the specified colors.
	 *
	 * @param x1 the first x-coordinate of the rectangle
	 * @param y1 the first y-coordinate of the rectangle
	 * @param x2 the second x-coordinate of the rectangle
	 * @param y2 the second y-coordinate of the rectangle
	 * @param colorA the first color, starting from y1
	 * @param colorB the second color, ending in y2
	 * @see net.minecraft.client.gui.Gui method drawGradientRect(int, int, int, int, int, int)
	 */
	public static void drawGradientRect(int x1, int y1, int x2, int y2, int colorA, int colorB, double zLevel) {
		float alphaA = (float) (colorA >> 24 & 255) / 255.0F;
		float redA = (float) (colorA >> 16 & 255) / 255.0F;
		float greenA = (float) (colorA >> 8 & 255) / 255.0F;
		float blueA = (float) (colorA & 255) / 255.0F;
		float alphaB = (float) (colorB >> 24 & 255) / 255.0F;
		float redB = (float) (colorB >> 16 & 255) / 255.0F;
		float greenB = (float) (colorB >> 8 & 255) / 255.0F;
		float blueB = (float) (colorB & 255) / 255.0F;

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		// before: GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glShadeModel(GL11.GL_SMOOTH);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(redA, greenA, blueA, alphaA);
		tessellator.addVertex((double) x2, (double) y1, zLevel);
		tessellator.addVertex((double) x1, (double) y1, zLevel);
		tessellator.setColorRGBA_F(redB, greenB, blueB, alphaB);
		tessellator.addVertex((double) x1, (double) y2, zLevel);
		tessellator.addVertex((double) x2, (double) y2, zLevel);
		tessellator.draw();

		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
