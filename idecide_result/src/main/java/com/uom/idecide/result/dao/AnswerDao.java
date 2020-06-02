package com.uom.idecide.result.dao;

        import com.uom.idecide.result.pojo.Answer;
        import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerDao extends MongoRepository<Answer,String> {

}
