import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void writeToFile(String path, List<Product> productList){
        try{
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(productList);
            oos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Random thing","random brand",10000,"no description"));
        productList.add(new Product(2,"Random thing","random brand",20000,"no description"));

        writeToFile("product.thiet", productList);
        List<Product> productFromFile = readFromFile("product.thiet");
        for (Product product : productFromFile){
            System.out.println(product);
        }

        System.out.println("Adding");
        Product add = new Product(3,"Add","Trying to add",30000,"haha");
        addToFile("product.thiet",add);
        productFromFile = readFromFile("product.thiet");
        for (Product product : productFromFile){
            System.out.println(product);
        }

        System.out.println("Searching");
        searchFromFile("product.thiet",2);


    }
    public static List<Product> readFromFile(String path){
        List<Product> productList = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            productList = (List<Product>) ois.readObject();
            fis.close();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public static void addToFile(String path, Product product){
        List<Product> productList = readFromFile("product.thiet");
        productList.add(product);
        writeToFile("product.thiet",productList);
    }

    public static void searchFromFile(String path, int code){
        List<Product> productList = readFromFile("product.thiet");
        Product result = null;
        boolean check = false;
        for (Product product : productList){
            if (product.getCode() == code){
                result = product;
                check = true;
            }
        }
        if (check){
            System.out.println(result);
        }else{
            System.out.println("Product not found");
        }
    }
}
