package com.sugano

import org.specs2.mutable.Specification

import org.jfree.chart.{JFreeChart, ChartFactory}
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.junit.Before;
import org.junit.Test;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


class LineSpec extends Specification {

  "aaa" should {
    def createLineChart():JFreeChart = {
      var data = Array[Array[Double]](Array(42,12,32), Array(2,2,2))
      var dataset = DatasetUtilities.createCategoryDataset("S", "Year", data)
      return ChartFactory.createLineChart("Line Chart", "Domain", "Range", dataset)
    }

    def supplyDestination():java.io.File = {
      new java.io.File("line.jpg")
    }

    val HEIGHT = 500
    val RATIO = 2
    val WIDTH = HEIGHT*RATIO

    "test draw with null info" in {
      var chart = createLineChart()
      var image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)
      var g2 = image.createGraphics()
      chart.draw(g2, new Rectangle2D.Double(0, 0, WIDTH, HEIGHT), null, null)
      javax.imageio.ImageIO.write(image, "jpg", supplyDestination);
      g2.dispose()
      "" == ""
    }
  }
}
