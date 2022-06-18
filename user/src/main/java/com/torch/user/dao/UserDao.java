package com.torch.user.dao;

import com.torch.user.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<UserInfo,String> {
}
