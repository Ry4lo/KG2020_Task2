package com.company;

import java.awt.*;

import static com.company.DrawPanel.*;

public class WuLineDrawer implements LineDrawer {

    public PixelDrawer pd;

    public WuLineDrawer(PixelDrawer g) {
        this.pd = g;
    }

    static float fractionalPart(float x) {
        int tmp = (int) x;
        return x - tmp;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        fline(x1, y1, x2, y2);
    }

    public void fline(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1, dy = y2 - y1;
        float gradient;
        if (Math.abs(dy) >= Math.abs(dx)) {
            if (y2 < y1) {
                x1 += x2;
                x2 = x1 - x2;
                x1 -= x2;
                y1 += y2;
                y2 = y1 - y2;
                y1 -= y2;
            }
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            for (int y = y1; y < y2; ++y) {
                pd.setPixel((int) interx - 1, y, new Color(110, 30, 170, (int) (255 - fractionalPart(interx) * 255)));
                pd.setPixel((int) interx, y, new Color(110, 30, 170, (int) (fractionalPart(interx) * 255)));
                interx += gradient;
            }
        } else {
            if (x2 <= x1) {
                x1 += x2;
                x2 = x1 - x2;
                x1 -= x2;
                y1 += y2;
                y2 = y1 - y2;
                y1 -= y2;
            }
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            for (int x = x1; x < x2; ++x) {
                pd.setPixel(x, (int) intery - 1, new Color(110, 30, 170, (int) (255 - fractionalPart(intery) * 255)));
                pd.setPixel(x, (int) intery, new Color(110, 30, 170, (int) (fractionalPart(intery) * 255)));
                intery += gradient;
            }
        }
    }
}
