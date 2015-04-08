import java.awt.BasicStroke
import java.awt._
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.imageio.ImageIO

class Surface extends JPanel {
  override def paintComponent(g:Graphics) = {
      super.paintComponent(g)
      doDrawing(g)
  }

  /**
  def save() = {
    var bImg = new BufferedImage(getWidth(), getWidth(), BufferedImage.TYPE_INT_RGB);
    var cg = bImg.createGraphics();
    paintAll(cg);
    try {
      if (ImageIO.write(bImg, "png", new File("./output_image.png"))) {
        println("-- saved");
      }
    }catch{
       case e:Exception=> println(e)
    }
  }
  */

  def get_image(g:Graphics, path:String) = {
    var res = g.drawImage(Toolkit.getDefaultToolkit().getImage(path), 0, 0, null)
    println(res)
  }


  def doDrawing(g:Graphics) = {
   //get_image(g, "src/data/gnavi.jpg")
   get_image(g, "gnavi.jpg")
   /**
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
    */
  }
}

object Tabula extends JFrame{
  def resize(args:Array[String]) = {

  }

  def initTabula(args:Array[String]) = {
    setTitle("test")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new Surface())
    // paper size
    setSize(500, 1110)
    setLocationRelativeTo(null)
  }

  def main(args:Array[String]) = {

    def curro(func:Array[String] => Unit, args:Array[String]) = {
      SwingUtilities.invokeLater(new Runnable() {
        override def run() {
          func(args)
          setVisible(true)
        }
      })
    }

   //qua est desirable when implemented:
   // implement Gantt Chart related pictures....

   args(0) match {
     case "magnus" => curro(initTabula, args)
     case "resize" => curro(resize, args)
     case _ => println("need args")
   }

    /**
    var operation = args(0)
    operation match {
      case "ja" =>
        SwingUtilities.invokeLater(new Runnable() {
          override def run() {
            initTabula()
            setVisible(true)
          }
        })
      case "" => println("non sequitur")
      case _ =>
    }
   */
  }
}
