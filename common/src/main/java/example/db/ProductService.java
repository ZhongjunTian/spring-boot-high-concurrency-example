package example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public static void burnCpu() {
        burnCpu(10000);
    }

    public static void burnCpuSlightly() {
        burnCpu(1000);
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
