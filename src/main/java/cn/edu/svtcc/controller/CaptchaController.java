package cn.edu.svtcc.controller;

import cn.edu.svtcc.common.constant.Constants;
import cn.edu.svtcc.common.domain.AjaxResult;
import cn.edu.svtcc.common.utils.Base64Utils;
import com.google.code.kaptcha.Producer;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {
    @Resource(name="captchaProducer")
    private Producer captchaProducer;

    @Resource(name="captchaProducerMath")
    private Producer captchaProducerMath;

    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        AjaxResult ajax = AjaxResult.success();

        // 保存验证码信息
//        String uuid = IdUtils.simpleUUID();
//        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = "char";
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            System.out.println("code="+capText);
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
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

//        ajax.put("uuid", uuid);
        ajax.put("img", Base64Utils.encode(os.toByteArray()));
        return ajax;
    }
}
