package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.ItemController;
import com.example.demo.model.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private ItemController itemController;
	
	@Test
	public void getAllItems() {
		List<Item> items = itemController.getItems();
		assertThat(items.size()).isEqualTo(5);
	}
	
	@Test
	public void checkItemId() {
		Item sneakers = itemController.getItem(1);
		assertNotNull(sneakers);
		assertThat(sneakers.getAmount()).isEqualTo(79001);
	}
	
	@Test
	public void checkItemIdNotExsits() throws Exception {
		try {
		Item notFound = itemController.getItem(12);
		throw new Exception();
		} catch (RuntimeException runtimeException) {
			assertNotNull(runtimeException);
		}
	}
}