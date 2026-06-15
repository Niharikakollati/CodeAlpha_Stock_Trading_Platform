import java.util.HashMap;
import java.util.Scanner;

public class StockTradingPlatform {

    static double balance = 100000.0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<String, Double> stockPrices = new HashMap<>();
        HashMap<String, Integer> portfolio = new HashMap<>();

        stockPrices.put("TCS", 3500.0);
        stockPrices.put("INFY", 1500.0);
        stockPrices.put("RELIANCE", 2800.0);
        stockPrices.put("HDFCBANK", 1700.0);

        while (true) {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Balance");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Stocks:");
                    for (String stock : stockPrices.keySet()) {
                        System.out.println(stock + " : ₹" + stockPrices.get(stock));
                    }
                    break;

                case 2:
                    System.out.print("Enter Stock Name: ");
                    String buyStock = sc.next().toUpperCase();

                    if (!stockPrices.containsKey(buyStock)) {
                        System.out.println("Invalid Stock!");
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();

                    double buyCost = stockPrices.get(buyStock) * buyQty;

                    if (buyCost > balance) {
                        System.out.println("Insufficient Balance!");
                    } else {
                        balance -= buyCost;
                        portfolio.put(buyStock,
                                portfolio.getOrDefault(buyStock, 0) + buyQty);

                        System.out.println("Stock Purchased Successfully!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Stock Name: ");
                    String sellStock = sc.next().toUpperCase();

                    if (!portfolio.containsKey(sellStock)) {
                        System.out.println("You don't own this stock!");
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();

                    int ownedQty = portfolio.get(sellStock);

                    if (sellQty > ownedQty) {
                        System.out.println("Not enough shares!");
                    } else {

                        double sellValue =
                                stockPrices.get(sellStock) * sellQty;

                        balance += sellValue;

                        portfolio.put(sellStock,
                                ownedQty - sellQty);

                        if (portfolio.get(sellStock) == 0) {
                            portfolio.remove(sellStock);
                        }

                        System.out.println("Stock Sold Successfully!");
                    }
                    break;

                case 4:
                    System.out.println("\n===== PORTFOLIO =====");

                    if (portfolio.isEmpty()) {
                        System.out.println("No Stocks Owned.");
                    } else {
                        for (String stock : portfolio.keySet()) {
                            System.out.println(
                                    stock + " : "
                                            + portfolio.get(stock)
                                            + " shares");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Available Balance: ₹" + balance);
                    break;

                case 6:
                    System.out.println("Thank you for using Stock Trading Platform!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}