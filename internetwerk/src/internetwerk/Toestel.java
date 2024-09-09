package internetwerk;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import logicalcollections.LogicalSet;

/**
 * @invar | getNetwerken().stream().allMatch(n -> n != null && n.getToestellen().contains(this))
 */
public class Toestel {
	
	/**
	 * @invar | netwerken != null
	 * @invar | netwerken.stream().allMatch(n -> n != null && n.toestellen.contains(this))
	 * 
	 * @representationObject
	 * @peerObjects
	 */
	Set<Netwerk> netwerken = new HashSet<>();
	
	/**
	 * @post | result != null
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Netwerk> getNetwerken() {
		return Set.copyOf(netwerken);
	}
	
	/**
	 * @post | getNetwerken().isEmpty()
	 */
	public Toestel() {}
	
	/**
	 * @throws IllegalArgumentException | netwerk == null
	 * @mutates_properties | getNetwerken(), netwerk.getToestellen()
	 * @post | getNetwerken().equals(LogicalSet.plus(old(getNetwerken()), netwerk))
	 * @post | netwerk.getToestellen().equals(LogicalSet.plus(old(netwerk.getToestellen()), this))
	 */
	public void verbind(Netwerk netwerk) {
		if (netwerk == null)
			throw new IllegalArgumentException("`netwerk` is null");
		
		netwerken.add(netwerk);
		netwerk.toestellen.add(this);
	}
	
	/**
	 * @pre | netwerk != null
	 * @mutates_properties | getNetwerken(), netwerk.getToestellen()
	 * @post | getNetwerken().equals(LogicalSet.minus(old(getNetwerken()), netwerk))
	 * @post | netwerk.getToestellen().equals(LogicalSet.minus(old(netwerk.getToestellen()), this))
	 */
	public void ontkoppel(Netwerk netwerk) {
		netwerken.remove(netwerk);
		netwerk.toestellen.remove(this);
	}
	
	public Set<Toestel> getBereikbareToestellen(int aantalTussenstations) {
		return netwerken.stream()
				        .flatMap(n -> n.toestellen.stream())
				        .flatMap(t ->
				            aantalTussenstations == 0 ?
				            	Stream.of(t)
				            :
				            	t.getBereikbareToestellen(aantalTussenstations - 1).stream()).collect(Collectors.toSet());
	}
	
}