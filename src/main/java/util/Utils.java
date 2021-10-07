package util;

public class Utils {
    public static String convertNameProduct(String nameProduct){
        return nameProduct.replaceAll("\n", "").replaceAll("\\s", "");
    }
    public static Integer convertPrices(String price){
        return Integer.parseInt(price.replaceAll("\\D",""));
    }
}
