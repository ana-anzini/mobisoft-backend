package com.mobisoft.mobisoftapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobisoft.mobisoftapi.configs.exceptions.CostumerNotFoundException;
import com.mobisoft.mobisoftapi.dtos.costumers.CostumerDTO;
import com.mobisoft.mobisoftapi.models.Costumer;
import com.mobisoft.mobisoftapi.repositories.CostumerRepository;

@Service
public class CostumerService {

	@Autowired
    private CostumerRepository costumerRepository;

    public Costumer createCostumer(CostumerDTO costumerDTO) {
        Costumer costumer = new Costumer();
        costumer.setName(costumerDTO.getName());
        costumer.setCpfOrCnpj(costumerDTO.getCpfOrCnpj());
        costumer.setPhone(costumerDTO.getPhone());
        costumer.setEmail(costumerDTO.getEmail());
        costumer.setCep(costumerDTO.getCep());
        costumer.setAddress(costumerDTO.getAddress());
        costumer.setNumber(costumerDTO.getNumber());
        costumer.setNeighborhood(costumerDTO.getNeighborhood());
        costumer.setAdditional(costumerDTO.getAdditional());
        costumer.setRg(costumerDTO.getRg());
        costumer.setBirthday(costumerDTO.getBirthday());
        costumer.setNetworkType(costumerDTO.getNetworkType());
        costumer.setPersonType(costumerDTO.getPersonType());
        costumer.setNotes(costumerDTO.getNotes());

        return costumerRepository.save(costumer);
    }

    public Costumer getCostumerById(Long id) {
        return costumerRepository.findById(id).orElseThrow(() -> new CostumerNotFoundException(id));
    }

    public List<Costumer> getAllCostumers() {
        return costumerRepository.findAll();
    }

    public Costumer updateCostumer(Long id, CostumerDTO costumerDTO) {
        Costumer existingCostumer = getCostumerById(id);
        existingCostumer.setName(costumerDTO.getName());
        existingCostumer.setCpfOrCnpj(costumerDTO.getCpfOrCnpj());
        existingCostumer.setPhone(costumerDTO.getPhone());
        existingCostumer.setEmail(costumerDTO.getEmail());
        existingCostumer.setCep(costumerDTO.getCep());
        existingCostumer.setAddress(costumerDTO.getAddress());
        existingCostumer.setNumber(costumerDTO.getNumber());
        existingCostumer.setNeighborhood(costumerDTO.getNeighborhood());
        existingCostumer.setAdditional(costumerDTO.getAdditional());
        existingCostumer.setRg(costumerDTO.getRg());
        existingCostumer.setBirthday(costumerDTO.getBirthday());
        existingCostumer.setNetworkType(costumerDTO.getNetworkType());
        existingCostumer.setPersonType(costumerDTO.getPersonType());
        existingCostumer.setNotes(costumerDTO.getNotes());

        return costumerRepository.save(existingCostumer);
    }

    public void deleteCostumer(Long id) {
        costumerRepository.deleteById(id);
    }
}
