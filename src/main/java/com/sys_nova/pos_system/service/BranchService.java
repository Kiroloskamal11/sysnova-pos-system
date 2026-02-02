package com.sys_nova.pos_system.service;

import com.sys_nova.pos_system.payload.dto.BranchDTO;
import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDTO);
    BranchDTO updateBranch(Long id, BranchDTO branchDTO);
    List<BranchDTO> getBranchesByStore(Long storeId);
    BranchDTO getBranchById(Long id);
    void deleteBranch(Long id);
}