package edu.nyu.cess.payment.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class is used to generate image icons
 */
public class IconFactory extends ImageIcon
{

    /**
     * Creates an image icon given a path and its description.
     *
     * @param path
     * @param description
     * @return
     * @throws IOException
     */
    public static ImageIcon createImageIcon(String path, String description) throws IOException
    {
        InputStream inputStream = IconFactory.class.getClassLoader().getResourceAsStream(path);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        return new ImageIcon(bufferedImage, description);
    }
}