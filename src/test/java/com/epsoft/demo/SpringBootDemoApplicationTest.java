package com.epsoft.demo;

import com.alibaba.fastjson.JSON;
import com.epsoft.demo.annotation.Value;
import com.epsoft.demo.base64.RSASignature;
import com.epsoft.demo.bean.dto.AccountMessage;
import com.epsoft.demo.bean.dto.FileCheckDTO;
import com.epsoft.demo.bean.entity.Person;
import com.epsoft.demo.bean.entity.User;
import com.epsoft.demo.bean.model.TableHeaderExcelProperty;
import com.epsoft.demo.bean.po.RegConfirm;
import com.epsoft.demo.dao.RegConfirmMapper;
import com.epsoft.demo.dao.UserMapper;
import com.epsoft.demo.jdk8.PageSort;
import com.epsoft.demo.pattern.proxy.cglib.CglibCommon;
import com.epsoft.demo.principle.dependenceInversion.Leym;
import com.epsoft.demo.service.proxy.Payment;
import com.epsoft.demo.service.proxy.impl.AliPay;
import com.epsoft.demo.service.proxy.impl.RealPay;
import com.epsoft.demo.service.user.impl.UserServiceImpl;
import com.epsoft.demo.utils.DataConverter;
import com.epsoft.demo.utils.DateUtils;
import com.epsoft.demo.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@Slf4j
public class SpringBootDemoApplicationTest {

    @Autowired
    private Person person;

    @Autowired
    private Leym leym;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Properties properties;

    @Autowired
    private PageSort pageSort;

    @Autowired
    private RegConfirmMapper regConfirmMapper;

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CglibCommon cglibCommon;

    @Test
    public void contextLoads() {

        Map<String, Object> parseObject = JSON.parseObject(person.getHnMock(), Map.class);
        System.out.println(parseObject);

        List<Map> parseArray = JSON.parseArray(JSON.toJSONString(parseObject.get("data")), Map.class);
        List list = new ArrayList<>();
        for (Map map : parseArray) {
            System.out.println(map);
        }
        Collections.sort(parseArray, new Comparator<Map>() {

            @Override
            public int compare(Map o1, Map o2) {
                if (Integer.valueOf(String.valueOf(o2.get("year"))).compareTo(Integer.valueOf(String.valueOf(o1.get("year")))) != 0) {
                    return Integer.valueOf(String.valueOf(o2.get("year"))).compareTo(Integer.valueOf(String.valueOf(o1.get("year"))));
                } else {
                    return Integer.valueOf(String.valueOf(o2.get("month"))).compareTo(Integer.valueOf(String.valueOf(o1.get("month"))));
                }
            }
        });
        for (Object object : parseArray) {
            System.out.println("排序后" + object);
        }
    }

    @Test
    public void testHnMap() {
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(person.getHnMock());
        System.out.println("\n1" + json);

        Map map = JSON.parseObject(json.getString("data"), Map.class);
        System.out.println("\n2" + map);

        List<Map> list1 = JSON.parseArray(json.get("data").toString(), Map.class);
        System.out.println("\n3" + list1);
    }

    @Test
    public void testRedis() {
        //redisTemplate.opsForSet().add("red_123", "1","2","3");
        long start = System.currentTimeMillis();
        stringRedisTemplate.opsForValue().set("hkey", "HELLO REDIS", 1L, TimeUnit.MINUTES);
        for (int i = 0; i < 100; i++) {
            if (stringRedisTemplate.opsForValue().get("hkey") != null) {
                List<User> list = userMapper.selectAll();
                if (list != null && list.size() > 0) {
                    System.out.println("查询到结果");
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
		/*Boolean setIfAbsent = stringRedisTemplate.opsForValue().setIfAbsent("hellokey", "hellovalue");
		System.out.println(setIfAbsent);*/
    }

    @Test
    public void testPorxy() {
        log.info("我是rabbitAdmin===========================\t{}",rabbitAdmin);
        Payment proxy = new AliPay(new RealPay());
        proxy.pay();
    }

    @Test
    public void testEmail() {
        String email = "1";
        if (email.length() == 17 && email.endsWith("@qq.com")) {
            System.out.println("验证通过");
        } else {
            System.out.println("二狗子，你连qq都不会输吗？");
        }

    }

    @Test
    public void testValue() throws InstantiationException, IllegalAccessException, ClassNotFoundException, UnsupportedEncodingException, FileNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		/*AccountMessage newInstance = AccountMessage.class.newInstance();
		newInstance.setGroupName("杭州");
		System.out.println(newInstance.toString());
		
		AccountMessage newInstance3 = (AccountMessage)Class.forName("com.epsoft.demo.bean.dto.AccountMessage").newInstance();
		newInstance3.setGroupName("上海");
		System.out.println(newInstance3.toString());
		
		AccountMessage accountMessage = new AccountMessage();
		AccountMessage newInstance2 = accountMessage.getClass().newInstance();
		newInstance2.setGroupName("南京");
		System.out.println(newInstance2.toString());*/

        AccountMessage obj = new AccountMessage();
        Class<? extends AccountMessage> clz = obj.getClass();

        Field[] fields = AccountMessage.class.getDeclaredFields();
        this.properties = new Properties();
        this.properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(AccountMessage.class.getClassLoader().getResource("application-prod.properties").getFile()), "utf-8")));
        for (Field field : fields) {
            field.setAccessible(true);
            boolean annotationPresent = field.isAnnotationPresent(Value.class);
            if (annotationPresent) {
                Value valueAnnotation = field.getAnnotation(Value.class);
                String value = valueAnnotation.value();
                String propertiedValue = Objects.requireNonNull(this.properties.getProperty(value));
                String nameType = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                String type = field.getGenericType().toString();
                if (type.equals("class java.lang.String")) {
                    Method m = clz.getMethod("set" + nameType, String.class);
                    // invoke方法传递实例对象，因为要对实例处理，而不是类
                    m.invoke(obj, propertiedValue);
                }
                // int Integer类型
                if (type.equals("class java.lang.Integer")) {
                    Method m = clz.getMethod("set" + nameType, Integer.class);
                    m.invoke(obj, Integer.parseInt(propertiedValue));
                }
                if (type.equals("int")) {
                    Method m = clz.getMethod("set" + nameType, int.class);
                    m.invoke(obj, (int) Integer.parseInt(propertiedValue));
                }
                // boolean Boolean类型
                if (type.equals("class java.lang.Boolean")) {
                    Method m = clz.getMethod("set" + nameType, Boolean.class);
                    if (propertiedValue.equalsIgnoreCase("true")) {
                        m.invoke(obj, true);
                    }
                    if (propertiedValue.equalsIgnoreCase("false")) {
                        m.invoke(obj, true);
                    }
                }
                if (type.equals("boolean")) {
                    Method m = clz.getMethod("set" + nameType, boolean.class);
                    if (propertiedValue.equalsIgnoreCase("true")) {
                        m.invoke(obj, true);
                    }
                    if (propertiedValue.equalsIgnoreCase("false")) {
                        m.invoke(obj, true);
                    }
                }
                // long Long 数据类型
                if (type.equals("class java.lang.Long")) {
                    Method m = clz.getMethod("set" + nameType, Long.class);
                    m.invoke(obj, Long.parseLong(propertiedValue));
                }
                if (type.equals("long")) {
                    Method m = clz.getMethod("set" + nameType, long.class);
                    m.invoke(obj, Long.parseLong(propertiedValue));
                }
                // 时间数据类型
                if (type.equals("class java.util.Date")) {
                    Method m = clz.getMethod("set" + nameType, java.util.Date.class);
                    m.invoke(obj, DataConverter.convert(propertiedValue));
                }
            }
        }
        System.out.println(obj.getPaymentId());
    }

    @Test
    public void testRandom() {
        System.out.println(new Random().nextInt(100));
        System.out.println(new Random().nextLong());
    }

    @Test
    public void testDendenceInversion() {
        leym.StudyPrincipleDependenceInversion();
    }

    @Test
    public void testJava8Sort() {
        String s = "{\"data\":{\"insurances\":[{\"areacode\":\"330482\",\"insurancename\":\"1\",\"insurancestatus\":\"1\"},{\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancestatus\":\"1\"},{\"areacode\":\"330482\",\"insurancename\":\"3\",\"insurancestatus\":\"0\"},{\"areacode\":\"330482\",\"insurancename\":\"4\",\"insurancestatus\":\"0\"},{\"areacode\":\"330482\",\"insurancename\":\"5\",\"insurancestatus\":\"0\"}],\"contribution\":[{\"insuranceGroupNo\":\"88888888\",\"month\":\"10\",\"year\":\"2017\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"123.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"2470\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"11\",\"year\":\"2017\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"123.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"2470\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"12\",\"year\":\"2017\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"123.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"2470\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"01\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"02\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"03\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"insurancefee\":\"8.3\",\"areacode\":\"330482\",\"insurancename\":\"5\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"04\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"22\",\"areacode\":\"330482\",\"insurancename\":\"3\",\"insurancebase\":\"1100\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"12.6\",\"areacode\":\"330482\",\"insurancename\":\"4\",\"insurancebase\":\"1570\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"8.3\",\"areacode\":\"330482\",\"insurancename\":\"5\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"05\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"22\",\"areacode\":\"330482\",\"insurancename\":\"3\",\"insurancebase\":\"1100\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"12.6\",\"areacode\":\"330482\",\"insurancename\":\"4\",\"insurancebase\":\"1570\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"8.3\",\"areacode\":\"330482\",\"insurancename\":\"5\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"06\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"22\",\"areacode\":\"330482\",\"insurancename\":\"3\",\"insurancebase\":\"1100\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"13.2\",\"areacode\":\"330482\",\"insurancename\":\"4\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"07\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"08\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"09\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"10\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"11\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"12\",\"year\":\"2018\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"215.5\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4310\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"01\",\"year\":\"2019\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"238\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4760\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"02\",\"year\":\"2019\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"238\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4760\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"03\",\"year\":\"2019\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"238\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4760\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"04\",\"year\":\"2019\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"238\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4760\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"5\",\"insurancebase\":\"--\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"05\",\"year\":\"2019\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"238\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4760\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"3\",\"insurancebase\":\"--\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"insurancefee\":\"8.3\",\"areacode\":\"330482\",\"insurancename\":\"5\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"06\",\"year\":\"2019\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"238\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4760\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"22\",\"areacode\":\"330482\",\"insurancename\":\"3\",\"insurancebase\":\"1100\",\"paymentStatus\":\"1\"},{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"4\",\"insurancebase\":\"--\"},{\"insurancefee\":\"8.3\",\"areacode\":\"330482\",\"insurancename\":\"5\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"}]},{\"insuranceGroupNo\":\"88888888\",\"month\":\"07\",\"year\":\"2019\",\"Remarks\":\" \",\"Insuranceprofile\":[{\"areaCode\":\"330482\",\"insurancefee\":\"--\",\"insurancename\":\"1\",\"insurancebase\":\"--\"},{\"insurancefee\":\"238\",\"areacode\":\"330482\",\"insurancename\":\"2\",\"insurancebase\":\"4760\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"22\",\"areacode\":\"330482\",\"insurancename\":\"3\",\"insurancebase\":\"1100\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"13.2\",\"areacode\":\"330482\",\"insurancename\":\"4\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"},{\"insurancefee\":\"8.3\",\"areacode\":\"330482\",\"insurancename\":\"5\",\"insurancebase\":\"1650\",\"paymentStatus\":\"1\"}]}],\"sex\":\"1\",\"name\":\"周亚华\",\"endtime\":\"201710\",\"currenGroupNo\":\"88888888\",\"starttime\":\"201907\",\"idNo\":\"330422196703206325\",\"insuranceGroup\":\"灵活就业个人参保\"},\"result\":1}";
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(s);
        System.out.println(json);
    }

    @Test
    public void testPage() {
        pageSort.testPage();
    }

    @Test
    public void testJSONObjectOrJSONArray() throws JSONException {
        //String jsonObjectType = "<body><state>1</state><result></result><PBXX><ZLF>0</ZLF><YYXE>0</YYXE><YSMC>陈铁忠</YSMC><GHF>14</GHF><GZRQ>2018-12-17</GZRQ><YSDM>9114</YSDM><PBID>2018121710191141</PBID><KSMC>疼痛科</KSMC><KSDM>101</KSDM><TINGZHENBZ>0</TINGZHENBZ><ZBLB>1</ZBLB><GHLB>2</GHLB><HYSL>30</HYSL><SYHY>30</SYHY><YYRS>0</YYRS></PBXX></body>";
        String jsonArrayType = "<body><state>1</state><result></result><PBXX><ZLF>0</ZLF><YYXE>0</YYXE><YSMC>陈铁忠</YSMC><GHF>14</GHF><GZRQ>2018-12-17</GZRQ><YSDM>9114</YSDM><PBID>2018121710191141</PBID><KSMC>疼痛科</KSMC><KSDM>101</KSDM><TINGZHENBZ>0</TINGZHENBZ><ZBLB>1</ZBLB><GHLB>2</GHLB><HYSL>30</HYSL><SYHY>30</SYHY><YYRS>0</YYRS></PBXX><PBXX><ZLF>0</ZLF><YYXE>0</YYXE><YSMC>陈铁忠</YSMC><GHF>14</GHF><GZRQ>2018-12-17</GZRQ><YSDM>9114</YSDM><PBID>2018121710191141</PBID><KSMC>疼痛科</KSMC><KSDM>101</KSDM><TINGZHENBZ>0</TINGZHENBZ><ZBLB>1</ZBLB><GHLB>2</GHLB><HYSL>30</HYSL><SYHY>30</SYHY><YYRS>0</YYRS></PBXX></body>";
        String jsonObject = XML.toJSONObject(jsonArrayType).toString();
        com.alibaba.fastjson.JSONObject jsonType = JSON.parseObject(JSON.parseObject(jsonObject).get("body").toString());
        Object listArry = new JSONTokener(JSON.toJSONString(jsonType.get("PBXX"))).nextValue();
        if (listArry instanceof JSONObject) {
            System.out.println("====================JSONObject格式:" + listArry);
        } else if (listArry instanceof JSONArray) {
            System.out.println("=======================JSONArray格式:" + listArry);
        }
    }

    @Test
    public void testLocaDate() throws ParseException {
        String youWantTime = DateUtils.getYouWantTime("yyyy-MM-dd", new Date());
        Date ymdhms = DateUtils.stringToDate(youWantTime + " " + "08:10:12", "yyyy-MM-dd HH:mm:ss");
        Date hms = DateUtils.stringToDate("08:10:12", "HH:mm:ss");
        System.out.println(hms);
    }

    //oracle的insert
    @Test
    public void testRegConfirmOracle() {
        RegConfirm reg = new RegConfirm();
        reg.setId(null);
        reg.setHosId(Long.valueOf(220101));
        reg.setPatientNo("3287593287532986");
        reg.setAccessDate("2019-11-12");
        regConfirmMapper.insertConfirm(reg);
        System.out.println(reg.getId());
    }

    //oracle function
    @Test
    public void testFunction() {
        Long hosId = regConfirmMapper.getHosId("3287593287532985");
        System.out.println("函数返回{}==========" + hosId);
    }

    @Test
    public void testCallable() {

    }

    @Test
    public void testSelect() {
        System.out.println(JSON.toJSON(regConfirmMapper.selectByHosId(123432432L)));
    }

    @Test
    public void testJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "东芝");
        map.put("age", Integer.valueOf(23));
        map.put("id", Long.valueOf(32423423));
        map.put("money", new BigDecimal("22.33"));
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(JSON.toJSONString(map));
        String age = json.getString("age");
        Integer money = json.getInteger("money");
        System.out.println(age);
        System.out.println(money);
    }

    @Test
    public void testExcel() {
        List<User> users = userMapper.selectAll();
        Long start = System.currentTimeMillis();
        String filePath = "F:\\学生.xlsx";
        ArrayList<TableHeaderExcelProperty> data = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            TableHeaderExcelProperty table = new TableHeaderExcelProperty();
            table.setName(users.get(i).getUserName());
            table.setAge(users.get(i).getAge());
            table.setSchool(users.get(i).getEmail());
            data.add(table);
        }
        ExcelUtil.writeWithTemplate(filePath, data);
        Long end = System.currentTimeMillis();
        log.info("[{}]", end - start);
    }

    @Test
    public void testRestTemplate() {
        String s = "{\"hosId\":\"101274\",\"checkDate\":\"20191212\",\"merchantId\":\"weiji\"}";
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(s);
        String signContent = RSASignature.getSignContent(json);
        String sign = RSASignature.sign(signContent, environment.getProperty("his_pri_key"), "utf-8");
        json.put("sign", sign);
        FileCheckDTO fileCheck = JSON.parseObject(json.toString(), FileCheckDTO.class);
        String result = restTemplate.postForObject("http://127.0.0.1:8811/getCheckFile", fileCheck, String.class);

        com.alibaba.fastjson.JSONObject respData = JSON.parseObject(result);
        String respSignContent = RSASignature.getSignContent(json);
        boolean isSuccess = RSASignature.doCheck(respSignContent, respData.getString("sign"), environment.getProperty("his_pub_key"));
        if (!isSuccess) {
            System.out.println("验签失败");
        }

    }

    @Test
    public void testPinpline() {
        Jedis jedis = new Jedis("192.168.96.133", 6379);
        for (int i = 0; i < 10; i++) {
            Pipeline pipelined = jedis.pipelined();
            for (int j = i * 10; j < (i + 1) * 10; j++) {
                pipelined.hset("hashset:" + j, "field:" + j, "value:" + j);
            }
            pipelined.syncAndReturnAll();
        }
    }

    @Test
    public void testdelKeyByScan() {
        Jedis jedis = new Jedis("192.168.96.133", 7000);
        List<String> keys = getByScan(jedis);
        long start = System.currentTimeMillis();
        log.info("开始模糊删除set中的数据,keys" + keys);
        String[] array = keys.stream().toArray(String[]::new);
        jedis.del(array);
        long end = System.currentTimeMillis();
        log.info("删除成功耗时:" + String.valueOf(end - start));
    }

    private List<String> getByScan(Jedis jedis) {
        List<String> list = new ArrayList<>();
        ScanParams params = new ScanParams();
        params.match("hashset:*");
        params.count(100);
        String cursor = "0";
        while (true) {
            ScanResult<String> scanResult = jedis.scan(cursor, params);
            List<String> resultList = scanResult.getResult();
            list.addAll(resultList);
            cursor = scanResult.getStringCursor();
            if ("0".equals(cursor)) {
                break;
            }
            log.info(" getByScan 查到的数据集是 ============ " + list);
        }
        return list;
    }


    @Test
    public void testSentinel(){
        String myMaster = "mymaster";
        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("192.168.96.133:26379");
        sentinelSet.add("192.168.96.133:26380");
        sentinelSet.add("192.168.96.133:26381");
        JedisSentinelPool sentinelPool = new JedisSentinelPool(myMaster,sentinelSet);

        int count =0;
        while (true){
            Jedis jedis = null;
            count++;
            try {
                jedis = sentinelPool.getResource();
                int random = new Random().nextInt(100000);
                String key = "k-"+random;
                String value = "v-"+random;
                jedis.set(key,value);
                if(count % 100 == 0){
                    log.info("{} values is {}",key,jedis.get(key));
                }
                Thread.sleep(10);
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }finally {
                if(jedis!=null){
                    jedis.close();
                }
            }
        }
    }

    @Test
    public void testAddUser() {

        new Thread(()->{
            try {
                userService.addUser("萌萌",0,"1","2020-05-03 00:00:01",12,"1315081410@qq.com","15188888888");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                userService.addUser("萌萌",0,"1","2020-05-03 00:00:00",12,"1315081410@qq.com","15188888888");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        },"B").start();

    }

    @Test
    public void testCblib(){
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        json.put("serviceName","orderServiceImpl");
        cglibCommon.Common(json);
    }

}

