package com.exchange.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.Accounts;
import com.exchange.dto.LoginDto;
import com.exchange.service.AccountService;
import com.exchange.util.CaptchaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private Producer captchaProducer;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private AccountService accountService;
	@Value("${spring.mail.username}")
	private String from;
	/**
	 *临时使⽤10分钟有效，⽅便测试
	 */
	private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;
	/**
	 * 获取图形验证码
	 * @param request
	 * @param response
	 */
	@GetMapping("get_captcha")
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
		String captchaText = captchaProducer.createText();
		System.out.println(captchaText+"===========================");
		//存储
		redisTemplate.opsForValue().set(CaptchaUtils.getCaptchaKey(request),captchaText,CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);
		BufferedImage bufferedImage = captchaProducer.createImage(captchaText);

		ServletOutputStream outputStream = null;
		try {

			outputStream = response.getOutputStream();
			ImageIO.write(bufferedImage,"jpg",outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
		}
	}

	@PostMapping(value = "/email_captcha",produces = "application/json;charset=utf-8")
	public ApiResponse getCaptcha(HttpServletRequest request, @RequestBody LoginDto loginDto) throws UnsupportedEncodingException {
		Accounts account = accountService.getOne(new QueryWrapper<Accounts>().eq("name", loginDto.getName()).le("accountType",1).last("limit 1"));
		Assert.notNull(account,"用户不存在");
		String email=loginDto.getEmail();

		if(account.getEmail().equals(email)){
			System.out.println("email "+email);
			String captchaText = captchaProducer.createText();
			System.out.println(captchaText+"===========================");
			//存储
			redisTemplate.opsForValue().set(CaptchaUtils.getCaptchaKey(request),captchaText,CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);
			//发送邮箱
			SimpleMailMessage message=new SimpleMailMessage();
			message.setText("您本次登录的邮箱验证码是: "+captchaText+", 10分钟内有效, 请妥善保管!");
			message.setSentDate(new Date());
			message.setSubject("【SUSTech Exchange】邮箱登录");
			message.setTo(email);
			message.setFrom(from);
			javaMailSender.send(message);
			return ApiResponse.success("发送成功");
		}else{
			return ApiResponse.fail("用户名与邮箱不对应");
		}



	}
//	/**
//	 * 获取缓存的key
//	 * @param request
//	 * @return
//	 */
//	public static String getCaptchaKey(HttpServletRequest request){
//		String ip = CaptchaUtils.getIpAddr(request);
//		String userAgent = request.getHeader("User-Agent");
//		String key = "user-service:captcha:"+ CaptchaUtils.MD5(ip+userAgent);
//		return key;
//	}

	/**
	 * ⽀持⼿机号、邮箱发送验证码
	 * @return
	 */
//	@GetMapping("get_code")
//	public JsonResult sendRegisterCode(@RequestParam(value = "to", required = true)String to,
//									   @RequestParam(value = "captcha", required = true)String captcha,
//									   HttpServletRequest request){
//		String key = CaptchaUtils.getCaptchaKey(request);
//		String cacheCaptcha = redisTemplate.opsForValue().get(key);
//		if(captcha!=null && cacheCaptcha!=null && cacheCaptcha.equalsIgnoreCase(captcha)) {
//			redisTemplate.delete(key);
//			//TODO 发送验证码
//			return JsonResult.buildSuccess();
//		}else {
//			return JsonResult.fail("图形验证码错误");
//		}
//	}
}
