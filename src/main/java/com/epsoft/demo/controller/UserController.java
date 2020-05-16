package com.epsoft.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.epsoft.demo.base64.RSASignature;
import com.epsoft.demo.bean.dto.FileCheckDTO;
import com.epsoft.demo.bean.dto.ReceiveParm;
import com.epsoft.demo.bean.entity.User;
import com.epsoft.demo.common.ServerResponse;
import com.epsoft.demo.interactive.HttpClientTest;
import com.epsoft.demo.service.article.ArticleService;
import com.epsoft.demo.service.user.UserService;
import com.epsoft.demo.utils.Assert;
import com.epsoft.demo.utils.HttpHelper;
import com.epsoft.demo.utils.HttpUtil;
import com.github.pagehelper.Page;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private Environment environment;

	/*@Autowired
	ReflectUtil reflectUtil;*/

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value="/getUserInfo",produces= {"application/xml;charset=UTF-8"},method=RequestMethod.POST)
	//@ResponseBody
	public Page<User> getUser(@RequestParam("name") String name, @RequestParam("currentPage") String currentPage,
			@RequestParam("pageSize") String pageSize) {
		Page<User> page = userService.getUser(name, currentPage, pageSize);
		return page;

	}

	@PostMapping("/addUser")
	//@ResponseBody
	public void addUser(@RequestParam("phone") String phone,@RequestParam("brith") String brith,@RequestParam("sex") String sex, @RequestParam("relationId") Integer relationId,@RequestParam("userName") String userName, @RequestParam("age") Integer age,
			 @RequestParam("email") String email
			) throws InterruptedException, ParseException {
		userService.addUser(userName, age, sex, brith, relationId, email, phone);
	}
	
	@PostMapping("/addUserbyUser")
	//@ResponseBody
	public void addUserbyUser(@Valid User user) {
		long start = System.currentTimeMillis();
		userService.addUser(user);
		long end = System.currentTimeMillis();
		logger.info("响应时间", end-start);
	}
	
	@PostMapping("/getFileCheck")
	//@ResponseBody
	public String getFileCheck() {
		String s ="{\"hosId\":\"101274\",\"checkDate\":\"20191212\",\"merchantId\":\"weiji\"}";
    	com.alibaba.fastjson.JSONObject json = JSON.parseObject(s);
    	String signContent = RSASignature.getSignContent(json);
    	String sign = RSASignature.sign(signContent, environment.getProperty("pri_key"));
    	json.put("sign", sign);
    	String result = HttpHelper.httpPost("http://127.0.0.1:8811/getCheckFile", json.toString(),1);
    	log.info("收到gateway响应参数[{}]",result);
    	com.alibaba.fastjson.JSONObject respData = JSON.parseObject(result);
    	String respSignContent = RSASignature.getSignContent(respData);
    	boolean isSuccess = RSASignature.doCheck(respSignContent, respData.getString("sign"), environment.getProperty("his_pub_key"));
    	if(!isSuccess) {
    		return "验签失败";
    		
    	}
    	return respSignContent;
	}
	
	@PostMapping("/gateway")
	//@ResponseBody
	public String receiveGetFileCheckPost(@RequestBody ReceiveParm receiveParm) {
		JSONObject receive = JSON.parseObject(JSON.toJSONString(receiveParm));
		String signContent = RSASignature.getSignContent(receive);
    	boolean success = RSASignature.doCheck(signContent, receive.getString("sign"), environment.getProperty("pub_key"));
    	JSONObject json = new JSONObject();
    	if(!success) {
    		json.put("platCode", "BMXXXXX");
    		json.put("platMsg","业务处理异常");
    		String content = RSASignature.getSignContent(json);
    		String sign = RSASignature.sign(content, environment.getProperty("pri_key"));
    		json.put("sign", sign);
    		log.info("gateway响应参数[{}]",json);
    		return json.toString();
    	}
    	json.put("platCode", "00000000");
		json.put("platMsg","成功");
		json.put("body", "{\"checkFileStrs\":[\"20191229\",\"334238438743847\",\"20.00\",\"20191229\",\"334238438743847\",\"30.00\"]}}");
		String content = RSASignature.getSignContent(json);
		String sign = RSASignature.sign(content, environment.getProperty("pri_key"));
		json.put("sign", sign);
		log.info("gateway响应参数[{}]",json);
    	return json.toString();
	}

	/*@PostMapping("/addUserReflct")
	public void addUser(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		User user = null;
		try {
			user = reflectUtil.convert(User.class, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//userService.addUser(user);
		long end = System.currentTimeMillis();
		logger.info("响应时间", end-start);
	}*/

	@GetMapping("/updateById")
	//@ResponseBody
	public ServerResponse<String> updateById(@RequestParam("id") Long id, @RequestParam("sex") String sex,
			@RequestParam("age") Integer age) {
		return userService.updateById(id, sex, age);
	}

	@PostMapping("/deleteById")
	//@ResponseBody
	public void deleteByIds(@RequestParam("id") String id) {
		userService.deleteByIds(id);
	}

	@PostMapping("/selectUserName")
	//@ResponseBody
	public ServerResponse<String> login(String userName) {
		logger.info("请求参数{}:", userName);
		return userService.selectUserName(userName);
	}
	
	@GetMapping("/selectName")
	//@ResponseBody
	public ServerResponse<String> selectName(String userName) {
		User user = userService.selectName(userName);
		if(user!=null) {
			return ServerResponse.createBySuccess("找到您想要的名字");
		}
		return ServerResponse.createBySuccessMessage("找不到你想要的名字");	
	}
	
	@GetMapping("/selectById")
	//@ResponseBody
	public Long selectById(Long id) {
		return userService.selectById(id);	
	}
	
	
	
	@PostMapping("getFormSummit")
	//@ResponseBody
	public String formSummitStyle(@RequestBody String dataJson) {
		JSONObject json = JSON.parseObject(dataJson);
		System.out.println(json);
		Assert.paramsNotNull(json, "name","age","sex");
		return json.toString();
	}
	
	@PostMapping("mockPostHttpClient")
	//@ResponseBody
	public String mockPostHttpClient(@RequestParam("id") Integer id) throws Exception{
		String json ="100006";
		return HttpClientTest.sendPostDataByJson("http://localhost:8083/user/getCategory?id=100006", json);		
	}
	
	@PostMapping("mockPostHttpClientSendJson")
	//@ResponseBody
	public String mockPostHttpClientSendJson(@RequestBody String json) throws Exception{
		return HttpClientTest.sendPostDataByJson("http://localhost:8083/user/postGetById", json);		
	}
	
	@PostMapping("mockPostHttpClientSendMap")
	//@ResponseBody
	public String mockPostHttpClientSendMap(@RequestBody String json) throws Exception{
		Map<String,String> map = new HashMap<>();
		JSONObject jsonObejct = JSON.parseObject(json);
		map.put("id", jsonObejct.getString("id"));
		System.out.println("map是"+map);
		return HttpClientTest.sendPostDataByMap("http://localhost:8083/user/postGetByMap", map,"utf-8");		
	}
	
	@GetMapping("mockTcp")
	//@ResponseBody
	public void mockTcp() {
		userService.mockTcp();
	}
	
	@GetMapping("testApple")
	//@ResponseBody
	public List<User> testJavaApple() {
		List<User> list = userService.queryUserList();
		return list;
	}


	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void testExcelUtil(HttpServletResponse response) throws IOException {
		long start = System.currentTimeMillis();
		}
}
