package Service;

import bean.Products;
import dao.*;

import java.util.List;

public class ProductsService {
    private static ProductsService instance;

    public static ProductsService getInstance(){
        if(instance ==null ) instance = new ProductsService();
        return instance;
    }
    private final IProductsDao productDAO = new ProductsDao();
    public List<Products> findAll1(String name){
        return productDAO.findAll1(name);
    }
    public List<Products>findByCategory(int idCate,String name){
        return productDAO.findByCategory(idCate,name);
    }
    public int numOfProduct(String search){
        return ProductsDao.numOfProduct(search);
    }
    public int numOfProCate(int idCate,String search){
        return ProductsDao.numOfProCate(idCate,search);
    }

    //=======================================Dưới là của admin=============================================
    public List<Products> productList(String search) {
        return ProductsDao.productList(search);
    }
    public List<Products>getTenPro(int index,String search){return ProductsDao.getTenPro(index,search);}
    public void insertProduct(String name, String image, int price, int category, int status, String desc){
        ProductsDao.insertProduct(name, image, price, category, status, desc);
    }
    public void deleteProduct(int proID){
        ProductsDao.deleteProduct(proID);
    }
    public Products getProductById(int proID){return ProductsDao.getProductById(proID);}
    public void editProduct(String name,String image,int price,int idCategory,int status,String proDesc,int id){
        ProductsDao.editProduct(name, image, price, idCategory, status, proDesc, id);
    }
    public List<Products> numOfPro(){return ProductsDao.numOfPro();}
    public List<Products> getAllProducts(){return ProductsDao.getAllProducts();}
    public static void main(String[] args) {
        System.out.println(ProductsService.getInstance().numOfPro());
//        System.out.println(ProductsService.getInstance().getProductById(1));
    }



}
