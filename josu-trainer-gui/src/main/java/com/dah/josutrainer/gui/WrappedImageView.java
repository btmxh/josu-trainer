package com.dah.josutrainer.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// From:
// https://stackoverflow.com/questions/32781362/centering-an-image-in-an-imageview
public class WrappedImageView extends ImageView {
    public WrappedImageView() {
        setPreserveRatio(false);
    }

    @Override
    public double minWidth(double height) {
        return 40;
    }

    @Override
    public double prefWidth(double height) {
        Image I = getImage();
        if (I == null)
            return minWidth(height);
        return I.getWidth();
    }

    @Override
    public double maxWidth(double height) {
        return 16384;
    }

    @Override
    public double minHeight(double width) {
        return 40;
    }

    @Override
    public double prefHeight(double width) {
        Image I = getImage();
        if (I == null)
            return minHeight(width);
        return I.getHeight();
    }

    @Override
    public double maxHeight(double width) {
        return 16384;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        setFitWidth(width);
        setFitHeight(height);
    }

    // From:
    // https://stackoverflow.com/questions/32781362/centering-an-image-in-an-imageview
    public void centerImage() {
        Image img = getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = getFitWidth() / img.getWidth();
            double ratioY = getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            setX((getFitWidth() - w) / 2);
            setY((getFitHeight() - h) / 2);

        }
    }
}
