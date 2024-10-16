package org.fungover.laboration2;

import org.fungover.laboration2.resource.ProductResource;
import org.fungover.laboration2.service.ImplWarehouseService;

public class Factory {

    public static ProductResource resource(){

        return new ProductResource(new ImplWarehouseService());
    }
}
