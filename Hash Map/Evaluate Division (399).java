public class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> rec = new HashMap<String, Map<String, Double>>();
        double[] results = new double[queries.length];
        
        for (int i = 0; i < equations.length; i++) {
            String dividend = equations[i][0];
            String divisor = equations[i][1];
            double value = values[i];
            
            updateMap(rec, dividend, divisor, value);
            updateMap(rec, divisor, dividend, 1 / value);
        }
        
        for (int i = 0; i < queries.length; i++) {
            results[i] = findInMap(rec, queries[i][0], queries[i][1], 1, new HashSet<String>());
        }
        
        return results;
    }
    
    private void updateMap(Map<String, Map<String, Double>> rec, String dividend, String divisor, double value) {
        if (!rec.containsKey(dividend)) {
            rec.put(dividend, new HashMap<String, Double>());
        }
        
        rec.get(dividend).put(divisor, value);
    }
    
    private double findInMap(Map<String, Map<String, Double>> rec, String dividend, String divisor, double curr, Set<String> visited) {
        if (!rec.containsKey(dividend) || visited.contains(dividend)) {
            return -1;
        }
        
        if (dividend.equals(divisor)) {
            return curr;
        }
        
        visited.add(dividend);
        Map<String, Double> map = rec.get(dividend);
        
        for (String key : map.keySet()) {
            double result = findInMap(rec, key, divisor, curr * map.get(key), visited);
                
            if (result != -1) {
                return result;
            }
        }
        
        return -1;
    }
}
