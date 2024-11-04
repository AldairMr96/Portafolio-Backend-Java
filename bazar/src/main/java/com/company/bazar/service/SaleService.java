package com.company.bazar.service;import com.company.bazar.model.Sale;import com.company.bazar.repository.ISaleRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class SaleService implements ISaleService {    @Autowired    ISaleRepository saleRepository;    @Override    public void createSale(Sale sale) {        saleRepository.save(sale);    }    @Override    public List<Sale> getSales() {        List<Sale> saleList = saleRepository.findAll();        return saleList;    }    @Override    public Sale findSale(Long codSale) {        return saleRepository.findById(codSale).orElse(null);    }    @Override    public void deleteSale(Long codSale) {        saleRepository.deleteById(codSale);    }    @Override    public void editSale(Sale sale) {        this.createSale(sale);    }}