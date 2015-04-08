package com.sugano

import java.awt._
import org.specs2.mutable.Specification

class IconSpec extends Specification {
  def get_image(path:String):Image = {
    Toolkit.getDefaultToolkit().getImage(path);
  }

  "Imago" should {
    "resize" in {
      var img1 = get_image("src/data/gnavi.jpg")
      "" == ""
    }
  }


}



