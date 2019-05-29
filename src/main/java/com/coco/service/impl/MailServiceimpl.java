package com.coco.service.impl;

import com.coco.entity.Result;
import com.coco.service.MailService;
import com.coco.utils.RandomSecuritycode;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

/**
 * @Classname MailServiceimpl
 * @Description TODO
 * @Date 19-5-29 下午6:19
 * @Created by ltt
 */

@Service("MailService")
public class MailServiceimpl implements MailService {

    public Result SendSecuritycode(String mail) {
        Result re = new Result();
        SimpleEmail email = new SimpleEmail();//创建一个HtmlEmail实例对象
        try {
            email.setHostName("smtp.qq.com");
            email.setCharset("utf-8");
            //收件人
            email.addTo(mail);
            email.setFrom("672107240@qq.com", "coco剧院票务有限公司");
            email.setAuthentication("672107240@qq.com", "sljbjdmdpyxtbfbh");
            email.setSSLOnConnect(true);
            String securitycode = RandomSecuritycode.achieveCode();
            re.setMessage(securitycode);
            email.setSubject("注册验证码");
            email.setMsg("尊敬的用户:你好!\n注册验证码为:"+ securitycode + "\n");
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
            re.setJudge(false);
        }
        re.setJudge(true);
        return re;
    }
}
