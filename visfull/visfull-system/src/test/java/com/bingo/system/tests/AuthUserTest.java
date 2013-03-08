package com.bingo.system.tests;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import com.visfull.system.dao.AuthResourceDao;
import com.visfull.system.dao.AuthUserDao;
import com.visfull.system.domain.AuthResource;
import com.visfull.system.domain.AuthUser;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class AuthUserTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AuthUserDao authUserDao;
    @Autowired
    private AuthResourceDao authResourceDao;

    @Test
    public void testAuthUserDao() {
        AuthUser authUser = new AuthUser();
        authUser.setUserName("bingosssssssssssssss");
        authUser.setUserType("user");
        authUser.setCreateDate(new Date());
        authUserDao.save(authUser);
    }

    @Test
    public void testAuthResource() {

        AuthResource authResource;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            authResource = new AuthResource();
            authResource.setResourceName("测试菜单"+i);
            authResource.setResourceType("MENU");
            authResource.setResourcePid(0L);
            authResource.setTargetUrl("#");
            authResource.setCreateDate(new Date());
            authResourceDao.save(authResource);
            if (i!=9) {
                sb.append(authResource.getId()).append(",");
            }else {
                sb.append(authResource.getId());
            }
            
        }
        
        List<AuthResource> authResources = authResourceDao.findAuthResourcesByIds(sb.toString(), "MENU");
        Assert.isTrue(authResources.size()>0);
    }
    @Test
    public void testAuthResourceByType(){
        List<AuthResource> authResources = authResourceDao.findAuthResourcesByType("MENU");
        Assert.isTrue(authResources.size()>0);
    }

}
