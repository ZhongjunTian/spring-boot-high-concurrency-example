package example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    private static final Object lock = new Object();
    private static boolean inited = false;
    private int total = 1000000;
    @PostConstruct
    private void initDatabase(){
        if(!inited)
            synchronized(lock) {
                if (!inited) {
                    inited = true;
//                    insertProducts(total);
//                    System.out.println("database inserted "+ total +" products");
                }
            }
    }
    public Product findOne(){
        return repo.findOne((long)(total*Math.random()));
    }
    public List<Product> getAllProducts() {
        return repo.findAll();
    }
    public void insertProducts(int n){
        List<Product> products = new LinkedList<Product>();
        for(int i=0; i<n; i++) {
            Product p = new Product();
            p.setProductId((long)i);
            p.setDescription("p:" + Math.random());
            products.add(p);
        }
        repo.save(products);
    }
    public static void burnCpu(int n) {
        double[] data = new double[n];
        for(int i=0; i<data.length; i++){
            data[i] = Math.random();
        }
        Arrays.sort(data);
    }

    public static void blockIo() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
