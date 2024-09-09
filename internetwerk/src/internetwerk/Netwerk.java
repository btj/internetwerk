package internetwerk;

import java.util.HashSet;
import java.util.Set;

/**
 * @invar | getToestellen().stream().allMatch(t -> t != null && t.getNetwerken().contains(this))
 */
public class Netwerk {
	
	/**
	 * @invar | toestellen != null
	 * @invar | toestellen.stream().allMatch(t -> t != null && t.netwerken.contains(this))
	 * 
	 * @representationObject
	 * @peerObjects
	 */
	Set<Toestel> toestellen = new HashSet<>();

	/**
	 * @post | result != null
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Toestel> getToestellen() {
		return Set.copyOf(toestellen);
	}
	
	/**
	 * @post | getToestellen().isEmpty()
	 */
	public Netwerk() {}
	
}