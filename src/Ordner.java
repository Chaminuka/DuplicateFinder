import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Ordner {
    private String path;
    private int len;
public Ordner(String path){
    this.path = path;
    File folder = new File(this.path);
    if(folder.isDirectory()) {
        this.len = returnLength(this.path);

    }

}



public void askPath(){
    while(true) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your file path:");
        this.path = scanner.nextLine();
        File folderPath = new File(this.path);
        if(folderPath.isDirectory()){
            this.path = this.path.replace("\\", "\\\\");
            this.len = returnLength(this.path);
            return;
        }
    }
}
private static int returnLength(String path){
    File folder = new File(path);
    File[] objects = folder.listFiles();

    return objects.length;
}
public void removeDuplicates(){

    File folder = new File(this.path);
    File[] files = folder.listFiles();
    String[] paths = new String[this.len];
    for(int i = 0; i < paths.length; i++){
        paths[i] = files[i].getPath();
    }
    List<Object> images = new ArrayList<>();
    for(String path : paths){
        Objekt temp = new Objekt(path);
        Object workObject = temp.getObject();

        if(workObject instanceof Bild workImage){
            adder(workImage, images, 0, images.size());

        }
        if(workObject instanceof Ordner workFolder){
            workFolder.removeDuplicates();
        }
    }
}
private static void adder(Bild bil, List list, int start, int end){
    if(start == end){
        list.add(start, bil);
        return;
    }
    int curr = (end-start)/2 + start;
    Object check = list.get(curr);
    Bild checkBild = (Bild) check;
    String res = checkBild.isSame(bil);
    if(res.equals("same")){
        bil.del();
        return;
    }
    if(res.equals("bigger")){
        adder(bil, list, curr+1, end);
    }
    if(res.equals("smaller")){
        adder(bil, list, start, curr);
    }


}





}
