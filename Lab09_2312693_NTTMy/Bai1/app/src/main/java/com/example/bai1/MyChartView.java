package com.example.bai1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class MyChartView extends View {
    private Paint paint;
    private List<Float> dataList = new ArrayList<>();
    private int chartType = 0; // 0: Cột, 1: Tròn
    private float animationPercent = 0f; // Chạy từ 0.0 đến 1.0
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN};

    public MyChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dataList.isEmpty()) return;

        if (chartType == 0) {
            drawBarChart(canvas);
        } else {
            drawPieChart(canvas);
        }
    }
    // Hàm cập nhật phần trăm để vẽ (gọi từ luồng phụ)
    public void setAnimationPercent(float percent) {
        this.animationPercent = percent;
        postInvalidate(); // Quan trọng: dùng postInvalidate() vì gọi từ Thread phụ
    }
    public void updateChartDataOnly(List<Float> data, int type) {
        this.dataList = data;
        this.chartType = type;
        this.animationPercent = 0f;
    }

    private void drawBarChart(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();
        float barWidth = (width - 100) / dataList.size();
        float maxVal = 0;
        for (float v : dataList) if (v > maxVal) maxVal = v;

        for (int i = 0; i < dataList.size(); i++) {
            paint.setColor(colors[i % colors.length]);
            // CHIỀU CAO thực tế nhân với phần trăm hiệu ứng
            float finalHeight = (dataList.get(i) / maxVal) * (getHeight() - 100);
            float currentHeight = finalHeight * animationPercent;

            float left = 50 + i * barWidth;
            float top = getHeight() - currentHeight - 50;
            float right = left + barWidth - 10;
            float bottom = height - 50;

            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawPieChart(Canvas canvas) {
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        float radius = Math.min(centerX, centerY) - 100;
        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        float total = 0;
        for (float v : dataList) total += v;

        float startAngle = 0;
        // Khoảng cách để vẽ Legend phía dưới
        float legendStartY = centerY + radius + 100;
        float legendSpacing = getWidth() / (dataList.size() + 1);
        for (int i = 0; i < dataList.size(); i++) {
            float sweepAngle = (dataList.get(i) / total) * 360f;
            float currentSweepAngle = sweepAngle * animationPercent;
            int color = colors[i % colors.length];
            paint.setColor(color);
            canvas.drawArc(rectF, startAngle, currentSweepAngle, true, paint);
            // Legend sẽ hiện ra cùng lúc với Animation
            drawLegend(canvas, dataList.get(i), color, (i + 1) * legendSpacing - 50, legendStartY);
            startAngle += sweepAngle;
        }
    }
    private void drawLegend(Canvas canvas, float dataValue, int color, float x, float y) {
        //Vẽ ô vuông màu
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + 30, y + 30, paint);

        //Vẽ text giá trị bên cạnh
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText(String.valueOf(dataValue), x + 40, y + 25, paint);
    }
}