package com.sugano

import org.specs2.mutable.Specification
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage
import java.util.Calendar
import java.util.Date

import org.jfree.chart.{JFreeChart, ChartFactory}

import org.jfree.data.gantt.TaskSeries
import org.jfree.data.gantt.Task
import org.jfree.data.time.SimpleTimePeriod
import org.jfree.data.gantt.TaskSeriesCollection
import org.jfree.chart.plot.CategoryPlot
import org.jfree.data.category.IntervalCategoryDataset


class GanttSpec extends Specification {
  def createGanttChart():JFreeChart = {
    return ChartFactory.createGanttChart(
      "各データベースの開発期間の比較",
      "製品", "時期",
      null,
      true,
      true,
      true
    )
  }
  def supplyDestination():java.io.File = {
    new java.io.File("image.jpg")
  }

  def supplyImage(width:Int, height:Int):BufferedImage = {
    new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
  }

  def date(day:Int, month:Int, year:Int):Date = {
     var calendar = Calendar.getInstance();
     calendar.set(year, month, day);
     calendar.getTime();
 }

  def parseData(file:String):List[List[String]] = {
    val lines = scala.io.Source.fromFile(file).getLines
    lines.foldLeft(List[List[String]]()){(collection, line) =>
      collection.+:(line.split(',').toList)
    }
  }

  def parseDate(date:String):List[Int] = {
    date.replace(" ","").split("/").foldLeft(List[Int]()){(collection, elem) =>
      collection.+:(elem.toInt)
    }
  }

  def supplyDataSet():TaskSeriesCollection = {
    def add_to_series(datum:List[String], from_d:List[Int], to_d:List[Int]):Task = {
      (new Task("",
        new SimpleTimePeriod(
         date(from_d(0), from_d(1)-1, from_d(2)), date(to_d(0), to_d(1)-1, to_d(2))
        )
       )
     )
    }
    def supplyTSC(datum:List[String]):TaskSeries = {
      var series = new TaskSeries(datum(0))
      val relative_frequency = datum(1)
      val from_d = parseDate(datum(2))
      val to_d = parseDate(datum(3))
      series.add(add_to_series(datum, from_d, to_d))
      series
    }

    var collection = new TaskSeriesCollection()
    parseData(DATA_LOCATION).reverse.foreach { d =>
      collection.add(supplyTSC(d))
    }
    collection
  }

 val FRAME_WIDTH = 1000
 val FRAME_HEIGHT = 600
 val DATA_LOCATION = "src/data/data"


  "aaa" should {
    "parse data" in {
       val data = parseData(DATA_LOCATION)
       for (datum <- data){
         println(datum)
       }
       "" == ""
     }
    "test draw with null info" in {
      var chart = createGanttChart()
      var plot = chart.getPlot().asInstanceOf[CategoryPlot]
      plot.setDataset(supplyDataSet)
      var image = supplyImage(FRAME_WIDTH, FRAME_HEIGHT)
      var g2 = image.createGraphics()
      chart.draw(g2, new Rectangle2D.Double(0,0,FRAME_WIDTH,FRAME_HEIGHT), null, null)
      javax.imageio.ImageIO.write(image, "jpg", supplyDestination);
      g2.dispose()
      "" == ""
    }
  }
}
