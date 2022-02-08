import com.tencent.wxcloudrun.dao.UserinfoMapper;
import com.tencent.wxcloudrun.model.Userinfo;
import com.tencent.wxcloudrun.service.UserinfoService;
import com.tencent.wxcloudrun.service.impl.UserinfoServiceImpl;
import lombok.val;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {

    @Autowired
    UserinfoService userinfoService;

    @org.junit.Test
    public void testUser(){
        Userinfo userinfo = new Userinfo();
        userinfo.setId("abv");
        userinfo.setSex("male");
        userinfo.setLocation("beijing");

        userinfoService.upsertUser(userinfo);
    }

}