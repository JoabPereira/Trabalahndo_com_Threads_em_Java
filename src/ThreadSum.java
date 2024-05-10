 class ThreadSum extends Thread {
        private int[] array;
        private int threadId;
        private int inicioIdx;
        private int fimIdx;
        private int sumResult;

        public ThreadSum(int[] array, int threadId, int startIdx, int endIdx) {
            this.array = array;
            this.threadId = threadId;
            this.inicioIdx = startIdx;
            this.fimIdx = endIdx;
        }

        public void run() {
            System.out.println("Thread " + threadId + " está somando os elementos de " + inicioIdx + " a " + fimIdx);

            // Soma os elementos do array para este intervalo
            for (int i = inicioIdx; i <= fimIdx; i++) {
                sumResult += array[i];
            }

            System.out.println("Thread " + threadId + " concluiu. Soma parcial: " + sumResult);
        }

        public int getSumResult() {
            return sumResult;
        }
    }
