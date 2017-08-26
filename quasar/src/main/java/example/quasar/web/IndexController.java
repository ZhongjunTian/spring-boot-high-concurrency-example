/*
 * COMSAT
 * Copyright (c) 2013-2015, Parallel Universe Software Co. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
/*
 * Based on the corresponding class in Spring Boot Samples.
 * Copyright the original author Dave Syer.
 * Released under the ASF 2.0 license.
 */
package example.quasar.web;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import example.db.Product;
import example.db.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class IndexController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String index() throws InterruptedException, SuspendExecution {
        Fiber.sleep(10);

        return productService.findOne().toString();
    }

    public static void burnCpu(int n) {
        System.out.println("burn thread "+Thread.currentThread());
        double[] data = new double[n];
        for(int i=0; i<data.length; i++){
            data[i] = Math.random();
        }
        Arrays.sort(data);
    }
}
