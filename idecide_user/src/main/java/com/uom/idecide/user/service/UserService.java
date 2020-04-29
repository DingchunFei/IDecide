package com.uom.idecide.user.service;

import com.uom.idecide.user.dao.UserDao;
import com.uom.idecide.user.pojo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;
import util.JwtUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * 增加
	 * @param user
	 */
	public void add(User user) {
		user.setUserId( idWorker.nextId()+"" );
		//密码加密
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
	}


	public User login(String email, String password) {
		User userLogin = userDao.findByEmail(email);
		//2.然后那数据库中的密码和用户输入的密码匹配是否相同
		if(userLogin!=null && encoder.matches(password,userLogin.getPassword())){	//prevent from null pointer
			//login successful
			return userLogin;
		}
		return null;
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public Page<User> findAllWithPagination(int page, int size) {
		//DB从0开始，页面从1开始
		Pageable pageable = PageRequest.of(page-1,size);
		return userDao.findAll(pageable);
	}


	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	 * 修改
	 * @param user
	 */
	public void updateById(User user) {
		userDao.save(user);
	}

	@Autowired
	private HttpServletRequest request;
	/**
	 * 删除：必须admin角色才能删除
	 * @param id
	 */
	public void deleteById(String id) {
		//把token从header中取出
		String token = (String)request.getAttribute("claims_admin");
		if(token==null || "".equals(token)){
			throw new RuntimeException("无法删除该用户，权限不足！");
		}
		//只有通过上面的层层判断全部通过后，才能删除用户
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))) {
                	predicateList.add(cb.like(root.get("userId").as(String.class), "%"+(String)searchMap.get("userId")+"%"));
                }
                // password
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // gender
                if (searchMap.get("gender")!=null && !"".equals(searchMap.get("gender"))) {
                	predicateList.add(cb.like(root.get("gender").as(String.class), "%"+(String)searchMap.get("gender")+"%"));
                }
				// partnerGender
				if (searchMap.get("partnerGender")!=null && !"".equals(searchMap.get("partnerGender"))) {
					predicateList.add(cb.like(root.get("partnerGender").as(String.class), "%"+(String)searchMap.get("partnerGender")+"%"));
				}
                // email
                if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
                	predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
				// phone
				if (searchMap.get("phoneNumber")!=null && !"".equals(searchMap.get("phoneNumber"))) {
					predicateList.add(cb.like(root.get("phoneNumber").as(String.class), "%"+(String)searchMap.get("phoneNumber")+"%"));
				}
				// phone
				if (searchMap.get("postcode")!=null && !"".equals(searchMap.get("postcode"))) {
					predicateList.add(cb.like(root.get("postcode").as(String.class), "%"+(String)searchMap.get("postcode")+"%"));
				}
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}


}
