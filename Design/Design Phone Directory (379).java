public class PhoneDirectory {
    private Set<Integer> used;
    private Queue<Integer> available;
    int maxNumbers;
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        used = new HashSet<Integer>();
        available = new LinkedList<Integer>();
        
        for (int i = 0; i < maxNumbers; i++) {
            available.offer(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (available.isEmpty()) {
            return -1;
        }
        
        Integer peek = available.poll();
        used.add(peek);
        return peek;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (number < 0 || number >= maxNumbers) {
            return false;
        }
        
        return !used.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (used.remove(number)) {
            available.offer(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
