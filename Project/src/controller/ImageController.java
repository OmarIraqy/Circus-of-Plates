
package controller;

import model.ImageObject;


public class ImageController {

    public ImageObject[] getPlates(int width, int height, String color, int id) {

        ImageObject plate1 = new ImageObject((int) (Math.random() * (width / 2)), -1 * (int) (Math.random() * height), "./images/" + color + "platewithoutbase.png", id);
        ImageObject plate2 = new ImageObject((int) (Math.random() * (width / 2)), -1 * (int) (Math.random() * height), "./images/" + color + "platewithbase.png", id);
        ImageObject plate3 = new ImageObject((int) (Math.random() * (width / 2)), -1 * (int) (Math.random() * height), "./images/" + color + "platewithdeepbase.png", id);
        ImageObject plate4 = new ImageObject((int) (Math.random() * (width / 2)), -1 * (int) (Math.random() * height), "./images/" + color + "pot.png", id);
        ImageObject[] Plates = {plate1, plate2, plate3, plate4};
        return Plates;

    }

}


