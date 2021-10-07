package data;

import org.junit.jupiter.api.Assertions;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager INSTANCE = null;

    private List<Product> productList = new ArrayList<>();

    private DataManager() {
    }

    public static DataManager getDataManager() {
        if (INSTANCE == null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }
    public List<Product> getProductList(){
        return productList;
    }
    public Product getProductByName(String productName){
        for (int i = 0; i < productList.size(); i++) {
            if (Utils.convertNameProduct(productList.get(i).getName()).contains(Utils.convertNameProduct(productName))){
                return getProductList().get(i);
            }
        }
        Assertions.fail("msg");
        return null;
    }
}
