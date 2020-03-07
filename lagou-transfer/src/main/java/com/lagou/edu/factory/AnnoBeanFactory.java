package com.lagou.edu.factory;

import com.google.common.collect.Sets;
import com.lagou.edu.annotation.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.reflections.Reflections;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hsr
 *
 * 工厂类，生产对象（使用反射技术）
 */
public class AnnoBeanFactory {


    private static Map<String,Object> map = new HashMap<>();  // 存储对象


    static {
        // 任务一：扫描package，通过反射技术实例化对象并且存储待用（map集合）
        // 加载xml
        try {
            //扫描目标package，通过反射技术实例化对象并且存储待用（map集合）
            Reflections ref = new Reflections("com.lagou.edu");
            Set<Class<?>> set1 = ref.getTypesAnnotatedWith(Service.class);
            for (Class<?> c : set1) {
                Object bean = c.newInstance();
                Service annotation = c.getAnnotation(Service.class);
                map.put(annotation.value(), bean);
            }
            Set<Class<?>> set2 = ref.getTypesAnnotatedWith(Repository.class);
            for (Class<?> c : set2) {
                Object bean = c.newInstance();
                Repository annotation = c.getAnnotation(Repository.class);
                map.put(annotation.value(), bean);
            }
            Set<Class<?>> set3 = ref.getTypesAnnotatedWith(Component.class);
            for (Class<?> c : set3) {
                Object bean = c.newInstance();
                Component annotation = c.getAnnotation(Component.class);
                map.put(annotation.value(), bean);
            }
            Set<Class<?>> set4 = ref.getTypesAnnotatedWith(Transactional.class);
            for (Class<?> c : set4) {
                Object bean = c.newInstance();
                Transactional annotation = c.getAnnotation(Transactional.class);
                map.put(annotation.value(), bean);
            }

            // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
            // entrySet 获取key and value
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if(entry.getValue()!=null){
                    Object obj = entry.getValue();
                    // 获取对象的类
                    Class clazz = obj.getClass();
                    // 使用反射获取该类所有字段
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field f : fields) {
                        // 获取字段注解对象
                        Autowired autowired = f.getAnnotation(Autowired.class);
                        if (autowired != null) {
                            // 遍历父对象中的所有方法，找到"set" + name
                            Method[] methods = obj.getClass().getMethods();
                            for (int j = 0; j < methods.length; j++) {
                                Method method = methods[j];
                                if(method.getName().equalsIgnoreCase("set" + f.getName())) {  // 该方法就是 setAccountDao(AccountDao accountDao)
                                    method.invoke(obj,map.get(f.getName()));
                                }
                            }
                        }
                    }
                    map.put(entry.getKey(),obj);
                }
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }



    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static  Object getBean(String id) {
        return map.get(id);
    }

}
