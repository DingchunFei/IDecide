package com.uom.idecide.result.user.controller;

import com.uom.idecide.result.user.pojo.User;
import com.uom.idecide.result.user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

//	@Autowired
//	private RedisTemplate redisTemplate;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * 增加新用户
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user){
		userService.add(user);
		return new Result(true, StatusCode.OK,"增加成功");
	}

	/**
	 * 根据ID查询
	 * 当前user权限
	 * admin权限
	 */
	@RequestMapping(value="/{userId}",method= RequestMethod.GET)
	public Result findById(@PathVariable(value="userId") String id){
		User user;
		try{
			user = userService.findById(id);
		}catch(Exception e){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
		return new Result(true, StatusCode.OK,"查询成功",user);
	}

	/**
	 * user修改
	 * 当前user权限
	 */
	@RequestMapping(value="/{userId}",method= RequestMethod.PUT)
	public Result updateById(@RequestBody User user, @PathVariable(value="userId") String id){
		try{
			userService.updateById(user);
		}catch(Exception e){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
		return new Result(true, StatusCode.OK,"修改成功");
	}

	/**
	 * user删除
	 * admin权限
	 */
	@RequestMapping(value="/{userId}",method= RequestMethod.DELETE)
	public Result deleteById(@PathVariable(value="userId") String id){
		try{
			userService.deleteById(id);
		}catch(Exception e){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
		return new Result(true, StatusCode.OK,"删除成功");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestBody User user){
		user =userService.login(user.getEmail(),user.getPassword());
		if(user == null){
			return new Result(false, StatusCode.LOGINERROR,"login fail");
		}

		String token = jwtUtil.createJWT(user.getUserId(),user.getEmail(),"user");
		//把token打印出来看看
		System.out.println(token);
		Map<String,Object> map = new HashMap<>();
		map.put("token",token);		//把token返回给前端
		map.put("roles","user");	//告诉前端role是user
		return new Result(true, StatusCode.OK,"login successful",map);
	}

	/**
	 * 登出
	 * user权限
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public Result logout(@RequestBody User user){
		//TODO 登出只需要在前端销毁token即可
		return new Result(true, StatusCode.OK,"logout successful");
	}

	/**
	 * 查询用户列表
	 * admin权限
	 */
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public Result userList(){
		List<User> userList;
		try{
			userList = userService.findAll();
		}catch(Exception e){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
		return new Result(true, StatusCode.OK,"operation successful",userList);
	}

	/**
	 * 查询用户列表含分页
	 * admin权限
	 */
	@RequestMapping(value = "/userList/{page}/{size}", method = RequestMethod.GET)
	public Result userListWithPagination(@PathVariable(value="page") int page,@PathVariable(value="size") int size){
		Page<User> pages;
		try{
			pages = userService.findAllWithPagination(page, size);
		}catch(Exception e){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
		//PageResult中第一个是返回记录条数，第二个是对应的userList
		return new Result(true, StatusCode.OK,"operation successful", new PageResult<User>(page,pages.getTotalElements(),pages.getTotalPages(),pages.getContent()));
	}


	@RequestMapping(value = "/jwt", method = RequestMethod.POST)
	public Result testJwt(HttpServletRequest req){
		String token = (String) req.getAttribute("claims_user");
		if(token!=null){
			System.out.println(token);
		}else{
			System.out.println("没有token");
		}
		return new Result(true, StatusCode.OK,"test end");
	}
	
}
