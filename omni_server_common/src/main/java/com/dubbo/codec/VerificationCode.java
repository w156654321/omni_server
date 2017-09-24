package com.dubbo.codec;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * 验证码生成工具类
 *
 * @author zhenghua.yang 
 * @date 2016年1月29日 上午11:12:36 
 * @version V1.0
 */
public class VerificationCode {

	/**
	 * 生成验证码
	 *
	 * @author zhenghua.yang  
	 * @date 2016年1月29日 上午11:15:13 
	 * @return
	 * @return String
	 * @throws
	 */
	public static Map<String, Object> generateCode() {
		return generateCode(0, 0, 1);
	}

	/**
	 * 生成验证码
	 *
	 * @author zhenghua.yang  
	 * @date 2016年1月29日 上午11:12:16 
	 * @param width 定义图片的width
	 * @param height 定义图片的height
	 * @param codeCount 定义图片上显示验证码的个数
	 * @return
	 * @return String
	 * @throws
	 */
	public static Map<String, Object> generateCode(int width, int height, int codeCount) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		int xx = 15;
		int fontHeight = 18;
		int codeY = 16;
		
		// 干扰线数量
		int interferingLine = 1;

		if (width <= 0) {
			width = 90;
		}

		if (height <= 0) {
			height = 20;
		}

		if (codeCount <= 0) {
			codeCount = 4;
		}

		// char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
		// 'J', 'K',
		// 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
		// 'Z',
		// 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		// 'o',
		// 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
		// '3',
		// '4', '5', '6', '7', '8', '9' };

		char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		// Graphics2D gd = (Graphics2D) buffImg.getGraphics();

		Graphics graphics = buffImg.getGraphics();

		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		graphics.setFont(font);

		// 画边框。
		graphics.setColor(Color.BLACK);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 随机产生interferingLine条干扰线，使图象中的认证码不易被其它程序探测到。
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < interferingLine; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
			
			// 用随机产生的颜色将验证码绘制到图像中。
			graphics.setColor(color);
			graphics.drawString(code, (i + 1) * xx, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}

		resultMap.put("buffImg", buffImg);
		resultMap.put("code", randomCode.toString());

		return resultMap;
	}

	public static void main(String[] args) {
		Map<String, Object> resultMap = null;

		for (int i = 0; i < 100; i++) {
			resultMap = generateCode();
			System.out.println(resultMap.get("buffImg"));
			System.out.println(resultMap.get("code"));

		}
	}

}
