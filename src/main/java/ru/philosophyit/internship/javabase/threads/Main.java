package ru.philosophyit.internship.javabase.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // Почему при вызове executorService.shutdown(); программа продолжает свое исполнение ?
    //Ответ: не продолжает
    // Почему если убрать строчку 28 (executorService.shutdown()) программа не прекратит свое исполнение
    // даже после завершения всех тасок в executorService ? Ответ выше
    // Почему при работе тасок executorService в консоль в секунду попадает всего 4 сообщения, тогда как тасок в executorService - 16?
    //Ответ: так как поток засыпает на секунду а в это время работает  цикл while в someDaemon() и за секунду пока спит цикл успевает сработать только 4 строчки
    public static void main(String[] args) {
        startSomeDaemon();

        int num = getThreadsCount();

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < num; i++) {
            int captureId = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.err.println(String.format("Hello from %d callable", captureId));
            });
        }
        //executorService.shutdown();
    }

    private static int getThreadsCount() {
        return 16;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            int num = Integer.valueOf(reader.readLine());
//            return num;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return 0;
    }

    private static void startSomeDaemon() {
        Thread thread = new Thread(() -> {
            int t = 0;
            while (true) {
                System.err.println(t++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
