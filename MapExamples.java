import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapExamples {
    public static void main(String[] args) {
        Map<Integer,String> sectorMap=new HashMap<>();
        sectorMap.put(10,"HealthCare");
        sectorMap.put(11,"Basic Materials");
        sectorMap.put(12,"Technology");
        sectorMap.put(13,"Real Estate");
        sectorMap.put(14,"Finance");

        System.out.println(sectorMap);
        sectorMap.put(14,"Energy");
        sectorMap.put(21,"HealthCare");
        sectorMap.remove(21);
        System.out.println("Key 11 has value: "+sectorMap.get(11));
        System.out.println("Does the map contain key 15?: "+sectorMap.containsKey(15));
        System.out.println("Does the map contain value Technology?: "+sectorMap.containsValue("Technology"));

        //Iterating a map method 1 BiConsumer
        sectorMap.forEach((sectorId,sectorName)-> System.out.println("Key is: "+sectorId+" , "+"Value is: "+sectorName));
        System.out.println();
        //Iterating a map Method 2 using keySet
        Set<Integer> keySet = sectorMap.keySet();

        keySet.forEach(sectorId->System.out.println("Key is: "+sectorId+" , "+"Value is: "+sectorMap.get(sectorId)));

        //compute if absent- checks a key. if not present in map, it wil computes its value from the function and add it to the map. else, it will return the value of the key
        String computeIfAbsentString = sectorMap.computeIfAbsent(85, key -> "Test" + key);
        System.out.println(computeIfAbsentString);

//        Compute if present- looks for a key, if present in the map, returns a string else null
        String computeIfPresentString = sectorMap.computeIfPresent(88, (sectorId, sectorName) -> sectorId + sectorName);
        System.out.println(computeIfPresentString);

        List<String> tickerList= List.of("APPL","AMD","GOOGL","MSFT","NVDA","NVDA","NVDA","AMD","MSFT");

        Map<String,Integer> countsMap= new HashMap<>();
        tickerList.forEach(ticker->{
            Integer count = countsMap.get(ticker);
            if (count==null){
                count=0;
            }
            count++;
            countsMap.put(ticker,count);
        });
        System.out.println(countsMap);

        Map<String,Integer> anotherCountMap=new HashMap<>();
        for (String ticker : tickerList) {
            Integer count = anotherCountMap.computeIfAbsent(ticker, key -> 0) + 1;
            anotherCountMap.put(ticker,count);
        }
        System.out.println(anotherCountMap);

    }
}
