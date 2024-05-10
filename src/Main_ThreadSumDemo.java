import java.util.Random;

public class Main_ThreadSumDemo {
    private static final int ARRAY_SIZE = 20;
    private static final int NUM_THREADS = 4; // Defina o número desejado de threads

    public static void main(String[] args) {
        // Cria um array com valores aleatórios
        int[] array = generateRandomArray(ARRAY_SIZE);

        // Garante que o tamanho do array seja múltiplo do número de threads
        int chunkSize = ARRAY_SIZE / NUM_THREADS;

        // Array para armazenar as threads
        ThreadSum[] threads = new ThreadSum[NUM_THREADS];

        // Inicializa e inicia as threads
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int end = (i == NUM_THREADS - 1) ? ARRAY_SIZE - 1 : start + chunkSize - 1;

            threads[i] = new ThreadSum(array, i, start, end);
            threads[i].start();
        }

        // Aguarda todas as threads completarem
        try {
            for (ThreadSum thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Soma os resultados parciais de todas as threads
        int totalSum = 0;
        for (ThreadSum thread : threads) {
            totalSum += thread.getSumResult();
        }

        System.out.println("Soma total: " + totalSum);
    }

    // Método auxiliar para gerar um array com valores inteiros aleatórios
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // Gera valores aleatórios entre 0 e 99
        }
        return array;
    }
}