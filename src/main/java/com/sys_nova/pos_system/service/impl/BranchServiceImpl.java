package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.mapper.BranchMapper;
import com.sys_nova.pos_system.model.Branch;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.payload.dto.BranchDTO;
import com.sys_nova.pos_system.repository.BranchRepository;
import com.sys_nova.pos_system.repository.StoreRepository;
import com.sys_nova.pos_system.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public BranchDTO createBranch(BranchDTO dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Branch branch = Branch.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .workingDay(dto.getWorkingDay())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .store(store)
                .build();

        return BranchMapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO dto) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        branch.setName(dto.getName());
        branch.setPhone(dto.getPhone());
        branch.setAddress(dto.getAddress());
        branch.setEmail(dto.getEmail());
        branch.setWorkingDay(dto.getWorkingDay());
        branch.setOpenTime(dto.getOpenTime());
        branch.setCloseTime(dto.getCloseTime());

        return BranchMapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public List<BranchDTO> getBranchesByStore(Long storeId) {
        return branchRepository.findByStoreId(storeId).stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        return BranchMapper.toDTO(branch);
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}