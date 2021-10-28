public class MultiThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Стирать белье выполнена на "+i+"%");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Мыть посуду выполнена на "+i+"%");
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Пылесосить выполнена на "+i+"%");
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        /*
        Способ №1
        Task task1 = new Task("Стирать белье");
        Task task2 = new Task("Мыть посуду");
        Task task3 = new Task("Пылесосить");
        task1.start();
        task2.start();
        task3.start();*/

        /* Способ №2
        Thread thread1 = new Thread(new TaskRunnable("Стирать белье"));
        Thread thread2 = new Thread(new TaskRunnable("Мыть посуду"));
        Thread thread3 = new Thread(new TaskRunnable("Пылесосить"));
        thread1.start();
        thread2.start();
        thread3.start();*/
    }
}

/*
Способ №2
class TaskRunnable implements Runnable{
    String taskName;

    public TaskRunnable(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(taskName+" выполнена на "+i+"%");
        }
    }
}*/
/*
Способ №1
class Task extends Thread{
    String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public void run(){
        super.run();
        for (int i = 0; i < 100; i++) {
            System.out.println(taskName+" выполнена на "+i+"%");
        }
    }
}*/