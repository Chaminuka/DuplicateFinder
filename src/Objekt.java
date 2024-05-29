import java.io.File;


public class Objekt {
    private String path;
    private String type;
    public Objekt(String path){
        this.path = path;
        this.type = typeFinder(path);

    }
    private static String typeFinder(String path){
        if(isImage(path)){
            return "Image";
        }
        File filePath = new File(path);
        if(filePath.isDirectory()){
            return "Folder";
        }
        return "Other";
    }
    private static boolean isImage(String filePath) {

        String lowerCaseFilePath = filePath.toLowerCase();

        return lowerCaseFilePath.endsWith(".jpg") ||
                lowerCaseFilePath.endsWith(".jpeg") ||
                lowerCaseFilePath.endsWith(".png") ||
                lowerCaseFilePath.endsWith(".gif") ||
                lowerCaseFilePath.endsWith(".bmp") ||
                lowerCaseFilePath.endsWith(".tiff");
    }
    public Object getObject(){
        if(this.type.equals("Image")){
            return new Bild(this.path);

        }
        if(this.type.equals("Folder")){
            return new Ordner(this.path);
        }
        return null;
    }


}
