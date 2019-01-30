package org.oos.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oos.domain.ProductVO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.java.Log;

@Log
@Component
public class ProductViewInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Cookie[] cks=request.getCookies();
		log.info("cookie checker................................................");
		boolean check=false;
		
		Object result=modelAndView.getModel().get("product");
		ProductVO productVO=(ProductVO)result;
		
		log.info("result"+result);
		if(result==null) {
			return;
		}
		
		
		Loop1:for (int i=0;i<cks.length;i++) {
			if(cks[i].getName().equals("pViewCookie")) {
				check=true;
				log.info("pVcookievalue:"+cks[i].getValue());
				
				String[] values=cks[i].getValue().split("_");
				for (String val : values) {
				
					if(val.equals(""+productVO.getPno())) {
						break Loop1;
				}
				}
			
				String value=(String)(cks[i].getValue())+"_"+productVO.getPno();
				Cookie viewCookie=new Cookie("pViewCookie",URLEncoder.encode(value,"UTF-8"));
				response.addCookie(viewCookie);
				log.info(cks[i].getValue());
				break;
			}
		}
		log.info("Login check result: "+check);
		if(check==false) {
		
			
			Cookie pviewCookie=new Cookie("pViewCookie",URLEncoder.encode(""+productVO.getPno(), "UTF-8"));
			pviewCookie.setMaxAge(60*60*24);
			response.addCookie(pviewCookie);
		
		
		}
	
		
		
	}
}