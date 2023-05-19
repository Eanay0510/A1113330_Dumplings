import java.util.Random;

class A1113330_Dumplings {
    private static final int PORK_DUMPLINGS = 5000;
    private static final int BEEF_DUMPLINGS = 3000;
    private static final int VEGETABLE_DUMPLINGS = 1000;

    private static int porkDumplingsRemaining = PORK_DUMPLINGS;
    private static int beefDumplingsRemaining = BEEF_DUMPLINGS;
    private static int vegetableDumplingsRemaining = VEGETABLE_DUMPLINGS;

    private static Object lock = new Object();

    static class Customer implements Runnable {
        private static Random random = new Random();
        private static int totalCustomers = 0;

        private int customerNumber;

        Customer() {
            customerNumber = ++totalCustomers;
        }

        @Override
        public void run() {
            int dumplingsOrdered = random.nextInt(41) + 10;
            int dumplingType = random.nextInt(3);

            synchronized (lock) {
                switch (dumplingType) {
                    case 0:
                        if (porkDumplingsRemaining >= dumplingsOrdered) {
                            porkDumplingsRemaining -= dumplingsOrdered;
                            System.out.printf("顧客 %d 點了 %d 顆豬肉水餃\n", customerNumber, dumplingsOrdered);
                        } else {
                            System.out.printf("顧客 %d 點了 %d 顆豬肉水餃，但是沒有足夠的水餃了\n", customerNumber, dumplingsOrdered);
                        }
                        break;
                    case 1:
                        if (beefDumplingsRemaining >= dumplingsOrdered) {
                            beefDumplingsRemaining -= dumplingsOrdered;
                            System.out.printf("顧客 %d 點了 %d 顆牛肉水餃\n", customerNumber, dumplingsOrdered);
                        } else {
                            System.out.printf("顧客 %d 點了 %d 顆牛肉水餃，但是沒有足夠的水餃了\n", customerNumber, dumplingsOrdered);
                        }
                        break;
                    case 2:
                        if (vegetableDumplingsRemaining >= dumplingsOrdered) {
                            vegetableDumplingsRemaining -= dumplingsOrdered;
                            System.out.printf("顧客 %d 點了 %d 顆蔬菜水餃\n", customerNumber, dumplingsOrdered);
                        } else {
                            System.out.printf("顧客 %d 點了 %d 顆蔬菜水餃，但是沒有足夠的水餃了\n", customerNumber, dumplingsOrdered);
                        }
                        break;
                }
            }

            try {
                Thread.sleep(3000); // 等待服務生的時間
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int numberOfCustomers = 0; // 用戶輸入的顧客數目

        // 模擬點水餃的顧客
        Thread[] customers = new Thread[numberOfCustomers];
        for (int i = 0; i < numberOfCustomers; i++) {
            customers[i] = new Thread(new Customer());
            customers[i].start();
        }

        // 等待所有顧客點餐完畢
        for (int i = 0; i < numberOfCustomers; i++) {
            try {
                customers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("水餃已售完！");
    }
}

