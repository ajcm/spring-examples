package com.baeldung.lsd.projection;

import org.springframework.beans.factory.annotation.Value;

public interface WorkerFullname {

    @Value("#{target.firstName} #{target.lastName}")
    String getFullname();
}
