package com.apu.TcpServerForAccessControlDB.interfaces;

public interface ActivatableEntity extends AccessControlEntity {
    
    Boolean getActive();

    void setActive(Boolean active);
    
}
