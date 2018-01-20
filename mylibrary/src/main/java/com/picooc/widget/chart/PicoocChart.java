package com.picooc.widget.chart;

import android.content.Context;
import android.graphics.Color;

import com.picooc.utils.TextUtils;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class PicoocChart extends PicoocAbstractChart
{
  private double getMax(double[] paramArrayOfDouble)
  {
    double d = paramArrayOfDouble[0];
    for (int i = 1; ; i++)
    {
      if (i >= paramArrayOfDouble.length)
        return d;
      if (paramArrayOfDouble[i] > d)
        d = paramArrayOfDouble[i];
    }
  }

  private double getMin(double[] paramArrayOfDouble)
  {
    double d = paramArrayOfDouble[0];
    for (int i = 1; ; i++)
    {
      if (i >= paramArrayOfDouble.length)
        return d;
      if (paramArrayOfDouble[i] < d)
        d = paramArrayOfDouble[i];
    }
  }

  public GraphicalView execute(Context paramContext)
  {
    String[] arrayOfString = { "体重" };
    ArrayList localArrayList1 = new ArrayList();
    int i = 0;
    int j = arrayOfString.length;
    ArrayList localArrayList2;
    XYMultipleSeriesRenderer localXYMultipleSeriesRenderer;
    int k;
    if (i >= j)
    {
      localArrayList2 = new ArrayList();
      localArrayList2.add(new double[] { 60.0D, 60.5D, 61.399999999999999D, 62.700000000000003D, 63.299999999999997D, 62.100000000000001D, 61.899999999999999D });
      int[] arrayOfInt1 = new int[2];
      arrayOfInt1[0] = Color.parseColor("#ADFD50");
      arrayOfInt1[1] = Color.parseColor("#00FFFF");
      PointStyle[] arrayOfPointStyle = new PointStyle[2];
      arrayOfPointStyle[0] = PointStyle.CIRCLE;
      arrayOfPointStyle[1] = PointStyle.CIRCLE;
      localXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer(2);
      setRenderer(localXYMultipleSeriesRenderer, arrayOfInt1, arrayOfPointStyle);
      k = localXYMultipleSeriesRenderer.getSeriesRendererCount();
    }
    for (int m = 0; ; m++)
    {
      if (m >= k)
      {
        setChartSettings(localXYMultipleSeriesRenderer, "", "", "", 1.0D, 7.0D, 16.5D, 27.0D, -3355444, -3355444);
        localXYMultipleSeriesRenderer.setXLabels(7);
        localXYMultipleSeriesRenderer.setYLabels(5);
        localXYMultipleSeriesRenderer.setLabelsTextSize(paramContext.getResources().getDimension(2131230748));
        localXYMultipleSeriesRenderer.setYLabelsVerticalPadding(paramContext.getResources().getDimension(2131230751));
        int[] arrayOfInt2 = new int[4];
        arrayOfInt2[1] = ((int)paramContext.getResources().getDimension(2131230749));
        arrayOfInt2[3] = ((int)paramContext.getResources().getDimension(2131230750));
        localXYMultipleSeriesRenderer.setMargins(arrayOfInt2);
        localXYMultipleSeriesRenderer.setYLabelsPadding(-paramContext.getResources().getDimension(2131230751));
        localXYMultipleSeriesRenderer.setYAxisMax(74.5D);
        localXYMultipleSeriesRenderer.setYAxisMin(54.0D);
        localXYMultipleSeriesRenderer.setShowGridY(true);
        localXYMultipleSeriesRenderer.setAntialiasing(true);
        localXYMultipleSeriesRenderer.setApplyBackgroundColor(true);
        localXYMultipleSeriesRenderer.setBackgroundColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setMarginsColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setPointSize(paramContext.getResources().getDimension(2131230752));
        localXYMultipleSeriesRenderer.setTextTypeface(TextUtils.getTypeface(paramContext, null));
        localXYMultipleSeriesRenderer.setPanEnabled(false);
        localXYMultipleSeriesRenderer.setZoomButtonsVisible(false);
        localXYMultipleSeriesRenderer.setZoomEnabled(false, false);
        localXYMultipleSeriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        localXYMultipleSeriesRenderer.setShowLegend(false);
        localXYMultipleSeriesRenderer.setLabelsColor(-1);
        localXYMultipleSeriesRenderer.setXLabelsColor(-1);
        localXYMultipleSeriesRenderer.setYLabelsColor(0, -1);
        localXYMultipleSeriesRenderer.setYLabelsColor(1, -1);
        localXYMultipleSeriesRenderer.setYAxisAlign(Paint.Align.RIGHT, 1);
        localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.LEFT, 1);
        localXYMultipleSeriesRenderer.setYAxisMax(27.0D, 1);
        localXYMultipleSeriesRenderer.setYAxisMin(10.0D, 1);
        localXYMultipleSeriesRenderer.setXAxisMax(7.0D, 1);
        localXYMultipleSeriesRenderer.setXAxisMin(1.0D, 1);
        localXYMultipleSeriesRenderer.setShowCustomTextGrid(true);
        localXYMultipleSeriesRenderer.setClickEnabled(true);
        localXYMultipleSeriesRenderer.setSelectableBuffer((int)paramContext.getResources().getDimension(2131230753));
        localXYMultipleSeriesRenderer.setShowGoleWeight(true);
        localXYMultipleSeriesRenderer.setGoleWeightAndTextAndColor(55.5F, "目标", Color.parseColor("#ff6895"), 0);
        XYMultipleSeriesDataset localXYMultipleSeriesDataset = buildDataset(arrayOfString, localArrayList1, localArrayList2);
        localArrayList2.clear();
        localArrayList2.add(new double[] { 20.300000000000001D, 20.5D, 21.800000000000001D, 22.800000000000001D, 23.399999999999999D, 24.399999999999999D, 25.0D });
        addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 1);
        return ChartFactory.getLineChartView(paramContext, localXYMultipleSeriesDataset, localXYMultipleSeriesRenderer);
        localArrayList1.add(new double[] { 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D });
        i++;
        break;
      }
      XYSeriesRenderer localXYSeriesRenderer = (XYSeriesRenderer)localXYMultipleSeriesRenderer.getSeriesRendererAt(m);
      localXYSeriesRenderer.setFillPoints(false);
      localXYSeriesRenderer.setDisplayChartValues(true);
      localXYSeriesRenderer.setChartValuesTextSize(paramContext.getResources().getDimension(2131230747));
      localXYSeriesRenderer.setChartValuesTextAlign(Paint.Align.CENTER);
      localXYSeriesRenderer.setChartValuesSpacing(paramContext.getResources().getDimension(2131230746));
      localXYSeriesRenderer.setDisplayChartValuesDistance(50);
      localXYSeriesRenderer.setLineWidth(paramContext.getResources().getDimension(2131230744));
      localXYSeriesRenderer.setPointStrokeWidth(paramContext.getResources().getDimension(2131230745));
    }
  }

  public GraphicalView execute(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, float paramFloat)
  {
    String[] arrayOfString1 = { "体重" };
    ArrayList localArrayList1 = new ArrayList();
    int i = 0;
    int j = arrayOfString1.length;
    ArrayList localArrayList2;
    XYMultipleSeriesRenderer localXYMultipleSeriesRenderer;
    int m;
    label126: int n;
    int i1;
    String[] arrayOfString2;
    if (i >= j)
    {
      localArrayList2 = new ArrayList();
      localArrayList2.add(paramArrayOfDouble2);
      int[] arrayOfInt1 = new int[2];
      arrayOfInt1[0] = Color.parseColor("#ADFD50");
      arrayOfInt1[1] = Color.parseColor("#00FFFF");
      PointStyle[] arrayOfPointStyle = new PointStyle[2];
      arrayOfPointStyle[0] = PointStyle.CIRCLE;
      arrayOfPointStyle[1] = PointStyle.CIRCLE;
      localXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer(2);
      setRenderer(localXYMultipleSeriesRenderer, arrayOfInt1, arrayOfPointStyle);
      int k = localXYMultipleSeriesRenderer.getSeriesRendererCount();
      m = 0;
      if (m < k)
        break label728;
      n = -2 + (int)paramFloat;
      i1 = 17 + 10 * (int)(getMax(paramArrayOfDouble2) / 10.0D);
      setChartSettings(localXYMultipleSeriesRenderer, "", "", "", paramArrayOfDouble1[0], paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], n, 10 * (int)(getMax(paramArrayOfDouble2) / 10.0D), -3355444, -3355444);
      localXYMultipleSeriesRenderer.setXLabels(0);
      arrayOfString2 = new String[] { "一", "二", "三", "四", "五", "六", "日" };
    }
    for (int i2 = 1; ; i2++)
    {
      if (i2 > 7)
      {
        localXYMultipleSeriesRenderer.setYLabels(5);
        localXYMultipleSeriesRenderer.setLabelsTextSize(paramContext.getResources().getDimension(2131230748));
        localXYMultipleSeriesRenderer.setYLabelsVerticalPadding(paramContext.getResources().getDimension(2131230751));
        int[] arrayOfInt2 = new int[4];
        arrayOfInt2[1] = ((int)paramContext.getResources().getDimension(2131230749));
        arrayOfInt2[3] = ((int)paramContext.getResources().getDimension(2131230750));
        localXYMultipleSeriesRenderer.setMargins(arrayOfInt2);
        localXYMultipleSeriesRenderer.setYLabelsPadding(-paramContext.getResources().getDimension(2131230751));
        localXYMultipleSeriesRenderer.setYAxisMax(i1);
        localXYMultipleSeriesRenderer.setYAxisMin(n);
        localXYMultipleSeriesRenderer.setShowGridY(true);
        localXYMultipleSeriesRenderer.setAntialiasing(true);
        localXYMultipleSeriesRenderer.setApplyBackgroundColor(true);
        localXYMultipleSeriesRenderer.setBackgroundColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setMarginsColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setPointSize(paramContext.getResources().getDimension(2131230752));
        localXYMultipleSeriesRenderer.setTextTypeface(TextUtils.getTypeface(paramContext, null));
        localXYMultipleSeriesRenderer.setPanEnabled(false);
        localXYMultipleSeriesRenderer.setZoomButtonsVisible(false);
        localXYMultipleSeriesRenderer.setZoomEnabled(false, false);
        localXYMultipleSeriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        localXYMultipleSeriesRenderer.setShowLegend(false);
        localXYMultipleSeriesRenderer.setLabelsColor(-1);
        localXYMultipleSeriesRenderer.setXLabelsColor(-1);
        localXYMultipleSeriesRenderer.setYLabelsColor(0, -1);
        localXYMultipleSeriesRenderer.setYLabelsColor(1, -1);
        localXYMultipleSeriesRenderer.setYAxisAlign(Paint.Align.RIGHT, 1);
        localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.LEFT, 1);
        localXYMultipleSeriesRenderer.setYAxisMax(2.0D + getMax(paramArrayOfDouble3), 1);
        localXYMultipleSeriesRenderer.setYAxisMin(getMin(paramArrayOfDouble3) - 8.0D, 1);
        localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 1);
        localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 1);
        localXYMultipleSeriesRenderer.setShowCustomTextGrid(true);
        localXYMultipleSeriesRenderer.setClickEnabled(true);
        localXYMultipleSeriesRenderer.setSelectableBuffer((int)paramContext.getResources().getDimension(2131230753));
        localXYMultipleSeriesRenderer.setShowGoleWeight(true);
        localXYMultipleSeriesRenderer.setGoleWeightAndTextAndColor(paramFloat, paramFloat + "kg", Color.parseColor("#ff6895"), 0);
        XYMultipleSeriesDataset localXYMultipleSeriesDataset = buildDataset(arrayOfString1, localArrayList1, localArrayList2);
        localArrayList2.clear();
        localArrayList2.add(paramArrayOfDouble3);
        addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 1);
        return ChartFactory.getLineChartView(paramContext, localXYMultipleSeriesDataset, localXYMultipleSeriesRenderer);
        localArrayList1.add(paramArrayOfDouble1);
        i++;
        break;
        label728: XYSeriesRenderer localXYSeriesRenderer = (XYSeriesRenderer)localXYMultipleSeriesRenderer.getSeriesRendererAt(m);
        localXYSeriesRenderer.setFillPoints(false);
        localXYSeriesRenderer.setDisplayChartValues(true);
        localXYSeriesRenderer.setChartValuesTextSize(paramContext.getResources().getDimension(2131230747));
        localXYSeriesRenderer.setChartValuesTextAlign(Paint.Align.CENTER);
        localXYSeriesRenderer.setChartValuesSpacing(paramContext.getResources().getDimension(2131230746));
        localXYSeriesRenderer.setDisplayChartValuesDistance(50);
        localXYSeriesRenderer.setLineWidth(paramContext.getResources().getDimension(2131230744));
        localXYSeriesRenderer.setPointStrokeWidth(paramContext.getResources().getDimension(2131230745));
        m++;
        break label126;
      }
      localXYMultipleSeriesRenderer.addXTextLabel(i2, arrayOfString2[(i2 - 1)]);
    }
  }

  public GraphicalView execute(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, float paramFloat1, float paramFloat2)
  {
    String[] arrayOfString = { "体重" };
    ArrayList localArrayList1 = new ArrayList();
    XYMultipleSeriesRenderer localXYMultipleSeriesRenderer;
    int k;
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfString.length)
      {
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add(paramArrayOfDouble3);
        int[] arrayOfInt1 = new int[2];
        arrayOfInt1[0] = Color.parseColor("#00FFFF");
        arrayOfInt1[1] = Color.parseColor("#ADFD50");
        PointStyle[] arrayOfPointStyle = new PointStyle[2];
        arrayOfPointStyle[0] = PointStyle.POINT;
        arrayOfPointStyle[1] = PointStyle.POINT;
        localXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer(6);
        setRenderer(localXYMultipleSeriesRenderer, arrayOfInt1, arrayOfPointStyle);
        int j = localXYMultipleSeriesRenderer.getSeriesRendererCount();
        k = 0;
        if (k < j)
          break;
        localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0]);
        localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)]);
        localXYMultipleSeriesRenderer.setShowLabels(false);
        int[] arrayOfInt2 = new int[4];
        arrayOfInt2[1] = ((int)paramContext.getResources().getDimension(2131230749));
        arrayOfInt2[3] = ((int)paramContext.getResources().getDimension(2131230750));
        localXYMultipleSeriesRenderer.setMargins(arrayOfInt2);
        localXYMultipleSeriesRenderer.setAntialiasing(true);
        localXYMultipleSeriesRenderer.setApplyBackgroundColor(true);
        localXYMultipleSeriesRenderer.setBackgroundColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setMarginsColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setPanEnabled(false);
        localXYMultipleSeriesRenderer.setZoomButtonsVisible(false);
        localXYMultipleSeriesRenderer.setZoomEnabled(false, false);
        localXYMultipleSeriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        localXYMultipleSeriesRenderer.setShowLegend(false);
        localXYMultipleSeriesRenderer.setShowCustomTextGrid(true);
        localXYMultipleSeriesRenderer.setYAxisMax(paramFloat1, 0);
        localXYMultipleSeriesRenderer.setYAxisMin(paramFloat2, 0);
        localXYMultipleSeriesRenderer.setLabelsColor(-1);
        localXYMultipleSeriesRenderer.setXLabelsColor(-1);
        XYMultipleSeriesDataset localXYMultipleSeriesDataset = buildDataset(arrayOfString, localArrayList1, localArrayList2);
        localXYMultipleSeriesRenderer.setYLabelsColor(1, -1);
        localXYMultipleSeriesRenderer.setYAxisAlign(Paint.Align.RIGHT, 1);
        localXYMultipleSeriesRenderer.setYAxisMax(paramFloat1, 1);
        localXYMultipleSeriesRenderer.setYAxisMin(paramFloat2, 1);
        localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 1);
        localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 4);
        localArrayList2.clear();
        localArrayList2.add(paramArrayOfDouble2);
        addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 1);
        return ChartFactory.getLineChartView(paramContext, localXYMultipleSeriesDataset, localXYMultipleSeriesRenderer);
      }
      localArrayList1.add(paramArrayOfDouble1);
    }
    XYSeriesRenderer localXYSeriesRenderer = (XYSeriesRenderer)localXYMultipleSeriesRenderer.getSeriesRendererAt(k);
    localXYSeriesRenderer.setFillPoints(false);
    if (k == 0)
      localXYSeriesRenderer.setLineWidth(1.01F);
    while (true)
    {
      k++;
      break;
      localXYSeriesRenderer.setLineWidth(2.01F);
    }
  }

  public GraphicalView execute(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, double[] paramArrayOfDouble6, double[] paramArrayOfDouble7, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt)
  {
    String[] arrayOfString1 = { "体重" };
    ArrayList localArrayList1 = new ArrayList();
    int i = 0;
    ArrayList localArrayList2;
    PointStyle[] arrayOfPointStyle;
    label165: XYMultipleSeriesRenderer localXYMultipleSeriesRenderer;
    int k;
    String[] arrayOfString2;
    int m;
    label287: label300: label572: XYMultipleSeriesDataset localXYMultipleSeriesDataset;
    if (i >= arrayOfString1.length)
    {
      localArrayList2 = new ArrayList();
      localArrayList2.add(paramArrayOfDouble4);
      int[] arrayOfInt1 = new int[6];
      arrayOfInt1[0] = Color.parseColor("#ADFD50");
      arrayOfInt1[1] = Color.parseColor("#00FFFF");
      arrayOfInt1[2] = Color.parseColor("#ADFD50");
      arrayOfInt1[3] = Color.parseColor("#00FFFF");
      arrayOfInt1[4] = Color.parseColor("#ADFD50");
      arrayOfInt1[5] = Color.parseColor("#00FFFF");
      if (paramInt != 1)
        break label1046;
      arrayOfPointStyle = new PointStyle[6];
      arrayOfPointStyle[0] = PointStyle.POINT;
      arrayOfPointStyle[1] = PointStyle.POINT;
      arrayOfPointStyle[2] = PointStyle.POINT;
      arrayOfPointStyle[3] = PointStyle.POINT;
      arrayOfPointStyle[4] = PointStyle.CIRCLE;
      arrayOfPointStyle[5] = PointStyle.CIRCLE;
      localXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer(6);
      setRenderer(localXYMultipleSeriesRenderer, arrayOfInt1, arrayOfPointStyle);
      int j = localXYMultipleSeriesRenderer.getSeriesRendererCount();
      k = 0;
      if (k < j)
        break label1098;
      localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0]);
      localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)]);
      if (paramInt != 1)
        break label1335;
      localXYMultipleSeriesRenderer.setXLabels(0);
      arrayOfString2 = new String[] { "一", "二", "三", "四", "五", "六", "日" };
      m = 1;
      if (m <= 7)
        break label1314;
      localXYMultipleSeriesRenderer.setXLabelsColor(-1);
      localXYMultipleSeriesRenderer.setYLabels(5);
      localXYMultipleSeriesRenderer.setLabelsTextSize(paramContext.getResources().getDimension(2131230748));
      localXYMultipleSeriesRenderer.setYLabelsVerticalPadding(paramContext.getResources().getDimension(2131230751));
      int[] arrayOfInt2 = new int[4];
      arrayOfInt2[1] = ((int)paramContext.getResources().getDimension(2131230749));
      arrayOfInt2[3] = ((int)paramContext.getResources().getDimension(2131230750));
      localXYMultipleSeriesRenderer.setMargins(arrayOfInt2);
      localXYMultipleSeriesRenderer.setYLabelsPadding(-paramContext.getResources().getDimension(2131230751));
      localXYMultipleSeriesRenderer.setShowGridY(true);
      localXYMultipleSeriesRenderer.setAntialiasing(true);
      localXYMultipleSeriesRenderer.setApplyBackgroundColor(true);
      localXYMultipleSeriesRenderer.setBackgroundColor(Color.argb(0, 220, 228, 234));
      localXYMultipleSeriesRenderer.setMarginsColor(Color.argb(0, 220, 228, 234));
      localXYMultipleSeriesRenderer.setPointSize(paramContext.getResources().getDimension(2131230752));
      localXYMultipleSeriesRenderer.setTextTypeface(TextUtils.getTypeface(paramContext, null));
      localXYMultipleSeriesRenderer.setPanEnabled(false);
      localXYMultipleSeriesRenderer.setZoomButtonsVisible(false);
      localXYMultipleSeriesRenderer.setZoomEnabled(false, false);
      localXYMultipleSeriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
      localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
      localXYMultipleSeriesRenderer.setShowLegend(false);
      localXYMultipleSeriesRenderer.setShowCustomTextGrid(true);
      localXYMultipleSeriesRenderer.setClickEnabled(true);
      localXYMultipleSeriesRenderer.setSelectableBuffer((int)paramContext.getResources().getDimension(2131230753));
      localXYMultipleSeriesRenderer.setYAxisMax(paramFloat1, 0);
      localXYMultipleSeriesRenderer.setYAxisMin(paramFloat2, 0);
      localXYMultipleSeriesRenderer.setLabelsColor(-1);
      if (paramInt != 1)
        break label1373;
      localXYMultipleSeriesRenderer.setYLabelsColor(0, 0);
      localXYMultipleSeriesDataset = buildDataset(arrayOfString1, localArrayList1, localArrayList2);
      if (paramInt != 1)
        break label1383;
      localXYMultipleSeriesRenderer.setYLabelsColor(1, 0);
    }
    while (true)
    {
      localXYMultipleSeriesRenderer.setYAxisAlign(Paint.Align.RIGHT, 1);
      localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.LEFT, 1);
      localXYMultipleSeriesRenderer.setYAxisMax(paramFloat3, 1);
      localXYMultipleSeriesRenderer.setYAxisMin(paramFloat4, 1);
      localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 1);
      localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 1);
      localArrayList2.clear();
      localArrayList2.add(paramArrayOfDouble5);
      addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 1);
      localXYMultipleSeriesRenderer.setYLabelsColor(2, 0);
      localXYMultipleSeriesRenderer.setYAxisMax(paramFloat1, 2);
      localXYMultipleSeriesRenderer.setYAxisMin(paramFloat2, 2);
      localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 2);
      localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 4);
      localArrayList2.clear();
      localArrayList2.add(paramArrayOfDouble6);
      addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 2);
      localXYMultipleSeriesRenderer.setYLabelsColor(3, 0);
      localXYMultipleSeriesRenderer.setYAxisMax(paramFloat3, 3);
      localXYMultipleSeriesRenderer.setYAxisMin(paramFloat4, 3);
      localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 3);
      localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 3);
      localArrayList2.clear();
      localArrayList2.add(paramArrayOfDouble7);
      addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 3);
      localXYMultipleSeriesRenderer.setYLabelsColor(4, 0);
      localXYMultipleSeriesRenderer.setYAxisMax(paramFloat1, 4);
      localXYMultipleSeriesRenderer.setYAxisMin(paramFloat2, 4);
      localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 4);
      localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 4);
      localArrayList2.clear();
      localArrayList2.add(paramArrayOfDouble2);
      addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 4);
      localXYMultipleSeriesRenderer.setYLabelsColor(5, 0);
      localXYMultipleSeriesRenderer.setYAxisMax(paramFloat3, 5);
      localXYMultipleSeriesRenderer.setYAxisMin(paramFloat4, 5);
      localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 5);
      localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 5);
      localArrayList2.clear();
      localArrayList2.add(paramArrayOfDouble3);
      addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 5);
      return ChartFactory.getLineChartView(paramContext, localXYMultipleSeriesDataset, localXYMultipleSeriesRenderer);
      localArrayList1.add(paramArrayOfDouble1);
      i++;
      break;
      label1046: arrayOfPointStyle = new PointStyle[6];
      arrayOfPointStyle[0] = PointStyle.POINT;
      arrayOfPointStyle[1] = PointStyle.POINT;
      arrayOfPointStyle[2] = PointStyle.POINT;
      arrayOfPointStyle[3] = PointStyle.POINT;
      arrayOfPointStyle[4] = PointStyle.POINT;
      arrayOfPointStyle[5] = PointStyle.POINT;
      break label165;
      label1098: XYSeriesRenderer localXYSeriesRenderer = (XYSeriesRenderer)localXYMultipleSeriesRenderer.getSeriesRendererAt(k);
      localXYSeriesRenderer.setFillPoints(false);
      if ((k >= 4) && (paramInt == 1))
      {
        localXYSeriesRenderer.setDisplayChartValues(true);
        localXYSeriesRenderer.setChartValuesTextSize(paramContext.getResources().getDimension(2131230747));
        localXYSeriesRenderer.setChartValuesTextAlign(Paint.Align.CENTER);
        localXYSeriesRenderer.setChartValuesSpacing(paramContext.getResources().getDimension(2131230746));
        localXYSeriesRenderer.setDisplayChartValuesDistance(50);
        DecimalFormat localDecimalFormat = new DecimalFormat("#0.0");
        localDecimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        localXYSeriesRenderer.setChartValuesFormat(localDecimalFormat);
      }
      if ((k <= 1) || (k >= 4))
      {
        localXYSeriesRenderer.setLineWidth(paramContext.getResources().getDimension(2131230744));
        localXYSeriesRenderer.setPointStrokeWidth(paramContext.getResources().getDimension(2131230745));
      }
      while (true)
      {
        if (k <= 1)
          localXYSeriesRenderer.setStroke(new BasicStroke(Paint.Cap.ROUND, Paint.Join.ROUND, 1.0F, new float[] { 1.0F, 2.0F, 4.0F, 8.0F }, 1.0F));
        k++;
        break;
        localXYSeriesRenderer.setLineWidth(1.01F);
      }
      label1314: localXYMultipleSeriesRenderer.addXTextLabel(m, arrayOfString2[(m - 1)]);
      m++;
      break label287;
      label1335: if (paramInt == 2)
      {
        localXYMultipleSeriesRenderer.setXLabels(6);
        localXYMultipleSeriesRenderer.setXLabelsColor(-1);
        break label300;
      }
      localXYMultipleSeriesRenderer.setXLabelsColor(0);
      localXYMultipleSeriesRenderer.setXLabels(18);
      break label300;
      label1373: localXYMultipleSeriesRenderer.setYLabelsColor(0, -1);
      break label572;
      label1383: localXYMultipleSeriesRenderer.setYLabelsColor(1, -1);
    }
  }

  public GraphicalView executeForCubeLine(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, float paramFloat)
  {
    String[] arrayOfString = { "体重" };
    ArrayList localArrayList1 = new ArrayList();
    int i = 0;
    int j = arrayOfString.length;
    ArrayList localArrayList2;
    XYMultipleSeriesRenderer localXYMultipleSeriesRenderer;
    int k;
    if (i >= j)
    {
      localArrayList2 = new ArrayList();
      localArrayList2.add(paramArrayOfDouble2);
      int[] arrayOfInt1 = new int[2];
      arrayOfInt1[0] = Color.parseColor("#ADFD50");
      arrayOfInt1[1] = Color.parseColor("#00FFFF");
      PointStyle[] arrayOfPointStyle = new PointStyle[2];
      arrayOfPointStyle[0] = PointStyle.POINT;
      arrayOfPointStyle[1] = PointStyle.POINT;
      localXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer(2);
      setRenderer(localXYMultipleSeriesRenderer, arrayOfInt1, arrayOfPointStyle);
      k = localXYMultipleSeriesRenderer.getSeriesRendererCount();
    }
    for (int m = 0; ; m++)
    {
      if (m >= k)
      {
        int n = -3 + (int)paramFloat;
        int i1 = 17 + 10 * (int)(getMax(paramArrayOfDouble2) / 10.0D);
        setChartSettings(localXYMultipleSeriesRenderer, "", "", "", paramArrayOfDouble1[0], paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], n, 10 * (int)(getMax(paramArrayOfDouble2) / 10.0D), -3355444, -3355444);
        localXYMultipleSeriesRenderer.setXLabels(7);
        localXYMultipleSeriesRenderer.setYLabels(5);
        localXYMultipleSeriesRenderer.setLabelsTextSize(paramContext.getResources().getDimension(2131230748));
        localXYMultipleSeriesRenderer.setYLabelsVerticalPadding(paramContext.getResources().getDimension(2131230751));
        int[] arrayOfInt2 = new int[4];
        arrayOfInt2[1] = ((int)paramContext.getResources().getDimension(2131230749));
        arrayOfInt2[3] = ((int)paramContext.getResources().getDimension(2131230750));
        localXYMultipleSeriesRenderer.setMargins(arrayOfInt2);
        localXYMultipleSeriesRenderer.setYLabelsPadding(-paramContext.getResources().getDimension(2131230751));
        localXYMultipleSeriesRenderer.setYAxisMax(i1);
        localXYMultipleSeriesRenderer.setYAxisMin(n);
        localXYMultipleSeriesRenderer.setShowGridY(true);
        localXYMultipleSeriesRenderer.setAntialiasing(true);
        localXYMultipleSeriesRenderer.setBackgroundColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setMarginsColor(Color.argb(0, 220, 228, 234));
        localXYMultipleSeriesRenderer.setTextTypeface(TextUtils.getTypeface(paramContext, null));
        localXYMultipleSeriesRenderer.setPanEnabled(false);
        localXYMultipleSeriesRenderer.setZoomButtonsVisible(false);
        localXYMultipleSeriesRenderer.setZoomEnabled(false, false);
        localXYMultipleSeriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        localXYMultipleSeriesRenderer.setShowLegend(false);
        localXYMultipleSeriesRenderer.setLabelsColor(-1);
        localXYMultipleSeriesRenderer.setXLabelsColor(-1);
        localXYMultipleSeriesRenderer.setYLabelsColor(0, -1);
        localXYMultipleSeriesRenderer.setYLabelsColor(1, -1);
        localXYMultipleSeriesRenderer.setYAxisAlign(Paint.Align.RIGHT, 1);
        localXYMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.LEFT, 1);
        localXYMultipleSeriesRenderer.setYAxisMax(2.0D + getMax(paramArrayOfDouble3), 1);
        localXYMultipleSeriesRenderer.setYAxisMin(getMin(paramArrayOfDouble3) - 8.0D, 1);
        localXYMultipleSeriesRenderer.setXAxisMax(paramArrayOfDouble1[(-1 + paramArrayOfDouble1.length)], 1);
        localXYMultipleSeriesRenderer.setXAxisMin(paramArrayOfDouble1[0], 1);
        localXYMultipleSeriesRenderer.setShowCustomTextGrid(true);
        localXYMultipleSeriesRenderer.setClickEnabled(true);
        localXYMultipleSeriesRenderer.setSelectableBuffer((int)paramContext.getResources().getDimension(2131230753));
        localXYMultipleSeriesRenderer.setShowGoleWeight(true);
        localXYMultipleSeriesRenderer.setGoleWeightAndTextAndColor(paramFloat, paramFloat + "kg", Color.parseColor("#ff6895"), 0);
        XYMultipleSeriesDataset localXYMultipleSeriesDataset = buildDataset(arrayOfString, localArrayList1, localArrayList2);
        localArrayList2.clear();
        localArrayList2.add(paramArrayOfDouble3);
        addXYSeries(localXYMultipleSeriesDataset, new String[] { "" }, localArrayList1, localArrayList2, 1);
        return ChartFactory.getCubeLineChartView(paramContext, localXYMultipleSeriesDataset, localXYMultipleSeriesRenderer, 0.3F);
        localArrayList1.add(paramArrayOfDouble1);
        i++;
        break;
      }
      XYSeriesRenderer localXYSeriesRenderer = (XYSeriesRenderer)localXYMultipleSeriesRenderer.getSeriesRendererAt(m);
      localXYSeriesRenderer.setChartValuesTextSize(paramContext.getResources().getDimension(2131230747));
      localXYSeriesRenderer.setLineWidth(paramContext.getResources().getDimension(2131230744));
      localXYSeriesRenderer.setPointStrokeWidth(paramContext.getResources().getDimension(2131230745));
    }
  }

  public String getDesc()
  {
    return "The average temperature in 4 Greek islands (cubic line chart)";
  }

  public String getName()
  {
    return "Average temperature";
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     PicoocChart
 * JD-Core Version:    0.6.2
 */
