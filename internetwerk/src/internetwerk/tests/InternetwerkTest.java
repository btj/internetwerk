package internetwerk.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import internetwerk.Netwerk;
import internetwerk.Toestel;

class InternetwerkTest {

	@Test
	void test() {
		Toestel pc = new Toestel();
		assertEquals(Set.of(), pc.getNetwerken());
		
		Netwerk wifi = new Netwerk();
		assertEquals(Set.of(), wifi.getToestellen());
		
		pc.verbind(wifi);
		assertEquals(Set.of(wifi), pc.getNetwerken());
		assertEquals(Set.of(pc), wifi.getToestellen());
		
		Toestel telefoon = new Toestel();
		telefoon.verbind(wifi);
		
		Netwerk vierg = new Netwerk();
		telefoon.verbind(vierg);
		
		Toestel server = new Toestel();
		server.verbind(vierg);
		
		assertEquals(Set.of(server, telefoon, pc), pc.getBereikbareToestellen(1));
		
		pc.ontkoppel(wifi);
		assertEquals(Set.of(), pc.getNetwerken());
		assertEquals(Set.of(telefoon), wifi.getToestellen());
	}

}
