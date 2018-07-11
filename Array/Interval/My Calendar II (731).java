class MyCalendarTwo {
    private TreeMap<Integer, Integer> map;

    public MyCalendarTwo() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        map.putIfAbsent(start, 0);
        map.putIfAbsent(end, 0);
        map.put(start, map.get(start) + 1);
        map.put(end, map.get(end) - 1);
        int count = 0;
        
        for (int val : map.values()) {
            count += val;
            
            if (count > 2) {
                return false;
            }
        }
        
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
