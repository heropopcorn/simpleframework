package org.simpleframework.inject;

import com.xuan.controller.frontend.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

public class DependencyInjectTest {

    @DisplayName("依赖注入")
    @Test
    public void doIocTest() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.xuan");
        Assertions.assertEquals(true, beanContainer.isLoaded());
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, mainPageController instanceof MainPageController);
        Assertions.assertEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIoc();
        Assertions.assertNotNull(mainPageController.getHeadLineShopCategoryCombineService());

    }
}
