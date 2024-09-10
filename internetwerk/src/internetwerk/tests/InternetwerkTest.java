package internetwerk.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import internetwerk.Netwerk;
import internetwerk.Toestel;

class InternetwerkTest {

	Toestel pc = new Toestel();
	Netwerk wifi = new Netwerk();
	
	@Test
	void testToestelConstructor() {
		assertEquals(Set.of(), pc.getNetwerken());
	}
	
	@Test
	void testNetwerkConstructor() {
		assertEquals(Set.of(), wifi.getToestellen());
	}
	
	@Test
	void testVerbind() {
		pc.verbind(wifi);
		assertEquals(Set.of(wifi), pc.getNetwerken());
		assertEquals(Set.of(pc), wifi.getToestellen());
	}
	
	@Test
	void testGetBereikbareToestellen() {
		pc.verbind(wifi);
		
		Toestel telefoon = new Toestel();
		telefoon.verbind(wifi);
		
		Netwerk vierg = new Netwerk();
		telefoon.verbind(vierg);
		
		Toestel server = new Toestel();
		server.verbind(vierg);
		
		assertEquals(Set.of(server, telefoon, pc), pc.getBereikbareToestellen(1));
	}
	
	@Test
	void testOntkoppel() {
		pc.verbind(wifi);
		pc.ontkoppel(wifi);
		
		assertEquals(Set.of(), pc.getNetwerken());
		assertEquals(Set.of(), wifi.getToestellen());
	}

}
