package Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 2011년 트랜잭션을 금액을 기준으로 오름차순 정렬 후 출력
        List<Transaction> tr2011 = transactions.stream()
                                                .filter(transaction -> transaction.getYear() == 2011)
                                                .sorted(Comparator.comparing(Transaction::getValue))
                                                .collect(Collectors.toList());
        System.out.println("tr2011 : "  + tr2011);
        System.out.println("=========================================");

        // Transaction에 있는 사람들의 도시를 중복없이 추출
        List<String> cities = transactions.stream()
                                            .map(transaction -> transaction.getTrader().getCity())
                                            .distinct()
                                            .collect(Collectors.toList());
        System.out.println("cities : " + cities);
        System.out.println("=========================================");

        // Transaction 내에서 Cambridge 도시에서 거래한 Trader만 중복없이 이름을 기준으로 오름차순 정렬 후 추출
        List<Trader> traders = transactions.stream()
                                            .map(Transaction::getTrader)
                                            .filter(trader -> trader.getCity().equals("Cambridge"))
                                            .distinct()
                                            .sorted(Comparator.comparing(Trader::getName))
                                            .collect(Collectors.toList());
        System.out.println("traders : " + traders);
        System.out.println("=========================================");

        // Transaction에 있는 이름을 Map 후 중복없이 오름차순 정렬하여 하나의 문자열로 추출
        String traderStr = transactions.stream()
                                        .map(transaction -> transaction.getTrader().getName())
                                        .distinct()
                                        .sorted()
                                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println("traderStr : " + traderStr);
        System.out.println("=========================================");

        // Transaction 내 Trader의 도시가 Milan인 element가 하나로 있는지 확인
        boolean milanBased = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("milanBased : " + milanBased);

        // Transaction 내 'Milan' 도시에서 거래한 Trader의 도시를 모두 Cambridge 셋팅
        transactions.stream()
                    .map(Transaction::getTrader)
                    .filter(trader -> trader.getCity().equals("Milan"))
                    .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println("transactions : " + transactions);

        // Transaction 내 최대 금액 추출
        int highestValue = transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
        System.out.println("highestValue : " + highestValue);
    }
}
