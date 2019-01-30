package org.oos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.oos.service.MahoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MahoutTests {

	@Setter(onMethod_=@Autowired)
	private MahoutService mahoutService;
	
	@Test
	public void contextLoads() {
		mahoutService.setTable();
	}

}
