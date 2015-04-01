package com.sugano

import org.specs2.mutable.Specification
import java.awt._
import java.awt.event._
import javax.swing.JPanel

trait Magnus extends JPanel{
  def doDrawing(g:Graphics) = {
        var g2d = g.asInstanceOf[Graphics2D]
        g2d.setColor(Color.blue);
        for (i <- 1 to 100) {
            g2d.drawLine(i, i, i, i);
        }
  }
}

class MagnusSpec extends Specification with Magnus{
  "iaaa" should {
    "" in {
      "" == ""
    }
  }
}
