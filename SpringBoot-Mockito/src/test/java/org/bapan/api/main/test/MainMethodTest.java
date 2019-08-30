/**
 * 
 */
package org.bapan.api.main.test;

import org.bapan.MockitoDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ruptam
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainMethodTest {

	@Test
	public void main() {
		MockitoDemo.main(new String[] {});
	}
}
