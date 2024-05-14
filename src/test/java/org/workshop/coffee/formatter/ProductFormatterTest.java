package org.workshop.coffee.formatter;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductFormatterTest {
	@Test
	public void testFormatting(){
		ProductFormatter pf = new ProductFormatter(null);
		assertEquals(pf.print(null, null), "");
	}
}
