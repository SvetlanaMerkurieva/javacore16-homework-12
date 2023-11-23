package src.task2;

import java.util.ArrayList;
import java.util.Scanner;

public class NumberThreadResult {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введіть число та натисніть Enter: ");
        int enteredNumber = in.nextInt();
        in.close();

        NumberThread A = new NumberThread(n -> {
            if (n % 3 == 0 && n % 5 != 0) {
                System.out.print("Fizz, ");
            }
        });
        NumberThread B = new NumberThread(n -> {
            if (n % 3 != 0 && n % 5 == 0) {
                System.out.print("Buzz, ");
            }
        });
        NumberThread C = new NumberThread(n -> {
            if (n % 3 == 0 && n % 5 == 0) {
                System.out.print("FizzBuzz, ");
            }
        });
        NumberThread D = new NumberThread(n -> {
            if (n % 3 != 0 && n % 5 != 0) {
                System.out.print(n + ", ");
            }
        });

        ArrayList<NumberThread> threads = new ArrayList<>();

        threads.add(A);
        threads.add(B);
        threads.add(C);
        threads.add(D);

        for (NumberThread thread: threads) {
            thread.start();
        }

        for (int i = 1; i <= enteredNumber; i++) {
            for (NumberThread thread: threads) {
                thread.process(i);
            }

            while(true) {
                int counter = 0;
                for (NumberThread thread: threads) {
                    if (thread.processed()) {
                        counter++;
                    }
                }
                    if (counter  == threads.size()) {
                        break;
                    }
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Кінець)))");
    }
}
