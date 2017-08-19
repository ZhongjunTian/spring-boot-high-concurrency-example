package example.db;

import org.junit.Test;

/**
 * Created by zhongjun on 8/18/17.
 */
public class ProductServiceTest {
    @Test
    public void burnCpu() throws Exception {
        long t0 = System.currentTimeMillis();
        ProductService service = new ProductService();
        for(int i=0; i<1000; i++)
            service.burnCpu();
        long t1 = System.currentTimeMillis();
        System.out.println((t1-t0));
    }

}