package org.fungover.laboration2.resource;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.fungover.laboration2.entities.Category;
import org.fungover.laboration2.entities.Product;
import org.fungover.laboration2.entities.Products;
import org.fungover.laboration2.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("/")
public class ProductResource {

    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    private WarehouseService warehouseService; // Assuming Warehouse is properly implemented

    @Context
    private UriInfo uriInfo;

    public ProductResource() {}
    @Inject
    public ProductResource(@Named("Impl") WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

//    @Inject
//    public ProductResource(@Named("Second") WarehouseService warehouseService){
//        System.out.println("HelloResource object created");
//        this.warehouseService = warehouseService;
//    }

    @GET
    @Path("/warehouse")
    @Produces("text/plain")
    public String warehouse() {
        return "This is warehouse service!";
    }

    @POST
    @Path("/two-products")
    @Produces(MediaType.APPLICATION_JSON)
    public Products names() {
        return new Products(List.of( new Product("5", "pc", Category.electronics,15,
                LocalDate.of(2024, 10, 1),
                LocalDate.of(2024, 10, 1)),
                new Product("6", "cola", Category.drink, 3,
                        LocalDate.of(2024, 2, 16),
                        LocalDate.of(2024, 2, 16))), "updated");

    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Products allProducts() {
        logger.info("This is all products");
        return new Products(warehouseService.getAllProducts(), "Updated");
    }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response oneProduct(@PathParam("id") String id) {
        if(id.equals("1"))
            return Response.ok().entity(new Product("1", "iPhone", Category.electronics, 10,
                    LocalDate.of(2024, 1, 1),
                    LocalDate.of(2024, 1, 1))).build();
        if (id.equals("2"))
            return Response.ok().entity(new Product("2", "Water", Category.drink, 15,
                    LocalDate.of(2024, 2, 1),
                    LocalDate.of(2024, 2, 1))).build();
        if (id.equals("4"))
            return Response.ok().entity(new Product("4", "Picture Book", Category.books, 17,
                    LocalDate.of(2024, 2, 16),
                    LocalDate.of(2024, 2, 16))).build();
        logger.error("Invalid id uesd {}", id);
        return Response.status(Response.Status.NOT_FOUND).header("product-error","Try again").build();
    }

//    @POST
//    @Path("/product")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response names(@Valid Product product) {
//        warehouseService.addProduct(product);
//        return Response.created(URI.create("/product/" + warehouseService.getCount())).build();
//    }
@POST
@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public Response names(@Valid Product product, @HeaderParam("X-Forwarded-Proto") String proto) {

    warehouseService.addProduct(product);

    //All this code is to handle the case of running behind reverse proxy gateway
    if (proto == null) {
        proto = uriInfo.getRequestUri().getScheme(); // Fallback to request scheme
    }

    URI baseUri = uriInfo.getBaseUri();
    String fullPath = baseUri.getPath() + "product/" + warehouseService.getCount();
    URI location = URI.create(proto + "://" + baseUri.getHost() + (baseUri.getPort() != -1 ? ":" + baseUri.getPort() : "") + fullPath);

    return Response.created(location).build();
}

    @GET
    @Path("/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductsByCategory(@PathParam("category")Category category) {
        System.out.println("Request received for category: " + category);
        List<Product> products = warehouseService.getProductsByCategorySortedByName(category);
        System.out.println("Products retrieved: " + products.size());
        return products;
    }
}