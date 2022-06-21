package com.torch.document.dao;

import com.torch.document.domain.BanInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanDao extends MongoRepository<BanInfo, ObjectId> {

}
