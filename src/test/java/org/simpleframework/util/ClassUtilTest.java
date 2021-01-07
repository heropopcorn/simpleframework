package org.simpleframework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ClassUtilTest {
    @DisplayName("提取目标类方法")
    @Test
    public void extractPackageTest(){
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.xuan.entity");
        System.out.println(classSet);
        Assertions.assertEquals(4,classSet.size());
    }


}
