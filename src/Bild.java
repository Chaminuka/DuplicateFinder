import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bild {
    private double mean;
    private String path;

    public Bild(String path){
        this.path = path;
        try {
            this.mean = findMean(this.path);
        } catch (IOException e) {
            // Handle the IOException
            System.err.println("Error reading image: " + e.getMessage());
            this.mean = -1; // Or any other value indicating error
        }
    }

    private static double findMean(String path) throws IOException {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

        if (image == null) {
            throw new IOException("Image file could not be read or is not an image file.");
        }

        int width = image.getWidth();
        int height = image.getHeight();
        long sum = 0;

        // Loop through each pixel and sum up the RGB values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                sum += red + green + blue; // Add up the RGB values
            }
        }

        // Calculate the mean pixel value
        double meanPixelValue = (double) sum / (width * height * 3);
        return meanPixelValue;
    }
    public String isSame(Bild bil){
        if(this.mean > bil.mean){
            return "bigger";
        }
        if(this.mean == bil.mean){
            return "same";
        }
        return"smaller";

    }
    public void del(){
        File duplicate = new File(this.path);

        boolean worked = duplicate.delete();
        if(!worked){
            System.out.println(this.path+" could not be deleted.");
        }

    }
}
