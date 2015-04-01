import java.awt.BasicStroke
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import javax.swing.JFrame
import javax.swing.SwingUtilities


class Surface extends JPanel {
  override def paintComponent(g:Graphics) = {
      super.paintComponent(g)
      doDrawing(g)
  }

  def doDrawing(g:Graphics) = {
    var g2d = g.asInstanceOf[Graphics2D]
    var bs1 = new BasicStroke(8, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_BEVEL)
    g2d.setStroke(bs1)
    g2d.drawRect(15, 15, 80, 50)
    g2d.drawString("魂Moomi",15,51)

    var bs2 = new BasicStroke(8, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_MITER)
    g2d.setStroke(bs2)
    g2d.drawRect(125, 15, 80, 50)

    var bs3 = new BasicStroke(8, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND)
    g2d.setStroke(bs3)
    g2d.drawRect(235, 15, 80, 50)
  }
}

object Tabula extends JFrame{

  def initTabula() = {
    setTitle("test")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new Surface())
    setSize(1340, 1110)
    setLocationRelativeTo(null)
  }

  def main(args:Array[String]) = {
    SwingUtilities.invokeLater(new Runnable() {
      override def run() {
        initTabula()
        setVisible(true)
      }
    })
  }
}

