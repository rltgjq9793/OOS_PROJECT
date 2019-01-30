package org.oos.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oos.domain.StoreVO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.java.Log;

@Log
@Component
public class StoreViewInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Cookie[] cks=request.getCookies();
		log.info("cookie checker................................................");
		boolean check=false;
		
		Object result=modelAndView.getModel().get("store");
		StoreVO storeVO=(StoreVO)result;
		
		log.info(""+result);
		if(result==null) {
			return;
		}
		
		
		Loop1:for (int i=0;i<cks.length;i++) {
			if(cks[i].getName().equals("sViewCookie")) {
				check=true;
				log.info("sVcookievalue:"+cks[i].getValue());
				
				String[] values=cks[i].getValue().split("_");
				for (String val : values) {
				
					if(val.equals(""+storeVO.getSno())) {
						break Loop1;
				}
				}
			
				String value=(String)(cks[i].getValue())+"_"+storeVO.getSno();
				Cookie viewCookie=new Cookie("sViewCookie",URLEncoder.encode(value,"UTF-8"));
				response.addCookie(viewCookie);
				log.info(cks[i].getValue());
				break;
			}
		}
		log.info("Login check result: "+check);
		if(check==false) {
		
			
			Cookie viewCookie=new Cookie("sViewCookie",URLEncoder.encode(""+storeVO.getSno(), "UTF-8"));
			viewCookie.setMaxAge(60*60*24);
			response.addCookie(viewCookie);
		
		
		}
	
		
		
	}
}