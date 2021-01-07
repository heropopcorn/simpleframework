package org.simpleframework.core;

import com.xuan.controller.frontend.MainPageController;
import com.xuan.controller.superadmin.HeadLineOperationController;
import com.xuan.controller.superadmin.ShopCategoryOperationController;
import com.xuan.service.combine.HeadLineShopCategoryCombineService;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

import java.util.Set;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//控制单元测试执行的顺序,在方法上通过注解@Order控制顺序
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("容器加载测试")
    @Order(1)
    @Test
    public void loadBeansTest() {
        beanContainer.loadBeans("com.xuan");
        Set<Class<?>> classes = beanContainer.getClasses();
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
        }
        Assertions.assertEquals(2, beanContainer.size());
    }

    @DisplayName("测试getBean")
    @Order(2)
    @Test
    public void getBeanTest() {
        HeadLineOperationController bean = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        System.out.println(bean);
        Assertions.assertEquals(true, bean instanceof HeadLineOperationController);

        ShopCategoryOperationController bean2 = (ShopCategoryOperationController) beanContainer.getBean(ShopCategoryOperationController.class);
        System.out.println(bean2);
        Assertions.assertEquals(null, bean2);
    }

    @DisplayName("测试根据注解获取从容器冲获取类的方法")
    @Test
    @Order(3)
    public void getBeanByAnnotationTest() {
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(1, beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("根据接口获取实现类")
    @Test
    @Order(4)
    public void getBeanBySuperTest() {
        Assertions.assertEquals(1, beanContainer.getClassesBySuper(HeadLineShopCategoryCombineService.class).size());
    }
}
