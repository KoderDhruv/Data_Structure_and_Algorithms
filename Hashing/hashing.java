import java.util.*;

class hashing {

	static class hashmap<K, V> { // generic
		private class Node {
			K key;
			V value;

			Node(K key, V value) {
				this.key = key;
				this.value = value;
			}
		}

		private int n = 0; // total elements
		private int N = 0; // size of linkedlist
		private LinkedList<Node> buckets[];

		@SuppressWarnings("unchecked")
		public hashmap() {
			this.N = 4;
			this.buckets = new LinkedList[4];
			for (int i = 0; i < 4; i++) {
				this.buckets[i] = new LinkedList<>();
			}
		}

		private int hashFunction(K key) {
			int hc = key.hashCode();
			return Math.abs(hc) % N;
		}

		private int searchLL(K key, int bi) {
			LinkedList<Node> ll = buckets[bi];
			int di = 0;
			for (int i = 0; i < ll.size(); i++) {
				Node node = ll.get(i);
				if (node.key == key) {
					return di;
				}
				di++;
			}
			return -1;
		}

		@SuppressWarnings("unchecked")
		private void rehash() {
			LinkedList<Node> oldBuck[] = buckets;
			buckets = new LinkedList[N * 2];
			N = N * 2;
			for (int i = 0; i < buckets.length; i++) {
				buckets[i] = new LinkedList<>();
			}
			for (int i = 0; i < oldBuck.length; i++) {
				LinkedList<Node> ll = oldBuck[i];
				for (int j = 0; j < ll.size(); j++) {
					Node node = ll.remove();
					put(node.key, node.value);
				}
			}
		}

		public void put(K key, V value) {
			int bi = hashFunction(key);
			int di = searchLL(key, bi);
			if (di != -1) {
				Node node = buckets[bi].get(di);
				node.value = value;
			} else {
				buckets[bi].add(new Node(key, value));
				n++;
			}
			double lambda = (double) n / N;
			if (lambda > 2.0) {
				rehash();
			}
		}

		public boolean containsKey(K key) {
			int bi = hashFunction(key);
			int di = searchLL(key, bi);
			if (di != -1) {
				return true;
			}
			return false;
		}

		public V get(K key) {
			int bi = hashFunction(key);
			int di = searchLL(key, bi);
			if (di != 1) {
				Node node = buckets[bi].get(di);
				return node.value;
			}
			return null;
		}

		public V remove(K key) {
			int bi = hashFunction(key);
			int di = searchLL(key, bi);
			if (di != -1) {
				n--;
				Node node = buckets[bi].remove(di);
				return node.value;
			}
			return null;
		}

		public ArrayList<K> keySet() {
			ArrayList<K> keys = new ArrayList<>();
			for (int i = 0; i < buckets.length; i++) {
				LinkedList<Node> ll = buckets[i];
				for (Node node : ll) {
					keys.add(node.key);
				}
			}
			return keys;
		}

		public boolean isEmpty() {
			return n == 0;
		}
	}

	public static void majorityElement(int arr[]) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
			// map.put(arr[i], map.getOrDefault(arr[i], 0)); Same functionality as above
		}
		for (Integer key : map.keySet()) {
			if (map.get(key) > arr.length / 3) {
				System.out.println(key);
			}
		}
	}

	public static boolean isAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {
			char ch = s1.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		for (int i = 0; i < s2.length(); i++) {
			char ch = s2.charAt(i);
			if (map.get(ch) != null) {
				if (map.get(ch) == 1) {
					map.remove(ch);
				} else {
					map.put(ch, map.get(ch) - 1);
				}
			} else {
				return false;
			}
		}
		return map.isEmpty();
	}

	public static String getStart(HashMap<String, String> tickets) {
		HashMap<String, String> revMap = new HashMap<>();
		for (String key : tickets.keySet()) {
			revMap.put(tickets.get(key), key);
		}
		for (String key : tickets.keySet()) {
			if (!revMap.containsKey(key)) {
				return key;
			}
		}
		return null;
	}

	public static void ticketsIternary(HashMap<String, String> tickets) {
		String start = getStart(tickets);
		System.out.print(start);
		for (String key : tickets.keySet()) {
			System.out.print("-> " + tickets.get(start));
			start = tickets.get(start);
		}
	}

	public static void largestSubArrayZeroSum(int arr[]) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int len = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum)) {
				len = Math.max(len, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		System.out.println(len);
	}

	public static void totalSubArrayKSum(int arr[], int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int count = 0;
		map.put(0, 1);
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		System.out.println(count);
	}

	public static void main(String args[]) {
		// Hashmap implementation using JCF
		// HashMap<String, Integer> hm = new HashMap<>();
		// hm.put("India", 100);
		// hm.put("China", 150);
		// hm.put("US", 50);
		// hm.put("Nepal", 5);
		// Set<String> keys = hm.keySet();
		// System.out.println(keys);
		// for (String k : keys) {
		// System.out.println("key= " + k + ", value= " + hm.get(k));
		// }
		// System.out.println("Initial Mappings are: " + hm);
		// System.out.println("The set is: " + hm.entrySet());

		// Implementation from scratch
		// hashmap<String, Integer> hm = new hashmap<>();
		// hm.put("India", 100);
		// hm.put("India", 10);
		// hm.put("China", 150);
		// hm.put("US", 50);
		// hm.put("Nepal", 5);
		// ArrayList<String> keys = hm.keySet();
		// System.out.println(keys);
		// for (String key : keys) {
		// System.out.println("key= " + key + ", value= " + hm.get(key));
		// }

		// Data remains in ordered fashion as it was inserted, doubly linked list is
		// used for implementation.
		// LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
		// Put, remove, get complexity becomes O(logn), KEYS ARE SORTED.
		// Implemented using Red Black trees.
		// TreeMap<String, Integer> tm = new TreeMap<>();

		// Majority Element
		// int arr[] = { 1, 3, 2, 5, 1, 3, 1, 5, 1 };
		// majorityElement(arr);

		// Valid Anagram
		// String s1 = "Dhruv";
		// String s2 = "Banuni";
		// if (isAnagram(s1, s2)) {
		// System.out.println("It is an Anagram.");
		// } else {
		// System.out.println("Not an Anagram.");
		// }

		// HashsSet, LinkedHashSet, TreeSet Implementation
		// HashSet<Integer> set = new HashSet<>();
		// set.add(1);
		// set.add(2);
		// set.add(5);
		// set.add(1);
		// System.out.println(set);
		// if (set.contains(2)) {
		// System.out.println("Element exists.");
		// } else {
		// System.out.println("No such element in set.");
		// }
		// System.out.println(set.size());
		// set.clear();
		// System.out.println(set.size());
		// System.out.println(set.isEmpty());
		// HashSet<String> cities=new HashSet<>();
		// cities.add("Delhi");
		// cities.add("Mumbai");
		// cities.add("Noida");
		// cities.add("Bengaluru");
		// Iterator it=cities.iterator();
		// while(it.hasNext()){
		// System.out.println(it.next());
		// }
		// for(String city: cities){
		// System.out.println(city);
		// }
		// LinkedHashSet<String> cities1=new LinkedHashSet<>(); // in order of addition
		// cities1.add("Delhi");
		// cities1.add("Mumbai");
		// cities1.add("Noida");
		// cities1.add("Bengaluru");
		// Iterator it1=cities1.iterator();
		// while(it1.hasNext()){
		// System.out.println(it1.next());
		// }
		// TreeSet<String> cities2=new TreeSet<>(); // sorted
		// cities2.add("Delhi");
		// cities2.add("Mumbai");
		// cities2.add("Noida");
		// cities2.add("Bengaluru");
		// Iterator it2=cities2.iterator();
		// while(it2.hasNext()){
		// System.out.println(it2.next());
		// }

		// Find Iternary from tickets
		// HashMap<String, String> tickets = new HashMap<>();
		// tickets.put("Chennai", "Bangaluru");
		// tickets.put("Mumbai", "Delhi");
		// tickets.put("Goa", "Chennai");
		// tickets.put("Delhi", "Goa");
		// ticketsIternary(tickets);

		// Largest SubArray with zero sum
		// int arr[] = { 15, -2, 2, -8, 1, 7, 1, 23 };
		// largestSubArrayZeroSum(arr);

		// No. of subarrays with sum k
		int arr[] = { 10, 2, -2, -20, 10 };
		int k = -10;
		totalSubArrayKSum(arr, k);
	}
}