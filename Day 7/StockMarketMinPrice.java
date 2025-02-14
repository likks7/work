import java.util.*;

public class StockMarketMinPrice {
    public static List<Integer> minStockPrices(int[] prices, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < prices.length; i++) {
            // Remove elements out of the window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove elements that are larger than the current price
            while (!deque.isEmpty() && prices[deque.peekLast()] > prices[i]) {
                deque.pollLast();
            }

            // Add current element index to the deque
            deque.offerLast(i);

            // Start adding minimum values to result once window size reaches k
            if (i >= k - 1) {
                result.add(prices[deque.peekFirst()]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of days: ");
        int n = scanner.nextInt();
        
        int[] prices = new int[n];
        System.out.println("Enter stock prices: ");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        
        System.out.print("Enter window size (k): ");
        int k = scanner.nextInt();
        
        scanner.close();

        List<Integer> minPrices = minStockPrices(prices, k);
        System.out.println("Minimum stock prices over " + k + "-day windows: " + minPrices);
    }
}
