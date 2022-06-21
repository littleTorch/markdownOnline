package com.torch.admin.dao;

import com.torch.admin.domain.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends MongoRepository<Admin, ObjectId> {
}
