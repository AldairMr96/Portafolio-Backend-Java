package com.company.bazar.controller;import com.company.bazar.model.Sale;import com.company.bazar.service.ISaleService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.DeleteMapping;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.PutMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseStatus;import org.springframework.web.bind.annotation.RestController;import java.util.List;@RestController@RequestMapping("/bazar/sale")public class SaleController {  @Autowired  private ISaleService saleService;    @PostMapping("/create")    @ResponseStatus (HttpStatus.CREATED)    public Sale createSale (@RequestBody Sale sale ){        saleService.createSale(sale);        return sale;    }    @GetMapping("/get")    @ResponseStatus(HttpStatus.OK)    public List<Sale> getSales (){        List<Sale> saleList = saleService.getSales();        return  saleList;    }    @PutMapping("/edit")    @ResponseStatus (HttpStatus.OK)    public Sale editSale (@RequestBody Sale sale){        saleService.editSale(sale);        return saleService.findSale(sale.getCodSale());    }    @DeleteMapping("/delete")    @ResponseStatus(HttpStatus.OK)    public String deleteSale (Long codSale){        saleService.deleteSale(codSale);        return "Sale deleted";    }}