package com.smart.main.testJFreeChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import java.awt.*;
import java.util.Map;

//这是柱形图的另一种效果，其实跟第一种相比都只有数据集发生了变化，再无其他变化
public class BarChart1 {
	ChartPanel frame1;
	public  BarChart1(Map<Integer, Integer> contributors_map){
		CategoryDataset dataset = getDataSet(contributors_map);
        JFreeChart chart = ChartFactory.createBarChart3D(
       		                 "统计图", // 图表标题
                            "project", // 目录轴的显示标签
                            "contributor(s)", //数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,           // 是否显示图例(对于简单的柱状图必须是false)
                            false,          // 是否生成工具
                            false           // 是否生成URL链接
                            );
        
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setLowerMargin(0.01);// 左边距 边框距离
        domainAxis.setUpperMargin(0.1);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴
        CategoryItemRenderer categoryitemrenderer = plot.getRenderer();
        categoryitemrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryitemrenderer.setItemLabelFont(new Font("黑体",Font.PLAIN,20));
        categoryitemrenderer.setItemLabelsVisible(true);
        BarRenderer barrenderer   =   (BarRenderer)plot.getRenderer();
        barrenderer.setItemMargin(0);
        plot.setRenderer(barrenderer);
        barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barrenderer.setBaseItemLabelsVisible(true);
        barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        barrenderer.setItemLabelAnchorOffset(5D);// 设置柱形图上的文字偏离值
        domainAxis.setMaximumCategoryLabelLines(10);
        barrenderer.setItemLabelsVisible(true);
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,15));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,15));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 12));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
          
          //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
          
         frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame
         
	}
	   private static CategoryDataset getDataSet(Map<Integer, Integer> contributors_map) {
           DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//           dataset.addValue(100, "苹果", "11111111");
//           dataset.addValue(200, "梨子", "梨子");
//           dataset.addValue(300, "葡萄", "葡萄");
//           dataset.addValue(400, "香蕉", "香蕉");
//           dataset.addValue(500, "荔枝", "荔枝");
//           for(Iterator i = contributors_map.keySet().iterator(); i.hasNext();){
//               String key = (String) i.next();
//               int value = contributors_map.get(key);
//               dataset.addValue(value,"project",key);
//           }
           for(Map.Entry entry:contributors_map.entrySet()){
               int key = (Integer) entry.getKey();
               int value = (Integer)entry.getValue();
               dataset.addValue(value,"project",String.valueOf(key));
           }
           return dataset;
}

public ChartPanel getChartPanel(){
	return frame1;
	
}
}

