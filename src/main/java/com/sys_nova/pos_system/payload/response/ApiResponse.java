// package com.sys_nova.pos_system.payload.response;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class ApiResponse {
//     private String message;
//     private boolean success;
// }

package com.sys_nova.pos_system.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

// package com.sys_nova.pos_system.payload.response;

// public class ApiResponse {
// private String message;
// private boolean success;

// // ضيف الـ Constructor ده ضروري
// public ApiResponse(String message, boolean success) {
// this.message = message;
// this.success = success;
// }

// // Getters and Setters
// public String getMessage() { return message; }
// public void setMessage(String message) { this.message = message; }
// public boolean isSuccess() { return success; }

// public void setSuccess(boolean success) { this.success = success; }
// }