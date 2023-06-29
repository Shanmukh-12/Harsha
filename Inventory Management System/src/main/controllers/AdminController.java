package main.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    // Warehouse and Home page
    @RequestMapping(value = { "/adminHome" })
    public String getHome() {
        logger.info("Redirecting to admin Home Page!!");
        return "admin/adminHome";
    }

    // WareHouseStockData
    @RequestMapping(value = "/warehouseStock")
    public String getwarehouseStock() {
        logger.info("Redirecting to WareHosueStock Page!!");
        return "admin/warehouseStock";
    }

    // StoreStockData
    @RequestMapping(value = "/storeStock")
    public String getStoreStock() {
        logger.info("Redirecting to StoreStock Page!!");
        return "admin/storeStock";
    }
    
    /* Manipulating Users */
    /* Vendor */

    // Redirect to Add vendor page
    @GetMapping("/addVendor")
    public String addVendor() {
        logger.debug("Redirecting to Add Vendor Page");
        return "admin/addVendor";
    }

    // Redirect to Update vendor page
    @GetMapping("/updateVendor")
    public String updateVendor() {
        logger.debug("Redirecting to Update Vendor Page");
        return "admin/updateVendor";
    }

    // Redirect to Get Vendors Page
    @GetMapping("/displayVendors")
    public String getVendors() {
        logger.debug("Redirecting to Vendor Data Page");
        return "admin/vendorData";
    }

    // Redirect to Delete Vendors Page
    @GetMapping("/deleteVendorPage")
    public String deleteVendor() {
        logger.debug("Redirecting to Delete Vendor Page");
        return "admin/deleteVendor";
    }

    /* Store */

    // Redirect to Add Stores Page
    @GetMapping("/addStorePage")
    public String addStore() {
        logger.debug("Redirecting to Add Store Page");
        return "admin/addStore";
    }

    // Redirect to Delete Stores Page
    @GetMapping("/deleteStorePage")
    public String deleteStore() {
        logger.debug("Redirecting to Delete Store Page");
        return "admin/deleteStore";
    }

    // Redirect to Display Stores Page
    @GetMapping("/displayStorePage")
    public String displayStore() {
        logger.debug("Redirecting to Display Store Page");
        return "admin/storeData";
    }

    /* User */

    // Redirect to Add user Page
    @GetMapping("/addUserPage")
    public String addUser() {
        logger.debug("Redirecting to Add User Page");
        return "admin/addUser";
    }

    // redirect to Delete User Page
    @GetMapping("/deleteUserPage")
    public String deleteUser() {
        logger.debug("Redirecting to Delete User Page");
        return "admin/deleteUser";
    }

    // Display User Information Page
    @GetMapping("/displayUserPage")
    public String displayUser() {
        logger.debug("Redirecting to Display User Page");
        return "admin/userData";
    }

    // redirect to Product Categories page
    @GetMapping("/productCategories")
    public String getProductCategories() {
        logger.debug("Redirecting to Product Categories Page");
        return "admin/productCategories";
    }

    // Reports
    @GetMapping("/reports")
    public String getReports() {
        logger.debug("Redirecting to Reports Page");
        return "admin/reports";
    }
}
