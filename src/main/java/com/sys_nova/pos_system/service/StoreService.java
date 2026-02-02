package com.sys_nova.pos_system.service;

import java.util.List;

import com.sys_nova.pos_system.domain.StoreStatus;
import com.sys_nova.pos_system.exception.UserException;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.StoreDTO;





public interface StoreService {
    StoreDTO createStore(StoreDTO storeDTO, User user); // ترجع DTO وتستقبل DTO
    StoreDTO getStoreById(Long id) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException; // لاحظ هنا الفيديو سابها Store (Entity) غالباً لغرض داخلي
    StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception;
    void deleteStore(Long id)throws Exception;
    StoreDTO getStoreByEmployee()throws Exception; 

    StoreDTO moderateStore(Long id ,StoreStatus status) throws Exception;
}

















// package com.sys_nova.pos_system.service;

// import com.sys_nova.pos_system.model.Store;
// import com.sys_nova.pos_system.model.User;

// public interface StoreService {
//     // إنشاء محل جديد وربطه بالمدير (User)
//     public Store createStore(Store store, User user);

//     // البحث عن محل بمعلومية صاحبه (Admin)
//     public Store findStoreByAdminId(Long adminId);

//     // تعديل بيانات المحل
//     public Store updateStore(Long id, Store store) throws Exception;
// }