package com.company.bazar.controller;import com.company.bazar.dto.CreateSaleDTO;import com.company.bazar.dto.MaxSaleDTO;import com.company.bazar.dto.SaleDTO;import com.company.bazar.model.Client;import com.company.bazar.model.Product;import com.company.bazar.model.Sale;import com.company.bazar.model.SaleProduct;import com.company.bazar.service.ISaleService;import org.apache.coyote.Response;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.format.annotation.DateTimeFormat;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.DeleteMapping;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.PutMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseStatus;import org.springframework.web.bind.annotation.RestController;import java.time.LocalDate;import java.util.Date;import java.util.List;@RestController@RequestMapping("/bazar/sale")public class  SaleController {  @Autowired  private ISaleService saleService;    @PostMapping("/create")    @ResponseStatus (HttpStatus.CREATED)    public ResponseEntity<?> createSale (@RequestBody CreateSaleDTO sale ){        try {            saleService.createSale(sale);            return ResponseEntity.ok("Sale Create Susccessfully");        }catch (RuntimeException ex){            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());        }catch (Exception ex) {            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");        }    }    @GetMapping("/get")    @ResponseStatus(HttpStatus.OK)    public List<Sale> getSales (){        List<Sale> saleList = saleService.getSales();        return  saleList;    }    @PutMapping("/edit")    @ResponseStatus (HttpStatus.OK)    public Sale editSale (@RequestBody CreateSaleDTO sale){        saleService.editSale(sale);        return null;    }    @DeleteMapping("/delete")    public ResponseEntity<?> deleteSale (Long codSale){        try {            saleService.deleteSale(codSale);            return ResponseEntity.ok("Delete sale  susccessfully");        }catch (RuntimeException ex){            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());        }catch (Exception ex){            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server internal Error");        }    }    @GetMapping("/find/products/sale")    @ResponseStatus(HttpStatus.OK)    public List<String> findProductSale (@RequestParam Long codSale){        return  saleService.findProductSale(codSale);    }    @GetMapping("/find/total/sale")    @ResponseStatus(HttpStatus.OK)    //La anotación @DateTimeFormat(pattern = "yyyy-MM-dd") indica que el formato de fecha esperado es yyyy-MM-dd    public SaleDTO findTotalSale (@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateSale){        return  saleService.totalSale(dateSale);    }    @GetMapping("/find/max/sale")    @ResponseStatus(HttpStatus.OK)   public MaxSaleDTO findMaxSale (){        return saleService.saleMajor();    }    @GetMapping("/find/sale")    public ResponseEntity<?> findClient (@RequestParam long codSale){        try{            Sale sale = saleService.findSale(codSale);            return ResponseEntity.ok(sale);        } catch (RuntimeException ex) {            // Captura la excepción y devuelve una respuesta con estado 404            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());        } catch (Exception ex) {            // Captura cualquier otra excepción no controlada            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server internal Error");        }    }}