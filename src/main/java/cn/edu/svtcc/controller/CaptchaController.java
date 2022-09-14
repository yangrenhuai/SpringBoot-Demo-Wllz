package cn.edu.svtcc.controller;

import cn.edu.svtcc.common.constant.Constants;
import cn.edu.svtcc.common.domain.AjaxResult;
import cn.edu.svtcc.common.utils.Base64Utils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class CaptchaController {
    @Resource(name="captchaProducer")
    private Producer captchaProducer;

    @Resource(name="captchaProducerMath")
    private Producer captchaProducerMath;

    @Value("${kaptcha.type}")
    private String captchaType;

    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        AjaxResult ajax = AjaxResult.success();

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);

            //将答案存储在Session中
            request.getSession().setAttribute(Constants.CAPTCHA_CODE_SESSION_KEY,code);
            System.out.println("code="+code);
        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
            //将答案存储在Session中
            request.getSession().setAttribute(Constants.CAPTCHA_CODE_SESSION_KEY,capStr);
        }

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("img", Base64Utils.encode(os.toByteArray()));

        //2分钟后删除Session中的验证码
        this.removeCapchaCode(request.getSession());

        return ajax;
    }

    /**
     * 设置2分钟后删除session中的验证码
     *
     */
    private void removeCapchaCode(HttpSession session) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 删除session中存的验证码
                session.removeAttribute(Constants.CAPTCHA_CODE_SESSION_KEY);
                timer.cancel();
            }
        }, 1000 * 60 * Constants.CAPTCHA_EXPIRATION);
    }

}
