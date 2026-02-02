package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.domain.StoreStatus;
import com.sys_nova.pos_system.mapper.StoreMapper;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.StoreDTO;
import com.sys_nova.pos_system.repository.StoreRepository;
import com.sys_nova.pos_system.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {
        Store store = StoreMapper.mapToStore(storeDTO);
        store.setStoreAdmin(user);
        return StoreMapper.mapToStoreDto(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("Store not found with id: " + id));
        return StoreMapper.mapToStoreDto(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream()
                .map(StoreMapper::mapToStoreDto)
                .collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() {
        // بنرجع الـ Entity مباشرة حسب طلب الـ Interface في الصورة
        return storeRepository.findByStoreAdminId(null); // استبدل null بـ id الأدمن الفعلي
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("Store not found"));
        
        if(storeDTO.getStoreName() != null) store.setStoreName(storeDTO.getStoreName());
        // ... باقي الحقول بنفس الطريقة
        
        return StoreMapper.mapToStoreDto(storeRepository.save(store));
    }

    @Override
    public void deleteStore(Long id) throws Exception {
        storeRepository.deleteById(id);
    }

    @Override
    public StoreDTO moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("Store not found"));
        store.setStatus(status);
        return StoreMapper.mapToStoreDto(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreByEmployee() {
        return null; // ستكتمل لاحقاً
    }
}