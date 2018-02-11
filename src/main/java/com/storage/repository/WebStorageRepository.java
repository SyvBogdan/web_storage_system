package com.storage.repository;

import com.storage.entity.WebStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebStorageRepository  extends JpaRepository<WebStorageEntity,String> {

}
